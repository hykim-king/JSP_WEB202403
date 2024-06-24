<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib  prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  prefix="fn"   uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="CP" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="${CP}/assets/images/favicon.ico" type="image/x-icon">

<link rel="stylesheet" href="${CP}/assets/css/bootstrap.css">
<!-- bootstrap icon -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<title>동가홍상</title>
<link rel="stylesheet" href="${CP}/css/poster.css">
<script src="${CP}/assets/js/jquery_3_7_1.js"></script>
<script>
/*
 * DOMContentLoaded 이벤트는 HTML 문서가 완전히 로드되고 파싱된 후에 발생합니다. 
 이벤트 핸들러는 스타일시트, 이미지, 하위 프레임 로딩이 완료되지 않았더라도 실행됩니다. 
 페이지의 외관이 로드되기를 기다리지 않고도 JavaScript 코드를 실행할 수 있습니다.
 */
document.addEventListener("DOMContentLoaded", function(){
	console.log("DOMContentLoaded");
	
	const mailForm = document.querySelector("#mailForm");
	console.log("mailForm:",mailForm);
	
	const title = document.querySelector("#title");
	console.log("title:",title);
	
	const mailTo = document.querySelector("#mailTo");
	console.log("mailTo:",mailTo);	
	
	const contents = document.querySelector("#contents");
	console.log("contents:",contents);  	
	
	//form submit
	mailForm.addEventListener("submit",function(event){
		event.preventDefault();//폼의 전송 방지
		console.log("submit");
		
		console.log("title:",title.value);
		console.log("mailTo:",mailTo.value);
		console.log("contents:",contents.value);
		mailForm.submit();
	});
	
	
});   
</script>
</head>
<body>
<!-- container -->
<div class="container">
   <!-- menu -->
  <jsp:include page="/cmn/menu.jsp"></jsp:include>
  <!--// menu end ------------------------------------------------------------->
  
  <!-- 제목 -->
  <div class="page-header">
    <h2>Email</h2>
  </div>
  <!--// 제목 end ------------------------------------------------------------->
  

  
  <!-- form -->
  <form action="${CP }/form_mail/mail.pcw" name="mailForm" id="mailForm" class="form-horizontal" >
	  <!-- 버튼 -->
	  <div class="mb-2 d-grid gap-2 d-md-flex justify-content-md-end">
	      <input type="submit" value="mail전송" class="btn btn-primary">
	  </div>
	  <!--// 버튼 ----------------------------------------------------------------->  
    <div class="row mb-2">
        <label for="title" class="col-sm-2 col-form-label">제목</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" name="title" id="title"  maxlength="75" required="required">
        </div>      
    </div>
    <div class="row mb-2">
        <label for="regId" class="col-sm-2 col-form-label">받는사람</label>
        <div class="col-sm-10">
          <input type="email" class="form-control" name="mailTo" id="mailTo"  maxlength="320" required="required">        
        </div>      
    </div>    
    <div class="row mb-2"">
        <label for="regId" class="col-sm-2 col-form-label">내용</label>
        <div class="col-sm-10">    
         <textarea style="height: 200px"  class="form-control" id="contents" name="contents" required="required"></textarea>
        </div> 
    </div>    
    
  </form>
  
  <!--// form end -->
    <jsp:include page="/cmn/footer.jsp"></jsp:include>  
</div>
<!--// container end ---------------------------------------------------------->
<script src="/WEB02/assets/js/bootstrap.bundle.min.js"></script> 
</body>
</html>