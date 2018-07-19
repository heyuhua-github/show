package com.show.comm.utils;

/**
 * 语言
 * 
 * @author heyuhua
 * @date 2018年2月28日
 */
public enum Langs {

	/** 简体中文 */
	cn("简体中文"),
	/** 繁体中文 */
	hk("繁體中文"),
	/** 英文 */
	en("English");

	/** 语言名称 */
	private String val;

	private Langs(String val) {
		this.val = val;
	}

	/**
	 * 解析语言
	 * 
	 * @param lang
	 *            语言代码
	 * @return Langs/cn
	 */
	public static Langs parse(String lang) {
		if (null != lang) {
			String s = lang.toLowerCase().trim();
			if (s.equals("cn")) {
				return cn;
			} else if (s.equals("hk")) {
				return hk;
			} else if (s.equals("en")) {
				return en;
			}
		}
		return cn;
	}

	/**
	 * @return 语言名称
	 */
	public String getVal() {
		return val;
	}

}
