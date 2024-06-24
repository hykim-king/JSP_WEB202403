<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>     
<%@ taglib uri="http://page.ehr.pcwk.com/tags/pagination"  prefix="pagination"%>  
<%@ taglib uri="http://code.ehr.pcwk.com/tags"  prefix="codeTags"%> 
<c:set var="CP"  value="${pageContext.request.contextPath }"  />  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="${CP}/assets/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="${CP}/assets/css/bootstrap.css">

<title>동가홍상</title>
<script src="${CP}/assets/js/jquery_3_7_1.js"></script>
<script src="${CP}/assets/js/common.js"></script> 
<script>
document.addEventListener("DOMContentLoaded", function(){
	console.log('DOMContentLoaded---');
	//목록 버튼
	
	const moveToListBtn = document.querySelector("#moveToList");
  //수정 버튼
  const doUpdateBtn = document.querySelector("#doUpdate");
	//삭제 버튼 
	const doDeleteBtn = document.querySelector("#doDelete");
	console.log('doDeleteBtn:'+doDeleteBtn);
	
	const workDiv = document.querySelector("#work_div");//작업구분
	const seq = document.querySelector("#seq");//seq
	const title = document.querySelector("#title");//title
	const contents = document.querySelector("#contents");//contents
	const modId = document.querySelector("#modId");//modId
	
	//이벤트 핸들러 등록
	moveToListBtn.addEventListener("click", function(event){
		console.log('moveToListBtn click event'+event);
		moveToList();
	});
	
	doUpdateBtn.addEventListener("click", function(event){
		console.log('doUpdateBtn click event'+event);
		doUpdate();
	});
	
	doDeleteBtn.addEventListener("click", function(event){
		console.log('doDeleteBtn click event'+event);
		//doDelete
		doDelete();
	});
	//--------------------------------------------------------
	function moveToList(){
		console.log('moveToList()');
		alert("게시 목록으로 이동 합니다.");
		window.location.href= "${CP}/board/board.do?work_div=doRetrieve";
	}
	
	
	function doUpdate(){
		console.log('doUpdate()');
		
    if( isEmpty(seq.value) ==true){
        alert('Seq를 확인 하세요.');
        return;
    }
    
    if( isEmpty(title.value) ==true){
    	  title.focus();
        alert('제목을 확인 하세요.');
        return;
    }    

    if( isEmpty(contents.value) ==true){
    	  contents.focus();
        alert('내용 확인 하세요.');
        return;
    }    
    
    $.ajax({
        type: "POST", 
        url:"${CP}/board/board.do",
        asyn:"true",
        dataType:"html",
        data:{
            "work_div":"doUpdate",
            "seq": seq.value,
            "title": title.value,
            "contents": contents.value,
            "modId": modId.value  
        },
        success:function(response){//통신 성공
            console.log("success data:"+response);
        
            //null, undefined처리
            if(response){
            	try{
            		const messageVO = JSON.parse(response);
            		console.log("messageVO.messageId:"+messageVO.messageId);
                console.log("messageVO.msgContents:"+messageVO.msgContents);
                
                if(isEmpty(messageVO) == false &&  "1" === messageVO.messageId){
                	alert(messageVO.msgContents);
                	window.location.href= "${CP}/board/board.do?work_div=doRetrieve";
                }else{
                	alert(messageVO.msgContents);
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
    
	}//--doUpdate() end
	
	function doDelete(){
		  console.log('doDelete()');
		  workDiv.value = 'doDelete';
		  
		  console.log('seq.value:'+seq.value);
		  
		  //seq 
		  if( isEmpty(seq.value) ==true){
		     alert('Seq를 확인 하세요.');
		     return;
		  }
		  
		  if(false === confirm('삭제 하시겠습니까?')){
			   return;
		  }
		  
		  
		  $.ajax({
			    type: "GET", 
			    url:"${CP}/board/board.do",
			    asyn:"true",
			    dataType:"html",
			    data:{
			        "work_div":"doDelete",
			        "seq": seq.value 
			    },
			    success:function(response){//통신 성공
			        console.log("success response:"+response);
			        const messageVO = JSON.parse(response);
			        console.log("messageVO.messageId:"+messageVO.messageId);
			        console.log("messageVO.msgContents:"+messageVO.msgContents);
			    
			        if(isEmpty(messageVO) == false &&  "1" === messageVO.messageId){
			        	alert(messageVO.msgContents);
			        	window.location.href= "${CP}/board/board.do?work_div=doRetrieve";
			        }else{
			        	alert(messageVO.msgContents);
			        }
			        
			    },
			    error:function(data){//실패시 처리
			            console.log("error:"+data);
			    }
			});//--ajax end		  
	}//--doDelete()
	
	
 
	
	
});//--DOMContentLoaded end 


function renderingPager(maxNum, currentPageNo, rowPerPage, bottomCount, url, scriptName) {
    let html = [];
    
    let maxPageNo = Math.floor((maxNum - 1) / rowPerPage) + 1;
    let startPageNO = Math.floor((currentPageNo - 1) / bottomCount) * bottomCount + 1;
    let endPageNo = Math.floor((currentPageNo - 1) / bottomCount + 1) * bottomCount;
    
    let nowBlockNo = Math.floor((currentPageNo - 1) / bottomCount) + 1;
    let maxBlockNo = Math.floor((maxNum - 1) / bottomCount) + 1;

    if (currentPageNo > maxPageNo) {
        return '';
    }

    html.push('<ul class="pagination">');
    
    // <<
    if (nowBlockNo > 1 && nowBlockNo <= maxBlockNo) {
        html.push('<li class="page-item">');
        html.push('<a class="page-link" href="javascript:' + scriptName + '(\'' + url + '\', 1);">');
        html.push('<span>&laquo;</span>');
        html.push('</a>');
        html.push('</li>');
    }
    
    // <
    if (startPageNO > bottomCount) {
        html.push('<li class="page-item">');
        html.push('<a class="page-link" href="javascript:' + scriptName + '(\'' + url + '\',' + (startPageNO - bottomCount) + ');">');
        html.push('<span>&lt;</span>');
        html.push('</a>');
        html.push('</li>');
    }
    
    // 1 2 3 ... 10
    for (let inx = startPageNO; inx <= maxPageNo && inx <= endPageNo; inx++) {
        if (inx == currentPageNo) {
            html.push('<li class="page-item">');
            html.push('<a class="page-link active" href="#">' + inx + '</a>');
            html.push('</li>');
        } else {
            html.push('<li class="page-item">');
            html.push('<a class="page-link" href="javascript:' + scriptName + '(\'' + url + '\',' + inx + ');">' + inx + '</a>');
            html.push('</li>');
        }
    }
    
    // >
    if (maxPageNo > endPageNo) {
        html.push('<li class="page-item">');
        html.push('<a class="page-link" href="javascript:' + scriptName + '(\'' + url + '\',' + ((nowBlockNo * bottomCount) + 1) + ');">');
        html.push('<span>&gt;</span>');
        html.push('</a>');
        html.push('</li>');
    }
    
    // >>
    if (maxPageNo > endPageNo) {
        html.push('<li class="page-item">');
        html.push('<a class="page-link" href="javascript:' + scriptName + '(\'' + url + '\',' + maxPageNo + ');">');
        html.push('<span>&raquo;</span>');
        html.push('</a>');
        html.push('</li>');
    }
    
    html.push('</ul>');

    return html.join('');
}

function doSelectOne(url,pageNo){
    console.log('doSelectOne');
    console.log('url:'+url);
    console.log('pageNo:'+pageNo);
    //?work_div=doSelectOne"
    $.ajax({
        type: "GET", 
        url:url,
        asyn:"true",
        dataType:"html",
        data:{
            "work_div":"answerAjaxDoRetrieve",
            "page_no": pageNo,
            "seq": seq.value
        },
        success:function(response){//통신 성공
            //console.log("success response:"+response);
            //null, undefined처리
            if(response){
            	
            	try{
            		  const answerResult = JSON.parse(response);
            		  
            		  //답변 내용 삭제!
                  const table = document.getElementById('answerList');
                  const tbody = table.getElementsByTagName('tbody')[0];
                  // Clear the tbody content
                  tbody.innerHTML = '';
            		  
                  
                  //console.log('answerResult.answerList:\n'+answerResult.answerList);    
                  //console.log('answerResult.pageObject:\n'+answerResult.pageObject.pageSize);
                  
                  const answerList = answerResult.answerList;
                  
                  answerList.forEach(item => {
                	    const row = document.createElement('tr');
                	    
                	    if(item['seq'] ){
                	    	const cell = document.createElement('td');
                	    	cell.textContent = item['seq'];
                	    	cell.classList.add('text-center');
                	    	row.appendChild(cell);
                	    }
                	    
                      if(item['contents'] ){
                         const cell = document.createElement('td');
                         cell.textContent = item['contents'];
                         cell.classList.add('text-left');
                         row.appendChild(cell);
                      }                	 
                      
                      if(item['modId'] ){
                          const cell = document.createElement('td');
                          cell.textContent = item['modId'];
                          cell.classList.add('text-center');
                          row.appendChild(cell);
                      }  
                      
                      if(item['modDt'] ){
                          const cell = document.createElement('td');
                          cell.textContent = item['modDt'];
                          cell.classList.add('text-center');
                          row.appendChild(cell);
                      }                       
                      
                      if(item['seq'] ){

                          // 새 셀 추가
                          const div = document.createElement('div');
                          const actionCell = document.createElement('td');
                          const button = document.createElement('input');
                          button.type = 'button';
                          button.value = '수정';
                          button.className = 'btn btn-outline-success btn-sm';
                          button.setAttribute('data-hidden-info', item.seq); // seq 값을 data-hidden-info 속성에 설정

                          div.appendChild(button);
                          actionCell.appendChild(div);
                          actionCell.classList.add('text-center');
                          row.appendChild(actionCell);                          
                      }         
                      
                      if(item['boardSeq'] ){
                          const cell = document.createElement('td');
                          cell.textContent = item['boardSeq'];
                          //cell.classList.add('text-center');
                          cell.style.display = 'none';
                          row.appendChild(cell);
                      }                    

                	    
                	    tbody.appendChild(row);
                  });
                  
                  
                  //paging 기존 삭제
                  const nav = document.getElementById('answerPage');
                  const ul = nav.getElementsByTagName('ul')[0];                  
                  // Clear the tbody content
                  ul.innerHTML = '';
                  
                  
                  //pagig 새롭게 생성
                  console.log('answerResult.pageObject:\n'+answerResult.pageObject.pageSize);
                  const pageObject = answerResult.pageObject;
                  
                  let pagerHtml = renderingPager(pageObject.totalCnt, pageObject.pageNo, pageObject.pageSize, pageObject.bottomCount, '${CP}/board/board.do', 'doSelectOne');
                  ul.innerHTML = pagerHtml;

                  
               }catch(e){    
                 console.error("JSON 파싱 에러:",e);
               }
               
            }else{
                console.warn("response가 null혹은 undefined.");
                alert("response가 null혹은 undefined.");            	
            }
            
        
        
        },
        error:function(response){//실패시 처리
                console.log("error:"+response);
        }
    });
    
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
    <h2>게시-관리</h2>
  </div>
  <!--// 제목 end ------------------------------------------------------------->
  <!-- 버튼 -->
  <div class="mb-2 d-grid gap-2 d-md-flex justify-content-md-end">
      <input type="button" value="목록" class="btn btn-primary" id="moveToList">
      <input type="button" value="수정" class="btn btn-primary" id="doUpdate">
      <input type="button" value="삭제" class="btn btn-primary" id="doDelete">
  </div>
  <!--// 버튼 ----------------------------------------------------------------->
  
  <!-- form -->
  <form action="#" class="form-horizontal">
    <input type="hidden" name="work_div" id="work_div">
    <div class="row mb-3">
        <label for="seq" class="col-sm-2 col-form-label">순번</label>
        <div class="col-sm-10">   
          <input disabled type="text" class="form-control" name="seq" id="seq"  required="required" value="<c:out value='${outVO.seq }' escapeXml='true' />">
        </div>      
    </div>  

    <div class="row mb-3">
        <label for="readCnt" class="col-sm-2 col-form-label">조회수</label>
        <div class="col-sm-10">
          <input disabled type="text" class="form-control" name="readCnt" id="readCnt"   value="<c:out value='${outVO.readCnt }' escapeXml='true' />" >
        </div>      
    </div>  
         
    <div class="row mb-3">
        <label for="regId" class="col-sm-2 col-form-label">등록자</label>
        <div class="col-sm-10">
          <input type="text" disabled="disabled" class="form-control" name="regId" id="regId"   value="<c:out value='${outVO.regId }' escapeXml='true' />"  >        
        </div>      
    </div>    
    <div class="row mb-3">
        <label for="regDt" class="col-sm-2 col-form-label">등록일</label>
        <div class="col-sm-10">
          <input type="text" disabled="disabled" class="form-control" name="regDt" id="regDt"    value="<c:out value='${outVO.regDt }' escapeXml='true' />" >        
        </div>      
    </div>        
    <div class="row mb-3">
        <label for="modId" class="col-sm-2 col-form-label">수정자</label>
        <div class="col-sm-10">
          <input type="text" disabled="disabled" class="form-control" name="modId" id="modId"   value="<c:out value='${outVO.modId }' escapeXml='true' />"  >        
        </div>      
    </div>    
    <div class="row mb-3">
        <label for="modDt" class="col-sm-2 col-form-label">수정일</label>
        <div class="col-sm-10">
          <input type="text" disabled="disabled" class="form-control" name="modDt" id="modDt"   value="<c:out value='${outVO.modDt }' escapeXml='true' />"  >        
        </div>      
    </div>    
    <div class="row mb-3">
        <label for="title" class="col-sm-2 col-form-label">제목</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" name="title" id="title"  maxlength="75" required="required"  value="<c:out value='${outVO.title }' escapeXml='true' />">
        </div>      
    </div>     
    <div class="row mb-3"">
        <label for="contents" class="col-sm-2 col-form-label">내용</label>
        <div class="col-sm-10">    
         <textarea style="height: 200px"  class="form-control" id="contents" name="contents"><c:out value='${outVO.contents }' escapeXml='true' /></textarea>
        </div> 
    </div>    
    
  </form>
      <!-- 행(row)을 만드는 태그는 <tr>와 셀을 만드는 <td>, <th>태그: -->
      

    <table class="table table-striped table-hover table-bordered" id="answerList">
      <thead>
        <tr class="table-success">
          <th class="text-center col-sm-1">no</th>
          <th class="text-center col-sm-5">답변</th>
          <th class="text-center col-sm-2">등록자</th>
          <th class="text-center col-sm-2">등록일</th>
          <th class="text-center col-sm-2">관리</th>
          <th style="display: none;">board_seq</th>
        </tr>
      </thead>
      <tbody>
         <c:choose>
          <c:when test="${not empty answerList }">
            <c:forEach var="vo"  items="${answerList}">
              <tr>
                <td  class="text-center"><c:out value="${vo.num }" escapeXml="true" /></td>
                <td><c:out value="${vo.contents }" escapeXml="true" /></td>
                <td class="text-center"><c:out value="${vo.modId }" escapeXml="true" /></td>
                <td class="text-center"><c:out value="${vo.modDt }" escapeXml="true" /></td>
                <td class="text-center">
                  <div>
                    <input type="button"  data-hidden-info="<c:out value='${vo.seq }' escapeXml='true' />" value="수정"  class="btn btn-outline-success btn-sm ">
                  </div>
                </td>
                <td style="display: none;"><c:out value='${vo.boardSeq }' escapeXml='true' /></td>
              </tr> 
            </c:forEach>
          </c:when>
          <c:otherwise>
            <tr>
              <td colspan="99" class="text-center">조회된 데이터가 없습니다.</td>
            </tr>
          </c:otherwise>
         </c:choose>

      </tbody>  
       
    </table>
    <!-- paging --> 
    <nav aria-label="Page navigation example" id="answerPage">

	    <pagination:renderPaging 
	          maxNum="${pagingVO.totalCnt }"  
	          currentPageNo="${pagingVO.pageNo }" 
	          rowPerPage="${pagingVO.pageSize }" 
	          bottomCount="${pagingVO.bottomCount }" 
	          url="${CP}/board/board.do" 
	          scriptName="doSelectOne" />    
    </nav>
    <!--// paging end --------------------------------------------------------->    
  
  
  
  <!--// form end -->
  <jsp:include page="/cmn/footer.jsp"></jsp:include>
</div>
<!--// container end ---------------------------------------------------------->
<script src="${CP}/assets/js/bootstrap.bundle.min.js"></script> 
</body>
</html>