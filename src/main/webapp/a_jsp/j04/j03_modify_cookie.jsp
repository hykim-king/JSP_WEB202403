<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
</head>
<body>
    <h2>쿠키수정: 기존이름에 새로운 데이터를 넣기</h2>
    <hr/>
    <%
        Cookie[]  cookies = request.getCookies();
        if( null != cookies  && cookies.length > 0){
        	
        	for(Cookie cookie :cookies){
        		
        		//쿠키 이름이 pcwk
        		if(cookie.getName().equals("pcwk")){
        			Cookie modCookie=new Cookie("pcwk",URLEncoder.encode("오늘은 즐거운 금요일-수정", "UTF-8") );
        			
        			//생성한 쿠키 브라우저로 전송
        			response.addCookie(modCookie);
        			
        		}
        	}
        	
        	
        }
    
    %>
</body>
</html>