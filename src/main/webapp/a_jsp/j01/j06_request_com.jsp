<%@page import="com.pcwk.ehr.cmn.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    //요청으로 들어오는 데이터는 : String
    String userNm    =  StringUtil.nvl(request.getParameter("user_nm"),  "Anonymous");
    String cellphone =  StringUtil.nvl(request.getParameter("cellphone"), "01012345678");
    String lang      =  StringUtil.nvl(request.getParameter("lang"),"10");
    
    String langArrays[] = request.getParameterValues("lang");//배열
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
</head>
<body>
    <h2>form->jsp전송</h2>
    <hr/>
    이름 : <%=userNm %><br/>
    핸드폰 : <%out.print(cellphone); %><br/>
    좋아하는 프로그램 : <%=lang %><br/>
    
<%
    for(String str :langArrays){
    	  out.print(str+",");   	
    }

%>    
    
    
</body>
</html>