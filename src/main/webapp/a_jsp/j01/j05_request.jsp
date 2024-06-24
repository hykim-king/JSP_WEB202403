<%--
request
request객체는 클라이언트 요청 정보를 포함 합니다. 
이를 통해 클라이언트가 보내 파라미터, 헤더, 쿠키 등을 가져올 수 있습니다.
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
</head>
<body>
    <h2>request 내장객체</h2>
    <hr/>
    
    서버이름 : <%= request.getServerName() %><br/>
    서버PORT : <%= request.getServerPort() %><br/>
    ClientIP : <%= request.getRemoteAddr() %><br/>
    프로토콜 : <%= request.getProtocol() %><br/>
    서버URL  : <%= request.getRequestURI() %><br/>
    서버컨텍스트 :<%=request.getContextPath() %>
    <!-- 
서버이름 : localhost
서버PORT : 8080
ClientIP : 0:0:0:0:0:0:0:1
프로토콜 : HTTP/1.1
서버URL : /WEB02/a_jsp/j01/j05_request.jsp
서버컨텍스트 :/WEB02    
     -->
    
</body>
</html>