package com.show.comm.restful;

/**
 * Restful api 状态
 * 
 * @author heyuhua
 * @date 2017年12月15日
 */
public enum RestfulStatus {

	/** 成功 */
	SUCC(1, "成功"),
	/** 失败 */
	FAIL(2, "失败"),
	/** 服务调用失败 */
	HYSTRIC(3, "服务调用失败"),
	/** 服务调用超时 */
	TIMEOUT(4, "服务调用超时"),
	/** 无数据权限 */
	NOACCESS(13, "无数据权限"),

	/** 参数错误 */
	H400(400, "参数错误"),
	/** 客户未授权 */
	H401(401, "客户未授权"),
	/** 客户无访问权限 */
	H403(403, "客户无访问权限"),
	/** API不存在 */
	H404(404, "API不存在"),
	/** API请求类型错误 */
	H405(405, "API请求类型错误"),
	/** API内部错误 */
	H500(500, "API内部错误");

	/** 状态码 */
	private int code;
	/** 状态描述 */
	private String val;

	private RestfulStatus(int code, String val) {
		this.code = code;
		this.val = val;
	}

	/**
	 * 解析状态
	 * 
	 * @param s
	 *            状态/状态码
	 * @return 状态/null
	 */
	public static RestfulStatus parse(String s) {
		return parse(s, null);
	}

	/**
	 * 解析状态
	 * 
	 * @param s
	 *            状态/状态码
	 * @param dau
	 *            缺省状态
	 * @return 状态/dau
	 */
	public static RestfulStatus parse(String s, RestfulStatus dau) {
		if (null != s) {
			if (s.matches("[0-9]{1,4}")) {
				return parse(Integer.parseInt(s), dau);
			} else if (s.matches("([A-Z]{4,8})|(H[0-9]{3})")) {
				try {
					return RestfulStatus.valueOf(s);
				} catch (IllegalArgumentException e) {
				}
			}
		}
		return dau;
	}

	/**
	 * 解析状态
	 * 
	 * @param code
	 *            状态码
	 * @return 状态/null
	 */
	public static RestfulStatus parse(int code) {
		return parse(code, null);
	}

	/**
	 * 解析状态
	 * 
	 * @param code
	 *            状态码
	 * @param dau
	 *            缺省状态
	 * @return 状态/dau
	 */
	public static RestfulStatus parse(int code, RestfulStatus dau) {
		switch (code) {
		case 1:
			return RestfulStatus.SUCC;
		case 2:
			return RestfulStatus.FAIL;
		case 3:
			return RestfulStatus.HYSTRIC;
		case 4:
			return RestfulStatus.TIMEOUT;
		case 13:
			return RestfulStatus.NOACCESS;

		case 400:
			return RestfulStatus.H400;
		case 401:
			return RestfulStatus.H401;
		case 403:
			return RestfulStatus.H403;
		case 404:
			return RestfulStatus.H404;
		case 405:
			return RestfulStatus.H405;
		case 500:
			return RestfulStatus.H500;

		}
		return dau;
	}

	/**
	 * @return 状态码
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return 状态描述
	 */
	public String getVal() {
		return val;
	}

}
