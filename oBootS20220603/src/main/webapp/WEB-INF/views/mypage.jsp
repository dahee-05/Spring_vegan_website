<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 메인화면</title>

<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/mypage_contents.css" rel="stylesheet" type="text/css">
<link href="css/mypage.css" rel="stylesheet" type="text/css">
<link href="css/mypagejh.css" rel="stylesheet" type="text/css">
<style type="text/css">
.sts td{
	width: 150px;
}
</style>
</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/header.jsp"/>
<!-- ----------------------------------------------------------------------main시작----------------------------------- -->
		<div class="margin_wrap">
			<div class="main clearfix">
			<jsp:include page="/WEB-INF/views/mySnb.jsp"/>
<!-- ----------------------------------------------------------------------여기에 코딩 시작----------------------------------->
				<section class="sectionmy">
					<div>
						<h2>나의 쇼핑 정보</h2>
						<div class="contents_wrap">
						<!-- 작업 영역 -->
							<div class="selbox">
								<table>
									<tr class="numb">
										<td>${selCount }</td>
										<td>${canCount }</td>
										<td>${refingCount }</td>
										<td>${refCount }</td>
										<td>${delCount }</td>
										<td>${selFixCount }</td>
									</tr>
									<tr class="sts">
										<td>주문완료 > </td>
										<td>취소완료 > </td>
										<td>환불진행중 > </td>
										<td>환불완료 > </td>
										<td>배송완료 > </td>
										<td>구매확정</td>
									</tr>
								</table>
							</div>
							<div class="coupbox">
								<h3><a href="userCoupList">나의 쿠폰</a></h3> <span class="span">${coupCount }</span>
							</div>
							<div class="milebox">
								<h3>나의 마일리지</h3> <span class="span"><fmt:formatNumber value="${mileTot }" pattern="###,###,###"/> 원</span>
							</div>
						<!-- 작업 영역 -->
						</div>
					</div>
			
				</section>	
			</div>
		</div>
		<!-- ----------------------------------------------------------------------여기에 코딩 끝------------------------------------>
		<!-- ----------------------------------------------------------------------main끝----------------------------------- -->
		
		<jsp:include page="/WEB-INF/views/footer.jsp"/>
		
	</div> <!-- wrap -->
	
</body>
</html>