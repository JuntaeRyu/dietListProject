package com.spring.dietprint.diet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DietPrintServiceImpl implements DietPrintService {

	@Autowired
	private DietPrintDAO dpDAO;

	// 주간 식단 데이터 가져오기
	@Override
	public List<List<List<DietPrintVO>>> selectAll(DietPrintVO dpVO) {
		List<DietPrintVO> dpdatas= dpDAO.selectAll(dpVO);

		List<List<List<DietPrintVO>>> weeklyDpdatas = new ArrayList<>();

		// 식사구분별 리스트 생성
		for(int i=0; i<4; i++) {
			weeklyDpdatas.add(new ArrayList<>());
			// 날짜별 리스트 생성
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

			// 재료명에 요리명을 가지는 데이터를 생성해서 추가
			if(!(mealName.equals(dpdata.getMealName())&& beforeWeekday.equals(dpdata.getWeekday()))) {
				// 재료명에 요리명을 가지고 있는 데이터 생성
				dpVO=new DietPrintVO();
				dpVO.setYmd(dpdata.getYmd());
				dpVO.setWeekday(dpdata.getWeekday());
				dpVO.setRestaurantName(dpdata.getRestaurantName());
				dpVO.setMealTime(dpdata.getMealTime());
				dpVO.setMealName(dpdata.getMealName());
				dpVO.setIngredimentName(dpdata.getMealName());
				// 요리데이터인지 재료데이터인지 구분하는 칼럼
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
	
	// 식사구분에 해당하는 인덱스 추출
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
	
	// 날짜가 해당하는 인덱스 추출
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
