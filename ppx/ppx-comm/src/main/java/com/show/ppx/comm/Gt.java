package com.show.ppx.comm;

/**
 * 平台群组类型
 * 
 * 
 * @author heyuhua
 * @date 2017年12月15日
 */
public enum Gt {

	/** 系统 */
	SYS("系统"),
	/** 代理商 */
	SA("代理商"),
	/** 开放平台 */
	WS("开放平台"),
	/** 学校 */
	SCH("学校");

	/** 群组类型名称 */
	private String val;

	private Gt(String val) {
		this.val = val;
	}

	/**
	 * 解析平台群组类型
	 * 
	 * @param gt
	 *            平台群组类型代码
	 * @return 群组类型/null
	 */
	public static Gt parse(String gt) {
		return parse(gt, null);
	}

	/**
	 * 解析平台群组类型
	 * 
	 * @param gt
	 *            平台群组类型代码
	 * @param dau
	 *            缺省群组类型
	 * @return 群组类型/dau
	 */
	public static Gt parse(String gt, Gt dau) {
		if (null != gt && !gt.isEmpty()) {
			String g = gt.toUpperCase();
			if (g.equals("SCH")) {
				return SCH;
			} else if (g.equals("SA")) {
				return SA;
			} else if (g.equals("WS")) {
				return WS;
			}
		}
		return dau;
	}

	/**
	 * @return 群组类型名称
	 */
	public String getVal() {
		return val;
	}
}
