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
    <h2>request 헤더 정보</h2>
    <hr/>
    <!-- 
request 헤더 정보:
Http 프로토콜은 헤더 정보에 부가적인 정보를 담 을수 있다. 
웹 브라우저 정보, 요청의 특성, 콘텐츠에 대한 설명 등을 포함할 수 있습니다.     
    
host, localhost:8080
connection, keep-alive
cache-control, max-age=0
sec-ch-ua, "Google Chrome";v="125", "Chromium";v="125", "Not.A/Brand";v="24"
sec-ch-ua-mobile, ?0
sec-ch-ua-platform, "Windows"
upgrade-insecure-requests, 1
user-agent, Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36
accept, text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
sec-fetch-site, none
sec-fetch-mode, navigate
sec-fetch-user, ?1
sec-fetch-dest, document
accept-encoding, gzip, deflate, br, zstd
accept-language, ko,en-US;q=0.9,en;q=0.8,ko-KR;q=0.7
cookie, JSESSIONID=F6C91ED54E1BD6ADB02098878583E5DD    
     -->
     
     <%
      Enumeration<String> headerEnum = request.getHeaderNames();
      while(headerEnum.hasMoreElements() == true){
    	  String headerName  = headerEnum.nextElement();
    	  
    	  String headerValue = request.getHeader(headerName);
    	  
    	  out.print("<p>"+headerName +", "+headerValue +"</p>" );
      }
      
     %>
</body>
</html>