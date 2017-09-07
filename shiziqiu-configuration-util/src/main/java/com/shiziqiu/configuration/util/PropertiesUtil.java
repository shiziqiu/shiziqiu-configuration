package com.shiziqiu.configuration.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @title : PropertiesUtil
 * @author : crazy
 * @date : 2017年9月6日 下午4:12:54
 * @Fun : 处理工具类
 */
public class PropertiesUtil {

	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	
	/**
	 * 根据文件名加载文件
	 * @param fileName
	 * @return
	 * 
	 */
	public static Properties loadProperties(String fileName) {
		Properties prop = new Properties();
		InputStream in = null;
		try {
			ClassLoader loder = Thread.currentThread().getContextClassLoader();
			URL url = loder.getResource(fileName); // 方式1：配置更新不需要重启JVM
			if(null != url) {
				in = new FileInputStream(url.getPath());
				if(null != in) {
					prop.load(in);
				}
			}
		} catch (Exception e) {
			logger.error("load {} error!", fileName);
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("close {} error!", fileName);	
				}
			}
		}
		return prop;
	}
	
	/**
	 * @param fileName
	 * @return
	 */
	public static Properties loadFileProperties(String fileName) {
		Properties prop = new Properties();
		InputStream in = null;
		try {
			ClassLoader loder = Thread.currentThread().getContextClassLoader();
			URL url = url = new File(fileName).toURI().toURL();
			in = new FileInputStream(url.getPath());
			if (in != null) {
				prop.load(in);
			}
		} catch (Exception e) {
			logger.error("load {} error!", fileName);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("close {} error!", fileName);
				}
			}
		}
		return prop;
	}
	
	
	/**
	 * @param key
	 * @return
	 */
	public static String getString(Properties prop, String key) {
		return prop.getProperty(key);
	}

	/**
	 * @param key
	 * @return
	 */
	public static int getInt(Properties prop, String key) {
		return Integer.parseInt(getString(prop, key));
	}

	/**
	 * @param prop
	 * @param key
     * @return
     */
	public static boolean getBoolean(Properties prop, String key) {
		return Boolean.valueOf(getString(prop, key));
	}

	public static void main(String[] args) {
		Properties prop = loadProperties("shiziqiu-conf.properties");
		System.out.println(prop);
		logger.info("==========");
	}

}
