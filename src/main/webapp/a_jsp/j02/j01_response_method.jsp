<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Response Method</title>
</head>
<body>
    <h2>Response Method</h2>
    <hr/>
<%
    //ContentType설정
    response.setContentType("text/html; charset=UTF-8");

    //Character Encoding
    response.setCharacterEncoding("UTF-8");

    //Status 설정
    response.setStatus(HttpServletResponse.SC_OK);
    
    //Header 설정:?
    response.setHeader("PCWK-Header", "즐거운 화요일");
    
    //Cookie추가
    Cookie cookie=new Cookie("username","Goodman");
    cookie.setMaxAge(60*60);//1 hour
    response.addCookie(cookie);
    
    //에러 전송
    //response.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found 한!");
    
    //리다이렉트
    //response.sendRedirect("https://cafe.daum.net/pcwk");
    
    //응답 본문 작성
    PrintWriter printW = response.getWriter();
    printW.println("<p>오늘은 즐거운 화요일, 내일은 더 즐거운 수요일!</p>");
    
%>    
</body>
</html>