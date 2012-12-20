/**
* Copyright(c) 2004-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.laidians.utils;

import java.util.Map;

import org.junit.Test;

/**
 * @author wangx
 * @date 2012-8-29
 */
public class ConfigINIUtilsTests {
	
	@Test
	public void test(){
		String path = ConfigINIUtilsTests.class.getResource("config.ini").getFile();
		ConfigINIUtils configUtils = ConfigINIUtils.getInstance(path);
		//执行完，检查classpath下的配置文件；
		configUtils.setPropValue("sql_ftp", "table_name", "fdsfdsfds");
		
		Map<String, Map<String, String>> propMap = configUtils.getPropMap();
		for(Map.Entry<String, Map<String, String>> map : propMap.entrySet()){
			for(Map.Entry<String, String> entry : map.getValue().entrySet()){
				System.out.println(map.getKey() + "--->" + entry.getKey() + "--->" + entry.getValue());
			}
		}
	}
}

