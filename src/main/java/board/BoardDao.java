package com.pcwk.ehr.board;

import java.sql.Connection;
import java.sql.DriverManager;
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

public class BoardDao implements WorkDiv<BoardDTO>, PLog {


	private ConnectionMaker connectionMaker;
	
	public BoardDao() {
		connectionMaker=new ConnectionMaker();
	}


	
	/**
	 * 조회 count증가
	 * @param param
	 * @return 성공(1)/실패(0)
	 */
	public int updateReadCnt(BoardDTO param) {
//		1. DriverManager로 데이터 베이스와 연결을 생성
//		2. Connection: 데이터 베이스와 연결 id/pass 인터페이스
//		3. Statement/PreparedStatement: SQL문을 실행 인터페이스
//		4. 연결종료		
		
		int flag = 0;
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;//SQL+PARAM
		StringBuilder sb=new StringBuilder(200);
		sb.append(" UPDATE board                   \n");
		sb.append("    SET read_cnt = read_cnt +1  \n");
		sb.append("  WHERE seq = ?                 \n");
		sb.append("    AND NOT EXISTS              \n");
		sb.append("    (                           \n");
		sb.append("     SELECT 1 FROM board        \n");
		sb.append("     WHERE seq = ?              \n");
		sb.append("       AND reg_id = ?           \n");
		sb.append("    )                           \n");
		
		log.debug("1.sql:{}\n",sb.toString());
		log.debug("2.conn:{}",conn);
		log.debug("3.param:{}",param);		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt:{}",pstmt);
			
			//param설정
			pstmt.setInt(1, param.getSeq());
			pstmt.setInt(2, param.getSeq());
			pstmt.setString(3, param.getRegId());
			
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
	public List<BoardDTO> doRetrieve(DTO search) {
//		1. DriverManager로 데이터 베이스와 연결을 생성
//		2. Connection: 데이터 베이스와 연결 id/pass 인터페이스
//		3. Statement/PreparedStatement: SQL문을 실행 인터페이스
//		4. ResultSet: SQL문의 결과를 저장하고 조회하는 인터페이스
//		5. 연결종료
		SearchDTO  searchVO = (SearchDTO) search;
		
		StringBuilder sbWhere=new StringBuilder(200);
//        --WHERE title    LIKE :searchWord||'%'  "10"
//        --WHERE contents LIKE :searchWord||'%'  "20"
//        --WHERE mod_id   = searchWord           "30"
//        --WHERE title   LIKE :searchWord||'%'   "40"
//        --   OR contents LIKE :searchWord||'%'
//        --WHERE seq      = :searchWord		  "50"
		
		if(null !=searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("10")) {
			sbWhere.append("WHERE title LIKE  ? ||'%' \n");
		}else if(null !=searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("20")) {
			sbWhere.append("WHERE contents LIKE ?||'%' \n");
		}else if(null !=searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("30")) {
			sbWhere.append("WHERE mod_id = ? \n");
		}else if(null !=searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("40")) {
			sbWhere.append("WHERE title    LIKE ? ||'%' \n");
			sbWhere.append("   OR contents LIKE ? ||'%' \n");
		}else if(null !=searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("50")) {
			sbWhere.append("WHERE seq = ? \n");
		}
		
		
		
		
		List<BoardDTO> list=new ArrayList<BoardDTO>();
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;//SQL+PARAM
		ResultSet         rs    = null;//SQL문의 결과		
		StringBuilder sb=new StringBuilder(1000);
		sb.append("  SELECT A.*,B.*                                                                                   \n");
		sb.append("  FROM (                                                                                           \n");
		sb.append("      SELECT tt1.rnum AS num,                                                                      \n");
		sb.append("             tt1.seq,                                                                              \n");
		sb.append("             tt1.title,                                                                            \n");
		sb.append("             tt1.read_cnt,                                                                         \n");
		sb.append("             tt1.mod_id,                                                                           \n");
		sb.append("             DECODE(TO_CHAR(tt1.mod_dt,'YYYY/MM/DD'), TO_CHAR(SYSDATE,'YYYY/MM/DD')                \n");
		sb.append("                                                    , TO_CHAR(tt1.mod_dt,'HH24:MI')                \n");
		sb.append("                                                    , TO_CHAR(tt1.mod_dt,'YYYY/MM/DD')) mod_dt     \n");
		sb.append("      FROM (                                                                                       \n");
		sb.append("          SELECT ROWNUM AS rnum, T1.*                                                              \n");
		sb.append("          FROM (                                                                                   \n");
		sb.append("                  SELECT *                                                                         \n");
		sb.append("                    FROM board                                                                     \n");
		sb.append("                   --WHERE조건                                                                      \n");
		//--where---------------------------------------------------------------------------------------------------------
		sb.append(sbWhere.toString());
		//--where---------------------------------------------------------------------------------------------------------		
		sb.append("                   ORDER BY mod_dt DESC                                                            \n");
		sb.append("                                                                                                   \n");
		sb.append("          )T1                                                                                      \n");
		//sb.append("          WHERE ROWNUM <=( :pageSize * (:pageNo - 1)+:pageSize)                                  \n");
		sb.append("          WHERE ROWNUM <=( ? * (? - 1)+ ?)                                                         \n");
		sb.append("      )TT1                                                                                         \n");
		//sb.append("      WHERE rnum >=( :pageSize * (:pageNo - 1) + 1)                                              \n");
		sb.append("      WHERE rnum >=(? * ( ? - 1) +1  )                                                              \n");
		sb.append("      --WHERE rnum BETWEEN 1 AND 10                                                                \n");
		sb.append("      )A,(                                                                                         \n");
		sb.append("      SELECT COUNT(*) totalCnt                                                                     \n");
		sb.append("        FROM board                                                                                 \n");
		sb.append("      --WHERE조건                                                                                   \n");
		sb.append("                                                                                                   \n");
		//--where---------------------------------------------------------------------------------------------------------
		sb.append(sbWhere.toString());
		//--where---------------------------------------------------------------------------------------------------------		
		sb.append("      )B                                                                                           \n");		
		
		log.debug("1.sql: {} \n", sb.toString());
		log.debug("2.conn: {}",   conn);
		log.debug("3.search: {} ", search);	
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt: {} ", pstmt);	
			
			//param설정
			//paging
			if(null !=searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("10")) {
				log.debug("4.1 searchDiv: {} ", searchVO.getSearchDiv());	
				//검색어
				pstmt.setString(1, searchVO.getSearchWord());
				//ROWNUM
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				//rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());	
				
				//검색어
				pstmt.setString(7, searchVO.getSearchWord());
				
			}else if(null !=searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("20")) {
				log.debug("4.1 searchDiv: {} ", searchVO.getSearchDiv());	
				//검색어
				pstmt.setString(1, searchVO.getSearchWord());
				//ROWNUM
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				//rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());	
				
				//검색어
				pstmt.setString(7, searchVO.getSearchWord());
			//mod_id	
			}else if(null !=searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("30")) {
				log.debug("4.1 searchDiv: {} ", searchVO.getSearchDiv());	
				//검색어
				pstmt.setString(1, searchVO.getSearchWord());
				//ROWNUM
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				//rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());	
				
				//검색어
				pstmt.setString(7, searchVO.getSearchWord());
				
			//제목+내용	
			}else if(null !=searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("40")) {
				log.debug("4.1 searchDiv: {} ", searchVO.getSearchDiv());	
				//검색어
				pstmt.setString(1, searchVO.getSearchWord());
				pstmt.setString(2, searchVO.getSearchWord());
				
				//ROWNUM
				pstmt.setInt(3, searchVO.getPageSize());
				pstmt.setInt(4, searchVO.getPageNo());
				pstmt.setInt(5, searchVO.getPageSize());
				
				//rnum
				pstmt.setInt(6, searchVO.getPageSize());
				pstmt.setInt(7, searchVO.getPageNo());	
				
				//검색어
				pstmt.setString(8, searchVO.getSearchWord());				
				pstmt.setString(9, searchVO.getSearchWord());
			
			//seq
			}else if(null !=searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("50")) {
				log.debug("4.1 searchDiv: {} ", searchVO.getSearchDiv());	
				//검색어
				pstmt.setString(1, searchVO.getSearchWord());
				//ROWNUM
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				//rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());	
				
				//검색어
				pstmt.setString(7, searchVO.getSearchWord());				
			//전체	
			}else {

				//ROWNUM
				pstmt.setInt(1, searchVO.getPageSize());
				pstmt.setInt(2, searchVO.getPageNo());
				pstmt.setInt(3, searchVO.getPageSize());
				
				//rnum
				pstmt.setInt(4, searchVO.getPageSize());
				pstmt.setInt(5, searchVO.getPageNo());				
				
			}
			

			
			
			//SELECT실행
			rs = pstmt.executeQuery();
			log.debug("5.rs:{}",rs);
			while(rs.next()) {
				//건수 최대값만 정해짐 
				BoardDTO outVO=new BoardDTO();
				
				outVO.setNum(rs.getInt("num"));
				outVO.setSeq(rs.getInt("seq"));
				outVO.setTitle(rs.getString("title"));
				outVO.setReadCnt(rs.getInt("read_cnt"));
				outVO.setModId(rs.getString("mod_id"));
				outVO.setModDt(rs.getString("mod_dt"));
				
				outVO.setTotalCnt(rs.getInt("totalCnt"));
				
				
				list.add(outVO);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(conn, pstmt, rs);
			log.debug("6.finally conn:{} pstmt:{} rs:{}",conn,pstmt,rs);
		}
		
		
		return list;
	}

	@Override
	public int doSave(BoardDTO param) {
//		1. DriverManager로 데이터 베이스와 연결을 생성
//		2. Connection: 데이터 베이스와 연결 id/pass 인터페이스
//		3. Statement/PreparedStatement: SQL문을 실행 인터페이스
//		4. 연결종료		
		int flag = 0;
		
		Connection conn = connectionMaker.getConnection();
		
		PreparedStatement pstmt = null;//SQL+PARAM
		
		StringBuilder sb=new StringBuilder(500);
		sb.append(" INSERT INTO board (  \n");
		sb.append("     seq,             \n");
		sb.append("     title,           \n");
		sb.append("     contents,        \n");
		sb.append("     read_cnt,        \n");
		sb.append("     reg_id,          \n");
		sb.append("     reg_dt,          \n");
		sb.append("     mod_id,          \n");
		sb.append("     mod_dt           \n");
		sb.append(" ) VALUES (           \n");
		sb.append("     board_seq.NEXTVAL,  \n");
		sb.append("     ?,               \n");
		sb.append("     ?,               \n");
		sb.append("     0,               \n");
		sb.append("     ?,               \n");
		sb.append("     SYSDATE,         \n");
		sb.append("     ?,               \n");
		sb.append("     SYSDATE           \n");
		sb.append(" )                    \n");		
		
		log.debug("1.sql:{}",sb.toString());
		log.debug("2.conn:{}",conn);
		log.debug("3.param:{}",param);
		
		try {
			conn.setAutoCommit(true);
			
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt:{}",pstmt);
			
			//param설정
			//pstmt.setInt(1, param.getSeq());
			pstmt.setString(1, param.getTitle());
			pstmt.setString(2, param.getContents());
			pstmt.setString(3, param.getRegId());
			pstmt.setString(4, param.getRegId());
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
	public int doUpdate(BoardDTO param) {
//		1. DriverManager로 데이터 베이스와 연결을 생성
//		2. Connection: 데이터 베이스와 연결 id/pass 인터페이스
//		3. Statement/PreparedStatement: SQL문을 실행 인터페이스
//		4. 연결종료
		
		int flag = 0;
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;//SQL+PARAM
		StringBuilder sb=new StringBuilder(200);
		sb.append(" UPDATE board           \n");
		sb.append(" SET                    \n");
		sb.append("     title    = ?,      \n");
		sb.append("     contents = ?,      \n");
		sb.append("     mod_id   = ?,      \n");
		sb.append("     mod_dt   = SYSDATE \n");
		sb.append(" WHERE                  \n");
		sb.append("         seq = ?        \n");		
		log.debug("1.sql:{}\n",sb.toString());
		log.debug("2.conn:{}",conn);
		log.debug("3.param:{}",param);
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt:{}",pstmt);
		
			pstmt.setString(1, param.getTitle());
			pstmt.setString(2, param.getContents());
			pstmt.setString(3, param.getModId());
			pstmt.setInt(4, param.getSeq());
			
			
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
	public int doDelete(BoardDTO param) {
//		1. DriverManager로 데이터 베이스와 연결을 생성
//		2. Connection: 데이터 베이스와 연결 id/pass 인터페이스
//		3. Statement/PreparedStatement: SQL문을 실행 인터페이스
//		4. 연결종료	
		int flag = 0;
		Connection conn = connectionMaker.getConnection();
		
		PreparedStatement pstmt = null;//SQL+PARAM
		StringBuilder sb=new StringBuilder(150);
		sb.append(" DELETE FROM board \n"); 
		sb.append(" WHERE seq = ?     \n");
		
		log.debug("1.sql:{}",sb.toString());		
		log.debug("2.conn:{}",conn);
		log.debug("3.param:{}",param);
		
		try {
			pstmt=conn.prepareStatement(sb.toString());
			log.debug("4.pstmt:{}",pstmt);
			//param설정
			pstmt.setInt(1, param.getSeq());
			
			//DML수행
			flag =pstmt.executeUpdate();
			
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
	public BoardDTO doSelectOne(BoardDTO param) {
//		1. DriverManager로 데이터 베이스와 연결을 생성
//		2. Connection: 데이터 베이스와 연결 id/pass 인터페이스
//		3. Statement/PreparedStatement: SQL문을 실행 인터페이스
//		4. ResultSet: SQL문의 결과를 저장하고 조회하는 인터페이스
//		5. 연결종료
		BoardDTO outVO = null;//단건조회 결과
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null;//SQL+PARAM
		ResultSet         rs    = null;//SQL문의 결과		
		StringBuilder sb=new StringBuilder(500);
		sb.append(" SELECT                                                 \n"); 
		sb.append("     seq,                                               \n");
		sb.append("     title,                                             \n");
		sb.append("     contents,                                          \n");
		sb.append("     read_cnt,                                          \n");
		sb.append("     reg_id,                                            \n");
		sb.append("     TO_CHAR(reg_dt,'YYYY/MM/DD HH24:MI:SS') reg_dt ,   \n");
		sb.append("     mod_id,                                            \n");
		sb.append("     TO_CHAR(mod_dt,'YYYY/MM/DD HH24:MI:SS') mod_dt     \n");
		sb.append(" FROM                                                   \n");
		sb.append("     board                                              \n");
		sb.append(" WHERE  seq = ?                                         \n");		
		
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
				outVO = new BoardDTO();
				
				outVO.setSeq(rs.getInt("seq"));
				outVO.setTitle(rs.getString("title"));
				outVO.setContents(rs.getString("contents"));
				outVO.setReadCnt(rs.getInt("read_cnt"));
				outVO.setRegId(rs.getString("reg_id"));
				outVO.setRegDt(rs.getString("reg_dt"));
				
				outVO.setModId(rs.getString("mod_id"));
				outVO.setModDt(rs.getString("mod_dt"));
				
				log.debug("6.outVO: {}",outVO);
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

















