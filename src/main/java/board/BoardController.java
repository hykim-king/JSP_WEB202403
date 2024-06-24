package com.pcwk.ehr.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.pcwk.ehr.answer.AnswerDTO;
import com.pcwk.ehr.answer.AnswerService;
import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.JView;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.ehr.code.CodeService;
import com.pcwk.ehr.code.CodeVO;
import com.pcwk.ehr.member.MemberDTO;

public class BoardController extends HttpServlet implements ControllerV, PLog {
	private static final long serialVersionUID = 1L;

	BoardService service;
    CodeService  codeService;
    
    AnswerService answerService;
    
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardController() {
		log.debug("-----------------");
		log.debug("BoardController()");
		log.debug("-----------------");

		service = new BoardService();
		codeService = new CodeService();
		
		answerService = new AnswerService();
	}

	public JView doRetrieve(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("doRetrieve()");
		log.debug("-----------------");

		// JSP viewName저장
		JView viewName = null;

		//코드조회
		CodeVO pageCode=new CodeVO();
		pageCode.setMsgCode("PAGE_SIZE");
		
		List<CodeVO> pageCodeList = codeService.doRetrieve(pageCode);
		log.debug("pageCodeList:{}", pageCodeList);
		// 검색조건 UI로 전달
		request.setAttribute("page", pageCodeList);		
		
		//BOARD_SEARCH
		pageCode.setMsgCode("BOARD_SEARCH");
		List<CodeVO> boardSearchList = codeService.doRetrieve(pageCode);
		log.debug("boardSearchList:{}", boardSearchList);
		// 검색조건 UI로 전달
		request.setAttribute("boardSearchList", boardSearchList);				
		
		
		SearchDTO inVO = new SearchDTO();
		// http://localhost:8080/WEB02/board/board.do?work_div=doRetrieve&page_no=1&page_size=10
		// page_no
		// page_size
		String pageNo = StringUtil.nvl(request.getParameter("page_no"), "1");
		String pageSize = StringUtil.nvl(request.getParameter("page_size"), "10");

		String searchWord = StringUtil.nvl(request.getParameter("search_word"), "");
		String searchDiv = StringUtil.nvl(request.getParameter("search_div"), "");

		log.debug("pageNo:{}", pageNo);
		log.debug("pageSize:{}", pageSize);
		log.debug("searchWord:{}", searchWord);
		log.debug("searchDiv:{}", searchDiv);

		inVO.setPageNo(Integer.parseInt(pageNo));
		inVO.setPageSize(Integer.parseInt(pageSize));
		inVO.setSearchDiv(searchDiv);
		inVO.setSearchWord(searchWord);

		log.debug("inVO:{}", inVO);

		// service call
		List<BoardDTO> list = service.doRetrieve(inVO);

		// return 데이터 확인
		int i = 0;
		for (BoardDTO vo : list) {
			log.debug("i: {}, vo: {}", ++i, vo);
		}

		// UI 데이터 전달
		request.setAttribute("list", list);

		//paging:총글수: totalCnt,
		//currentPageNo: pagNo
		//rowPerPage   : pageSize
		//bottomCount  : 10
		int botomCount = 10;
		int totalCnt = 0; //총글수
		
		if(null != list && list.size()>0) {
			BoardDTO pagingVO = list.get(0);
			totalCnt = pagingVO.getTotalCnt();
			log.debug("totalCnt: {}", totalCnt);
			
			inVO.setTotalCnt(totalCnt);
		}
		
		inVO.setBottomCount(botomCount);
		
		// 검색조건 UI로 전달
		request.setAttribute("vo", inVO);
		log.debug("inVO: {}", inVO);
		return  new JView("/board/board_list.jsp");
	}

	public JView doSaveAjax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("doSaveAjax()");
		log.debug("-----------------");
		BoardDTO inVO = new BoardDTO();

		String title = StringUtil.nvl(request.getParameter("title"), "");
		String contents = StringUtil.nvl(request.getParameter("contents"), "");
		String regId = StringUtil.nvl(request.getParameter("reg_id"), "");

		inVO.setTitle(title);
		inVO.setContents(contents);
		inVO.setRegId(regId);
		inVO.setModId(regId);
		log.debug("inVO:{}", inVO);

		int flag = service.doSave(inVO);
		log.debug("flag:{}", flag);

		// VIEW전송
		// flag: 1성공, 0실패
		com.pcwk.ehr.cmn.MessageVO msgVO = new com.pcwk.ehr.cmn.MessageVO();
		msgVO.setMessageId(String.valueOf(flag));

		String message = "";
		if (1 == flag) {
			message = "등록 성공!";
		} else {
			message = "등록 실패!";
		}

		msgVO.setMsgContents(message);

		Gson gson = new Gson();
		String jsonString = gson.toJson(msgVO);

