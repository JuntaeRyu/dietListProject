package com.spring.biz.page;


public class PageVO {
	private int totalPage;
	private int currentPage;
	private int listCount;
	private int listFirstPage;
	private int listLastPage;
	private boolean beforePage;
	private boolean nextPage;
	private boolean beforePageList;
	private boolean nextPageList;
	
	// 서치컨디션
	private String searchStartDate;
	private String searchLastDate;
	private String restaurantName;
	private String mealTime;
	
	
	// getter,setter
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public int getListFirstPage() {
		return listFirstPage;
	}
	public void setListFirstPage(int listFirstPage) {
		this.listFirstPage = listFirstPage;
	}
	public int getListLastPage() {
		return listLastPage;
	}
	public void setListLastPage(int listLastPage) {
		this.listLastPage = listLastPage;
	}
	public boolean isBeforePage() {
		return beforePage;
	}
	public void setBeforePage(boolean isBeforPage) {
		this.beforePage = isBeforPage;
	}
	public boolean isNextPage() {
		return nextPage;
	}
	public void setNextPage(boolean isNextPage) {
		this.nextPage = isNextPage;
	}
	public boolean isBeforePageList() {
		return beforePageList;
	}
	public void setBeforePageList(boolean isBeforPageList) {
		this.beforePageList = isBeforPageList;
	}
	public boolean isNextPageList() {
		return nextPageList;
	}
	public void setNextPageList(boolean isNextPageList) {
		this.nextPageList = isNextPageList;
	}
	public String getSearchStartDate() {
		return searchStartDate;
	}
	public void setSearchStartDate(String searchStartDate) {
		this.searchStartDate = searchStartDate;
	}
	public String getSearchLastDate() {
		return searchLastDate;
	}
	public void setSearchLastDate(String searchLastDate) {
		this.searchLastDate = searchLastDate;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getMealTime() {
		return mealTime;
	}
	public void setMealTime(String mealTime) {
		this.mealTime = mealTime;
	}
	@Override
	public String toString() {
		return "PageVO [totalPage=" + totalPage + ", currentPage=" + currentPage + ", listCount=" + listCount
				+ ", listFirstPage=" + listFirstPage + ", listLastPage=" + listLastPage + ", beforePage=" + beforePage
				+ ", nextPage=" + nextPage + ", beforePageList=" + beforePageList + ", nextPageList=" + nextPageList
				+ ", searchStartDate=" + searchStartDate + ", searchLastDate=" + searchLastDate + ", restaurantName="
				+ restaurantName + ", mealTime=" + mealTime + "]";
	}
	
}
