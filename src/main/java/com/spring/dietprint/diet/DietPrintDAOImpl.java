package com.spring.dietprint.diet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DietPrintDAOImpl implements DietPrintDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// SQL 쿼리문
	// 페이지당 식단목록
	// 전체 식단목록
	private final String SQL_SELECTALL_ALLDIETLIST="SELECT 날짜, 요일, 식당, 식사구분, 요리, 재료 FROM"
			+ 	" (SELECT TO_CHAR(a.YMD, 'YYYY\"-\"MM\"-\"DD') AS 날짜"
			+ 	" , TO_CHAR((TO_DATE(a.YMD)), 'dy') AS 요일"
			+ 	" , b.CODE_NM AS 식당"
			+ 	" , c.CODE_NM AS 식사구분"
			+ 	" , d.FOOD_NM AS 요리"
			+ 	" , d.INGREDIENTS_NM AS 재료"
			+ 	" FROM cms_menu A"
			+	" , cms_common_code B"
			+ 	" , cms_common_code C"
			+ 	" , VW_FOOD_INGREDIMENT D"
			+ 	" WHERE A.restaurant_id = B.code_id AND B.CODE_TYPE_ID='R'"
			+ 	" AND A.MEAL_ID = C.CODE_ID  AND C.CODE_TYPE_ID='M'"
			+ 	" AND A.FOOD_ID = D.FOOD_ID"
			+ 	" AND C.CODE_NM LIKE '%' || ? || '%'"
			+	" AND A.YMD BETWEEN TO_DATE(?,'YYYY-MM-DD') AND TO_DATE(?,'YYYY-MM-DD')"
			+	" AND B.CODE_NM LIKE '%' || ? || '%'"
			+ 	" ORDER BY YMD,C.CODE_ID,d.FOOD_NM)";

	// DietDAO의 CRUD
	@Override
	public List<DietPrintVO> selectAll(DietPrintVO dpVO) {
				Object[] args= {dpVO.getMealTime(), dpVO.getSearchStartDate(), dpVO.getSearchLastDate(), dpVO.getRestaurantName()};
				return jdbcTemplate.query(SQL_SELECTALL_ALLDIETLIST,args, new DietListMapper());
	}

}

// rowMapper
class DietListMapper implements RowMapper<DietPrintVO>{

	@Override
	public DietPrintVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		DietPrintVO dpdata=new DietPrintVO();
		
		dpdata.setYmd(rs.getString("날짜"));
		dpdata.setWeekday(rs.getString("요일"));
		dpdata.setRestaurantName(rs.getString("식당"));
		dpdata.setMealTime(rs.getString("식사구분"));
		dpdata.setMealName(rs.getString("요리"));
		dpdata.setIngredimentName(rs.getString("재료"));
		
		return dpdata;
	}
}