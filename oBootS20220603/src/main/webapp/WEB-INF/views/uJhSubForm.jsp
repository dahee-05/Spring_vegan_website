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
<title>마이페이지</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/httpRequest.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<link href="css/mypage.css" rel="stylesheet" type="text/css">
<link href="css/subForm.css" rel="stylesheet" type="text/css">


<title>구독 신청</title>
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
					<div id="vegimg"><img alt="" src="images/subsvegan.jpg" ></div>
					<h1 class="title">월 5,900원으로  환경을 위한 소비를 실천하세요</h1><p>
					<br>
					<br>
					<h3 class="subtitle">비숲의 모든 제품을 무료배송으로 만나볼 수 있습니다</h3>
					<br>
					<br>
					
					<form action="jhSubSucU" method="post">
					<input type="hidden" name="id" value="${id }">
					<input type="hidden" name="subs" value="${subs }">
					<div class="subsinfobox">
						<ul>
							<li>
								<span class="bank">
									<label> 정기구독 출금 계좌
										<select name="bank" required="required">
											<option value="1" selected="selected" >신한</option>
											<option value="2" >국민</option>
											<option value="3" >우리</option>
											<option value="4" >카카오</option>
										</select>
									</label>
								</span>
								<span class="account">
									<input type="text" name="account" id="accinput" placeholder="예)110-322-1111" required="required">
								</span>	
							</li>
							<li><button onclick="jhSubSucU" id="subssubmit">등록</button></li>
						</ul>
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