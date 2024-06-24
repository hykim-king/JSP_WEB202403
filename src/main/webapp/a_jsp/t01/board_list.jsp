<%@page import="com.pcwk.ehr.board.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.pcwk.ehr.cmn.SearchDTO"%>
<%@page import="com.pcwk.ehr.board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

   BoardDao  dao=new BoardDao();
   SearchDTO searchVO=new SearchDTO();
   searchVO.setPageNo(1);
   searchVO.setPageSize(10);
   
   List<BoardDTO> list = dao.doRetrieve(searchVO);
   
   for(BoardDTO vo   :list){
	   System.out.println(vo);
   }
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>행복코딩</title>
<link rel="stylesheet" href="/WEB02/css/poster.css">
</head>
<body>
  <hr />
  <h3>게시판_목록</h3>
  <!-- content -->
  <!-- 행(row)을 만드는 태그는 <tr>와 셀을 만드는 <td>, <th>태그: -->
  <table>
    <thead>
      <tr>
        <th>no</th>
        <th>제목</th>
        <th>등록자</th>
        <th>등록일</th>
        <th>조회수</th>
      </tr>
    </thead>
    <tbody>
      <%   for(BoardDTO vo   :list){ %>
      <tr>
        <td><%=vo.getNum() %></td>
        <td><%=vo.getTitle() %></td>
        <td><%=vo.getModId() %></td>
        <td><%=vo.getModDt() %></td>
        <td><%=vo.getReadCnt() %></td>
      </tr>     
      <%  } %>
    </tbody>

  </table>
  <!--// content end -->


</body>
</html>