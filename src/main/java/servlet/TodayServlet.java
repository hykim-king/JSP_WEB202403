package com.pcwk.ehr.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TodayServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ContentType설정
		response.setContentType("text/html; charset=UTF-8");
		
		//Character Encoding
		response.setCharacterEncoding("UTF-8");
		
		//응답 본문 작성
		PrintWriter out = response.getWriter();
	
		//
		out.print(" <!DOCTYPE html>                        \n");
		out.print(" <html>                                 \n");
		out.print(" <head>                                 \n");
		out.print(" <meta charset='UTF-8'>                 \n");
		out.print(" <title>내일은 더 즐거운 수요일</title>    \n");
		out.print(" </head>                                \n");
		out.print(" <body>                                 \n");
		out.print("   <h2>Hello, World!(행복한 수요일)</h2>  \n");
		out.print(" </body>                                \n");
		out.print(" </html>                                \n");
	}

	
	
}
