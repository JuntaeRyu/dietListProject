package com.spring.biz.dietTable;

import java.util.List;


public interface DietTableService {
	public boolean insert(DietTableVO dtVO);
	public DietTableVO selectOne(DietTableVO dtVO);
	public List<DietTableVO> selectAll(DietTableVO dtVO);
	public boolean update(DietTableVO dtVO);
	public boolean delete(DietTableVO dtVO);
}
