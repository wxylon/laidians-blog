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
	
	// 2.0调用其他的可执行文件，例如：自己制作的exe，或是下载安装的软件
	public boolean openCsv2SQLserverExe(String ... params) throws CallExeException{
		
		if(null == params || params.length == 0){
			throw new IllegalArgumentException("params must not be null");
		}
		String main = params[0];
		String directory  = main.substring(0, main.lastIndexOf("\\")+1);
		
		if (this.log.isInfoEnabled()) {
			this.log.info("CallExe: " + toString(params) + ": execute started");
		}
		
		long startTime = System.currentTimeMillis();
		
		doIt(main, directory, params);
		
		if (this.log.isInfoEnabled()) {
			long elapsedTime = System.currentTimeMillis() - startTime;
			this.log.info("CallExe: execute completed in " + elapsedTime + " ms");
		}
		return true;
	}
	
	private boolean doIt(String batFilePath, String directory, String ... params) throws CallExeException{
		BufferedReader stdlog = null;
		BufferedReader stdError = null;
		StringBuilder messages = new StringBuilder();
		StringBuilder errors = new StringBuilder();
		try {
			ProcessBuilder builder = new ProcessBuilder(); 
			builder.command(params);
			builder.directory(new File(directory));
			Process pid = builder.start(); 
			stdlog = new BufferedReader(new InputStreamReader(pid.getInputStream()));
			stdError = new BufferedReader(new InputStreamReader(pid.getErrorStream()));
			String line;
			int index = 0;
			while ((line = stdlog.readLine()) != null) {
				if(index > 0){
					messages.append("\r\n");
				}
				messages.append(line);
				index++;
			}
			if (messages.length() > 0) {
				this.log.info(messages.toString());
			}
			index = 0;
			while ((line = stdError.readLine()) != null) {
				if(index > 0){
					errors.append("\r\n");
				}
				errors.append(line);
				index++;
			}
			if (errors.length() > 0) {
				CallExeException callExeException = new CallExeException(errors.toString());
				this.log.error(messages.toString(), callExeException);
				throw callExeException;
			}
			pid.waitFor();
			return true;
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
			throw new CallExeException(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new CallExeException(e.getMessage(), e);
		}finally{
			try {
				if(null != stdlog){
					stdlog.close();
				}
				if(null != stdError){
					stdError.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage(), e);
				throw new CallExeException(e.getMessage(), e);
			}
		}
	}
	
	public String toString(String ... params){
		StringBuilder builder = new StringBuilder();
		builder.append("\"");
		for(int i = 0; i < params.length; i++){
			if(i != 0){
				builder.append(" ");
				builder.append("'"+params[i]+"'");
			}else{
				builder.append(params[i]);
			}
		}
		builder.append("\"");
		return builder.toString();
	}
}
