package com.show.comm.restful;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Restful对象属性验证
 * 
 * @author heyuhua
 * @date 2017年12月18日
 */
public class RestfulObj<T> {

	/** 类方法缓存 */
	public static final Map<String, Map<String, Method>> AMS = new HashMap<String, Map<String, Method>>();

	private String cn;
	private T t;

	public RestfulObj(T t) {
		this.t = t;
		this.cn = t.getClass().getName();
		init();
	}

	/** 初始化方法缓存 */
	private void init() {
		Map<String, Method> ms = AMS.get(cn);
		if (null == ms) {
			synchronized (AMS) {
				ms = new HashMap<>();
				Method[] mr = t.getClass().getMethods();
				if (null != mr && mr.length > 0) {
					for (Method m : mr) {
						String n = m.getName().toLowerCase();
						if (n.startsWith("set")) {
							if (void.class.equals(m.getReturnType()) && m.getParameterCount() == 1) {
								ms.put(n, m);
							}
						} else if (n.startsWith("get")) {
							if (!m.getReturnType().equals(void.class) && m.getParameterCount() == 0) {
								ms.put(n, m);
							}
						}
					}
				}
				AMS.put(cn, ms);
			}
		}
	}

	/**
	 * 获取属性
	 * 
	 * @param field
	 *            属性
	 * @param nullable
	 *            是否允许空
	 * @param msg
	 *            错误信息
	 * @return 属性值
	 * @throws RestfulEx
	 *             get方法不存在/调用失败
	 */
	private Object get(String field, boolean nullable, String msg) {
		Method gm = AMS.get(cn).get(("get" + field).toLowerCase());
		if (null != gm) {
			try {
				Object val = gm.invoke(t);
				if (null != val || nullable) {
					return val;
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			}
		}
		throw new RestfulEx(field, null != msg ? msg : "Null");
	}

	/**
	 * 设置属性
	 * 
	 * @param field
	 *            属性
	 * @param val
	 *            属性值
	 * @param msg
	 *            错误信息
	 * @throws RestfulEx
	 *             set方法不存在/调用失败
	 */
	private void set(String field, Object val, String msg) {
		Method sm = AMS.get(cn).get(("set" + field).toLowerCase());
		if (null != sm) {
			try {
				sm.invoke(t, val);
				return;
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			}
		}
		throw new RestfulEx(field, null != msg ? msg : "Null");
	}

	/**
	 * 数据不允许空
	 * 
	 * @param field
	 *            属性字段
	 * @return this
	 * @throws RestfulEx
	 *             null
	 */
	public RestfulObj<T> notNull(String field) {
		return notNull(field, null);
	}

	/**
	 * 数据不允许空
	 * 
	 * @param field
	 *            属性字段
	 * @param msg
	 *            错误信息
	 * @return this
	 * @throws RestfulEx
	 *             null
	 */
	public RestfulObj<T> notNull(String field, String msg) {
		get(field, false, msg);
		return this;
	}

	/**
	 * 属性值match
	 * 
	 * @param field
	 *            属性字段
	 * @param reg
	 *            表达式
	 * @return this
	 * @throws RestfulEx
	 *             null/不匹配/set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> match(String field, String reg) {
		return match(field, reg, null);
	}

	/**
	 * 属性值match
	 * 
	 * @param field
	 *            属性字段
	 * @param reg
	 *            表达式
	 * @param msg
	 *            错误信息
	 * @return this
	 * @throws RestfulEx
	 *             null/不匹配/set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> match(String field, String reg, String msg) {
		Object val = get(field, false, msg);
		if (!val.toString().matches(reg)) {
			throw new RestfulEx(field, null != msg ? msg : ("NotMatches:" + reg));
		}
		return this;
	}

	/**
	 * 属性值match,允许null
	 * 
	 * @param field
	 *            属性字段
	 * @param reg
	 *            表达式
	 * @return this
	 * @throws RestfulEx
	 *             不匹配/set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> matchAndNullable(String field, String reg) {
		return matchAndNullable(field, reg, null);
	}

	/**
	 * 属性值match,允许null
	 * 
	 * @param field
	 *            属性字段
	 * @param reg
	 *            表达式
	 * @param msg
	 *            错误信息
	 * @return this
	 * @throws RestfulEx
	 *             不匹配/set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> matchAndNullable(String field, String reg, String msg) {
		Object val = get(field, true, msg);
		if (null != val && !val.toString().matches(reg)) {
			throw new RestfulEx(field, null != msg ? msg : ("NotMatches:" + reg));
		}
		return this;
	}

	/**
	 * 属性值非match则填充为null
	 * 
	 * @param field
	 *            属性字段
	 * @param reg
	 *            表达式
	 * @return this
	 * @throws RestfulEx
	 *             set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> matchOtherwiseNull(String field, String reg) {
		return matchOtherwiseNull(field, reg, null);
	}

	/**
	 * 属性值非match则填充为null
	 * 
	 * @param field
	 *            属性字段
	 * @param reg
	 *            表达式
	 * @param msg
	 *            错误信息
	 * @return this
	 * @throws RestfulEx
	 *             set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> matchOtherwiseNull(String field, String reg, String msg) {
		Object val = get(field, true, msg);
		if (null != val && !val.toString().matches(reg)) {
			set(field, null, msg);
		}
		return this;
	}

	/**
	 * 属性值是否match且startwith
	 * 
	 * @param field
	 *            属性字段
	 * @param reg
	 *            表达式
	 * @param prefix
	 *            前缀
	 * @return this
	 * @throws RestfulEx
	 *             set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> matchAndStartWith(String field, String reg, String prefix) {
		return matchAndStartWith(field, reg, prefix, null, false);
	}

	/**
	 * 属性值是否match且startwith
	 * 
	 * @param field
	 *            属性字段
	 * @param reg
	 *            表达式
	 * @param prefix
	 *            前缀
	 * @param nullable
	 *            是否允许空
	 * @return this
	 * @throws RestfulEx
	 *             set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> matchAndStartWith(String field, String reg, String prefix, boolean nullable) {
		return matchAndStartWith(field, reg, prefix, null, nullable);
	}

	/**
	 * 属性值是否match且startwith
	 * 
	 * @param field
	 *            属性字段
	 * @param reg
	 *            表达式
	 * @param prefix
	 *            前缀
	 * @param msg
	 *            错误信息
	 * @return this
	 * @throws RestfulEx
	 *             set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> matchAndStartWith(String field, String reg, String prefix, String msg) {
		return matchAndStartWith(field, reg, prefix, msg, false);
	}

	/**
	 * 属性值是否match且startwith
	 * 
	 * @param field
	 *            属性字段
	 * @param reg
	 *            表达式
	 * @param prefix
	 *            前缀
	 * @param msg
	 *            错误信息
	 * @param nullable
	 *            是否允许空
	 * @return this
	 * @throws RestfulEx
	 *             set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> matchAndStartWith(String field, String reg, String prefix, String msg, boolean nullable) {
		Object val = get(field, nullable, msg);
		if (null != val) {
			String s = val.toString();
			if (!s.matches(reg)) {
				throw new RestfulEx(field, null != msg ? msg : ("NotMatches:" + reg));
			}
			if (!s.startsWith(prefix)) {
				throw new RestfulEx(field, null != msg ? msg : ("NotStarts:" + prefix));
			}
		}
		return this;
	}

	/**
	 * int属性范围匹配
	 * 
	 * @param field
	 *            属性字段
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return this
	 * @throws RestfulEx
	 *             不匹配/set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> intRang(String field, int min, int max) {
		return intRang(field, min, max, null);
	}

	/**
	 * int属性范围匹配
	 * 
	 * @param field
	 *            属性字段
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param msg
	 *            错误信息
	 * @return this
	 * @throws RestfulEx
	 *             不匹配/set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> intRang(String field, int min, int max, String msg) {
		Object val = get(field, false, msg);
		if (null == val || !(val instanceof Integer)) {
			throw new RestfulEx(field, null != msg ? msg : "Null");
		}
		int v = ((Integer) val).intValue();
		if (v < min || v > max) {
			throw new RestfulEx(field, null != msg ? msg : ("NotRang:" + min + "-" + max));
		}
		return this;
	}

	/**
	 * int属性范围匹配
	 * 
	 * @param field
	 *            属性字段
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param dau
	 *            缺省值
	 * @return this
	 * @throws RestfulEx
	 *             set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> intRang(String field, int min, int max, int dau) {
		return intRang(field, min, max, dau, null);
	}

	/**
	 * int属性范围匹配
	 * 
	 * @param field
	 *            属性字段
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param dau
	 *            缺省值
	 * @param msg
	 *            错误信息
	 * @return this
	 * @throws RestfulEx
	 *             set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> intRang(String field, int min, int max, int dau, String msg) {
		Object val = get(field, true, msg);
		if (null != val && val instanceof Integer) {
			int v = ((Integer) val).intValue();
			if (v >= min && v <= max) {
				return this;
			}
		}
		set(field, dau, msg);
		return this;
	}

	/**
	 * int属性匹配
	 * 
	 * @param field
	 *            属性字段
	 * @param vs
	 *            匹配的值
	 * @return this
	 * @throws RestfulEx
	 *             不等于指定值/set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> intEq(String field, int... vs) {
		return intEq(field, null, vs);
	}

	/**
	 * int属性匹配
	 * 
	 * @param field
	 *            属性字段
	 * @param msg
	 *            错误信息
	 * @param vs
	 *            匹配的值
	 * @return this
	 * @throws RestfulEx
	 *             不等于指定值/set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> intEq(String field, String msg, int... vs) {
		Object val = get(field, false, msg);
		if (null == val || !(val instanceof Integer)) {
			throw new RestfulEx(field, null != msg ? msg : "Null");
		}
		int v = ((Integer) val).intValue();
		for (int i : vs) {
			if (v == i) {
				return this;
			}
		}
		throw new RestfulEx(field, null != msg ? msg : "NotEq");
	}

	/**
	 * int属性匹配
	 * 
	 * @param field
	 *            属性字段
	 * @param vs
	 *            匹配的值
	 * @param dau
	 *            缺省值
	 * @return this
	 * @throws RestfulEx
	 *             不等于指定值/set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> intEqOtherwiseDau(String field, int[] vs, int dau) {
		return intEqOtherwiseDau(field, vs, dau, null);
	}

	/**
	 * int属性匹配
	 * 
	 * @param field
	 *            属性字段
	 * @param vs
	 *            匹配的值
	 * @param dau
	 *            缺省值
	 * @param msg
	 *            错误信息
	 * @return this
	 * @throws RestfulEx
	 *             不等于指定值/set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> intEqOtherwiseDau(String field, int[] vs, int dau, String msg) {
		Object val = get(field, true, msg);
		if (null != val && val instanceof Integer) {
			int v = ((Integer) val).intValue();
			for (int i : vs) {
				if (v == i) {
					return this;
				}
			}
		}
		set(field, dau, msg);
		return this;
	}

	/**
	 * long属性范围匹配
	 * 
	 * @param field
	 *            属性字段
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return this
	 * @throws RestfulEx
	 *             不匹配/set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> longRang(String field, long min, long max) {
		return longRang(field, min, max, null);
	}

	/**
	 * long属性范围匹配
	 * 
	 * @param field
	 *            属性字段
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param msg
	 *            错误信息
	 * @return this
	 * @throws RestfulEx
	 *             不匹配/set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> longRang(String field, long min, long max, String msg) {
		Object val = get(field, false, msg);
		if (null == val || !(val instanceof Long)) {
			throw new RestfulEx(field, null != msg ? msg : "Null");
		}
		long v = ((Long) val).longValue();
		if (v < min || v > max) {
			throw new RestfulEx(field, null != msg ? msg : ("NotRang:" + min + "-" + max));
		}
		return this;
	}

	/**
	 * long属性范围匹配
	 * 
	 * @param field
	 *            属性字段
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param dau
	 *            缺省值
	 * @return this
	 * @throws RestfulEx
	 *             set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> longRang(String field, long min, long max, long dau) {
		return longRang(field, min, max, dau, null);
	}

	/**
	 * long属性范围匹配
	 * 
	 * @param field
	 *            属性字段
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param dau
	 *            缺省值
	 * @param msg
	 *            错误信息
	 * @return this
	 * @throws RestfulEx
	 *             set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> longRang(String field, long min, long max, long dau, String msg) {
		Object val = get(field, true, msg);
		if (null != val && val instanceof Long) {
			long v = ((Long) val).longValue();
			if (v >= min && v <= max) {
				return this;
			}
		}
		set(field, dau, msg);
		return this;
	}

	/**
	 * long属性匹配
	 * 
	 * @param field
	 *            属性字段
	 * @param vs
	 *            匹配的值
	 * @return this
	 * @throws RestfulEx
	 *             不等于指定值/set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> longEq(String field, long... vs) {
		return longEq(field, null, vs);
	}

	/**
	 * long属性匹配
	 * 
	 * @param field
	 *            属性字段
	 * @param msg
	 *            错误信息
	 * @param vs
	 *            匹配的值
	 * @return this
	 * @throws RestfulEx
	 *             不等于指定值/set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> longEq(String field, String msg, long... vs) {
		Object val = get(field, false, msg);
		if (null == val || !(val instanceof Long)) {
			throw new RestfulEx(field, null != msg ? msg : "Null");
		}
		long v = ((Long) val).longValue();
		for (long i : vs) {
			if (v == i) {
				return this;
			}
		}
		throw new RestfulEx(field, null != msg ? msg : "NotEq");
	}

	/**
	 * long属性匹配
	 * 
	 * @param field
	 *            属性字段
	 * @param vs
	 *            匹配的值
	 * @param dau
	 *            缺省值
	 * @return this
	 * @throws RestfulEx
	 *             不等于指定值/set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> longEqOtherwiseDau(String field, long[] vs, long dau) {
		return longEqOtherwiseDau(field, vs, dau, null);
	}

	/**
	 * long属性匹配
	 * 
	 * @param field
	 *            属性字段
	 * @param vs
	 *            匹配的值
	 * @param dau
	 *            缺省值
	 * @param msg
	 *            错误信息
	 * @return this
	 * @throws RestfulEx
	 *             不等于指定值/set/get方法不存在或者调用失败
	 */
	public RestfulObj<T> longEqOtherwiseDau(String field, long[] vs, long dau, String msg) {
		Object val = get(field, true, msg);
		if (null != val && val instanceof Long) {
			long v = ((Long) val).longValue();
			for (long i : vs) {
				if (v == i) {
					return this;
				}
			}
		}
		set(field, dau, msg);
		return this;
	}

	/**
	 * 获取属性验证后的对象
	 * 
	 * @return 对象
	 */
	public T get() {
		return t;
	}

}
