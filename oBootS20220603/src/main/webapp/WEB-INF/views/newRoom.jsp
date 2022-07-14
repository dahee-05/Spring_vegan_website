<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="js/jquery.js"></script>
</head>
<body>
	<div class="container">

    <form class="form-signing" action="/room/new" method= "post">
        <h2 class="form-signing-heading">새로운 채팅방 생성</h2>
        <label for="title" class="sr-only">채팅방 이름</label>
        <input type="text" name="name" id="title" class="form-control" placeholder="채팅방 이름을 입력해주세요" required autofocus>
        <br>
        <button class="btn btn-primary" type="submit">생성하기</button>
        <button class="btn btn-danger" onclick = "location.href='/'">돌아가기</button>
    </form>

</div> <!-- /container -->

</body>
</html>