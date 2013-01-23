package com.laidians.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 参考：org.apache.catalina.util.StringManager
 * @author wxylon@gmail.com
 * @date 2013-1-23
 */
public class Environment {

	private static Properties properties = new Properties();

	static{
		try {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			InputStream is = cl.getResourceAsStream("environment.properties");
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return getProperty(key, null);
	}
	
	public static String getProperty(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}
	
	public static double getDoubleProperty(String key) {
		String temp = getProperty(key, null);
		if(null == temp || temp.trim().length() == 0){
			return 0.0d;
		}
		return Double.valueOf(temp);
	}
}
