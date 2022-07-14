<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="utf-8" class="light-style layout-menu-fixed" dir="ltr"
	data-theme="theme-default" data-assets-path="assets/"
	data-template="vertical-menu-template-free">
<head>
	<meta charset="utf-8" />
<title>쿠폰 마스터 상세</title>
<jsp:include page="A_head.jsp"/>
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
						<h4 class="fw-bold py-3 mb-4">쿠폰 마스터 상세</h4>
						<div class="card">
							<!-- Account -->
							<div class="card-body">
							
							<table class="table">
								<tr><th>쿠폰번호</th><td>${couponMaster.coupno}</td></tr>
								<tr><th>쿠폰명</th><td>${couponMaster.coup_name}</td></tr>
								<tr><th>쿠폰유형</th>
								<td>
									<c:if test="${couponMaster.coup_type == 1}">
											할인율
									</c:if>
									<c:if test="${couponMaster.coup_type == 2}">
										가격
									</c:if>	
								</td>
								</tr>
								<tr><th>결제금액할인율</th><td>${couponMaster.coup_dc_rate}</td></tr>
								<tr><th>할인가격</th><td>${couponMaster.coup_dc_price}</td></tr>
								<tr><th>조건가격</th><td>${couponMaster.coup_req_price}</td></tr>
								<tr><th>유효기간(일)</th><td>${couponMaster.coup_expdate}</td></tr>
								<tr><td colspan="2">
							    <input type="button" value="목록" 
									onclick="location.href='coupMasList'">
								<input type="button" value="수정" 
									onclick="location.href='coupMasUpdateForm?coupno=${couponMaster.coupno}'">
								<input type="button" value="삭제" 
									onclick="location.href='coupMasDelete?coupno=${couponMaster.coupno}'"></td></tr>
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