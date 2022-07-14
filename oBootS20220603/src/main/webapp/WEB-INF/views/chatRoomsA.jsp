<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1 채팅 문의</title>
<link href="css/chatRoomsA.css" rel="stylesheet" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
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
						<h4 class="fw-bold py-3 mb-4">1:1 채팅 문의</h4>
						
						<div class = "container">
						    <table id="chatListTable" class = "table table-hover">
						        <thead>
						        <tr>
						            <th>고객ID</th>
						            <th>참여 인원</th>
						            <th>입장 버튼</th>
						        </tr>
						        </thead>
						        <tbody>
						        
						        	<c:if test="${empty rooms }">
						        		<td colspan="3" style="text-align:center;">현재 문의가 없습니다.</td>
						        	</c:if>
						        	
						        	<c:if test="${not empty rooms }">
						        		<c:forEach var="room" items="${rooms}">
								        	<tr>
									            <td>${room.name}</td>
									            <td>${room.cnt} / 2</td>
									            <td>
									                <c:if test="${room.cnt >= 2}">
									                	답변중
									                </c:if>
									                <c:if test="${room.cnt < 2 }">
									                	<a class = "btn btn-primary" href = "/chatrooms/room?roomId=${room.roomId }">입장</a>
									                </c:if>
									            </td>
								            </tr>
								        </c:forEach>
						        	</c:if>
						        	
							        
						        </tbody>
						    </table>
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