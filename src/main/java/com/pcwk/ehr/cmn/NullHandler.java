package com.pcwk.ehr.cmn;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NullHandler implements ControllerV, PLog {

	@Override
	public JView doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("-----------------");
    	log.debug("NullHandler 작업구분을 확인 하세요");
    	log.debug("-----------------");			
    	
    	//request 인코딩 처리:
    	request.setCharacterEncoding("UTF-8");
    	
    	String workDiv = StringUtil.nvl( request.getParameter("work_div"),"");
        log.debug("workDiv : {} ",workDiv);
        
		response.sendError(HttpServletResponse.SC_NOT_FOUND, "작업구분을 확인 하세요");
		return null;
	}

}
