/**
 * 
 */
 document.addEventListener("DOMContentLoaded", function() {
    // 처리할 작업
    
  //객체 생성
  const doRetrieveBtn = document.querySelector("#doRetrieve");
  const doMoveToRegBtn= document.querySelector("#moveToReg");
  const searchWordInput= document.querySelector("#search_word");
  
  const searchDivSelect= document.querySelector("#search_div");


  //이벤트 헨들러 
  searchDivSelect.addEventListener("change", function(event){
    const choice = searchDivSelect.value;
    
    if(choice === ""){
      searchWordInput.value = "";
    }
    
  });
  
  doRetrieveBtn.addEventListener("click", doRetrieve);
  doMoveToRegBtn.addEventListener("click", moveToReg);
  searchWordInput.addEventListener("keydown", function(event) {
        console.log("Key pressed:", event.key, event.keyCode);
        const inputValue = searchWordInput.value;
        if(event.key === 'Enter' && null !== inputValue && inputValue.length > 0){
          console.log(`searchWordInput.value:${searchWordInput.value}`);
          doRetrieve();
        }
  });

  function moveToReg(){
      // 폼 요소 선택
      let frm = document.getElementById("board_frm");
      
      // 폼 데이터 설정
      frm.work_div.value = "moveToReg";
      
      // 각 입력 요소 값 출력
      console.log("frm.search_word.value: " + frm.search_word.value);
      
      // 서버로 보낼 액션 설정
      console.log("frm.action: " + "<%=cPath%>" + "/board/board.do");
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
      frm.action = "/WEB02" + "/board/board.do";
      
      // 폼 제출
      frm.submit();
  }    
});