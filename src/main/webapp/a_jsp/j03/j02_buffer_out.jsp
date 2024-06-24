<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page buffer="8kb" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
</head>
<body>
    <h2>out객체</h2>
    <hr/>
    <%
        //출력
		    out.print("오늘은 즐거운 수요일!<br/>");
		    out.newLine();//줄바꿈 문자(\n, \r\n)
		    out.println("내일은 더 즐거운 목요일!<br/>");
    
        //버퍼 크기 출력
        out.println("Buffer Size:"+out.getBufferSize()+" bytes<br/>");
        out.println("Remaining Buffer Size:"+out.getRemaining()+" bytes<br/>");
        
        //강제 플러시
        out.flush();
        out.println("This is flushed content!<br/>");
        
        //버퍼를 지우기
        out.clearBuffer();
        out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
    
    %>
</body>
</html>