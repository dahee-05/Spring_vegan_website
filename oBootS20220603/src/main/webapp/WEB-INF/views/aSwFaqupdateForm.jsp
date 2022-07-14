<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<style>
	table{ border-collapse: separate;
		border-spacing : 50px;
		margin-bottom: 50px
		/* padding: 50px; */
		}
		
	th, td{ padding-bottom: 50px;}
	
	input[type=button] { border:solid 2px blue }
	input[type=submit] { border:solid 2px blue }
	
</style>
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
						<h4 class="fw-bold py-3 mb-4">공지사항 수정</h4>
						<div class="card">
							<!-- Account -->
							<div class="card-body">
								
								<form action="aSwFaqupdate" method="post">
	<input type="hidden" name="boardno" value="${faq.boardno }">
	
	<h4>
			<c:if test="${faq.board_category == 1}">회원정보 문의</c:if>
			<c:if test="${faq.board_category == 2}">주문 및 결제 문의</c:if>
			<c:if test="${faq.board_category == 3}">배송문의</c:if>
			<c:if test="${faq.board_category == 4}">교환 및 반품 문의</c:if>
		
	</h4> 
	
<table>	
	<tr><th>글번호</th>		<td>${faq.boardno }</td></tr>
	<tr>
			<td> (Q)질문 </td>
			<td> <textarea rows="10" cols="60" name="board_title" required="required">"${faq.board_title }"</textarea></td>
				
	</tr>
	<tr>
			<td> (A)답변 </td>
			<td> <textarea rows="10" cols="60" name="board_content" required="required">"${faq.board_content }"</textarea></td>
	</tr>
	<%-- <tr><th>(Q)질문</th><td>
		<input type="text" name="board_title" required="required" value="${faq.board_title }"></td></tr>
	<tr><th>(A)답변</th><td>
		<input type="text" name="board_content" required="required" value="${faq.board_content }"></td></tr> --%>
	<tr><td colspan="2" style="text-align: end;">
	   <input type="submit" value="수정">&nbsp;&nbsp;
	   <input type="button" value="목록" 
			onclick="location.href='aSwFaqList'">&nbsp;&nbsp;
	   </td>
	</tr>
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