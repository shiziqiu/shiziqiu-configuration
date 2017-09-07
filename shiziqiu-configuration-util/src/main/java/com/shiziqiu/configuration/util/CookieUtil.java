package com.shiziqiu.configuration.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @title : CookieUtil
 * @author : crazy
 * @date : 2017年9月6日 下午8:33:33
 * @Fun :
 */
public class CookieUtil {

	/**
	 * 保存
	 * 
	 * @param response
	 * @param key
	 * @param value
	 * @param ifRemember
	 * true = age设置-1，不缓存；否则 age设置俩小时；
	 */
	public static void set(HttpServletResponse response, String key,
			String value, boolean ifRemember) {

		int age = Constant.COOKIE_MAX_AGE;
		if (ifRemember) {
			age = Constant.COOKIE_MAX_AGE;
		} else {
			age = -1;
		}

		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(age); // Cookie过期时间,单位/秒
		cookie.setPath(Constant.COOKIE_PATH); // Cookie适用的路径
		response.addCookie(cookie);
	}

	/**
	 * 保存
	 * 
	 * @param response
	 * @param key
	 * @param value
	 * @param maxAge
	 */
	@SuppressWarnings("unused")
	private static void set(HttpServletResponse response, String key, String value, int maxAge, String path) {
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(maxAge); // Cookie过期时间,单位/秒
		cookie.setPath(path); // Cookie适用的路径
		response.addCookie(cookie);
	}

	/**
	 * 查询value
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static String getValue(HttpServletRequest request, String key) {
		Cookie cookie = get(request, key);
		if (cookie != null) {
			return cookie.getValue();
		}
		return null;
	}

	/**
	 * 查询Cookie
	 * 
	 * @param request
	 * @param key
	 */
	private static Cookie get(HttpServletRequest request, String key) {
		Cookie[] arr_cookie = request.getCookies();
		if (arr_cookie != null && arr_cookie.length > 0) {
			for (Cookie cookie : arr_cookie) {
				if (cookie.getName().equals(key)) {
					return cookie;
				}
			}
		}
		return null;
	}

	/**
	 * 删除Cookie
	 * 
	 * @param request
	 * @param response
	 * @param key
	 */
	public static void remove(HttpServletRequest request, HttpServletResponse response, String key) {
		Cookie cookie = get(request, key);
		if (cookie != null) {
			set(response, key, "", 0, Constant.COOKIE_PATH);
		}
	}
}
