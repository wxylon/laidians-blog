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
	
	
	public static void  main(String args[]){
		CallExeUitls callExeUitls = new CallExeUitls();
		callExeUitls.setBatFilePath("D:\\csv2sqlserver\\dist\\");
		callExeUitls.openCsv2SQLserverExe("D:\\csv2sqlserver\\dist\\main.exe", "testAuto", "ftp download",  "44");
	}
	
	@Test
	public void testOpenCsv2SQLserverExe(){
		CallExeUitls callExeUitls = new CallExeUitls();
		callExeUitls.setBatFilePath("D:\\csv2sqlserver\\dist\\main.exe");
		callExeUitls.openCsv2SQLserverExe(" \"testAuto\" \"ftp download\" \"74\"");
	}
	
}

