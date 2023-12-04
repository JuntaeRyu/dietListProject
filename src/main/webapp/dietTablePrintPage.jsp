<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식단표 조회</title>
<style type="text/css">
.container {
	display: flex;
	flex-direction: row;
}

table {
	border-collapse: collapse;
}

a {
	text-decoration: none;
}

th {
	background-color: #f5f5f5;
}

th, td {
	border: 1px solid #000000;
	padding: 8px;
	text-align: center;
	white-space: nowrap;
}

#weeklyCalendar {
	margin-top: 20px;
	margin-left: 20px;
}

#header {
	border: 1px solid;
	margin-left: 20px;
	margin-top: 10px;
	border-radius: 8px;
}

#weeklyCalendar thead tr th {
	width: 10%;
}

#calendarBody tr td {
	text-align: left;
	vertical-align: top;
}

@page {
	size: A4 landscape;
	margin: 0;
}

.page-break {
	page-break-before: always;
}

.pageNum {
	text-align: center;
	padding: 10px;
}

@media print {
	#header {
		width: 1000px;
	}
	.pageNum {
		bottom: 0;
		left: 0;
		width: 100%;
		text-align: center;
		padding: 10px;
	}
}
</style>
</head>
<body>
	<c:forEach var="currentPage" begin="1" end="${totalPage}">
		<div style="display: inline-block;" class="page-break">
			<div class="container">
				<div class="content">
					<div id='header' style="border: 1px solid">
						<h2 style="text-align: center">주간 식단표</h2>
						<c:if test="${searchdata.searchStartDate ne null}">
							<span style="display: flex; justify-content: space-around">
								<h5>식당명: ${searchdata.restaurantName}</h5>
								<h5>기간: ${searchdata.searchStartDate} ~
									${searchdata.searchLastDate}</h5>
							</span>
						</c:if>
					</div>

					<div>
						<c:if test="${not empty dietPrintMap}">
							<table id="weeklyCalendar">
								<thead>
									<tr>
										<th id="categorization" style="width: 5%; text-align: center">구분</th>
										<c:forEach var="date" items="${dates}">
											<th>${date.ymd}<br>(${date.weekday})
											</th>
										</c:forEach>
									</tr>
								</thead>
								<tbody id="calendarBody">
									<c:forEach var="mealTypedata" items="${mealTypes}">
										<c:if test="${mealTypedata.key eq currentPage}">
											<c:forEach var="mealType" items="${mealTypedata.value}">
												<c:forEach var="mealTime" items="${mealType}">
													<tr>
														<td style="vertical-align: middle;">${mealTime}</td>
														<c:forEach var="date" items="${dates}">
															<td><c:forEach var="dietMap"
																	items="${dietPrintMap }">
																	<c:set var="dietMapKey"
																		value="${date.ymd}${mealTime}${currentPage}" />
																	<c:if test="${dietMap.key eq dietMapKey}">
																		<c:forEach var="dietTableValue"
																			items="${dietMap.value}">
																			<c:if test="${dietTableValue.meal}">●${dietTableValue.ingredimentName }<br>
																			</c:if>
																			<c:if test="${not dietTableValue.meal}">-${dietTableValue.ingredimentName }<br>
																			</c:if>
																		</c:forEach>
																	</c:if>
																</c:forEach></td>
														</c:forEach>
													</tr>
												</c:forEach>
											</c:forEach>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
							<div class="pageNum">${currentPage}/${totalPage}</div>
						</c:if>
					</div>
				</div>
				<br>
			</div>
		</div>
	</c:forEach>
	<script>
		window.onload = function() {
			window.print();
		};
	</script>

</body>
</html>