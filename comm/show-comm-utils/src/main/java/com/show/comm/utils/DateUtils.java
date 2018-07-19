package com.show.comm.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @author heyuhua
 * @date 2017年6月22日
 */
public class DateUtils {

	private DateUtils() {
	}
	/** 日期(yyyy-MM)正则校验字符 */
	public static final String RF_MON = "20[0-9][0-9]-(([0][1-9])|([1][0-2]))";
	/** 日期(yyyyMM)正则校验字符 */
	public static final String RF_MON_S = "20[0-9][0-9](([0][1-9])|([1][0-2]))";
	
	/** 日期(yyyy-MM-dd)正则校验字符 */
	public static final String RF_DATE = "20[0-9][0-9]-(([0][1-9])|([1][0-2]))-(([0][1-9])|([1-2][0-9])|(3[0,1]))";
	/** 日期(yyyyMMdd)正则校验字符 */
	public static final String RF_DATE_S = "20[0-9][0-9](([0][1-9])|([1][0-2]))(([0][1-9])|([1-2][0-9])|(3[0,1]))";

	/** 时间(yyyy-MM-dd HH:mm:ss)正则校验字符 */
	public static final String RF_TIME = "20[0-9][0-9]-(([0][1-9])|([1][0-2]))-(([0][1-9])|([1-2][0-9])|(3[0,1])) (([0-1][0-9])|([2][0-3])):[0-5][0-9]:[0-5][0-9]";
	/** 时间(yyyyMMddHHmmss)正则校验字符 */
	public static final String RF_TIME_S = "20[0-9][0-9](([0][1-9])|([1][0-2]))(([0][1-9])|([1-2][0-9])|(3[0,1]))(([0-1][0-9])|([2][0-3]))[0-5][0-9][0-5][0-9]";
	/** 时间(HH:mm)正则校验字符 */
	public static final String RF_HHMM = "(([0-1][0-9])|([2][0-3])):[0-5][0-9]";
	/** 时间(HH:mm:ss)正则校验字符 */
	public static final String RF_HHMMSS = "(([0-1][0-9])|([2][0-3])):[0-5][0-9]:[0-5][0-9]";

	/** 月份格式yyyy-MM */
	public static final SimpleDateFormat DF_MON = new SimpleDateFormat("yyyy-MM");
	/** 月份格式yyyyMM */
	public static final SimpleDateFormat DF_MON_S = new SimpleDateFormat("yyyyMM");

	/** 日期格式yyyy-MM-dd */
	public static final SimpleDateFormat DF_DATE = new SimpleDateFormat("yyyy-MM-dd");
	/** 日期格式yyyyMMdd */
	public static final SimpleDateFormat DF_DATE_S = new SimpleDateFormat("yyyyMMdd");

	/** 时间格式yyyy-MM-dd HH:mm:ss */
	public static final SimpleDateFormat DF_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/** 时间格式yyyyMMddHHmmss */
	public static final SimpleDateFormat DF_TIME_S = new SimpleDateFormat("yyyyMMddHHmmss");
	/** 时间格式yyyyMMddHHmmssSSS */
	public static final SimpleDateFormat DF_TIME_SF = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	/** 时间格式yyyy-MM-dd HH:mm */
	public static final SimpleDateFormat DF_TIME_MIN = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	/** 时间格式yyyyMMddHH */
	public static final SimpleDateFormat DF_yMdH = new SimpleDateFormat("yyyyMMddHH");
	/** 时间格式HH:mm:ss */
	public static final SimpleDateFormat DF_Hms = new SimpleDateFormat("HH:mm:ss");
	/** 时间格式HH:mm */
	public static final SimpleDateFormat DF_Hm = new SimpleDateFormat("HH:mm");
	/** 时间格式MM-dd HH:mm */
	public static final SimpleDateFormat DF_MdHm = new SimpleDateFormat("MM-dd HH:mm");

