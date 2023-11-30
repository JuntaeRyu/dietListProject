package com.spring.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.common.date.DateService;
import com.spring.common.date.DateVO;
import com.spring.common.mealtype.MealTypeService;
import com.spring.common.mealtype.MealTypeVO;
import com.spring.dietprint.diet.DietPrintService;
import com.spring.dietprint.diet.DietPrintVO;

@Controller
public class DietPrintController {

	@Autowired
	private DietPrintService dietTableService;

	@Autowired
	private MealTypeService mealTypeService;
	
	@Autowired
	private DateService dateService;


	@RequestMapping(value="/dietPrintPage.do")
	public String dietTablePage(DietPrintVO dtVO,DateVO dVO,MealTypeVO mtVO, Model model) {

		List<MealTypeVO> mtdatas=mealTypeService.selectAll(mtVO);
		mtdatas.remove(0);


		List<DateVO> ddatas=dateService.selectAll(dVO);
		List<DietPrintVO> dtdatas=dietTableService.selectAll(dtVO);

		for(DietPrintVO v : dtdatas) {
			System.out.println("dtdata"+v);
			
		}
		
		return "dietTablePage.jsp";
	}
}
