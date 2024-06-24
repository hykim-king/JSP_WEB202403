<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib  prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  prefix="fn"   uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="CP"  value="${pageContext.request.contextPath }"  />  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/WEB02/assets/css/bootstrap.css">
<title>동가홍상</title>
<script src="/WEB02/assets/js/jquery_3_7_1.js"></script>
<script src="${CP}/assets/js/common.js"></script> 
<script>
document.addEventListener("DOMContentLoaded", function(){
	  console.log('DOMContentLoaded ');
	  const myForm = document.querySelector("#mailForm");
	  const title = document.querySelector("#title");
	  const email = document.querySelector("#email");
	  const contents = document.querySelector("#contents");
	  
	  console.log('myForm ',myForm);
	  console.log('title ',title);
	  console.log('email ',email);
	  console.log('contents ',contents);
	  
	  myForm.addEventListener("submit",function(event){
		  console.log('submit ');
		  event.preventDefault();//를 사용하여 폼의 기본 제출 동작을 막습니다.
		  //let frm = document.getElementById("mailForm");
		  console.log('title:',myForm.title.value);
		  console.log('email:',myForm.email.value);
		  console.log('contents:',myForm.contents.value);
		  
		  
		  myForm.action = "/WEB02/mail/mail.do";
		  myForm.submit();    
	  });
});

</script>
</head>
<body>
<!-- container -->
<div class="container">
  
  <!-- 제목 -->
  <div class="page-header">
    <h2>게시-등록</h2>
  </div>
  <!--// 제목 end ------------------------------------------------------------->
  

  
  <!-- form -->
  <form action="#" class="form-horizontal" method="POST" name="mailForm" id="mailForm">
  
  <input type="hidden" name="work_div"  id="work_div" placeholder="작업구분" value="sendMail">
  <!-- 버튼 -->
  <div class="mb-2 d-grid gap-2 d-md-flex justify-content-md-end">
      <input type="submit" value="전송" class="btn btn-primary">
  </div>
  <!--// 버튼 ----------------------------------------------------------------->  
    <div class="row mb-2">
        <label for="title" class="col-sm-2 col-form-label">제목</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" name="title" id="title"  maxlength="75" required="required">
        </div>      
    </div>
    <div class="row mb-2">
        <label for="regId" class="col-sm-2 col-form-label">받는사람 Email</label>
        <div class="col-sm-10">
          <input type="email" class="form-control" name="email" id="email"  maxlength="320" required="required">        
        </div>      
    </div>    
    <div class="row mb-2"">
        <label for="contents" class="col-sm-2 col-form-label">내용</label>
        <div class="col-sm-10">    
         <textarea style="height: 200px"  class="form-control" id="contents" name="contents"></textarea>
        </div> 
    </div>    
    
  </form>
  
  <!--// form end -->
</div>
<!--// container end ---------------------------------------------------------->
<script src="/WEB02/assets/js/bootstrap.bundle.min.js"></script> 
</body>
</html>