package com.show.comm.mybatis;

import java.util.Arrays;
import java.util.List;

/**
 * SQL格式化工具
 * 
 * @author heyuhua
 * @date 2017年6月6日
 *
 */
public class SQLFormat {

	/** SQL格式化 */
	private static String f(String sql) {
		return sql.replaceAll("\\s", " ").replaceAll("\\s{2,}", " ");
	}

	/**
	 * SQL格式化
	 * 
	 * @param sql
	 *            SQL
	 * @return 格式化后的sql
	 */
	public static String fmt(String sql) {
		return null == sql ? null : f(sql);
	}

	/**
	 * SQL格式化
	 * 
	 * @param sql
	 *            SQL
	 * @param args
	 *            ?参数数组
	 * @return 格式化后的sql
	 */
	public static String fmt(String sql, Object... args) {
		if (null == sql) {
			return null;
		}
		String s = f(sql);
		if (null == args || args.length == 0) {
			return s;
		}
		int i = 0;
		while (sql.indexOf("?") != -1) {
			s = s.replaceFirst("\\?", "#arg" + i + "#");
			i++;
		}
		for (int j = 0; j < args.length; j++) {
			Object o = args[j];
			if (null == o) {
				s = s.replace("#arg" + j + "#", "null");
			} else if (o instanceof Number || o instanceof Boolean) {
				s = s.replace("#arg" + j + "#", o.toString());
			} else {
				s = s.replace("#arg" + j + "#", "'" + o.toString() + "'");
			}
		}
		return s.replaceAll("#arg([0-9]+)#", "\\?");
	}

	/**
	 * 获取用于调试显示的SQL信息
	 * 
	 * @param sql
	 *            SQL
	 * @param arg
	 *            参数
	 * @return 用于调试显示的sql信息
	 */
	public static String forPrint(String sql, Object arg) {
		StringBuilder info = new StringBuilder();
		String s = f(sql);
		char b = s.charAt(0);
		boolean cc = (b == 'i' || b == 'I');// 是否数据插入
		if (cc) {
			if (s.length() > 600) {
				info.append(s.substring(0, 600));
			} else {
				info.append(s);
			}
		} else {
			info.append(s);
		}
		if (null != arg && s.indexOf("?") > 0) {
			info.append(" ## arg:");
			info.append(arg);
		}
		if (cc && info.length() > 1000) {
			return info.substring(0, 1000);
		} else {
			return info.toString();
		}
	}

	/**
	 * 获取用于调试显示的SQL信息
	 * 
	 * @param sql
	 *            SQL
	 * @param args
	 *            参数数组集合
	 * @return 用于调试显示的sql信息
	 */
	public static String forPrintBatch(String sql, List<Object[]> args) {
		StringBuilder sb = new StringBuilder();
		sb.append(f(sql));
		sb.append(" ## args:");
		for (Object[] arg : args) {
			sb.append(Arrays.toString(arg));
		}
		return sb.toString();
	}

}
