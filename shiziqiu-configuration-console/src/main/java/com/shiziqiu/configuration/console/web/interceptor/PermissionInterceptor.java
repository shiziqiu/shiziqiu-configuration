package com.shiziqiu.configuration.console.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.shiziqiu.configuration.console.web.annotation.Permession;
import com.shiziqiu.configuration.util.Constant;
import com.shiziqiu.configuration.util.CookieUtil;

/**
 * @title : PermissionInterceptor
 * @author : crazy
 * @date : 2017年9月6日 下午8:32:07
 * @Fun :
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter{

	public static boolean login(HttpServletResponse response, boolean ifRemember){
		CookieUtil.set(response, Constant.LOGIN_PERM_KEY, Constant.LOGIN_PERM_VAL, ifRemember);
		return true;
	}
	
	public static void logout(HttpServletRequest request, HttpServletResponse response){
		CookieUtil.remove(request, response, Constant.LOGIN_PERM_KEY);
	}
	
	public static boolean isLogin(HttpServletRequest request){
		String userInfo = CookieUtil.getValue(request, Constant.LOGIN_PERM_KEY);
		if (null == userInfo || !Constant.LOGIN_PERM_VAL.equals(userInfo.trim())) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		if (!(handler instanceof HandlerMethod)) {
			return super.preHandle(request, response, handler);
		}
		
		if (!isLogin(request)) {
			HandlerMethod method = (HandlerMethod)handler;
			Permession permission = method.getMethodAnnotation(Permession.class);
			if (null == permission || permission.permession()) {
				throw new Exception("登陆失效");
			}
		}
		
		return super.preHandle(request, response, handler);
	}
	
}
