<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="/WEB02/assets/images/favicon.ico" type="image/x-icon">

<link rel="stylesheet" href="/WEB02/assets/css/bootstrap.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<title>동가홍상</title>
<script src="/WEB02/assets/js/jquery_3_7_1.js"></script>
</head>
<body>
<!-- container -->
<div class="container">
  <!-- menu -->
  <jsp:include page="/cmn/menu.jsp"></jsp:include>
  <!-- 제목 -->
  <div class="page-header  mb-4">
    <h2>main</h2>
  </div>
  <!--// 제목 end ------------------------------------------------------------->
    
    session:${sessionScope.member}
   <jsp:include page="/cmn/footer.jsp"></jsp:include>
</div>
<!--// container end ---------------------------------------------------------->    
</body>
<script src="/WEB02/assets/js/bootstrap.bundle.min.js"></script> 
</html>