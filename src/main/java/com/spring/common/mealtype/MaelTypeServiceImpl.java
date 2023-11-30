package com.spring.common.mealtype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaelTypeServiceImpl implements MealTypeService{

	@Autowired
	private MealTypeDAO cDAO;
	
	@Override
	public boolean insert(MealTypeVO cVO) {
		return cDAO.insert(cVO);
	}

	@Override
	public MealTypeVO selectOne(MealTypeVO cVO) {
		return cDAO.selectOne(cVO);
	}

	@Override
	public List<MealTypeVO> selectAll(MealTypeVO cVO) {
		return cDAO.selectAll(cVO);
	}

	@Override
	public boolean update(MealTypeVO cVO) {
		return cDAO.update(cVO);
	}

	@Override
	public boolean delete(MealTypeVO cVO) {
		return cDAO.delete(cVO);
	}

}
