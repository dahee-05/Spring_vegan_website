<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="js/jquery.js"></script>
</head>
<body>
	<div class = "container">
	    <table class = "table table-striped">
	        <thead>
	        <tr>
	            <th>번호</th>
	            <th>방 이름</th>
	            <th>입장버튼</th>
	        </tr>
	        </thead>
	        <tbody>
		        <c:forEach var="room" items="${rooms}">
		        	<td>${room.roomId}</td>
		            <td>${room.name}</td>
		            <td>
		                <a class = "btn btn-primary" href = "/rooms/${room.roomId}">입장</a>
		            </td>
		        </c:forEach>
	        </tbody>
	    </table>
	    <a class = "btn btn-primary pull-right" href = "/new">새로 만들기</a>
	</div>

</body>
</html>