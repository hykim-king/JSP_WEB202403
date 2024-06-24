
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
   #uploadArea{
      width: 450px;
      border: 1px solid black;
      margin: auto;
   }

   
</style> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/WEB_EX01/js/jquery-ui.css" >
<title>Insert title here</title>
<script src="/WEB_EX01/js/jquery-1.12.4.js"></script>
<script src="/WEB_EX01/js/jquery-ui.js"></script>
</head>
<body>
  <h3>FileUpload</h3>   
  <hr/> 
  <div id="uploadArea">
    <form method="post"  enctype="multipart/form-data" action="/WEB02/file/upload">
      <table>
        <tr>
          <td colspan="2" >UploadForm</td>
        </tr>
        <tr>
          <td><label for="name">올림사람:</label></td>
          <td><input  type="text" name="name" id="name" /></td>
        </tr>
        <tr>
          <td><label for="title">제목:</label></td>
          <td><input  type="text" name="title" id="title" /></td>
        </tr>
        <tr>
          <td><label for="fileName01">파일명01:</label></td>
          <td><input  type="file" name="fileName01" id="fileName01" /></td>
        </tr>
        <tr>
          <td><label for="fileName02">파일명02:</label></td>
          <td><input  type="file" name="fileName02" id="fileName02" /></td>
        </tr>
        <tr>
          <td colspan="2"><input type="submit" value="전송" /></td>
        </tr>                                             
      </table>
    </form>
  </div>
</body>
</html>