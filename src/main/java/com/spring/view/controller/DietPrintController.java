package com.spring.view.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.common.date.DateService;
import com.spring.common.date.DateVO;
import com.spring.common.mealtype.MealTypeService;
import com.spring.common.mealtype.MealTypeVO;
import com.spring.dietprint.convert.ConvertService;
import com.spring.dietprint.diet.DietPrintService;
import com.spring.dietprint.diet.DietPrintVO;

@Controller
public class DietPrintController {

	@Autowired
	private DietPrintService dietPrintService;

	@Autowired
	private DateService dateService;

	@Autowired
	private ConvertService convertService;
	
	@RequestMapping(value="/dietPrintPage.do")
	public String dietTablePage(DietPrintVO dtVO,DateVO dVO, Model model) {

		
		List<DateVO> ddatas=dateService.selectAll(dVO);
		
		List<List<List<DietPrintVO>>> weeklyDpdatas=dietPrintService.selectAll(dtVO);

		Map<String,List<DietPrintVO>> dtdatasMap =convertService.convertPrintPage(weeklyDpdatas);
		
		int totalPage=convertService.getPrintPageTotalNum(weeklyDpdatas);
		
		 Map<String,List<String>> mealType=convertService.getPagePerMealType(totalPage, weeklyDpdatas);

		
		model.addAttribute("dates",ddatas);
		model.addAttribute("dietPrintMap",dtdatasMap);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("mealTypes",mealType);
		
		return "dietTablePage.jsp";
	}
}
