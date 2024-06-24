package com.pcwk.ehr.answer;

import java.util.List;

import com.pcwk.ehr.board.BoardDTO;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;

public class AnswerService implements PLog {

	private AnswerDao dao;
	
	public AnswerService() {
		dao = new AnswerDao();
	}
	
	public List<AnswerDTO> doRetrieve(DTO search) {
		return dao.doRetrieve(search);
	}
	
	public int doSave(AnswerDTO param) {
		return dao.doSave(param);
	}
	
	
	public int doUpdate(AnswerDTO param) {
		return dao.doUpdate(param);
	}
	
	public int doDelete(AnswerDTO param) {
		return dao.doDelete(param);
	}
	
	public AnswerDTO doSelectOne(AnswerDTO param) {
		return dao.doSelectOne(param);
	}
	
}
