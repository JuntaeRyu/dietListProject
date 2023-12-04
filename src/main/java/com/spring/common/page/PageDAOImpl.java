package com.spring.common.page;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PageDAOImpl implements PageDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// SQL
	private String SQL_SELECTONE="SELECT TRUNC((count(*)+?-1)/?) AS 총페이지 FROM"
			+ " (SELECT ROWNUM AS 번호"
			+ "	,TO_CHAR(a.YMD, 'YYYY\"-\"MM\"-\"DD') AS 날짜"
			+ "	, TO_CHAR((TO_DATE(a.YMD)), 'dy') AS 요일"
			+ "	, b.CODE_NM AS 식당명"
			+ "	, c.CODE_NM AS 식사구분"
			+ "	, d.FOOD_NM AS 요리"
			+ "	, d.INGREDIENTS_NM AS 재료"
			+ "	FROM cms_menu A"
			+ "	, cms_common_code B"
			+ "	, cms_common_code C"
			+ "	, VW_FOOD_INGREDIMENT D"
			+ " WHERE A.restaurant_id = B.code_id AND B.CODE_TYPE_ID='R'"
			+ 		" AND A.MEAL_ID = C.CODE_ID  AND C.CODE_TYPE_ID='M'"
			+		" AND A.FOOD_ID = D.FOOD_ID"
			+ 		" AND C.CODE_NM LIKE '%' || ? || '%'"
			+		" AND A.YMD BETWEEN TO_DATE(?,'YYYY-MM-DD') AND TO_DATE(?,'YYYY-MM-DD')"
			+		" AND B.CODE_NM LIKE '%' || ? || '%'"
			+ " ORDER BY YMD,C.CODE_ID,FOOD_NM)";

//	private String SQL_SELECTONE_ALL="SELECT TRUNC((count(*)+count(*)-1)/count(*)) AS 총페이지 FROM"
//			+ " (SELECT ROWNUM AS 번호"
//			+ "	,TO_CHAR(a.YMD, 'YYYY\"-\"MM\"-\"DD') AS 날짜"
//			+ "	, TO_CHAR((TO_DATE(a.YMD)), 'dy') AS 요일"
//			+ "	, b.CODE_NM AS 식당명"
//			+ "	, c.CODE_NM AS 식사구분"
//			+ "	, d.FOOD_NM AS 요리"
//			+ "	, d.INGREDIENTS_NM AS 재료"
//			+ "	FROM cms_menu A"
//			+ "	, cms_common_code B"
//			+ "	, cms_common_code C"
//			+ "	, VW_FOOD_INGREDIMENT D"
//			+ " WHERE A.restaurant_id = B.code_id AND B.CODE_TYPE_ID='R'"
//			+ 		" AND A.MEAL_ID = C.CODE_ID  AND C.CODE_TYPE_ID='M'"
//			+		" AND A.FOOD_ID = D.FOOD_ID"
//			+ 		" AND C.CODE_NM LIKE '%' || ? || '%'"
//			+		" AND A.YMD BETWEEN TO_DATE(?,'YYYY-MM-DD') AND TO_DATE(?,'YYYY-MM-DD')"
//			+		" AND B.CODE_NM LIKE '%' || ? || '%'"
//			+ " ORDER BY YMD,C.CODE_ID,FOOD_NM)";
	
	
	
	
	// PageDAO CRUD
	@Override
	public PageVO selectOne(PageVO pVO) {
		if(pVO.getListCount()>1) {
		Object[] args = {pVO.getListCount(), pVO.getListCount(), pVO.getMealTime(),pVO.getSearchStartDate(), pVO.getSearchLastDate(),pVO.getRestaurantName()};
		return jdbcTemplate.queryForObject(SQL_SELECTONE, args, new PageRowMapper());
		}
		pVO.setTotalPage(1);
		return pVO;
	}

}

	// rowMapper
class PageRowMapper implements RowMapper<PageVO>{

		@Override
		public PageVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			PageVO pdata=new PageVO();
			
			pdata.setTotalPage(rs.getInt("총페이지"));
			
			return pdata;
		}
	
}
