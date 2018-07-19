package com.show.comm.restful;

import java.util.Date;

import com.show.comm.utils.DateUtils;

/**
 * Restful参数验证工具
 * 
 * @author heyuhua
 * @date 2017年8月4日
 */
public class RestfulVerify {

	private RestfulVerify() {
	}

	/**
	 * 参数为空
	 * 
	 * @param paramName
	 *            参数名称
	 * @throws RestfulEx
	 */
	public static void errEmpty(String paramName) {
		throw new RestfulEx(paramName, "Null");
	}

	/**
	 * 参数为空
	 * 
	 * @param paramName
	 *            参数名称
	 * @param msg
	 *            自定义错误信息
	 * @throws RestfulEx
	 */
	public static void errWith(String paramName, String msg) {
		throw new RestfulEx(paramName, msg);
	}

	/**
	 * 非空
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @return 参数检测无误时返回参数值
	 * @throws RestfulEx
	 */
	public static String notNull(String paramName, Object paramVal) {
		return notNull(paramName, paramVal, null);
	}

	/**
	 * 非空
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param msg
	 *            错误信息
	 * @return 参数检测无误时返回参数值
	 * @throws RestfulEx
	 */
	public static String notNull(String paramName, Object paramVal, String msg) {
		if (null == paramVal) {
			throw new RestfulEx(paramName, null != msg ? msg : "Null");
		}
		return paramVal.toString();
	}

	/**
	 * 字符串是否非空且匹配
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param regex
	 *            匹配表达式
	 * @return 参数检测无误时返回参数值
	 * @throws RestfulEx
	 */
	public static String match(String paramName, String paramVal, String regex) {
		return match(paramName, paramVal, regex, null);
	}

	/**
	 * 字符串是否非空且匹配
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param regex
	 *            匹配表达式
	 * @param msg
	 *            错误提示
	 * @return 参数检测无误时返回参数值
	 * @throws RestfulEx
	 */
	public static String match(String paramName, String paramVal, String regex, String msg) {
		if (null == paramVal) {
			throw new RestfulEx(paramName, null != msg ? msg : "Null");
		}
		if (!paramVal.matches(regex)) {
			throw new RestfulEx(paramName, null != msg ? msg : ("NotMatches:" + regex));
		}
		return paramVal;
	}

	/**
	 * 字符串是否匹配
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param regex
	 *            匹配表达式
	 * @param nullable
	 *            是否允许空
	 * @return 参数检测无误时返回参数值
	 * @throws RestfulEx
	 */
	public static String match(String paramName, String paramVal, String regex, boolean nullable) {
		return match(paramName, paramVal, regex, nullable, null);
	}

	/**
	 * 字符串是否匹配
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param regex
	 *            匹配表达式
	 * @param nullable
	 *            是否允许空
	 * @param msg
	 *            错误提示
	 * @return 参数检测无误时返回参数值
	 * @throws RestfulEx
	 */
	public static String match(String paramName, String paramVal, String regex, boolean nullable, String msg) {
		if (null == paramVal) {
			if (nullable) {
				return null;
			}
			throw new RestfulEx(paramName, null != msg ? msg : "Null");
		}
		if (!paramVal.matches(regex)) {
			throw new RestfulEx(paramName, null != msg ? msg : ("NotMatches:" + regex));
		}
		return paramVal;
	}

	/**
	 * 字符串是否匹配(允许空)
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param regex
	 *            匹配表达式
	 * @return 参数检测无误时返回参数值
	 * @throws RestfulEx
	 */
	public static String matchAndNullable(String paramName, String paramVal, String regex) {
		return matchAndNullable(paramName, paramVal, regex, null);
	}

	/**
	 * 字符串是否匹配(允许空)
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param regex
	 *            匹配表达式
	 * @param msg
	 *            错误信息
	 * @return 参数检测无误时返回参数值
	 * @throws RestfulEx
	 */
	public static String matchAndNullable(String paramName, String paramVal, String regex, String msg) {
		return match(paramName, paramVal, regex, true, msg);
	}

	/**
	 * 字符串是否非空且匹配
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param regex
	 *            匹配表达式
	 * @param prefix
	 *            前缀
	 * @return 参数检测无误时返回参数值
	 * @throws RestfulEx
	 */
	public static String matchAndStartWith(String paramName, String paramVal, String regex, String prefix) {
		return matchAndStartWith(paramName, paramVal, regex, prefix, false, null);
	}

