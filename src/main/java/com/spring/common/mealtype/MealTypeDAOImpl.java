package com.spring.common.mealtype;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MealTypeDAOImpl implements MealTypeDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL_SELECTALL_MEALTYPE="SELECT CODE_ID, CODE_NM"
			+ " FROM CMS_COMMON_CODE"
			+ " WHERE CODE_TYPE_ID = 'M'"
			+ " ORDER BY CODE_ID";
	
	@Override
	public boolean insert(MealTypeVO mtVO) {
		return false;
	}

	@Override
	public MealTypeVO selectOne(MealTypeVO mtVO) {
		return null;
	}

	@Override
	public List<MealTypeVO> selectAll(MealTypeVO mtVO) {
		
		return jdbcTemplate.query(SQL_SELECTALL_MEALTYPE,new MealTypeMapper());
	}

	@Override
	public boolean update(MealTypeVO mtVO) {
		return false;
	}

	@Override
	public boolean delete(MealTypeVO mtVO) {
		return false;
	}

}
class MealTypeMapper implements RowMapper<MealTypeVO>{

	@Override
	public MealTypeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MealTypeVO mtdata=new MealTypeVO();
		
		mtdata.setDataCode(rs.getString("CODE_ID"));
		mtdata.setDataName(rs.getString("CODE_NM"));
		
		return mtdata;
	}
}
