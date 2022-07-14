<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<link href="css/faq.css" rel="stylesheet" type="text/css">
<!-- --------드롭다운 자바스크립트 시작--------------------------------->
<!-- <link href="css/mypage.css" rel="stylesheet" type="text/css"> -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript">
/* RestController TEST1   과제 */
function getFaqList(vboardCategory){
	/* alert("vboardCategory->"+vboardCategory); */
	var str = "";
	var str2 = "";
	 $.ajax(
			  {
					url:"<%=context%>/getBoardCategoryList",
					data:{board_category : vboardCategory},
					dataType:'json',
					success:function(faqList){
						var jsondata = JSON.stringify(faqList);
						/* alert("jsondata->"+jsondata); */
						str  += "";
						$(faqList).each(
							function(){
								str2 = "<div style='font-size: 25px;font-weight: 500;color: crimson;'>"+this.board_title+
										"</div><br><br><div>"+"<pre style='font-size: 19px; font-weight: 600; color: #2525B5; line-height: 30px;'>"+this.board_content+"</pre>"+"</div><br><hr><br>" ; 
								/* alert("str2-->"+str2); */
								str += str2 ;
							}
						);
						/* str += "<p>" */
					    $('#faqList').html(str);
					}
			  }
	);  
}	
</script>
<!-- --------드롭다운 자바스크립트 끝--------------------------------->
</head>

<body>
	<div id="wrap">
	
		<jsp:include page="/WEB-INF/views/header.jsp"/>

				<div class="margin_wrap">
				<div class="main">
				
					<h2 id="event" style="text-align: center; margin: 60px 0px 20px; padding: 0px 0px 20px;
					font-size: 24px; color: #5F8350; border-bottom: outset; position: relative; ">
					공지사항</h2>
			
					<div style="text-align: center; padding-top: 20px;">
						
						<input type="button" id="btn_Dept" value="회원정보 문의" onclick="getFaqList(1)" 
						style="color: #158353;font-size: 25px;font-weight: 550;border: none;background-color: white;
						margin-right: 90px;">
						<input type="button" id="btn_Dept" value="주문 및 결제 문의" onclick="getFaqList(2)" 
						style="color: #158353;font-size: 25px;font-weight: 550;border: none;background-color: white;
						margin-right: 90px;">
						<input type="button" id="btn_Dept" value="배송 문의" onclick="getFaqList(3)" 
						style="color: #158353;font-size: 25px;font-weight: 550;border: none;background-color: white;
						margin-right: 90px;">
						<input type="button" id="btn_Dept" value="교환 및 반품 문의" onclick="getFaqList(4)" 
						style="color: #158353;font-size: 25px;font-weight: 550;border: none;background-color: white;">
						
					</div><br><br>
					
					<div id="faqList" style="margin: 50px 0; font-size: 20px; padding: 0 110px;">
						
						
						
					</div>
					
					<div style="text-align: center;margin-top: 250px;font-size: larger;font-weight: 600;">
						[각 탭 클릭시 해당 공지사항으로 이동합니다]
					</div>
						
					
					
					<div class="clearfix"></div>
					</div>
	
				</div>
		<!-- ----------------------------------------------------------------------드롭다운 메뉴 끝----------------------------------->
		<!-- ----------------------------------------------------------------------여기에 코딩 시작----------------------------------->
			<!-- 	<section class="sectionmy">
					
			
		
				</section> -->
			
		
		<!-- ----------------------------------------------------------------------여기에 코딩 끝------------------------------------>
		<!-- ----------------------------------------------------------------------main끝----------------------------------- -->
		
		
		<jsp:include page="/WEB-INF/views/footer.jsp"/>
	</div>	
	 <!-- wrap -->
</body>
</html>