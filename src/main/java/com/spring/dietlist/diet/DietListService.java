package com.spring.dietlist.diet;

import java.util.List;

public interface DietListService {
	public List<DietListVO> selectAll(DietListVO dlVO);
}
