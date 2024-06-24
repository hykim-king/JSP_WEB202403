<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/cmn/common.jsp" %>       
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 등록</title>
    <!-- jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="styles.css">
    <script>
    
    window.addEventListener("load", function() {
        console.log('load');
        
    });
    document.addEventListener("DOMContentLoaded", function() {
    	console.log('DOMContentLoaded');	
    	
    	const doSaveBtn = document.querySelector("#doSave");
    	
    	const title = document.querySelector("#title");
    	const contents = document.querySelector("#contents");
    	const regId = document.querySelector("#reg_id");
    	
    	
    	doSaveBtn.addEventListener("click", function(){
    		  
    	    console.log("doSaveBtn()");
    	    
    	    saveAjax();
    	});
    	
    	function save(){
            // 폼 요소 선택
            let frm = document.getElementById("board_frm");
            
            // 폼 데이터 설정
            frm.work_div.value = "doSave";
            
            // 각 입력 요소 값 출력
            console.log("frm.title.value: " + frm.title.value);
            console.log("frm.contents.value: " + frm.contents.value);
            console.log("frm.reg_id.value: " + frm.reg_id.value);
            
            // 서버로 보낼 액션 설정
            console.log("frm.action: " + "<%=cPath%>" + "/board/board.do");
            frm.action = "<%=cPath%>" + "/board/board.do";
              
            // 폼 제출
            frm.submit();      		
    	}
    	
    	function saveAjax(){
    		// AJAX 요청 보내기
				// Form 데이터 생성

				// JSON 데이터 생성
				var jsonData = {
						work_div: 'doSaveAjax',
						title: title.value,
						contents: contents.value,
						reg_id: regId.value,
				};
				
				console.log('jsonData:', jsonData);
    		$.ajax({
    		    url: '<%=cPath%>/board/board.do',
    		    method: 'POST',
    		    asyn:"true",
    	      dataType:"html",
    		    data: jsonData,
    		    success: function(response) {
    		        console.log('성공:', response);
    		        
    		        const jsonObj = JSON.parse(response);
 	              console.log('jsonObj:'+ jsonObj.messageId);
 	              console.log('msgContents:'+ jsonObj.msgContents);
 	              
 	             if(null !=jsonObj && jsonObj.messageId ==='1' ){//등록 성공
 	            	 window.location.href = "/WEB02/board/board.do?work_div=doRetrieve";
 	             }
 	              
    		    },
    		    error: function(xhr, status, error) {
    		        console.error('오류:', error);
    		    }
    		});    		
    		
    	}
    	
    });
    
    </script>
</head>
<body onload="">
    <div class="container">
        <h1>게시판 등록</h1>
        <div class="button-group">
            <input type="button" value="등록" id="doSave" /> 
        </div>
        <form action="submit_post" method="post" name="board_frm" id="board_frm">
            <input type="text" name="work_div"  id="work_div" placeholder="작업구분">
            <div class="form-group">
                <label for="title">제목:</label>
                <input type="text" id="title" name="title" required>
            </div>
            <div class="form-group">
                <label for="content">내용:</label>
                <textarea id="contents" name="contents" rows="10" required></textarea>
            </div>
            <div class="form-group">
                <label for="author">등록자:</label>
                <input type="text" id="reg_id" name="reg_id" required>
            </div>

        </form>
    </div>
</body>
</html>