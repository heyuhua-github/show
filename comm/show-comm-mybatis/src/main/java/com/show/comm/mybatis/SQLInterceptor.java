package com.show.comm.mybatis;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mybatis分页拦截器，拦截需要进行分页查询的操作，对其进行分页处理。<br/>
 * 
 * 拦截支持#c#标签自定义行数统计：<br/>
 * 未使用#c#标签时，拦截器会自动将select至最后一个from之间的sql代码替换成count(*)；<br/>
 * 使用#c#标签后，拦截器会将两段被#c#标签包围的 sql代码第一段处理为count sql，第二段处理为查询sql；<br/>
 * 
 * <pre>
 * 普通sql：select *** from ***<br/>
 * 使用#c#标签后：select #c# count(id) #c# id,name,... #c# from ***<br/>
 * </pre>
 * 
 * @author heyuhua
 * @date 2017年6月6日
 * 
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class SQLInterceptor implements Interceptor {

	/** 日志 */
	private static final Logger LOG = LoggerFactory.getLogger(SQLInterceptor.class);

	/** 自定义count统计标记 */
	public static final String COUNT_SPLIT = "#c#";

	/** SQL打印服务 */
	private SQLPrinter printer = null;

	public SQLInterceptor() {
		printer = SQLPrinterRegister.get();// 加载SQL打印服务
		LOG.info("初始化MyBatis拦截器并加载SQL打印服务：" + printer);
	}

	/** 打印sql */
	private void print(String sql, Object arg) {
		if (null != printer) {
			printer.print(sql, arg);
		}
	}

	/**
	 * 拦截Mybatis执行流程并处理方法参数存在Paging对象的查询
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
		StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
		BoundSql boundSql = delegate.getBoundSql();

		Object obj = boundSql.getParameterObject();// 获取参数
		Paging paging = null;
		if (obj instanceof Paging) {// 只有一个参数且参数为Paging对象
			paging = (Paging) obj;
		}
		if (null != paging) {
			// 通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
			MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");
			// 拦截到的prepare方法参数是一个Connection对象
			Connection connection = (Connection) invocation.getArgs()[0];

			// 给当前的paging参数对象设置总记录数
			amount(paging, mappedStatement, connection);
			if (paging.getAmount() > 0) {
				String sql = pagingSql(paging, boundSql.getSql());// 生成分页sql
				ReflectUtil.setFieldValue(boundSql, "sql", sql);
				print(sql, paging);
			} else {
				// 填充无结果sql
				ReflectUtil.setFieldValue(boundSql, "sql", "select 1 from dual where 1=0");
				// 移除其它查询属性
				ReflectUtil.setFieldValue(boundSql, "parameterMappings", null);
				ReflectUtil.setFieldValue(boundSql, "parameterObject", null);
				ReflectUtil.setFieldValue(boundSql, "additionalParameters", null);
				ReflectUtil.setFieldValue(boundSql, "metaParameters", null);
			}
		} else {
			print(boundSql.getSql(), boundSql.getParameterObject());
		}
		return invocation.proceed();
	}

	/**
	 * 拦截器对应的封装原始对象的方法
	 */
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * 设置注册拦截器时设定的属性 该方法，会在配置文件加载前执行
	 */
	public void setProperties(Properties properties) {
		// do nothing
	}

	/**
	 * 获取Oracle数据库的分页查询语句
	 * 
	 * @param paging
	 *            分页对象
	 * @param sql
	 *            原sql
	 * @return Oracle数据库的分页查询语句
	 */
	private String pagingSql(Paging paging, String sql) {
		String s;
		if (sql.indexOf(COUNT_SPLIT) >= 0) {
			String srr[] = sql.split(COUNT_SPLIT);
			s = srr[0] + " " + srr[2] + " " + srr[3];
		} else {
			s = sql;
		}
		StringBuffer sqlBuffer = new StringBuffer(s);

		// 检查排序
		Map<String, String> orders = paging.getOrders();
		if (null != orders) {
			String ends = s.toUpperCase();
			ends = ends.substring(ends.lastIndexOf("FROM"));
			if (ends.indexOf("ORDER BY") == -1) {
				sqlBuffer.append(" order by ");
			} else {
				sqlBuffer.append(" ,");
			}
			sqlBuffer.append(paging.getOrder());
		}

		// 填充limit
		int offset = (paging.getCurrentPage() - 1) * paging.getPageSize();
		sqlBuffer.append(" limit ").append(offset).append(",").append(paging.getPageSize());
		return sqlBuffer.toString();
	}

	/**
	 * 给当前的参数对象paging设置总记录数
	 * 
	 * @param paging
	 *            Mapper映射语句对应的参数对象
	 * @param mappedStatement
	 *            Mapper映射语句
	 * @param connection
	 *            当前的数据库连接
	 */
	private void amount(Paging paging, MappedStatement mappedStatement, Connection connection) {
		// 获取对应的BoundSql，这个BoundSql其实跟我们利用StatementHandler获取到的BoundSql是同一个对象。
		// delegate里面的boundSql也是通过mappedStatement.getBoundSql(paramObj)方法获取到的。
		BoundSql boundSql = mappedStatement.getBoundSql(paging);
		// 通过查询Sql语句获取到对应的计算总记录数的sql语句
		String sql = amountSql(boundSql.getSql());
		// 打印SQL
		print(sql, boundSql.getParameterObject());
		// 通过BoundSql获取对应的参数映射
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		// 利用Configuration、查询记录数的Sql语句countSql、参数映射关系parameterMappings和参数对象paging建立查询记录数对应的BoundSql对象。
		BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), sql, parameterMappings, paging);
		// 通过mappedStatement、参数对象paging和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象
		ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, paging, countBoundSql);
		// 通过connection建立一个countSql对应的PreparedStatement对象。
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(sql);
			// 通过parameterHandler给PreparedStatement对象设置参数
			parameterHandler.setParameters(pstmt);
			// 之后就是执行获取总记录数的Sql语句和获取结果了。
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int amount = rs.getInt(1);
				// 给当前的参数paging对象设置总记录数
				paging.setAmount(amount);
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 根据原Sql语句获取对应的查询总记录数的Sql语句
	 * 
	 * @param sql
	 * @return
	 */
	private String amountSql(String sql) {
		if (sql.indexOf(COUNT_SPLIT) > 0) {
			String srr[] = sql.split(COUNT_SPLIT);
			return amountSqlRmvOrder(srr[0] + " " + srr[1] + " " + srr[3]);
		} else {
			String s = sql.toUpperCase();
			if (s.indexOf("GROUP BY") > 0) {
				int index = s.lastIndexOf("FROM");
				return "select count(*) from (select 1 " + sql.substring(index) + ")";
			} else {
				int index = s.lastIndexOf("FROM");
				return amountSqlRmvOrder("select count(*) " + sql.substring(index));
			}
		}
	}

	/** 移除order by 条件 */
	private String amountSqlRmvOrder(String sql) {
		String s = sql.toUpperCase();
		int i = s.indexOf(" ORDER ");
		if (i > 1) {
			return sql.substring(0, i);
		} else {
			return sql;
		}
	}

	/**
	 * 利用反射进行操作的一个工具类
	 * 
	 */
	private static class ReflectUtil {

		/**
		 * 利用反射获取指定对象的指定属性
		 * 
		 * @param obj
		 *            目标对象
		 * @param fieldName
		 *            目标属性
		 * @return 目标属性的值
		 */
		public static Object getFieldValue(Object obj, String fieldName) {
			Object result = null;
			Field field = ReflectUtil.getField(obj, fieldName);
			if (field != null) {
				field.setAccessible(true);
				try {
					result = field.get(obj);
				} catch (IllegalArgumentException e) {
					LOG.error(e.getMessage(), e);
				} catch (IllegalAccessException e) {
					LOG.error(e.getMessage(), e);
				}
			}
			return result;
		}

		/**
		 * 利用反射获取指定对象里面的指定属性
		 * 
		 * @param obj
		 *            目标对象
		 * @param fieldName
		 *            目标属性
		 * @return 目标字段
		 */
		private static Field getField(Object obj, String fieldName) {
			Field field = null;
			for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
				try {
					field = clazz.getDeclaredField(fieldName);
					break;
				} catch (NoSuchFieldException e) {
					// 这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
				}
			}
			return field;
		}

		/**
		 * 利用反射设置指定对象的指定属性为指定的值
		 * 
		 * @param obj
		 *            目标对象
		 * @param fieldName
		 *            目标属性
		 * @param fieldValue
		 *            目标值
		 */
		public static void setFieldValue(Object obj, String fieldName, String fieldValue) {
			Field field = ReflectUtil.getField(obj, fieldName);
			if (field != null) {
				try {
					field.setAccessible(true);
					field.set(obj, fieldValue);
				} catch (IllegalArgumentException e) {
					LOG.error(e.getMessage(), e);
				} catch (IllegalAccessException e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
	}
}