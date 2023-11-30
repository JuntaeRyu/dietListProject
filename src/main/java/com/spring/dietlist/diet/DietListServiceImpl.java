package com.spring.dietlist.diet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DietService")
public class DietListServiceImpl implements DietListService {

	@Autowired
	private DietListDAO dlDAO;
	
	@Override
	public boolean insert(DietListVO dlVO) {
		return dlDAO.insert(dlVO);
	}

	@Override
	public DietListVO selectOne(DietListVO dlVO) {
		return dlDAO.selectOne(dlVO);
	}

	@Override
	public List<DietListVO> selectAll(DietListVO dlVO) {

		if(dlVO.getCurrentPage()==0) {
			dlVO.setCurrentPage(1);
		}
		if(dlVO.getListCount()== 0) {
			dlVO.setListCount(1);
		}
		
		return dlDAO.selectAll(dlVO);
	}

	@Override
	public boolean update(DietListVO dlVO) {
		return dlDAO.update(dlVO);
	}

	@Override
	public boolean delete(DietListVO dlVO) {
		return dlDAO.delete(dlVO);
	}

}
