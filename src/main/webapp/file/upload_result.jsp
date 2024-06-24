<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<script>
document.addEventListener("DOMContentLoaded", function(){
	
	  //table 자식모두 tbody선택
	  const rows = document.querySelectorAll("#fileList tbody tr");
	
    rows.forEach(function(row){
        //double click
        row.addEventListener('dblclick',function(){
          console.log('row click');
          //this(tr) 자식 (td: 마지막 위치)
          let pathValue = this.querySelector('td:last-child').textContent.trim();
          console.log('pathValue:'+pathValue);
          
          // 특정 인덱스의 td 값 가져오기
          let indexToGet = 5; // 예시로 5번째 td 값
          let saveFileName = this.cells[indexToGet].textContent.trim();
          console.log('saveFileName:'+saveFileName);
          
          let fileName = pathValue+"\\"+saveFileName;
          console.log('fileName:'+fileName);
          
          indexToGet = 2; // 예시로 3번째 td 값
          let orgFileName = this.cells[indexToGet].textContent.trim();
          console.log('orgFileName:'+orgFileName);
          download(fileName,orgFileName);
        });
        
      }); 
	    
    function download(saveFileName,orgFileName){
        // 폼 요소 선택
        let frm = document.getElementById("download_frm");
        
        // 폼 데이터 설정
        frm.fileName.value = saveFileName;
        frm.orgName.value = orgFileName;
        
        // 각 입력 요소 값 출력
        console.log(" frm.fileName.value: " +  frm.fileName.value);
        console.log(" frm.orgName.value: " +  frm.orgName.value);
        // 서버로 보낼 액션 설정
        frm.action = "/WEB02/file/download";
        
        // 폼 제출
        frm.submit();       	
    }
	    
});
</script>
</head>
<body>
<!-- container -->
<div class="container">
  
  <!-- 제목 -->
  <div class="page-header">
    <h2>파일 업로드</h2>
  </div>
  <!--// 제목 end ------------------------------------------------------------->
      <form action="#" name="download_frm" method="POST" id="download_frm"  >
          <input type="text" name=fileName       id="fileName"      placeholder="파일경로 포함 파일이름">
          <input type="text" name=orgName       id="orgName"      placeholder="원본 파일이름">
          
      </form>
        <!-- table -->
      <table class="table table-striped table-hover table-bordered" id="fileList">
        <tr class="table-success">  
          <th class="text-center col-sm-1 " >파일ID</th>
          <th class="text-center col-sm-1 ">순번</th>
          <th class="text-center col-sm-6 ">원본파일명</th>
          <th class="text-center col-sm-2 ">파일사이즈</th>
          <th class="text-center col-sm-2 ">확장자</th>
          <th class="text-center hidden-lg hidden-md hidden-xs">저장파일명</th>
          <th class="text-center hidden-lg hidden-md hidden-xs">저장경로</th>
        </thead>
          <tbody>
              <!-- 문자: 왼쪽, 숫자: 오른쪽, 같은면: 가운데 -->
            <c:choose>    
                <c:when test="${fileList.size()>0 }">
                  <c:forEach  var="vo" items="${fileList}">                     
		                <tr>
		                  <td class="text-center"><c:out value="${vo.fileId }" /></td>
		                  <td class="text-left"><c:out value="${vo.seq }" /></td>
		                  <td class="text-left"><c:out value="${vo.orgNm }" /></td>
		                  <td class="text-right"><fmt:formatNumber  value="${vo.fileSize }" type="number" />&nbsp;bytes</td>
		                  <td class="text-left">..</td>
		                  <td class="hidden-lg hidden-md hidden-xs"><c:out value="${vo.saveNm }" /></td>
		                  <td class="hidden-lg hidden-md hidden-xs"><c:out value="${vo.savePath }" /></td>
		                </tr>
                </c:forEach>
              </c:when>
            </c:choose>
              
          </tbody>
      
      </table>
      <!--// table -->  

  
  <!--// form end -->
</div>
<!--// container end ---------------------------------------------------------->
<script src="/WEB02/assets/js/bootstrap.bundle.min.js"></script> 
</body>
</html>