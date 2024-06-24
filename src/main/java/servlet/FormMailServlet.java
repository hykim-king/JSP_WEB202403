package com.pcwk.ehr.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.StringUtil;

import javax.mail.*;
import javax.mail.internet.*;

@WebServlet("/form_mail/mail.pcw")
public class FormMailServlet extends HttpServlet implements PLog {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FormMailServlet() {
		log.debug("---------------------");
		log.debug("---FormMailServlet()-");
		log.debug("---------------------");

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("---------------------");
		log.debug("---doGet()-");
		log.debug("---------------------");
//        받는 사람: mailTo
//        제목:title
//        내용:contents
		String mailTo = StringUtil.nvl(request.getParameter("mailTo"), "");
		String title = StringUtil.nvl(request.getParameter("title"), "");
		String contents = StringUtil.nvl(request.getParameter("contents"), "");

		//http://localhost:8080/WEB02/form_mail/mail.pcw?mailTo=jamesol@paran.com&title=subject&contents=내용
		
		//발신자 mail 계정과 비번: 
		final String from = "james@naver.com";//james@naver.com
		final String user = "james";//james
		final String password = "본인 naver비번";//james@naver.com
		
		
		//naver SMTP 서버명 : smtp.naver.com
		String host = "smtp.naver.com";
		
		//SMTP 서버정보
		Properties porps =new Properties();
		porps.put("mail.smtp.host", host);
		porps.put("mail.smtp.port", 465);
		porps.put("mail.smtp.auth", "true");
		porps.put("mail.smtp.ssl.enable", "true");
		porps.put("mail.smtp.ssl.trust", host);
		
		//SMTP서버정보와 사용자/비번을 기반으로 Session 클래스 생성
		Session session = Session.getInstance(porps, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication(){
			   return new PasswordAuthentication(user, password);
		    }
		});
		
		
		try {
			//4. Message 클래스를 사용하여 수신자, 내용, 제목, 메시지를 작성한다.
			Message  message = new MimeMessage(session);
			
			//from
			message.setFrom(new InternetAddress(from));
			//to
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
			
			//subject
			message.setSubject(title);
			
			//contents
			message.setText(contents);
			
			//Transport 클래스 사용 전송
			Transport.send(message);
			
			
		}catch(MessagingException e) {
			log.debug("-MessagingException:-"+e.getMessage());
			throw new RuntimeException(e);
		}
		
		log.debug("---------------------");
		log.debug("--전송 성공!-");
		log.debug("---------------------");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("---------------------");
		log.debug("---doPost()-");
		log.debug("---------------------");
		doGet(request, response);
	}

}
