<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/cmn/common.jsp" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/WEB02/assets/css/bootstrap.css">
<title>동가홍상</title>
<script src="/WEB02/assets/js/jquery_3_7_1.js"></script>
<script src="/WEB02/assets/js/common.js"></script> 
<script>
document.addEventListener("DOMContentLoaded", function(){
	  console.log('DOMContentLoaded ');
	 
	  const  moveToListBtn = document.querySelector("#moveToList");
	  const doSaveBtn = document.querySelector("#doSave");
	  
	  const workDiv = document.querySelector("#work_div");//작업구분
	  const title = document.querySelector("#title");//제목
	  const contents = document.querySelector("#contents");//내용
	  
	  moveToListBtn.addEventListener("click",function(){
		  moveToList();
	  });
	  
	  doSaveBtn.addEventListener("click",function(){
		  console.log(title.value);
		  ajaxDoSave();
    });   

	  function moveToList(){
		    console.log('moveToList()');
		    alert("게시 목록으로 이동 합니다.");
		    window.location.href= "/WEB02/board/board.do?work_div=doRetrieve";
		}
	  
//일반화된 fetch 함수
async function fetchRequest(url, options = {}) {
    try {
        const response = await fetch(url, options);
        console.log('---------------------------');
        console.log('data:', options);
        console.log('---------------------------');
        // 응답이 성공적인지 확인
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        
        // 응답을 JSON으로 변환
        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Fetch error:', error);
        throw error; // 오류를 호출자에게 전달
    }
}	  
  //ajaxDoSave(); 
async function postData() {
      const url = '/WEB02/board/board.do';
       const data = {
          "work_div":"ajaxDoSave",
          "title": title.value,
          "contents": contents.value
      }; 
      
      
      try {
          const response = await fetchRequest(url, {
              method: 'POST',        
              headers: {
                  'Content-Type': 'application/json'
              },              
              body: JSON.stringify(data)
          });
          console.log('Success:', response);
          //const messageVO = JSON.parse(response);
          console.log("messageId:"+response.messageId);
          console.log("msgContents:"+response.msgContents);
          
          if(response.messageId === "1"){
            alert(response.msgContents);
            window.location.href ="/WEB02/board/board.do?work_div=doRetrieve";
          }else{
            alert(response.msgContents);
          }
          
      } catch (error) {
          console.error('Error posting data:', error);
      }
  }     
  
});	  
	  

//비동기 통신
function ajaxDoSave(){	
	  
	  
    //제목
	  if( isEmpty(title.value) == true){
	      title.focus();
	      alert('제목을 입력 하세요.');
	      return;
	  }
     
    //내용
    if( isEmpty(contents.value) == true){
    	  contents.focus();
        alert('내용을 입력 하세요.');
        return;
    }    
	   
	  //DML발생시 !
	  if(false==confirm('저장 하시겠습니까?')){
		  return;
	  }
	
    $.ajax({
        type: "POST", 
        url:"/WEB02/board/board.do",
        asyn:"true",  //비동기 통신
        dataType:"html",
        data:{
            "work_div":"ajaxDoSave",
            "title": title.value,
            "contents": contents.value
        },
        success:function(data){//통신 성공
            console.log("success data:"+data);
            const messageVO = JSON.parse(data);
            console.log("messageId:"+messageVO.messageId);
            console.log("msgContents:"+messageVO.msgContents);
            
            if(messageVO.messageId === "1"){
              alert(messageVO.msgContents);
              window.location.href ="/WEB02/board/board.do?work_div=doRetrieve";
            }else if(messageVO.messageId === "50"){
            	alert(messageVO.msgContents);
                window.location.href ="/WEB02/login/login.jsp";
            }else{
              alert(messageVO.msgContents);
            }
            
        },
        error:function(data){//실패시 처리
            console.log("error:"+data);
        }
    });  //ajax end	
}

//동기 통신
function doSave(){
    console.log('doSaveBtn click ');
    
    let frm = document.getElementById("regForm");
    frm.work_div.value = "doSave";
    
    frm.title.value = title.value;
    frm.contents.value = contents.value;
    
    
    console.log('work_div:',frm.title.value);
    console.log('title:',frm.title.value);
    console.log('contents:',frm.contents.value);
    
    frm.action = "<%=cPath%>" + "/board/board.do";
    // 폼 제출
    frm.submit();	
	
}
</script>
</head>
<body>
<!-- container -->
<div class="container">
  <!-- menu -->
  <jsp:include page="/cmn/menu.jsp"></jsp:include>
  <!--// menu end ------------------------------------------------------------->
  
  <!-- 제목 -->
  <div class="page-header  mb-4">
    <h2>게시-등록</h2>
  </div>
  <!--// 제목 end ------------------------------------------------------------->
    
    <!-- 버튼 -->
  <!-- 버튼 -->
  <div class="mb-2 d-grid gap-2 d-md-flex justify-content-md-end">
      <input type="button" value="목록" class="btn btn-primary" id="moveToList">
      <input type="button" value="등록" class="btn btn-primary" id="doSave">
  </div>
  <!--// 버튼 ----------------------------------------------------------------->
    
    <!-- form -->
    <form action="#" name="regForm" id="regForm" method="get"  class="form-horizontal">
       <input type="hidden"  name="work_div" id="work_div">
       <div class="row mb-3">   
        <label for="title" class="col-sm-2 col-form-label">제목</label>
        <div class="col-sm-10">
          <input type="text" class="form-control"  name="title"  id="title"  maxlength="75" required="required">
        </div>
       </div>
         

        
       <div class="row mb-3"">
        <label for="title" class="col-sm-2 col-form-label">내용</label>
        <div class="col-sm-10">
          <textarea style="height: 200px"  class="form-control" id="contents" name="contents"></textarea>
        </div>
       </div>              
    </form>
    <!--//form --------------------------------------------------------------->
    <jsp:include page="/cmn/footer.jsp"></jsp:include>
</div>    
<!--// container end ---------------------------------------------------------->
<script src="/WEB02/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>