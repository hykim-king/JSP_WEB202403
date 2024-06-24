<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %> <!-- html 출력 상단 5줄 enter제거 -->
  
<%
    Date d=new Date();

%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
</head>
<body>
    <h2>page속성</h2>
    <hr/>
    
    <p>날짜=<%=d %></p>
    
    <%
      //java code
      for(int i=1;i<=10;i++){
    
    %>
       <p><%=i %></p>
    <%
      }// 
    %>

<%--

contentType, 응답 MIME(Multipurpose Internet Mail Extensions) 타입과 문자 인코딩을 설정 합니다. 
language="java"
pageEncoding="UTF-8" JSP 페이지 파일의 문자 인코딩을 설정 합니다.
--%>
  
</body>
</html>