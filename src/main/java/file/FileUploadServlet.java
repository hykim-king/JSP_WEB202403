package com.pcwk.ehr.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.pcwk.ehr.board.BoardController;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.StringUtil;
import com.pcwk.ehr.member.MemberController;
import com.pcwk.ehr.servlet.ConnectController;
import com.pcwk.ehr.servlet.CookieController;
import com.pcwk.ehr.servlet.LoginController;

/**
 * Servlet implementation class FileUploadServlet
 */
@WebServlet("/file/upload")
public class FileUploadServlet extends HttpServlet implements PLog {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파일저장 경로
				String savePath ="D:\\JAP_20249311\\upload";      
				//파일Size:50M
				int size = 1024*1024*50;
				//Encoding
				String encType = "UTF-8";
				
				//화면 Return
				List<FileDTO> fileList=new ArrayList<FileDTO>();
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter  out = response.getWriter();
					
				//upload dir이 없으면 생성.
				File dirUpload =new File(savePath);
				if(dirUpload.exists()==false)dirUpload.mkdir();
				
				//2020
				Date date=new Date();
				SimpleDateFormat sdfYear=new SimpleDateFormat("yyyy");
				String year = sdfYear.format(date);//2020
				
				String yearPath = savePath+File.separator +year;
				File yearDir=new File(yearPath);
				if(yearDir.exists()==false)yearDir.mkdir();
				
				//03
				SimpleDateFormat sdfMonth=new SimpleDateFormat("MM");
				String month = sdfMonth.format(date);//2020	
				String monthPath = yearPath+File.separator+month;
				File monthDir=new File(monthPath);
				if(monthDir.exists()==false)monthDir.mkdir();
				
				//savePath -> yearPath+monthPath
				log.debug("before savePath:"+savePath);
				savePath = monthPath;
				log.debug("after savePath:"+savePath);
				try {
					MultipartRequest  multi=new MultipartRequest(request
			                ,savePath
			                ,size
			                ,encType
			                ,new DefaultFileRenamePolicy()
			                );
					//FileID
					
					String uuid = StringUtil.getUUID();
					String yyyyMMdd = StringUtil.getDate("yyyyMMddHHmmss");
					
					String fileId = yyyyMMdd+uuid;
					log.debug("fileId:"+fileId+" length:"+fileId.length());
					
					Enumeration<String> files = multi.getFileNames();
					int fileSeq = 0;
				    while(files.hasMoreElements()){
				    	FileDTO fileVO=new FileDTO();
				    	String tmpFile = files.nextElement();
				    	if(null == multi.getOriginalFileName(tmpFile) 
				    			|| tmpFile.length()==0)continue;
				    	
				    	//seq
				    	fileSeq++;
				    	
				    	//원본파일
				    	String orgFileName = multi.getOriginalFileName(tmpFile);
				    	//저장파일
				    	String saveFileName = multi.getFilesystemName(tmpFile);
				    	//savePath:savePath+saveFileName
				    	String saveFilePath = savePath+File.separator+saveFileName;
				    	log.debug("------------------");
				    	log.debug("orgFileName-"+orgFileName);
				    	log.debug("saveFileName-"+saveFileName);
				    	log.debug("savePath+saveFileName-"+saveFilePath);
				    	log.debug("------------------");
				    	//파일사이즈
				    	File fileSize=new File(saveFilePath);
				    	long fileLongSize = fileSize.length();
				    	
				    	//확장자
				    	String ext = "";
				    	if(saveFileName.lastIndexOf(".")>0) {
				    		int dotIndex = saveFileName.lastIndexOf(".");
				    		ext = saveFileName.substring(dotIndex+1);
				    	}

				    	
				    	
				    	fileVO.setOrgNm(orgFileName);
				    	fileVO.setSaveNm(saveFileName);
				    	fileVO.setFileSize(fileLongSize);
				    	fileVO.setSavePath(savePath);
				    	fileVO.setExt(ext);
				    	fileVO.setSeq(fileSeq);
				    	fileVO.setFileId(fileId);
				    	//TODO:Session->등록자ID,수정자ID
				    	
				    	fileList.add(fileVO);
				    }
					
				    for(FileDTO vo:fileList) {
				    	log.debug("-vo-"+vo);
				    }
					
					
				}catch(Exception e) {
					log.debug("------------------");
					log.debug("-Exception-"+e.getMessage());
					log.debug("------------------");
				}
				

				//화면으로 전달
				request.setAttribute("fileList", fileList);

				String retPath ="/file/upload_result.jsp";
				RequestDispatcher dequestDispatcher=request.getRequestDispatcher(retPath);
				dequestDispatcher.forward(request, response);	

		}



}
