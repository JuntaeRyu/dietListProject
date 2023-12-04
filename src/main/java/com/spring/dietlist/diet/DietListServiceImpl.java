package com.spring.dietlist.diet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DietService")
public class DietListServiceImpl implements DietListService {

	@Autowired
	private DietListDAO dlDAO;

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

}
