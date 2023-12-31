package com.spring.common.restaurant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService{

	@Autowired
	private RestaurantDAO cDAO;
	

	@Override
	public List<RestaurantVO> selectAll(RestaurantVO cVO) {
		return cDAO.selectAll(cVO);
	}

}
