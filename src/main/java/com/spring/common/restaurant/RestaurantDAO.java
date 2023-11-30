package com.spring.common.restaurant;

import java.util.List;

public interface RestaurantDAO {

	public boolean insert(RestaurantVO cVO);
	public RestaurantVO selectOne(RestaurantVO cVO);
	public List<RestaurantVO> selectAll(RestaurantVO cVO);
	public boolean update(RestaurantVO cVO);
	public boolean delete(RestaurantVO cVO);
}
