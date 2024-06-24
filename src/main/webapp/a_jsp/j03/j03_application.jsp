<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
</head>
<body>
    <h2>application내장 객체</h2>
    <hr/>
<%
   Enumeration<String> initParam =application.getInitParameterNames();
   while(initParam.hasMoreElements() == true){
	   String initParamName = initParam.nextElement();
	   out.print("initParamName:"+initParamName +"<br/>");
	   out.print("value:"+application.getInitParameter(initParamName)+"<br/>");
   }


%>    
</body>
</html>