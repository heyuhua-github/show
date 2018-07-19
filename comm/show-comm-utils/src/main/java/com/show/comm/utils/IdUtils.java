package com.show.comm.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * ID工具
 * 
 * @author heyuhua
 * @date 2017年6月22日
 */
public class IdUtils {

	private IdUtils() {
	}

	/** 时间匹配表达式 - yyyyMM */
	public static final String REG_MONTH_S = "20[0-9][0-9](([0][1-9])|([1][0-2]))";
	/** 时间匹配表达式 - yyyyMMdd */
	public static final String REG_DATE_S = "20[0-9][0-9](([0][1-9])|([1][0-2]))(([0][1-9])|([1-2][0-9])|(3[0,1]))";
	/** 时间匹配表达式 - yyyyMMddHHmmss */
	public static final String REG_TIME_S = "20[0-9][0-9](([0][1-9])|([1][0-2]))(([0][1-9])|([1-2][0-9])|(3[0,1]))(([0-1][0-9])|([2][0-3]))[0-5][0-9][0-5][0-9]";

	/**
	 * 获取UUID
	 * 
	 * @return 36位UUID字符串
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 根据当前时间生成20位ID
	 * 
	 * @return yyyyMMddHHmmssSSS+ranNum(3)
	 */
	public static String id20() {
		return msid(20, new Date());
	}

	/**
	 * 根据时间生成20位ID
	 * 
	 * @param date
	 *            指定时间
	 * @return yyyyMMddHHmmssSSS+ranNum(3)
	 */
	public static String id20(Date date) {
		return msid(20, date);
	}

	/**
	 * 根据当前时间生成32位ID
	 * 
	 * @return yyyyMMddHHmmssSSS+ranNum(15)
	 */
	public static String id32() {
		return msid(32, new Date());
	}

	/**
	 * 根据时间生成32位ID
	 * 
	 * @param date
	 *            指定时间
	 * @return yyyyMMddHHmmssSSS+ranNum(15)
	 */
	public static String id32(Date date) {
		return msid(32, date);
	}

	/**
	 * 根据8位前缀生成32位ID
	 * 
	 * @param p8
	 *            8位长度前缀(长度大于8时将抛出runtime异常)
	 * @return p8+yyyyMMddHHmmssSSS+ranNum(7)
	 */
	public static String p8(String p8) {
		String p = StringUtils.padZeroToLeft(p8, 8);
		if (p.length() != 8) {
			throw new RuntimeException("length of p8 out of 8.");
		}
		StringBuilder sb = new StringBuilder();
		sb.append(p);
		sb.append(msid(24, null));
		return sb.toString();
	}

	/**
	 * 是否根据8位前缀生成32位ID
	 * 
	 * @param id
	 *            ID
	 * @return true:符合P8规则
	 */
	public static boolean isP8(String id) {
		return null != id && id.length() == 32 && id.matches("[0-9]{8}20[0-9]{22}");
	}

	/**
	 * 根据4位前缀生成32位ID
	 * 
	 * @param p4
	 *            4位长度前缀(长度大于8时将抛出runtime异常)
	 * @return p4+yyyyMMddHHmmssSSS+ranNum(11)
	 */
	public static String p4(String p4) {
		String p = StringUtils.padZeroToLeft(p4, 4);
		if (p.length() != 4) {
			throw new RuntimeException("length of p4 out of 4.");
		}
		StringBuilder sb = new StringBuilder();
		sb.append(p);
		sb.append(msid(28, null));
		return sb.toString();
	}

	/**
	 * 是否根据4位前缀生成32位ID
	 * 
	 * @param id
	 *            ID
	 * @return true:符合P4规则
	 */
	public static boolean isP4(String id) {
		return null != id && id.length() == 32 && id.matches("[0-9]{4}20[0-9]{26}");
	}

	/**
	 * ID 生成 - 按时间生成,yyyyMMddHHmmssSSS(17位)+(len-17)位随机数字
	 * 
	 * @param len
	 *            长度,超过17的填充随机数字
	 * @param date
	 *            指定时间
	 * @return 指定长度的数字字符串
	 */
	private static String msid(int len, Date date) {
		StringBuilder id = new StringBuilder();
		id.append(DateUtils.DF_TIME_SF.format(null != date ? date : new Date()));
		int l = len - 17;
		if (l > 0) {
			id.append(StringUtils.ranNum(l));
			return id.toString();
		} else {
			return id.substring(0, len);
		}
	}

