package com.spring.biz.dietList;


public class DietListVO {
	private String ymd;
	private String weekday;
	private String restaurantName;
	private String mealTime;
	private String mealName;
	private String ingredimentName;
	
	//서치컨디션
	private String listCount;
	private int currentPage;
	private String searchStartDate;
	private String searchLastDate;
	
	// getter,setter
	public String getYmd() {
		return ymd;
	}
	public void setYmd(String ymd) {
		this.ymd = ymd;
	}
	public String getWeekday() {
		return weekday;
	}
	public void setWeekday(String weekday) {
		this.weekday = weekday;
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
	public String getMealName() {
		return mealName;
	}
	public void setMealName(String mealName) {
		this.mealName = mealName;
	}
	public String getIngredimentName() {
		return ingredimentName;
	}
	public void setIngredimentName(String ingredimentName) {
		this.ingredimentName = ingredimentName;
	}
	public String getListCount() {
		return listCount;
	}
	public void setListCount(String listCount) {
		this.listCount = listCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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
	@Override
	public String toString() {
		return "DietVO [ymd=" + ymd + ", weekday=" + weekday + ", restaurantName=" + restaurantName + ", mealTime="
				+ mealTime + ", mealName=" + mealName + ", ingredimentName=" + ingredimentName + ", listCount="
				+ listCount + ", currentPage=" + currentPage + ", searchStartDate=" + searchStartDate
				+ ", searchLastDate=" + searchLastDate + "]";
	}
	
	
	
}
