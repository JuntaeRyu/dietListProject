package com.spring.common.date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DateServiceImpl implements DateService {

	@Autowired
	private DateDAO dDAO;
	
	@Override
	public List<DateVO> selectAll(DateVO dVO) {
		return dDAO.selectAll(dVO);
	}

}
