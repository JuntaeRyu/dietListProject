package com.spring.biz.page;

import java.util.List;

public interface PageDAO {
	
	public boolean insert(PageVO pVO);
	public PageVO selectOne(PageVO pVO);
	public List<PageVO> selectAll(PageVO pVO);
	public boolean update(PageVO pVO);
	public boolean delete(PageVO pVO);
}
