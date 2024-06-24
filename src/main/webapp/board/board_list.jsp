<%@page import="com.pcwk.ehr.cmn.StringUtil"%>
<%@page import="com.pcwk.ehr.code.CodeVO"%>
<%@page import="com.pcwk.ehr.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.pcwk.ehr.cmn.SearchDTO"%>
<%@page import="com.pcwk.ehr.board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>     
<%@ taglib uri="http://page.ehr.pcwk.com/tags/pagination"  prefix="pagination"%>  
<%@ taglib uri="http://code.ehr.pcwk.com/tags"  prefix="codeTags"%>
<%@ include file="/cmn/common.jsp" %>
<c:set var="CP"  value="${pageContext.request.contextPath }"  />      

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
	
	//등록 버튼
	const moveToRegBtn = document.querySelector("#moveToReg");
	
	//조회 버튼
	const doRetrieveBtn = document.querySelector("#doRetrieve");
	
	//html객체를 자바스크리트에서 생성
	const searchWord = document.querySelector("#search_word");
  
	
	//table 자식모두 tbody선택
 	const rows = document.querySelectorAll("#boardList tbody tr");

  const buttons = document.querySelectorAll(".btn-outline-success")	; 
	
	//이벤트 핸들러
 	buttons.forEach(function(button){
		button.addEventListener('click',function(){
			let hiddenInfo = this.getAttribute('data-hidden-info');
			console.log('hiddenInfo:'+hiddenInfo);
			doSelectOne(hiddenInfo);
		});
		
	}); 

 	  rows.forEach(function(row){
 		    //double click
 		    row.addEventListener('dblclick',function(){
 		      console.log('row click');
 		      //this(tr) 자식 (td: 마지막 위치)
 		      let seqValue = this.querySelector('td:last-child').textContent.trim();
 		      console.log('seqValue:'+seqValue);
 		      
 		      doSelectOne(seqValue);    
 		    });
 		    
 		  }); 	
	

  
  //jquery선택
/*   $('#boardList>tbody').on('click','tr',function(){
	  console.log('#boardList>tbody tr');
	  let pTr =$(this);
	  let tdArray = pTr.children();
	  //let seqValue = tdArray.eq(5).text();
	  let seqValue = tdArray.last().text();
	  
	  console.log('seqValue:'+seqValue);
  });
   */
	
	
	moveToRegBtn.addEventListener("click", function(event){
		 console.log('moveToRegBtn click');
	    // 폼 요소 선택
	    let frm = document.getElementById("board_frm");
	    
	    // 폼 데이터 설정
	    frm.work_div.value = "moveToReg";
	    
	    // 각 입력 요소 값 출력
	    console.log(" frm.work_div.value: " +  frm.work_div.value);
	    
	    // 서버로 보낼 액션 설정
	    frm.action = "<%=cPath%>" + "/board/board.do";
	    
	    // 폼 제출
	    frm.submit();		 
	});
	
	
  doRetrieveBtn.addEventListener("click", function(event){
	  console.log('doRetrieveBtn click');
	  doRetrieve();
  });	
	
	searchWord.addEventListener("keydown", function(event){
	  console.log('keydown');
	  
	  if(event.keyCode === 13){
	    doRetrieve();
	  }
	});	
	
});


function doSelectOne(seqValue){
	
    // 폼 요소 선택
    let frm = document.getElementById("board_frm");
    // 폼 데이터 설정
    frm.work_div.value = "doSelectOne";  
    
    //seq
    frm.seq.value = seqValue;
    frm.action = "<%=cPath%>" + "/board/board.do";
    
    // 폼 제출
    frm.submit(); 	
}

function doRetrieve() {
    console.log("doRetrieve()");
    
    // 폼 요소 선택
    let frm = document.getElementById("board_frm");
    
    // 폼 데이터 설정
    frm.work_div.value = "doRetrieve";
    frm.page_no.value = "1";
    
    // 각 입력 요소 값 출력
    console.log("frm.search_div.value: " + frm.search_div.value);
    console.log("frm.search_word.value: " + frm.search_word.value);
    console.log("frm.page_size.value: " + frm.page_size.value);
    
    // 서버로 보낼 액션 설정
    console.log("frm.action: " + "<%=cPath%>" + "/board/board.do");
    frm.action = "<%=cPath%>" + "/board/board.do";
    
    // 폼 제출
    frm.submit();
} 


