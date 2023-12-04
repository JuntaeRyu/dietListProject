package com.spring.dietprint.convert;

import java.util.List;
import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;

import com.spring.dietprint.diet.DietPrintVO;

public interface ConvertService {

	public Map<String,List<DietPrintVO>> convertPrintPage(List<List<List<DietPrintVO>>> dtdatas);
	public int getPrintPageTotalNum(List<List<List<DietPrintVO>>> weeklyDpdatas);
	public LinkedMultiValueMap<Integer, List<String>> getPagePerMealType(List<List<List<DietPrintVO>>> weeklyDpdatas);
}
