/**
* Copyright(c) 2004-2012, 360buy.com  All Rights Reserved
*/

package com.laidians.utils;

import org.junit.Test;

/**
 * @author wangx
 * @date 2012-9-25
 */
public class EnvironmentTests {
	
	@Test
	public void testGetProperty() throws Exception {
		String properties = "environment.properties";
		Environment environment = Environment.getEnvironment(properties);
		System.out.println(environment.getString("k2"));
	}
}

