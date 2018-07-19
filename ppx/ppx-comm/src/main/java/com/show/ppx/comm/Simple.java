package com.show.ppx.comm;

/**
 * 简单信息
 * 
 * @author heyuhua
 * @date 2017年12月19日
 */
public class Simple {

	/** id */
	private String id;
	/** name */
	private String name;
	/** 值 */
	private String val;

	public Simple() {
	}

	public Simple(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Simple(String id, String name, String val) {
		this.id = id;
		this.name = name;
		this.val = val;
	}

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 值
	 */
	public String getVal() {
		return val;
	}

	/**
	 * @param val
	 *            值
	 */
	public void setVal(String val) {
		this.val = val;
	}

}
