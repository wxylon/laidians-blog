/**
* Copyright(c) 2004-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.laidians.utils;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author wangx
 * @date 2012-8-13
 */
public class DateUtils {
	
	/**
	 * 获取当前系统时间的季度
	 * @return		季度数
	 * @author wangx
	 * @date 2012-8-13
	 */
	public static int getCurrentSeason(){
		int month = Calendar.getInstance().get(Calendar.MONTH);
		int season = 1;
		if(month >= 0 && month < 3){
			season = 1;
		}else if(month >= 3 && month < 6){
			season = 2;
		}else if(month >= 6 && month < 9){
			season = 3;
		}else if(month >= 9 && month < 12){
			season = 4;
		}
		return season;
	}
	
	/**
	 * 获取当前系统时间的年份
	 * @return		年数
	 * @author wangx
	 * @date 2012-8-13
	 */
	public static int getCurrentYear(){
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	/**
	 * 返回当前系统时间组成的字符串
	 * eg.20120921172950
	 * @return
	 * @author wangx
	 * @date 2012-9-21
	 */
	public static String getCurrentTime(){
		StringBuilder builder = new StringBuilder();
		Calendar calendar = Calendar.getInstance();
		builder.append(calendar.get(Calendar.YEAR));
		int month = calendar.get(Calendar.MONTH) + 1;
		if(month >= 10){
			builder.append(month);
		}else{
			builder.append("0").append(month);
		}
		builder.append(calendar.get(Calendar.DAY_OF_MONTH));
		builder.append(calendar.get(Calendar.HOUR_OF_DAY));
		builder.append(calendar.get(Calendar.MINUTE));
		builder.append(calendar.get(Calendar.SECOND));
		return builder.toString();
	}
	
	
	/**
	 * 根据指定年份和季度，返回该年份该季度的最后一天日期
	 * @param year		年份
	 * @param season	年份
	 * @return			返回该年份该季度的最后一天日期
	 * @author wangx
	 * @date 2012-9-24
	 */
	public static Date getLastDayOfSeason(int year, int season){
		if(season > 4 || season <= 0){
			throw new IllegalArgumentException("season param not support : " + season);
		}
		
		int tempMonth=3;
		switch (season) {
		case 1:
			tempMonth = 3;
			break;
		case 2:
			tempMonth = 6;
			break;
		case 3:
			tempMonth = 9;
			break;
		case 4:
			year += 1;
			tempMonth = 0;
			break;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, tempMonth);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 根据指定年份和季度，返回该年份该季度的最后一天日期
	 * @param year		年份
	 * @param season	年份
	 * @return			返回该年份该季度的最后一天日期
	 * @author wangx
	 * @date 2012-9-24
	 */
	public static Date getLastDayOfSeason(String year, String season){
		
		int tempYear, tempSeason;
		if(StringUtils.isEmpty(year)){
			tempYear = getCurrentYear();
		}else{
			tempYear = Integer.valueOf(year);
		}
		
		if(StringUtils.isEmpty(season)){
			tempSeason = getCurrentSeason();
		}else{
			tempSeason = Integer.valueOf(season);
		}
		return getLastDayOfSeason(tempYear, tempSeason);
		
	}
	
	/**
	 * 根据指定年份和季度，返回该年份该季度的第一天日期
	 * @param year		年份
	 * @param season	年份
	 * @return			返回该年份该季度的第一天日期
	 * @author wangx
	 * @date 2012-9-24
	 */
	public static Date getFirstDayOfSeason(String year, String season){
		
		int tempYear, tempSeason;
		if(StringUtils.isEmpty(year)){
			tempYear = getCurrentYear();
		}else{
			tempYear = Integer.valueOf(year);
		}
		
		if(StringUtils.isEmpty(season)){
			tempSeason = getCurrentSeason();
		}else{
			tempSeason = Integer.valueOf(season);
		}
		
		return getFirstDayOfSeason(tempYear, tempSeason);
	}
	
	/**
	 * 根据指定年份和季度，返回该年份该季度的第一天日期
	 * @param year		年份
	 * @param season	年份
	 * @return			返回该年份该季度的第一天日期
	 * @author wangx
	 * @date 2012-9-24
	 */
	public static Date getFirstDayOfSeason(int year, int season){
		if(season > 4 || season <= 0){
			throw new IllegalArgumentException("season param not support : " + season);
		}
		
		int tempMonth=3;
		switch (season) {
		case 1:
			tempMonth = 1;
			break;
		case 2:
			tempMonth = 4;
			break;
		case 3:
			tempMonth = 7;
			break;
		case 4:
			tempMonth = 10;
			break;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, tempMonth - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
}

