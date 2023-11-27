package com.spring.biz.dietTable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class DietTableServiceImpl implements DietTableService {

	@Autowired
	private DietTableDAO dtDAO;
	
	@Override
	public boolean insert(DietTableVO dtVO) {
		return dtDAO.insert(dtVO);
	}

	@Override
	public DietTableVO selectOne(DietTableVO dtVO) {
		return dtDAO.selectOne(dtVO);
	}

	@Override
	public List<DietTableVO> selectAll(DietTableVO dtVO) {
		return dtDAO.selectAll(dtVO);
	}

	@Override
	public boolean update(DietTableVO dtVO) {
		return dtDAO.update(dtVO);
	}

	@Override
	public boolean delete(DietTableVO dtVO) {
		return dtDAO.delete(dtVO);
	}

}
