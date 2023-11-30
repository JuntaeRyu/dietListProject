package com.spring.dietprint.diet;

import java.util.List;

public interface DietPrintService {
	public List<List<List<DietPrintVO>>> selectAll(DietPrintVO dpVO);
}
