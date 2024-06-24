<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   out.print("오늘은 즐거운 수요일!");
   out.println("내일은 더 즐거운 목요일!");

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
</head>
<body>
    <h2>out내장 객체</h2>
    <hr/>
<%
		out.print("오늘은 즐거운 수요일!");
    out.newLine();//줄바꿈 문자(\n, \r\n)
		out.println("내일은 더 즐거운 목요일!");
		
%>    
</body>
</html>