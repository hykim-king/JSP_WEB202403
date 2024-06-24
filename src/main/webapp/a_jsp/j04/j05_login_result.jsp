<%@page import="com.pcwk.ehr.member.OldMemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
</head>
<body>
    <h2>세션읽기</h2>
    <hr/>
    <%
    OldMemberDTO member = (OldMemberDTO)session.getAttribute("member");
           if(null != member){
            out.print("userId:"+member.getUserId()+"<br/>");
            out.print("name:"+member.getName());
           }else{
        	   out.print("logout");
           }
    %>
</body>
</html>