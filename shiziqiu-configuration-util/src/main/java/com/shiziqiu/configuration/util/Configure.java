package com.shiziqiu.configuration.util;

import java.util.Properties;

/**
 * @title : Configure
 * @author : crazy
 * @date : 2017年9月6日 下午4:09:30
 * @Fun : 常用配置
 */
public class Configure {

	public static final String CONF_DATA_PATH = "/shziiqiu-conf";
	
	private static final String ZK_LINUX_ADDRESS_FILE = "/home/appConfig/shiziqiu-conf.properties";
	
	private static final String ZK_WINDOWS_ADDRESS_FILE = "c:/shiziqiu-conf.properties";
	
	/**
	 * zk地址
	 * 例：zk地址：格式	ip1:port,ip2:port,ip3:port
	 */
	public static final String ZK_ADDRESS;
	
	static {
		/**
		 * loadFileProperties 根据路径加载文件
		 * loadProperties 当前路径下加载文件
		 */
		Properties props = System.getProperties(); //获得系统属性集
		String osName = props.getProperty("os.name"); //操作系统名称
		Properties prop = null;
		try {
			if(StringUtils.isNotBlank(osName)) {
				if((osName.substring(0, 7)).equals("Windows")) {
					prop = PropertiesUtil.loadFileProperties(ZK_WINDOWS_ADDRESS_FILE);
				} else {
					prop = PropertiesUtil.loadFileProperties(ZK_LINUX_ADDRESS_FILE);
				}
			}
		} catch (Exception e) {
			prop = PropertiesUtil.loadFileProperties(ZK_LINUX_ADDRESS_FILE);
		}
		
		ZK_ADDRESS = PropertiesUtil.getString(prop, "zkserver");
	}
	
	public static void main(String[] args) {
		System.out.println(ZK_ADDRESS);
		
	}
	
}
