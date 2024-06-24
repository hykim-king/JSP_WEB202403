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
    <h2>쿠키삭제</h2>
    <hr/>
    <%
        Cookie[]  cookies = request.getCookies();
        if( null != cookies  && cookies.length > 0){
        	
        	for(Cookie cookie :cookies){
        		
        		//쿠키 이름이 pcwk
        		if(cookie.getName().equals("pcwk")){
        			//별도삭제 메서드 없음: 데이터 초기화, setMaxAge(0)
        			Cookie modCookie=new Cookie("pcwk","" );
        			modCookie.setMaxAge(0);
        			
        			//생성한 쿠키 브라우저로 전송
        			response.addCookie(modCookie);
        			
        		}
        	}
        	
        	
        }
    
    %>
</body>
</html>