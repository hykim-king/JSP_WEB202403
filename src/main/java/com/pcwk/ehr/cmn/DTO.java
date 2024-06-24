package com.pcwk.ehr.cmn;

public class DTO {

	private int flag ;//DML작성 상태값 
	private int num  ;//글 번호
	private int totalCnt  ;//총 글수	
	
	private int bottomCount;//바닥 글수:20240617 추가
	private int pageNo  ;//페이지 번호:1,2,3..
	private int pageSize;//페이지 사이즈:10,20,50,100,200..
	
	
	public DTO() {
		pageNo      = 1;
		bottomCount = 10;
		pageSize    = 10;
	}


	public int getFlag() {
		return flag;
	}


	public void setFlag(int flag) {
		this.flag = flag;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public int getTotalCnt() {
		return totalCnt;
	}


	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}


	public int getBottomCount() {
		return bottomCount;
	}


	public void setBottomCount(int bottomCount) {
		this.bottomCount = bottomCount;
	}


	public int getPageNo() {
		return pageNo;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	@Override
	public String toString() {
		return "DTO [flag=" + flag + ", num=" + num + ", totalCnt=" + totalCnt + ", bottomCount=" + bottomCount
				+ ", pageNo=" + pageNo + ", pageSize=" + pageSize + "]";
	}

}
