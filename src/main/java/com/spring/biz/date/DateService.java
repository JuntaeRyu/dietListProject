package com.spring.biz.date;

import java.util.List;

public interface DateService {

	public boolean insert(DateVO dVO);
	public DateVO selectOne(DateVO dVO);
	public List<DateVO> selectAll(DateVO dVO);
	public boolean update(DateVO dVO);
	public boolean delete(DateVO dVO);
}