	/**
	 * 字符串是否符合id规则
	 * 
	 * <pre>
	 * id32、id20、idP4、idP8
	 * </pre>
	 * 
	 * @see IdUtils#id32()
	 * @see IdUtils#id20()
	 * @param id
	 *            ID
	 * @return 1: id32() <br/>
	 *         2: id20()<br/>
	 *         3: 32位数字(p4、p8)
	 */
	public static int isId(String id) {
		if (null != id) {
			int l = id.length();
			if (l == 20) {
				if (id.matches("20[0-9]{18}")) {
					return 2;
				}
			} else if (l == 32) {
				if (id.matches("20[0-9]{30}")) {
					return 1;
				}
				if (id.matches("[0-9]{32}")) {
					return 3;
				}
			}
		}
		return 0;
	}

	/**
	 * 字符串是否符合id20规则
	 * 
	 * <pre>
	 * match 20[0-9]{18}
	 * </pre>
	 * 
	 * @see #id20()
	 * @param id
	 *            ID
	 * @return true：符合id20规则
	 */
	public static boolean isId20(String id) {
		return null != id && id.matches("20[0-9]{18}");
	}

	/**
	 * 字符串是否符合id32规则
	 * 
	 * <pre>
	 * match 20[0-9]{30}
	 * </pre>
	 * 
	 * @see #id32()
	 * @param id
	 *            ID
	 * @return true：符合id32规则
	 */
	public static boolean isId32(String id) {
		return null != id && id.matches("20[0-9]{30}");
	}

	/**
	 * 基于id32()/id20()生成的id获取时间
	 * 
	 * @param id
	 *            id
	 * @return 时间(yyyy-MM-dd HH:mm:ss)/null
	 */
	public static String idToTime(String id) {
		if (null != id && id.length() > 14) {
			String s = id.substring(0, 14);
			if (s.matches(REG_TIME_S)) {
				StringBuilder sb = new StringBuilder(s);
				sb.insert(12, ":");
				sb.insert(10, ":");
				sb.insert(8, " ");
				sb.insert(6, "-");
				sb.insert(4, "-");
				return sb.toString();
			}
		}
		return null;
	}

	/**
	 * 基于idP4生成的id获取时间
	 * 
	 * @param id
	 *            id
	 * @return 时间(yyyy-MM-dd HH:mm:ss)/null
	 */
	public static String idToTimeP4(String id) {
		if (null != id && id.length() >= 18) {
			String s = id.substring(4, 18);
			if (s.matches(REG_TIME_S)) {
				StringBuilder sb = new StringBuilder(s);
				sb.insert(12, ":");
				sb.insert(10, ":");
				sb.insert(8, " ");
				sb.insert(6, "-");
				sb.insert(4, "-");
				return sb.toString();
			}
		}
		return null;
	}

	/**
	 * 基于idP8生成的id获取时间
	 * 
	 * @param id
	 *            id
	 * @return 时间(yyyy-MM-dd HH:mm:ss)/null
	 */
	public static String idToTimeP8(String id) {
		if (null != id && id.length() >= 22) {
			String s = id.substring(8, 22);
			if (s.matches(REG_TIME_S)) {
				StringBuilder sb = new StringBuilder(s);
				sb.insert(12, ":");
				sb.insert(10, ":");
				sb.insert(8, " ");
				sb.insert(6, "-");
				sb.insert(4, "-");
				return sb.toString();
			}
		}
		return null;
	}

	/**
	 * 根据P4及时间,生成用于对比的ID字符串
	 * 
	 * @param p4
	 *            P4
	 * @param time
	 *            能解析为yyyyMMddHHmmss、yyyyMMddd或者yyyyMM的时间/日期字符串
	 * @param max
	 *            是否最大ID
	 * @return time格式错误：null<br/>
	 *         max true：32位p4及时间最大可能ID值<br/>
	 *         max false：32位p4及时间最小可能ID值
	 */
	public static String timeToIdP4(String p4, String time, boolean max) {
		String s = timeFullS(time, max);
		if (null == s) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(p4);// 4
		sb.append(s);// 14
		if (max) {
			sb.append("99999999999999");// 14
		} else {
			sb.append("00000000000000");// 14
		}
		return sb.toString();
	}

	/**
	 * 根据P8及时间,生成用于对比的ID字符串
	 * 
	 * @param p8
	 *            P8
	 * @param time
	 *            能解析为yyyyMMddHHmmss、yyyyMMddd或者yyyyMM的时间/日期字符串
	 * @param max
	 *            是否最大ID
	 * @return time格式错误：null<br/>
	 *         max true：32位p8及时间最大可能ID值<br/>
	 *         max false：32位p8及时间最小可能ID值
	 */
	public static String timeToIdP8(String p8, String time, boolean max) {
		String s = timeFullS(time, max);
		if (null == s) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(p8);// 8
		sb.append(s);// 14
		if (max) {
			sb.append("9999999999");// 10
		} else {
			sb.append("0000000000");// 10
		}
		return sb.toString();
	}

