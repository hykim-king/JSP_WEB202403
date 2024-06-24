<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
1. 스크립트릿 : <% 자바코드 %>
2. 표현식 : <%= %>  변수 데이터 출력, ';'금지
3. 선언부 : <%!   %> 자바에 함수, 변수 만들기, 비권장
 --%>    
<%
  //지역 변수
  int sum = 0;

  for(int i=1;i<=100;i++){
    sum+=i;
  }

%>

<%!
   //전역 변수
   String[] strArray = {"jsp가", " 재미 ", "있다."};

%>
 
 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
</head>
<body>
    <h2>스크립 요소</h2>
    <hr/>
    <div>
      <p>합계:<%=sum %></p>
    </div>
    <div>
      <%
          for(String str   :strArray){
        	  out.print("<p>"+str+"</p>");
          }
      %>
    
    </div>
    
    
</body>
</html>

