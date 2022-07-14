<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ include file="header.jsp" %>  

<%
	String context = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/httpRequest.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
<title>회원 로그인 | 비숲</title>
 <link rel="stylesheet" type="text/css" href="assets/css/loginForm.css">
</head>
<script>
//익명 펑션, 화면 로드된 후(html그려진후) 실행 
	window.onload=function(){
		document.querySelector(".IdFindPw").style.display="none";
		
	}
	function findPw(){ 
		//조건 확인
		if(document.querySelector(".IdFindPw").style.display == 'none'){
			
			 document.querySelector(".IdFindPw").style.display="";
			
		} else{
			////display==none숨김
			document.querySelector(".IdFindPw").style.display="none";
			
		}
		
	}
	
	// [로그인] 아이디 비밀번호 검증
	function valueCheck() {
			
		var isSubmit=false;
		var id= $('#id').val();
		var pw= $('#pw').val();

		$.ajax(
			{
				url:"<%=context%>/jhLoginCheckU",
				data: {id:id, pw:pw},
				dataType: 'text',
				async:false,
				success: function(data){
					if(data==1){
						isSubmit=true;
						
					}else{
						alert('아이디 비밀번호를 확인하세요')
						isSubmit= false;
					}
				}
				
			}		
		
		)
		if(!isSubmit) return false;
	}
	
	
	//[로그인] id 있나 없나 확인 
	function idCheck() {
		var id= $('#userid').val();
			alert('userid->'+id)
	
			$.ajax(
				{
					url:"<%=context%>/jhFindPwIdCheckU",
					data: {id:id},
					dataType: 'text',
					success: function(data){
						alert('data->'+data)
						if(data==1){
							alert('존재하는 아이디 입니다. 비밀번호 찾기를 진행해주세요.')
							$('#id').val(id);
							
						}else{
							alert('존재하지 않는 아이디 입니다. 회원가입을 진행해주세요.')
						}
					}
					
				}		
			)
		}
	
	
	// 임시 비밀번호 발송 
	function pwEmail() {
		var id= $('#userid').val();
		alert('userid->'+id)
		
		$.ajax(
			{
				url:"<%=context%>/jhFindPwEmailGetU",
				data: {id:id},
				dataType: 'text',
				success:function(data){
					if(data==1){
						alert('data->'+data);
						alert('가입하신 이메일로 임시번호를 발송해드렸습니다.');
						location.replace("jhLoginFormU");
					}else{
						alert('이메일 전송에 실패했습니다. 다시 시도해주세요.')
					}
				}
			}		
		)
	}
	
</script>

<body>
 <div class="authentication-inner">
        <div class="card">
 
            <div>
                <h3>Welcome to 비숲</h3><br>
                <p>로그인이 필요합니다!</p>
            </div>
           	<c:if test = "${msg!=null }">${msg }</c:if>
			
			<form action = "jhLoginSucU" name="frm" method="post" onsubmit="return valueCheck()">
				<div>
					<input class="input" type="text" name="id" id="id" required="required" placeholder="아이디를 입력하세요">
				</div>
				<div>
					<input class="input" type="password" name="pw" id="pw" required="required" placeholder="비밀번호를 입력하세요">
				</div>
				<button class= "button_area" type="submit">로그인</button> <br>
			</form>
			
			<!-- 화면으로 이동 -->
			<a href="#" onclick=findPw()>비밀번호 찾기</a><br>
			<div class="IdFindPw">
				<div class="input-group mb-3">
				  <input class="input" type="text"  placeholder="아이디를 입력하세요" id="userid" style="width: 240px;">
				  <button class="button_area"  id="button-addon2" onclick="idCheck()" style="width: 50px;">id체크</button>
				</div>
				<button class="button_area" onclick="pwEmail()" style="margin-top: 0px;">비밀번호찾기</button> 
			</div>
			<div class="joingo"> 아직 회원이 아니신가요? 
				<a href="uYjJoinForm">회원가입</a>
			</div>
        </div>
    </div>
    

			
	
	


</body>
</html>