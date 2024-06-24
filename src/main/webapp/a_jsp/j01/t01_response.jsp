<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*, javax.servlet.http.*, javax.servlet.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Response Object Example</title>
</head>
<body>
    <h1>Response Object Example</h1>
    <%
        // ContentType 설정
        response.setContentType("text/html;charset=UTF-8");

        // Character Encoding 설정
        response.setCharacterEncoding("UTF-8");

        // Status 설정
        response.setStatus(HttpServletResponse.SC_OK);  // 200 OK

        // Header 설정
        response.setHeader("Custom-Header", "HeaderValue");

        // Cookie 추가
        Cookie cookie = new Cookie("username", "JohnDoe");
        cookie.setMaxAge(60*60);  // 1 hour
        response.addCookie(cookie);

        // 에러 전송 (주석 처리된 부분은 실제 사용 시 활성화)
        // response.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found");

        // 리다이렉트 (주석 처리된 부분은 실제 사용 시 활성화)
        // response.sendRedirect("https://cafe.daum.net/pcwk");

        // 응답 본문 작성
        PrintWriter outP = response.getWriter();  
        outP.println("<p>Hello, world!</p>");
    %>
</body>
</html>
