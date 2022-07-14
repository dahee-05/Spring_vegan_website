<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	String context = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="utf-8" class="light-style layout-menu-fixed" dir="ltr"
	data-theme="theme-default" data-assets-path="assets/"
	data-template="vertical-menu-template-free">
<head>
<meta charset="UTF-8">
<title>배송/판매(판매목록)</title>
<jsp:include page="A_head.jsp"/>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/httpRequest.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<style type="text/css">
	.search th{
		float: right;
	}
	
	.miainT th{
		text-align: center;
    	padding-right: 10px;
    	padding-left: 0px;
		
	}
	.table-borderless a:hover{
		color: red;
		font-weight: bold;
	}

	.table main{
		border-bottom: 1px;
		border-left: none;
		border-right: none;
		border-top: none;
	}
	
	#px td{
    	padding-right: 10px;
    	padding-left: 0px;	
	}
	
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
						<h4 class="fw-bold py-3 mb-4">배송/판매(판매목록)</h4>
						<div class="card">
							
<!---------------------------------------------------- Account ------------------------------------------------------------------------------------------------------------->
							<div class="card-body">
								<table class="table table-borderless" border="1" style="margin-bottom: 40px; text-align: center;">
									<tr>
										<td><a href="jhDelivListA" >배송처리</a></td>
										<td><a href="jhSelListA">판매목록</a></td>
									</tr>
								</table>
								<form action="jhSelSearchListA">
									<input type="hidden" name="currentPage" value="${pg.currentPage }">
										<table class="table table-borderless" border="1">
										<tr class="search">
											<th>검색어</th>
											<td>
												<select name="search" style="height: 29px;">
													<option value="s_payno">주문번호</option>
													<option value="s_name">주문자 이름</option>
												</select>
												<input type="text" name="keyword1">
												<%-- 초기화 하려고 뺌 새로고침 할 때 가지고 가려면 있어야함 value=${keyword1 } --%>
											</td>
										</tr>
										
										<tr class="search">
											<th>결제일</th>
											<td>
												<input type="date" name="sdate">
												<input type="date" name="edate">
											</td>
										</tr> 
										</table>
										<div style="text-align: center;margin-top: 10px;" >
											<input type="submit" value="검색" class="btn btn-primary">
											<button onclick="location.href='jhSelListA'" class="btn btn-light">초기화</button>
										</div>
									</form>	
									<br>
									<br>
									<br>								
									<c:choose>	
										<c:when test="${not empty listSel }">
											<table class="table main">
												<tr class="miainT">
													<th>번호</th><th>주문일(결제일)</th><th>주문번호</th><th>운송장번호</th><th>주문자</th><th>상품명</th><th>수량</th><th>배송지정보</th><th>총결제금액</th><th>주문상태</th>
												</tr>
												
												<c:forEach var="list" items="${listSel}" varStatus="status">
													<!--1. status.index == 0 처음 시작일 때 무조건   보여줌 -->
											    	<tr id="tr${list.payno }" id="px">	
													    <c:if test="${status.index == 0}">	
														<c:set var="num" value="${pg.start }"></c:set>
												    		<td>${num }</td>							
											    			<td><fmt:formatDate value="${list.pay_date }" pattern="yyyy-MM-dd"/></td>
															<td>${list.payno }</td>
															<td>${list.deliveryno }</td>
															<td>${list.name }</td>
															<td>${list.prod_name }</td>
													        <td>${list.buy_qty }</td>
															<td>
																수령인 : ${list.rec_name } <br>
																배송지 정보 : ${list.del_address } <br>
																${list.del_daddress }
															</td>
															<td><fmt:formatNumber value="${list.real_amount }" pattern="###,###,###"/></td>
															<c:choose>
																<c:when test="${list.pay_sts==5}">
																	<td>배송완료</td>
																</c:when>
																<c:when test="${list.pay_sts==6}">
																	<td>구매확정</td>
																</c:when>
															</c:choose>
															<!-- 다시 다음 줄 비교를 위해 -->
															<c:set var="payno" value="${list.payno}" />
															<c:set var="num" value="${num+1}"></c:set>	
														</c:if>
													</tr>
													
													<c:choose>
											     	<c:when test="${status.index > 0 }">
											        <!-- 2-1. 처음 값이 아닐 때 payno중복되면 안보여줌 -->
												    	<c:if test="${list.payno eq payno}">
													        <tr id="tr${list.payno }" id="px">	
														    		<td></td>   
																	<td></td>
																	<td></td>
																	<td></td>
																	<td></td>
																	<td>${list.prod_name }</td>
														            <td>${list.buy_qty }</td>
																	<td></td>
																	<td></td>
																	<td></td>
														      	<c:set var="payno" value="${list.payno}"/>
														      </tr>
														 </c:if>
												        <!-- 2-2. 처음 값이 아닐 때 중복되지 않는 payno일 땐 보여줌 -->
												    	<c:if test="${list.payno ne  payno}">
													    	<tr id="tr${list.payno }" id="px">
													    		<td>${num }</td>   
																<td><fmt:formatDate value="${list.pay_date }" pattern="yyyy-MM-dd"/></td>
																<td>${list.payno }</td>
																<td>${list.deliveryno }</td>
																<td>${list.name }</td>
																<td>${list.prod_name }</td>
														        <td>${list.buy_qty }</td>
																<td>
																	수령인 : ${list.rec_name } <br>
																	배송지 정보 : ${list.del_address } <br>
																	${list.del_daddress }
																</td>
																<td><fmt:formatNumber value="${list.real_amount }" pattern="###,###,###"/></td>
																<c:choose>
																	<c:when test="${list.pay_sts==5}">
																		<td>배송완료</td>
																	</c:when>
																	<c:when test="${list.pay_sts==6}">
																		<td>구매확정</td>
																	</c:when>
															</c:choose>
																<!-- 다시 다음 줄 비교를 위해 -->
																<c:set var="payno" value="${list.payno}" />
																<c:set var="num" value="${num+1}"></c:set>
															</tr>
														</c:if>
													</c:when>
													</c:choose>
												</c:forEach>
											</table>
										</c:when>
										<c:otherwise>
											검색 결과 없음
										</c:otherwise>
									</c:choose>
							</div>
<!---------------------------------------------------- Account ------------------------------------------------------------------------------------------------------------->
						
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