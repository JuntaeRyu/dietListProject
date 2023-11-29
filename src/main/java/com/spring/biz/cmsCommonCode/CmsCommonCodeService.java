package com.spring.biz.cmsCommonCode;

import java.util.List;

public interface CmsCommonCodeService {

	public boolean insert(CmsCommonCodeVO cVO);
	public CmsCommonCodeVO selectOne(CmsCommonCodeVO cVO);
	public List<CmsCommonCodeVO> selectAll(CmsCommonCodeVO cVO);
	public boolean update(CmsCommonCodeVO cVO);
	public boolean delete(CmsCommonCodeVO cVO);
}
