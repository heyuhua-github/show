package com.show.ppx.restful.zuul;

/**
 * Zuul基础
 * 
 * @author heyuhua
 * @date 2018年1月5日
 */
public final class Zuul {

	private Zuul() {
	}

	private static final ThreadLocal<Boolean> NEXT = new ThreadLocal<Boolean>();

	/**
	 * 是否执行当前流程
	 * 
	 * @return true：执行当前流程<br/>
	 *         false：忽略当前流程
	 */
	public static boolean current(boolean rmv) {
		Boolean b = NEXT.get();
		if (null == b) {
			if (rmv) {
				NEXT.remove();
			}
			return true;
		}
		return b.booleanValue();
	}

	/**
	 * 是否执行后续流程
	 * 
	 * @param b
	 *            true：执行下一流程 <br/>
	 *            false：忽略后续流程
	 */
	public static void next(boolean b) {
		NEXT.set(b);
	}

}
