package com.pcwk.ehr.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hello
 */
@WebServlet(description = "최초 Servlet", urlPatterns = { "/hello.do" })
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		   String userId = req.getParameter("user_id");
		   String passwd = req.getParameter("passwd");
		   
		   System.out.println("userId:"+userId);
		   System.out.println("passwd:"+passwd);
		   
		   //화면으로 데이터 전달
		   //처리 결과 저장
		   req.setAttribute("user_id", userId+"_Server");
		   req.setAttribute("passwd", passwd+"_Server");
		   ///h03/h09_form_login.jsp : 전달받을 화면
		   //req.setAttribute("key", 데이터);
		   RequestDispatcher dispatcher = req.getRequestDispatcher("/h03/h09_form_login.jsp");
		   dispatcher.forward(req, resp);
		   
	}	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hello() {
        super();
    }

    
    




	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter  out = response.getWriter();
		out.println("<html>");
		out.println("<head><meta charset='UTF-8'><title>hello</title></head>");
		out.println("<body>");
		out.println("<h2>Hello, world!</h2>");
		out.println("현재 시간:"+new Date());
		out.println("</body>");
		out.println("</html>");

	}  

}
