package com.itheima.domain;

import java.util.List;

//PageBean表示一个分页的实体，它不仅包括分页的消息，还包括一个商品的集合
//这里商品的类型用樊星表示
public class PageBean<T> {

	private int currentPage;
	private int currentCount;
	private int totalCount;
	private int totalPage;
	private List<T> list;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	
	
}
