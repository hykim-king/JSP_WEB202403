<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>행복코딩</title>
<style>
	* {
		margin: 0;
		padding: 0;
		box-sizing: border-box;
	}
	#header {
	  background-color: #5C007A;
	  width: 100%;
	  height: 120px;
	  line-height: 120px;
	}
	
	#logo {
	  float: left;
	  background-color: #5C007A;
	  width: 20%;
	}
	
	
	#header nav ul {
	    width: 80%;
	   
	    list-style: none;
	    height: 100%; /* 부모 요소의 높이와 동일하게 설정 */
	}
	#header nav ul li {
	    margin: 0 15px;
	    float: left;
	}
	
	#header nav ul li a {
	    color: white;
	    text-decoration: none;
	    font-size: 18px;
	    padding: 10px 20px;
	    transition: background-color 0.3s ease;
	}
	
	#header nav ul li a:hover {
	    background-color: #8E24AA; /* 호버 시 배경색 변경 */
	    border-radius: 5px; /* 모서리를 둥글게 */
	}
	
	#container {
		width: 1200px;
		margin: 20px auto;
	}
	
	#sidebar {
	  clear: both;
		background-color: #8E24AA;
		width: 100px;
		height: 600px;
		float: left;
	}
	
	#contents {
		background-color: #C158DC;
		width: 1100px;
		height: 600px;
		float: left;
	}
	
	#footer {
		background-color: #F8BDCD;
		width: 100%;
		height: 100px;
		line-height: 100px;
		clear: both;
	}
</style>
</head>
<body>
	<div id="container">
		<jsp:include page="header.jsp" />
		<jsp:include page="sidebar.jsp" />
		<%
			String pageName = request.getParameter("page");
			if ("board".equals(pageName)) {
		%>
		  <jsp:include page="board_list.jsp" />
		<%
		} else {
		%>
		  <jsp:include page="contents.jsp" />
		<%
		}
		%>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>
