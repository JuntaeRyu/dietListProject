package com.spring.dietprint.diet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DietPrintServiceImpl implements DietPrintService {

	@Autowired
	private DietPrintDAO dpDAO;
	
	@Override
	public List<DietPrintVO> selectAll(DietPrintVO dpVO) {
		List<DietPrintVO> dpdatas= dpDAO.selectAll(dpVO);
//		List<DietPrintVO> 
		
		
		String mealName="";
		for(int i=0; i<dpdatas.size(); i++) {
			if(!(mealName.equals(dpdatas.get(i).getMealName()))) {
				dpVO=dpdatas.get(i);
				dpVO.setIngredimentName(mealName);
				dpVO.setMeal(true);
				
				dpdatas.add(i,dpVO);
			}
		}
		return dpdatas;
	}

}
