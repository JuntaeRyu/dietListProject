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

select {
	width: 30%;
}

.title {
	margin-top: 10px;
	text-align: center;
}

.sidebar {
	border-radius: 8px;
	border: 2px solid #000000;
	padding-top: 20px;
	margin-top: 30px;
	margin-left: 30px;
	width: 130px;
	box-sizing: border-box;
	height: 65vh;
}

.sidebar h2.major {
	text-align: center;
}

.sidebarList {
	list-style: none;
	padding: 0;
	text-align: center;
}

.sidebarList li:hover {
	background-color: #ddd;
}

.sidebarList li {
	padding-top: 5px;
	padding-bottom: 5px;
}

.conditionCheck {
	margin-top: 30px;
	margin-left: 20px;
	margin-right: 40px;
	border-radius: 8px;
	border: 2px solid #000000;
	width: -webkit-fill-available;
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
	padding: 10px;
	align-items: center;
}

.conditionCheck ul {
	list-style: none;
	padding-left: 10px;
	display: flex;
	flex-wrap: wrap;
	width: 80%;
}

.conditionCheck ul li {
	width: calc(40%);
	margin-bottom: 5px;
}

#weeklyCalendar {
	margin-top: 20px;
	margin-left: 20px;
}

#header{
	border: 1px solid;
    margin-left: 20px;
    margin-top: 10px;
    border-radius: 8px;
}
#weeklyCalendar thead tr th{
	width:10%;
}
#calendarBody tr td{
	text-align:left;
	vertical-align: top;
}


@media print {
	button {
		display: none !important;
	}
	.conditionCheck, .sidebar,.title{
		display: none !important;
	}
	#header {
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            width: 95%;
            background-color: white;
            magin-bottom: 10px;
        }
}

