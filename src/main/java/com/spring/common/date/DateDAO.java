package com.spring.common.date;

import java.util.List;

public interface DateDAO {
	
	public boolean insert(DateVO dVO);
	public DateVO selectOne(DateVO dVO);
	public List<DateVO> selectAll(DateVO dVO);
	public boolean update(DateVO dVO);
	public boolean delete(DateVO dVO);
}
