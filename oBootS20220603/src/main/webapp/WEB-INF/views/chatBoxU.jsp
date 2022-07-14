<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<%
	String context = request.getContextPath();
%>
<meta charset="UTF-8">
<script src="js/jquery.js"></script>
<link href="css/chatBoxU.css" rel="stylesheet" type="text/css">
</head>
<body>
	
	<script type="text/javascript">
	    $(function() {
			$("#chatBtn").click(function(){
				$("#chatBox").css('display','block');
				var id = $("#id").val();
				
				if(!id) {
					alert("로그인 한 회원만 이용가능합니다.");
				} else {
					
					$("#container").css('display','inline-block');
					//window.open("/room/new?id="+id,"","width=1000px,height=800px");
					//window.open("/chatrooms","","width=1000px,height=800px");
					$.ajax(
							{
								url:"<%=context %>/room/new",
								type:'post',
								data:{id: id},
								dataType:'html',
								success:function(data) {
									$("#chatBox").html(data);
								}
							}
							
					);
					
					
				}
			});
	
		});
		
		
		function exit() {
			$("#chatBox").css('display','none');
			onClose();
		}
		
	</script>
	<input type="hidden" id="id" value="${id }">
	<div id="chatBox"></div>
	
	
	<button id="chatBtn">1:1 채팅문의</button>
	
	
	
	
</body>
</html>