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
    <h2>JSTL함수 사용</h2>
    <hr/>
    <c:set var="str1" value="Functions 태그를 사용 합니다." />
    <c:set var="str2" value="사용 " />
    
    <p>length(str1): ${fn:length(str1) }</p>
    <p>toUpperCase(str1): ${fn:toUpperCase(str1) }</p>
    <p>substring(str1,3,6): ${fn:substring(str1,3,6) }</p>
    <p>trim(str2): ${fn:trim(str2) }, ${str2 }</p>
</body>
</html>