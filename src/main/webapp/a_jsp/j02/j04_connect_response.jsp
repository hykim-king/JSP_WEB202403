<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String userName = (String)request.getAttribute("userNm");

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
</head>
<body>
    <h2>/a_jsp/j02/j04_connect_response.jsp</h2>
    <hr/>
    userName:<%=userName %>
</body>
</html>