<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 

웹 브라우저 캐시는 서버 지연을 줄이기 위해 웹페이지, 이미지, CSS 기타 문서들을 임시 저장하기 위한 기술이다. 
<%@ include file="/WEB02/cmn/no_cache.jsp" %>
--%>
<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP/1.1
		response.setHeader("Pragma", "no-cache"); // HTTP/1.0
		response.setDateHeader("Expires", 0); // Proxies
		
		out.print("웹 브라우저 캐시");
%>