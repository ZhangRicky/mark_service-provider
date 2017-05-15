package com.base;

import java.util.List;

/**
 * 分页封装
 * @author Mark
 *
 * @param <M>
 */
public class PaginationSupport<M> {
	
	public final static int PAGESIZE = 15;

	private int pageSize = PAGESIZE;

	private List<M> items;	//数据
	
	private int totalCount;	//总条数

	private int[] indexes = new int[0];

	private int startIndex = 0;	//开始标

	public PaginationSupport(List<M> items, int totalCount) {
		setPageSize(PAGESIZE);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(0);
	}

	public PaginationSupport(List<M> items, int totalCount, int startIndex) {
		setPageSize(PAGESIZE);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(startIndex);
	}

	public PaginationSupport(List<M> items, int totalCount, int pageSize,
			int startIndex) {
		setPageSize(pageSize);
		setTotalCount(totalCount);
		setItems(items);
		setStartIndex(startIndex);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<M> getItems() {
		return items;
	}

	public void setItems(List<M> items) {
		this.items = items;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int[] getIndexes() {
		return indexes;
	}

	public void setIndexes(int[] indexes) {
		this.indexes = indexes;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	
}
