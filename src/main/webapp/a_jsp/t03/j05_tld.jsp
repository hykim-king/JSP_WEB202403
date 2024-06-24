<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://ehr.pcwk.com/tags" prefix="formatUtil" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
<script src="/WEB02/assets/js/jquery_3_7_1.js"></script>
</head>
<body>
    <h2></h2>
    <hr/>
    <p>Formatted Number: <formatUtil:formatNumber number="123456789" pattern="#,###.00" /></p>
</body>
</html>