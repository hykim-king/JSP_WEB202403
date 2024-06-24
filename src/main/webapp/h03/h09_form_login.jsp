<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h2>로그인</h2>
  <hr/>
  <fieldset>
    <legend>login</legend>

    <form action="/WEB02/hello.do"  method="post" name="login_frm" >
      <label for="user_id">아이디</label>
      <input type="text" size="20" name="user_id" id="user_id" maxlength="20">
      <label for="passwd">비밀번호</label>
      <input type="password" size="20" name="passwd" id="passwd"  maxlength="20">
      <input type="submit" value="로그인">
    </form>
  
  </fieldset>
  user_id:${user_id}<br/>
  passwd:${passwd}<br/>
</body>
</html>