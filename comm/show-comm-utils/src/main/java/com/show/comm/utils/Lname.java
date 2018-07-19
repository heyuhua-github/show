package com.show.comm.utils;

/**
 * 多语言名称
 * 
 * @author heyuhua
 * @date 2018年3月20日
 */
public final class Lname {

	/** 简体中文 */
	private String cn;
	/** 繁体中文 */
	private String hk;
	/** 英文 */
	private String en;

	/** 下标 */
	private int ordinal = 0;

	/**
	 * 构造多语言名称
	 * 
	 * @param cn
	 *            简体中文名称值
	 * @param hk
	 *            繁体中文名称值
	 * @param en
	 *            英文名称值
	 */
	public Lname(String cn, String hk, String en) {
		this.cn = cn;
		this.hk = hk;
		this.en = en;
	}

	/**
	 * 判断是否当前名称
	 * 
	 * @param name
	 *            名称
	 * @return true：是
	 */
	public boolean isName(String name) {
		if (cn.equals(name)) {
			this.ordinal = 0;
			return true;
		} else if (hk.equals(name)) {
			this.ordinal = 1;
			return true;
		} else if (en.equals(name)) {
			this.ordinal = 2;
			return true;
		}
		return false;
	}

	/**
	 * 根据语言获取名称
	 * 
	 * @param lang
	 *            语言
	 * @return 名称对应语言取值
	 */
	public String name(Langs lang) {
		if (null == lang || Langs.cn.equals(lang)) {
			this.ordinal = 0;
			return this.cn;
		} else if (Langs.hk.equals(lang)) {
			this.ordinal = 1;
			return this.hk;
		} else {
			this.ordinal = 2;
			return this.en;
		}
	}

	/**
	 * @return 简体中文
	 */
	public String getCn() {
		return cn;
	}

	/**
	 * @return 繁体中文
	 */
	public String getHk() {
		return hk;
	}

	/**
	 * @return 英文
	 */
	public String getEn() {
		return en;
	}

	/**
	 * @return 下标
	 */
	public int getOrdinal() {
		return ordinal;
	}

	/**
	 * @return 当前下标对应值
	 */
	public String getByOrdinal() {
		if (ordinal == 0) {
			return cn;
		} else if (ordinal == 1) {
			return hk;
		} else if (ordinal == 2) {
			return en;
		} else {
			return cn;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Lname [cn=");
		builder.append(cn);
		builder.append(", hk=");
		builder.append(hk);
		builder.append(", en=");
		builder.append(en);
		builder.append(", ordinal=");
		builder.append(ordinal);
		builder.append("]");
		return builder.toString();
	}

}