		log.debug("===========================");
		log.debug("=jsonString=" + jsonString);
		log.debug("===========================");

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonString);

		return null;

	}

	public JView moveToReg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("moveToReg()");
		log.debug("-----------------");
		return new JView("/board/board_reg.jsp");
	}
  
	public JView doSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("doSave()");
		log.debug("-----------------");
		BoardDTO  inVO=new BoardDTO();
		String title = StringUtil.nvl(request.getParameter("title"), "");
		String regId = StringUtil.nvl(request.getParameter("regId"), "");
		String contents = StringUtil.nvl(request.getParameter("contents"), "");
		
		log.debug("title:{}",title);
		log.debug("regId:{}",regId);
		log.debug("contents:{}",contents);
		
		inVO.setTitle(title);
		inVO.setContents(contents);
		inVO.setRegId(regId);
		inVO.setModId(regId);
		
		int flag = this.service.doSave(inVO);
		log.debug("flag:{}",flag);
		
		if(1 == flag) {
			return new JView("/board/board.do?work_div=doRetrieve");
		}
		
		
		return null;                   
	}
	
	public JView ajaxDoSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("ajaxDoSave()");
		log.debug("-----------------");
		BoardDTO  inVO=new BoardDTO();
		String title = StringUtil.nvl(request.getParameter("title"), "");
		//String regId = StringUtil.nvl(request.getParameter("regId"), "");
		String contents = StringUtil.nvl(request.getParameter("contents"), "");
		
		String regId = "";
		HttpSession session=request.getSession();
		if(null != session &&  null != session.getAttribute("member")) {
			MemberDTO member =  (MemberDTO) session.getAttribute("member");
			regId = member.getMemberId();
		}else { //session 없는 경우
			MessageVO  validVO =new MessageVO();
			validVO.setMessageId(String.valueOf("50"));
			validVO.setMsgContents("로그인 페이지로 이동 합니다.");
			
			log.debug("messageVO:{}",validVO);
			
			Gson gson=new Gson();
			String jsonString = gson.toJson(validVO);
			
			log.debug("jsonString:{}",jsonString);
			
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.print(jsonString);
			
			return null;			
			
		}
		
		
		log.debug("title:{}",title);
		log.debug("regId:{}",regId);
		log.debug("contents:{}",contents);
		
		inVO.setTitle(title);
		inVO.setContents(contents);
		inVO.setRegId(regId);
		inVO.setModId(regId);
		
		int flag = this.service.doSave(inVO);
		log.debug("flag:{}",flag);		
		
		String message = "";
		if(flag == 1) {
			message = "등록성공";
		}else {
			message= "등록실패";
		}
		MessageVO  messageVO =new MessageVO();
		messageVO.setMessageId(String.valueOf(flag));
		messageVO.setMsgContents(message);
		
		log.debug("messageVO:{}",messageVO);
		
		Gson gson=new Gson();
		String jsonString = gson.toJson(messageVO);
		
		log.debug("jsonString:{}",jsonString);
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		
		return null;
	}
	
	public JView doSelectOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("doSelectOne()");
		log.debug("-----------------");
		BoardDTO  inVO=new BoardDTO();
		String seq = StringUtil.nvl(request.getParameter("seq"), "0");
		
		
		String regId = "";
		HttpSession session=request.getSession();
		if(null != session &&  null != session.getAttribute("member")) {
			MemberDTO member =  (MemberDTO) session.getAttribute("member");
			regId = member.getMemberId();
		}
		inVO.setRegId(regId);
		inVO.setModId(regId);
		inVO.setSeq(Integer.parseInt(seq));
		log.debug("inVO:"+inVO);
		
		BoardDTO outVO = this.service.selectOneReadCnt(inVO);
		log.debug("outVO:"+outVO);
		
		// UI 데이터 전달
		request.setAttribute("outVO", outVO);
		
		//answerService: 추가
		answerDoRetrieve(request,response);
		
		return new JView("/board/board_mng.jsp");
	}
	
	public void answerDoRetrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AnswerDTO  answerInVO = new AnswerDTO();
		String seq = StringUtil.nvl(request.getParameter("seq"), "0");
		answerInVO.setBoardSeq(Integer.parseInt(seq));
		
		String pageNo = StringUtil.nvl(request.getParameter("page_no"), "1");
		List<AnswerDTO> answerList = answerService.doRetrieve(answerInVO);
		int botomCount = 10;
		int totalCnt = 0; //총글수
		
		AnswerDTO pagingVO =new AnswerDTO();
		if(null != answerList && answerList.size()>0) {
			pagingVO = answerList.get(0);
			totalCnt = pagingVO.getTotalCnt();
			
			pagingVO.setTotalCnt(totalCnt);
			pagingVO.setBottomCount(botomCount);
			pagingVO.setPageSize(10);
			pagingVO.setPageNo(Integer.parseInt(pageNo));
			
			
		}
		log.debug("pagingVO: {}", pagingVO);
		request.setAttribute("pagingVO", pagingVO);

		request.setAttribute("answerList", answerList);		
	}
	
	
	/**
	 * 답변 상세 AJAX
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public JView answerAjaxDoRetrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AnswerDTO  answerInVO = new AnswerDTO();
		String seq = StringUtil.nvl(request.getParameter("seq"), "0");
		answerInVO.setBoardSeq(Integer.parseInt(seq));
		
		String pageNo = StringUtil.nvl(request.getParameter("page_no"), "1");
		answerInVO.setPageNo(Integer.parseInt(pageNo));
		
		List<AnswerDTO> answerList = answerService.doRetrieve(answerInVO);
		int botomCount = 10;
		int pageSize = 10;
		int totalCnt = 0; //총글수
		
		AnswerDTO pagingVO =new AnswerDTO();
		if(null != answerList && answerList.size()>0) {
			pagingVO = answerList.get(0);
			totalCnt = pagingVO.getTotalCnt();
			
			pagingVO.setTotalCnt(totalCnt);
			pagingVO.setBottomCount(botomCount);
			pagingVO.setPageSize(pageSize);
			pagingVO.setPageNo(Integer.parseInt(pageNo));   
			
			
		}
		//log.debug("pagingVO: {}", pagingVO);
		//request.setAttribute("pagingVO", pagingVO);
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Gson gson = new Gson();
        // JSON 객체로 변환하면서 루트 객체에 이름 부여
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("answerList", gson.toJsonTree(answerList));

		jsonObject.add("pageObject", gson.toJsonTree(pagingVO));
		

        // JSON 문자열로 변환
        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = prettyGson.toJson(jsonObject);
        
		
		log.debug("===========================");
		log.debug("=jsonString=" + jsonString);
		log.debug("===========================");
		out.print(jsonString);  
		 
		return null;
	}
	
	
	//doDelete는 상속 받은 HttpServlet에 메서드로 return이 void이므로 이름 변경 
	public JView doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("doDel()");
		log.debug("-----------------");
		
		BoardDTO inVO =new BoardDTO();
		String seq =StringUtil.nvl(request.getParameter("seq"),"0");
		
		inVO.setSeq(Integer.parseInt(seq));
		log.debug("inVO:"+inVO);
		
		int flag = service.doDelete(inVO);
		log.debug("flag:{}",flag);
		
		String message = "";
		if(1==flag) {
			message = "삭제 성공";
		}else {
			message = "삭제 실패";
		}
		
		MessageVO messageVO = new MessageVO();
		messageVO.setMessageId(String.valueOf(flag));
		messageVO.setMsgContents(message);
		log.debug("messageVO:{}",messageVO);
		
		Gson gson=new Gson();
		String jsonString = gson.toJson(messageVO);
		log.debug("jsonString:{}",jsonString);
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out= response.getWriter();
		out.print(jsonString);
		return null;
	}
	
	public JView doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("doUpdate()");
		log.debug("-----------------");
		
		BoardDTO  inVO=new BoardDTO();
		String seq =StringUtil.nvl(request.getParameter("seq"), "0");
		String title =StringUtil.nvl(request.getParameter("title"), "");
		String contents =StringUtil.nvl(request.getParameter("contents"), "");
		String modId =StringUtil.nvl(request.getParameter("modId"), "");
		
		log.debug("seq:"+seq);
		log.debug("title:"+title);
		log.debug("contents:"+contents);
		log.debug("modId:"+modId);
		
		inVO.setSeq(Integer.parseInt(seq));
		inVO.setTitle(title);
		inVO.setContents(contents);
		inVO.setModId(modId);
		
		log.debug("inVO:"+inVO);
		int flag = service.doUpdate(inVO);
		String message = "";
		log.debug("flag:"+flag);
		
		if(1==flag) {
			message = "수정 되었습니다.";
		}else {
			message = "수정 실패!";
		}
		
		MessageVO  messageVO=new MessageVO();
		messageVO.setMessageId(String.valueOf(flag));
		messageVO.setMsgContents(message);
		
		Gson  gson=new Gson();
		String jsonString = gson.toJson(messageVO);
		
		log.debug("jsonString:"+jsonString);
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		
		return null;
	}
	
	@Override
	public JView doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("doWork()");
		log.debug("-----------------");

		JView viewName = null;

		String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
		log.debug("workDiv : {} ", workDiv);

		switch (workDiv) {
		//
		case "answerAjaxDoRetrieve":
			viewName = answerAjaxDoRetrieve(request, response);
			break;	
			
		case "doUpdate":
			viewName = doUpdate(request, response);
			break;			
		case "doDelete":
			viewName = doDel(request, response);
			break;			
		case "doSelectOne":
			viewName = doSelectOne(request, response);
			break;		
		case "ajaxDoSave":
			viewName = ajaxDoSave(request, response);
			break;
		case "doSave":
			viewName = doSave(request, response);
			break;
		case "moveToReg":
			viewName = moveToReg(request, response);
			break;
		case "doRetrieve":
			viewName = doRetrieve(request, response);
			break;
		default:
			log.debug("작업구분을 확인 하세요. workDiv : {} ", workDiv);
			break;
		}

		return viewName;
	}

}
