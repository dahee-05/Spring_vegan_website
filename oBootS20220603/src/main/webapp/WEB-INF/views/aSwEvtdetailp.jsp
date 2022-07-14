<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html lang="utf-8" class="light-style layout-menu-fixed" dir="ltr"
	data-theme="theme-default" data-assets-path="assets/"
	data-template="vertical-menu-template-free">
<head>
	<meta charset="UTF-8">
	<title>어드민 | 비숲</title>
	<jsp:include page="A_head.jsp"/>

<style>
table{ border-collapse: separate;
		border-spacing : 5px;
		margin-left: 5px 
		/* padding: 50px; */
		}

	th {
	padding: 20px;
 
    }
 
	input[type=button] { border:solid 2px blue }	
</style>
	
</head>

<body>
	<!-- Layout wrapper -->
	<div class="layout-wrapper layout-content-navbar">
		<div class="layout-container">
			<jsp:include page="A_menu.jsp"/>

			<!-- Layout container -->
			<div class="layout-page">

				<!-- Content wrapper -->
				<div class="content-wrapper">

					<!-- Content -->
					<div class="container-xxl flex-grow-1 container-p-y">
						<h4 class="fw-bold py-3 mb-4">할인 이벤트 상세</h4>
						<div class="card">
							<!-- Account -->
							<div class="card-body">
								
<%-- 	<h4>
			<c:if test="${faq.board_category == 1}">회원정보 문의</c:if>
			<c:if test="${faq.board_category == 2}">주문 및 결제 문의</c:if>
			<c:if test="${faq.board_category == 3}">배송문의</c:if>
			<c:if test="${faq.board_category == 4}">교환 및 반품 문의</c:if>
		
	</h4>  --%>
<table>	

<tr><th>이벤트번호</th>	<td>${event.evt_no }</td></tr>	
	<tr><th>이벤트명</th>	<td>${event.evt_name }</td></tr>
	<tr><th>상품할인율</th>	<td>${event.dc_rate }%</td></tr>
	 <tr><th>시작일</th><td><fmt:parseDate var="evt_startDate" value="${event.evt_start }" pattern="yyyy-MM-dd HH:mm:ss" />
      <fmt:formatDate value="${evt_startDate}" pattern="yyyy.MM.dd" /></td></tr>
     <tr><th>마감일</th><td><fmt:parseDate var="evt_endDate" value="${event.evt_end }" pattern="yyyy-MM-dd HH:mm:ss" />
      <fmt:formatDate value="${evt_endDate}" pattern="yyyy.MM.dd" /></td></tr>
	<%-- <tr><th>시작일</th><td><fmt:formatDate pattern = "yy/MM/dd" value="${event.evt_start }"/></td></tr>
	<tr><th>마감일</th><td><fmt:formatDate pattern = "yy/MM/dd" value="${event.evt_end }"/></td></tr> --%>
	<%-- <tr><th>등록일</th>	<td>${event.evt_regDate }</td></tr> --%>
	<tr><th>등록일</th><td><fmt:formatDate pattern = "yy/MM/dd" value="${event.evt_regDate }"/></td></tr>
	<tr><th>이벤트설명</th>	<td>${event.evt_exp }</td></tr>
	<tr><th>유의사항</th>	<td>${event.evt_notice }</td></tr>
	<tr><th>이벤트 이미지</th>	<td>
			<div class="event_img">
			<img alt="이벤트이미지" width="600px" height="400px" src="../upload/event/${event.evt_img}">
			</div></td></tr>
	<tr><th>이벤트상태</th>	
	<td>
				<c:if test="${event.evt_sts == 0}">
				종료				
				</c:if>
				<c:if test="${event.evt_sts == 1}">
				진행중				
				</c:if>
				</td></tr>
	<tr><th>이벤트유형</th>
	<td>
				<c:if test="${event.evt_type == 1}">
				할인 이벤트				
				</c:if>
				<c:if test="${event.evt_type == 2}">
				쿠폰 이벤트				
				</c:if>
	</td></tr>
	<tr><th>상품번호</th><th>상품이름</th></tr>
		
	<c:forEach var="product" items="${p_list }">
		<tr>
			<td style="text-align: center; padding-right: 38px;">${product.prodno }</td>
			<td>[${product.brand_name }]${product.prod_name }</td>
		</tr>
	</c:forEach>
	
	<tr style="text-align: right;"><td colspan="6">
	    <input type="button" value="목록" 
			onclick="location.href='aSwEventList'">
		<input type="button" value="수정" 
			onclick="location.href='aSwEventupdateFormp?evt_no=${event.evt_no}'">
		<input type="button" value="삭제" 
			onclick="location.href='aSwEventdelete?evt_no=${event.evt_no}'"></td></tr>
</table>
							
							</div>
							<!-- /Account -->
						</div>
					</div>
					<!-- / Content -->

					<div class="content-backdrop fade"></div>
				</div>
				<!-- Content wrapper -->
			</div>
			<!-- / Layout page -->
		</div>

		<!-- Overlay -->
		<div class="layout-overlay layout-menu-toggle"></div>
	</div>
	<!-- / Layout wrapper -->

	<jsp:include page="A_footer.jsp"/>
</body>
	
</html>