package com.pcwk.ehr.emp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.pcwk.ehr.cmn.DBUtil;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.WorkDiv;

public class DeptDao implements WorkDiv<DeptDTO> {
	final static String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	// jdbc:oracle:thin:@IP:PORT:전역DB명칭(SID)
	final static String DB_URL    = "jdbc:oracle:thin:@192.168.0.30:1521:xe";
	final static String DB_USER   = "scott";
	final static String DB_PASSWORD   = "pcwk";
	
	public Connection getConnection() {
		Connection conn = null;
		
		System.out.println("1");
		try {
			System.out.println("2");
			Class.forName(DB_DRIVER);
			System.out.println("3");
			
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("4 conn:"+conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	@Override
	public List<DeptDTO> doRetrieve(DTO search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doSave(DeptDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doUpdate(DeptDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doDelete(DeptDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DeptDTO doSelectOne(DeptDTO param) {
//		1. DriverManager로 데이터 베이스와 연결을 생성
//		2. Connection: 데이터 베이스와 연결 id/pass 인터페이스
//		3. Statement/PreparedStatement: SQL문을 실행 인터페이스
//		4. ResultSet: SQL문의 결과를 저장하고 조회하는 인터페이스
//		5. 연결종료
		
		DeptDTO outVO = null;//단건조회 결과
		Connection conn = getConnection();
		PreparedStatement pstmt = null;//SQL+PARAM
		ResultSet         rs    = null;//SQL문의 결과
		
		StringBuilder sb=new StringBuilder(300);
		sb.append(" SELECT                 \n");
		sb.append(" 	    deptno,        \n");
		sb.append(" 	    dname,         \n");
		sb.append(" 	    loc            \n");
		sb.append(" FROM dept              \n");
		sb.append(" WHERE deptno = ?       \n");
		
		log.debug("1.sql:\n"+sb.toString());
		log.debug("2.conn:"+conn);
		log.debug("3.param:"+param);
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4.pstmt:"+pstmt);
			
			//param설정
			pstmt.setInt(1, param.getDeptno());
			
			//SELECT실행
			rs = pstmt.executeQuery();
			log.debug("5.rs:"+rs);
			
			if(rs.next()) {
				outVO = new DeptDTO();
				
				outVO.setDeptno(rs.getInt("deptno"));
				outVO.setDname( rs.getString("dname"));
				outVO.setLoc(rs.getString("loc"));
				
				log.debug("6.outVO:"+outVO);
			}
			
		}catch(SQLException e) {
			log.debug("────────────────────────");
			log.debug("SQLException:"+e.getMessage());
			log.debug("────────────────────────");
		}finally {
			
			//DBUtil.close(conn, pstmt, rs);
			if(null !=rs) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			
			if(null !=pstmt) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}		
			
			if(null !=conn) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}				
		}
		
		
		return outVO;
	}

}





