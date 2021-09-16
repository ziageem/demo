<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<head>
    <title>Index</title>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
</head>
<body style="text-align: center">
방 페이지
<div>
    <h1>${room.name}</h1>
    <c:set var="roomName" value="${room.name}"/>
    <c:set var="roomId" value="${room.roomId}"/>
    <input type="hidden" id="roomId2" value="${room.roomId}"/>
    <br/>
    <div id="msgArea"></div>
    <div>
        <input type="text" id="msg">
        <button type="button" id="button-send">전송</button>
    </div>
</div>
</body>
<script type="text/javascript" language="javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        var roomName='<c:out value="${roomName}"/>';
        var roomId='<c:out value="${roomId}"/>';
        var username='홍길동';

        console.log(roomName+", "+roomId+", "+username);
        //alert(roomId+"입장하셨도다");

        var sockJs=new SockJS("/stomp/chat");

        var stomp=Stomp.over(sockJs);
        
        stomp.connect({}, function(){
            console.log("STOMP Connection");

            stomp.subscribe("/sub/chat/room/"+roomId, function(chat){
                var content=JSON.parse(chat.body);
                var writer=content.writer;
                var message=content.message;
                console.log("#message: "+message);

                var str='';
                if(writer===username){
                    str="<div>";
                    str+="<div>";
                    str+="<b>"+writer+" : "+message+"</b>";
                    str+="</div>";
                    str+="</div>";
                    console.log("같은 경우");
                    $("#msgArea").append(str);
                }else{
                    str="<div>";
                    str+="<div>";
                    str+="<b>"+writer+" : "+message+"</b>";
                    str+="</div>";
                    str+="</div>";
                    console.log("다른 경우");
                    $("#msgArea").append(str);
                }
                //$("#msgArea").append(str);
            });
            stomp.send('/pub/chat/enter',{},JSON.stringify({roomId:roomId, writer:username}))
        });

        $("#button-send").on("click", function(e){
            var msg=document.getElementById("msg");
            var roomId2=document.getElementById("roomId2");
            stomp.send('/pub/chat/message',{},JSON.stringify({roomId:roomId2.value, writer:username, message:msg.value}))
            msg.value='';
        });
    });
</script>
</html>