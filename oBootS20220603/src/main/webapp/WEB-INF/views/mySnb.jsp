<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	String context = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/mypage.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//menu v1 
		$(".menu-v1").mouseover(function(){
			$(this).children(".submenu").stop().slideDown();
		});
		$(".menu-v1").mouseleave(function(){
			$(this).children(".submenu").stop().slideUp();
		});
	});
</script>
</head>
<body>
	<div id="container">
		<div id="mypage">
			<h3>${name }님 마이페이지</h3>
			<br>
			<br>
		</div>
		<ul id="nav-v1">
			<li class="menu-v1"><a href="jhMypageU">쇼핑 관리</a>
				<ul class="submenu">
					<li><a href="wish">위시리스트</a></li>
					<li><a href="shoppingcartList">장바구니</a></li>
					<li><a href="jhMyOrderListU">구매내역</a></li>
					<li><a href="userCoupList">쿠폰관리</a></li>
				</ul>
			</li>
			<li class="menu-v1"><a href="#">나의 정보 관리</a>
				<ul class="submenu">
					<li><a href="myInfoCheckU">개인정보 관리</a></li>
					<li><a href="hkpasswdChange">비밀번호 변경</a></li>
					<c:choose>
						<c:when test="${subs<1 }"> <!-- 구독 전 session에 값이 유지된다는 조건 구독전 -->
							<li><a href="jhSubFormU">구독 정보</a></li>
						</c:when>
						<c:when test="${subs==1 }"> <!-- 구독중 -->
							<li><a href="jhSubOldU">구독 정보</a></li>
						</c:when>
					</c:choose>
				</ul>
			</li>
		</ul>
		<div class="clear"></div>
	</div>
</body>
</html>