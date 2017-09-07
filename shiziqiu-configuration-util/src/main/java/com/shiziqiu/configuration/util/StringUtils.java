package com.shiziqiu.configuration.util;

/**
 * @title : StringUtils
 * @author : crazy
 * @date : 2017年9月7日 上午9:17:17
 * @Fun :
 */
public class StringUtils {

	public static boolean isNotBlank(String str) {
		return !StringUtils.isBlank(str);
	}

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static String trim(String str) {
		return str == null ? null : str.trim();
	}
}
