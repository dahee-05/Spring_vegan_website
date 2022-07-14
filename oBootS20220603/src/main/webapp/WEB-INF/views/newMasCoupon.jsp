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
<title>신규 마스터 쿠폰 등록</title>
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
						<h4 class="fw-bold py-3 mb-4">신규 마스터 쿠폰 등록</h4>
						<div class="card">
							<!-- Account -->
							<div class="card-body">
								
							<form action="newMasCoupInsert" method="post">
								<table class="table">
									<tr><th>쿠폰번호</th><td>등록시 자동 지정 됨</td></tr>
									<tr><th>쿠폰명</th><td><input type="text" name="coup_name" required="required" value=""></td></tr>
									<tr><th>쿠폰유형</th><td><input type="number" name="coup_type" required="required" value=""></td></tr>
									<tr><th>결제금액할인율</th><td><input type="number" name="coup_dc_rate" required="required" value=""></td></tr>
									<tr><th>할인가격</th><td><input type="number" name="coup_dc_price" required="required" value=""></td></tr>
									<tr><th>조건가격</th><td><input type="number" name="coup_req_price" required="required" value=""></td></tr>
									<tr><th>유효기간(일)</th><td><input type="number" name="coup_expdate" required="required" value=""></td></tr>
									<tr><td colspan="2">
								    <input type="submit" value="신규 쿠폰 등록"> 
									</td></tr>
								</table>
							</form>
		
								
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