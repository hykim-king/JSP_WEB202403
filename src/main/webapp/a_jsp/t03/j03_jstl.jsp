<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
<script src="/WEB02/assets/js/jquery_3_7_1.js"></script>
</head>
<body>
    <h2>EL_비교 연산자</h2>
    <hr/>
	  <p>1 == 1: ${1 == 1}</p>
	  <p>1 eq 1: ${1 eq 1}</p>
	  <p>1 != 2: ${1 != 2}</p>
	  <p>1 ne 2: ${1 ne 2}</p> 
	  <p>1 < 2: ${1 < 2}</p>
	  <p>1 lt 2: ${1 lt 2}</p>
	  <p>2 > 1: ${2 > 1}</p>
	  <p>2 gt 1: ${2 gt 1}</p>
	  <p>1 <= 2: ${1 <= 2}</p>
	  <p>1 le 2: ${1 le 2}</p>
	  <p>2 >= 1: ${2 >= 1}</p>
	  <p>2 ge 1: ${2 ge 1}</p>
</body>
</html>