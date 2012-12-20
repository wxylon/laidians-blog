/**
* Copyright(c) 2004-2012, wxylon@gmail.com  All Rights Reserved
*/

package com.laidians.utils;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author wangx
 * @date 2012-9-21
 */
public class DateUtilsTests {
	
	@Test
	public void testgetFirstDayOfSeasonByStringVlues(){
		Assert.assertEquals("2012-1-1 0:00:00", DateUtils.getFirstDayOfSeason("2012", "1").toLocaleString());
		Assert.assertEquals("2012-4-1 0:00:00", DateUtils.getFirstDayOfSeason("2012", "2").toLocaleString());
		Assert.assertEquals("2012-7-1 0:00:00", DateUtils.getFirstDayOfSeason("2012", "3").toLocaleString());
		Assert.assertEquals("2012-10-1 0:00:00", DateUtils.getFirstDayOfSeason("2012", "4").toLocaleString());
	}
	
	@Test
	public void testgetLastDayOfSeasonByStringVlues(){
		Assert.assertEquals("2012-4-1 0:00:00", DateUtils.getLastDayOfSeason("2012", "1").toLocaleString());
		Assert.assertEquals("2012-7-1 0:00:00", DateUtils.getLastDayOfSeason("2012", "2").toLocaleString());
		Assert.assertEquals("2012-10-1 0:00:00", DateUtils.getLastDayOfSeason("2012", "3").toLocaleString());
		Assert.assertEquals("2013-1-1 0:00:00", DateUtils.getLastDayOfSeason("2012", "4").toLocaleString());
	}
	
	@Test
	public void testgetFirstDayOfSeasonByIntVlues(){
		Assert.assertEquals("2012-1-1 0:00:00", DateUtils.getFirstDayOfSeason(2012, 1).toLocaleString());
		Assert.assertEquals("2012-4-1 0:00:00", DateUtils.getFirstDayOfSeason(2012, 2).toLocaleString());
		Assert.assertEquals("2012-7-1 0:00:00", DateUtils.getFirstDayOfSeason(2012, 3).toLocaleString());
		Assert.assertEquals("2012-10-1 0:00:00", DateUtils.getFirstDayOfSeason(2012, 4).toLocaleString());
	}
	
	@Test
	public void testgetLastDayOfSeasonByIntVlues(){
		Assert.assertEquals("2012-4-1 0:00:00", DateUtils.getLastDayOfSeason(2012, 1).toLocaleString());
		Assert.assertEquals("2012-7-1 0:00:00", DateUtils.getLastDayOfSeason(2012, 2).toLocaleString());
		Assert.assertEquals("2012-10-1 0:00:00", DateUtils.getLastDayOfSeason(2012, 3).toLocaleString());
		Assert.assertEquals("2013-1-1 0:00:00", DateUtils.getLastDayOfSeason(2012, 4).toLocaleString());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testgetFirstAndLastDayOfSeasonByIntVlues(){
		DateUtils.getLastDayOfSeason("2012", "5").toLocaleString();
		DateUtils.getLastDayOfSeason("2012", "5").toLocaleString();
		DateUtils.getFirstDayOfSeason(2012, 5).toLocaleString();
		DateUtils.getLastDayOfSeason(2012, 5).toLocaleString();
	}
	
}

