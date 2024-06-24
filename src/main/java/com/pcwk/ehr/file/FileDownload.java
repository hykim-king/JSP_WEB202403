package com.pcwk.ehr.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownload
 */
@WebServlet("/file/download")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileDownload() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// String file_repo = "D:\\JAP_20249311\\upload";
		String fileName = (String) request.getParameter("fileName"); // 매개변수로 전송된 파일 이름을 읽어옴
		String orgName = (String) request.getParameter("orgName"); // 매개변수로 전송된 파일 이름을 읽어옴
		System.out.println("fileName=" + fileName);

		// 한글 인코딩 처리
		orgName = URLEncoder.encode(orgName, "UTF-8");

		System.out.println("orgName=" + orgName);
		OutputStream out = response.getOutputStream(); // response에서 OutputStream 객체를 가져옴
		String downFile = fileName;
		File f = new File(downFile);
		// 파일을 다운로드할 수 있게 설정
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + orgName);
		FileInputStream in = new FileInputStream(f);
		// 버퍼 기능을 이용해 파일에서 버퍼로 데이터를 읽어와 한꺼번에 출력함
		byte[] buffer = new byte[1024 * 8];
		while (true) {
			int count = in.read(buffer);
			if (count == -1)
				break;
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
	}

}
