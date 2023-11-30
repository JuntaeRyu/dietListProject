package com.spring.view.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.common.date.DateService;
import com.spring.common.date.DateVO;
import com.spring.common.mealtype.MealTypeService;
import com.spring.common.mealtype.MealTypeVO;
import com.spring.common.restaurant.RestaurantService;
import com.spring.common.restaurant.RestaurantVO;
import com.spring.diettable.diet.DietTableService;
import com.spring.diettable.diet.DietTableVO;

@Controller
public class DietTableController {

	@Autowired
	private DietTableService dietTableService;

	@Autowired
	private MealTypeService mealTypeService;
	
	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private DateService dateService;

	@ModelAttribute("dietTableRestaurantNameSearch")
	public Map<String,String> restaurantNameSearch(RestaurantVO rVO){
		Map<String,String> restaurantNameMap = new LinkedHashMap<String, String>();

		List<RestaurantVO> rdatas=restaurantService.selectAll(rVO);

		restaurantNameMap.put("-선택-", "");
		for (RestaurantVO rdata : rdatas) {
			restaurantNameMap.put(rdata.getDataName(), rdata.getDataName());
		}

		return restaurantNameMap;
	}

	@ModelAttribute("dietTableMealTimeSearch")
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

	@RequestMapping(value="/dietTablePage.do")
	public String dietTablePage(DietTableVO dtVO,DateVO dVO,MealTypeVO mtVO, Model model) {

		List<MealTypeVO> mtdatas=mealTypeService.selectAll(mtVO);
		mtdatas.remove(0);


		List<DateVO> ddatas=dateService.selectAll(dVO);
		List<DietTableVO> dtdatas=dietTableService.selectAll(dtVO);

		if(dtdatas!=null) {
			MultiValueMap<String,DietTableVO> dietTableMap= new LinkedMultiValueMap<>();
			for(DietTableVO dtdata:dtdatas) {
				dietTableMap.add(dtdata.getYmd()+dtdata.getMealTime(), dtdata);
			}

			model.addAttribute("dietTableMap",dietTableMap);
			model.addAttribute("dates",ddatas);
			model.addAttribute("mealTypedatas",mtdatas);
		}
		model.addAttribute("searchdata",dtVO);


		return "dietTablePage.jsp";
	}
}
