package com.show.comm.utils.ext;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 二维码编码生成工具类
 * 
 * @author heyuhua
 * @date 2017年9月26日
 */
public class QrcodeUtil {

	/**
	 * 门禁卡号二维码生成规则key(Ins_Ghost*322)的ascii码,生成规则{@link QrcodeUtil#toAscii(String)}
	 */
	private static final String QR_MJ_ASCII_KEY = "496E735F47686F73742A333232";

	/**
	 * 卡号转二维码
	 * 
	 * @param card
	 *            卡号
	 * @return 二维码字符串
	 */
	public static String cardToQrcode(String card) {
		String s1 = invertLowHigh(cardToHexStr(card).toUpperCase());
		byte[] bs = hexStrToByte(s1 + QR_MJ_ASCII_KEY);
		String md5 = byteToHexStr(DigestUtils.md5(bs));
		return card + "_" + md5;
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
	 * 10进制卡号转为16进制字符串
	 * 
	 * @see #cardForHexStr(String)
	 * @param card
	 *            卡号(8位卡号)
	 * @return hex 3byte
	 */
	public static String cardToHexStr(String card) {
		String s = card;
		int len = card.length();
		if (len < 8) {
			s = ("00000000" + s).substring(len);
		}
		String h1 = Integer.toHexString(Integer.parseInt(s.substring(0, 3)));// 前3位->int->hex
		String h2 = Integer.toHexString(Integer.parseInt(s.substring(3)));// 后5位 ->int->hex
		StringBuilder sb = new StringBuilder();
		if (h1.length() == 1) {
			sb.append("0");
		}
		sb.append(h1);
		int h2l = h2.length();
		if (h2l < 4) {
			if (h2l == 3) {
				sb.append("0");
			} else if (h2l == 2) {
				sb.append("00");
			} else if (h2l == 1) {
				sb.append("000");
			} else {
				sb.append("0000");
			}
		}
		sb.append(h2);
		return sb.toString();
	}

	/**
	 * 字符串转ascii
	 * 
	 * @param str
	 *            字符串
	 * @return ascii字符串
	 */
	public static String toAscii(String str) {
		StringBuilder sb = new StringBuilder();
		for (char c : str.toCharArray()) {
			String s = Integer.toHexString((int) c);
			if (s.length() == 1) {
				sb.append("0");
			}
			sb.append(s);
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 将16进制转换为byte[]
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
	 * byte[]转换成16进制
	 * 
	 * @param bytes
	 *            byte数组
	 * @return 16进制字符串
	 */
	public static String byteToHexStr(byte[] bytes) {
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

}
