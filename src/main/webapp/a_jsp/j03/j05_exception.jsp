<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
</head>
<body>
    <h2>익셉션 처리</h2>
    <hr/>
    <% try{ %>
         age : <%=request.getParameter("age").toLowerCase() %>
    <% }catch(NullPointerException e){ %>
         age파라메터가 올바라지 않습니다.
    <% }  %>
</body>
</html>