	/**
	 * 字符串是否非空且匹配
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param regex
	 *            匹配表达式
	 * @param prefix
	 *            前缀
	 * @param msg
	 *            错误提示
	 * @return 参数检测无误时返回参数值
	 * @throws RestfulEx
	 */
	public static String matchAndStartWith(String paramName, String paramVal, String regex, String prefix, String msg) {
		return matchAndStartWith(paramName, paramVal, regex, prefix, false, msg);
	}

	/**
	 * 字符串是否匹配
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param regex
	 *            匹配表达式
	 * @param prefix
	 *            前缀
	 * @param nullable
	 *            是否允许空
	 * @return 参数检测无误时返回参数值
	 * @throws RestfulEx
	 */
	public static String matchAndStartWith(String paramName, String paramVal, String regex, String prefix, boolean nullable) {
		return matchAndStartWith(paramName, paramVal, regex, prefix, nullable, null);
	}

	/**
	 * 字符串是否匹配
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param regex
	 *            匹配表达式
	 * @param prefix
	 *            前缀
	 * @param nullable
	 *            是否允许空
	 * @param msg
	 *            错误提示
	 * @return 参数检测无误时返回参数值
	 * @throws RestfulEx
	 */
	public static String matchAndStartWith(String paramName, String paramVal, String regex, String prefix, boolean nullable, String msg) {
		if (null == paramVal) {
			if (nullable) {
				return null;
			}
			throw new RestfulEx(paramName, null != msg ? msg : "Null");
		}
		if (null != regex && !paramVal.matches(regex)) {
			throw new RestfulEx(paramName, null != msg ? msg : ("NotMatches:" + regex));
		}
		if (!paramVal.startsWith(prefix)) {
			throw new RestfulEx(paramName, null != msg ? msg : ("NotStarts:" + prefix));
		}
		return paramVal;
	}

	/**
	 * Integer转为int
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @return int
	 * @throws RestfulEx
	 */
	public static int intNotNull(String paramName, Integer paramVal) {
		return intNotNull(paramName, paramVal, null);
	}

	/**
	 * Integer转为int
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param msg
	 *            错误信息
	 * @return int
	 * @throws RestfulEx
	 */
	public static int intNotNull(String paramName, Integer paramVal, String msg) {
		if (null == paramVal) {
			throw new RestfulEx(paramName, null != msg ? msg : "Null");
		}
		return paramVal.intValue();
	}

	/**
	 * Integer转为int
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param dau
	 *            缺省值
	 * @return int
	 */
	public static int intNotNull(String paramName, Integer paramVal, int dau) {
		if (null == paramVal) {
			return dau;
		}
		return paramVal.intValue();
	}

	/**
	 * int属性范围匹配
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return int
	 * @throws RestfulEx
	 */
	public static int intRang(String paramName, Integer paramVal, int min, int max) {
		return intRang(paramName, paramVal, min, max, null);
	}

	/**
	 * int属性范围匹配
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param msg
	 *            错误信息
	 * @return int
	 * @throws RestfulEx
	 */
	public static int intRang(String paramName, Integer paramVal, int min, int max, String msg) {
		if (null == paramVal) {
			throw new RestfulEx(paramName, null != msg ? msg : "Null");
		}
		int i = paramVal.intValue();
		if (i < min || i > max) {
			throw new RestfulEx(paramName, null != msg ? msg : ("NotRang:" + min + "-" + max));
		}
		return i;
	}

	/**
	 * int属性范围匹配
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param dau
	 *            缺省值
	 * @return int
	 */
	public static int intRang(String paramName, Integer paramVal, int min, int max, int dau) {
		if (null != paramVal) {
			int i = paramVal.intValue();
			if (i >= min && i <= max) {
				return i;
			}
		}
		return dau;
	}

	/**
	 * int属性匹配
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param vs
	 *            匹配值
	 * @return int
	 * @throws RestfulEx
	 */
	public static int intEq(String paramName, Integer paramVal, int... vs) {
		return intEq(paramName, paramVal, null, vs);
	}

	/**
	 * int属性匹配
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param msg
	 *            错误信息
	 * @param vs
	 *            匹配值
	 * @return int
	 * @throws RestfulEx
	 */
	public static int intEq(String paramName, Integer paramVal, String msg, int... vs) {
		if (null == paramVal) {
			throw new RestfulEx(paramName, null != msg ? msg : "Null");
		}
		int i = paramVal.intValue();
		for (int v : vs) {
			if (v == i) {
				return i;
			}
		}
		throw new RestfulEx(paramName, null != msg ? msg : "NotEq");
	}

