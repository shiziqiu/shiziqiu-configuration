package com.shiziqiu.configuration.util;

/**
 * @title : Constant
 * @author : crazy
 * @date : 2017年9月6日 下午5:13:48
 * @Fun : 常用的常量
 */
public class Constant {

	public static final String PLACEHOLDER_PREFIX = "${";
	public static final String PLACEHOLDER_SUFFIX = "}";
	public static final String LOGIN_PERM_KEY = "LOGIN_PERM";
	public static final String LOGIN_PERM_VAL = "1234567890";

	// 默认缓存时间,单位/秒, 2H
	public static final int COOKIE_MAX_AGE = 60 * 60 * 2;

	// 保存路径,根路径
	public static final String COOKIE_PATH = "/";
}
