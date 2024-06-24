<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
<script src="/WEB02/assets/js/jquery_3_7_1.js"></script>
</head>
<body>
    <h2>jstl</h2>
    <hr/>
    <c:set var="greeting" value="Hello, JSTL!" />
    <h1>${greeting}</h1>

    <c:if test="${greeting == 'Hello, JSTL!'}">
        <p>The greeting is correct.</p>
    </c:if>

    <c:forEach var="i" begin="1" end="10">
        <p>Number: ${i}</p>
    </c:forEach>    
</body>
</html>