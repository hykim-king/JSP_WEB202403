package com.pcwk.ehr.code;

import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;

public class CodeMain implements PLog {

	CodeDao  dao;
	CodeVO   codeVO;
	
	public CodeMain() {
		dao = new CodeDao();
		codeVO=new CodeVO();
	}
	
	public void doRetrieve() {
		log.debug(" doRetrieve() ");
		codeVO.setMsgCode("PAGE_SIZE");
		List<CodeVO> list = dao.doRetrieve(codeVO);
		
		for(CodeVO vo :list) {
			log.debug(vo);
		}
	}
	
	public static void main(String[] args) {
		CodeMain main=new CodeMain();
		main.doRetrieve();

	}

}
