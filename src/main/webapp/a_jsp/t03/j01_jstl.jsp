<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <h2>EL_01</h2>
    <hr/>
    요청 URI :${pageContext.request.requestURI}<br/>
    요청 name :${requestScope.name}<br/>
    code 파람 :${param.code}<br/>    
</body>
</html>