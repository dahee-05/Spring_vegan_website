<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript">
	function message() {
		var value = "${msg}";
		if(value != ""){
			alert(value);
		}
		
	}
	
	function couponGet(evt_no, coupno) {
		
		$.ajax(
			{
				url:"/coupGetChk",
				type:'post',
				data:{coupno : coupno},
				dataType:'text',
				success:function(result) {
					if(result == "exist") {
						alert("이미 발급된 쿠폰입니다.");
					} else {
						location.href="coupget?evt_no="+evt_no+"&coupno="+coupno;
					}			
					
				}
			}	
		);
		
	}

</script>
<head>
<meta charset="UTF-8">
<title>메인 화면</title>
<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/mypage_contents.css" rel="stylesheet" type="text/css">
<link href="css/event.css" rel="stylesheet" type="text/css">
</head>
<body onload="message()">
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/header.jsp"/>
		<!-- ----------------------------------------------------------------------main시작----------------------------------- -->
<%-- 		<div class="margin_wrap">
			<div class="main clearfix">
			<jsp:include page="/WEB-INF/views/mySnb.jsp"/> --%>
		<!-- ----------------------------------------------------------------------여기에 코딩 시작----------------------------------->
				<div class="margin_wrap">
				<div id="main">
				
					<h2 id="event" style="text-align: center; margin: 60px 0px 20px; padding: 0px 0px 20px;
					font-size: 24px; color: #5F8350; border-bottom: outset; position: relative; ">
					${event.evt_name }</h2>
					
				<table>
					
				
				<c:forEach var="couponmaster" items="${c_list }">
				
				<div>
					<div style="text-align: center; margin-top: 80px; font-weight: 700;
								/* border: 1px solid black; width: 290px; height: 20px; margin-left: 356px;
								background-color: #f5d682;  */ text-shadow:1px 1px 0px #f40; position: relative;">
					<%-- <a href="coupget?evt_no=${event.evt_no }&coupno=${couponmaster.coupno}">${couponmaster.coup_name }&nbsp;쿠폰&nbsp;[발급받기]</a> --%>
					<span style="cursor:pointer" onclick="couponGet(${event.evt_no}, ${couponmaster.coupno})">${couponmaster.coup_name }&nbsp;쿠폰&nbsp;[발급받기]</span>
					</div>
					<div style="text-align: center; margin-top: 3px; font-weight: 700;">
					<c:if test="${couponmaster.coup_dc_rate > couponmaster.coup_dc_price}">(${couponmaster.coup_dc_rate }% 할인)</c:if>
					<c:if test="${couponmaster.coup_dc_rate < couponmaster.coup_dc_price}">(${couponmaster.coup_dc_price }원 할인)</c:if>
					</div>
					<div style="text-align: center; margin-top: 7px; font-weight: 100;">
					사용가능일 : 발급일로부터&nbsp;${couponmaster.coup_expdate }일 이후까지
					
					</div>
			
				</div>
			
				</c:forEach>	
			
						
						
				</table>	
				
			<div class="tabs" style="padding-top: 40px;">
			
    		<input id="tab01" type="radio" name="tab_item" checked>
   			 <label class="tab_item" for="tab01">이벤트 설명</label>
   			<input id="tab02" type="radio" name="tab_item">
   			 <label class="tab_item" for="tab02">이벤트 주의사항</label>
   
    		<div class="tab_content" id="tab01_content">
        			${event.evt_exp }
    		</div>
    		
    		<div class="tab_content" id="tab02_content">
        			${event.evt_notice }
    		</div>
  
			</div>
         
 
         		</div>
       		</div>
			
	
		
		<!-- ----------------------------------------------------------------------여기에 코딩 끝------------------------------------>
		<!-- ----------------------------------------------------------------------main끝----------------------------------- -->
		
		<jsp:include page="/WEB-INF/views/footer.jsp"/>
		
	</div> <!-- wrap -->
	
</body>
</html>