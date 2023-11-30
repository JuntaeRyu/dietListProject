package com.spring.common.mealtype;

import java.util.List;

public interface MealTypeService {

	public boolean insert(MealTypeVO cVO);
	public MealTypeVO selectOne(MealTypeVO cVO);
	public List<MealTypeVO> selectAll(MealTypeVO cVO);
	public boolean update(MealTypeVO cVO);
	public boolean delete(MealTypeVO cVO);
}
