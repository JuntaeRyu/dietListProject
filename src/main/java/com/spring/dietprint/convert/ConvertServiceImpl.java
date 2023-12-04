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
				// 식사구분별 첫번째 페이지
				page=mealTypePerFirstPage;
				// 식사구분별 20-채울데이터
				pagePerData=mealTypePagePerFirstData;
				for(DietPrintVO dpdata: dpdatas) {
					// 키값으로 날짜+식사구분+페이지를 가지고 있는 Map
					dietPrintMap.add(dpdata.getYmd()+dpdata.getMealTime()+page, dpdata);
					pagePerData++;

					// 해당 페이지식사구분별 데이터가 20개가 된다면
					if(pagePerData==20) {
						page++;
						pagePerData=0;
					}
				}
				// 식사구분별 최고 페이지, 최대 데이터 추출
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

	// 총페이지수 추출
	public int getPrintPageTotalNum(List<List<List<DietPrintVO>>> weeklyDpdatas) {

		// 각 식사구분별 페이지갯수 추출
		int[] mealTypeMaxList=mealTypeMaxList(weeklyDpdatas);
		
		// 합을 통해 총페이지 수 추출
		int sum=0;
		for(int v:mealTypeMaxList) {
			sum+=v;
		}

		int page=sum/20;
		// 데이터가 남아있다면 페이지 +1
		if(sum%20>0) {
			page++;
		}

		return page;
	}

	// 페이지당 포함하는 식사구분 추출
	public LinkedMultiValueMap<Integer,List<String>> getPagePerMealType(List<List<List<DietPrintVO>>> weeklyDpdatas) {

		// 각 식사구분별 페이지갯수 추출
		int[] mealTypeMaxList=mealTypeMaxList(weeklyDpdatas);
		String[] mealType= {"조식","중식","석식","간식"};

		// 한페이지에 포함되는 식사구분 리스트 생성
		List<String> pageMealType=new ArrayList<String>();
		// 페이지당 식사구분 Map생성
		LinkedMultiValueMap<Integer, List<String>> pagePerMealType=new LinkedMultiValueMap<>();

		int page=1;
		int pagePerData=0;

		for(int i=0; i<mealTypeMaxList.length; i++) {
			for(int j=0; j<mealTypeMaxList[i]; j++) {
				// 해당 페이지의 식단 데이터의 식사구분 추가
				pageMealType.add(mealType[i]);
				pagePerData++;
				if(pagePerData==20) {
					// 식사구분 리스트 중복제거를 위해 Set으로 변환
					LinkedHashSet<String> deleteDuplicateData = new LinkedHashSet<>(pageMealType);
					// 다시 리스트로 변환
					pageMealType=new ArrayList<>(deleteDuplicateData);
					// 중복 제거된 리스트를 key를 페이지로 하는 Map에 추가
					pagePerMealType.add(page, pageMealType);
					// 리스트 초기화
					pageMealType=new ArrayList<>();
					++page;
					pagePerData=0;
				}
			}
		}
		// 20개가 안되었지만 데이터가 남아있다면 똑같이 Map에 추가
		if (!pageMealType.isEmpty()) {
			LinkedHashSet<String> deleteDuplicateData = new LinkedHashSet<>(pageMealType);
			List<String> pageMealTypeNotDuplicate = new ArrayList<>(deleteDuplicateData);


			pagePerMealType.add(page, pageMealTypeNotDuplicate);
		}
		
		return pagePerMealType;
	}

	// 각 식사구분별 페이지갯수 추출
	public int[] mealTypeMaxList(List<List<List<DietPrintVO>>> weeklyDpdatas) {

		// 식사구분별 페이지 리스트 생성
		int[] mealTypeMaxList= {1,1,1,1};

		// 각 식사구분별 페이지갯수 추출
		for(int i=0; i<weeklyDpdatas.size(); i++) {
			for(List<DietPrintVO> dpdatas: weeklyDpdatas.get(i)) {
				if(mealTypeMaxList[i]<dpdatas.size()) {
					mealTypeMaxList[i]=dpdatas.size();
				}
			}
		}
		return mealTypeMaxList;
	}
}
