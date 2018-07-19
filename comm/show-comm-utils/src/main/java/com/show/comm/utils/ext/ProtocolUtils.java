package com.show.comm.utils.ext;

import java.nio.ByteBuffer;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * TCP协议数据处理工具类
 * 
 * @author heyuhua
 * @date 2018年1月24日
 */
public class ProtocolUtils {

	/** 加密算法 */
	private static final String ALGORITHM = "AES";
	/** 填充算法 */
	private static final String TRANSFORMER = "AES/ECB/NoPadding";

	
	/**
	 * aes加密
	 * 
	 * @param source
	 *            明文
	 * @param key
	 *            密码
	 * @return 密文
	 */
	public static byte[] encrypt(byte[] source, byte[] key) {
		if (null != source && null != key) {
			try {
				byte[] sb = source;
				int length = sb.length;
				int yu = length % 16;
				if (yu != 0) {
					ByteBuffer bb = ByteBuffer.allocate(length + (16 - yu));
					bb.put(sb);
					byte[] array = bb.array();
					for (int i = length; i < array.length; i++) {
						array[i] = 0x00;
					}
					sb = array;
				}
				Cipher cipher = Cipher.getInstance(TRANSFORMER);// 创建密码器
				cipher.init(Cipher.ENCRYPT_MODE, genKey(key));// 初始化
				byte[] result = cipher.doFinal(sb);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * aes解密
	 * 
	 * @param source
	 *            密文
	 * @param key
	 *            密码
	 * @return 明文
	 */
	public static byte[] decrypt(byte[] source, byte[] key) {
		if (null != source && null != key) {
			try {
				Cipher cipher = Cipher.getInstance(TRANSFORMER);// 创建密码器
				cipher.init(Cipher.DECRYPT_MODE, genKey(key));// 初始化
				return cipher.doFinal(source);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/** 密钥规则 */
	private static SecretKeySpec genKey(byte[] key) {
		return new SecretKeySpec(key, ALGORITHM);
	}

	/**
	 * String key转为 16Bytes key
	 * 
	 * @param key
	 *            String
	 * @return 16Bytes
	 */
	public static byte[] keyToByte(String key) {
		byte[] bs = key.getBytes();
		if (bs.length != 16) {
			bs = Arrays.copyOf(bs, 16);// 处理数组长度为16
		}
		return bs;
	}

	/**
	 * 10进制卡号转为16进制卡号
	 * 
	 * @param dec
	 *            不超过10位10进制卡号
	 * @return 4Bytes长度16进制卡号
	 */
	public static String cardFromDecToHex(String dec) {
		if (null == dec || !dec.matches("[0-9]{1,10}")) {
			throw new RuntimeException("Card [dec:" + dec + "] is illegal.");
		}
		String hex = Long.toHexString(Long.parseLong(dec, 10)).toUpperCase();
		int len = hex.length();
		if (len == 8) {
			return hex;
		} else if (len > 8) {
			throw new RuntimeException("Card [dec:" + dec + "] is illegal.");
		}
		return ("00000000" + hex).substring(len);
	}

	/**
	 * 16进制卡号转为10进制卡号
	 * 
	 * @param hex
	 *            4Bytes长度16进制卡号
	 * @return 10位10进制卡号
	 */
	public static String cardFromHexToDec(String hex) {
		if (null == hex || !hex.matches("[0-9A-Za-z]{1,8}")) {
			throw new RuntimeException("Card [hex:" + hex + "] is illegal.");
		}
		String card = String.valueOf(Long.parseLong(hex, 16));
		int len = card.length();
		if (len == 10) {
			return card;
		} else if (len > 10) {
			throw new RuntimeException("Card [hex:" + hex + "] is illegal.");
		}
		return ("0000000000" + card).substring(len);
	}

	/**
	 * 10进制转16进制字符串 (Little-Endian)
	 * 
	 * @param i
	 *            int
	 * @return hex
	 */
	public static String littleEndian(int i) {
		return littleEndian(i, 0, true);
	}

	/**
	 * 10进制转16进制字符串 (Little-Endian)
	 * 
	 * @param i
	 *            int
	 * @param len
	 *            byte长度(不足时前补0)
	 * @return hex
	 */
	public static String littleEndian(int i, int len) {
		return littleEndian(i, len, true);
	}

	/**
	 * 10进制转16进制字符串 (Little-Endian)
	 * 
	 * @param i
	 *            int
	 * @param len
	 *            byte长度(不足时前补0)
	 * @param upper
	 *            是否转为大写
	 * @return hex
	 */
	public static String littleEndian(int i, int len, boolean upper) {
		String hex = Integer.toHexString(i);
		StringBuilder sb = new StringBuilder();
		int n = len > 0 ? (len * 2 - hex.length()) : (hex.length() % 2);
		if (n > 0) {
			for (int j = 0; j < n; j++) {
				sb.append("0");
			}
		}
		hex = invertLowHigh(sb.append(hex).toString());
		if (upper) {
			hex = hex.toUpperCase();
		}
		return hex;
	}

	/**
	 * 10进制转16进制字符串 (Big-Endian)
	 * 
	 * @param i
	 *            int
	 * @return hex
	 */
	public static String bigEndian(int i) {
		return bigEndian(i, 0, true);
	}

	/**
	 * 10进制转16进制字符串 (Big-Endian)
	 * 
	 * @param i
	 *            int
	 * @param len
	 *            byte长度(不足时前补0)
	 * @return hex
	 */
	public static String bigEndian(int i, int len) {
		return bigEndian(i, len, true);
	}

	/**
	 * 10进制转16进制字符串 (Big-Endian)
	 * 
	 * @param i
	 *            int
	 * @param len
	 *            int字符长度(不足时前补0)
	 * @param upper
	 *            是否转为大写
	 * @return hex
	 */
	public static String bigEndian(int i, int len, boolean upper) {
		String hex = Integer.toHexString(i);
		StringBuilder sb = new StringBuilder();
		int n = len > 0 ? (len * 2 - hex.length()) : (hex.length() % 2);
		if (n > 0) {
			for (int j = 0; j < n; j++) {
				sb.append("0");
			}
		}
		hex = sb.append(hex).toString();
		if (upper) {
			hex = hex.toUpperCase();
		}
		return hex;
	}

	/**
	 * 16进制字符串高低位转换
	 * 
	 * @param source
	 *            源
	 * @return 转换后的字符串/null(source为空或者length()非偶数)
	 */
	public static String invertLowHigh(String source) {
		if (null == source) {
			return null;
		}
		int len = source.length();
		if (len == 2) {
			return source;
		}
		if (len % 2 != 0) {
			return null;
		}
		char[] cs = source.toCharArray();
		char[] rs = new char[len];
		int i = len - 1;
		int j = 0;
		while (i >= 0) {
			rs[j++] = cs[i - 1];
			rs[j++] = cs[i];
			i -= 2;
		}
		return new String(rs);
	}

	/**
	 * 16进制字符串二进制数组高低位转换
	 * 
	 * @param source
	 *            源
	 * @return 转换后的二进制数组/null(source为空或者length非偶数)
	 */
	public static byte[] invertLowHigh(byte[] source) {
		int len = source.length;
		if (len % 2 != 0) {
			return null;
		}
		byte[] rs = new byte[len];
		int i = len - 1;
		int j = 0;
		while (i >= 0) {
			rs[j++] = source[i - 1];
			rs[j++] = source[i];
			i -= 2;
		}
		return rs;
	}

	/**
	 * 16进制字符串高低位转换并解析为int
	 * 
	 * @param source
	 *            源
	 * @return int/0
	 */
	public static int invertLowHighThenInt(String source) {
		String s = invertLowHigh(source);
		if (null != s) {
			try {
				return Integer.parseInt(s, 16);
			} catch (NumberFormatException e) {
			}
		}
		return 0;
	}

	/**
	 * byte[]转换成16进制字符串
	 * 
	 * @param bytes
	 *            byte数组
	 * @return 16进制字符串
	 */
	public static String hexStrFromByte(byte[] bytes) {
		if (null == bytes) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 字符串转为16进制字符串
	 * 
	 * @param str
	 *            字符串
	 * @return 16进制字符串
	 */
	public static String hexStrFromStr(String str) {
		return null != str ? hexStrFromByte(str.getBytes()) : null;
	}

	/**
	 * 将16进制字符串转换为byte[]
	 * 
	 * @param hex
	 *            16进制字符串
	 * @return byte数组/null
	 */
	public static byte[] hexStrToByte(String hex) {
		if (null == hex) {
			return null;
		}
		int len = hex.length();
		if (len == 0) {
			return null;
		}
		len = len / 2;
		byte[] bytes = new byte[len];
		for (int i = 0; i < len; i++) {
			int high = Integer.parseInt(hex.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hex.substring(i * 2 + 1, i * 2 + 2), 16);
			bytes[i] = (byte) (high * 16 + low);
		}
		return bytes;
	}

	/**
	 * 将16进制字符串转换为普通字符串
	 * 
	 * @param hexStr
	 *            16进制字符串
	 * @return String/""
	 */
	public static String hexStrToStr(String hex) {
		byte[] bs = hexStrToByte(hex);
		return null != bs ? new String(bs) : "";
	}

}
