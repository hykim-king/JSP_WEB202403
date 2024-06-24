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
      <input type="hidden" name="work_div" value="login">
    <fieldset>
      <label>아이디:<input type="text" id="user_id" name="user_id" size="10"></label>
      <label>비밀번호:<input type="password" id="user_pw" name="user_pw" size="10"></label>
      <!-- submit됨 -->
      <input type="image" src="/WEB02/images/login.png" alt="로그인">
    </fieldset>
  
  </form>

</body>
</html>