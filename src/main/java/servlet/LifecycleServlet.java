package com.pcwk.ehr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pcwk.ehr.cmn.PLog;

/**
 * Servlet implementation class LifecycleServlet
 */
@WebServlet(asyncSupported = true, description = "서블릿 설명", urlPatterns = { "/LifecycleServlet.do","/pcwk99.do" })
public class LifecycleServlet extends HttpServlet implements PLog {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LifecycleServlet() {
        super();
        log.debug("---------------------------------");
        log.debug("생성자 : {} ","LifecycleServlet()");
        log.debug("---------------------------------");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		 log.debug("---------------------------------");
		 log.debug("Servlet init ");
		 log.debug("---------------------------------");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		 log.debug("====================================");
		 log.debug("Servlet destroy ");
		 log.debug("====================================");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 log.debug("---------------------------------");
		 log.debug("doGet() ");
		 log.debug("---------------------------------");
		 
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//ContentType설정
		response.setContentType("text/html; charset=UTF-8");
		
		//Character Encoding
		response.setCharacterEncoding("UTF-8");
		
		//응답 본문 작성
		PrintWriter out = response.getWriter();
	
		Date now=new Date();
		
		//
		out.print(" <!DOCTYPE html>                        \n");
		out.print(" <html>                                 \n");
		out.print(" <head>                                 \n");
		out.print(" <meta charset='UTF-8'>                 \n");
		out.print(" <title>내일은 더 즐거운 수요일</title>    \n");
		out.print(" </head>                                \n");
		out.print(" <body>                                 \n");
		out.print("   <h2>Hello, World!(행복한 수요일)</h2>  \n");
		out.print("<p>"+now +"</p>");
		out.print(" </body>                                \n");
		out.print(" </html>                                \n");		
		
	}

}
//(LifecycleServlet.java:29) - ---------------------------------
//(LifecycleServlet.java:30) - 생성자 : LifecycleServlet() 
//(LifecycleServlet.java:31) - ---------------------------------
//(LifecycleServlet.java:38) - ---------------------------------
//(LifecycleServlet.java:39) - Servlet init 
//(LifecycleServlet.java:40) - ---------------------------------
//(LifecycleServlet.java:56) - ---------------------------------
//(LifecycleServlet.java:57) - doGet() 
//(LifecycleServlet.java:58) - ---------------------------------