package com.show.comm.restful;

/**
 * Rest服务响应数据
 * 
 * @author heyuhua
 * @date 2017年12月15日
 */
public class RestfulRes<T> {

	/** 状态 */
	private int s;
	/** 信息 */
	private String m = null;
	/** 数据 */
	private T d = null;

	public RestfulRes() {
	}

	public RestfulRes(T d) {
		this.d = d;
		this.s = RestfulStatus.SUCC.getCode();
		this.m = RestfulStatus.SUCC.getVal();
	}

	public RestfulRes(T d, String m) {
		this.d = d;
		this.s = RestfulStatus.SUCC.getCode();
		this.m = null != m ? m : RestfulStatus.SUCC.getVal();
	}

	public RestfulRes(T d, RestfulStatus s, String m) {
		this.d = d;
		this.s = s.getCode();
		this.m = null != m ? m : s.getVal();
	}

	/**
	 * 失败 - 熔断
	 * 
	 * @return 失败响应
	 */
	public static <T> RestfulRes<T> hystric() {
		return hystric(null);
	}

	/**
	 * 失败 - 熔断
	 * 
	 * @return 失败响应
	 */
	public static <T> RestfulRes<T> hystric(T d) {
		RestfulRes<T> r = new RestfulRes<>();
		if (null != d) {
			r.setD(d);
		}
		r.setS(RestfulStatus.HYSTRIC.getCode());
		r.setM(RestfulStatus.HYSTRIC.getVal());
		return r;
	}

	/**
	 * 失败
	 * 
	 * @param s
	 *            状态
	 * @return 失败响应
	 */
	public static <T> RestfulRes<T> err(RestfulStatus s) {
		return err(s, null);
	}

	/**
	 * 失败
	 * 
	 * @param m
	 *            失败消息(缺省 RestfulStatus.FAIL.val)
	 * @return 失败响应
	 */
	public static <T> RestfulRes<T> err(String m) {
		return err(RestfulStatus.FAIL, m);
	}

	/**
	 * 失败
	 * 
	 * @param s
	 *            状态
	 * @param m
	 *            失败消息(缺省 s.val)
	 * @return 失败响应
	 */
	public static <T> RestfulRes<T> err(RestfulStatus s, String m) {
		return err(s.getCode(), null != m ? m : s.getVal());
	}

	/**
	 * 失败
	 * 
	 * @param s
	 *            状态
	 * @param m
	 *            失败消息(缺省 s.val)
	 * @return 失败响应
	 */
	public static <T> RestfulRes<T> err(int s, String m) {
		RestfulRes<T> r = new RestfulRes<>();
		r.setS(s);
		if (null != m) {
			r.setM(m);
		}
		return r;
	}

	/**
	 * 参数错误
	 * 
	 * @param paramName
	 *            参数名称
	 * @return 失败响应
	 */
	public static <T> RestfulRes<T> h400(String paramName) {
		return h400(paramName, null);
	}

	/**
	 * 参数错误
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramMsg
	 *            参数错误提示信息
	 * @return 失败响应
	 */
	public static <T> RestfulRes<T> h400(String paramName, String paramMsg) {
		RestfulRes<T> r = new RestfulRes<>();
		r.setS(RestfulStatus.H400.getCode());
		if (null != paramMsg) {
			r.setM(paramName + "#" + paramMsg);
		} else {
			r.setM(paramName);
		}
		return r;
	}

	/**
	 * 是否成功
	 * 
	 * @return true：成功(状态为1(RestfulStatus.SUCC))
	 */
	public boolean succ() {
		return 1 == s;
	}

	/**
	 * 是否可以重试
	 * 
	 * @return true：是<br/>
	 * 		false：状态重试无意义
	 */
	public boolean retry() {
		return 2 == s || 3 == s || 4 == s;
	}

	/**
	 * @return 状态
	 */
	public int getS() {
		return s;
	}

	/**
	 * @param s
	 *            状态
	 */
	public void setS(int s) {
		this.s = s;
	}

	/**
	 * @return 信息
	 */
	public String getM() {
		return m;
	}

	/**
	 * @param m
	 *            信息
	 */
	public void setM(String m) {
		this.m = m;
	}

	/**
	 * @return 数据
	 */
	public T getD() {
		return d;
	}

	/**
	 * @param d
	 *            数据
	 */
	public void setD(T d) {
		this.d = d;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SvRes [s=");
		builder.append(s);
		builder.append(", m=");
		builder.append(m);
		builder.append(", d=");
		builder.append(d);
		builder.append("]");
		return builder.toString();
	}

}
