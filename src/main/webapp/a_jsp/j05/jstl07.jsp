<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="price" value="900000" />
<fmt:formatNumber value="${price}" type="number" var="numberType" />       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
<script src="/WEB02/assets/js/jquery_3_7_1.js"></script>
</head>
<body>
    <h2>JSTL 숫자 formatting</h2>
    <hr/>
    <p>price:${price }</p>
    <p>number:${numberType }</p>
    <p>통화:<fmt:formatNumber value="${price }" type="currency"  currencySymbol="$" /></p>
    <p>퍼센트:<fmt:formatNumber  value="${price }"  type="percent"  /> </p>
    <p>패턴:<fmt:formatNumber  value="${price }"  pattern="0000000000.00"/>  </p>
</body>
</html>