//페이징 조회
function pageRetrieve(url,pageNo){
	console.log("url:"+url);
	console.log("pageNo:"+pageNo);
	
    // 폼 요소 선택
    let frm = document.getElementById("board_frm");
    frm.work_div.value = "doRetrieve";
    // 폼 데이터 설정
    frm.page_no.value = pageNo;
    
    // url
    frm.action = url;
    
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
  <div class="page-header mb-4">
    <h2>게시-목록</h2>
  </div>
  <!--// 제목 end ------------------------------------------------------------->
  
  <!-- 버튼 -->
  <div class="mb-2 d-grid gap-2 d-md-flex justify-content-md-end">
      <input type="button" value="조회" class="btn btn-primary" id="doRetrieve" >
      <input type="button" value="등록" class="btn btn-primary"  id="moveToReg">
  </div>
  <!--// 버튼 ----------------------------------------------------------------->

   <!-- 검색 -->  
    <form action="#" name="board_frm" method="get" id="board_frm"  class="row g-2 align-items-center">
        <div class="col-sm-4">
          <input type="hidden" name="work_div"  id="work_div" placeholder="작업구분">
          <input type="hidden" name="page_no"   id="page_no"  placeholder="페이지 번호">        
          <input type="hidden" name="seq"       id="seq"      placeholder="순번">
        </div>

        <div class="col-sm-2 text-end g-2">
          <label for="search_div"  class="form-label">구분</label>
        </div>  
        <div class="col-sm-2">
	          <select name="search_div" id="search_div"  class="form-control">
	            <option value="">전체</option>
	            <codeTags:codeList pageCode="${boardSearchList }" selectedVal="${vo.searchDiv }"/>            
	          </select>  
          </div>
          <div class="col-sm-2">
	          <input type="search"  class="form-control" name="search_word" id="search_word" placeholder="검색어" 
	                 value="<c:if test="${not empty vo }">${vo.searchWord }</c:if>">
          </div>
     
  
          <div class="col-sm-2">       
	          <select name="page_size" id="page_size" class='form-control' >
	            <codeTags:codeList pageCode="${page }" selectedVal="${vo.pageSize }"/>
	          </select>
          </div>  
    </form>
     <!--// 검색 end ------------------------------------------------------------->
 
    
    
    <!-- content -->
    <!-- 행(row)을 만드는 태그는 <tr>와 셀을 만드는 <td>, <th>태그: -->
    <table class="table table-striped table-hover table-bordered" id="boardList">
      <thead>
        <tr class="table-success">
          <th class="text-center col-sm-1">no</th>
          <th class="text-center col-sm-5">제목</th>
          <th class="text-center col-sm-2">등록자</th>
          <th class="text-center col-sm-2">등록일</th>
          <th class="text-center col-sm-1">조회수</th>
          <th class="text-center col-sm-1">관리</th>
          <th style="display: none;">SEQ</th>
        </tr>
      </thead>
      <tbody>
         <c:choose>
          <c:when test="${not empty list }">
            <c:forEach var="vo"  items="${list}">
			        <tr>
			          <td  class="text-center"><c:out value="${vo.num }" escapeXml="true" /></td>
			          <td><c:out value="${vo.getTitle() }" escapeXml="true" /></td>
			          <td class="text-center"><c:out value="${vo.modId }" escapeXml="true" /></td>
			          <td class="text-center"><c:out value="${vo.modDt }" escapeXml="true" /></td>
			          <td class="text-end"><c:out value="${vo.readCnt }" escapeXml="true" /></td>
			          <td class="text-center">
			            <div>
			              <input type="button"  data-hidden-info="<c:out value='${vo.seq }' escapeXml='true' />" value="수정"  class="btn btn-outline-success btn-sm ">
			            </div>
			          </td>
			          <td style="display: none;"><c:out value='${vo.seq }' escapeXml='true' /></td>
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
		<nav aria-label="Page navigation example">

    <pagination:renderPaging 
          maxNum="${vo.totalCnt }" 
          currentPageNo="${vo.pageNo }" 
          rowPerPage="${vo.pageSize }" 
          bottomCount="${vo.bottomCount }" 
          url="${CP}/board/board.do" 
          scriptName="pageRetrieve" />    
		</nav>
		<!--// paging end --------------------------------------------------------->    
  
    <jsp:include page="/cmn/footer.jsp"></jsp:include>
  </div>
  
  
  
<!--// container end ---------------------------------------------------------->
<script src="${CP}/assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>