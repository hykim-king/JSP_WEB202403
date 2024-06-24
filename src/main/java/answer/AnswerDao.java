package com.pcwk.ehr.answer;

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
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.cmn.WorkDiv;

public class AnswerDao implements WorkDiv<AnswerDTO>, PLog {

	private ConnectionMaker connectionMaker;
	
	public AnswerDao() {
		connectionMaker=new ConnectionMaker();
	}
	
	@Override
	public List<AnswerDTO> doRetrieve(DTO search) {
//		1. DriverManager로 데이터 베이스와 연결을 생성
//		2. Connection: 데이터 베이스와 연결 id/pass 인터페이스
//		3. Statement/PreparedStatement: SQL문을 실행 인터페이스
//		4. ResultSet: SQL문의 결과를 저장하고 조회하는 인터페이스
//		5. 연결종료
		AnswerDTO   inVO = (AnswerDTO) search;
		
		if(inVO.getPageNo() == 0) {
			inVO.setPageNo(1);
		}
		
		
		if(inVO.getPageSize() == 0) {
			inVO.setPageSize(10);
		}		

		//return값
		List<AnswerDTO> list=new ArrayList<AnswerDTO>();
		Connection  conn=connectionMaker.getConnection();//DB연결
		PreparedStatement  pstmt = null;//SQL+PARAM
		ResultSet          rs    = null;//SQL결과
		
		StringBuilder sb=new StringBuilder(1000);
		sb.append(" SELECT A.*,B.*                                                                          \n");
		sb.append(" FROM (                                                                                  \n");
		sb.append("     SELECT tt2.rnum no,                                                                 \n");
		sb.append("            tt2.seq,                                                                     \n");
		sb.append("            tt2.contents,                                                                \n");
		sb.append("            tt2.board_seq,                                                               \n");
		sb.append("            tt2.mod_id,                                                                  \n");
		sb.append("            DECODE(TO_CHAR(tt2.mod_dt,'YYYY/MM/DD'),TO_CHAR(SYSDATE,'YYYY/MM/DD')        \n");
		sb.append("                                                   ,TO_CHAR(tt2.mod_dt,'HH24:MI')        \n");
		sb.append("                                                   ,TO_CHAR(tt2.mod_dt,'YYYY/MM/DD'))    \n");
		sb.append("            mod_dt                                                                       \n");
		sb.append("     FROM (                                                                              \n");
		sb.append("         SELECT ROWNUM rnum,t1.*                                                         \n");
		sb.append("         FROM (                                                                          \n");
		sb.append("                 SELECT t1.*                                                             \n");
		sb.append("                   FROM answer t1                                                        \n");
		sb.append("                  WHERE board_seq = ?                                                    \n");
		sb.append("                  ORDER BY mod_dt DESC                                                   \n");
		sb.append("         )t1                                                                             \n");
		//sb.append("         WHERE ROWNUM <=10                                                               \n");
		sb.append("          WHERE ROWNUM <=( ? * (? - 1)+ ?)                                               \n");
		sb.append("     )tt2                                                                                \n");
		//sb.append("     WHERE rnum>=1                                                                       \n");
		sb.append("      WHERE rnum >=(? * ( ? - 1) +1  )                                                   \n");
		sb.append(" )A CROSS JOIN (                                                                         \n");
		sb.append("                 SELECT count(*) totalCnt                                                \n");
		sb.append("                   FROM answer t1                                                        \n");
		sb.append("                  WHERE board_seq = ?                                                    \n");
		sb.append(" )B                                                                                      \n");
		
		log.debug("1.sql:\n {}",sb.toString());
		log.debug("2.conn: {}",conn);
		log.debug("3.inVO:{}",inVO);
		try {
			pstmt=conn.prepareStatement(sb.toString());
			log.debug("4.pstmt:{}",pstmt);
			//PARAM설정
			pstmt.setInt(1, inVO.getBoardSeq());
			
			pstmt.setInt(2, inVO.getPageSize());
			pstmt.setInt(3, inVO.getPageNo());
			pstmt.setInt(4, inVO.getPageSize());
			
			pstmt.setInt(5, inVO.getPageSize());
			pstmt.setInt(6, inVO.getPageNo());
			
			pstmt.setInt(7, inVO.getBoardSeq());
			//SELECT SQL실행
			rs = pstmt.executeQuery();
			log.debug("5.rs:{}",rs);
			
			while(rs.next()) {
				AnswerDTO  outVO=new AnswerDTO();
				
				outVO.setNum(rs.getInt("no"));
				outVO.setSeq(rs.getInt("seq"));
				outVO.setContents(rs.getString("contents"));
				outVO.setBoardSeq(rs.getInt("board_seq"));
				outVO.setModId(rs.getString("mod_id"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setTotalCnt(rs.getInt("totalCnt"));
				
				list.add(outVO);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, pstmt, rs);
			log.debug("6.finally ");
		}
		return list;
	}

	@Override
	public int doSave(AnswerDTO param) {
		int flag = 0;
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;//SQL+PARAM
		StringBuilder sb=new StringBuilder(500);
		sb.append("  INSERT INTO answer (  \n");
		sb.append("      seq,              \n");
		sb.append("      contents,         \n");
		sb.append("      board_seq,        \n");
		sb.append("      reg_id,           \n");
		sb.append("      reg_dt,           \n");
		sb.append("      mod_id,           \n");
		sb.append("      mod_dt            \n");
		sb.append("  ) VALUES (            \n");
		sb.append("      ANSWER_SEQ.NEXTVAL,                \n");
		sb.append("      ?,                \n");
		sb.append("      ?,                \n");
		sb.append("      ?,                \n");
		sb.append("      SYSDATE,          \n");
		sb.append("      ?,                \n");
		sb.append("      SYSDATE           \n");
		sb.append("  )                     \n");
		
		log.debug("1.sql:{}",sb.toString());
		log.debug("2.conn:{}",conn);
		log.debug("3.param:{}",param);		
		
		try {
			conn.setAutoCommit(true);
			pstmt = conn.prepareStatement(sb.toString());

			//param설정
			pstmt.setString(1, param.getContents());
			pstmt.setInt(2, param.getBoardSeq());
			pstmt.setString(3, param.getRegId());
			pstmt.setString(4, param.getRegId());	
			
			String sqlLog = DBUtil.formatQuery(sb.toString(), param.getSeq(),param.getContents(),param.getBoardSeq(),param.getRegId(),param.getRegId());
			
			log.debug("4.sqlLog:{}",sqlLog);		
			
			//DML
			flag = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, pstmt);
			log.debug("5.finally conn:{} pstmt:{}",conn,pstmt);
		}	
		
		log.debug("6.flag:{}",flag);
		return flag;
	}

	@Override
	public int doUpdate(AnswerDTO param) {
//		1. DriverManager로 데이터 베이스와 연결을 생성
//		2. Connection: 데이터 베이스와 연결 id/pass 인터페이스
//		3. Statement/PreparedStatement: SQL문을 실행 인터페이스
//      3.1 Query 수행, 수행 결과 return		
//		4. 연결종료
		
		int flag = 0; //DML반영 
		Connection conn = connectionMaker.getConnection();//DB연결
		PreparedStatement  pstmt = null;//SQL+PARAM
		StringBuilder sb=new StringBuilder();
		sb.append(" UPDATE answer                   \n");
		sb.append(" SET                             \n");
		sb.append("     contents = ?,               \n");
		sb.append("     mod_id = ?,                 \n");
		sb.append("     mod_dt = SYSDATE            \n");
		sb.append(" WHERE                           \n");
		sb.append("         seq = ?                 \n");		
		
		log.debug("1.sql:\n{}",sb.toString());
		log.debug("2.conn:{}",conn);
		log.debug("3.param:{}",param);
		
		try {
			pstmt=conn.prepareStatement(sb.toString());//PreparedStatement 객체 생성
			log.debug("4.pstmt:{}",pstmt);
			
			//contents,mod_id : 문자
			//seq : 숫자
			pstmt.setString(1, param.getContents());
			pstmt.setString(2, param.getModId());
			pstmt.setInt(3, param.getSeq());
			
			//DML
			flag =pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt);
			log.debug("5.finally conn:{}, pstmt:{}",conn,pstmt);
		}
		log.debug("6. flag : {}",flag);
		return flag;
	}

