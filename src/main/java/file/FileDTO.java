package com.pcwk.ehr.file;

import com.pcwk.ehr.cmn.DTO;

public class FileDTO extends DTO {
	private	String	fileId	;/**	파일ID	*/
	private	int 	seq	    ;/**	순번	*/
	private	String	orgNm	;/**	원본파일명	*/
	private	String	saveNm	;/**	저장파일명	*/
	private	String	savePath;/**	저장경로	*/
	private	long	fileSize;/**	사이즈	*/
	private	String	ext	    ;/**	확장자	*/
	private	String	regId	;/**	등록자아이디	*/
	private	String	regDt	;/**	등록일	*/
	private	String	modId	;/**	아이디ID	*/
	private	String	modDt	;/**	수정일	*/
	
	public FileDTO() {	}

	public FileDTO(String fileId, int seq, String orgNm, String saveNm, String savePath, long fileSize, String ext,
			String regId, String regDt, String modId, String modDt) {
		super();
		this.fileId = fileId;
		this.seq = seq;
		this.orgNm = orgNm;
		this.saveNm = saveNm;
		this.savePath = savePath;
		this.fileSize = fileSize;
		this.ext = ext;
		this.regId = regId;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getOrgNm() {
		return orgNm;
	}

	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	public String getSaveNm() {
		return saveNm;
	}

	public void setSaveNm(String saveNm) {
		this.saveNm = saveNm;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
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
		return "FileDTO [fileId=" + fileId + ", seq=" + seq + ", orgNm=" + orgNm + ", saveNm=" + saveNm + ", savePath="
				+ savePath + ", fileSize=" + fileSize + ", ext=" + ext + ", regId=" + regId + ", regDt=" + regDt
				+ ", modId=" + modId + ", modDt=" + modDt + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
