<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="utf-8" class="light-style layout-menu-fixed" dir="ltr"
	data-theme="theme-default" data-assets-path="assets/"
	data-template="vertical-menu-template-free">
<head>
<meta charset="UTF-8">
<title>어드민 | 비숲</title>

<style type="text/css">
	.menu-link {
		text-decoratioa:none;
	}
	
	.content-wrapper {
		background-color: #EFF5FF;
	}
	
	#chatBox {
		margin:0 auto;
	}
	
	.fw-bold {
		margin-left:10px;
		font-size:22px;
	}
	
</style>

<jsp:include page="A_head.jsp"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		var roomId = $("#roomId").val();
		
		$.ajax(
				{
					url:"${pageContext.request.contextPath}/rooms_a/"+roomId,
					type:'get',
					dataType:'html',
					success:function(data) {
						$("#chatBox").html(data);
					}
				}
				
		);
		
	});
	
	function exit() {
		onClose();
		history.go(-1);
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
					<div class="container-xxl flex-grow-1 container-p-y">
						<input type="hidden" id="roomId" name="roomId" value="${roomId }">
						<h4 class="fw-bold py-3 mb-4">1:1 채팅 문의</h4>
						<p>${pageContext.request.contextPath }</p>
						
						<div id="chatBox" style="width:350px"></div>
						
						
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