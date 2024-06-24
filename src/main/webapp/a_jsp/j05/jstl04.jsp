<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
<script src="/WEB02/assets/js/jquery_3_7_1.js"></script>
</head>
<body>
    <h2>JSTL Core</h2>
    <hr/>
    <c:set var="greeting" value="Hello, JSTL!"></c:set>
    <h3>${greeting }</h3>
    
    <c:if test="${greeting  !='Hello, JSTL!'  }">
      <p>greeting이 맞아요!</p>
    </c:if>
    
</body>
</html>