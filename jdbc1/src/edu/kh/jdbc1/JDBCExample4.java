package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample4 {
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
			
			conn = DriverManager.getConnection(type + ip + port + sid , user, pw);
			
			System.out.println("<70년대생 직원만 조회>");
			
			String sql = "SELECT EMP_ID, EMP_NAME, EMP_NO FROM EMPLOYEE WHERE EMP_NO LIKE '7%'";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				String empId = rs.getString("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				int empNo = rs.getInt("EMP_NO");
				
				System.out.printf("<사번> %s  <이름> %s  <주민번호> %d \n" + empId, empName, empNo);
				
				
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
