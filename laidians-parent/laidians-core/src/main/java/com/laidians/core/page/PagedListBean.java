package com.laidians.core.page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 分页列表实现
 * <p>
 * 注意：页码索引，从1开始（第一页为1）
 * @param <T>
 */
public class PagedListBean<T> implements PagedList<T> {
	public static final int DEFAULT_PAGESIZE = 30;

	private final int page;
	private final int pageSize;
	private final int itemCount;
	private final int pageCount;
	private final boolean countRequired;
	private final List<T> underly;

	public PagedListBean(int page, int itemCount) {
		this(page, itemCount, DEFAULT_PAGESIZE, true);
	}

	public PagedListBean(int page, int itemCount, int pageSize) {
		this(page, itemCount, pageSize, true);
	}

	public PagedListBean(int page, int itemCount, int pageSize, boolean countRequired) {
		if (page <= 0) {
			page = 1;
		}
		if (pageSize <= 0) {
			pageSize = DEFAULT_PAGESIZE;
		}
		if (itemCount < 0) {
			itemCount = 0;
		}
		this.page = page;
		this.pageSize = pageSize;
		this.itemCount = itemCount;
		this.pageCount = (int) Math.ceil(((double) itemCount / (double) pageSize));
		this.countRequired = countRequired;
		this.underly = new ArrayList<T>(pageSize);
	}

	@Override
	public boolean isCountRequired() {
		return countRequired;
	}

	@Override
	public int getItemCount() {
		return itemCount;
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public int getPage() {
		return page;
	}

	@Override
	public int getPageCount() {
		return pageCount;
	}

	@Override
	public int getStartIndex() {
		return (page - 1) * pageSize + 1;
	}

	@Override
	public int getEndIndex() {
		int end = page * pageSize;
		if (end > itemCount) {
			end = itemCount;
		}
		return end;
	}

	// ///////////////////////////////////
	//
	// 下面是List接口的实现
	// ////////////////////////////////////
	@Override
	public int size() {
		return underly.size();
	}

	@Override
	public boolean isEmpty() {
		return underly.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return underly.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		return underly.iterator();
	}

	@Override
	public Object[] toArray() {
		return underly.toArray();
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] a) {
		return underly.toArray(a);
	}

	@Override
	public boolean add(T e) {
		return underly.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return underly.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return underly.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		return underly.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		return underly.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return underly.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return underly.retainAll(c);
	}

	@Override
	public void clear() {
		underly.clear();
	}

	@Override
	public T get(int index) {
		return underly.get(index);
	}

	@Override
	public T set(int index, T element) {
		return underly.set(index, element);
	}

	@Override
	public void add(int index, T element) {
		underly.add(index, element);
	}

	@Override
	public T remove(int index) {
		return underly.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return underly.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return underly.lastIndexOf(o);
	}

	@Override
	public ListIterator<T> listIterator() {
		return underly.listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return underly.listIterator(index);
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return underly.subList(fromIndex, toIndex);
	}

}
