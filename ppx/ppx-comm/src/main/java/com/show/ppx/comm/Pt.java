package com.show.ppx.comm;

/**
 * 平台类型
 * 
 * @author heyuhua
 * @date 2017年12月15日
 */
public enum Pt {

	/** 系统 */
	SYS("系统"),
	/** 管理平台 */
	ADM("管理平台"),
	/** 校园平台 */
	XY("校园平台"),
	/** Pad设备 */
	PAD("Pad设备"),
	/** 开放平台 */
	WS("开放平台"),
	/** 公众号 */
	WX("公众号");

	/** 平台名称 */
	private String val;

	private Pt(String val) {
		this.val = val;
	}

	/**
	 * 解析平台类型
	 * 
	 * @param pt
	 *            平台用户类型
	 * @return 用户类型/null
	 */
	public static Pt parse(String pt) {
		return parse(pt, null);
	}

	/**
	 * 解析平台类型
	 * 
	 * @param pt
	 *            平台类型代码
	 * @param dau
	 *            缺省类型
	 * @return 类型/dau
	 */
	public static Pt parse(String pt, Pt dau) {
		if (null != pt && !pt.isEmpty()) {
			try {
				return Pt.valueOf(pt.toUpperCase());
			} catch (IllegalArgumentException e) {
			}
		}
		return dau;
	}

	/**
	 * @return 平台名称
	 */
	public String getVal() {
		return val;
	}
}
