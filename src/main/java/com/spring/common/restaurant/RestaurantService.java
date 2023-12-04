package com.spring.common.restaurant;

import java.util.List;

public interface RestaurantService {

	public List<RestaurantVO> selectAll(RestaurantVO cVO);
}
