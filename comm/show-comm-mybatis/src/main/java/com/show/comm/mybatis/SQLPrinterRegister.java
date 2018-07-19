package com.show.comm.mybatis;

/**
 * SQL打印服务工厂
 * 
 * @author heyuhua
 * @date 2017年6月7日
 *
 */
public class SQLPrinterRegister {

	/** SQL打印服务 */
	private static SQLPrinter printer = null;

	/** 获取SQL打印服务 */
	public static SQLPrinter get() {
		return printer;
	}

	/**
	 * 注册SQL打印服务
	 * 
	 * @param printer
	 *            SQL打印服务
	 */
	public static void register(SQLPrinter printer) {
		SQLPrinterRegister.printer = printer;
	}

	/**
	 * 注册缺省SQL打印服务
	 * 
	 * @param showSql
	 *            是否打印SQL
	 */
	public static void register(boolean showSql) {
		SQLPrinterRegister.printer = new SQLPrinterDefaultImpl(showSql);
	}
}
