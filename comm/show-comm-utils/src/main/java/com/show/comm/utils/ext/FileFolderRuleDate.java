package com.show.comm.utils.ext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 以当前日期作为目录的创建规则
 * 
 * @author HeHongxin
 * @date 2014-2-10
 * 
 */
public class FileFolderRuleDate extends FileFolderRule {

	/**
	 * 默认日期格式
	 */
	public static final String FORMAT = "yyyy/MMdd";

	/**
	 * 日期格式
	 */
	private DateFormat format;

	/**
	 * 以默认日期格式构造
	 */
	public FileFolderRuleDate() {
		this.format = new SimpleDateFormat(FORMAT);
	}

	/**
	 * 以指定格式构造
	 * 
	 * @param format
	 *            日期格式
	 */
	public FileFolderRuleDate(String format) {
		this.format = new SimpleDateFormat(format);
	}

	/**
	 * 以指定格式创建目录
	 */
	@Override
	public String createFolder() {
		return format.format(new Date());
	}

}
