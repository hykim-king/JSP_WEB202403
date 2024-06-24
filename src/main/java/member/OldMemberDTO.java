package com.pcwk.ehr.member;

import com.pcwk.ehr.cmn.DTO;

public class OldMemberDTO extends DTO {
	private String  userId     ;//회원id
	private String  name       ;//이름
	private String  password   ;//비번
	private String  email      ;//email
	private String  regId      ;//등록자id
	private String  regDt      ;//등록일
	
	
	public OldMemberDTO() {}


	public OldMemberDTO(String userId, String name, String password, String email, String regId, String regDt) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.email = email;
		this.regId = regId;
		this.regDt = regDt;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
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


	@Override
	public String toString() {
		return "MemberDTO [userId=" + userId + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", regId=" + regId + ", regDt=" + regDt + ", toString()=" + super.toString() + "]";
	}


	
	
}                              
