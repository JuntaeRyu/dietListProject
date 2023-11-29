<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식단 목록 조회</title>
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

.dietList {
	margin-top: 30px;
	margin-left: 20px;
	width: -webkit-fill-available;
	height: 15%;
	display: flex;
	flex-wrap: wrap;
	justify-content: space-between;
	padding: 10px;
	align-items: center;
}

.dietTable {
	margin-top: 20px;
	margin-left: 20px;
	margin-right: 40px;
	border: 1px solid #000000;
	text-align: center;
	width: -webkit-fill-available;
}

.page {
	margin-left: 120px;
}

.page table {
	margin: auto;
}

.page a {
	color: #000000;
}

.currentPage {
	font-weight: bolder;
}

.notCurrentPage {
	font-weight: lighter;
}
</style>
</head>
<body>
	<div style="display: inline-block;">
		<h1 class="title">식단목록 조회</h1>
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
									<c:forEach var="rName" items="${dietListRestaurantNameSearch}">
										<option value="${rName.value}">${rName.key}</option>
									</c:forEach>
							</select>
							</li>
							<li>식사 구분 <select id="mealType" name="mealType">
									<c:forEach var="mealTime" items="${dietListMealTimeSearch}">
										<option value="${mealTime.value}">${mealTime.key}</option>
									</c:forEach>
							</select>
							</li>
							<li>목록 갯수 <select id="listCnt" name="listCnt">
									<c:forEach var="listCount" items="${dietListListCountSearch}">
										<option value="${listCount.value}">${listCount.key}</option>
									</c:forEach>
							</select></li>
						</ul>
						<button style="margin-right: 30px; width: 100px; height: 60px;"
							onclick="search()">검색</button>
					</div>
				</div>

				<div>
					<table class="dietTable">
						<thead>
							<tr>
								<th>날짜</th>
								<th>요일</th>
								<th>식당</th>
								<th>식사구분</th>
								<th>요리</th>
								<th>재료</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${empty dietdatas}">
								<tr>
									<td colspan="6">식단 데이터가 없습니다.</td>
								</tr>
							</c:if>
							<c:if test="${dietdatas ne null }">
								<c:forEach var="dietdata" items="${dietdatas}">
									<tr>
										<td>${dietdata.ymd}</td>
										<td>${dietdata.weekday}</td>
										<td>${dietdata.restaurantName}</td>
										<td>${dietdata.mealTime}</td>
										<td>${dietdata.mealName}</td>
										<td>${dietdata.ingredimentName}</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			<br>
		</div>
		<div class="page">
			<table>
				<thead>
					<tr>
						<c:if test="${pagedata.beforePageList }">
							<th><a
								href="dietListPage.do?currentPage=${pagedata.listFirstPage - 1}&searchStartDate=${searchdata.searchStartDate}&searchLastDate=${searchdata.searchLastDate}&restaurantName=${searchdatarestaurantName}&mealTime=${searchdata.mealTime}&listCount=${searchdata.listCount}">&lt;&lt;</a></th>
						</c:if>
						<c:if test="${pagedata.beforePage}">
							<th><a
								href="dietListPage.do?currentPage=${pagedata.currentPage - 1}&searchStartDate=${searchdata.searchStartDate}&searchLastDate=${searchdata.searchLastDate}&restaurantName=${searchdatarestaurantName}&mealTime=${searchdata.mealTime}&listCount=${searchdata.listCount}">&lt;</a></th>
						</c:if>
						<c:forEach var="pageCount" begin="${pagedata.listFirstPage }"
							end="${pagedata.listLastPage }">
							<th><c:if test="${pagedata.currentPage eq pageCount}">
									<a href="#" class="currentPage" style="color: blue">${pageCount}
									</a>
								</c:if> <c:if test="${pagedata.currentPage ne pageCount}">
									<a
										href="dietListPage.do?currentPage=${pageCount}&searchStartDate=${searchdata.searchStartDate}&searchLastDate=${searchdata.searchLastDate}&restaurantName=${searchdatarestaurantName}&mealTime=${searchdata.mealTime}&listCount=${searchdata.listCount}"
										class="notCurrentPage">${pageCount} </a>
								</c:if></th>
						</c:forEach>
						<c:if test="${pagedata.nextPage}">
							<th><a
								href="dietListPage.do?currentPage=${pagedata.currentPage + 1}&searchStartDate=${searchdata.searchStartDate}&searchLastDate=${searchdata.searchLastDate}&restaurantName=${searchdatarestaurantName}&mealTime=${searchdata.mealTime}&listCount=${searchdata.listCount}">></a></th>
						</c:if>
						<c:if test="${pagedata.nextPageList}">
							<th><a
								href="dietListPage.do?currentPage=${pagedata.listLastPage + 1}&searchStartDate=${searchdata.searchStartDate}&searchLastDate=${searchdata.searchLastDate}&restaurantName=${searchdatarestaurantName}&mealTime=${searchdata.mealTime}&listCount=${searchdata.listCount}">>></a></th>
						</c:if>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<script>
		function updateEndDateMinMax() {

			var startDate = new Date(document.getElementById("startDate").value);
			document.getElementById("endDate").value = "";

			document.getElementById("endDate").min = document.getElementById("startDate").value;
		}

		function search() {
			var startDate = document.getElementById("startDate").value;
			var endDate = document.getElementById("endDate").value;
			var restaurantName = document.getElementById("restaurantName").value;
			var mealType = document.getElementById("mealType").value;
			var listCnt = document.getElementById("listCnt").value;

			// 유효성 검사
			if (startDate === "" || endDate === "") {
				alert("날짜 선택하세요.");
				return;
			}
			if (listCnt === "") {
				alert("목록갯수를 선택하세요.");
				return;
			}

			// 페이지 이동
			window.location.href = 'dietListPage.do?searchStartDate='
					+ startDate + '&searchLastDate=' + endDate
					+ '&restaurantName=' + restaurantName + '&mealTime='
					+ mealType + '&listCount=' + listCnt;
		}
		window.onload = function() {
			document.getElementById("startDate").value = "${searchdata.searchStartDate}"
			document.getElementById("endDate").value = "${searchdata.searchLastDate}"
			document.getElementById("restaurantName").value = "${searchdata.restaurantName}"
			document.getElementById("mealType").value = "${searchdata.mealTime}"
			document.getElementById("listCnt").value = "${searchdata.listCount}"
			document.getElementById("endDate").min = document.getElementById("startDate").value;
		}
	</script>

</body>
</html>