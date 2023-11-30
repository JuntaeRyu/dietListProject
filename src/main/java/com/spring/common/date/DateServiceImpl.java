package com.spring.common.date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DateServiceImpl implements DateService {

	@Autowired
	private DateDAO dDAO;
	
	@Override
	public boolean insert(DateVO dVO) {
		return dDAO.insert(dVO);
	}

	@Override
	public DateVO selectOne(DateVO dVO) {
		return dDAO.selectOne(dVO);
	}

	@Override
	public List<DateVO> selectAll(DateVO dVO) {
		return dDAO.selectAll(dVO);
	}

	@Override
	public boolean update(DateVO dVO) {
		return dDAO.update(dVO);
	}

	@Override
	public boolean delete(DateVO dVO) {
		return dDAO.delete(dVO);
	}

}
