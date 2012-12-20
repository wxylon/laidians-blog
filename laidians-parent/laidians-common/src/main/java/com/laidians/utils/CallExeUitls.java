/**
 * Copyright(c) 2004-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.laidians.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

/**
 * @author wangx
 * @date 2012-8-29
 */
public class CallExeUitls {
	private static Logger log = Logger.getLogger(CallExeUitls.class);
	private String batFilePath;
	
	// 2.0调用其他的可执行文件，例如：自己制作的exe，或是下载安装的软件
	public boolean openCsv2SQLserverExe(String ... params){
		boolean ifSuccess = false;
		BufferedReader stdlog = null;
		BufferedReader stdError = null;
		try {
			String directory  = batFilePath.endsWith(".exe") ? batFilePath.substring(0, batFilePath.lastIndexOf("\\")+1) : batFilePath;
			log.info("Python's exe commands: " + toString(params));
			log.info("Python's exe directory: " + directory);
			ProcessBuilder builder = new ProcessBuilder(); 
			builder.command(params);
			builder.directory(new File(directory));
			Process pid = builder.start(); 
			stdlog = new BufferedReader(new InputStreamReader(pid.getInputStream()));
			stdError = new BufferedReader(new InputStreamReader(pid.getErrorStream()));
			String line;
			while ((line = stdlog.readLine()) != null) {
				log.info("call python exe log:" + line);
			}
			while ((line = stdError.readLine()) != null) {
				log.error("call python exe error:" + line);
			}
			pid.waitFor();
			ifSuccess = true;
		} catch (InterruptedException e) {
			ifSuccess = false;
			e.printStackTrace();
		} catch (IOException e) {
			ifSuccess = true;
			e.printStackTrace();
		}finally{
			try {
				if(null != stdlog){
					stdlog.close();
				}
				if(null != stdError){
					stdError.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ifSuccess;
	}

	public String getBatFilePath() {
		return batFilePath;
	}

	public void setBatFilePath(String batFilePath) {
		this.batFilePath = batFilePath;
	}
	
	public String toString(String ... params){
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < params.length; i++){
			if(i != 0){
				builder.append(" ");
			}
			builder.append("\""+params[i]+"\"");
		}
		return builder.toString();
	}
}
