<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<%
	String context = request.getContextPath();
%>
<html>
<head>
<meta charset="UTF-8">
<title>메인 화면</title>
<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/main.css" rel="stylesheet" type="text/css">
<!-- --------드롭다운 자바스크립트 시작--------------------------------->
<link href="css/mypage.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<!-- <script type="text/javascript"> -->
<!-- /* RestController TEST1   과제 */ -->
<%-- function getDept1(vboardCategory){
	alert("vboardCategory->"+vboardCategory);
	$.ajax(
			  {
					url:"<%=context%>/sample/sendVO1",
					data:{boardCategory : vboardCategory},
					dataType:'json',
					success:function(dept){
						/*  alert(".ajax getDept Data"+data);  */
						deptStr   = dept.dname + " " + dept.loc;
     					$('#getDept1').val(deptStr);     /*  input Tag */
     					 							
					}
			  }
			);
}	
</script> --%>
<!-- --------드롭다운 자바스크립트 끝--------------------------------->
</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/header.jsp"/>
		<!-- ----------------------------------------------------------------------main시작----------------------------------- -->
		<div class="margin_wrap">
			<div id="main">
		<!-- ----------------------------------------------------------------------드롭다운 메뉴 시작---------------------------------->
				<div id="container">
					<ul id="nav-v1">
						
							
								<li><a href="uSwFaqList1">회원정보 문의</a></li>
								    <!--   RestController 회원정보 문의<input type="button" id="btn_Dept"  value="회원정보 문의"     onclick="getFaqList(1)"><p></li> -->
								<li><a href="uSwFaqList?board_category=${faq.board_category == 2 }">주문 및 결제 문의</a></li>
								<li><a href="uSwFaqList?board_category=${faq.board_category == 3 }">배송 문의</a></li>
								<li><a href="uSwFaqList?board_category=${faq.board_category == 4 }">교환 및 반품 문의</a></li>
							
		
					</ul>
					<div class="clear"></div>
				</div>
		<!-- ----------------------------------------------------------------------드롭다운 메뉴 끝----------------------------------->
		<!-- ----------------------------------------------------------------------여기에 코딩 시작----------------------------------->
				<section class="sectionmy">
					<div>
							
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