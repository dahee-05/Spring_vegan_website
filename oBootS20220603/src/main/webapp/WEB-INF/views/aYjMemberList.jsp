<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<%
	String context = request.getContextPath();
    System.out.println("context->"+context);
%>
<html lang="utf-8" class="light-style layout-menu-fixed" dir="ltr"
	data-theme="theme-default" data-assets-path="assets/"
	data-template="vertical-menu-template-free">
	
<style>
	td, td{
		text-align:center;
}

</style>
<head>
	<meta charset="utf-8" />
	<title>어드민 | 비숲</title>
	<jsp:include page="A_head.jsp"/>
	<script>
	function updateMilage(id){
		console.log("id", id);
		
		var mileage = $('#mileage'+id).val();
		
		$.ajax({
			
			url:"<%=context%>/aYjmilEdit",
			data:{'id':id, 'mileage':mileage}, 
			success:function(data){ //성공하면 1 / data는 restController에서 return 값
				alert(".ajax updateMilage data->"+data);
			
				if(data > 0){
					alert("수정에 성공했습니다.");
					 location.reload();
				} else{
					alert("수정에 실패했습니다.");
					
				}
				
	
			},
				
			error : function(xhr, status, er){
				if(errorFunc){
				errorFunc(er)
				}
			}
		});
		
	}
	
	
	</script>
	


	
	
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
					
						
						
							<!-- Account -->
					
	<!-- -------------------------------------------------------------------------------------------------------- -->
					<div class="container-xxl flex-grow-1 container-p-y">
						<ul class="nav nav-pills flex-column flex-md-row">
							<li class="navbar-nav-right d-flex align-items-center">
								<h4 class="fw-bold py-3 mb-4">회원 관리</h4>
							</li>
						</ul>
						<!-- Basic Bootstrap Table -->
						<div class="card">
							<div class="table-responsive text-nowrap">
								<table class="table">
									<thead>
										<tr>
											<th>번호</th>
											<th>아이디</th>
											<th>마일리지</th>
											<th>이메일</th>
											<th>핸드폰번호</th>
											<th>계정상태</th>
										</tr>
									</thead>
									<tbody class="table-border-bottom-0">
						<c:set var="num" value="${pg.total-pg.start+1}"></c:set>
							<c:forEach var="member" items="${listMember}" varStatus="status" >
								<tr>
									<td>${member.rn}
									<td>${member.id }</td>
									<td><input type="text" name="mileage" id="mileage${member.id }" value="${member.mileage }"/>
											<input type="button" id="btn_milCheck" value="수정" onclick="updateMilage('${member.id}')"> </td>
									<td>${member.email }</td>
									<td>${member.tel }</td>
									<td>
										<c:if test="${member.status == 1}">
											활동
										</c:if>
										
										<c:if test="${member.status == 0}">
											탈퇴
										</c:if>
										
										<c:if test="${member.status == 2}">
											휴면
										</c:if>
									</td>
								</tr>
								<c:set var="num" value="${num - 1 }"></c:set>
							</c:forEach>
							</tbody>
						</table>
					</div>
			</div>
		</div>
	<!--/ Basic Bootstrap Table -->
	
							<!-- Basic Pagination -->
						<nav aria-label="Page navigation" class="container-p-y">
							<ul class="pagination justify-content-center">
						
								<c:if test="${pg.startPage > pg.pageBlock}">
										<li class="page-item prev">
									<a href="aYjMemberList?currentPage=${pg.startPage - pg.pageBlock}">[이전]</a></li>
								</c:if>
								
								<c:forEach var="i" begin="${pg.startPage}" end="${pg.endPage}">
									<li class="page-item">
									<a href="aYjMemberList?currentPage=${i}">[${i}]</a></li>
								</c:forEach>
								
								<c:if test="${pg.endPage < pg.totalPage}">
									<li class="page-item next">
									<a href="aYjMemberList?currentPage=${pg.startPage + pg.pageBlock }">[다음]</a></li>
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