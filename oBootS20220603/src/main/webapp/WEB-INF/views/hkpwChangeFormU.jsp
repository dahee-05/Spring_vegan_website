<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<%
	String context = request.getContextPath();
%>
<meta charset="UTF-8">
<title>비숲 | 비밀번호 변경</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="css/common.css" rel="stylesheet" type="text/css">
<link href="css/pwChange.css" rel="stylesheet" type="text/css">
<link href="css/mypage_contents.css" rel="stylesheet" type="text/css">
<link href="css/mypage.css" rel="stylesheet" type="text/css">
<link href="css/mypagejh.css" rel="stylesheet" type="text/css">

<script src="js/jquery.js"></script>

<script type="text/javascript">
	
	
	function chk() {
		var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
		var id = $("#user_id").val();
		var pw = $("#pw").val();
		var new_pw = $("#new_pw").val();
		
		if(frm.new_pw.value != frm.new_pw2.value) {
			$('#fontpw2').html('비밀번호가 일치하지 않습니다.');
			$('#fontpw2').css('color','red');
			frm.new_pw2.focus();
			return false;
		}
		if(pw == new_pw) {
			alert("현재 비밀번호와 동일한 비밀번호로는 변경할 수 없습니다.");
			$('#new_pw').val("");
			$('#new_pw').focus();
		}
		if(!reg.test(new_pw)) {
			$('#fontpw1').html('비밀번호는 8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.');
			$('#fontpw1').css('color','red');
			$('#new_pw').val("");
			$('#new_pw').focus();
			return false;
		}
		
		
		return true;
		
	}
	
	$(function() {
		$("#new_pw").change(function() {
			var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
			var new_pw = $("#new_pw").val();
			if(reg.test(new_pw)) {
				$("#fontpw1").html("");
			} else {
				$('#fontpw1').html('비밀번호는 8자 이상이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.');
				$('#fontpw1').css('color','red');
			}
			
			if(frm.new_pw.value == frm.new_pw2.value) {
				$("#fontpw2").html("");
			} else {
				$('#fontpw2').html('비밀번호가 일치하지 않습니다.');
				$('#fontpw2').css('color','red');
			}
		});
		
		$("#new_pw2").change(function() {
			if(frm.new_pw.value == frm.new_pw2.value) {
				$("#fontpw2").html("");
			} else {
				$('#fontpw2').html('비밀번호가 일치하지 않습니다.');
				$('#fontpw2').css('color','red');
			}
		});
	});
	
</script>

<style type="text/css">
	
</style>

</head>
<body>
	<c:if test="${not empty msg}">
		<input type="hidden" id="msg" value="${msg }">
		<script type="text/javascript">
			var msg = $("#msg").val();
			alert(msg);
		</script>
	</c:if>

	<div id="wrap">
		<jsp:include page="/WEB-INF/views/header.jsp"/>
<!-- ----------------------------------------------------------------------main시작----------------------------------- -->
		<div class="margin_wrap">
			<div class="main clearfix">
			<jsp:include page="/WEB-INF/views/mySnb.jsp"/>
<!-- ----------------------------------------------------------------------드롭다운 메뉴 시작---------------------------------->
<!-- ----------------------------------------------------------------------여기에 코딩 시작----------------------------------->
				<section class="sectionmy">
					<h2>비밀번호 변경</h2>
					<input type="hidden" id="user_id" value="${id }">
					<br>
					<hr>
					<br>
					<!-- 작업 영역 -->
					<form action="hkPwChangePro" method="post" onsubmit="return chk()" name="frm">
						<div class="input-group mb-3">
							<span class="input-group-text" id="inputGroup-sizing-default">현재 비밀번호</span>
							<input type="password" id="pw" name="pw" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" required="required">
						</div>
						
						<div class="blank"></div>
						
						<div class="input-group mb-3">
							<span class="input-group-text" id="inputGroup-sizing-default">새 비밀번호</span>
							<input type="password" id="new_pw" name="new_pw" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" required="required">
						</div>
						<span id="fontpw1"></span>
						
						
						<div class="input-group mb-3">
							<span class="input-group-text" id="inputGroup-sizing-default">비밀번호 확인</span>
							<input type="password" id="new_pw2" name="new_pw2" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" required="required">
						</div>
						<span id="fontpw2"></span>
						
						
						<input type="submit" class="btn btn-primary" value="변경">
						<input type="reset" class="btn btn-outline-primary" value="취소">	
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