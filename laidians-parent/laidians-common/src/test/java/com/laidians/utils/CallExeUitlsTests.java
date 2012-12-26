/**
* Copyright(c) 2004-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.laidians.utils;

import org.junit.Test;

/**
 * @author wangx
 * @date 2012-9-13
 */
public class CallExeUitlsTests {
	
//	@Test
	public void testOpenCsv2SQLserverExeNullParams() throws CallExeException{
		CallExeUitls callExeUitls = new CallExeUitls();
		callExeUitls.openCsv2SQLserverExe();
	}
	
	@Test
	public void testOpenCsv2SQLserverExe() throws CallExeException{
		CallExeUitls callExeUitls = new CallExeUitls();
		callExeUitls.openCsv2SQLserverExe("D:\\csv2sqlserver\\dist\\main.exe", "testAuto", "ftp download",  "44");
	}
	
//	@Test
	public void testExe() throws CallExeException{
		CallExeUitls callExeUitls = new CallExeUitls();
		callExeUitls.openCsv2SQLserverExe("D:\\csv2sqlserver\\dist\\i++and++i.exe");
	}
	
}

