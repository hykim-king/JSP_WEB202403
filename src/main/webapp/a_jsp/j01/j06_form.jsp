<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String cPath = request.getContextPath();
    out.print("cPath:"+cPath);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
</head>
<body>
    <h2>form요청</h2>
    <hr/>
    <!-- 
http://localhost:8080/WEB02/a_jsp/j01/j06_request.jsp?user_nm=james&cellphone=01012345678&lang=10&lang=20&lang=30    
    
     -->   
    <form action="<%=cPath%>/a_jsp/j01/j06_request.jsp" method="get">
      <label for="user_nm">이름</label>
      <input type="text" name="user_nm" id="user_nm" size="10"><br>
      <label for="cellphone">핸드폰</label>
      <input type="tel" name="cellphone" id="cellphone" size="15"><br/>
      
      <label><input type="checkbox" name="lang" value="10" >자바</label>
      <label><input type="checkbox" name="lang" value="20" >html</label>
      <label><input type="checkbox" name="lang" value="30" >css</label>
      <label><input type="checkbox" name="lang" value="40" >javascript</label>      
      <input type="submit" value="전송">
    </form>
    
</body>
</html>