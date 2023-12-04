package com.spring.dietprint.diet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DietPrintServiceImpl implements DietPrintService {

	@Autowired
	private DietPrintDAO dpDAO;

	@Override
	public List<List<List<DietPrintVO>>> selectAll(DietPrintVO dpVO) {
		List<DietPrintVO> dpdatas= dpDAO.selectAll(dpVO);

		List<List<List<DietPrintVO>>> weeklyDpdatas = new ArrayList<>();

		for(int i=0; i<4; i++) {
			weeklyDpdatas.add(new ArrayList<>());
			for(int j=0; j<7; j++) {
				weeklyDpdatas.get(i).add(new ArrayList<>());
			}
		}
		
		int weekday = 0;
		int mealType= 0;
		String beforeWeekday= "";
		String mealName="";
		
		for(DietPrintVO dpdata:dpdatas) {

			
			weekday=getWeekdayInteger(dpdata);

			mealType=getMealTimeInteger(dpdata);

			if(!(mealName.equals(dpdata.getMealName())&& beforeWeekday.equals(dpdata.getWeekday()))) {
				dpVO=new DietPrintVO();
				dpVO.setYmd(dpdata.getYmd());
				dpVO.setWeekday(dpdata.getWeekday());
				dpVO.setRestaurantName(dpdata.getRestaurantName());
				dpVO.setMealTime(dpdata.getMealTime());
				dpVO.setMealName(dpdata.getMealName());
				dpVO.setIngredimentName(dpdata.getMealName());
				dpVO.setMeal(true);
				dpVO.setSearchStartDate(dpdata.getSearchStartDate());
				dpVO.setSearchLastDate(dpdata.getSearchLastDate());
				mealName=dpdata.getMealName();
				beforeWeekday=dpdata.getWeekday();
				weeklyDpdatas.get(mealType).get(weekday).add(dpVO);
			}
			weeklyDpdatas.get(mealType).get(weekday).add(dpdata);

		}
		return weeklyDpdatas;
	}
	
	public int getMealTimeInteger(DietPrintVO dpVO) {
		
		int mealType=0;
		
		switch (dpVO.getMealTime()) {
		case "조식":
			mealType=0;
			break;
		case "중식":
			mealType=1;
			break;
		case "석식":
			mealType=2;
			break;
		case "간식":
			mealType=3;
			break;
		default:
			break;
		}
		return mealType;
	}
	
	public int getWeekdayInteger(DietPrintVO dpVO) {
		
		int weekday=0;
		
		switch (dpVO.getWeekday()) {
		case "월":
			weekday=0;
			break;
		case "화":
			weekday=1;
			break;
		case "수":
			weekday=2;
			break;
		case "목":
			weekday=3;
			break;
		case "금":
			weekday=4;
			break;
		case "토":
			weekday=5;
			break;
		case "일":
			weekday=6;
			break;
		default:
			break;
		}
		return weekday;
	}

}
