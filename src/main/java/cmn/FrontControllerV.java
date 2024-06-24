package com.pcwk.ehr.cmn;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pcwk.ehr.board.BoardController;
import com.pcwk.ehr.email.SendEmailController;
import com.pcwk.ehr.member.MemberController;
import com.pcwk.ehr.servlet.ConnectController;
import com.pcwk.ehr.servlet.CookieController;
import com.pcwk.ehr.servlet.LoginController;

/**
 * Servlet implementation class FrontControllerV
 */
@WebServlet("*.do")
public class FrontControllerV extends HttpServlet implements PLog {
	private static final long serialVersionUID = 1L;
       
	//URL과, ControllerV
	private Map<String, ControllerV> controllerMap =new HashMap<String, ControllerV>();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontControllerV() {
    	log.debug( "FrontControllerV()" ); 
    	
    	controllerMap.put("/WEB02/mail/mail.do", new SendEmailController());
    	controllerMap.put("/WEB02/member/login.do", new MemberController());
    	controllerMap.put("/WEB02/board/board.do", new BoardController());
    	controllerMap.put("/WEB02/connect/connect.do", new ConnectController());    	
    	controllerMap.put("/WEB02/cookie/cookie.do", new CookieController());
    	controllerMap.put("/WEB02/login/login.do", new LoginController());
        
    }  

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		log.debug("requestURI:{}",requestURI);
		
		ControllerV   controller=controllerMap.get(requestURI);
		//controller:com.pcwk.ehr.board.BoardController@39c4214f
		log.debug("controller:{}",controller);
	
		//controller가 null이면 404 예외
		if(null == controller) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
    	//request 인코딩 처리:
    	request.setCharacterEncoding("UTF-8");
    	
    	//-----------------------------------------
        // JSON 데이터를 읽기
//    	String contentType = request.getHeader("Content-Type");
//    	if(null != contentType) {
//	    	log.debug("contentType:{}",contentType);
//	    	
//	        StringBuilder sb = new StringBuilder();
//	        BufferedReader reader = request.getReader();
//	        String line;
//	        while ((line = reader.readLine()) != null) {
//	            sb.append(line);
//	        }
//	        String jsonString = sb.toString();    	
//	          
//	        log.debug("jsonString:{}",jsonString);
//	        // JSON 파싱
//	        Gson gson = new Gson();
//	        Type type = new TypeToken<Map<String, String>>(){}.getType();
//	        Map<String, String> jsonMap = gson.fromJson(jsonString, type);
//	
//	        // JSON 데이터를 기반으로 request에 key, value 설정
//	        for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
//	            request.setAttribute(entry.getKey(), entry.getValue());
//	            
//	            log.debug(request.getParameter("work_div"));
//	        }
//    	}  
    	//-----------------------------------------
		JView jview = controller.doWork(request, response);
		log.debug("jview:{}",jview);
		
		//각각의 컨트롤러가 forward 로직을 제대로 수행하고 있는지 하나하나 
		//신경쓸 필요가 없고 JView 객체만 제대로 반환한다면 무리없이 동작한다.
		
		if(null != jview  && jview.getViewName().length()>1) {
			jview.render(request, response);
		}
	}

}





