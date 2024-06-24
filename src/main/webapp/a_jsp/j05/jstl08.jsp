<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib  prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  prefix="fn"   uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
<script src="/WEB02/assets/js/jquery_3_7_1.js"></script>
</head>
<body>
    <h2>JSTL formatDate</h2>
    <hr/>
    <c:set  var="now" value="<%=new java.util.Date() %>"/>
    <p><fmt:formatDate value="${now }" dateStyle="full"/> </p>
    <p><fmt:formatDate value="${now }" dateStyle="short"/> </p>
    <p><fmt:formatDate value="${now }" type="time"/> </p>
    <p><fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/> </p>
    
    <p><fmt:formatDate value="${now }" pattern="z a h:mm"/> </p>
    
</body>
</html>