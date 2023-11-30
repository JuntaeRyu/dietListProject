package com.spring.dietprint.diet;


public class DietPrintVO {
	private String ymd;
	private String weekday;
	private String restaurantName;
	private String mealTime;
	private String mealName;
	private String ingredimentName;
	private int page;
	private boolean meal;
	
	//서치컨디션
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
	public boolean isMeal() {
		return meal;
	}
	public void setMeal(boolean meal) {
		this.meal = meal;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "DietPrintVO [ymd=" + ymd + ", weekday=" + weekday + ", restaurantName=" + restaurantName + ", mealTime="
				+ mealTime + ", mealName=" + mealName + ", ingredimentName=" + ingredimentName + ", searchStartDate="
				+ searchStartDate + ", searchLastDate=" + searchLastDate + ", getYmd()=" + getYmd() + ", getWeekday()="
				+ getWeekday() + ", getRestaurantName()=" + getRestaurantName() + ", getMealTime()=" + getMealTime()
				+ ", getMealName()=" + getMealName() + ", getIngredimentName()=" + getIngredimentName()
				+ ", getSearchStartDate()=" + getSearchStartDate() + ", getSearchLastDate()=" + getSearchLastDate()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
}
