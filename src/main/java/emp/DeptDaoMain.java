package com.pcwk.ehr.emp;

import com.pcwk.ehr.cmn.PLog;

public class DeptDaoMain implements PLog {

	DeptDao dao;
	DeptDTO search;
	public DeptDaoMain() {
		dao =new DeptDao();
		
		search=new DeptDTO();
	}
	 
	public void doSelectOne() {
		String name ="good";
		log.debug("doSelectOne():{}",name);
		search.setDeptno(20);
		
		DeptDTO outVO = dao.doSelectOne(search);
		
		if(null !=outVO) {
			log.debug("단건 조회 성공:"+outVO);
		}else {
			log.debug("단건 조회 실패:"+outVO);
		}
		
		
	}
	
	public static void main(String[] args) {
		DeptDaoMain deptMain=new DeptDaoMain();
		deptMain.doSelectOne();
	}

}
