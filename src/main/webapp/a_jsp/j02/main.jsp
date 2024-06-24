<%@page import="com.pcwk.ehr.cmn.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String name = StringUtil.nvl(request.getParameter("name"),"");

%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
</head>
<body>
    <h2>main</h2>
    <hr/>
    name:<%=name %>
</body>
</html>