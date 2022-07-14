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


	<!-- -------------------------------------------------------------------------------------------------------- -->
					
					<div class="container-xxl flex-grow-1 container-p-y">
						<ul class="nav nav-pills flex-column flex-md-row">
							<li class="navbar-nav-right d-flex align-items-center">
								<h4 class="fw-bold py-3 mb-4">리뷰 관리</h4>
							</li>
						</ul>
						
						
						<!-- Basic Bootstrap Table -->
						<div class="card">
							<div class="table-responsive text-nowrap">
								<table class="table">
									<thead>
										<tr>
											<th>글번호</th>
											<th>리뷰내용</th>
											<th>작성자</th>
											<th>작성일</th>
											<th>별점</th>
										</tr>
									</thead>
									<tbody class="table-border-bottom-0">
										<c:set var="num" value="${pg.total-pg.start+1}"></c:set> 
										<c:forEach var="review" items="${listReview}">
											<tr>
												 <td>${num}</td>
												<%-- <td>${review.rvno }</td> --%>
												<td><a href="rvDetail?rvno=${review.rvno }">${review.rv_content }</a></td>
												<td>${review.writer_id }</td>
												<td>${review.rv_date }</td>
												<td>${review.rv_stars}</td>
											</tr>
											<c:set var="num" value="${num - 1 }"></c:set>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<!--/ Basic Bootstrap Table -->

						<!-- Basic Pagination -->
						<nav aria-label="Page navigation" class="container-p-y">
							<ul class="pagination justify-content-center">
						
								<c:if test="${pg.startPage > pg.pageBlock}">
										<li class="page-item prev">
									<a href="reviewList?currentPage=${pg.startPage - pg.pageBlock}">[이전]</a></li>
									
								</c:if>
								
								<c:forEach var="i" begin="${pg.startPage}" end="${pg.endPage}">
									<li class="page-item">
									<a href="reviewList?currentPage=${i }">[${i }]</a></li>
								</c:forEach>
								
								<c:if test="${pg.endPage < pg.totalPage}">
									<li class="page-item next">
									<a href="reviewList?currentPage=${pg.startPage + pg.pageBlock }">[다음]</a></li>
								</c:if>
							</ul>
						</nav>
					<!-- /Basic Pagination -->
					


<!-- -------------------------------------------------------------------------------------------------------- -->
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