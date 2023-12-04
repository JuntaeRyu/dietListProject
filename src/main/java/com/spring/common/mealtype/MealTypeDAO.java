package com.spring.common.mealtype;

import java.util.List;

public interface MealTypeDAO {

	public List<MealTypeVO> selectAll(MealTypeVO cVO);
}