	/**
	 * 获取当前时间
	 * 
	 * @see #DF_TIME
	 * @return 格式化后的字符串
	 */
	public static String now() {
		return DF_TIME.format(new Date());
	}

	/**
	 * 获取当前日期
	 * 
	 * @see #DF_DATE
	 * @return 格式化后的字符串
	 */
	public static String nowDate() {
		return DF_DATE.format(new Date());
	}

	/**
	 * 获取当前日期
	 * 
	 * @see #DF_DATE_S
	 * @return int
	 */
	public static int nowDateInt() {
		return Integer.parseInt(DateUtils.DF_DATE_S.format(new Date()));
	}

	/**
	 * 格式化当前时间
	 * 
	 * @param pattern
	 *            字符串格式,为null则使用默认{@link #DF_DATE}
	 * @return 格式化后的字符串
	 */
	public static String now(String pattern) {
		return format(new Date(), pattern);
	}

	/**
	 * 当前月份
	 * 
	 * @see #DF_MON_S
	 * @return yyyyMM
	 */
	public static String nowMonth() {
		return DF_MON_S.format(new Date());
	}

	/**
	 * yyyy-MM/yyyyMM转换为yyyy年MM月
	 * 
	 * @param mon
	 *            月份
	 * @return 中文月份
	 */
	public static String monthCH(String mon) {
		int i = isMonth(mon);
		if (i == 1 || i == 2) {
			String m = i == 1 ? mon.substring(5) : mon.substring(4);
			StringBuilder sb = new StringBuilder(8);
			sb.append(mon.substring(0, 4));
			sb.append("年");
			sb.append(m.charAt(0) == '0' ? m.substring(1) : m);
			sb.append("月");
			return sb.toString();
		} else {
			return null;
		}
	}

	/**
	 * 使用DF_TIME格式化时间
	 * 
	 * @see #DF_TIME
	 * 
	 * @param d
	 *            时间
	 * @return 格式化后的字符串
	 */
	public static String format(Date d) {
		if (null != d) {
			return DF_TIME.format(d);
		}
		return null;
	}

	/**
	 * 格式化时间
	 * 
	 * @param d
	 *            时间
	 * @param pattern
	 *            字符串格式,为null则使用默认DF_TIME
	 * @return 格式化后的字符串
	 */
	public static String format(Date d, String pattern) {
		if (null == pattern || pattern.length() < 2) {
			return DF_TIME.format(d);
		}
		return new SimpleDateFormat(pattern).format(d);
	}

	/**
	 * 使用DF_DATE格式化日期
	 * 
	 * @see #DF_DATE
	 * @param d
	 *            日期
	 * @return 格式化后的字符串
	 */
	public static String formatDate(Date d) {
		if (null != d) {
			return DF_DATE.format(d);
		}
		return null;
	}

	/**
	 * 字符串(yyyy-MM-dd HH:mm:ss)转换为时间
	 * 
	 * @param s
	 *            字符串{@link #DF_TIME}
	 * @return 时间,转换出错为null
	 */
	public static Date parse(String s) {
		return parse(s, null);
	}

