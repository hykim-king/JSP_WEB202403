package com.pcwk.ehr.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.cmn.ConnectionMaker;
import com.pcwk.ehr.cmn.DBUtil;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.WorkDiv;

public class MemberDao implements WorkDiv<MemberDTO>, PLog {
    
	
	private ConnectionMaker connectionMaker;
	
	public MemberDao() {
		connectionMaker=new ConnectionMaker();
	}

	//id검사
	//ID비번검사
	//단건조회
	
	/**
	 * id조회
	 * @param param
	 * @return 1(존재)/0
	 */
	public int idCheck(MemberDTO param) {
		int flag = 0;
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;//SQL+PARAM
		ResultSet         rs    = null;//SQL문의 결과
		
		StringBuilder sb=new StringBuilder(500);
		sb.append(" SELECT COUNT(*) cnt  \n");
		sb.append("   FROM member        \n");
		sb.append("  WHERE member_id = ? \n");
		
		log.debug("1.sql: {} \n", sb.toString());
		log.debug("2.conn: {}",   conn);
		log.debug("3.param: {} ", param);			
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt: {} ", pstmt);	
			//param설정
			pstmt.setString(1, param.getMemberId());
			
			//SELECT실행
			rs=pstmt.executeQuery();
			log.debug("5.rs:{}",rs);
			
			if(rs.next()) {
				flag = rs.getInt("cnt");
				log.debug("6.flag: {}",flag);
			}
			
			
		}catch(SQLException e) {
			log.debug("────────────────────────");
			log.debug("SQLException:"+e.getMessage());
			log.debug("────────────────────────");
		}finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return flag;
	}
	
	/**
	 * id/비번 확인
	 * @param param
	 * @return 1(존재)/0
	 */
	public int idPasswordCheck(MemberDTO param) {
		int flag = 0;
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;//SQL+PARAM
		ResultSet         rs    = null;//SQL문의 결과
		StringBuilder sb=new StringBuilder(500);
		sb.append(" SELECT COUNT(*) cnt  \n");
		sb.append("   FROM member        \n");
		sb.append("  WHERE member_id = ? \n");
		sb.append("    AND password  = ? \n"); 		
		
		log.debug("1.sql: {} \n", sb.toString());
		log.debug("2.conn: {}",   conn);
		log.debug("3.param: {} ", param);
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt: {} ", pstmt);	
			
			//param설정
			pstmt.setString(1, param.getMemberId());
			pstmt.setString(2, param.getPassword());

			//SELECT실행
			rs=pstmt.executeQuery();
			log.debug("5.rs:{}",rs);			
			
			if(rs.next()) {
				flag = rs.getInt("cnt");
				log.debug("6.flag: {}",flag);
			}
			
			
		}catch(SQLException e) {
			log.debug("────────────────────────");
			log.debug("SQLException:"+e.getMessage());
			log.debug("────────────────────────");
		}finally {
			DBUtil.close(conn, pstmt, rs);
		}
		return flag;
	}	
	
	@Override
	public List<MemberDTO> doRetrieve(DTO search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doSave(MemberDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doUpdate(MemberDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(MemberDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberDTO doSelectOne(MemberDTO param) {
		MemberDTO outVO = null;
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;//SQL+PARAM
		ResultSet         rs    = null;//SQL문의 결과
		
		StringBuilder sb=new StringBuilder(500);
		sb.append(" SELECT                                             \n");
		sb.append("     member_id,                                     \n");
		sb.append("     name,                                          \n");
		sb.append("     email,                                         \n");
		sb.append("     password,                                      \n");
		sb.append("     grade,                                         \n");
		sb.append("     (SELECT det_code_nm                            \n");
		sb.append("       FROM code t2                                 \n");
		sb.append("      WHERE msg_code = 'GRADE'                      \n");
		sb.append("        AND t1.grade =t2.det_code ) det_code_nm,    \n");
		sb.append("     t1.reg_id,                                     \n");
		sb.append("     TO_CHAR(t1.reg_dt,'YYYY/MM/DD') reg_dt,        \n");
		sb.append("     t1.mod_id,                                     \n");
		sb.append("     TO_CHAR(t1.mod_dt,'YYYY/MM/DD')mod_dt          \n");
		sb.append(" FROM                                               \n");
		sb.append("     member t1                                      \n");
		sb.append(" WHERE member_id = ?                                \n");		
		
		log.debug("1.sql: {} \n", sb.toString());
		log.debug("2.conn: {}",   conn);
		log.debug("3.param: {} ", param);			
		try {				
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt: {} ", pstmt);	
			//param설정
			pstmt.setString(1, param.getMemberId());			
			
			//SELECT실행
			rs=pstmt.executeQuery();
			if(rs.next()) {
				outVO = new MemberDTO();
				
				outVO.setMemberId(rs.getString("member_id"));
				outVO.setName(rs.getString("name"));
				outVO.setEmail(rs.getString("email"));
				outVO.setPassword(rs.getString("password"));
				outVO.setGrade(rs.getString("grade"));
				outVO.setDetCodeNm(rs.getString("det_code_nm"));
				
				log.debug("5.outVO: {} ", outVO);	
			}
			
		}catch(SQLException e) {
			log.debug("────────────────────────");
			log.debug("SQLException:"+e.getMessage());
			log.debug("────────────────────────");
		}finally {
			DBUtil.close(conn, pstmt, rs);
		}		
		return outVO;
	}

}



















