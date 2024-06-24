package com.pcwk.ehr.emp;

import com.pcwk.ehr.cmn.DTO;

public class DeptDTO extends DTO {
	//ctrl+shift+y : 소문자
	
	private int    deptno  ;//부서번호
	private String dname   ;//부서명
	private String loc     ;//지역
	
	public DeptDTO() {}

	public DeptDTO(int deptno, String dname, String loc) {
		super();
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	@Override
	public String toString() {
		return "DeptDTO [deptno=" + deptno + ", dname=" + dname + ", loc=" + loc + "]";
	}
	
	
}
