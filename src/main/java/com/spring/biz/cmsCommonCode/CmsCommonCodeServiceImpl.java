package com.spring.biz.cmsCommonCode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CmsCommonCodeService")
public class CmsCommonCodeServiceImpl implements CmsCommonCodeService{

	@Autowired
	private CmsCommonCodeDAO cDAO;
	
	@Override
	public boolean insert(CmsCommonCodeVO cVO) {
		return cDAO.insert(cVO);
	}

	@Override
	public CmsCommonCodeVO selectOne(CmsCommonCodeVO cVO) {
		return cDAO.selectOne(cVO);
	}

	@Override
	public List<CmsCommonCodeVO> selectAll(CmsCommonCodeVO cVO) {
		return cDAO.selectAll(cVO);
	}

	@Override
	public boolean update(CmsCommonCodeVO cVO) {
		return cDAO.update(cVO);
	}

	@Override
	public boolean delete(CmsCommonCodeVO cVO) {
		return cDAO.delete(cVO);
	}

}
