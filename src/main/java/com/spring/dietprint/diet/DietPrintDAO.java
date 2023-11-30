package com.spring.dietprint.diet;

import java.util.List;

public interface DietPrintDAO {
	public List<DietPrintVO> selectAll(DietPrintVO dpVO);
}
