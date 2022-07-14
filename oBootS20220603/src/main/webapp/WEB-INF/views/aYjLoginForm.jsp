<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	
	function PwFind(){
		
		
	}
</script>
<body>
 <div class="authentication-inner">
        <div class="card">
 
            <div>
                <h3>관리자 모드 로그인</h3><br>
                <p>로그인이 필요합니다!</p>
            </div>
            	<c:if test = "${msg!=null }">${msg }</c:if>
				<form action = "aYjLogin" name="frm" method="post">
					<div>
						<input class="input" type="text" name="ad_id" required="required" placeholder="아이디를 입력하세요">
					</div>
					<div>
						<input class="input" type="password" name="ad_pw" required="required" placeholder="비밀번호를 입력하세요">
					</div>
					<button class= "button_area" type="submit">로그인</button> <br>
				</form>
					

        </div>
    </div>
    

			
	
	


</body>
</html>