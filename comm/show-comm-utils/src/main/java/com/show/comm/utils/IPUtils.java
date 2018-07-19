package com.show.comm.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.Inet4Address;
import java.net.InetAddress;

/**
 * IP地址工具类
 * 
 * @author heyuhua
 * @date 2017年6月13日
 *
 */
public class IPUtils {

	private IPUtils() {
	}

	/**
	 * 获取当前IP
	 * 
	 * @return 当前服务器IP地址
	 */
	public static String localIp() {
		return localIp(false);
	}

	/**
	 * 获取当前IP
	 * 
	 * @param nullThenLocalhost
	 *            获取到的IP为空时，是否返回127.0.0.1
	 * @return 当前服务器IP地址
	 */
	public static String localIp(boolean nullThenLocalhost) {
		String ip = null;
		try {
			ip = getLocalAddress();
			if (null == ip || !wwwIp(ip)) {
				String ip2 = getLocalIp();
				if (null != ip2) {
					ip = ip2;
				}
			}
		} catch (Exception e) {
		}
		if (null == ip && nullThenLocalhost) {
			ip = "127.0.0.1";
		}
		return ip;
	}

	/**
	 * 获取IP地址(优先外网IP,不会返回127.0.0.1)
	 * 
	 * @return ip地址
	 * @throws Exception
	 */
	public static String getLocalAddress() throws Exception {
		String name = InetAddress.getLocalHost().getHostName();
		String ip = null;
		int level = 0;
		for (InetAddress ad : InetAddress.getAllByName(name)) {
			if (ad instanceof Inet4Address) {
				if (ip == null) {
					ip = ad.getHostAddress();
					level = getIpLevel(ip);
				} else {
					String p = ad.getHostAddress();
					int l = getIpLevel(p);
					if (l > level) {
						ip = p;
						level = l;
					}
				}
			}
		}
		return ip;
	}

	/**
	 * 分区内外网
	 * 
	 * @param ip
	 * @return
	 */
	private static int getIpLevel(String ip) {
		if (ip.startsWith("192.")) {
			return 1;
		} else if (ip.startsWith("10.")) {
			return 0;
		} else if (ip.startsWith("127.")) {
			return -1;
		}
		return 2;
	}

	/**
	 * 检查ip是否外网IP
	 * 
	 * @param ip
	 * @return
	 */
	private static boolean wwwIp(String ip) {
		if (!ip.startsWith("127.") && !ip.startsWith("10.") && !ip.startsWith("192.")) {
			return true;
		}
		return false;
	}

	/**
	 * 通过命令获取IP(适用于linux等)
	 * 
	 * @return
	 */
	public static String getLocalIp() {
		Process process = null;
		InputStreamReader ir = null;
		LineNumberReader lnr = null;
		try {
			process = Runtime.getRuntime().exec("ifconfig");
			ir = new InputStreamReader(process.getInputStream());
			lnr = new LineNumberReader(ir);
			String s;
			while ((s = lnr.readLine()) != null) {
				int inx = s.indexOf("inet addr:");
				if (inx >= 0) {
					s = s.substring(inx + "inet addr:".length());
					s = s.substring(0, s.indexOf(" ")).trim();
					if (wwwIp(s)) {
						return s;
					}
				}
			}
		} catch (Exception e) {
			if (null != lnr) {
				try {
					lnr.close();
				} catch (IOException e1) {
				}
			}
			if (null != ir) {
				try {
					ir.close();
				} catch (IOException e1) {
				}
			}
			if (null != process) {
				process.destroy();
			}
		}
		return null;
	}
}
