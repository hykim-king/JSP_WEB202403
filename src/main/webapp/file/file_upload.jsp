<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:set var="CP"  value="${pageContext.request.contextPath }"  />     
<%@ include file="/cmn/common.jsp" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${CP}/assets/css/bootstrap.css">
<title>동가홍상</title>
<script src="${CP}/assets/js/jquery_3_7_1.js"></script>
<script src="${CP}/assets/js/common.js"></script> 
<script>
document.addEventListener("DOMContentLoaded", function(){
	console.log('DOMContentLoaded ');
	
	//doSave
	const myForm = document.querySelector("#fileForm");
	
	
	
	const doSaveBtn = document.querySelector("#doSave");
	const name = document.querySelector("#name");
	const title = document.querySelector("#title");
	const fileName01 = document.querySelector("#fileName01");
	const fileName02 = document.querySelector("#fileName02");
	
	
	
	console.log('myForm ',myForm);
	myForm.addEventListener("submit",function(event){
		console.log('submit ');
		event.preventDefault(); // Prevent the default form submission
		let frm = document.getElementById("fileForm");
    if(!fileName01.files || fileName01.files.length === 0){
      fileName01.focus();
      alert("파일01을 선택 하세요.");
      return;
    }
    
    if(!fileName02.files || fileName02.files.length === 0){
      fileName02.focus();
      alert("파일02을 선택 하세요.");
      return;
    }		
    
    frm.name.value = name.value;
    frm.title.value = title.value;
    
    
    console.log('name:',frm.name.value);
    console.log('title:',frm.title.value);
    
    const file01 = fileName01.files[0];
    console.log('file01.name:',file01.name);
    
    const file02 = fileName02.files[0];
    console.log('file02.name:',file02.name);
      
    
    frm.action = "/WEB02/file/upload";
    
    console.log('frm.action:',frm.action);
    // 폼 제출
    frm.submit();     
    
	});
	
	
	
/* 	doSaveBtn.addEventListener("click",function(){
		console.log('click ');
	    let frm = document.getElementById("fileForm");
	    
	    if(!fileName01.files || fileName01.files.length === 0){
	    	fileName01.focus();
	    	alert("파일01을 선택 하세요.");
	    	return;
	    }
	    
      if(!fileName02.files || fileName02.files.length === 0){
        fileName02.focus();
        alert("파일02을 선택 하세요.");
        return;
      }
	      
	    frm.name.value = name.value;
	    frm.title.value = title.value;
	    
	    
	    console.log('name:',frm.name.value);
	    console.log('title:',frm.title.value);
	    
	    const file01 = fileName01.files[0];
	    console.log('file01.name:',file01.name);
	    
      const file02 = fileName02.files[0];
      console.log('file02.name:',file02.name);
	      
	    
	    frm.action = "/WEB02/file/upload";
	    
	    console.log('frm.action:',frm.action);
	    // 폼 제출
	    frm.submit(); 		
	}); */
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
  
  <!-- 버튼 -->
  <div class="mb-2 d-grid gap-2 d-md-flex justify-content-md-end">
  </div>
  <!--// 버튼 ----------------------------------------------------------------->
  
  <!-- form -->
  <form action="#" class="form-horizontal" method="post"  id="fileForm"  name="fileForm" enctype="multipart/form-data" action="/WEB02/file/upload">
    <div class="row mb-2">
	    <label for="title" class="col-sm-2 col-form-label"></label>
	    <div class="mb-2 d-grid gap-2 d-md-flex justify-content-md-end">
	      <input type="submit" value="등록" class="btn btn-primary" id="doSave">
	    </div> 
    </div>  
    <div class="row mb-2">
        <label for="name" class="col-sm-2 col-form-label">올림사람:</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" name="name" id="name"  maxlength="75" required="required">
        </div>      
    </div>
    <div class="row mb-2">
        <label for="title" class="col-sm-2 col-form-label">제목</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" name="title" id="title"  maxlength="20" required="required">        
        </div>      
    </div>    
  
    <div class="row mb-2">
        <label for="title" class="col-sm-2 col-form-label">파일명01:</label>
        <div class="col-sm-10">
          <input type="file" class="form-control" name="fileName01" id="fileName01"  >        
        </div>      
    </div>   
    <div class="row mb-2">
        <label for="title" class="col-sm-2 col-form-label">파일명02:</label>
        <div class="col-sm-10">
          <input type="file" class="form-control" name="fileName02" id="fileName02"  >        
        </div>      
    </div>       
 
    </div>   
  </form>
  
  <!--// form end -->
</div>
<!--// container end ---------------------------------------------------------->
<script src="/WEB02/assets/js/bootstrap.bundle.min.js"></script> 
</body>
</html>