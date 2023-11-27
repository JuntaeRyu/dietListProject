package com.spring.biz.dietList;

import java.util.List;

public interface DietListService {
	public boolean insert(DietListVO dlVO);
	public DietListVO selectOne(DietListVO dlVO);
	public List<DietListVO> selectAll(DietListVO dlVO);
	public boolean update(DietListVO dlVO);
	public boolean delete(DietListVO dlVO);
}
