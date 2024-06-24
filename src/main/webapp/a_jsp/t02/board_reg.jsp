<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 등록</title>
    <link rel="stylesheet" href="styles_0610.css">
</head>
<body>
    <div class="container"> 
        <h1>게시판 등록</h1> 
        <div class="form-group button-group">
            <button type="submit">등록</button>
        </div>
        <form action="submit_post" method="post">
            <div class="form-group">
                <label for="title">제목:</label>
                <input type="text" id="title" name="title" required>
            </div>
            <div class="form-group">
                <label for="content">내용:</label>
                <textarea id="content" name="content" rows="10" required></textarea>
            </div>
            <div class="form-group">
                <label for="author">등록자:</label>
                <input type="text" id="regId" name="regId" required>
            </div>
        </form>
    </div>
</body>
</html>
