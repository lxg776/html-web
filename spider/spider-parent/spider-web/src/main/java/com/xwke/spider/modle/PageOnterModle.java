package com.xwke.spider.modle;

import java.util.List;

/**
 * 分页实体
 */
public class PageOnterModle {

	private int pageNum;// 当前页数
	private int pages;// 总页数
	private long total;// 总记录数
	private List dataList;
	private boolean dataFla=false;

	public boolean isDataFla() {
		return dataFla;
	}

	public void setDataFla(boolean dataFla) {
		this.dataFla = dataFla;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}



}
