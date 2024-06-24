package com.pcwk.ehr.email;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.pcwk.ehr.cmn.ControllerV;
import com.pcwk.ehr.cmn.JView;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.StringUtil;

public class SendEmailController implements ControllerV, PLog {

	public SendEmailController() {
		log.debug("-----------------");
		log.debug("SendEmailController()");
		log.debug("-----------------");
	}

	public JView sendMail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("sendMail()");
		log.debug("-----------------");
		String title = StringUtil.nvl(request.getParameter("title"), "");
		String emailTo = StringUtil.nvl(request.getParameter("email"), "");
		String contents = StringUtil.nvl(request.getParameter("contents"), "");
		// 1. 발신자의 mail계정과 비번
		String from = "jamesol@naver.com";
		final String username = "jamesol";
		final String password = "네이버 비번";

		String host = "smtp.naver.com";

		// 2. Property에 SMTP서버정보 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);

		//3. STMP서버정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스를 생성한다.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			//4. Message 클래스를 사용하여 수신자와 내용, 제목, 메시지를 작성한다.
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from)); //4.1.from
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));//4.2.to
			message.setSubject(title);//4.3.제목
			message.setText(contents);//4.4.내용
			
			//5. Transport 클래스를 사용하여 작성한 메시지를 전달한다. 
			Transport.send(message);

			response.getWriter().println("Email sent successfully");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		return null;
	}

	@Override
	public JView doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("-----------------");
		log.debug("doWork()");
		log.debug("-----------------");

		JView viewName = null;

		String workDiv = StringUtil.nvl(request.getParameter("work_div"), "");
		log.debug("workDiv : {} ", workDiv);

		switch (workDiv) {
		case "sendMail":
			viewName = sendMail(request, response);
			break;
		default:
			log.debug("작업구분을 확인 하세요. workDiv : {} ", workDiv);
			break;
		}

		return viewName;
	}

}
