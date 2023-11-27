<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>주간 달력</title>
<style>
table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: center;
}

th {
	background-color: #f2f2f2;
}

.current-day {
	background-color: #4CAF50;
	color: white;
}

@media print {
	button {
		display: none !important;
	}
	#header {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            background-color: white; /* 페이지에 표시되는 다른 내용과 겹치지 않도록 배경색 지정 */
            magin-bottom: 10px;
        }
}

@page {
	size: A4 landscape;
}
}
</style>

<div id='header' style="border: 1px solid">
	<h2 style="text-align: center">주간 식단표</h2>
	<span style="display: flex; justify-content: space-around">
		<h5>식당명: 여민관</h5>
		<h5>기간: 2023-11-15 ~ 2023-11-18</h5>
	</span>
</div>

<script>
        function printPage() {
            window.print();
        }
    </script>

</head>
<body>

	<br>
	<button onclick="printPage()">인쇄</button>

	<table id="weeklyCalendar">
		<thead>
			<tr>
				<th id="categorization" style="width: 5%">구분</th>
				<th>월</th>
				<th>화</th>
				<th>수</th>
				<th>목</th>
				<th>금</th>
				<th>토</th>
				<th>일</th>
			</tr>
		</thead>
		<tbody id="calendarBody">
			<c:forEach var="category" begin="1" end="4">
				<tr>
					<td>category</td>
					<c:forEach var="diet" begin="10" end="16">
						<td><c:forEach begin="1" end="2">
           					diet
           					<br>
								<c:forEach var="ingtediment" begin="1" end="3">
           						ingrediment
           						<br>
								</c:forEach>
							</c:forEach></td>
					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
			<ul>
				<li style="width:200px">검색 기간 <input type="date" id="startDate" value="${dateType1}" onchange="updateEndDateMinMax()"></li>
				<li> ~  <input type="date" id="endDate" min="${dateType1}" max="${dateType1}"></li>
				<li>식당명 <select id="dropdown" name="dropdown">
							<option value="" disabled selected>전체</option>
							<option value="option1">여민관</option>
							<option value="option2">춘추관</option>
						</select>
				</li>
				<li>식사 구분 <select id="dropdown" name="dropdown">
								<option value="option1">전체</option>
								<option value="option2">조식</option>
								<option value="option2">중식</option>
								<option value="option2">석식</option>
								<option value="option2">간식</option>
							</select>
				</li>
				<li>목록 갯수</li>
			</ul>
		</div>
</body>
</html>