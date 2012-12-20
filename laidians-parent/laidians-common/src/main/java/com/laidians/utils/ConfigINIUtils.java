/**
 * Copyright(c) 2004-2012, wxylon@gmail.com  All Rights Reserved
 */

package com.laidians.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用java读取配置文件 
 * 参考：http://jackchen.ddjava.com/blog/blog.html?blogId=373
 * @author wangx
 * @date 2012-8-29
 */
public class ConfigINIUtils {
	private Map<String, Map<String, String>> propMap = null;
	private static ConfigINIUtils _instance = null;
	private String path = "D:\\csv2sqlserver\\config.ini";
	private String encoding = "";
	
	public static ConfigINIUtils getInstance(String path){
		if(null == _instance){
			_instance = new ConfigINIUtils(path);
		}
		return _instance;
	}
	
	private ConfigINIUtils(String path){
		if(null != path && path.length() != 0){
			this.path = path;
		}
		init(path);
	}

	/** 初始化 */
	private void init(String path) {
		propMap = new HashMap<String, Map<String, String>>();
		BufferedReader br = null;
		try {
			encoding = new FileCharsetDetector().guestFileEncoding(path);
			br = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding));
			String line = "";
			String block = "";
			Map<String, String> current = null;
			while ((line = br.readLine()) != null) {
				if(!line.trim().startsWith("#") && line.trim().startsWith("[") && line.trim().endsWith("]")){
					block = line.trim();
					current = propMap.get(block);
					if(null == current){
						current = new HashMap<String, String>();
						propMap.put(block, current);
					}
				} 
				if (line.indexOf("=") != -1 && !line.trim().startsWith("#") && !line.trim().startsWith("//")) {
					String[] lineArr = line.split("=");
					if(lineArr.length == 1){
						current.put(lineArr[0].trim(), "");
					}else{
						current.put(lineArr[0].trim(), lineArr[1].trim());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取所有键值对组成的Map
	 * @param path  配置文件所在路径
	 * @return
	 */
	public Map<String, Map<String, String>> getPropMap() {
		return propMap;
	}
	
	private String getPropKeyWapper(String block){
		if(null != block && block.trim().length() != 0){
			String temp_block = block.trim();
			if(!temp_block.startsWith("[")){
				temp_block = "[" + temp_block;
			}
			if(!temp_block.endsWith("]")){
				temp_block =  temp_block + "]";
			}
			return temp_block;
		}
		return "";
	}

	/**
	 * 获取属性值
	 * @param path	 配置文件所在路径
	 * @param key   属性名
	 * @return
	 */
	public String getPropValue(String block, String key) {
		block = getPropKeyWapper(block);
		if(null == propMap.get(block)){
			return null;
		}
		return propMap.get(block).get(key);
	}

	/**
	 * 设置某个属性的值
	 * @param path
	 * @param key
	 * @param value
	 */
	public void setPropValue(String block, String key, String value) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(key, value);
		setPropValues(block, map);
	}

	/**
	 * 根据Map设置属性
	 * @param path
	 * @param map
	 */
	public void setPropValues(String block, Map<String, String> map) {
		block = getPropKeyWapper(block);
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding));
			StringBuffer sb = new StringBuffer();
			String line = "";
			// 读取文件并对替换文件内容
			boolean ifBlock = false;
			while ((line = br.readLine()) != null) {
				if(!line.trim().startsWith("#") && line.trim().startsWith("[") && line.trim().endsWith("]")){
					String block_temp = line.trim();
					if(block.equals(block_temp)){
						ifBlock = true;
					}else{
						ifBlock = false;
					}
				} 
				
				if (ifBlock && line.indexOf("=") != -1 && !line.trim().startsWith("#") && !line.trim().startsWith("//")) {
					String[] lineArr = line.split("=");
					String key = lineArr[0].trim();
					String newValue = map.get(key);
					if (null != newValue) {
						sb.append(key).append(" = ").append(newValue).append("\r\n");
						propMap.get(block).put(key, newValue);
					} else {
						sb.append(line).append("\r\n");
					}
				} else {
					sb.append(line).append("\r\n");
				}
			}
			br.close();
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), encoding));
			// 写入文件
			bw.write(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
