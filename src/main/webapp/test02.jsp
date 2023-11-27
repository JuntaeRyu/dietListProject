<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>�ְ� �޷�</title>
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
            background-color: white; /* �������� ǥ�õǴ� �ٸ� ����� ��ġ�� �ʵ��� ���� ���� */
            magin-bottom: 10px;
        }
}

@page {
	size: A4 landscape;
}
}
</style>

<div id='header' style="border: 1px solid">
	<h2 style="text-align: center">�ְ� �Ĵ�ǥ</h2>
	<span style="display: flex; justify-content: space-around">
		<h5>�Ĵ��: ���ΰ�</h5>
		<h5>�Ⱓ: 2023-11-15 ~ 2023-11-18</h5>
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
	<button onclick="printPage()">�μ�</button>

	<table id="weeklyCalendar">
		<thead>
			<tr>
				<th id="categorization" style="width: 5%">����</th>
				<th>��</th>
				<th>ȭ</th>
				<th>��</th>
				<th>��</th>
				<th>��</th>
				<th>��</th>
				<th>��</th>
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
				<li style="width:200px">�˻� �Ⱓ <input type="date" id="startDate" value="${dateType1}" onchange="updateEndDateMinMax()"></li>
				<li> ~  <input type="date" id="endDate" min="${dateType1}" max="${dateType1}"></li>
				<li>�Ĵ�� <select id="dropdown" name="dropdown">
							<option value="" disabled selected>��ü</option>
							<option value="option1">���ΰ�</option>
							<option value="option2">���߰�</option>
						</select>
				</li>
				<li>�Ļ� ���� <select id="dropdown" name="dropdown">
								<option value="option1">��ü</option>
								<option value="option2">����</option>
								<option value="option2">�߽�</option>
								<option value="option2">����</option>
								<option value="option2">����</option>
							</select>
				</li>
				<li>��� ����</li>
			</ul>
		</div>
</body>
</html>