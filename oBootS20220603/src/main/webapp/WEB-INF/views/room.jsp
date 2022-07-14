<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<style type="text/css">
	#chatroom p {
        display: block;
	    margin-block-start: 1em;
	    margin-block-end: 1em;
	    margin-inline-start: 5px;
	    margin-inline-end: 5px;
	    margin-bottom: 1rem;
	}


	#chatRoomInfo {
		width:350px;
		height:40px;
		background-color:#8AB758;
		border:1px solid #8AB758;
	}
	
	#roomName {
		color:#ffffff;
		font-size:20px;
		font-weight:bold;
		line-height:40px;
		padding-left:10px;
	}
	
	
	.default_msg {
		width:100%;
		text-align:center;
	}
	
	.my_p {
		text-align:right;
	}
	
	.your_p {
		text-align:left;
	}
	
	.my_msg {
		display:inline-block;
		background-color:#FBE2C3;
		padding: 8px 10px 8px 10px;
    	border-radius: 7px;
    	max-width:280px;
    	word-break:break-all;
    	text-align:left;
	}
	.your_msg {
		display:inline-block;
		background-color:#FBE2C3;
		padding: 8px 10px 8px 10px;
   		border-radius: 7px;
   		max-width:280px;
   		word-break:break-all;
	}
	
	#exit {
	    font-weight: bold;
	    font-size: 14px;
	    float: right;
	    margin-right: 10px;
	    margin-top: 6px;
	    padding: 3px 3px 3px 3px;
	}
	
	#send {
		display:block;
		width:50px;
		height:70px;
		font-size:14px;
		font-weight:bold;
		text-align:center;
		line-height:70px;
		background-color:#8AB758;
		float:right;
	}
	
	#chat_entire {
		display:inline;
	}
	
</style>
    
</head>
<body>
	<div id="chat_entire">
		<input type="hidden" name="session1" value="">
		<input type="hidden" name="session2" value="">
		
		<input type="hidden" id="nickname" value="${id }" required autofocus>
		
		<div id="chatRoomInfo">
			<input type="hidden" id="roomId_input" value="${room.roomId }">
			
			<span id="roomName">${room.name}님의 채팅 문의</span>
			<button id="exit" onclick="exit()" class="btn btn-warning">나가기</button>
		</div>
		
		<div id = "chatroom" style = "overflow:auto; overflow-x:hidden; width:350px;  height: 500px; border:1px solid; background-color:#ffffff;">
			<p class="default_msg">상담원과 연결중입니다. 잠시만 기다려주세요.</p>
		</div>
		
		<div id="text_input_box">
			<!-- <input type = "text" id = "message" style = "height : 30px; width : 300px" placeholder="내용을 입력하세요" autofocus> -->
			<textarea id = "message" placeholder="내용을 입력하세요" style = "height : 70px; width : 300px; padding:6px" autofocus></textarea>
			<button id = "send">전송</button>
		</div>
	</div>
<script>

</script>

</body>

<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript">
    var webSocket;
    var nickname = $("#nickname").val();
    var roomId = $("#roomId_input").val();
    
    $(document).ready(function() {
    	connect();
    });

    document.getElementById("send").addEventListener("click",function(){
        send();
    })
    document.getElementById("message").addEventListener("keyup", function (event) {
        if (event.keyCode === 13) {
            send();
        }
    });

    function connect(){
		//alert("connect 시작")
        webSocket = new WebSocket("ws://localhost:8380/chat");
        webSocket.onopen = onOpen;
        webSocket.onclose = onClose;
        webSocket.onmessage = onMessage;
    }
    function disconnect(){
    	//alert("웹소켓 close");
    	//roomId = $("#roomId_input").val();
        webSocket.send(JSON.stringify({chatRoomId : roomId,type:'LEAVE',writer:nickname}));
        webSocket.close();
    }
    function send(){
    	//roomId = $("#roomId_input").val();
        msg = document.getElementById("message").value;
        if(msg != "") {
        	webSocket.send(JSON.stringify({chatRoomId : roomId,type:'CHAT',writer:nickname,message : msg}));
            document.getElementById("message").value = "";	
        }
        
    }
    function onOpen(){
    	//alert("onOpen()");
    	//roomId = $("#roomId_input").val();
    	//alert("roomId->"+roomId);
        webSocket.send(JSON.stringify({chatRoomId : roomId,type:'ENTER',writer:nickname}));
    }
    function onMessage(e){
    	//alert("onMessage()");
        data = e.data;
        var jsonMsg = JSON.parse(data);
        chatroom = document.getElementById("chatroom");
        //chatroom.innerHTML = chatroom.innerHTML + "<br>" + data;
        
        if(jsonMsg.type=="ENTER") {
        	if($("input[name=session1]").val() == null || $("input[name=session1]").val()=="") {
        		$("input[name=session1]").attr('id', jsonMsg.writer);
        		$("#"+jsonMsg.writer).val(jsonMsg.sessionId);
        	} else if($("input[name=session1]").val() != null || $("input[name=session1]").val()!=""){
        		$("input[name=session2]").attr('id', jsonMsg.writer);
        		$("#"+jsonMsg.writer).val(jsonMsg.sessionId);
        	}
        	
        	//$("#sessionId").val(jsonMsg.sessionId);
        	chatroom.innerHTML = chatroom.innerHTML + "<p class='default_msg'>" + jsonMsg.message + "</p>";
        }
        else if(jsonMsg.type=="LEAVE") {
        	chatroom.innerHTML = chatroom.innerHTML + "<p class='default_msg'>" + jsonMsg.message + "</p>";
        }
        else if(jsonMsg.type=="CHAT"){
        	if(jsonMsg.sessionId == $("#"+nickname).val()) {
        		chatroom.innerHTML = chatroom.innerHTML + "<p class='my_p'><span class='my_msg'>" + jsonMsg.message + "</span></p>";
        	} else {
        		chatroom.innerHTML = chatroom.innerHTML + "<p class='your_p'><span class='your_msg'>" + jsonMsg.message + "</span></p>";
        	}
        }
        
        //chatroom.innerHTML = chatroom.innerHTML + "<p>" + data + "</p>";
    }
    function onClose(){
        disconnect();
    }

</script>

</html>