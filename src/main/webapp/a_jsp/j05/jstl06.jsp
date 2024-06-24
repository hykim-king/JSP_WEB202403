<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
<script src="/WEB02/assets/js/jquery_3_7_1.js"></script>
</head>
<body>
    <h2>JSTL forEach</h2>
    <hr/>
    
    <h4>1 부터 10까지</h4>
    <c:set var="total" value="0"/>
    <c:forEach var="i" begin="1" end="10" step="1">
      <c:set var="sum" value="${sum+i }"/>
    </c:forEach>
    
    결과: ${sum }
    
</body>
</html>