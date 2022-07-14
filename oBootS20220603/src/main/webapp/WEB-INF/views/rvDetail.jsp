<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="utf-8" class="light-style layout-menu-fixed" dir="ltr"
	data-theme="theme-default" data-assets-path="assets/"
	data-template="vertical-menu-template-free">
	
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>score demo</title>
    <link href="css/shoppingcart.css" rel="stylesheet" type="text/css">
    <jsp:include page="A_head.jsp"/>
    <script src="js/jquery.js"></script>
    <style>
       .total{
            display: inline-flex;

       }
        .star_all {
            display:inline-flex;
            position: relative;
            
            color: blue;
            
        }
        .star_full{
            width: 10%;
            overflow: hidden;
            position: absolute;

        }

    </style>
   
    <script>
    window.onload=function(){
        // 1) 별점 계산
            
        const star = $("#rv_stars").val()/5*100;
        const score = $("#rv_stars").val();
        $(".star_full").css("width",star+"%");
        // document.querySelector(".star_full").style="width:"+star+"%";
        document.querySelector(".rate").innerHTML= score;

    }



    </script>
</head>
<body>
	<!-- Layout wrapper -->
	<div class="layout-wrapper layout-content-navbar">
		<div class="layout-container">
			<jsp:include page="A_menu.jsp"/>

			<!-- Layout container -->
			<div class="layout-page">

				<!-- Content wrapper -->
				<div class="content-wrapper">

					<!-- Content -->
					<div class="container-xxl flex-grow-1 container-p-y">
						<ul class="nav nav-pills flex-column flex-md-row">
							<li class="navbar-nav-right d-flex align-items-center">
								<h4 class="fw-bold py-3 mb-4">리뷰 상세보기</h4>
							</li>
						</ul>
	<!-- -------------------------------------------------------------------------------------------------------- -->

					<div class="card">
						<div class="table-responsive text-nowrap">
							<input type="hidden" id="rv_stars" value="${review.rv_stars }">
							<table class="table">
								<tbody class="table-border-bottom-0">
									<tr><th>별점</th>
										<td>
											<div class="total">
								       			<div class="star_all">
								            		<div class="star_full">
								                		★★★★★
								           			 </div>
								
								            		<div class="star_empty">
								            		    ☆☆☆☆☆
								            		</div>
								       			 </div>
								        		[ <div class="rate"></div> 점 ]
								   			 </div>
   										</td>
   									</tr>
   								
								<tr>
									<th>이미지</th>
									<td>
										<c:if test="${empty review.rv_img }">
											이미지 없음
										</c:if>
										
										<c:if test="${not empty review.rv_img }">
											<img src="../upload/rv_img/${review.rv_img }" width="200" height="200" class="rv_img">
										</c:if>
									</td>
								</tr>
								<tr><th>상품명</th><td>${review.prod_name }</td></tr>  <!-- 다른테이블  : 불러오지 않음 -->
								<tr><th>작성일</th><td>${review.rv_date }</td></tr> 
								<tr><th>작성자</th><td>${review.writer_id }</td></tr> 
								<tr><th>리뷰내용</th><td>${review.rv_content}</td></tr> 
							</table>
						</div>
					</div>
<!--------------------------------------------------- 등록된 댓글보기--------------------------------------------------------------------------------------------------->
					<hr>
					<div class="card">
						<div class="table-responsive text-nowrap">
							<table class="table">
								<tbody class="table-border-bottom-0">
								<tr><th>등록댓글</th><th>작성자</th></tr>
							 	<c:if test="${reply != null }">
									<c:forEach var="reply" items="${reply }" >
										<tr><td>
										<c:if test="${reply.re_level>0}">
											<img src="images/flecha.png" width="20" height="20">
										</c:if>
											 ${reply.rv_content}</td><td> ${reply.writer_id }</td></tr> 
									</c:forEach>
						 		</c:if> 
							</table>		
						</div>	
					</div>
<!--------------------------------------------------- 관리자가 댓글쓰기---------------------------------------------------------------------------------------------------->
					<hr>
					<div class="card">
						<div class="table-responsive text-nowrap">
							<form action="rvReplyWrite" method="post">
								<table class="table">
									<tr><th>관리자답글</th></tr>
										<input type="hidden" name="rvno" value=${review.rvno}> 
										<input type="hidden" name="prodno" value=${review.prodno}> 
										<input type="hidden" name="ref" value=${review.ref}>
										<input type="hidden" name="re_level" value=${review.re_level}>
										<input type="hidden" name="re_step" value=${review.re_step}> 
										<input type="hidden" name="rv_stars" value=${review.rv_stars}> 
										<input type="hidden" name="rv_img" value=${review.rv_img}> 
									<tr><td>
										<textarea name="rv_content" rows="10" cols="100"></textarea>
										</td>
										<td>
											<input type="submit" value="등록"></input>
										</td>
									</tr>
									<tr><td>
										<a href="reviewList">목록보기</a> 
									</td></tr>
								</table>
							</form>
						</div>	
					</div>
			</div>
		</div>

							
<!-- -------------------------------------------------------------------------------------------------------- -->
							</div>
							<!-- /Account -->
						</div>
					</div>
					<!-- / Content -->

					<div class="content-backdrop fade"></div>
				</div>
				<!-- Content wrapper -->
			</div>
			<!-- / Layout page -->
		</div>

		<!-- Overlay -->
		<div class="layout-overlay layout-menu-toggle"></div>
	</div>
	<!-- / Layout wrapper -->

	<jsp:include page="A_footer.jsp"/>

    
    <div class="test-score" data-max="5" data-rate="3"></div>
    
   
</body>
</html>