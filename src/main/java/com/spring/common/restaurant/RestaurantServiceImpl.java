package com.spring.common.restaurant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService{

	@Autowired
	private RestaurantDAO cDAO;
	
	@Override
	public boolean insert(RestaurantVO cVO) {
		return cDAO.insert(cVO);
	}

	@Override
	public RestaurantVO selectOne(RestaurantVO cVO) {
		return cDAO.selectOne(cVO);
	}

	@Override
	public List<RestaurantVO> selectAll(RestaurantVO cVO) {
		return cDAO.selectAll(cVO);
	}

	@Override
	public boolean update(RestaurantVO cVO) {
		return cDAO.update(cVO);
	}

	@Override
	public boolean delete(RestaurantVO cVO) {
		return cDAO.delete(cVO);
	}

}
