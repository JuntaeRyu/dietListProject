package com.spring.diettable.diet;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class DietTableServiceImpl implements DietTableService {

	@Autowired
	private DietTableDAO dtDAO;
	
	@Override
	public boolean insert(DietTableVO dtVO) {
		return dtDAO.insert(dtVO);
	}

	@Override
	public DietTableVO selectOne(DietTableVO dtVO) {
		return dtDAO.selectOne(dtVO);
	}

	@Override
	public List<DietTableVO> selectAll(DietTableVO dtVO) {
		List<DietTableVO> dtdatas=dtDAO.selectAll(dtVO);
		if(dtdatas.isEmpty()) {
			return null;
		}
		
		for(DietTableVO dtdata: dtdatas) {
			List<String> dataList=Arrays.asList(dtdata.getIngredimentNames().split(","));
			dtdata.setIngredimentName(dataList);
		}
		
		MultiValueMap<String,DietTableVO> dietTableMap= new LinkedMultiValueMap<>();
		for(DietTableVO dtdata:dtdatas) {
			dietTableMap.add(dtdata.getYmd()+dtdata.getMealTime(), dtdata);
		}
		
		return dtdatas;
	}

	@Override
	public boolean update(DietTableVO dtVO) {
		return dtDAO.update(dtVO);
	}

	@Override
	public boolean delete(DietTableVO dtVO) {
		return dtDAO.delete(dtVO);
	}

}
