<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/header.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="margin_wrap">
			<header>
		        <div id="logo">
		            <a href="/"><img src="images/logo.svg" class="logo"></a>
		        </div>
		        <div id="search_box">
		            <form name ="sfrm" action="searchProduct">
		                <fieldset>
		                    <legend class="visually-hidden">검색</legend>
		                    <img id="searchpic" alt="검색아이콘" src="images/icons/front_search.svg">
		                    <input type="search" name="search"  id="search" maxlength="340" tabindex="1" placeholder="찾으시는 상품이 있나요?"/>
		                </fieldset>
		            </form>
		        </div>
		        
	            <nav>
	                <div id="nav_items1">
	                    <ul class="clearfix">
	                    	<c:if test="${id eq null }">
		                        <li>
			                        <a href="jhLoginFormU">
			                        	<img src="images/icons/log-in.png"><br>
			                        	<span>로그인</span>
			                        </a>
		                        </li>
                    		</c:if>
                    		
   	                    	<c:if test="${id ne null }">
		                        <li>
			                        <a href="/jhLogoutU">
			                        	<img src="images/icons/log-out.png"><br>
			                        	<span>로그아웃</span>
			                        </a>
		                        </li>
	                    	</c:if>
	                    	
	                        <li><a href="/jhInterCeptor">
	                        	<img src="images/icons/user.png"><br>
	                        	<span>My</span>
	                        </a></li>
	                        
	                        <li><a href="/hkWishInterCeptor">
	                        	<img src="images/icons/Vector.png"><br>
	                        	<span>Like</span>
	                        </a></li>
	                        
	                        <li><a href="/hkCartInterCeptor">
	                        	<img src="images/icons/shopping-bag.png"><br>
	                        	<span>${bas_cnt }</span>
	                        </a></li>
	                        
	                    </ul>
	                </div>
	                
	            </nav>
		        
		    </header>

			
			
			
		</div> <!-- margin_wrap -->
		
		<div id="gnb">
			<div class="margin_wrap gnb_margin">
				<ul class="clearfix">
	                <li><span><a href="/prodList?cg=0" <c:if test="${cg == 'ALL'}">style="color:#2D8B31"</c:if>>ALL</a></span></li>
	                <li><span><a href="/prodList?cg=1" <c:if test="${cg == '비건식품'}">style="color:#2D8B31"</c:if>>비건식품</a></span></li>
	                <li><span><a href="/prodList?cg=2" <c:if test="${cg == '생활용품'}">style="color:#2D8B31"</c:if>>생활용품</a></span></li>
	                <li><span><a href="/prodList?cg=3" <c:if test="${cg == '고체뷰티'}">style="color:#2D8B31"</c:if>>고체뷰티</a></span></li>
	                <li><span><a href="/prodList?cg=4" <c:if test="${cg == '스킨케어'}">style="color:#2D8B31"</c:if>>스킨케어</a></span></li>
	                <li><span>|</span></li>
	                <li><span><a href="/uSwEventList">EVENT</a></span></li>
	                <li><span><a href="/uSwFaqList">공지사항</a></span></li>
	            </ul>
            </div>
		</div>
</body>
</html>