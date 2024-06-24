package com.pcwk.ehr.answer;

import java.util.List;

import com.pcwk.ehr.board.BoardDTO;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.SearchDTO;

public class AnswerMain implements PLog {

	AnswerDTO answer01;
	AnswerDao dao;
	
	public AnswerMain() {
		dao = new AnswerDao();
		answer01 = new AnswerDTO(900,"답변_900",900,"scott900","사용않함","scott900","사용않함");
		
		log.debug("AnswerMain 생성자");
		log.debug("dao:{}",dao);
		log.debug("answer01:{}",answer01);
	}
	
	public void doSave() {
		log.debug("doSave()");
		int flag = dao.doSave(answer01);
		if(1==flag) {
			log.debug("성공 :{}",flag);
		}else {
			log.debug("실패 :{}",flag);
		}		
	}
	
	public void doDelete() {
		log.debug("doDelete()");
		int flag = dao.doDelete(answer01);
		if(1==flag) {
			log.debug("성공 :{}",flag);
		}else {
			log.debug("실패 :{}",flag);
		}		
	}
	
	public void doSelectOne() {
		log.debug("doSelectOne()");
		
		AnswerDTO outVO = dao.doSelectOne(answer01);
		
		if(null != outVO) {
			log.debug("성공 :{}",outVO);
		}else {
			log.debug("실패 :{}",outVO);
		}
		
		
	}
	
	public void addAndGet() {
		//1.삭제: 성공 실패 체크 않함
		//2.등록:
		//3.조회:
		log.debug(" addAndGet() ");		
		
		//1.
		dao.doDelete(answer01);
		
		//2.
		int flag = dao.doSave(answer01);
		if(1==flag) {
			log.debug("등록 성공 :{}",flag);
		}else {
			log.debug("등록 실패 :{}",flag);
			return;
		}		
		
		//3
		//3.
		AnswerDTO outVO = dao.doSelectOne(answer01);
		
		
		boolean isSame = isSameData(outVO, answer01);
		
		if(false == isSame) {
			log.debug("-실패--");			
		}else {
			log.debug("----------------");
			log.debug("-성공--");
			log.debug("----------------");
		}		
	}
	
	public boolean isSameData(AnswerDTO outVO, AnswerDTO board01) {
		//board01, outVO 비교
		if(outVO.getSeq() != board01.getSeq() ) {
			log.debug("실패 seq :{},{}",outVO.getSeq(),board01.getSeq());
			return false;
		}
		
		//내용
		if(!outVO.getContents().equals(board01.getContents()) ) {
			log.debug("실패 contents :{},{}",outVO.getContents(),board01.getContents());
			return false;
		}		
		
		//등록자 ID
		if(!outVO.getRegId().equals(board01.getRegId()) ) {
			log.debug("실패 regId :{},{}",outVO.getRegId(),board01.getRegId());
			return false;
		}		
		
		return true;
	}	
	
	public void doUpdate() {
		log.debug("doUpdate()");
		String updateStr = "_U";
		
		this.answer01.setContents(answer01.getContents()+updateStr);
		answer01.setModId(answer01.getModId()+updateStr);
		
	    int flag =dao.doUpdate(answer01);
	    if(1==flag) {
	    	log.debug("수정 성공:{}",flag);
	    }else {
	    	log.debug("수정 실패:{}",flag);
	    }
		
	}
	
	public void doRetrieve() {
		log.debug("doRetrieve()");
		
		SearchDTO  searchVO=new SearchDTO();
		
		List<AnswerDTO> list = dao.doRetrieve(searchVO);
		for(AnswerDTO vo :list) {
			log.debug(vo);
		}
	}
	
	public static void main(String[] args) {
		AnswerMain aM=new AnswerMain();
		//aM.doSave();
		//aM.doDelete();
		//aM.doSelectOne();
		//aM.addAndGet();
		//aM.doUpdate();
		aM.doRetrieve();
	}

}
