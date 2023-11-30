package com.spring.diettable.diet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DietTableDAOImpl implements DietTableDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 식단표 
	private final String SQL_SELECTALL_DIETTABLE="SELECT TO_CHAR(a.YMD, 'YYYY\"-\"MM\"-\"DD') AS 날짜"
			+ "	, TO_CHAR((TO_DATE(a.YMD)), 'dy') AS 요일"
			+ "	, b.CODE_NM AS 식당"
			+ "	, c.CODE_NM AS 식사구분"
			+ "	, d.FOOD_NM AS 요리명"
			+ "	, LISTAGG(D.INGREDIENTS_NM,',') WITHIN GROUP(ORDER BY ROWNUM) AS 재료명들"
			+ "	FROM cms_menu A"
			+ "	, cms_common_code B"
			+ "	, cms_common_code C"
			+ "	, VW_FOOD_INGREDIMENT D"
			+ " WHERE A.restaurant_id = B.code_id AND B.CODE_TYPE_ID='R'"
			+ " AND A.MEAL_ID = C.CODE_ID  AND C.CODE_TYPE_ID='M'"
			+ " AND A.FOOD_ID = D.FOOD_ID"
			+ " AND C.CODE_NM LIKE '%' || ? || '%'"
			+ " AND A.YMD BETWEEN TO_DATE(?,'YYYY-MM-DD') AND TO_DATE(?,'YYYY-MM-DD')"
			+ " AND B.CODE_NM = ?"
			+ " GROUP BY A.YMD"
			+ "	, TO_CHAR(TO_DATE(a.YMD), 'dy')"
			+ "	,b.CODE_NM"
			+ "	,c.CODE_NM"
			+ "	,c.CODE_ID"
			+ "	,d.FOOD_NM"
			+ " ORDER BY YMD,C.CODE_ID,FOOD_NM";
	
	@Override
	public boolean insert(DietTableVO dtVO) {
		return false;
	}

	@Override
	public DietTableVO selectOne(DietTableVO dtVO) {
		return null;
	}

	@Override
	public List<DietTableVO> selectAll(DietTableVO dtVO) {
		Object[] args= {dtVO.getMealTime(), dtVO.getSearchStartDate(), dtVO.getSearchLastDate(), dtVO.getRestaurantName()};
		return jdbcTemplate.query(SQL_SELECTALL_DIETTABLE,args, new DietListMapper());
	}

	@Override
	public boolean update(DietTableVO dtVO) {
		return false;
	}

	@Override
	public boolean delete(DietTableVO dtVO) {
		return false;
	}

}

//rowMapper
class DietListMapper implements RowMapper<DietTableVO>{

	@Override
	public DietTableVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		DietTableVO dtdata=new DietTableVO();
		
		dtdata.setYmd(rs.getString("날짜"));
		dtdata.setWeekday(rs.getString("요일"));
		dtdata.setRestaurantName(rs.getString("식당"));
		dtdata.setMealTime(rs.getString("식사구분"));
		dtdata.setMealName(rs.getString("요리명"));
		dtdata.setIngredimentNames(rs.getString("재료명들"));
		
		return dtdata;
	}
}