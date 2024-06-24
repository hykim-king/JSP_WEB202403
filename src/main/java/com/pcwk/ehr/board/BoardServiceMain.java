package com.pcwk.ehr.board;

import com.pcwk.ehr.cmn.PLog;

public class BoardServiceMain implements PLog {

	BoardService service;
	BoardDTO board01;

	public BoardServiceMain() {
		service = new BoardService();

		board01 = new BoardDTO(900, "제목_900", "내용_900", 0, "admin_900", "사용않함", "admin_900", "사용않함");
	}

	public void selectOneReadCnt() {
		log.debug("selectOneReadCnt()");
		//본인 글은 조회 count를 증가 시키지 않음!
		board01.setRegId(board01.getRegId()+"000");
		
		BoardDTO dto = service.selectOneReadCnt(board01);
		if(null !=dto && dto.getFlag()==1) {
			log.debug("조회 및 readCount 처리 성공");
		}else {
			log.debug("조회 및 readCount 처리 실패");
		}
	}

	public static void main(String[] args) {
		BoardServiceMain mService = new BoardServiceMain();
		mService.selectOneReadCnt();
	}

}