	/**
	 * int属性匹配
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param vs
	 *            匹配值
	 * @param dau
	 *            缺省值
	 * @return int
	 */
	public static int intEqOtherwiseDau(String paramName, Integer paramVal, int[] vs, int dau) {
		if (null != paramVal) {
			int i = paramVal.intValue();
			for (int v : vs) {
				if (i == v) {
					return paramVal;
				}
			}
		}
		return dau;
	}

	/**
	 * Long转为long
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @return long
	 * @throws RestfulEx
	 */
	public static long longNotNull(String paramName, Long paramVal) {
		return longNotNull(paramName, paramVal, null);
	}

	/**
	 * Long转为long
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param msg
	 *            错误信息
	 * @return long
	 * @throws RestfulEx
	 */
	public static long longNotNull(String paramName, Long paramVal, String msg) {
		if (null == paramVal) {
			throw new RestfulEx(paramName, null != msg ? msg : "Null");
		}
		return paramVal.longValue();
	}

	/**
	 * Long转为long
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param dau
	 *            缺省值
	 * @return long
	 */
	public static long longNotNull(String paramName, Long paramVal, long dau) {
		if (null == paramVal) {
			return dau;
		}
		return paramVal.longValue();
	}

	/**
	 * long属性范围匹配
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return long
	 * @throws RestfulEx
	 */
	public static long longRang(String paramName, Integer paramVal, long min, long max) {
		return longRang(paramName, paramVal, min, max, null);
	}

	/**
	 * long属性范围匹配
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param msg
	 *            错误信息
	 * @return long
	 * @throws RestfulEx
	 */
	public static long longRang(String paramName, Integer paramVal, long min, long max, String msg) {
		if (null == paramVal) {
			throw new RestfulEx(paramName, null != msg ? msg : "Null");
		}
		long i = paramVal.longValue();
		if (i < min || i > max) {
			throw new RestfulEx(paramName, null != msg ? msg : ("NotRang:" + min + "-" + max));
		}
		return i;
	}

	/**
	 * long属性范围匹配
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @param dau
	 *            缺省值
	 * @return long
	 */
	public static long longRang(String paramName, Integer paramVal, long min, long max, long dau) {
		if (null != paramVal) {
			long i = paramVal.longValue();
			if (i >= min && i <= max) {
				return i;
			}
		}
		return dau;
	}

	/**
	 * long属性匹配
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param vs
	 *            匹配值
	 * @return long
	 * @throws RestfulEx
	 */
	public static long longEq(String paramName, Integer paramVal, long... vs) {
		return longEq(paramName, paramVal, null, vs);
	}

	/**
	 * long属性匹配
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param msg
	 *            错误信息
	 * @param vs
	 *            匹配值
	 * @return long
	 * @throws RestfulEx
	 */
	public static long longEq(String paramName, Integer paramVal, String msg, long... vs) {
		if (null == paramVal) {
			throw new RestfulEx(paramName, null != msg ? msg : "Null");
		}
		long i = paramVal.longValue();
		for (long v : vs) {
			if (v == i) {
				return i;
			}
		}
		throw new RestfulEx(paramName, null != msg ? msg : "NotEq");
	}

	/**
	 * long属性匹配
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值
	 * @param vs
	 *            匹配值
	 * @param dau
	 *            缺省值
	 * @return long
	 */
	public static long longEqOtherwiseDau(String paramName, Integer paramVal, long[] vs, long dau) {
		if (null != paramVal) {
			long i = paramVal.longValue();
			for (long v : vs) {
				if (i == v) {
					return paramVal;
				}
			}
		}
		return dau;
	}

	/**
	 * String转为Date
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值(yyyy-MM-dd)
	 * @return Date
	 * @throws RestfulEx
	 */
	public static Date toDate(String paramName, String paramVal) {
		return toDate(paramName, paramVal, null, false);
	}

	/**
	 * String转为Date
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值(yyyy-MM-dd)
	 * @param nullbale
	 *            是否允许空
	 * @return Date
	 * @throws RestfulEx
	 */
	public static Date toDate(String paramName, String paramVal, boolean nullbale) {
		return toDate(paramName, paramVal, null, nullbale);
	}

	/**
	 * String转为Date
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值(yyyy-MM-dd)
	 * @param msg
	 *            错误信息
	 * @return Date
	 * @throws RestfulEx
	 */
	public static Date toDate(String paramName, String paramVal, String msg) {
		return toDate(paramName, paramVal, msg, false);
	}

