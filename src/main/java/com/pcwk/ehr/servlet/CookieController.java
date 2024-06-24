package com.pcwk.ehr.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.JView;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.StringUtil;

public class CookieController implements ControllerV,PLog {

	public CookieController() {
    	log.debug("-----------------");
    	log.debug("CookieController()");
    	log.debug("-----------------");			
	}
	
	/**
	 * Servlet Cookie 설정
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public JView setCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("-----------------");
    	log.debug("setCookie()");
    	log.debug("-----------------");			
		
    	//쿠키 생성
    	Cookie  userCookie = new Cookie("username", "pcwk");
    	
    	//도메인 설정: pcwk.com으로 설정
    	userCookie.setDomain("pcwk.com");
    	
    	//경로설정
    	userCookie.setPath("/");
		
    	
    	//유효기간설정(1일)
    	userCookie.setMaxAge(60*60*24);
    	
    	//쿠키를 응답에 추가
    	response.addCookie(userCookie);
    	
    	//응답 작성
    	response.setContentType("text/html; charset=UTF-8");
    	PrintWriter pOut = response.getWriter();
    	
    	pOut.println("Cookie 'username set to pcwk '");
		return null;
	}
	@Override
	public JView doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("-----------------");
    	log.debug("doWork()");
    	log.debug("-----------------");	
    	
    	JView  viewName = null;
    	
    	String workDiv = StringUtil.nvl( request.getParameter("work_div"),"");
        log.debug("workDiv : {} ",workDiv);    	
    	
    	switch(workDiv) {
    	case "setCookie":
    		viewName = setCookie(request,response);
    	break;
    	}
    	
    	
		return viewName;
	}

}
