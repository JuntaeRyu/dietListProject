package com.spring.view.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.dietList.DietListService;
import com.spring.biz.dietList.DietListVO;
import com.spring.biz.page.PageService;
import com.spring.biz.page.PageVO;

@Controller
public class DietListController {

	@Autowired
	private DietListService dietListService;
	
	@Autowired
	private PageService pageService;
	
	@ModelAttribute("dietListRestaurantNameSearch")
	public Map<String,String> restaurantNameSearch(){
		Map<String,String> restaurantNameMap = new LinkedHashMap<String, String>();
		
		restaurantNameMap.put("전체", "");
		restaurantNameMap.put("여민관", "여민관");
		restaurantNameMap.put("춘추관", "춘추관");
		
		return restaurantNameMap;
	}
	
	@ModelAttribute("dietListMealTimeSearch")
	public Map<String,String> mealTimeSearch(){
		Map<String,String> mealTimeMap = new LinkedHashMap<String, String>();
		
		mealTimeMap.put("전체", "");
		mealTimeMap.put("조식", "조식");
		mealTimeMap.put("중식", "중식");
		mealTimeMap.put("석식", "석식");
		mealTimeMap.put("간식", "간식");
		
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
		
		System.out.println("dVO="+dlVO);
		System.out.println("pVO="+pVO);
		
		if(pVO.getCurrentPage()==0) {
			pVO.setCurrentPage(1);
		}
		if(pVO.getListCount()== null && dlVO.getListCount()== null) {
			pVO.setListCount("1");
			dlVO.setListCount("1");
		}
		
		pVO=pageService.selectOne(pVO);
		if(pVO!=null) {
			dlVO.setCurrentPage(pVO.getCurrentPage());
		}
		List<DietListVO> dietListdatas=dietListService.selectAll(dlVO);
		
		model.addAttribute("searchdata",dlVO);
		model.addAttribute("pagedata", pVO);
		model.addAttribute("dietdatas",dietListdatas);
		
		
		System.out.println("컨트롤러 통과");
		return "dietListPage.jsp";
	}
	
}
