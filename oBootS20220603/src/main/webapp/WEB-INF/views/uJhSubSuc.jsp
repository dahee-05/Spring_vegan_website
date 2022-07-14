<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	String context = request.getContextPath();
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구독성공(구독정보)</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/httpRequest.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<link href="css/mypage.css" rel="stylesheet" type="text/css">

<style type="text/css">
	.title{
    	padding-bottom: 20px;
		
	}
	
	.imgafter{
		float: left;
	}
	
	#subsend{
	 	margin-top: 10px;
	    height: 30px;
	    width: 50px;
	    border-color: white;
	    background-color: lightgray;
	}
	
	#subsend:hover{
		background-color: green;
		color: white;
		font-weight: bold;
	}
	.subsinfobox{
		float: left;
	    padding-left: 20px;
	}
	
</style>


<title>구독 정보</title>
</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/header.jsp"/>
<!-- ----------------------------------------------------------------------main시작----------------------------------- -->
		<div class="margin_wrap">
			<div class="main clearfix">
			<jsp:include page="/WEB-INF/views/mySnb.jsp"/>
<!-- ----------------------------------------------------------------------드롭다운 메뉴 시작---------------------------------->
<!-- ----------------------------------------------------------------------여기에 코딩 시작----------------------------------->
				<section class="sectionmy">
					<!-- 작업 영역 -->
					<form action="jhSubCanU">
					<input type="hidden" name="id" value="${member.id }">
						<h1 class="title">나의 구독 정보</h1>
						
						<div class="imgafter">
							<img alt="" src="images/subsafter.jpg" width="300px" height="200px">
						</div>	
						<div class="subsinfobox">
							<br>
							<br>
							<h3>나의 구독 시작일 : <fmt:formatDate value="${member.sub_start }" pattern="yyyy년 MM월 dd일"/></h3>
							<br>
							<br>
							<h3>나의 구독 만료일 : <fmt:formatDate value="${member.sub_exp }" pattern="yyyy년 MM월 dd일"/></h3>
							<br>
							<br>
						<input type="submit" value="구독해지" id="subsend" style="width: 68px;">
						</div>
					</form>
					<!-- 작업 영역 -->
				</section>	
			</div>
		</div>

<!-- ----------------------------------------------------------------------여기에 코딩 끝---------------------------------->
<!-- ----------------------------------------------------------------------main끝----------------------------------- -->
	<jsp:include page="/WEB-INF/views/footer.jsp"/>
	</div> <!-- wrap -->
</body>
</html>