package com.laidians.core.page;

/**
 * 分页导航条包装类
 * @param <T>
 */
public class PagesBar<T> {
	private final PagedList<T> pagedList;
	private final int width;
	// local cache flag
	private int startPage = -1;
	private int stopPage = -1;

	/**
	 * @param pagedList 分页列表
	 * @param width 页面导航条中，页码序列个数
	 */
	public PagesBar(final PagedList<T> pagedList, final int width) {
		this.pagedList = pagedList;
		this.width = width;
	}

	public PagedList<T> getPagedList() {
		return pagedList;
	}

	public int getItemCount() {
		return pagedList.getItemCount();
	}

	public boolean isPreviousEnable() {
		return getStartPage() > 1;
	}

	public boolean isNextEnable() {
		return getStopPage() < pagedList.getPageCount();
	}

	public boolean isCurrent(final int page) {
		return page == (pagedList.getPage());
	}

	public int getLastPage() {
		return pagedList.getPageCount();
	}

	public int[] getPageList() {
		int start = getStartPage();
		int stop = getStopPage();

		int[] arrPages = new int[(stop - start + 1)];
		for (int i = start; i <= stop; i++) {
			arrPages[(i - start)] = i;
		}
		return arrPages;
	}

	public int getStartPage() {
		if (startPage < 0) {
			int current = pagedList.getPage();

			int rightMargin = width / 2;
			int stop = current + rightMargin;
			int lastPage = pagedList.getPageCount();

			int leftAddon = 0;
			if (stop > lastPage) {
				leftAddon = stop - lastPage;
			}
			int leftMargin = (width - 1) / 2 + leftAddon;
			startPage = current - leftMargin;
			if (startPage < 1) {
				startPage = 1;
			}
		}
		return startPage;
	}

	public int getStopPage() {
		if (stopPage < 0) {
			int current = pagedList.getPage();

			int leftMargin = (width - 1) / 2;
			int start = current - leftMargin;
			int firstPage = 1;

			int rightAddon = 0;
			if (start < firstPage) {
				rightAddon = firstPage - start;
			}
			int rightMargin = width / 2 + rightAddon;
			stopPage = current + rightMargin;
			int lastPage = pagedList.getPageCount();
			if (stopPage > lastPage) {
				stopPage = lastPage;
			}
		}
		return stopPage;
	}

}