@page {
	size: A4 landscape;
}
</style>
</head>
<body>

	<div style="display: inline-block;">
		<h1 class="title">식단표 조회</h1>
		<div class="container">
			<div class="sidebar">
				<section style="margin-bottom: 2em;">
					<h2 class="major">
						<span>메뉴 목록</span>
					</h2>
					<ul class="sidebarList">
						<li><a href="dietListPage.do">식단목록 조회</a></li>
						<li><a href="dietTablePage.do">식단표 조회</a></li>
					</ul>

				</section>
			</div>
			<div class="content">
				<div class="conditionCheck">
					<div style="display: flex; align-items: center;">
						<ul>
							<li style="width: 200px">검색 기간 <input type="date"
								id="startDate" value="${dateType1}"
								onchange="updateEndDateMinMax()"></li>
							<li>~ <input type="date" id="endDate" min="${dateType1}"
								max="${dateType1}"></li>

							<li>식당명 <select id="restaurantName" name="restaurantName">
									<c:forEach var="rName" items="${dietTableRestaurantNameSearch}">
										<option value="${rName.value}">${rName.key}</option>
									</c:forEach>
							</select>
							</li>
							<li>식사 구분 <select id="mealType" name="mealType">
									<c:forEach var="mealTime" items="${dietTableMealTimeSearch}">
										<option value="${mealTime.value}">${mealTime.key}</option>
									</c:forEach>
							</select>
							</li>
						</ul>
						
						<div>
						<button style="margin-right: 30px; width: 100px; height: 60px;"
							onclick="search()">검색</button>
						</div>
					</div>
				</div>
				<c:if test="${not empty dietTableMap}">
				<button style="margin-left: 90%; margin-top: 10px; width: 60px; height: 40px;" onclick="printPage()">인쇄</button>
				</c:if>
				<div id='header' style="border: 1px solid">
					<h2 style="text-align: center">주간 식단표</h2>
					<c:if test="${searchdata.searchStartDate ne null}">
					<span style="display: flex; justify-content: space-around">
						<h5> 식당명: ${searchdata.restaurantName}</h5>
						<h5> 기간: ${searchdata.searchStartDate}  ~  ${searchdata.searchLastDate}</h5>
					</span>
					</c:if>
					<c:if test="${empty dietTableMap}">
						<h4 style="text-align: center;">식단 데이터가 없습니다.</h4>
					</c:if>
				</div>

				<div>
					<c:if test="${not empty dietTableMap}">
					<table id="weeklyCalendar">
						<thead>
							<tr>
								<th id="categorization" style="width: 5%; text-align:center">구분</th>
								<c:forEach var="date" items="${dates}">
									<th>${date.ymd}<br>(${date.weekday})</th>
								</c:forEach>
							</tr>
						</thead>
						<tbody id="calendarBody">
							<c:forEach var="mealTypedata" items="${mealTypedatas}">
								<tr>
									<td style="vertical-align: middle;">${mealTypedata.dataName}</td>
									<c:forEach var="date" items="${dates }">
										<td><c:forEach var="dietMap" items="${dietTableMap }">
										<c:set var="dietMapKey" value="${date.ymd}${mealTypedata.dataName }"/>
										 <c:if test="${dietMap.key eq dietMapKey}">
										 	<c:forEach var="dietTableValue" items="${dietMap.value}">
												●${dietTableValue.mealName}<br>										 		
												<c:forEach var="ingrediment" items="${dietTableValue.ingredimentName }">-${ingrediment }<br>
												</c:forEach>
										 	</c:forEach>
												 </c:if>
											</c:forEach></td>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</c:if>
				</div>
			</div>
			<br>
		</div>
	</div>
	<script>
		function updateEndDateMinMax() {
			// 선택한 시작 날짜 가져오기
			var startDate = new Date(document.getElementById("startDate").value);
			document.getElementById("endDate").value = "";

			// 선택한 시작 날짜 이후로 다음 주 일요일 계산
			var endDate = new Date(startDate);
			endDate.setDate(startDate.getDate() + (7 - startDate.getDay()));

			if (startDate.getDay() === 0) {
				endDate = startDate;
			}

			var formattedEndDate = endDate.toISOString().split('T')[0];

			// 종료 날짜 입력의 min,max 속성 설정
			document.getElementById("endDate").min = document
					.getElementById("startDate").value;
			document.getElementById("endDate").max = formattedEndDate;
		}

		function search() {
			var startDate = document.getElementById("startDate").value;
			var endDate = document.getElementById("endDate").value;
			var restaurantName = document.getElementById("restaurantName").value;
			var mealType = document.getElementById("mealType").value;

			// 유효성 검사
			if (startDate === "" || endDate === "") {
				alert("날짜 선택하세요.");
				return;
			}
			if (restaurantName === "") {
				alert("식당을 선택하세요.");
				return;
			}
			
			window.location.href = 'dietTablePage.do?searchStartDate='
					+ startDate + '&searchLastDate=' + endDate
					+ '&restaurantName=' + restaurantName + '&mealTime='
					+ mealType;
		}
		window.onload = function() {
			var startDateInput = document.getElementById("startDate");
			var endDateInput = document.getElementById("endDate");

		    startDateInput.value = "${searchdata.searchStartDate}";
		    endDateInput.value = "${searchdata.searchLastDate}";

		    // 마지막 날짜가 시작 날짜보다 작으면 시작 날짜로 설정
		    if (endDateInput.value < startDateInput.value) {
		        endDateInput.value = startDateInput.value;
		    }
			
			var startDate = new Date(document.getElementById("startDate").value);
			
			var endDate = new Date(startDate);
			endDate.setDate(startDate.getDate() + (7 - startDate.getDay()));
			
			var formattedEndDate = endDate.toISOString().split('T')[0];
			
			
			document.getElementById("restaurantName").value = "${searchdata.restaurantName}"
					|| ""
			document.getElementById("mealType").value = "${searchdata.mealTime}"
			document.getElementById("endDate").min = document.getElementById("startDate").value;
			document.getElementById("endDate").max = formattedEndDate;
		}
		
		function printPage() {
			var startDate = document.getElementById("startDate").value;
			var endDate = document.getElementById("endDate").value;
			var restaurantName = document.getElementById("restaurantName").value;
			var mealType = document.getElementById("mealType").value;

			
			window.location.href = 'dietPrintPage.do?searchStartDate='
				+ startDate + '&searchLastDate=' + endDate
				+ '&restaurantName=' + restaurantName + '&mealTime='
				+ mealType;
        }
	</script>

</body>
</html>