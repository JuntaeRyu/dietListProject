package com.spring.dietprint.convert;

import java.util.List;
import java.util.Map;

import com.spring.common.date.DateVO;
import com.spring.dietprint.diet.DietPrintVO;

public interface ConvertService {

	public Map<String,List<DietPrintVO>> convertPrintPage(List<List<List<DietPrintVO>>> dtdatas);
	public int getPrintPageTotalNum(List<List<List<DietPrintVO>>> weeklyDpdatas);
	public Map<String,List<String>> getPagePerMealType(int totalPage,List<List<List<DietPrintVO>>> weeklyDpdatas);
}
