package com.spring.dietprint.convert;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.spring.common.date.DateVO;
import com.spring.dietprint.diet.DietPrintVO;

@Service
public class ConvertServiceImpl implements ConvertService{

	public Map<String,List<DietPrintVO>> convertPrintPage(List<List<List<DietPrintVO>>> weeklyDpdatas){

		MultiValueMap<String,DietPrintVO> dietPrintMap= new LinkedMultiValueMap<>();

		int page=1;
		int mealTypePerFirstPage=1;
		int mealTypePerMaxPage=1;
		int pagePerData=0;
		int mealTypePagePerFirstData=0;
		int mealTypePagePerMaxData=0;


		for(List<List<DietPrintVO>> mealTypeDpdatas: weeklyDpdatas) {
			for(List<DietPrintVO> dpdatas: mealTypeDpdatas) {
				page=mealTypePerFirstPage;
				pagePerData=mealTypePagePerFirstData;
				for(DietPrintVO dpdata: dpdatas) {
					dpdata.setPage(page);
					dietPrintMap.add(dpdata.getYmd()+dpdata.getMealTime()+page, dpdata);
					pagePerData++;
					if(pagePerData==20) {
						page++;
						pagePerData=0;
					}
				}
				if(mealTypePerMaxPage<page) {
					mealTypePerMaxPage=page;
					mealTypePagePerMaxData=pagePerData;
				}else if(mealTypePerMaxPage==page) {
					if(mealTypePagePerMaxData<pagePerData) {
						mealTypePagePerMaxData=pagePerData;
					}
				}
			}
			mealTypePerFirstPage=mealTypePerMaxPage;
			mealTypePagePerFirstData=mealTypePagePerMaxData;
		}

		return dietPrintMap;
	}

	public int getPrintPageTotalNum(List<List<List<DietPrintVO>>> weeklyDpdatas) {

		int[] mealTypeMaxList= {1,1,1,1};

		for(int i=0; i<weeklyDpdatas.size(); i++) {
			for(List<DietPrintVO> dpdatas: weeklyDpdatas.get(i)) {
				if(mealTypeMaxList[i]<dpdatas.size()) {
					mealTypeMaxList[i]=dpdatas.size();
				}
			}
		}
		int sum=0;
		for(int v:mealTypeMaxList) {
			sum+=v;
		}

		int page=sum/20;
		if(sum%20>0) {
			page++;
		}

		return page;
	}
	
	public Map<String,List<String>> getPagePerMealType(int totalPage,List<List<List<DietPrintVO>>> weeklyDpdatas) {
		
		int[] mealTypeMaxList= {1,1,1,1};
		String[] mealType= {"조식","중식","석식","간식"};
		
		for(int i=0; i<weeklyDpdatas.size(); i++) {
			for(List<DietPrintVO> dpdatas: weeklyDpdatas.get(i)) {
				if(mealTypeMaxList[i]<dpdatas.size()) {
					mealTypeMaxList[i]=dpdatas.size();
				}
			}
		}

		int i=0;
		while(true) {
			int pagedataCnt=mealTypeMaxList[i]-20;
			i++;
		}
		
		return null;
	}
	
}
