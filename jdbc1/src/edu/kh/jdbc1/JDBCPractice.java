package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCPractice {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String type = "jdbc:oracle:thin:@";
			String ip = "localhost";
			String port = ":1521";
			String sid = ":XE"; 
			String user = "kh_jjy";
			String pw = "kh1234";
			
			System.out.println("<직원 사번, 이름, 부서명 출력>");
			
			conn = DriverManager.getConnection(type+ ip + port + sid , user, pw);
			
			String sql = "SELECT EMP_ID, EMP_NAME, DEPT_TITLE FROM EMPLOYEE JOIN DEPARTMENT ON(DEPT_CODE=DEPT_ID)";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empDp = rs.getString("DEPT_TITLE");
				
				System.out.printf("사번 : %s / 이름 : %s / 부서명 : %s \n" , empId, empName, empDp);
				
				
			}
			
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
			
		}finally {
			
			try {
				if(rs != null)rs.close();
				if(stmt != null)stmt.close();
				if(conn != null)conn.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
				
			}
		}
		
	
	}
	

}