	/**
	 * 字符串转换为时间
	 * 
	 * @param s
	 *            字符串
	 * @param pattern
	 *            字符串格式,为null则使用默认DF_TIME
	 * @return 时间,转换出错为null
	 */
	public static Date parse(String s, String pattern) {
		if (null != s) {
			try {
				if (null == pattern || pattern.length() < 4) {
					return DF_TIME.parse(s);
				}
				return new SimpleDateFormat(pattern).parse(s);
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * 字符串(yyyy-MM-dd)转换为日期
	 * 
	 * @param s
	 *            字符串{@link #DF_DATE}
	 * @return 日期,转换出错为null
	 */
	public static Date parseDate(String s) {
		int i = isDateM(s);
		if (i == 1) {
			try {
				return DF_DATE.parse(s);
			} catch (ParseException e) {
			}
		} else if (i == 2) {
			try {
				return DF_DATE_S.parse(s);
			} catch (ParseException e) {
			}
		}
		return null;
	}

	/**
	 * 字符串转换为日期
	 * 
	 * @param s
	 *            字符串
	 * @param pattern
	 *            字符串格式,为null则使用默认DF_DATE
	 * @return 时间,转换出错为null
	 */
	public static Date parseDate(String s, String pattern) {
		if (null != s) {
			try {
				if (null == pattern || pattern.length() < 4) {
					return DF_DATE.parse(s);
				}
				return new SimpleDateFormat(pattern).parse(s);
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * 获取指定时间的下/上指定秒数
	 * 
	 * @param time
	 *            时间
	 * @param seconds
	 *            秒数
	 * @return 增加指定秒数后的时间
	 */
	public static Date nextSecond(Date time, int seconds) {
		if (null != time && seconds != 0) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(time);
			calendar.add(Calendar.SECOND, seconds);
			return calendar.getTime();
		}
		return time;
	}

	/**
	 * 获取指定时间的下一天
	 * 
	 * @param thisDay
	 *            指定时间
	 * @return 下一天(0点整)
	 */
	public static Date nextDay(Date thisDay) {
		return nextDay(thisDay, 1);
	}

	/**
	 * 获取指定时间的下/上指定天数
	 * 
	 * @param thisDay
	 *            指定时间
	 * @param days
	 *            相隔天数(>0为向后，<0为向前,0为格式化时间为当天开始时间)
	 * @return 指定日期(0点整)(calendar.add(Calendar.DATE, days))
	 */
	public static Date nextDay(Date thisDay, int days) {
		if (null != thisDay) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(thisDay);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			if (days != 0) {
				calendar.add(Calendar.DATE, days);
			}
			return calendar.getTime();
		}
		return null;
	}

	/**
	 * 返回指定时间当天最小时间(精确至毫秒)
	 * 
	 * @param day
	 *            时间,null则为当前时间
	 * @return yyyy-mm-dd 00:00:00.0
	 */
	public static Date dayMin(Date day) {
		Calendar cal = Calendar.getInstance();
		if (null != day) {
			cal.setTime(day);
		}
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 返回指定时间当天最大时间(精确至毫秒)
	 * 
	 * @param day
	 *            时间,null则为当前时间
	 * @return yyyy-mm-dd 23:59:59.999
	 */
	public static Date dayMax(Date day) {
		Calendar cal = Calendar.getInstance();
		if (null != day) {
			cal.setTime(day);
		}
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * 获取指定时间月份开始时间
	 * 
	 * @param thisDay
	 *            指定时间
	 * @return 指定时间所在月份开始时间
	 */
	public static Date monthBegin(Date thisDay) {
		return monthBegin(thisDay, 0);
	}

	/**
	 * 获取指定时间前/后月份
	 * 
	 * @param thisDay
	 *            指定时间
	 * @param month
	 *            要加的月数，负数为往前，正数往后
	 * @return 指定时间前/后月份月份
	 */
	public static Date monthBegin(Date thisDay, int month) {
		if (null != thisDay) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(thisDay);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(Calendar.DATE, 1);
			if (0 != month) {
				calendar.add(Calendar.MONTH, month);
			}
			return calendar.getTime();
		}
		return null;
	}

	/** 星期 汉字 数组(1-7) */
	public static final String[] WEEKDAYS = new String[] { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	/**
	 * 获取中文星期
	 * 
	 * @param day
	 *            每周第几天 (1-7,星期日 - 星期六)
	 * @return 星期日 - 星期六/空字符
	 */
	public static String weekDay(int day) {
		if (day >= 1 && day <= 7) {
			return WEEKDAYS[day - 1];
		}
		return "";
	}

	/**
	 * 格式化小时:分钟字符串
	 * 
	 * @param HHmm
	 *            时间(HH:mm)
	 * @return 成功返回HH:mm，否则返回null
	 */
	public static String fmtHHmm(String HHmm) {
		if (null != HHmm) {
			HHmm = HHmm.replaceAll("[^0-9:]", "");
			int len = HHmm.length();
			if (len > 5) {
				HHmm = HHmm.substring(0, 5);
				if (HHmm.matches(RF_HHMM)) {
					return HHmm;
				}
			} else if (len == 5 && HHmm.matches(RF_HHMM)) {
				return HHmm;
			}

		}
		return null;
	}

	/**
	 * 验证字符串是否小时:分钟格式
	 * 
	 * @see RF_HHMM
	 * @param HHmm
	 *            时间格式字符串(HH:mm)
	 * 
	 * @return 是:true，否:false
	 */
	public static boolean isHHmm(String HHmm) {
		if (null != HHmm && HHmm.length() == 5) {
			if (HHmm.matches(RF_HHMM)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 验证字符串是否标准日期格式
	 * 
	 * @see #DF_DATE
	 * @see #RF_DATE
	 * @param date
	 *            日期格式(yyyy-MM-dd)(2000年后)
	 * @return 是:true，否:false
	 */
	public static boolean isDate(String date) {
		if (null != date && date.length() == 10) {
			return date.matches(RF_DATE);
		}
		return false;
	}

	/**
	 * 验证字符串是否日期格式
	 * 
	 * @param date
	 *            日期格式(yyyy-MM-dd/yyyyMMdd)(2000年后)字符串
	 * @return yyyy-MM-dd:1 <br/>
	 *         yyyyMMdd:2<br/>
	 *         其它:0
	 */
	public static int isDateM(String date) {
		if (null != date) {
			int l = date.length();
			if (l == 10) {
				return date.matches(RF_DATE) ? 1 : 0;
			} else if (l == 8) {
				return date.matches(RF_DATE_S) ? 2 : 0;
			}
		}
		return 0;
	}

	/**
	 * 日期格式字符串转为int
	 * 
	 * @param date
	 *            日期格式(yyyy-MM-dd/yyyyMMdd)(2000年后)字符串
	 * @return 转换成功：20******<br/>
	 *         参数为空：-1<br/>
	 *         格式错误：0
	 */
	public static int toDateInt(String date) {
		String s = StringUtils.trim(date);
		if (null == s) {
			return -1;
		}
		int l = date.length();
		if (l == 10) {
			if (date.matches(RF_DATE)) {
				return Integer.parseInt(date.replaceAll("-", ""));
			}
		} else if (l == 8) {
			if (date.matches(RF_DATE_S)) {
				return Integer.parseInt(date);
			}
		}
		return 0;
	}

	/**
	 * 日期格式字符串转为int
	 * 
	 * @param date
	 *            日期格式(yyyy-MM-dd/yyyyMMdd)(2000年后)字符串
	 * @param dau
	 *            缺省值
	 * @return 转换成功：20******<br/>
	 *         转换失败：dau
	 */
	public static int toDateInt(String date, int dau) {
		int i = toDateInt(date);
		if (i == 0 || i == -1) {
			return dau;
		}
		return i;
	}

	/**
	 * 日期格式字符串转为int
	 * 
	 * @param date
	 *            日期格式(yyyy-MM-dd/yyyyMMdd)(2000年后)字符串
	 * @param dau
	 *            缺省值
	 * @param errThenDau
	 *            指定返回缺省值的错误代码
	 * @return 转换成功：20******<br/>
	 *         参数为空：errThenDau为-1?dau:-1<br/>
	 *         格式错误：errThenDau为0?dau:0
	 * 
	 */
	public static int toDateInt(String date, int dau, int errThenDau) {
		int i = toDateInt(date);
		if (i == errThenDau) {
			return dau;
		}
		return i;
	}

	/**
	 * 判断日期2是否超过日期1指定天数
	 * 
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @param maxDays
	 *            天数
	 * @return 超过指定天数：1<br/>
	 *         等于指定天数：0<br/>
	 *         小于指定天数：-1<br/>
	 *         日期1格式错误：-11<br/>
	 *         日期2格式错误：-12
	 */
	public static int moreThan(String date1, String date2, int maxDays) {
		Date d1 = parseDate(date1);
		if (null == d1) {
			return -11;
		}
		Date d2 = parseDate(date2);
		if (null == d2) {
			return -12;
		}
		d1 = nextDay(d1, maxDays);
		return d2.compareTo(d1);
	}

	/**
	 * 判断日期2是否超过日期1指定天数
	 * 
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @param maxDays
	 *            天数
	 * @return 超过指定天数：1<br/>
	 *         等于指定天数：0<br/>
	 *         小于指定天数：-1<br/>
	 *         日期1格式错误：-11<br/>
	 *         日期2格式错误：-12
	 */
	public static int moreThan(int date1, int date2, int maxDays) {
		return moreThan(Integer.toString(date1), Integer.toString(date2), maxDays);
	}

	/**
	 * 验证字符串是否yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @param str
	 *            字符串(2000年后)
	 * @return 是:true，否:false
	 */
	public static boolean isTime(String str) {
		if (null != str && str.length() == 19) {
			return str.matches(RF_TIME);
		}
		return false;
	}

	/**
	 * 是否月份
	 * 
	 * @param mon
	 *            yyyy-MM(2000-01~2099-12)<br/>
	 *            或者<br/>
	 *            yyyyMM(200001~209912)
	 * @return 1:符合yyyy-MM规则<br/>
	 *         2:符合yyyyMM规则<br/>
	 *         0:非月份
	 */
	public static int isMonth(String mon) {
		if (null != mon) {
			int len = mon.length();
			if (len == 7) {
				if (mon.matches("20[0-9][0-9]-(([0][1-9])|([1][0-2]))")) {
					return 1;
				} else {
					return 0;
				}
			} else if (len == 6) {
				if (mon.matches("20[0-9][0-9](([0][1-9])|([1][0-2]))")) {
					return 2;
				} else {
					return 0;
				}
			}
		}
		return 0;
	}

	/**
	 * 验证字符串是否指定日期格式
	 * 
	 * @param str
	 *            字符串
	 * @param pattern
	 *            格式
	 * @return 是:true，否:false
	 */
	public static boolean isFmt(String str, String pattern) {
		if (null != str && null != pattern && str.length() == pattern.length()) {
			try {
				DateFormat fmt = new SimpleDateFormat(pattern);
				if (null != fmt.parse(str)) {
					return true;
				}
			} catch (ParseException e) {
			}
		}
		return false;
	}

	/**
	 * 比较日期(仅对比年月日)
	 * 
	 * @param s
	 *            需要对比的时间
	 * @param t
	 *            被对比的时间
	 * @return s&gt;t:1；s&lt;t:-1；s=t:0
	 */
	public static int compareDate(Date s, Date t) {
		if (null == s && null == t) {
			return 1;
		}
		Calendar calendar = Calendar.getInstance();
		if (null != t) {
			calendar.setTime(t);
		}
		int ty = calendar.get(Calendar.YEAR);// 当前年份
		int td = calendar.get(Calendar.DAY_OF_YEAR);// 当前天
		if (null != s) {
			calendar.setTime(s);
		}
		int sy = calendar.get(Calendar.YEAR);// 对比年份
		int sd = calendar.get(Calendar.DAY_OF_YEAR);// 对比天
		if (ty == sy) {
			if (td == sd) {
				return 0;
			} else if (td > sd) {
				return -1;
			} else {
				return 1;
			}
		} else {
			return ty > sy ? -1 : 1;
		}
	}
}
