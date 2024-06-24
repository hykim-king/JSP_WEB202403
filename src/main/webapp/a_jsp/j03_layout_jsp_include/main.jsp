<%@page import="com.pcwk.ehr.cmn.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>    
<%@ include file="/cmn/no_cache.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
</head>
<body>
  <!-- header -->
  <header>
    <h2> WelCome to My WebSite</h2>
    <nav>
      <ul>
         <li><a href="/WEB02/a_jsp/j03_layout_jsp_include/main.jsp?page_name=index">Home</a></li> 
         <li><a href="/WEB02/a_jsp/j03_layout_jsp_include/main.jsp?page_name=about">About</a></li> 
         <li><a href="/WEB02/a_jsp/j03_layout_jsp_include/main.jsp?page_name=contact">Contact</a></li> 
      </ul>
    </nav>
  </header>
  <!--// header end -->
  <%
     String pageName = StringUtil.nvl(request.getParameter("page_name"),"");
     if(pageName.equals("about")){
  %>
     <jsp:include page="about.jsp" flush="false"/>

  <%
     }else if(pageName.equals("contact")){
  %>
     <jsp:include page="contact.jsp"/>
  <%
     }else if(pageName.equals("contact")){
  %>
     <jsp:include page="index.jsp"/>
  <%
     }else{
  %>
      page not found!
  <%
     }
  %>

  <!-- footer -->
  <footer>
    <p>&copy; 2024 My WebSite</p>
  </footer>
  <!--// footer end -->
</body>
</html>