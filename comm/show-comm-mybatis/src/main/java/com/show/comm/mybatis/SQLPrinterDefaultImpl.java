package com.show.comm.mybatis;

import com.show.comm.utils.ext.Out;

/**
 * SQL打印实现
 * 
 * @author heyuhua
 * @date 2017年6月7日
 *
 */
public class SQLPrinterDefaultImpl implements SQLPrinter {

	private boolean showSql = false;

	public SQLPrinterDefaultImpl(boolean showSql) {
		this.showSql = showSql;
	}

	@Override
	public void print(String sql, Object arg) {
		if (showSql) {
			Out.sql(SQLFormat.forPrint(sql, arg));
		}
	}

	@Override
	public boolean showSql() {
		return showSql;
	}

}
