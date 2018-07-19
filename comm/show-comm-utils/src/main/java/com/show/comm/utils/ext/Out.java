package com.show.comm.utils.ext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定向日志输出
 * 
 * <pre>
 * logback配置忽略类、方法等信息，仅输出内容
 * </pre>
 * 
 * @author heyuhua
 * @date 2017年8月7日
 */
public class Out {

	/** 日志 */
	private static final Logger LOG = LoggerFactory.getLogger(Out.class);

	/**
	 * 打印info信息
	 * 
	 * <pre>
	 * LOG.info(msg)
	 * </pre>
	 * 
	 * @param msg
	 *            信息
	 */
	public static void println(String msg) {
		LOG.info(msg);
	}

	/**
	 * 打印info信息
	 * 
	 * <pre>
	 * LOG.info(msg)
	 * </pre>
	 * 
	 * @param msg
	 *            信息
	 */
	public static void info(String msg) {
		LOG.info(msg);
	}

	/**
	 * 打印error信息
	 * 
	 * <pre>
	 * LOG.error(msg)
	 * </pre>
	 * 
	 * @param msg
	 *            信息
	 */
	public static void error(String msg) {
		LOG.error(msg);
	}

	/**
	 * 打印error信息
	 * 
	 * <pre>
	 * LOG.error(msg, t)
	 * </pre>
	 * 
	 * @param msg
	 *            信息
	 * @param t
	 *            异常堆栈信息
	 */
	public static void error(String msg, Throwable t) {
		LOG.error(msg, t);
	}

	/**
	 * 打印url信息
	 * 
	 * <pre>
	 * LOG.info("URL: " + url)
	 * </pre>
	 * 
	 * @param url
	 *            url
	 */
	public static void url(String url) {
		LOG.info("URL: " + url);
	}

	/**
	 * 打印sql信息
	 * 
	 * <pre>
	 * LOG.info("SQL: " + sql)
	 * </pre>
	 * 
	 * @param sql
	 *            sql
	 */
	public static void sql(String sql) {
		LOG.info("SQL: " + sql);
	}

}
