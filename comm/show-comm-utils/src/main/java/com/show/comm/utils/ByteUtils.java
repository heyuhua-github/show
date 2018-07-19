package com.show.comm.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Byte工具类
 * 
 * @author heyuhua
 * @date 2017年12月27日
 */
public class ByteUtils {

	private ByteUtils() {
	}

	/** 空byte[] */
	public static final byte[] EMPTY = "".getBytes();

	/**
	 * null转为byte[]
	 * 
	 * @return {@link #EMPTY}
	 */
	public static byte[] toBytes() {
		return EMPTY;
	}

	/**
	 * String转换为byte[]
	 * 
	 * @param obj
	 *            String对象
	 * @return byte[]/{@link #EMPTY}
	 */
	public static byte[] toBytes(String obj) {
		if (null != obj) {
			return obj.toString().getBytes();
		}
		return EMPTY;
	}

	/**
	 * Enum转换为byte[]
	 * 
	 * @param obj
	 *            Enum对象
	 * @return byte[]/{@link #EMPTY}
	 */
	public static byte[] toBytes(Enum<?> obj) {
		if (null != obj) {
			return obj.toString().getBytes();
		}
		return EMPTY;
	}

	/**
	 * 时间转换为byte[]
	 * 
	 * @param date
	 *            Date对象
	 * @return byte[]/{@link #EMPTY}
	 */
	public static byte[] toBytes(Date date) {
		if (null != date) {
			return DateUtils.DF_TIME.format(date).getBytes();
		}
		return EMPTY;
	}

	/**
	 * byte[]转为Long
	 * 
	 * @param bs
	 *            byte[]
	 * @return long/null
	 */
	public static Long bytesToLong(byte[] bs) {
		if (null != bs && bs.length != 0) {
			String s = new String(bs);
			return StringUtils.toLong(s);
		}
		return null;
	}

	/**
	 * byte[]转为long
	 * 
	 * @param bs
	 *            byte[]
	 * @param dau
	 *            缺省值
	 * @return long/dau
	 */
	public static long bytesToLong(byte[] bs, long dau) {
		if (null != bs && bs.length != 0) {
			String s = new String(bs);
			return StringUtils.toLong(s, dau);
		}
		return dau;
	}

	/**
	 * String Set 转为 byte[] Set
	 * 
	 * @param ss
	 *            String Set
	 * @return byte[] Set (非null)
	 */
	public static Set<byte[]> setFromStrToBytes(Set<String> ss) {
		if (null == ss || ss.isEmpty()) {
			return new HashSet<>();
		}
		Set<byte[]> rs = new HashSet<>(ss.size());
		for (String s : ss) {
			rs.add(s.getBytes());
		}
		return rs;
	}

	/**
	 * byte[] Set 转为 String Set
	 * 
	 * @param bs
	 *            byte[] Set
	 * @return String Set (非null)
	 */
	public static Set<String> setFromBytesToStr(Set<byte[]> bs) {
		if (null == bs || bs.isEmpty()) {
			return new HashSet<>();
		}
		Set<String> rs = new HashSet<>(bs.size());
		for (byte[] b : bs) {
			rs.add(new String(b));
		}
		return rs;
	}

	/**
	 * String Collection转换为byte[]数组
	 * 
	 * @param ss
	 *            String Collection
	 * @return byte[] Array / null
	 */
	public static byte[][] bytesArrayFromStrCol(Collection<String> ss) {
		if (null != ss && !ss.isEmpty()) {
			byte[][] arr = new byte[ss.size()][];
			int i = 0;
			for (String s : ss) {
				if (null != s) {
					arr[i++] = s.getBytes();
				}
			}
			return i > 0 ? arr : null;
		}
		return null;
	}

	/**
	 * String Collection转换为byte[]数组
	 * 
	 * @param ss
	 *            String Collection
	 * @return byte[] Array / null
	 */
	public static Object[] bytesObjArrayFromStrCol(Collection<String> ss) {
		return bytesArrayFromStrCol(ss);
	}

	/**
	 * byte[] Collection转换为byte[]数组
	 * 
	 * @param bs
	 *            byte[] Collection
	 * @return byte[] Array / null
	 */
	public static byte[][] bytesArrayFromBytesCol(Collection<byte[]> bs) {
		if (null != bs && !bs.isEmpty()) {
			byte[][] arr = new byte[bs.size()][];
			int i = 0;
			for (byte[] b : bs) {
				arr[i++] = b;
			}
			return arr;
		}
		return null;
	}

	/**
	 * byte[] map转为String map
	 * 
	 * @param bm
	 *            byte[] map
	 * @return String map (非null)
	 */
	public static Map<String, String> mapFromBytesToStr(Map<byte[], byte[]> bm) {
		Map<String, String> sm = new HashMap<>();
		if (null != bm && !bm.isEmpty()) {
			for (Entry<byte[], byte[]> e : bm.entrySet()) {
				byte[] v = e.getValue();
				if (null != v) {
					sm.put(new String(e.getKey()), new String(v));
				}
			}
		}
		return sm;
	}

	/**
	 * String map 转为 byte[] map
	 * 
	 * @param sm
	 *            byte[] map
	 * @return byte[] map (非null)
	 */
	public static Map<byte[], byte[]> mapFromStrToBytes(Map<String, String> sm) {
		Map<byte[], byte[]> bm = new HashMap<>();
		if (null != sm && !sm.isEmpty()) {
			for (Entry<String, String> e : sm.entrySet()) {
				String v = e.getValue();
				if (null != v) {
					bm.put(e.getKey().getBytes(), v.getBytes());
				}
			}
		}
		return bm;
	}

	/**
	 * String Collection转换为byte[]集合
	 * 
	 * @param ss
	 *            String Collection
	 * @return List<byte[]> / null
	 */
	public static List<byte[]> bytesListFromStrCol(Collection<String> ss) {
		if (null != ss && !ss.isEmpty()) {
			List<byte[]> bss = new ArrayList<>();
			for (String s : ss) {
				if (null != s) {
					bss.add(s.getBytes());
				}
			}
			return bss.isEmpty() ? null : bss;
		}
		return null;
	}
}
