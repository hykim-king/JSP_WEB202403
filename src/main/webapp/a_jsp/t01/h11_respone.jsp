<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    //http://localhost:8081/studyhtml5/jsp/j01/h11_response_redirect.jsp?userId=pcwk
    String userId = request.getParameter("userId");
    System.out.println("userId:"+userId);
    
    if(null !=userId && userId.equals("pcwk") ){
        String value = "자바";
        String encodedValue = URLEncoder.encode(value,"UTF-8");
        
      response.sendRedirect("index.jsp?name="+value);
    }

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h2>response.sendRedirect</h2>
    <hr/>

    <div>잘못된 아이디 입니다.</div>
</body>
</html>