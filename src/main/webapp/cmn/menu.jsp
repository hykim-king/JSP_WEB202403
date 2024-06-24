<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- board url --%>
<c:url var="boardURL" value="/board/board.do">
   <c:param name="work_div" value="doRetrieve"></c:param>
</c:url>
    
<%-- logout url --%>    
<c:url var="logoutURL" value="/member/login.do">
   <c:param name="work_div" value="logout"></c:param>
</c:url>    
<nav class="navbar navbar-expand-lg  navbar-light" style="background-color: #e3f2fd;">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">PCW</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/WEB02/main/main.jsp">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${boardURL}">Board</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/WEB02/a_jsp/j06/form_mail.jsp">Email</a>
        </li>          
        <li class="nav-item">
          <a class="nav-link" href="/WEB02/template/list.html">List</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/WEB02/template/reg.html">Reg</a>
        </li>        
        <li class="nav-item">
          <a class="nav-link disabled" aria-disabled="true">Disabled</a>
        </li>
        <li>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
           <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <c:choose>
              <c:when test="${empty sessionScope.member }">
                <li class="nav-item">
                  <a class="nav-link"  href="/WEB02/login/login.jsp">로그인&nbsp;<i class="bi bi-box-arrow-left"></i></a>
                </li> 
              </c:when>
              <c:otherwise>
                <li class="nav-item">
                  <a class="nav-link"  href="${logoutURL}">${sessionScope.member.name } 님(로그아웃)&nbsp;<i class="bi bi-box-arrow-right"></i></a>
                </li> 
              </c:otherwise>
            </c:choose>
            </ul>      
        </div>
        </li>       
      </ul>
    </div>
  </div>
</nav>