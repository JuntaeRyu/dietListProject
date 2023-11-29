package com.spring.biz.cmsCommonCode;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CmsCommonCodeDAOImpl implements CmsCommonCodeDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL_SELECTALL_MEALTIME="SELECT CODE_ID, CODE_NM"
			+ " FROM CMS_COMMON_CODE"
			+ " WHERE CODE_TYPE_ID = ?"
			+ " ORDER BY CODE_ID";
	
	@Override
	public boolean insert(CmsCommonCodeVO cVO) {
		return false;
	}

	@Override
	public CmsCommonCodeVO selectOne(CmsCommonCodeVO cVO) {
		return null;
	}

	@Override
	public List<CmsCommonCodeVO> selectAll(CmsCommonCodeVO cVO) {
		
		Object[] args= { cVO.getTypeId() };
		
		return jdbcTemplate.query(SQL_SELECTALL_MEALTIME,args, new CmsCommonCodeMapper());
	}

	@Override
	public boolean update(CmsCommonCodeVO cVO) {
		return false;
	}

	@Override
	public boolean delete(CmsCommonCodeVO cVO) {
		return false;
	}

}
class CmsCommonCodeMapper implements RowMapper<CmsCommonCodeVO>{

	@Override
	public CmsCommonCodeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CmsCommonCodeVO cdata=new CmsCommonCodeVO();
		
		cdata.setDataCode(rs.getString("CODE_ID"));
		cdata.setDataName(rs.getString("CODE_NM"));
		
		return cdata;
	}
}
