<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
</head>
<body>
    <h2>application:서버정보</h2>
    <hr/>
    <%
        out.print("서버정보 : "+application.getServerInfo()+"<br/>");
        out.print("서블릿 API 메이저 버전 : "+application.getMajorVersion()+"<br/>");
        out.print("서블릿 API 마이너 버전 : "+application.getMinorVersion()+"<br/>");
    
    %>
</body>
</html>