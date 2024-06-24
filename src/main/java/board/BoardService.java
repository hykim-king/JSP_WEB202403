package com.pcwk.ehr.board;

import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;

public class BoardService implements PLog {

	private BoardDao dao;

	public BoardService() {
		dao = new BoardDao();
	}

	/**
	 * 목록 조회
	 * @param search
	 * @return List<BoardDTO>
	 */
	public List<BoardDTO> doRetrieve(DTO search) {
		return dao.doRetrieve(search);
	}
	/**
	 * 저장
	 * @param param
	 * @return 성공(1)/실패(0)
	 */
	public int doSave(BoardDTO param) {
		return dao.doSave(param);
	}
	
	/**
	 * 수정
	 * @param param
	 * @return 성공(1)/실패(0)
	 */
	public int doUpdate(BoardDTO param) {
		return dao.doUpdate(param);
	}
	
	/**
	 * 삭제 
	 * @param param
	 * @return 성공(1)/실패(0)
	 */
	public int doDelete(BoardDTO param) {
		return dao.doDelete(param);
	}
	
	
	// doSelectOne + updateReadCnt
	public BoardDTO selectOneReadCnt(BoardDTO param) {
		BoardDTO outVO = new BoardDTO();
		
		int flag = dao.updateReadCnt(param);
		log.debug("flag:{}", flag);

		outVO.setFlag(flag);
		
		// doSelectOne
		outVO = dao.doSelectOne(param);


		return outVO;
	}

}
