<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 화면</title>
<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/mypage_contents.css" rel="stylesheet" type="text/css">
<!-- <link href="css/product.css" rel="stylesheet" type="text/css"> -->
<script src="js/jquery.js"></script>
</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/header.jsp"/>
		<!-- ----------------------------------------------------------------------main시작----------------------------------- -->
<%-- 		<div class="margin_wrap">
			<div class="main clearfix">
			<jsp:include page="/WEB-INF/views/mySnb.jsp"/> --%>
		<!-- ----------------------------------------------------------------------여기에 코딩 시작----------------------------------->
				<div class="margin_wrap">
				<div class="main">
				
				<h2 id="event" style="text-align: center; margin: 60px 0px 20px; padding: 0px 0px 20px;
				font-size: 24px; color: #5F8350; border-bottom: outset; position: relative; ">
				이벤트 리스트</h2>
				<c:forEach var="event" items="${listEventU }">
					<li class="prod_list">
					<div style="text-align: center; margin-top: 40px;">
						<c:if test="${event.evt_type == 1}">
							<a href="uSwEvtdetailp?evt_no=${event.evt_no}">
								<img alt="이벤트 이미지"  width="700px" height="300px;" src="../upload/event/${event.evt_img }">
							</a>				
						</c:if>
						
						<c:if test="${event.evt_type == 2}">
							<a href="uSwEvtdetailc?evt_no=${event.evt_no}">
								<img alt="이벤트 이미지"  width="700px" height="300px;" src="../upload/event/${event.evt_img }">
							</a>				
						</c:if>
					</div>
					<div style="text-align: center; margin-top: 10px; font-weight: 700;">
						<p class="이벤트 제목">
						<c:if test="${event.evt_type == 1}">
							<a href="uSwEvtdetailp?evt_no=${event.evt_no}">${event.evt_name }</a>				
						</c:if>
						<c:if test="${event.evt_type == 2}">
							<a href="uSwEvtdetailc?evt_no=${event.evt_no}">${event.evt_name }</a>				
						</c:if>
						</p>
					</div>
					<div style="text-align: center; margin-top: 5px; font-weight: 100;">
						<p class="이벤트 기간">
					<span>
					<fmt:parseDate var="evt_startDate" value="${event.evt_start }" pattern="yyyy-MM-dd HH:mm:ss" />
                    <fmt:formatDate value="${evt_startDate}" pattern="yyyy.MM.dd" />&nbsp;~&nbsp;</span>
                    <span>
                    <fmt:parseDate var="evt_endDate" value="${event.evt_end }" pattern="yyyy-MM-dd HH:mm:ss" />
                    <fmt:formatDate value="${evt_endDate}" pattern="yyyy.MM.dd" />
			   		</span>	
			   			</p>
						
					</div>
					</li>
				</c:forEach>	
			
						
						
					
				</div>
		

				</div>	
		
		<!-- ----------------------------------------------------------------------여기에 코딩 끝------------------------------------>
		<!-- ----------------------------------------------------------------------main끝----------------------------------- -->
		
		<jsp:include page="/WEB-INF/views/footer.jsp"/>
		
	</div> <!-- wrap -->
	
</body>
</html>