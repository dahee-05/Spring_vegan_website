<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%String context = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 화면</title>
<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/mypage_contents.css" rel="stylesheet" type="text/css">
<link href="css/product.css" rel="stylesheet" type="text/css">
<link href="css/event.css" rel="stylesheet" type="text/css">
<!-- 
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script> -->
<script src="js/jquery.js"></script>
</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/header.jsp"/>
		<!-- ----------------------------------------------------------------------main시작----------------------------------- -->
<%-- 		<div class="margin_wrap">
			<div class="main clearfix">
			<jsp:include page="/WEB-INF/views/mySnb.jsp"/> --%>
		<!-- ----------------------------------------------------------------------여기에 코딩 시작----------------------------------->
				<!-- <section class="sectionmy"> -->
				
				
				<div class="margin_wrap">
				<div id="main">
				
				<h2 class="hr">${event.evt_name }</h2>
				<%-- <h2 id="event" style="text-align: center; margin: 60px 0px 20px; padding: 0px 0px 20px;
					font-size: 24px; color: #5F8350; border-bottom: outset; position: relative; ">
					${event.evt_name }</h2> --%>
				
				<ul class="clearfix">
				<c:forEach var="product" items="${p_list }">
					<li class="prod_list">
							<!-- <div onclick="location.href='#'"></div> -->
								<a href="prodDetail?prodno=${product.prodno}">
									<div class="prod_th_img">
									<img alt="상품이미지" src="../upload/${product.th_img }">
									</div>
									<div>
										<p class="brand_name">${product.brand_name}</p> 
										<p class="prod_name">${product.prod_name}</p>
									</div>
									<div class="price">
									<c:if test="${product.dc_rate != 0}">
										<span style="color:#c0c0c0;text-decoration:line-through">
											<fmt:formatNumber value="${product.prod_price }" groupingUsed="true"></fmt:formatNumber>원
										</span> 
										<span style="color:red">
											<fmt:formatNumber value="${product.sale_price }" groupingUsed="true"></fmt:formatNumber>원
										</span>
									</c:if>
									</div>
								</a>
									
							
						</li>
					
				</c:forEach>	
			
					</ul>	
						
	
          
          <div class="tabs">
    		<input id="tab01" type="radio" name="tab_item" checked>
   			 <label class="tab_item" for="tab01">이벤트 설명</label>
   			<input id="tab02" type="radio" name="tab_item">
   			 <label class="tab_item" for="tab02">이벤트 주의사항</label>
   
    		<div class="tab_content" id="tab01_content">
        			${event.evt_exp }
    		</div>
    		
    		<div class="tab_content" id="tab02_content">
        			${event.evt_notice }
    		</div>
  
			</div>
         
 
         </div>
       </div>

 
				<!-- </section>	 -->
		
		<!-- ----------------------------------------------------------------------여기에 코딩 끝------------------------------------>
		<!-- ----------------------------------------------------------------------main끝----------------------------------- -->
		
		<jsp:include page="/WEB-INF/views/footer.jsp"/>
		
	</div> <!-- wrap -->
	
</body>
</html>