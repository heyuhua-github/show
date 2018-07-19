package com.show.comm.restful;

/**
 * Restful异常
 * 
 * @author heyuhua
 * @date 2017年12月18日
 */
public class RestfulEx extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/** 状态 */
	private RestfulStatus s;
	/** 消息 */
	private String m;

	public RestfulEx(RestfulStatus s) {
		this.s = s;
		this.m = s.getVal();
	}

	public RestfulEx(int s, String m) {
		this(RestfulStatus.parse(s), m);
	}

	public RestfulEx(RestfulStatus s, String m) {
		this.s = s;
		this.m = null != m ? m : s.getVal();
	}

	/**
	 * 构造参数错误异常
	 * 
	 * @param paramName
	 *            参数名称
	 */
	public RestfulEx(String paramName) {
		this(paramName, null);
	}

	/**
	 * 构造参数错误异常
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramMsg
	 *            参数错误提示
	 */
	public RestfulEx(String paramName, String paramMsg) {
		this.s = RestfulStatus.H400;
		if (null == paramMsg) {
			this.m = paramName;
		} else {
			this.m = paramName + "#" + paramMsg;
		}
	}

	@Override
	public String getMessage() {
		return m;
	}

	/**
	 * @return 状态
	 */
	public RestfulStatus getS() {
		return s;
	}

	/**
	 * @param s
	 *            状态
	 */
	public void setS(RestfulStatus s) {
		this.s = s;
	}

	/**
	 * @return 消息
	 */
	public String getM() {
		return m;
	}

	/**
	 * @param m
	 *            消息
	 */
	public void setM(String m) {
		this.m = m;
	}

}
