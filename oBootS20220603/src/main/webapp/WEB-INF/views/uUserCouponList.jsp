<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 화면</title>
<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/mypage_contents.css" rel="stylesheet" type="text/css">
<link href="css/coupon.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/header.jsp"/>
		<!-- ----------------------------------------------------------------------main시작----------------------------------- -->
		<div class="margin_wrap">
			<div class="main clearfix">
			<jsp:include page="/WEB-INF/views/mySnb.jsp"/>
		<!-- ----------------------------------------------------------------------여기에 코딩 시작----------------------------------->
				<section class="sectionmy">
					<div>
						<h2>쿠폰</h2>
						<div class="contents_wrap">
							
							<!-- 작업 영역 -->
							
				<div id="coupon_box">
						<c:if test="${totCnt > 0 }">
							<ul class="clearfix">
								<c:forEach var="ucoupon" items = "${listUserCoupon }">
									<li>
									
										<c:if test="${ucoupon.coup_type == 1 }">
											<h4>${ucoupon.coup_dc_rate}% 할인</h4>
										</c:if>
										<c:if test="${ucoupon.coup_type == 2 }">
											<h4><fmt:formatNumber value="${ucoupon.coup_dc_price }" groupingUsed="true"></fmt:formatNumber>원 할인</h4>
										</c:if>
									
										<h5>${ucoupon.coup_name }</h5>
										<p>
											<fmt:parseDate value="${ucoupon.coup_start}" var="coup_start" pattern="yyyy-MM-dd"/>
											<fmt:formatDate value="${coup_start}" pattern="yyyy-MM-dd" />
											~
											<fmt:parseDate value="${ucoupon.coup_end}" var="coup_end" pattern="yyyy-MM-dd"/>
											<fmt:formatDate value="${coup_end}" pattern="yyyy-MM-dd" />
										</p>
										
										<p>
											<fmt:formatNumber value="${ucoupon.coup_req_price }" pattern="###,###,###"/>원 이상 구매시 사용 가능
										</p>
											
									</li>
								</c:forEach>
					 		</ul>
						</c:if>
						

				 	
				 	<c:if test="${totCnt == 0 }">
			 			<div id="no_data">
			 				쿠폰이 존재하지 않습니다
			 			</div>
		 			</c:if>
				 	
				 	
				
				</div>
							<!-- 작업 영역 -->
							
							
						</div>
					</div>
			
				</section>	
			</div>
		</div>
		<!-- ----------------------------------------------------------------------여기에 코딩 끝------------------------------------>
		<!-- ----------------------------------------------------------------------main끝----------------------------------- -->
		
		<jsp:include page="/WEB-INF/views/footer.jsp"/>
		
	</div> <!-- wrap -->
	
</body>
</html>