package com.show.ppx.comm;

/**
 * 性别
 * 
 * @author heyuhua
 * @date 2017年12月26日
 */
public enum Sex {

	/** 男 */
	MALE("男"),
	/** 女 */
	FEMALE("女");

	/** 性别名称 */
	private String val;

	private Sex(String val) {
		this.val = val;
	}

	/**
	 * 解析性别（缺省MALE）
	 * 
	 * @deprecated 请使用 {@link #parse(String, Sex)} 或者 {@link #parse(String)}
	 * @param name
	 *            状态name
	 * @return 状态
	 */
	public static Sex sexOf(String name) {
		return parse(name, MALE);
	}

	/**
	 * 解析性别
	 * 
	 * @param sex
	 *            性别
	 * @return Sex/null
	 */
	public static Sex parse(Object sex) {
		return parse(sex, null);
	}

	/**
	 * 解析性别
	 * 
	 * @param sex
	 *            性别
	 * @param dau
	 *            缺省性别
	 * @return Sex/dau
	 */
	public static Sex parse(Object sex, Sex dau) {
		if (null != sex) {
			if (sex instanceof String) {
				String s = ((String) sex).toUpperCase();
				if (s.equals("FEMALE")) {
					return FEMALE;
				} else if (s.equals("MALE")) {
					return MALE;
				}
			} else if (sex instanceof Sex) {
				return (Sex) sex;
			}

		}
		return dau;
	}

	/**
	 * @return 性别名称
	 */
	public String getVal() {
		return val;
	}

}
