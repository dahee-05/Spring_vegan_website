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
<title>마이페이지</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/httpRequest.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<link href="css/mypage.css" rel="stylesheet" type="text/css">
<link href="css/subForm.css" rel="stylesheet" type="text/css">
<link href="css/shoppingcart.css" rel="stylesheet" type="text/css">



<title>구독 신청</title>
</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/header.jsp"/>
<!-- ----------------------------------------------------------------------main시작----------------------------------- -->
		<div class="margin_wrap">
			<div class="main clearfix">
			<jsp:include page="/WEB-INF/views/mySnb.jsp"/>
<!-- ----------------------------------------------------------------------드롭다운 메뉴 시작---------------------------------->
<!-- ----------------------------------------------------------------------여기에 코딩 시작----------------------------------->
				<section class="sectionmy">
					<h2> 리뷰작성 </h2>
					<br>
					<hr>
					<br>
					<!-- 작업 영역 -->
				<!-- 사용자 리뷰등록페이지 
					상품번호 prodno		-> 기존 글에서 정보가져감 (히든)
					리뷰번호rvno			-> 새로운 번호 부여
					작성자 writer_id 		-> 기존 사용자 정보 가져감
					리뷰내용 rv_content 	-> 작성하도록
					작성일rv_date 		-> sysdate
					ref 				-> null값 가져가
					re_step 			-> 0
					re_level 			-> 0
					rv_stars 			-> 정보 입력받음 
					rv_img 				-> 정보입력받음  -->
					
					<form action="uYjReviewWrite" method="post" enctype="multipart/form-data" onsubmit="return insert()" >
						<input type="hidden" name="prodno" value="${prodno}"> 
						<input type="hidden" name="payno" value="${payno}">
						<input type="hidden" name="writer_id" value="${writer_id}"> 
						<table>
							<tr><th> 별점 </th> <td><select id="rv_stars" name="rv_stars"> 
														<option value="1"> ⭐ </option>
														<option value="2"> ⭐⭐ </option>
														<option value="3"> ⭐⭐⭐</option>
														<option value="4"> ⭐⭐⭐⭐</option>
														<option value="5"> ⭐⭐⭐⭐⭐ </option>
												</select>
											</td>
										</tr>
							<tr><th> 리뷰내용 </th> <td><textarea rows="10" cols="60" name="rv_content"></textarea></td></tr>
							<tr><th> 리뷰이미지 </th> <td>
								<div>
									<input type="file" name="review_imgUp" id="rv_img" accept="image/*" >
									<img id="rv_img" src="#" alt="" width="200px">
								</div> </td></tr>
						</table>
								<div class="content_btn_section">
									<a><button type="submit" value="등록하기">등록하기</button></a>
								</div>
					
					</form>
					
					
					
					
					
					
					
					
					
					
					
					
					<!-- 작업 영역 -->
				</section>	
			</div>
		</div>

<!-- ----------------------------------------------------------------------여기에 코딩 끝---------------------------------->
<!-- ----------------------------------------------------------------------main끝----------------------------------- -->
	<jsp:include page="/WEB-INF/views/footer.jsp"/>
	</div> <!-- wrap -->
</body>
</html>