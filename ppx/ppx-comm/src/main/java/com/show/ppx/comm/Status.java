package com.show.ppx.comm;

/**
 * 状态
 * 
 * @author heyuhua
 * @date 2017年12月25日
 */
public enum Status {

	/** 正常 */
	NORMAL("正常"),
	/** 禁用 */
	DISABLE("禁用");

	/** 状态名称 */
	private String val;

	private Status(String val) {
		this.val = val;
	}

	/**
	 * @return 状态名称
	 */
	public String getVal() {
		return val;
	}

	/**
	 * 解析状态（缺省NORMAL）
	 * 
	 * @param status
	 *            状态
	 * @return Status
	 */
	public static Status parse(Object status) {
		if (null != status) {
			if (status instanceof Status) {
				return (Status) status;
			} else if (status instanceof String && (((String) status).equalsIgnoreCase("DISABLE"))) {
				return DISABLE;
			}
		}
		return NORMAL;
	}

}
