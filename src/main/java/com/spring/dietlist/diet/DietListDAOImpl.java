package com.spring.dietlist.diet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DietListDAOImpl implements DietListDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// SQL 쿼리문
	// 페이지당 식단목록
	private final String SQL_SELECTALL_DIETLISTPERPAGE="SELECT 번호,날짜, 요일, 식당, 식사구분, 요리, 재료 FROM (SELECT Rownum AS 번호, 날짜, 요일, 식당, 식사구분, 요리, 재료 FROM"
			+ 	"	(SELECT	TO_CHAR(a.YMD, 'YYYY\"-\"MM\"-\"DD') AS 날짜"
			+ 	"	, TO_CHAR((TO_DATE(a.YMD)), 'dy') AS 요일"
			+ 	"	, b.CODE_NM AS 식당"
			+ 	"	, c.CODE_NM AS 식사구분"
			+ 	"	, d.FOOD_NM AS 요리"
			+ 	"	, d.INGREDIENTS_NM AS 재료"
			+ 	"	FROM cms_menu A"
			+	"	, cms_common_code B"
			+ 	"	, cms_common_code C"
			+ 	"	, VW_FOOD_INGREDIMENT D"
			+ 	"	WHERE A.restaurant_id = B.code_id AND B.CODE_TYPE_ID='R'"
			+ 		" AND A.MEAL_ID = C.CODE_ID  AND C.CODE_TYPE_ID='M'"
			+ 		" AND A.FOOD_ID = D.FOOD_ID"
			+ 		" AND C.CODE_NM LIKE '%' || ? || '%'"
			+		" AND A.YMD BETWEEN TO_DATE(?,'YYYY-MM-DD') AND TO_DATE(?,'YYYY-MM-DD')"
			+		" AND B.CODE_NM LIKE '%' || ? || '%'"
			+ 	" ORDER BY YMD,C.CODE_ID,d.FOOD_NM))"
			+" WHERE 번호 BETWEEN (?*(?-1)+1) AND (?*?)";
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
	public boolean insert(DietListVO dlVO) {
		return false;
	}

	@Override
	public DietListVO selectOne(DietListVO dlVO) {
		return null;
	}

	@Override
	public List<DietListVO> selectAll(DietListVO dlVO) {
			if(dlVO.getListCount()==1) {
				Object[] args= {dlVO.getMealTime(), dlVO.getSearchStartDate(), dlVO.getSearchLastDate(), dlVO.getRestaurantName()};
				return jdbcTemplate.query(SQL_SELECTALL_ALLDIETLIST,args, new DietListMapper());
			}
			Object[] args= {dlVO.getMealTime(), dlVO.getSearchStartDate(), dlVO.getSearchLastDate(), dlVO.getRestaurantName(), dlVO.getListCount(), dlVO.getCurrentPage(), dlVO.getListCount(), dlVO.getCurrentPage()};
			return jdbcTemplate.query(SQL_SELECTALL_DIETLISTPERPAGE,args, new DietListMapper());
	}

	@Override
	public boolean update(DietListVO dlVO) {
		return false;
	}

	@Override
	public boolean delete(DietListVO dlVO) {
		return false;
	}
	
}

// rowMapper
class DietListMapper implements RowMapper<DietListVO>{

	@Override
	public DietListVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		DietListVO dldata=new DietListVO();
		
		dldata.setYmd(rs.getString("날짜"));
		dldata.setWeekday(rs.getString("요일"));
		dldata.setRestaurantName(rs.getString("식당"));
		dldata.setMealTime(rs.getString("식사구분"));
		dldata.setMealName(rs.getString("요리"));
		dldata.setIngredimentName(rs.getString("재료"));
		
		return dldata;
	}
	
	
}