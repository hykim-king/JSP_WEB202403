package com.pcwk.ehr.member;

import com.pcwk.ehr.cmn.DTO;

public class MemberDTO extends DTO {
	private String memberId  ;//회원ID
	private String name      ;//이름
	private String email     ;//이메일
	private String password  ;//비밀번호
	private String grade     ;//등급
	private String regId     ;//등록자
	private String regDt     ;//가입일
	private String modId     ;//수정자
	private String modDt     ;//수정일
	
	private String detCodeNm;//상세코드명:회원등급
	
	public MemberDTO() {}



	public MemberDTO(String memberId, String name, String email, String password, String grade, String regId,
			String regDt, String modId, String modDt, String detCodeNm) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.grade = grade;
		this.regId = regId;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
		this.detCodeNm = detCodeNm;
	}



	public String getDetCodeNm() {
		return detCodeNm;
	}



	public void setDetCodeNm(String detCodeNm) {
		this.detCodeNm = detCodeNm;
	}



	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
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
		return "MemberDTO [memberId=" + memberId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", grade=" + grade + ", regId=" + regId + ", regDt=" + regDt + ", modId=" + modId + ", modDt=" + modDt
				+ ", detCodeNm=" + detCodeNm + ", toString()=" + super.toString() + "]";
	}


	
	
	
	
}
