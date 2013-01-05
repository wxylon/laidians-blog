package com.laidians.core.page;

import java.util.List;

/**
 * 一个分页列表接口，继承List接口
 * @param <T> 分页列表元素类型
 */
public interface PagedList<T> extends List<T> {
	/**
	 * @return 是否需要计算条目总数。for example, top10 do not require count.
	 */
	boolean isCountRequired();

	/**
	 * @return 条目总数
	 */
	int getItemCount();

	/**
	 * @return 每页条目数量
	 */
	int getPageSize();

	/**
	 * @return 当前页码
	 */
	int getPage();

	/**
	 * @return 页码总数
	 */
	int getPageCount();

	/**
	 * @return 条目开始位置
	 */
	int getStartIndex();

	/**
	 * @return 条目结束位置
	 */
	int getEndIndex();

}
