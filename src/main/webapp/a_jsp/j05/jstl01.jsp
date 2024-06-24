<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    request.setAttribute("name", "이상무");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
<script src="/WEB02/assets/js/jquery_3_7_1.js"></script>
</head>
<body>
    <h2>EL</h2>
    <hr/>
    요청URI :${pageContext.request.requestURI }<br/>
    name :${requestScope.name}<br/>
    param:${param.code }
    
</body>
</html>