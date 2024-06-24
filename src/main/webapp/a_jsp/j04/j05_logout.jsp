<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
  input[type="image"]{
    vertical-align: middle; /* 버튼 수직 middle정렬 */
  } 
</style>
<meta charset="UTF-8">
<title>행복코딩</title>
</head>
<body>
  <h2>image버튼</h2>
  <hr/>
  <form action="/WEB02/login/login.do" method="post">
      <input type="hidden" name="work_div" value="logout">
    <fieldset>
      <!-- submit됨 -->
      <input type="submit" value="로그아웃" >
    </fieldset>
  
  </form>

</body>
</html>