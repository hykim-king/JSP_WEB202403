package com.pcwk.ehr.board;

import java.util.List;

import com.google.gson.Gson;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.SearchDTO;

public class BoardMain implements PLog {

	BoardDao  dao;
	BoardDTO  board01;
	public BoardMain() {
		dao = new BoardDao();
		
		board01=new BoardDTO(900, "제목_900", "내용_900", 0, "admin_900"
				, "사용않함", "admin_900", "사용않함");
	}
	
	public void doSave() {
		log.debug(" doSave() ");
		int flag =dao.doSave(board01);
		if(1==flag) {
			log.debug("성공 :{}",flag);
		}else {
			log.debug("실패 :{}",flag);
		}
	}
	
	
	public void doDelete() {
		log.debug(" doDelete() ");
		int flag = dao.doDelete(board01);
		if(1==flag) {
			log.debug("삭제 성공 :{}",flag);
		}else {
			log.debug("삭제 실패 :{}",flag);
		}		
		
	}
	
	public void doSelectOne() {
		log.debug(" doSelectOne() ");
		BoardDTO outVO = dao.doSelectOne(board01);
		if(null != outVO) {
			log.debug("단건조회 성공 :{}",outVO);
		}else {
			log.debug("단건조회 실패 :{}",outVO);
		}
	}
	
	public void doUpdate() {
		log.debug(" doUpdate() ");
		String updateStr = "_U";
		
		board01.setTitle(board01.getTitle()        +updateStr);
		board01.setContents(board01.getContents()  +updateStr);
		board01.setModId(board01.getModId()        +updateStr);
		int flag = dao.doUpdate(board01);
		if(1==flag) {
			log.debug("수정 성공 :{}",flag);
		}else {
			log.debug("수정 실패 :{}",flag);
		}
	}
	
	public void doRetrieve() {
		log.debug(" doRetrieve() ");
		SearchDTO  searchVO=new SearchDTO();
		searchVO.setPageNo(1);
		searchVO.setPageSize(10);
		
		//검색구분
		searchVO.setSearchDiv("50");
		searchVO.setSearchWord("80");
		
		List<BoardDTO> list=dao.doRetrieve(searchVO);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		log.debug("json: {}",json);
		int i=0;
		for(BoardDTO vo :list) {
			//log.debug("i: {}, vo: {}",++i,vo);
		}
	}

	public void addAndGet() {
		//1.삭제: 성공 실패 체크 않함
		//2.등록:
		//3.조회:
		log.debug(" addAndGet() ");
		
		//1.
		dao.doDelete(board01);
		
		//2.
		int flag = dao.doSave(board01);
		if(1==flag) {
			log.debug("등록 성공 :{}",flag);
		}else {
			log.debug("등록 실패 :{}",flag);
			return;
		}
		
		//3.
		BoardDTO outVO = dao.doSelectOne(board01);
		
		
		boolean isSame = isSameData(outVO, board01);
		
		if(false == isSame) {
			log.debug("-실패--");			
		}else {
			log.debug("----------------");
			log.debug("-성공--");
			log.debug("----------------");
		}
		
	}
	
	public boolean isSameData(BoardDTO outVO, BoardDTO board01) {
		//board01, outVO 비교
		if(outVO.getSeq() != board01.getSeq() ) {
			log.debug("실패 seq :{},{}",outVO.getSeq(),board01.getSeq());
			return false;
		}
		
		//제목
		if(!outVO.getTitle().equals(board01.getTitle()) ) {
			log.debug("실패 title :{},{}",outVO.getTitle(),board01.getTitle());
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
	
	
	public static void main(String[] args) {
		BoardMain m=new BoardMain();
		m.doSave();
		//m.doDelete();
		//m.doSelectOne();
		//m.doUpdate();
		m.doRetrieve();
		//m.addAndGet();
	}

}
