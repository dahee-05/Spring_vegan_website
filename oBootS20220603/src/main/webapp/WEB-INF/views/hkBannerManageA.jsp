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
	<script src="js/jquery.js"></script>
	<jsp:include page="A_head.jsp"/>
	<style type="text/css">
		#org_img {
			width:300px;
			height:80px;
		}
		
		#org_img img {
			width:300px;
			height:80px;
		}
		
		.table th {
			width: 20%;
		    font-size: 20px;
		    text-align: center;
		    border: 0;
		}
		
		.table td {
			font-size:15px;
			width: 80%;
    		border: 0;
		}
		
		.table tr {
			border-bottom:1px solid #D9DEE3;
		}
	
	</style>
	
	<script type="text/javascript">
		function chk() {
			var a1 = $("#formSelect1").val();
			var a2 = $("#formSelect2").val();
			var a3 = $("#formSelect3").val();
			
			if(a1==a2 || a2==a3 || a1==a3) {
				alert("동일한 이벤트는 중복해서 등록할 수 없습니다.");
				return false;
			}
			
			return true;
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

					<!--------------------------------------------------------------- Content ------------------------------------------------------->
					<div class="container-xxl flex-grow-1 container-p-y">
						<h4 class="fw-bold py-3 mb-4">메인 배너 관리</h4>
						
						<div class="card">
							<div class="table-responsive text-nowrap">
								<form action="/admin/banner/update" method="post" enctype="multipart/form-data" onsubmit="return chk()">
									<table class="table">
										<tbody class="table-border-bottom-0">
											<c:forEach var="banner" items="${banner_list }" varStatus="status">
												<tr>
													<th>메인${status.index+1 }</th>
													<td>
														<label class="form-label" for="formSelect">이벤트</label><p>
														<select name="evt_no" id="formSelect${status.index+1 }">
															<option value="0">없음</option>
															<c:forEach var="event" items="${event_list }">
															
																<option value="${event.evt_no }" <c:if test="${banner.evt_no == event.evt_no }">selected="selected"</c:if>>
																	${event.evt_name }
																</option>
																
															</c:forEach>
														</select>
														
														<p>
														
														<div class="mb-3 col-md-6">
									                        <label class="form-label" for="formFileMultiple">배너 이미지</label>
										                    <%-- <input type="hidden" name="org_banner_img${status.index+1 }" value="${banner.banner_img}"> --%>
										                          
										                    <input 
										                        type="file" 
										                        name="new_banner_img" 
										                        class="form-control mb-2" 
										                        id="formFileMultiple"
										                    />
										                      
							                          		<div id="org_img">
							                          			<img alt="기존 이미지" src="../upload/banner/${banner.banner_img }">
							                          		</div>
										                          
									                	</div>
									                	
													</td>
												</tr>
											</c:forEach>
											
											<c:if test="${banner_list_size < 3 }">
												<c:forEach begin="${banner_list_size }" end="2" step="1" varStatus="status">
													<tr>
													<th>메인${status.index+1 }</th>
													<td>
														<label class="form-label" for="formSelect">이벤트</label><p>
														<select name="evt_no" id="formSelect${status.index+1 }">
															<option value="0" selected="selected">없음</option>
															<c:forEach var="event" items="${event_list }">
																<option value="${event.evt_no }">
																	${event.evt_name }
																</option>
															</c:forEach>
														</select>
														
														<p>
														
														<div class="mb-3 col-md-6">
									                        <label class="form-label" for="formFileMultiple">배너 이미지</label>
										                          
										                    <input 
										                        type="file" 
										                        name="new_banner_img" 
										                        class="form-control mb-2" 
										                        id="formFileMultiple"
										                    />
										                      
									                	</div>
														
													</td>
												</tr>
												</c:forEach>
											</c:if>
											
											<tr>
												<td colspan="2" style="text-align: right; height: 80px;">
													<input type="reset" value="취소" class="btn btn-outline-secondary">
													<input type="submit" value="등록" class="btn btn-primary me-2">
												<td>
											<tr>
										</tbody>
									</table>
								</form>
			 				</div>
						</div>
						<!-- / Basic Bootstrap Table -->
						
						
						
						
					</div>
					<!--------------------------------------------------------------- / Content --------------------------------------------------------------->

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