	@Override
	public int doDelete(AnswerDTO param) {
		int flag = 0;
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;//SQL+PARAM
		StringBuilder sb=new StringBuilder(200);	
		sb.append(" DELETE FROM answer \n");
		sb.append(" WHERE              \n");
		sb.append("         seq = ?    \n");
		
		log.debug("1.sql:{}\n",sb.toString());
		log.debug("2.conn:{}",conn);
		log.debug("3.param:{}",param);	
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			//param설정
			pstmt.setInt(1, param.getSeq());
			
			//DML
			flag = pstmt.executeUpdate();			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, pstmt);
			
			log.debug("5.finally conn:{} pstmt:{}",conn,pstmt);
		}	
		log.debug("6.flag:{}",flag);
		
		return flag;
	}

	@Override
	public AnswerDTO doSelectOne(AnswerDTO param) {
		AnswerDTO outVO = null;//단건조회 결과
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;//SQL+PARAM
		ResultSet         rs    = null;//SQL문의 결과
		StringBuilder sb=new StringBuilder(500);
		sb.append("  SELECT                                                 \n");
		sb.append("      seq,                                               \n");
		sb.append("      contents,                                          \n");
		sb.append("      board_seq,                                         \n");
		sb.append("      reg_id,                                            \n");
		sb.append("      TO_CHAR(reg_dt,'YYYY/MM/DD HH24:MI:SS') reg_dt,    \n");
		sb.append("      mod_id,                                            \n");
		sb.append("      TO_CHAR(mod_dt,'YYYY/MM/DD HH24:MI:SS') mod_dt     \n");
		sb.append("  FROM                                                   \n");
		sb.append("      answer                                             \n");
		sb.append("  WHERE                                                  \n");
		sb.append("      seq = ?                                            \n");		
		log.debug("1.sql: {} \n", sb.toString());
		log.debug("2.conn: {}",   conn);
		log.debug("3.param: {} ", param);	
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt: {} ", pstmt);
			
			//param설정
			pstmt.setInt(1, param.getSeq());
			
			//SELECT실행
			rs=pstmt.executeQuery();
			log.debug("5.rs:{}",rs);
			if(rs.next()) {
				outVO = new AnswerDTO();
				outVO.setSeq(rs.getInt("seq"));
				outVO.setContents(rs.getString("contents"));
				outVO.setBoardSeq(rs.getInt("board_seq"));
				outVO.setRegId(rs.getString("reg_id"));
				outVO.setRegDt(rs.getString("reg_dt"));
				outVO.setModId(rs.getString("mod_id"));
				outVO.setModDt(rs.getString("mod_dt"));				
			}
			log.debug("6.outVO: {}",outVO);
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
