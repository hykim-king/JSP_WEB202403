package com.pcwk.ehr.member;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.cmn.PLog;

public class MemberService implements PLog {

	private MemberDao dao;
	
	
	public MemberService() {	
		dao = new MemberDao();
	}
	
	/**
	 * idCheck
	 * @param param
	 * @return
	 */
	public int idCheck(MemberDTO param) {
		return dao.idCheck(param);
	}
	
	/**
	 * 10 : id불일치
	 * 20 : 비번 불일치
	 * 30 : id/비번 일치
	 * @param param
	 * @return 10/20/30
	 */
	public int loginStatus(MemberDTO param) {
		//id Check > 0
		int result = 0;
		int flag = dao.idCheck(param);
		
		if(flag == 0) {
			result = 10;//ID 불일치
			return result;
		}
		
		//id/pass Check >0
		flag = dao.idPasswordCheck(param);
		if(flag == 0) {
			result = 20;//비번 불일치
			return result;
		}
		
		return 30;//로그인		
	}
	
	public DTO doMemberSelect(MemberDTO param) {
		int result = loginStatus(param);
		
		if(30 ==result ) {
			MemberDTO  outVO = dao.doSelectOne(param);
			return outVO;
		}else {
			MessageVO  message=new MessageVO();
			message.setMessageId(String.valueOf(result));
			
			String messageStr = "";
			if(10 == result) {
				messageStr = "ID를 확인 하세요.";
			}else if(20 == result) {
				messageStr = "비밀번호를 확인 하세요.";
			}
			
			message.setMsgContents(messageStr);
			log.debug("message  :{}",message);
			return message;
		}
	}
	
	
	
}
