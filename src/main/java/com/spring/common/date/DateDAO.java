package com.spring.common.date;

import java.util.List;

public interface DateDAO {
	
	public List<DateVO> selectAll(DateVO dVO);
}
