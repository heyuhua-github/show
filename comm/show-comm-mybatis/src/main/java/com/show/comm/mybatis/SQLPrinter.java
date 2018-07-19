package com.show.comm.mybatis;

/**
 * SQL打印服务
 * 
 * @author heyuhua
 * @date 2017年6月7日
 *
 */
public interface SQLPrinter {

	/**
	 * 打印SQL
	 * 
	 * @param sql
	 *            sql
	 * @param arg
	 *            参数
	 */
	void print(String sql, Object arg);

	/**
	 * 是否打印SQL
	 * 
	 * @return true:打印sql,false:不打印sql
	 */
	boolean showSql();

}
