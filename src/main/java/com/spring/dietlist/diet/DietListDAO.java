package com.spring.dietlist.diet;

import java.util.List;

public interface DietListDAO {
	public List<DietListVO> selectAll(DietListVO dlVO);
}
