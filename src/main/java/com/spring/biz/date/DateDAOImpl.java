package com.spring.biz.date;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DateDAOImpl implements DateDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String SQL_SELECTALL_DATE="SELECT"
			+ " TO_CHAR(TRUNC(TO_DATE(?, 'YYYY-MM-DD'), 'IW') + (LEVEL-1),'YYYY-MM-DD') AS ymd,"
			+ " TO_CHAR(TRUNC(TO_DATE(?, 'YYYY-MM-DD'), 'IW') + (LEVEL-1),'dy') AS weekday"
			+ " FROM dual"
			+ " CONNECT BY LEVEL <= 7";

	@Override
	public boolean insert(DateVO dVO) {
		return false;
	}

	@Override
	public DateVO selectOne(DateVO dVO) {
		return null;
	}

	@Override
	public List<DateVO> selectAll(DateVO dVO) {
		Object[] args= { dVO.getSearchStartDate(),dVO.getSearchStartDate() };

		return jdbcTemplate.query(SQL_SELECTALL_DATE,args, new DateMapper());
	}

	@Override
	public boolean update(DateVO dVO) {
		return false;
	}

	@Override
	public boolean delete(DateVO dVO) {
		return false;
	}

}
class DateMapper implements RowMapper<DateVO>{

	@Override
	public DateVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		DateVO ddata=new DateVO();

		ddata.setYmd(rs.getString("ymd"));
		ddata.setWeekday(rs.getString("weekday"));

		return ddata;
	}
}

