<%@page import="java.net.URLEncoder"%>
<%@page import="com.pcwk.ehr.cmn.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String userId = StringUtil.nvl(request.getParameter("user_id"),"");
    System.out.println("userId:"+userId);

    if(null != userId && userId.equals("pcwk")){
    	//String value ="java";
    	String value ="java1_자바";
    	String encodedValue = URLEncoder.encode(value, "UTF-8");
    	//encodedValue:java1_%EC%9E%90%EB%B0%94
    	System.out.println("encodedValue:"+encodedValue);
    	response.sendRedirect("main.jsp?name="+encodedValue);
    }
    

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
</head>
<body>
    <h2>response.sendRedirect</h2>
    <hr/>
    <p>잘못된 아이디 입니다.</p>
</body>
</html>