package com.spring.view.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.common.mealtype.MealTypeService;
import com.spring.common.mealtype.MealTypeVO;
import com.spring.common.page.PageService;
import com.spring.common.page.PageVO;
import com.spring.common.restaurant.RestaurantService;
import com.spring.common.restaurant.RestaurantVO;
import com.spring.dietlist.diet.DietListService;
import com.spring.dietlist.diet.DietListVO;

@Controller
public class DietListController {

	@Autowired
	private DietListService dietListService;
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private MealTypeService mealTypeService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@ModelAttribute("dietListRestaurantNameSearch")
	public Map<String,String> restaurantNameSearch(RestaurantVO rVO){
		Map<String,String> restaurantNameMap = new LinkedHashMap<String, String>();
		
		List<RestaurantVO> rdatas=restaurantService.selectAll(rVO);
		
		restaurantNameMap.put("전체", "");
		for (RestaurantVO rdata : rdatas) {
			restaurantNameMap.put(rdata.getDataName(), rdata.getDataName());
		}
		
		return restaurantNameMap;
	}
	
	@ModelAttribute("dietListMealTimeSearch")
	public Map<String,String> mealTimeSearch(MealTypeVO mtVO){
		Map<String,String> mealTimeMap = new LinkedHashMap<String, String>();

		List<MealTypeVO> mtdatas=mealTypeService.selectAll(mtVO);

		for (MealTypeVO mtdata: mtdatas) {
			if(mtdata.getDataName().equals("전체")) {
				mealTimeMap.put(mtdata.getDataName(),"");
			}else {
				mealTimeMap.put(mtdata.getDataName(),mtdata.getDataName());
			}
		}
		return mealTimeMap;
	}
	
	@ModelAttribute("dietListListCountSearch")
	public Map<String,String> listCountSearch(){
		Map<String,String> listCountMap = new LinkedHashMap<String, String>();
		
		listCountMap.put("전체", "1");
		listCountMap.put("10", "10");
		listCountMap.put("20", "20");
		listCountMap.put("30", "30");
		listCountMap.put("50", "50");
		listCountMap.put("100", "100");
		
		return listCountMap;
	}
	
	@RequestMapping(value="/dietListPage.do")
	public String dietListPage(PageVO pVO, DietListVO dlVO, Model model) {
		
		pVO=pageService.selectOne(pVO);
		List<DietListVO> dietListdatas=dietListService.selectAll(dlVO);
		
		model.addAttribute("searchdata",dlVO);
		model.addAttribute("pagedata", pVO);
		model.addAttribute("dietdatas",dietListdatas);
		
		return "dietListPage.jsp";
	}
	
}