	/**
	 * String转为Date
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值(yyyy-MM-dd)
	 * @param msg
	 *            错误信息
	 * @param nullbale
	 *            是否允许空
	 * @return Date
	 * @throws RestfulEx
	 */
	public static Date toDate(String paramName, String paramVal, String msg, boolean nullbale) {
		if (null == paramVal) {
			if (nullbale) {
				return null;
			}
			throw new RestfulEx(paramName, null != msg ? msg : "Null");
		}
		Date val = DateUtils.parseDate(paramVal);
		if (null == val && !nullbale) {
			throw new RestfulEx(paramName, null != msg ? msg : "Not:'yyyy-MM-dd'");
		}
		return val;
	}

	/**
	 * String转为Date time
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值(yyyy-MM-dd HH:mm:ss)
	 * @return Date
	 * @throws RestfulEx
	 */
	public static Date toTime(String paramName, String paramVal) {
		return toTime(paramName, paramVal, null, false);
	}

	/**
	 * String转为Date time
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值(yyyy-MM-dd HH:mm:ss)
	 * @param nullbale
	 *            是否允许空
	 * @return Date
	 * @throws RestfulEx
	 */
	public static Date toTime(String paramName, String paramVal, boolean nullbale) {
		return toTime(paramName, paramVal, null, nullbale);
	}

	/**
	 * String转为Date time
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值(yyyy-MM-dd HH:mm:ss)
	 * @param msg
	 *            错误信息
	 * @return Date
	 * @throws RestfulEx
	 */
	public static Date toTime(String paramName, String paramVal, String msg) {
		return toTime(paramName, paramVal, msg, false);
	}

	/**
	 * String转为Date time
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值(yyyy-MM-dd HH:mm:ss)
	 * @param msg
	 *            错误信息
	 * @param nullbale
	 *            是否允许空
	 * @return Date
	 * @throws RestfulEx
	 */
	public static Date toTime(String paramName, String paramVal, String msg, boolean nullbale) {
		if (null == paramVal) {
			if (nullbale) {
				return null;
			}
			throw new RestfulEx(paramName, null != msg ? msg : "Null");
		}
		Date val = DateUtils.parse(paramVal);
		if (null == val && !nullbale) {
			throw new RestfulEx(paramName, null != msg ? msg : "Not:'yyyy-MM-dd HH:mm:ss'");
		}
		return val;
	}

	/**
	 * 是否yyyy-MM-dd
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值(yyyy-MM-dd)
	 * @return 参数检测无误时返回参数值
	 * @throws RestfulEx
	 */
	public static String yyyyMMdd(String paramName, String paramVal) {
		return yyyyMMdd(paramName, paramVal, null);
	}

	/**
	 * 是否yyyy-MM-dd
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值(yyyy-MM-dd)
	 * @param msg
	 *            错误信息
	 * @return 参数检测无误时返回参数值
	 * @throws RestfulEx
	 */
	public static String yyyyMMdd(String paramName, String paramVal, String msg) {
		if (null == paramVal) {
			throw new RestfulEx(paramName, null != msg ? msg : "Null");
		}
		if (!DateUtils.isDate(paramVal)) {
			throw new RestfulEx(paramName, null != msg ? msg : "Not:'yyyy-MM-dd'");
		}
		return paramVal;
	}

	/**
	 * 是否HHmm
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值(HHmm)
	 * @return 参数检测无误时返回参数值
	 * @throws RestfulEx
	 */
	public static String HHmm(String paramName, String paramVal) {
		return HHmm(paramName, paramVal, null, false);
	}

	/**
	 * 是否HHmm
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值(HHmm)
	 * @param msg
	 *            错误信息
	 * @return 参数检测无误时返回参数值
	 * @throws RestfulEx
	 */
	public static String HHmm(String paramName, String paramVal, String msg) {
		return HHmm(paramName, paramVal, msg, false);
	}

	/**
	 * 是否HHmm
	 * 
	 * @param paramName
	 *            参数名称
	 * @param paramVal
	 *            参数值(HHmm)
	 * @param msg
	 *            错误信息
	 * @param nullable
	 *            是否允许空
	 * @return 参数检测无误时返回参数值
	 * @throws RestfulEx
	 */
	public static String HHmm(String paramName, String paramVal, String msg, boolean nullable) {
		if (null == paramVal) {
			if (nullable) {
				return null;
			}
			throw new RestfulEx(paramName, null != msg ? msg : "Null");
		}
		if (!DateUtils.isHHmm(paramVal) && !nullable) {
			throw new RestfulEx(paramName, null != msg ? msg : "Not:'HHmm'");
		}
		return paramVal;
	}

}
