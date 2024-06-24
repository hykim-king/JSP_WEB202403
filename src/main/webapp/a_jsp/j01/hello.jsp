<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 
  hello.jsp -> *.java(Servlet) -> *.class -> *.html (브라우저)
 -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello</title>
</head>
<body>
  <h2>Hello, world</h2>
  <hr/>
  오늘 날짜 : <%= new Date() %>
</body>
</html>