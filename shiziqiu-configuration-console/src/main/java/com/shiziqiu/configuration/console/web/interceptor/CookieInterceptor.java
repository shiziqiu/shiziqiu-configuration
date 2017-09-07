package com.shiziqiu.configuration.console.web.interceptor;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @title : CookieInterceptor
 * @author : crazy
 * @date : 2017年9月7日 上午9:23:02
 * @Fun :
 */
public class CookieInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		if (modelAndView != null && request.getCookies() != null
				&& request.getCookies().length > 0) {
			HashMap<String, Cookie> cookieMap = new HashMap<String, Cookie>();
			for (Cookie cookie : request.getCookies()) {
				cookieMap.put(cookie.getName(), cookie);
			}
			modelAndView.addObject("cookieMap", cookieMap);
		}

		super.postHandle(request, response, handler, modelAndView);
	}
}
