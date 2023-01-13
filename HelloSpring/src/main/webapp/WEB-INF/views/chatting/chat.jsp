<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅창</title>
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.6.1.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/chatting.js"></script>
</head>
<body>
	<h3>spring chatting</h3>
	<div id="chattingcontainer"></div>
	<input id="msg"><button id="sendBtn">전송</button>
	
	<script>
		const websocket=new WebSocket("ws://localhost:9090/spring/chatting");
		
		websocket.onopen=(data)=>{
			console.log(data);
			websocket.send(JSON.stringify(new Message("open",'${loginMember.userId}',"","","","")));
		}
		
		websocket.onmessage=(response)=>{
			console.log(response);
			const msg=JSON.parse(response.data);
			console.log(msg);
			switch(msg.type){
				case "system" : addMsgSystem(msg);break; 
				case "msg" : printMsg('${loginMember.userId}',msg);break;
			}
		}
		
		
		$("#sendBtn").click(e=>{
			const msg=$("#msg").val();
			const sendData=new Message("msg",'${loginMember.userId}',"",msg,"");
			websocket.send(JSON.stringify(sendData));
		});
		
		class Message{
			constructor(type,sender,reciever,msg,room){
				this.type=type;
				this.sender=sender;
				this.reciever=reciever;
				this.msg=msg;
				this.room=room;
			}
		}
	</script>	
	
	
	
</body>
</html>