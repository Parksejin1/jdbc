package koreait.jdbc.day02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//select 부터
public class OracleUtilty {

	public static Connection getConnection() {
		Connection conn = null;
String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		String user = "iclass";
		String password = "0419";
		try {
			conn = DriverManager.getConnection(url,user,password);
			
		}catch (SQLException e) {
			System.out.println("데이터 베이스 연결 및 사용에 문제가 생겼습니다. : "+e.getMessage());
		}
		return  conn;
	}
	
	public static void close(Connection conn) {
		try {
			if(conn !=null) {
				conn.close();
				System.out.println("연결종료");
			}else {
				System.out.println("Connection 이 null입니다.");
						}
			
		}catch(SQLException e) {
			System.out.println("데이터 베이스 연결 종료 오류 : "+e.getMessage());
		}
	} 
	
}
