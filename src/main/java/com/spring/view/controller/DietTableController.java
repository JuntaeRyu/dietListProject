package com.spring.view.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.dietList.DietListService;
import com.spring.biz.dietList.DietListVO;

@Controller
public class DietTableController {

	@Autowired
	private DietListService dietService;
	
	@ModelAttribute("dietTableRestaurantNameSearch")
	public Map<String,String> restaurantNameSearch(){
		Map<String,String> restaurantNameMap = new LinkedHashMap<String, String>();
		
		restaurantNameMap.put("-선택-", "");
		restaurantNameMap.put("여민관", "여민관");
		restaurantNameMap.put("춘추관", "춘추관");
		
		return restaurantNameMap;
	}
	
	@ModelAttribute("dietTableMealTimeSearch")
	public Map<String,String> mealTimeSearch(){
		Map<String,String> mealTimeMap = new LinkedHashMap<String, String>();
		
		mealTimeMap.put("전체", "");
		mealTimeMap.put("조식", "조식");
		mealTimeMap.put("중식", "중식");
		mealTimeMap.put("석식", "석식");
		mealTimeMap.put("간식", "간식");
		
		return mealTimeMap;
	}
	
	@RequestMapping(value="/dietTablePage.do")
	public String dietListPage(DietListVO dVO, Model model) {
		
		
		
		model.addAttribute("searchdata",dVO);
		return "dietTablePage.jsp";
	}
}
