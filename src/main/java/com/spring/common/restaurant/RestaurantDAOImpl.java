package com.spring.common.restaurant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantDAOImpl implements RestaurantDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL_SELECTALL_RESTAURANT="SELECT CODE_ID, CODE_NM"
			+ " FROM CMS_COMMON_CODE"
			+ " WHERE CODE_TYPE_ID = 'R'"
			+ " ORDER BY CODE_ID";
	
	@Override
	public List<RestaurantVO> selectAll(RestaurantVO rVO) {
		
		return jdbcTemplate.query(SQL_SELECTALL_RESTAURANT,new RestaurantMapper());
	}

}
class RestaurantMapper implements RowMapper<RestaurantVO>{

	@Override
	public RestaurantVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		RestaurantVO rdata=new RestaurantVO();
		
		rdata.setDataCode(rs.getString("CODE_ID"));
		rdata.setDataName(rs.getString("CODE_NM"));
		
		return rdata;
	}
}
