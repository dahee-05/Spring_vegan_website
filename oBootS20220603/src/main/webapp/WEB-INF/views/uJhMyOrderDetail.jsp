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
<title>마이페이지(구매내역 상세보기)</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/httpRequest.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<link href="css/mypage.css" rel="stylesheet" type="text/css">
<link href="css/myorderDetail.css" rel="stylesheet" type="text/css">

<style type="text/css">
span{
	font-weight: bold;
}


    padding-top: 10px;
    padding-bottom: 10px;




</style>

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
					<h2>주문상세정보</h2>
					<br>
					<!-- 작업 영역 -->
					<hr>
					<div class="status">
						<span class="odnum">주문번호 : ${dlistC.payno}</span>
						<span class="oddate">주문일 : <fmt:formatDate value="${dlistC.pay_date}" pattern="yyyy-MM-dd"/></span>
					</div>
					<hr>
					
					<c:forEach var="dlist" items="${dlist }" varStatus="status">
						<div class="infobox">
							<div class="thm_img">
								<img class='thm_rimg' alt="img" src="../upload/${dlist.th_img }">
							</div>
							
							<div class="prod_info">
								<ul>
									<li class="listTitle">[${dlist.brand_name}] ${dlist.prod_name}</li>
									<li class="listQty">수량 : ${dlist.buy_qty}개</li>
									<li class="listMon"><fmt:formatNumber value= "${dlist.prod_price*(1-(dlist.dc_rate*0.01))*dlist.buy_qty}" pattern="###,###,###"/>${pages}원</li>
								</ul>
							</div>
						</div>
					</c:forEach>
					<br>
					<br>
					<br>
					<div class="paymentbox">
						<h3>결제/금액 정보</h3>
						<br>
						<hr>
						<ul class="paymentinfo" style="list-style: none;">
							<li><span>상품금액</span> : <fmt:formatNumber value="${dlistC.org_amount }" pattern="###,###,###"/>원</li>
							<li><span>배송비</span> : <fmt:formatNumber value="${dlistC.delivery_fee }" pattern="###,###,###"/>원</li>
								<c:choose>
								<%-- 1. 할인 쿠폰이 적용됬을 때 --%>
									<c:when test="${not empty dcAmount.coupno  }"> 
										<li><span>쿠폰할인</span> : 
											<c:choose>
											<%-- 1-1 할인율 적용될 때 --%>
												<c:when test="${dcAmount.coup_dc_rate>0 }"> 
													-<fmt:formatNumber value= "${dcAmount.org_amount *(dcAmount.coup_dc_rate*0.01)}" pattern="###,###,###"/>${pages }원
												</c:when>
											<%-- 1-2 금액 할인율 적용될 때 --%>
												<c:when test="${dcAmount.coup_dc_price>0 }"> 
													-<fmt:formatNumber value="${dcAmount.coup_dc_price }" pattern="###,###,###"/>원
												</c:when>
								<%-- 2. 할인 쿠폰이 없을 때 --%>	
												<c:otherwise>
													0원
												</c:otherwise>
											</c:choose>
										</li>
									</c:when>
								</c:choose>
							<li><span>마일리지 사용</span> : -<fmt:formatNumber value="${dlistC.mile_use }" pattern="###,###,###"/>원</li>
							<li><span>구매적립</span> : <fmt:formatNumber value="${dlistC.mile_save }" pattern="###,###,###"/>원</li>
						</ul>
						<div class="real_amount">
							<h3>총 주문 금액 : <fmt:formatNumber value="${dlistC.real_amount }" pattern="###,###,###"/>원</h3>
						</div>
					</div>	
					<div  class="delivinfo" >
					<h3>배송지 정보</h3>
					<br>
					<hr>
						<ul style="list-style: none;">
							<li><span>수령인</span> : ${dlistC.rec_name }</li>
							<li><span>배송지 주소</span> : ${dlistC.del_address }&ensp;${dlistC.del_daddress }</li>
							<li><span>연락처</span> : ${dlistC.rec_phone }</li>
						</ul>
					</div>			
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
