<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	
<!DOCTYPE html>
<html lang="utf-8" class="light-style layout-menu-fixed" dir="ltr"
	data-theme="theme-default" data-assets-path="assets/"
	data-template="vertical-menu-template-free">
<head>
	<meta charset="utf-8" />
<style type="text/css">

	.table{
	    width: 702.5px;
	}
	td{
		font-size: 1.5em;
		font-weight: bold;
	}
	
	img{
		width: 100px;
		height: 100px;
		float: right;
	}
</style>

	<title>어드민 | 비숲</title>
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
						<h4 class="fw-bold py-3 mb-4">대시보드</h4>
						<div class="card">
							<!-- Account -->
							<div class="card-body">
								<h5>오늘의 매출 현황</h5>
								<table class="table table-borderless"  border="1">
										<tr>
											<td style="padding-bottom: 0px;"></td><th style="padding-bottom: 0px; font-size: 1.3em"><a href="jhDelivListA">매출</a></th><td style="padding-bottom: 0px;"></td><th style="padding-bottom: 0px; font-size: 1.3em"><a href="jhDelivListA">주문완료</a></th>
										</tr>
										<tr>
											<td style="padding-bottom: 30px;padding-top: 0px;"><img alt="" src="images/selMoney.png"></td>
											<td style="padding-top: 0px; padding-bottom: 20px;"><fmt:formatNumber value="${selMoney }" pattern="###,###,###"/>원</td>
											<td style="padding-bottom: 30px;padding-top: 0px;"><img alt="" src="images/selCount.png"> </td>
											<td style="padding-top: 0px; padding-bottom: 20px;">${selCount }건</td>
										</tr>
								</table>
								<br>
								<br>
								<h5>오늘의 취소/환불요청 현황</h5>
								<table class="table table-borderless"  border="1">
									<tr>
										<td style="padding-bottom: 0px;"></td><th style="padding-bottom: 0px;font-size: 1.3em"><a href="jhCanListA">취소</a></th><td style="padding-bottom: 0px;"></td><th style="padding-bottom: 0px; font-size: 1.3em"><a href="jhRefListA">환불</a></th>
									</tr>
									<tr class="image">
										<td style="padding-bottom: 30px;padding-top: 0px;padding-left: 10px;"><img alt="" src="images/canCount.png"> </td>
										<td style="padding-top: 0px; padding-bottom: 20px;">${canCount }건</td>
										<td style="padding-bottom: 30px;padding-top: 0px;padding-left: 10px;"><img alt="" src="images/refCount.png"> </td>
										<td style="padding-top: 0px;padding-bottom: 20px;padding-right: 60px;">${refCount }건</td>
									</tr>
								</table>
								<br>
								<br>								
								<h5>오늘의 소식</h5>
								<table class="table table-borderless"  border="1">
									<tr>
										<td style="padding-bottom: 0px;"></td><th style="padding-bottom: 0px; font-size: 1.3em"><a href="#">리뷰</a></th><td style="padding-bottom: 0px;"></td><th style="padding-bottom: 0px; font-size: 1.3em"><a href="#">구독회원</a></th>
									</tr>
									<tr class="image">
										<td style="padding-bottom: 30px;padding-top: 0px;"><img alt="" src="images/reviewTot.png"> </td>
										<td style="padding-top: 0px; padding-bottom: 20px;">${reviewTot }건</td>
										<td style="padding-bottom: 30px;padding-top: 0px;"><img alt="" src="images/subsMemTot.png"> </td>
										<td style="padding-top: 0px; padding-bottom: 20px;">${subsMemTot }명</td>
									</tr>
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