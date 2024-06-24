package com.pcwk.ehr.answer;

import com.pcwk.ehr.cmn.DTO;

public class AnswerDTO extends DTO {
	
	private int    seq 	    ;//순번
	private String contents	;//내용
	private int    boardSeq	;//게시글순번
	private String regId	;//등록자
	private String regDt	;//등록일
	private String modId	;//수정자
	private String modDt	;//수정일
	
	public AnswerDTO() {	}

	public AnswerDTO(int seq, String contents, int boardSeq, String regId, String regDt, String modId, String modDt) {
		super();
		this.seq = seq;
		this.contents = contents;
		this.boardSeq = boardSeq;
		this.regId = regId;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getModId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	@Override
	public String toString() {
		return "AnswerDTO [seq=" + seq + ", contents=" + contents + ", boardSeq=" + boardSeq + ", regId=" + regId
				+ ", regDt=" + regDt + ", modId=" + modId + ", modDt=" + modDt + ", toString()=" + super.toString()
				+ "]";
	}

	
	
	
	
	
	
}
