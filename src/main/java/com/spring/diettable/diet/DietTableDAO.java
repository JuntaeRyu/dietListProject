package com.spring.diettable.diet;

import java.util.List;

public interface DietTableDAO {
	public List<DietTableVO> selectAll(DietTableVO dtVO);
}
