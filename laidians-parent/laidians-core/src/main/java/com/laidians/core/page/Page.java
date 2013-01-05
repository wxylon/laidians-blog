/*
 * $Id: Page.java,v 1.1 2011/09/06 01:54:34 panly Exp $
 * Copyright(c) 2000-2007 HC360.COM, All Rights Reserved.
 */
package com.laidians.core.page;

import java.util.List;

/**
 * 分页查询结果Bean
 * 
 * @author sunsp
 * @date 2011-8-24
 * @version 1.0
 */
public class Page {

	/** 查询结果 */
	private List<?> lstResult;
	/** 分页信息Bean */
	private PageBean pageBean;
	
	/**
	 * (空)
	 */
	public Page() {}
	
	/**
	 * 根据查询结果、分页信息构造
	 * @param lstResult 查询结果
	 * @param pageBean 分页信息Bean
	 */
	public Page(List<?> lstResult, PageBean pageBean) {
		this.lstResult = lstResult;
		this.pageBean = pageBean;
	}
	
	/**
	 * 取得查询结果
	 * @return 查询结果
	 */
	public List<?> getLstResult() {
		return lstResult;
	}
	/**
	 * 设置查询结果
	 * @param lstResult 查询结果
	 */
	public void setLstResult(List<?> lstResult) {
		this.lstResult = lstResult;
	}
	
	/**
	 * 取得分页信息Bean
	 * @return 分页信息Bean
	 */
	public PageBean getPageBean() {
		return pageBean;
	}
	/**
	 * 设置分页信息Bean
	 * @param pageBean 分页信息Bean
	 */
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
}
