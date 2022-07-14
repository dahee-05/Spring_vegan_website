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

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/httpRequest.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/pwChange.css" rel="stylesheet" type="text/css">
<link href="css/mypage_contents.css" rel="stylesheet" type="text/css">
<link href="css/mypage.css" rel="stylesheet" type="text/css">
<link href="css/mypagejh.css" rel="stylesheet" type="text/css">



<title>개인정보확인(마이페이지)</title>
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
					<h2>개인정보 변경</h2>
					<input type="hidden" id="user_id" value="${id }">
					<br>
					<hr>
					<br>
					<!-- 작업 영역 -->
						<form action="uMyInfoUpdate">
					
							<h3 class="hh3">회원정보</h3>
								<br>
								<div class="input-group mb-3">
									<span class="input-group-text" id="inputGroup-sizing-default">아이디</span>
									<input type="text" value="${member.id}" name="id" placeholder="${member.id}" readonly="readonly" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
								</div>
								<div class="input-group mb-3">
									<span class="input-group-text" id="inputGroup-sizing-default">이름</span>
									<input type="text" value="${member.name}" name="name" placeholder="${member.name}" readonly="readonly" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
								</div>
								<div class="input-group mb-3">	
									<span class="input-group-text" id="inputGroup-sizing-default">이메일</span>
									<input  type="email" value="${member.email}" name="email" placeholder="${member.email}" required="required" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
								</div>
								<div class="input-group mb-3">	
									<span class="input-group-text" id="inputGroup-sizing-default">전화번호</span>
									<input type="text" value="${member.tel}" name="tel" placeholder="${member.tel}" pattern="[0-9]{11}" required="required" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
								</div>
							
							<hr>
						
							<h3 class="hh3">기본배송정보</h3>
								<br>
								<div class="input-group mb-3">
									<span class="input-group-text" id="inputGroup-sizing-default">우편번호</span>
									<input type="text" value="${member.post_code}" name="post_code" pattern="[0-9]{5}"  placeholder="${member.post_code}"  class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
								</div>
								<div class="input-group mb-3">
									<span class="input-group-text" id="inputGroup-sizing-default">주소</span>
									<input  type="text" value="${member.address}" name="address" placeholder="${member.address}"  class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
								</div>
								<div class="input-group mb-3">
									<span class="input-group-text" id="inputGroup-sizing-default">상세주소</span>
									<input type="text" value="${member.det_address}" name="det_address" placeholder="${member.det_address}"  class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
								</div>
							
							<hr>
							
							<h3 class="hh3">구독계좌정보</h3>
							<br>
								<div class="input-group mb-3">
									<span class="input-group-text" id="inputGroup-sizing-default">계좌은행</span>
									<input type="hidden" name="bank" value="${member.bank}" readonly="readonly"  class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
										<c:choose>
											<c:when test="${member.bank==0}">
												<input  type="text" value="" readonly="readonly"  class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
											</c:when>										
											<c:when test="${member.bank==1}">
												<input type="text" value="신한은행" readonly="readonly"  class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
											</c:when>
											<c:when test="${member.bank==2}">
												<input type="text" value="국민은행" readonly="readonly"  class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
											</c:when>
											<c:when test="${member.bank==3}">
												<input  type="text" value="우리은행" readonly="readonly"  class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
											</c:when>
											<c:when test="${member.bank==4}">
												<input  type="text" value="카카오뱅크" readonly="readonly"  class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
											</c:when>										
										</c:choose>
									</div>
									<div class="input-group mb-3">
									
										<span class="input-group-text" id="inputGroup-sizing-default">계좌번호</span>
										<input  type="text" placeholder="${member.account}" name="account" value="${member.account}" readonly="readonly"  class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
									
										<input id="sub" type="submit" value="개인정보변경"> 
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