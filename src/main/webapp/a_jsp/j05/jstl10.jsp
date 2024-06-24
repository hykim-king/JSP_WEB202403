<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib  prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  prefix="fn"   uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib  uri="http://format.pcwk.com/tags"  prefix="newformatUtil"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
<script src="/WEB02/assets/js/jquery_3_7_1.js"></script>
</head>
<body>
    <h2>사용자 정의 tag</h2>
    <hr/>
    <p><newformatUtil:newFormatNumber number="123456789" pattern="#,###.00"/>  </p>
</body>
</html>