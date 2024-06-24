package com.pcwk.ehr.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.JView;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.StringUtil;

public class MemberController implements PLog, ControllerV {

	MemberService service;
	
	public MemberController() {
		log.debug("-----------------");
		log.debug("MemberController()");
		log.debug("-----------------");		
		
		service = new MemberService();
	}
	   
	public JView login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("login()");
		log.debug("-----------------");	
		MemberDTO  inVO=new MemberDTO();
		
		String memberId =StringUtil.nvl(request.getParameter("memberId"), "");
		String password =StringUtil.nvl(request.getParameter("password"), "");
		log.debug("memberId:"+memberId);
		log.debug("password:"+password);
		
		inVO.setMemberId(memberId);
		inVO.setPassword(password);
		
		log.debug("inVO:"+inVO);
		DTO dto =service.doMemberSelect(inVO);
		
		MessageVO  message = new MessageVO();
		
		if( dto instanceof MemberDTO) {
			MemberDTO outVO = (MemberDTO) dto;
			log.debug("성공 outVO  :{}",outVO);
			
			//session생성
			HttpSession session = request.getSession();
			
			//세션에 데이터 저장
			session.setAttribute("member", outVO);
			
			//30, 로그인 성공
			message.setMessageId("30");
			message.setMsgContents("로그인 성공");
			
		}else {
			message = (MessageVO) dto;
			log.debug("실패 message  :{}",message);
		}		
		
		//ajax
		Gson gson=new Gson();
		String jsonString = gson.toJson(message);
		log.debug("jsonString:"+jsonString);
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		
		return null;  
	}
	
	public JView logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("logout()");
		log.debug("-----------------");		
		HttpSession  session = request.getSession(false);
		log.debug("session:"+session);
		String viewName ="";
		
    	if(null != session) {
    		log.debug("session:"+session);
    		session.invalidate();
    		viewName = "/main/main.jsp";
    	}		
		
		return new JView(viewName);
	}
	@Override
	public JView doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("doWork()");
		log.debug("-----------------");	
		
		JView viewName = null;   
		String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
		log.debug("workDiv : {} ", workDiv);
		
		switch(workDiv) {
		case "logout":
			viewName=logout(request, response);
			break;		
		case "login":
			viewName=login(request, response);
			break;
		default:
			log.debug("작업구분을 확인 하세요. workDiv : {} ", workDiv);
			break;			
		}
		
		
		return viewName;
	}

}
