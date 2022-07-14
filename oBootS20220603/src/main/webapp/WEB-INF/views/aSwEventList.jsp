<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="utf-8" class="light-style layout-menu-fixed" dir="ltr"
	data-theme="theme-default" data-assets-path="assets/"
	data-template="vertical-menu-template-free">
<!-- <script type="text/javascript">
function message1(){
	var value = "${msgEvtR}";
	if(value != ""){
		alert(value);
	}
}
</script> -->
<head>
	<meta charset="utf-8" />
	<title>어드민 | 비숲</title>
	<jsp:include page="A_head.jsp"/>
	
	<style>
	

	table{ border-collapse: separate;
		border-spacing : 10px;
		margin-left: 10px 
		/* padding: 50px; */
		}

	th {
	padding: 20px;
 
    }
 
	input[type=button] { border:solid 2px blue }
    
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
						<h4 class="fw-bold py-3 mb-4">이벤트 목록</h4>
						<div class="card">
							<!-- Account -->
							<div class="card-body">
								
		
<p>현재 등록된 이벤트  : ${total } 개

<table>
		<tr><th style="padding-right: 40px;">이벤트번호</th>
			<th style="padding-left: 40px;">이벤트명</th>
			<th>시작일</th>
			<th>마감일</th>
			<th>이벤트상태</th>
			<th>이벤트유형</th></tr>
		<c:forEach var="event" items="${listEvent }">
			<tr>
				<td style="
					text-align: center;
					padding-right: 35px;">${event.evt_no }</td>
				
				<td>
				<c:if test="${event.evt_type == 1}">
				<a href="aSwEvtdetailp?evt_no=${event.evt_no}">${event.evt_name }</a>				
				</c:if>
				<c:if test="${event.evt_type == 2}">
				<a href="aSwEvtdetailc?evt_no=${event.evt_no}">${event.evt_name }</a>				
				</c:if>
				</td>
			    <%-- <td><a href="aSwEvtdetailp?evt_no=${event.evt_type == 1}">${event.evt_name }</a></td>
			    <td><a href="aSwEvtdetailc?evt_no=${event.evt_type == 2}">${event.evt_name }</a></td> --%>
			   <%--  <td><fmt:formatDate pattern = "yy/MM/dd" value="${event.evt_start }"/></td> --%>
			    
			      <td><fmt:parseDate var="evt_startDate" value="${event.evt_start }" pattern="yyyy-MM-dd HH:mm:ss" />
                    <fmt:formatDate value="${evt_startDate}" pattern="yyyy.MM.dd" /></td>
                  <td><fmt:parseDate var="evt_endDate" value="${event.evt_end }" pattern="yyyy-MM-dd HH:mm:ss" />
                    <fmt:formatDate value="${evt_endDate}" pattern="yyyy.MM.dd" /></td>
			    <td style="text-align: center;"><c:if test="${event.evt_sts == 0}">종료</c:if>
			    	<c:if test="${event.evt_sts == 1}">진행중</c:if>
				</td>
				<td style="text-align: center;"><c:if test="${event.evt_type == 1}">할인 이벤트</c:if>
			    	<c:if test="${event.evt_type == 2}">쿠폰 이벤트</c:if>
				</td>
			</tr>
			<c:set var="num" value="${num - 1 }"></c:set>
		</c:forEach>
		
		<tr style="text-align: right;"><td colspan="6" style="padding-top: 20px;">
		<input type="button" value="할인이벤트등록" 
			onclick="location.href='aSwEventwrite_viewp?evt_no=${event.evt_no}'">
		<input type="button" value="쿠폰이벤트등록" 
			onclick="location.href='aSwEventwrite_viewc?evt_no=${event.evt_no}'"></td></tr>
			
		<tr><td colspan="6" style="text-align:center;">
		<c:if test="${pg.startPage > pg.pageBlock }">
		<a href="aSwEventList?currentPage=${pg.startPage-pg.pageBlock}">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${pg.startPage}" end="${pg.endPage}">
		<a href="aSwEventList?currentPage=${i}">[${i}]</a>
		</c:forEach>
		<c:if test="${pg.endPage < pg.totalPage }">
		<a href="aSwEventList?currentPage=${pg.startPage+pg.pageBlock}">[다음]</a>
		</c:if></td></tr>
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