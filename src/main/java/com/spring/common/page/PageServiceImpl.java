package com.spring.common.page;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageServiceImpl implements PageService {

	public int pageListSets(int currentPage,int pageListCount) {
		int pageListSet=(currentPage-1)/pageListCount+1;
		return pageListSet;
	}
	
	@Autowired
	private PageDAO pDAO;
	

	@Override
	public PageVO selectOne(PageVO pVO) {
		
		if(pVO.getCurrentPage()==0) {
			pVO.setCurrentPage(1);
		}
		if(pVO.getListCount()== 0) {
			pVO.setListCount(1);
		}
		
		PageVO pdata=pDAO.selectOne(pVO);
		
		int pageListCount=10;
		int currentPageListSet=pageListSets(pVO.getCurrentPage(),pageListCount);
		int maxPageListSet=pageListSets(pdata.getTotalPage(),pageListCount);
		int listFirstPage=(currentPageListSet-1)*pageListCount+1;
		
		int listLastPage=listFirstPage+9;
		if(listLastPage>pdata.getTotalPage()) {
			listLastPage=pdata.getTotalPage();
		}
		
		if(currentPageListSet>1) {
			pdata.setBeforePageList(true);
		}
		if(pVO.getCurrentPage()>1) {
			pdata.setBeforePage(true);
		}
		if(currentPageListSet<maxPageListSet){
			pdata.setNextPageList(true);
		}
		if(pVO.getCurrentPage()<pdata.getTotalPage()) {
			pdata.setNextPage(true);
		}
		
		pdata.setListFirstPage(listFirstPage);
		pdata.setListLastPage(listLastPage);
		pdata.setCurrentPage(pVO.getCurrentPage());
		pdata.setListCount(pVO.getListCount());

		return pdata;
	}

}
