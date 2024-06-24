<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg bg-body-tertiary mb-3">
  <div class="container">
    <a class="navbar-brand" href="#">
      <img src="/WEB02/assets/images/logo.png" alt="Bootstrap" width="50" >PWK
    </a> 
    
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/WEB02/board/board.do?work_div=doRetrieve">Board</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            템플릿
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="/WEB02/template/list.html">list</a></li>
            <li><a class="dropdown-item" href="/WEB02/template/reg.html">reg</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link disabled" aria-disabled="true">Disabled</a>
        </li>
      </ul>
    </div>  
  </div> 
</nav>