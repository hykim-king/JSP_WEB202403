<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
<script src="/WEB02/assets/js/jquery_3_7_1.js"></script>

<script>

</script>
</head>
<body>
<div>
    <!-- 제목 -->
    <div>
	    <h2>게시-등록</h2>
	    <hr/>
    </div>
    <!--//제목 --------------------------------------------------------------->
    
    <!-- 버튼 -->
    <div>
      <input type="button" value="목록"  id="moveToList">
      <input type="button" value="등록"  id="doSave">
    </div>
    <!--//버튼 --------------------------------------------------------------->
    
    <!-- form -->
    <form action="#" name="regForm" id="regForm">
       <input type="hidden"  name="work_div" id="work_div">
       <div>
        <label for="title">제목</label>
        <input type="text" name="title" id="title"  maxlength="75" required="required">
       </div>
       
       <div>
        <label for="regId">등록자</label>
        <input type="text" name="regId" id="regId"  maxlength="20" required="required">
       </div>
       
       <div>
        <label for="title">내용</label>
        <textarea rows="7" cols="10" id="contents" name="contents"></textarea>
       </div>              
    </form>
    <!--//form --------------------------------------------------------------->
</div>    
</body>
</html>