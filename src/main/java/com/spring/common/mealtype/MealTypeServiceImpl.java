package com.spring.common.mealtype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealTypeServiceImpl implements MealTypeService{

	@Autowired
	private MealTypeDAO cDAO;
	

	@Override
	public List<MealTypeVO> selectAll(MealTypeVO cVO) {
		return cDAO.selectAll(cVO);
	}


}
