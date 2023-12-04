package com.spring.view.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.common.date.DateService;
import com.spring.common.date.DateVO;
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

		// 선택한 날짜가 해당하는 주간 날짜 추출
		List<DateVO> ddatas=dateService.selectAll(dVO);
		
		// 주간식단표에 들어갈 데이터 추출
		List<List<List<DietPrintVO>>> weeklyDpdatas=dietPrintService.selectAll(dtVO);

		// 주간식단표에 들어갈 데이터를 key:날짜+식사구분+페이지로 변경해서 Map으로 저장
		Map<String,List<DietPrintVO>> dtdatasMap =convertService.convertPrintPage(weeklyDpdatas);
		
		// 총 페이지 추출
		int totalPage=convertService.getPrintPageTotalNum(weeklyDpdatas);
		
		// 페이지당 식사구분 추출
		LinkedMultiValueMap<Integer, List<String>> pagePerMealType=convertService.getPagePerMealType(weeklyDpdatas);
		 
		model.addAttribute("searchdata",dtVO);
		model.addAttribute("dates",ddatas);
		model.addAttribute("dietPrintMap",dtdatasMap);
		model.addAttribute("totalPage",totalPage);
		model.addAttribute("mealTypes",pagePerMealType);
		
		return "dietTablePrintPage.jsp";
	}
}
