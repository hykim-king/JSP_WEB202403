package com.pcwk.ehr.jdbc;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleJdbcMain {
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
	
	public static void main(String[] args) {
//		1. DriverManager로 데이터 베이스와 연결을 생성
//		2. Connection: 데이터 베이스와 연결 id/pass 인터페이스
//		3. Statement/PreparedStatement: SQL문을 실행 인터페이스
//		4. ResultSet: SQL문의 결과를 저장하고 조회하는 인터페이스
//		5. 연결종료
		OracleJdbcMain m=new OracleJdbcMain();
		m.getConnection();
		
		
		
	}

}
