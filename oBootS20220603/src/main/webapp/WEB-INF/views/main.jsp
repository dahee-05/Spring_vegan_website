<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<%
	String context = request.getContextPath();
%>
<head>
<meta charset="UTF-8">
<title>메인 화면</title>
<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<link href="css/product.css" rel="stylesheet" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery.js"></script>




</head>
<body>
	<div id="wrap">
	
		<jsp:include page="/WEB-INF/views/header.jsp"/>
		
		<!-- ----------------------------------------------------------------------main시작----------------------------------- -->
		<div id="slideShow">
		    <ul class="slides">
		    	<c:forEach var="banner" items="${banner_list }">
		    		<li onclick="location.href='hkEventSelect?evt_no=${banner.evt_no}'" style="cursor:pointer;"><img src="../upload/banner/${banner.banner_img }" alt=""></li>
		    	</c:forEach>
		    </ul>  
		    <p class="controller">
		      
			      <!-- &lang: 왼쪽 방향 화살표
						&rang: 오른쪽 방향 화살표 -->
			    <span class="prev">&lang;</span>  
			    <span class="next">&rang;</span>
		  	</p>
		</div>
		<script src="js/slideshow.js"></script>
		
		
		<div class="margin_wrap">
			<div id="main">
				<%-- <input type="hidden" id="id" value="${id }"> --%>
				
				<!-- best 상품 -->
				<h3 id="best">BEST 상품</h3>
				
				<!-- <ul class="clearfix" id="bestItems"> -->
				<ul class="clearfix">
					<c:forEach var="product" items="${p_list }">
						<li class="prod_list">
							<a href="prodDetail?prodno=${product.prodno}">
								<div class="prod_th_img">
									<img alt="상품이미지" src="../upload/${product.th_img}">
								</div>
								<div>
									<p class="brand_name">${product.brand_name}</p> 
									<p class="prod_name">${product.prod_name}</p>
								</div>
								<div class="price">
									<c:if test="${product.dc_rate > 0}">
										<span>${product.dc_rate}%</span>
										<span><fmt:formatNumber value="${product.sale_price}" pattern="###,###,###"/>원</span>
										<span><fmt:formatNumber value="${product.prod_price}" pattern="###,###,###"/>원</span>
									</c:if>
									<c:if test="${product.dc_rate == 0}">
										<span></span>
										<span><fmt:formatNumber value="${product.prod_price}" pattern="###,###,###"/>원</span>
										<span></span>
									</c:if>
								</div>
							</a>
						</li>
						
					</c:forEach>
				</ul>
				
				<div class="marginBtn">
					<button type="button" class="btn btn-outline-success" id="allProductBtn" onclick="location.href='prodList?cg=0'">더 보기</button>
				</div>
				
				
				
			</div> <!-- main끝 -->
		</div>
		<!-- ----------------------------------------------------------------------main끝----------------------------------- -->
		
		<jsp:include page="/WEB-INF/views/footer.jsp"/>
		
	</div> <!-- wrap -->
	
	

</body>

</html>