package com.pcwk.ehr.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pcwk.ehr.board.BoardDTO;
import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.JView;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.StringUtil;


public class ConnectController extends HttpServlet implements ControllerV, PLog {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectController() {
    	log.debug("-----------------");
    	log.debug("ConnectController()");
    	log.debug("-----------------");
    }

    
    //GET방식 호출
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	log.debug("-----------------");
    	log.debug("doGet()");
    	log.debug("-----------------");		
		doWork(req,resp);
	}
	
    //POST방식 호출
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	log.debug("-----------------");
    	log.debug("doPost()");
    	log.debug("-----------------");				
		doWork(req,resp);
	}


	/**
	 * get/post방식 호출을 처리
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	public JView doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("-----------------");
    	log.debug("doWork()");
    	log.debug("-----------------");		
    	
    	JView  viewName = null;
    	
    	//request 인코딩 처리:
    	request.setCharacterEncoding("UTF-8");
    	
    	String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
    	
    	
    	log.debug("workDiv:{}",workDiv);
    	
    	switch (workDiv) {
		case "doRetrieve":
			viewName = doRetrieve(request,response);
			break;
		case "doSave":
			doSave(request,response);
			break;
		default:
    		log.debug("작업구분을 확인 하세요.workDiv:"+workDiv);			
			break;
		}

    	return viewName;
	}

	
	public void doSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("-----------------");
    	log.debug("doSave()");
    	log.debug("-----------------");		
	}


	public JView doRetrieve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	log.debug("-----------------");
    	log.debug("doRetrieve()");
    	log.debug("-----------------");
    	
    	//JSP viewName저장
    	JView viewName = null;
    	
    	//request읽기
    	String userNm = request.getParameter("user_nm");
    	log.debug("userNm:{}",userNm);
    	
    	//Model: Service객체
    	
    	
    	//UI전달 데이터 설정
    	request.setAttribute("userNm", userNm+"님");
    	
    	//RequestDispatcher dispacher = request.getRequestDispatcher("/a_jsp/j02/j04_connect_response.jsp");
    	//dispacher.forward(request, response);
    	return viewName =new JView("/a_jsp/j02/j04_connect_response.jsp");
	}
}
