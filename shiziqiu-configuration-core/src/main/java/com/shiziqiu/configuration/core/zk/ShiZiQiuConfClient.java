package com.shiziqiu.configuration.core.zk;

import java.util.Properties;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shiziqiu.configuration.util.Configure;
import com.shiziqiu.configuration.util.PropertiesUtil;

/**
 * @title : ShiZiQiuConfClient
 * @author : crazy
 * @date : 2017年9月6日 下午5:23:16
 * @Fun :
 */
public class ShiZiQiuConfClient {

	private static Logger logger = LoggerFactory.getLogger(ShiZiQiuConfClient.class);
	public static Properties localProp = PropertiesUtil.loadProperties("local.properties");
	private static Cache cache;
	
	static {
		CacheManager manager = CacheManager.create();
		cache = new Cache(Configure.CONF_DATA_PATH, 10000, false, true, 1800, 1800);
		manager.addCache(cache);
	}
	
	public static void set(String key, String value) {
		if (cache != null) {
			logger.info(">>>>>>>>>> shiziqiu-conf: 初始化配置: [{}:{}]", new Object[]{key, value});
			cache.put(new Element(key, value));
		}
	}
	
	public static void update(String key, String value) {
		if (cache != null) {
			if (cache.get(key)!=null) {
				logger.info(">>>>>>>>>> shiziqiu-conf: 更新配置: [{}:{}]", new Object[]{key, value});
				cache.put(new Element(key, value));
			}
		}
	}
	
	public static String get(String key, String defaultVal) {
		if (localProp!=null && localProp.containsKey(key)) {
			return localProp.getProperty(key);
		}
		if (cache != null) {
			Element element = cache.get(key);
			if (element != null) {
				return (String) element.getObjectValue();
			}
		}
		String zkData = ShiZiQiuZkConfClient.getPathDataByKey(key);
		if (zkData!=null) {
			set(key, zkData);
			return zkData;
		}

		return defaultVal;
	}

	public static boolean remove(String key) {
		if (cache != null) {
			logger.info(">>>>>>>>>> shiziqiu-conf: 删除配置：key ", key);
			return cache.remove(key);
		}
		return false;
	}

	public static void main(String[] args) {
		String key = "key";
		String value = "hello";
		set(key, value);
		System.out.println(get(key, "-1"));
	}
	
	
}
