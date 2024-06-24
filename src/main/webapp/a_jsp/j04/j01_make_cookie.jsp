<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
</head>
<body>
    <h2>쿠키 생성</h2>
    <hr/>
<%
    Cookie cookie=new Cookie("pcwk",URLEncoder.encode("오늘은 즐거운 금요일", "UTF-8") );
    cookie.setMaxAge(-1);//session 쿠키 
    
    response.addCookie(cookie);
%>    
</body>
</html>