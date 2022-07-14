<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비숲 | 결제완료</title>
<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/paySuccess.css" rel="stylesheet" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery.js"></script>
<script type="text/javascript">
	const payno = ${info.partner_order_id }
	
	$(function() {
		// 쇼핑 계속하기
		$("#continue_shop").click(function() {
			history.go(-2);
		});
		
		// 주문 상세보기
		$("#goPaymentDetail").click(function() {
			location.href="jhMyOrderDetailU?payno="+payno;
		});				
		
	});

</script>

</head>
<body>
	<div id="wrap">
	
		<jsp:include page="/WEB-INF/views/header.jsp"/>
		
		<!-- ----------------------------------------------------------------------main시작----------------------------------- -->
		<div class="margin_wrap">
			<div id="main">
				<h2>주문이 성공적으로 완료되었습니다.</h2>
				<div id="info_box">
					<p>주문번호 : ${info.partner_order_id }</p>
					<p>주문상품 : ${info.item_name }</p>
					<p>결제일시 : <fmt:formatDate value="${info.approved_at }" type="both" dateStyle="medium" timeStyle="medium"/></p>
				</div>
				
				<div id="nextBtns">
					<button id="continue_shop" type="button" class="btn btn-dark">쇼핑 계속하기</button>
					<button id="goPaymentDetail" type="button" class="btn btn-light">주문 상세보기</button>
				</div>
			</div> <!-- main끝 -->
		</div>
		<!-- ----------------------------------------------------------------------main끝----------------------------------- -->
		
		<jsp:include page="/WEB-INF/views/footer.jsp"/>
		
	</div> <!-- wrap -->


</body>
</html>