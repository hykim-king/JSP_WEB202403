package com.pcwk.ehr.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.JView;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.ehr.member.OldMemberDTO;

public class LoginController implements ControllerV,PLog {

	public LoginController() {
    	log.debug("-----------------");
    	log.debug("LoginController()");
    	log.debug("-----------------");			
	}
	
	
	public JView login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("-----------------");
    	log.debug("login()");
    	log.debug("-----------------");		
		
    	String userId = StringUtil.nvl(request.getParameter("user_id"),"");
    	String userPw = StringUtil.nvl(request.getParameter("user_pw"),"");
    	
    	String viewName = "";
    	//id,비번 일치하면 Session처리
    	if("jjj".equals(userId)   && "4321".equals(userPw)) {
    		
    		//세션 객체 생성
    		HttpSession  session = request.getSession();
    		
    		OldMemberDTO  member=new OldMemberDTO();
    		member.setUserId(userId);
    		member.setPassword(userPw);
    		member.setName("이상무");
    		
    		//세션에 데이터 저장
    		session.setAttribute("member", member);
    		
    		log.debug("세션 생성");
    		viewName = "/a_jsp/j04/j05_login_result.jsp";
    	}
    	
    	log.debug("userId: {}",userId);
    	log.debug("userPw: {}",userPw);
    	
		return new JView(viewName);
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
    		viewName = "/a_jsp/j04/j05_login_result.jsp";
    	}
    	
		return new JView(viewName);
	}
	
	@Override
	public JView doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("-----------------");
    	log.debug("doWork()");
    	log.debug("-----------------");		
    	
    	JView viewName = null;
    	
    	String workDiv = StringUtil.nvl( request.getParameter("work_div"),"");
        log.debug("workDiv : {} ",workDiv);       	
    	
        switch(workDiv) {
        case "login":
        	viewName=login(request,response);
        	break;
        case "logout":
        	viewName=logout(request,response);    
        	break;
        }
        
		return viewName;
	}

}
