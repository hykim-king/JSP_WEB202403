<%@page import="java.util.Enumeration"%>
<%@page import="com.pcwk.ehr.cmn.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
</head>
<body>
    <h2>form->jsp전송</h2>
    <hr/>
<%
  //request에 들어오는 이름을 모두 추출
  Enumeration<String> paramNames =  request.getParameterNames();

  while(paramNames.hasMoreElements() == true){
    String name = paramNames.nextElement();
    
    out.print("<p>"+name+" :"+request.getParameter(name)+"</p>");
  }
%>  
    
</body>
</html>