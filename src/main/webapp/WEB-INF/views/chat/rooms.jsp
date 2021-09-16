<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<head>
    <title>Index</title>

    <script type="text/javascript" language="javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
</head>
<body style="text-align: center">
채팅방 리스트 페이지
<div>
    <c:forEach items="${list}" var="list">
        <li><a href="chat?roomId=${list.roomId}">${list.name}</a></li>
    </c:forEach>
    <form action="chat" method="post">
        <input type="text" name="name">
        <button id="makeChat">개설하기</button>
    </form>
</div>
</body>
</html>