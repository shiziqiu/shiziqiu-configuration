package com.shiziqiu.configuration.console.web.controller;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiziqiu.configuration.console.model.ResultT;
import com.shiziqiu.configuration.console.web.annotation.Permession;
import com.shiziqiu.configuration.console.web.interceptor.PermissionInterceptor;
import com.shiziqiu.configuration.util.PropertiesUtil;
import com.shiziqiu.configuration.util.StringUtils;

/**
 * @title : IndexController
 * @author : crazy
 * @date : 2017年9月6日 下午8:24:54
 * @Fun :
 */
@Controller
public class IndexController {

	private static Logger logger = LoggerFactory.getLogger(IndexController.class.getName());

	@RequestMapping("/")
	@Permession(permession = false)
	public String index(Model model, HttpServletRequest request) {
		if (!PermissionInterceptor.isLogin(request)) {
			return "redirect:/toLogin";
		}
		return "redirect:/conf";
	}

	@RequestMapping("/toLogin")
	@Permession(permession = false)
	public String toLogin(Model model, HttpServletRequest request) {
		if (PermissionInterceptor.isLogin(request)) {
			return "redirect:/";
		}
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	@Permession(permession = false)
	public ResultT<String> login(HttpServletRequest request,
			HttpServletResponse response, String userName, String password, String ifRemember) {
		if (!PermissionInterceptor.isLogin(request)) {
			Properties prop = PropertiesUtil.loadProperties("config.properties");
			if (StringUtils.isNotBlank(userName)
					&& StringUtils.isNotBlank(password)
					&& PropertiesUtil.getString(prop, "loginUsername").equals(userName)
					&& PropertiesUtil.getString(prop, "loginPassword").equals(password)) {
				boolean ifRem = false;
				if (StringUtils.isNotBlank(ifRemember)
						&& "on".equals(ifRemember)) {
					ifRem = true;
				}
				PermissionInterceptor.login(response, ifRem);
			} else {
				return new ResultT<String>(500, "账号或密码错误");
			}
		}
		return ResultT.SUCCESS;
	}

	@RequestMapping(value = "loginOut", method = RequestMethod.POST)
	@ResponseBody
	@Permession(permession = false)
	public ResultT<String> loginOut(HttpServletRequest request,
			HttpServletResponse response) {
		if (PermissionInterceptor.isLogin(request)) {
			PermissionInterceptor.logout(request, response);
		}
		return ResultT.SUCCESS;
	}

	@RequestMapping("/help")
	@Permession
	public String help() {
		return "help";
	}

}
