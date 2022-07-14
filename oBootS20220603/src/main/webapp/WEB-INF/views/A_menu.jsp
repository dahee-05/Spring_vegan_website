<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<!-- Menu -->
<aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
	<div class="app-brand demo">
		<span class="app-brand-logo demo"> 
			<img src="${pageContext.request.contextPath}/assets/img/favicon/admin_logo.svg">
		</span> 
		<a href="javascript:void(0);" class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
			<i class="bx bx-chevron-left bx-sm align-middle"></i>
		</a>
	</div>

	<div class="menu-inner-shadow"></div>

	<ul class="menu-inner py-1">
		<!-- menu_num 1 : 대시 보드 -->
		<li class="menu-item" <c:if test="${menu_num == '1' }">style="border-right:5px solid #3B82F6"</c:if>>
			<a href="/admin" class="menu-link"
			<c:if test="${menu_num == '1' }">style="background-color:#E3E9FE;color:#4487F6;font-weight:bold;"</c:if>>대시 보드</a>
		</li>

		<!-- menu_num 2 : 상품 관리 -->
		<li class="menu-item" <c:if test="${menu_num == '2' }">style="border-right:5px solid #3B82F6"</c:if>>
			<a href="/prodSelect" class="menu-link"
			<c:if test="${menu_num == '2' }">style="background-color:#E3E9FE;color:#4487F6;font-weight:bold;"</c:if>>상품 관리</a>
		</li>

		<!-- 드롭다운 메뉴  : 주문 배송 현황 -->
        <li class="menu-item">
           <a href="javascript:void(0);" class="menu-link menu-toggle">주문 배송 현황</a>
  
           <ul class="menu-sub"
              <c:if test="${menu_num == '3' || menu_num == '4' }">style="display:block"</c:if>>
              <!-- menu_num 3 : 배송 / 판매 -->
              <li class="menu-item"
                 <c:if test="${menu_num == '3'}">style="border-right:5px solid #3B82F6"</c:if>>
                 <a href="/jhDelivListA" class="menu-link"
                 <c:if test="${menu_num == '3' }">style="background-color:#E3E9FE;color:#4487F6;font-weight:bold;"</c:if>>배송 / 판매</a>
              </li>
              <!-- menu_num 4 : 취소 / 반품 -->
              <li class="menu-item"
                 <c:if test="${menu_num == '4' }">style="border-right:5px solid #3B82F6"</c:if>>
                 <a href="/jhCanListA" class="menu-link"
                 <c:if test="${menu_num == '4' }">style="background-color:#E3E9FE;color:#4487F6;font-weight:bold;"</c:if>>취소 / 반품</a>
              </li>
           </ul>
        </li>
		
		<!-- menu_num 5 : 회원 관리 -->
		<li class="menu-item" <c:if test="${menu_num == '5' }">style="border-right:5px solid #3B82F6"</c:if>>
			<a href="/aYjMemberList" class="menu-link"
			<c:if test="${menu_num == '5' }">style="background-color:#E3E9FE;color:#4487F6;font-weight:bold;"</c:if>>회원 관리</a>
		</li>
		
		<!-- menu_num 6 : 리뷰 관리 -->
		<li class="menu-item" <c:if test="${menu_num == '6' }">style="border-right:5px solid #3B82F6"</c:if>>
			<a href="/reviewList" class="menu-link"
			<c:if test="${menu_num == '6' }">style="background-color:#E3E9FE;color:#4487F6;font-weight:bold;"</c:if>>리뷰 관리</a>
		</li>
		
		<!-- menu_num 7 : 쿠폰 관리 -->
		<li class="menu-item" <c:if test="${menu_num == '7' }">style="border-right:5px solid #3B82F6"</c:if>>
			<a href="/coupMasList" class="menu-link"
			<c:if test="${menu_num == '7' }">style="background-color:#E3E9FE;color:#4487F6;font-weight:bold;"</c:if>>쿠폰 관리</a>
		</li>
		
		<!-- menu_num 8 : 이벤트 관리 -->
		<li class="menu-item" <c:if test="${menu_num == '8' }">style="border-right:5px solid #3B82F6"</c:if>>
			<a href="/aSwEventList" class="menu-link"
			<c:if test="${menu_num == '8' }">style="background-color:#E3E9FE;color:#4487F6;font-weight:bold;"</c:if>>이벤트 관리</a>
		</li>
		
		<!-- menu_num 9 : FAQ -->
		<li class="menu-item" <c:if test="${menu_num == '9' }">style="border-right:5px solid #3B82F6"</c:if>>
			<a href="/aSwFaqList" class="menu-link"
			<c:if test="${menu_num == '9' }">style="background-color:#E3E9FE;color:#4487F6;font-weight:bold;"</c:if>>FAQ</a>
		</li>
		
		<!-- menu_num 10 : 1:1 채팅 문의 -->
		<li class="menu-item" <c:if test="${menu_num == '10' }">style="border-right:5px solid #3B82F6"</c:if>>
			<a href="/chatrooms" class="menu-link"
			<c:if test="${menu_num == '10' }">style="background-color:#E3E9FE;color:#4487F6;font-weight:bold;"</c:if>>1:1 채팅 문의</a>
		</li>
		
		<!-- menu_num 11 : 메인 배너 관리 -->
		<li class="menu-item" <c:if test="${menu_num == '11' }">style="border-right:5px solid #3B82F6"</c:if>>
			<a href="/admin/banner" class="menu-link"
			<c:if test="${menu_num == '11' }">style="background-color:#E3E9FE;color:#4487F6;font-weight:bold;"</c:if>>메인 배너 관리</a>
		</li>
		
		
	</ul>

	<div class="text-center mb-3">
		<a href="/" target="_blank" class="bg-menu-theme mb-2" style="font-size:12px">비숲 바로가기 ></a>
		<p>
		<a href="/yjLogoutA" class="bg-menu-theme" style="font-size:12px">로그아웃</a>
	</div>
</aside>
<!-- / Menu -->