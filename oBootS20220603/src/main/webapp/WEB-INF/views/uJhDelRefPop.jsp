<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	String context = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>환불 정보 입력 </title>

<style type="text/css">
.accbox{
	text-align: center;
}

#accsub{
    margin-top: 20px;
	border: 1px solid #5F8350;
	background: none;
	cursor: pointer;
	color: green;
	border-radius: 12px;
    width: 62.4px;
    height: 27.4px;
}

#accsub:hover{
	background-color: green;
	color:white;	
}

</style>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/httpRequest.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script type="text/javascript">
	function popSubmit() {
		window.opener.name="uJhMyOrderList";
		document.frm.target="uJhMyOrderList";
		document.frm.action="jhChDelRefInfoU";
		document.frm.submit();
		self.close();
		// 페이지 url 이 자꾸 jhChDelRefInfoU
	}
</script>
</head>
<body>

	<br>
	<br>
	<div class="accbox">
		<h2>환불 계좌 번호 입력</h2>
			<form action="jhChDelRefInfoU" method="post" name="frm">
				<input type="hidden" name="payno" id="payno" value="${payno }" >
				<label> 계좌정보
					<input type="text" name="account" placeholder="예) 예금주: 김지호/ 은행명: 신한 / 계좌번호 110-111-1111" style="width: 350px;" required="required">
				</label>
		<br>
		<br>
		<h2>환불 사유 입력</h2>
				<label> 환불사유
				<select name="reason" required="required" style="height: 27px;">
					<option value="1" selected="selected">제품하자</option>
					<option value="2">배송 중 제품 손상</option>
					<option value="3">실수로 잘 못 주문</option>
					<option value="4">고객 변심</option>
				</select>
				</label>
			</form>
			<input type="button" onclick="popSubmit()" value="등록" id="accsub" >
		</div>
</body>
</html>