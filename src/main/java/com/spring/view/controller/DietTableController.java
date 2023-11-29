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

import com.spring.biz.cmsCommonCode.CmsCommonCodeService;
import com.spring.biz.cmsCommonCode.CmsCommonCodeVO;
import com.spring.biz.date.DateService;
import com.spring.biz.date.DateVO;
import com.spring.biz.dietTable.DietTableService;
import com.spring.biz.dietTable.DietTableVO;

@Controller
public class DietTableController {

	@Autowired
	private DietTableService dietTableService;

	@Autowired
	private CmsCommonCodeService cmsCommonCodeService;

	@Autowired
	private DateService dateService;

	@ModelAttribute("dietTableRestaurantNameSearch")
	public Map<String,String> restaurantNameSearch(CmsCommonCodeVO cVO){
		Map<String,String> restaurantNameMap = new LinkedHashMap<String, String>();

		cVO.setTypeId("R");
		List<CmsCommonCodeVO> cdatas=cmsCommonCodeService.selectAll(cVO);

		restaurantNameMap.put("-선택-", "");
		for (CmsCommonCodeVO cdata : cdatas) {
			restaurantNameMap.put(cdata.getDataName(), cdata.getDataName());
		}

		return restaurantNameMap;
	}

	@ModelAttribute("dietTableMealTimeSearch")
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

	@RequestMapping(value="/dietTablePage.do")
	public String dietTablePage(DietTableVO dtVO,DateVO dVO,CmsCommonCodeVO cVO, Model model) {

		cVO.setTypeId("M");
		List<CmsCommonCodeVO> cdatas=cmsCommonCodeService.selectAll(cVO);
		cdatas.remove(0);


		List<DateVO> ddatas=dateService.selectAll(dVO);
		List<DietTableVO> dtdatas=dietTableService.selectAll(dtVO);

		if(dtdatas!=null) {
			MultiValueMap<String,DietTableVO> dietTableMap= new LinkedMultiValueMap<>();
			for(DietTableVO dtdata:dtdatas) {
				dietTableMap.add(dtdata.getYmd()+dtdata.getMealTime(), dtdata);
			}

			model.addAttribute("dietTableMap",dietTableMap);
			model.addAttribute("dates",ddatas);
			model.addAttribute("mealTypedatas",cdatas);
		}
		model.addAttribute("searchdata",dtVO);


		return "dietTablePage.jsp";
	}
}
