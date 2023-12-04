package com.spring.dietprint.convert;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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

	public LinkedMultiValueMap<Integer,List<String>> getPagePerMealType(List<List<List<DietPrintVO>>> weeklyDpdatas) {

		int[] mealTypeMaxList= {1,1,1,1};
		String[] mealType= {"조식","중식","석식","간식"};

		for(int i=0; i<weeklyDpdatas.size(); i++) {
			for(List<DietPrintVO> dpdatas: weeklyDpdatas.get(i)) {
				if(mealTypeMaxList[i]<dpdatas.size()) {
					mealTypeMaxList[i]=dpdatas.size();
				}
			}
		}

		List<String> pageMealType=new ArrayList<String>();
		LinkedMultiValueMap<Integer, List<String>> pagePerMealType=new LinkedMultiValueMap<>();
		
		int page=1;
		int pagePerData=0;
		
		for(int i=0; i<mealTypeMaxList.length; i++) {
			for(int j=0; j<mealTypeMaxList[i]; j++) {
				pageMealType.add(mealType[i]);
				pagePerData++;
				if(pagePerData==20) {
					LinkedHashSet<String> deleteDuplicateData = new LinkedHashSet<>(pageMealType);
					pageMealType=new ArrayList<>(deleteDuplicateData);
					pagePerMealType.add(page, pageMealType);
					pageMealType=new ArrayList<>();
					++page;
					pagePerData=0;
				}
			}
		}
		if (!pageMealType.isEmpty()) {
		    LinkedHashSet<String> deleteDuplicateData = new LinkedHashSet<>(pageMealType);
		    List<String> pageMealTypeNotDuplicate = new ArrayList<>(deleteDuplicateData);
		    
		    
		    pagePerMealType.add(page, pageMealTypeNotDuplicate);
		}


		return pagePerMealType;
	}

}
