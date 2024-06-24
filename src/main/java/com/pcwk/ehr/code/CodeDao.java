package com.pcwk.ehr.code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pcwk.ehr.cmn.ConnectionMaker;
import com.pcwk.ehr.cmn.DBUtil;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.WorkDiv;

public class CodeDao implements WorkDiv<CodeVO>, PLog {

	private ConnectionMaker connectionMaker;
	
	public CodeDao() {
		connectionMaker = new ConnectionMaker();
	}
	
	
	@Override
	public List<CodeVO> doRetrieve(DTO search) {
//		1. DriverManager로 데이터 베이스와 연결을 생성
//		2. Connection: 데이터 베이스와 연결 id/pass 인터페이스
//		3. Statement/PreparedStatement: SQL문을 실행 인터페이스
//		4. ResultSet: SQL문의 결과를 저장하고 조회하는 인터페이스
//		5. 연결종료
		
		CodeVO inVO =  (CodeVO) search;
		List<CodeVO>  list=new ArrayList<CodeVO>();
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;//SQL+PARAM
		ResultSet         rs    = null;//SQL문의 결과	
		StringBuilder sb=new StringBuilder(300);
		sb.append(" SELECT                       \n");
		sb.append("     msg_code,                \n");
		sb.append("     det_code,                \n");
		sb.append("     mst_code_nm,             \n");
		sb.append("     det_code_nm,             \n");
		sb.append("     seq,                     \n");
		sb.append("     use_yn,                  \n");
		sb.append("     reg_id,                  \n");
		sb.append("     reg_dt,                  \n");
		sb.append("     mod_id,                  \n");
		sb.append("     mod_dt                   \n");
		sb.append(" FROM                         \n");
		sb.append("     code                     \n");
		sb.append(" WHERE msg_code = ?           \n"); 
		sb.append(" AND   use_yn   = '1'         \n");
		sb.append(" ORDER BY seq ASC             \n");
		
		log.debug("1.sql: {} \n", sb.toString());
		log.debug("2.conn: {}",   conn);
		log.debug("3.inVO: {} ", inVO);	
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt: {} ", pstmt);	
			
			//param설정
			pstmt.setString(1, inVO.getMsgCode());
			
			//SELECT실행
			rs = pstmt.executeQuery();
			log.debug("5.rs:{}",rs);
			while(rs.next()) {
				CodeVO codeVO=new CodeVO();
				codeVO.setMsgCode(rs.getString("msg_code")); 
				codeVO.setDetCode(rs.getString("det_code"));
				codeVO.setDetCodeNm(rs.getString("det_code_nm"));
				
				log.debug("5.CodeVO: {} ", codeVO);	
				list.add(codeVO);
			}
			
		}catch(SQLException e) {
			log.debug("4.SQLException: {} ", e.getMessage());	
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, pstmt, rs);
		}
		
		return list;
	}

	@Override
	public int doSave(CodeVO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doUpdate(CodeVO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(CodeVO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CodeVO doSelectOne(CodeVO param) {
		// TODO Auto-generated method stub
		return null;
	}

}
