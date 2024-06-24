<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://page.ehr.pcwk.com/tags/pagination"  prefix="pagination"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/WEB02/assets/css/bootstrap.css">
<script src="/WEB02/assets/js/jquery_3_7_1.js"></script>
</head>
<body>
    <h2>TLD pagination</h2>
    <hr/>
    <pagination:renderPaging maxNum="101" currentPageNo="1" rowPerPage="10" bottomCount="10" url="/example" scriptName="doRetrieve" />
</body>
</html>   