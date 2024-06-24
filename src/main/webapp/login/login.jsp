<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="/WEB02/assets/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="/WEB02/assets/css/bootstrap.css">
<style >
    .container {
      max-width: 400px; /* Adjust the width as needed */
      margin: auto; /* Center the form */
    }
</style>
<title>오늘 사람 프로그램</title>
<script src="/WEB02/assets/js/jquery_3_7_1.js"></script>
<script src="/WEB02/assets/js/common.js"></script> 

<script>
document.addEventListener("DOMContentLoaded", function(){
	console.log('DOMContentLoaded ');
	
	//login
	const loginBtn = document.querySelector("#login");
	//console.log('loginBtn: '+loginBtn);
	
	const memberId =  document.querySelector("#memberId");
	//console.log('memberId: '+memberId);
	const password =  document.querySelector("#password");
	//console.log('password: '+password);
	
	//이벤트 리스너 등록
	loginBtn.addEventListener("click",function(){
		console.log('loginBtn click: ');
		login();
	});
	
	function login(){
		console.log('login() ');
		
		//id검사
		if(isEmpty(memberId.value) == true){
			memberId.focus();
			alert('ID를 입력 하세요.');
			return;
		}
		
    //password 검사
    if(isEmpty(password.value) == true){
    	password.focus();
      alert('비번을 입력 하세요.');
      return;
    }		
    
    $.ajax({
        type: "POST", 
        url:"/WEB02/member/login.do",
        asyn:"true",
        dataType:"html",
        data:{
            "work_div":"login",
            "memberId": memberId.value,
            "password": password.value
        },
        success:function(response){//통신 성공
            console.log("success data:"+response);
        
            //null, undefined처리
            if(response){
            	try{  
            	    const messageVO = JSON.parse(response);
                  console.log("messageVO.messageId:"+messageVO.messageId);
                  console.log("messageVO.msgContents:"+messageVO.msgContents);
                  
                  //id 불일치
                  if(isEmpty(messageVO) == false &&  "10" === messageVO.messageId){
                	  alert(messageVO.msgContents);
                	  memberId.focus();
                  }else if(isEmpty(messageVO) == false &&  "20" === messageVO.messageId){
                    alert(messageVO.msgContents);
                    password.focus();                	  
                  }else if(isEmpty(messageVO) == false &&  "30" === messageVO.messageId){
                	  alert(messageVO.msgContents);
                	  window.location.href= "/WEB02/main/main.jsp";
                  }
            		
            		
              }catch(e){    
                console.error("JSON 파싱 에러:",e);
              }            	
            }else{
                console.warn("response가 null혹은 undefined.");
                alert("response가 null혹은 undefined.");            	
            	
            }
        },
        error:function(data){//실패시 처리
                console.log("error:"+data);
        }
    });    
    
    
	}
	
	
	
});//--DOMContentLoaded end


</script>



</head>
<body>
<!-- container -->
<div class="container">  
  <!-- 제목 -->
  <div class="page-header  mb-4">
    <h2>로그인</h2>
  </div>
  <!--// 제목 end ------------------------------------------------------------->
<form action="#" method="post">
  <input type="hidden" name="work_div" id="work_div">
  <!-- 회원ID input -->
  <div data-mdb-input-init class="form-outline mb-4">
    <input type="text" id="memberId" name="memberId" class="form-control" />
    <label class="form-label" for="memberId">Member ID</label>
  </div>

  <!-- Password input -->
  <div data-mdb-input-init class="form-outline mb-4">
    <input type="password" id="password" name="password" class="form-control" />
    <label class="form-label" for="password">Password</label>
  </div>

  <!-- 2 column grid layout for inline styling -->
  <div class="row mb-4">
    <div class="col d-flex justify-content-center">
      <!-- Checkbox -->
      <div class="form-check">
        <input class="form-check-input" type="checkbox" value="" id="form2Example31" checked />
        <label class="form-check-label" for="form2Example31"> Remember me </label>
      </div>
    </div>

    <div class="col">
      <!-- Simple link -->
      <a href="#!">Forgot password?</a>
    </div>
  </div>

  <!-- Submit button -->
  <button id="login"  type="button" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-block mb-4">Sign in</button>

</form>
</div>
<!--// container end ---------------------------------------------------------->
</body>
<script src="/WEB02/assets/js/bootstrap.bundle.min.js"></script> 
</html>