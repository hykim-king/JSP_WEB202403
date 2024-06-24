<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib  prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib  prefix="fn"   uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동가홍상</title>
<script src="/WEB02/assets/js/jquery_3_7_1.js"></script>
</head>
<body>
    <h2>Dom노드 추가/삭제</h2>
    <hr/>
    <div id="container">
      <a href="#" id="addView">더보기</a>
      <div id="info"></div>
    
    </div>
    
    <script>
       const addViewBTN = document.querySelector("#addView");
       console.log('addViewBTN:',addViewBTN);
    
       
       addViewBTN.addEventListener("click",function(){
    	   console.log('addViewBTN click:');
    	   let newP = document.createElement("p");//p태그 생성
    	   
    	   let txtNode = document.createTextNode("Dom은 document object model의 약자 입니다.");//text 생성
    	   
    	   newP.appendChild(txtNode);//ㅔ
    	   document.querySelector("#info").appendChild(newP);
    	   
    	   
       });
    </script>
</body>
</html>