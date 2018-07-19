package com.show.comm.utils.web;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.show.comm.utils.StringUtils;

/**
 * Web辅助工具类
 * 
 * @author think
 * @date 2017年7月28日
 */
public class WebUtils {

	private WebUtils() {
	}

	/**
	 * 是否ajax请求
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true:ajax请求
	 */
	public static boolean isAjax(HttpServletRequest request) {
		return (request.getHeader("accept").indexOf("application/json") != -1
				|| (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") != -1));
	}

	/**
	 * response输出内容
	 * 
	 * <pre>
	 * text/plain;charset=UTF-8
	 * </pre>
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param content
	 *            内容 (text/plain;charset=UTF-8)
	 * @return true:成功,false:异常
	 */
	public static boolean write(HttpServletResponse response, String content) {
		return write(response, content, "text/plain;charset=UTF-8");
	}

	/**
	 * response输出js
	 * 
	 * <pre>
	 * application/javascript;charset=UTF-8
	 * </pre>
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param content
	 *            js内容
	 * @return true:成功,false:异常
	 */
	public static boolean writeJs(HttpServletResponse response, String content) {
		return write(response, content, "application/javascript;charset=UTF-8");
	}

	/**
	 * response输出json
	 * 
	 * <pre>
	 * application/javascript;charset=UTF-8
	 * </pre>
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param content
	 *            json内容
	 * @return true:成功,false:异常
	 */
	public static boolean writeJson(HttpServletResponse response, String content) {
		return write(response, content, "application/json;charset=UTF-8");
	}

	/**
	 * response输出内容
	 * 
	 * <pre>
	 * text/plain;charset=UTF-8
	 * </pre>
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param content
	 *            内容
	 * @param contentType
	 *            内容类型
	 * @return true:成功,false:异常
	 */
	public static boolean write(HttpServletResponse response, String content, String contentType) {
		try {
			response.setStatus(200);// 指定状态为200
			response.setContentType(contentType);
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			writer.write(content);
			writer.flush();
			writer.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取ip
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return 返回客户端IP
	 */
	public static String ip(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 写cookie(7天)
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 */
	public static void cookieAdd(HttpServletResponse response, String name, String value) {
		cookieAdd(response, name, value, 7 * 24 * 3600);
	}

	/**
	 * 写cookie
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @param maxAge
	 *            最大有效时长(秒)
	 */
	public static void cookieAdd(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * 读取cookie
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param name
	 *            名称
	 * @return 值/null
	 */
	public static String cookieGet(HttpServletRequest request, String name) {
		Cookie[] cs = request.getCookies();
		if (null != cs && cs.length > 0) {
			for (Cookie c : cs) {
				if (name.equals(c.getName())) {
					return StringUtils.trim(c.getValue());
				}
			}
		}
		return null;
	}

	/**
	 * 删除cookie
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param names
	 *            名称数组
	 */
	public static void cookieRmv(HttpServletResponse response, String... names) {
		for (String name : names) {
			Cookie cookie = new Cookie(name, "");
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}

}
