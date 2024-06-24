package com.pcwk.ehr.cmn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBUtil implements PLog {


	
	
	
    public static String formatQuery(final String sql, Object... arguments) {
        if (arguments == null || arguments.length == 0) {
            return sql;
        }

        String query = sql;
        int count = 0;
        Pattern pattern = Pattern.compile("\\?");
        Matcher matcher = pattern.matcher(query);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            if (count < arguments.length) {
                matcher.appendReplacement(sb, "{" + count + "}");
                count++;
            } else {
                throw new IllegalArgumentException("Not enough arguments provided for placeholders in SQL query.");
            }
        }
        matcher.appendTail(sb);
        String formattedString = sb.toString();

        return java.text.MessageFormat.format(formattedString, arguments);
    }
    
	//close
	public static void close(Connection conn, PreparedStatement pstmt,ResultSet rs) {
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
	//close
	public static void close(Connection conn, PreparedStatement pstmt) {
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
}
