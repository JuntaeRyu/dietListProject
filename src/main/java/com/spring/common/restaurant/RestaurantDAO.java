package com.spring.common.restaurant;

import java.util.List;

public interface RestaurantDAO {

	public List<RestaurantVO> selectAll(RestaurantVO cVO);
}
