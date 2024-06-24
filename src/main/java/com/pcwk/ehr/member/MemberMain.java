package com.pcwk.ehr.member;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.cmn.PLog;

public class MemberMain implements PLog {

	MemberDao dao;
	MemberDTO member01;
	
	public MemberMain() {
		dao = new MemberDao();
		member01=new MemberDTO();
		member01.setMemberId("ADMIN");
		member01.setPassword("4321");
	}
	
	public void idCheck() {
		log.debug("idCheck()");
		int flag = dao.idCheck(member01);
		if(1==flag) {
			log.debug("idCheck 성공 :{}",flag);
		}else {
			log.debug("idCheck 실패 :{}",flag);
		}
		
	}
	
	public void idPasswordCheck() {
		log.debug("idPasswordCheck()");
		int flag = dao.idPasswordCheck(member01);
		if(1==flag) {
			log.debug("idPasswordCheck 성공 :{}",flag);
		}else {
			log.debug("idPasswordCheck 실패 :{}",flag);
		}		
	}
	
	public void doSelectOne() {
		log.debug("doSelectOne()");
		MemberDTO outVO=dao.doSelectOne(member01);
		if(null != outVO) {
			log.debug("doSelectOne 성공 :{}",outVO);
		}else {
			log.debug("doSelectOne 실패 :{}",outVO);
		}
		
		
	}
	
	public int login() {
		//id Check > 0
		int result = 0;
		int flag = dao.idCheck(member01);
		
		if(flag == 0) {
			result = 10;//ID 불일치
			return result;
		}
		
		//id/pass Check >0
		flag = dao.idPasswordCheck(member01);
		if(flag == 0) {
			result = 20;//비번 불일치
			return result;
		}
		
		
		return 30;//로그인
		
	}
	
	public DTO doMemberSelect() {
		int result = login();
		MemberDTO outVO = null;
		if( 30 == result ) {
			outVO = dao.doSelectOne(member01);
		}
		
		return outVO;
	}
	
	public static void main(String[] args) {
		MemberMain main=new MemberMain();
		//main.idCheck();
		//main.idPasswordCheck();
		//main.doSelectOne();
		int result = main.login();
		log.debug("result  :{}",result);
		
		MemberDTO member = (MemberDTO) main.doMemberSelect();
		log.debug("member  :{}",member);
	}

}
