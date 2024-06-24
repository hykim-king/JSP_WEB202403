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
    <h2>JSTL choose</h2>
    <hr/>
    <c:set var="dayOfWeek" value="Thursday" />
    
    <c:choose>
      <c:when test="${dayOfWeek == 'Monday' }">
        <p>오늘은 월요일 입니다.</p>
      </c:when>
      <c:when test="${dayOfWeek == 'Tuesday' }">
        <p>오늘은 화요일 입니다.</p>
      </c:when>    
      <c:when test="${dayOfWeek == 'Wednesday' }">
        <p>오늘은 수요일 입니다.</p>
      </c:when>   
        <c:when test="${dayOfWeek == 'Thursday'}">
            <p>오늘은 목요일입니다.</p>
        </c:when>
        <c:when test="${dayOfWeek == 'Friday'}">
            <p>오늘은 금요일입니다.</p>
        </c:when>      
      <c:otherwise>
        <p>오늘은 주말 입니다.</p>
      </c:otherwise> 
    </c:choose>
    

    
</body>
</html>