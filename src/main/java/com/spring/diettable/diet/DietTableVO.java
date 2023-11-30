package com.spring.diettable.diet;

import java.util.List;

public class DietTableVO {

	private String ymd;
	private String weekday;
	private String restaurantName;
	private String mealTime;
	private String mealName;
	private String ingredimentNames;
	private List<String> ingredimentName;
	
	//서치컨디션
	private String searchStartDate;
	private String searchLastDate;
	
	
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
	public String getIngredimentNames() {
		return ingredimentNames;
	}
	public void setIngredimentNames(String ingredimentNames) {
		this.ingredimentNames = ingredimentNames;
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
	public List<String> getIngredimentName() {
		return ingredimentName;
	}
	public void setIngredimentName(List<String> ingredimentName) {
		this.ingredimentName = ingredimentName;
	}
	
}
