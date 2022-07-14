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
<link href="css/wish.css" rel="stylesheet" type="text/css">
<script src="js/jquery.js"></script>

<script type="text/javascript" src="js/httpRequest.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<%
	String context = request.getContextPath();
%>

<script type="text/javascript">
	function emptyh(Prodno) {
		var prodno=Prodno;
		$.ajax(
			{
				url: "<%=context%>/chgFilToEmpHeart",
				data:{prodno:prodno},
				dataType:'text',
				success:function(data){
					if(data==1){
						$('#remove'+prodno).remove();
					}
					
				}
				
			}		
		)
		
	}
</script>

</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/header.jsp"/>
		<!-- ----------------------------------------------------------------------main시작----------------------------------- -->
		<div class="margin_wrap clearfix">
			<div class="main">
			<jsp:include page="/WEB-INF/views/mySnb.jsp"/>
		<!-- ----------------------------------------------------------------------여기에 코딩 시작----------------------------------->
				<section class="sectionmy">
					<div>
						<h2>위시리스트</h2>
						<ul class="prod_wrap clearfix" id="clearfix">
							<c:forEach var="list" items="${idWishList }">
								<li id="remove${list.prodno }">
									<div class="prod_list">
										<a href="prodDetail?prodno=${list.prodno }">
											<div class="prod_th_img">
												<img alt="상품이미지" src="../upload/${list.th_img }">
											</div>
											<div>
												<p class="brand_name">${list.brand_name }</p> 
												<p class="prod_name">${list.prod_name }</p>
											</div>
											<c:choose>
												<c:when test="${list.dc_rate==0 }">
													<div class="price">
														<span><fmt:formatNumber value="${list.prod_price }" pattern="###,###,###"/>원</span>
													</div>
												</c:when>
												<c:otherwise>
													<div class="price">
														<span>${list.dc_rate }%</span>
														<span><fmt:formatNumber value="${list.sale_price }" pattern="###,###,###"/>원</span>
														<span><fmt:formatNumber value="${list.prod_price }" pattern="###,###,###"/>원</span>
													</div>
												</c:otherwise>
											</c:choose>
										</a>
										<div class="like">
											<button onclick="emptyh(${list.prodno })" >
												<img alt="" src="images/icons/fillheart.png" id="imgfillh">
											</button>
										</div>
									</div>
								</li>
							</c:forEach>
						</ul>
						
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