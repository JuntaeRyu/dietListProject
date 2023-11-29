package com.spring.view.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.cmsCommonCode.CmsCommonCodeService;
import com.spring.biz.cmsCommonCode.CmsCommonCodeVO;
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
	
	@Autowired
	private CmsCommonCodeService cmsCommonCodeService;
	
	@ModelAttribute("dietListRestaurantNameSearch")
	public Map<String,String> restaurantNameSearch(CmsCommonCodeVO cVO){
		Map<String,String> restaurantNameMap = new LinkedHashMap<String, String>();
		
		cVO.setTypeId("R");
		List<CmsCommonCodeVO> cdatas=cmsCommonCodeService.selectAll(cVO);
		
		restaurantNameMap.put("전체", "");
		for (CmsCommonCodeVO cdata : cdatas) {
			restaurantNameMap.put(cdata.getDataName(), cdata.getDataName());
		}
		
		return restaurantNameMap;
	}
	
	@ModelAttribute("dietListMealTimeSearch")
	public Map<String,String> mealTimeSearch(CmsCommonCodeVO cVO){
		Map<String,String> mealTimeMap = new LinkedHashMap<String, String>();
		
		cVO.setTypeId("M");
		List<CmsCommonCodeVO> cdatas=cmsCommonCodeService.selectAll(cVO);
		
		for (CmsCommonCodeVO cdata: cdatas) {
			if(cdata.getDataName().equals("전체")) {
				mealTimeMap.put(cdata.getDataName(),"");
			}else {
				mealTimeMap.put(cdata.getDataName(),cdata.getDataName());
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
		
		System.out.println("dVO="+dlVO);
		System.out.println("pVO="+pVO);
		
		if(pVO.getCurrentPage()==0 && dlVO.getCurrentPage()==0) {
			pVO.setCurrentPage(1);
			dlVO.setCurrentPage(1);
		}
		
		if(pVO.getListCount()== 0 && dlVO.getListCount()== 0) {
			pVO.setListCount(1);
			dlVO.setListCount(1);
		}
		
		pVO=pageService.selectOne(pVO);
		List<DietListVO> dietListdatas=dietListService.selectAll(dlVO);
		
		model.addAttribute("searchdata",dlVO);
		model.addAttribute("pagedata", pVO);
		model.addAttribute("dietdatas",dietListdatas);
		
		
		System.out.println("컨트롤러 통과");
		return "dietListPage.jsp";
	}
	
}
