package com.pcwk.ehr.member;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.MessageVO;
import com.pcwk.ehr.cmn.PLog;

public class MemberServiceMain implements PLog {

	MemberService service;
	MemberDTO  member01;
	
	public MemberServiceMain() {
		service = new MemberService();
		member01= new MemberDTO();
		member01.setMemberId("ADMIN");
		member01.setPassword("4321");
	}
	
	public void doMemberSelect() {
		log.debug("doMemberSelect()  ");
		
		DTO dto =service.doMemberSelect(member01);
		if( dto instanceof MemberDTO) {
			MemberDTO outVO = (MemberDTO) dto;
			log.debug("성공 outVO  :{}",outVO);
		}else {
			MessageVO  message = (MessageVO) dto;
			log.debug("실패 message  :{}",message);
		}
	}
	
	public static void main(String[] args) {
		MemberServiceMain main=new MemberServiceMain();
		main.doMemberSelect();

	}

}