	/**
	 * 根据时间生成用于对比的ID字符串
	 * 
	 * @param time
	 *            能解析为yyyyMMddHHmmss、yyyyMMddd或者yyyyMM的时间/日期字符串
	 * @param max
	 *            是否最大ID
	 * @return time格式错误：null<br/>
	 *         max true：20位时间最大可能ID值<br/>
	 *         max false：20位时间最小可能ID值
	 */
	public static String timeToId20(String time, boolean max) {
		String s = timeFullS(time, max);
		if (null == s) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(s);// 14
		if (max) {
			sb.append("999999");// 6
		} else {
			sb.append("000000");// 6
		}
		return sb.toString();
	}

	/**
	 * 根据时间生成用于对比的ID字符串
	 * 
	 * @param time
	 *            能解析为yyyyMMddHHmmss、yyyyMMddd或者yyyyMM的时间/日期字符串
	 * @param max
	 *            是否最大ID
	 * @return time格式错误：null<br/>
	 *         max true：32位时间最大可能ID值<br/>
	 *         max false：32位时间最小可能ID值
	 */
	public static String timeToId32(String time, boolean max) {
		String s = timeFullS(time, max);
		if (null == s) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(s);// 14
		if (max) {
			sb.append("999999999999999999");// 18
		} else {
			sb.append("000000000000000000");// 18
		}
		return sb.toString();
	}

	/** 时间/日期/月份字符串处理为yyyyMMddHHmmss字符串 */
	private static String timeFullS(String time, boolean max) {
		String s = null;
		if (null != time) {
			int l = time.length();
			if (l == 19) {
				s = time.replaceAll("[^0-9]", "");
				s = s.matches(REG_TIME_S) ? s : null;
			} else if (l == 14) {
				s = time.matches(REG_TIME_S) ? time : null;
			} else {
				if (l == 10) {
					s = time.replaceAll("[^0-9]", "");
					s = s.matches(REG_DATE_S) ? (s + (max ? "235959" : "000000")) : null;
				} else if (l == 8) {
					s = time.matches(REG_DATE_S) ? (time + (max ? "235959" : "000000")) : null;
				} else if (l == 7) {
					s = time.replaceAll("[^0-9]", "");
					s = s.matches(REG_MONTH_S) ? (s + (max ? "31235959" : "01000000")) : null;
				} else if (l == 6) {
					s = time.matches(REG_MONTH_S) ? (time + (max ? "31235959" : "01000000")) : null;
				}
			}
		}
		return s;
	}

	/**
	 * 字符串组合转为id32集合(id32,id32,...)
	 * 
	 * @param str
	 *            字符串组合
	 * @param p
	 *            P4/P8前缀值||null
	 * @param distinct
	 *            是否过滤重复数据
	 * @return List/null
	 */
	public static List<String> strTo32s(String str, String p, boolean distinct) {
		return strTo32s(str, p, distinct, ",");
	}

	/**
	 * 字符串组合转为id32集合
	 * 
	 * @param str
	 *            字符串组合
	 * @param p
	 *            P4/P8前缀值||null
	 * @param distinct
	 *            是否过滤重复数据
	 * @param split
	 *            分割字符(允许,;)
	 * @return List/null
	 */
	public static List<String> strTo32s(String str, String p, boolean distinct, String split) {
		if (null != str) {
			str = str.replaceAll("[^0-9" + split + "]", "");
			String[] rr = str.split(split);
			String regex;
			int i = null != p ? p.length() : 0;
			if (i == 4) {
				regex = p + "20[0-9]{26}";
			} else if (i == 8) {
				regex = p + "20[0-9]{22}";
			} else {
				regex = "20[0-9]{30}";
			}
			List<String> ids = new ArrayList<String>();
			if (distinct) {
				Set<String> set = new LinkedHashSet<>();
				for (String s : rr) {
					if (s.length() == 32 && s.matches(regex)) {
						set.add(s);
					}
				}
				ids.addAll(set);
				set.clear();
				set = null;
			} else {
				for (String s : rr) {
					if (s.length() == 32 && s.matches(regex)) {
						ids.add(s);
					}
				}
			}
			if (!ids.isEmpty()) {
				return ids;
			}
		}
		return null;
	}

}
