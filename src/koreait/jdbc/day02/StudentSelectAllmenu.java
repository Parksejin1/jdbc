package koreait.jdbc.day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentSelectAllmenu {

	public static void main(String[] args) {
		Connection conn = OracleUtilty.getConnection();
		
		System.out.println("::::::::::::::학생을 학번으로 조회하는 메뉴:::::::::::");
		selectOneStuden(conn);
		
		OracleUtilty.close(conn);
		

	}

	
		
		private static void selectOneStuden(Connection conn) {
											
				String sql="select*from TBL_STUDENT";				
				
				try(
				PreparedStatement ps = conn.prepareStatement(sql);
				){	
					
					
					ResultSet rs = ps.executeQuery();
					System.out.println("rs 객체의 구현 클래스는 "+rs.getClass().getName());

				
					
				   while(rs.next())       {	//주의 : 테이블 컬럼의 구조를 알아야 인덱스를 정할 수 있습니다.
					System.out.println("학번: "+rs.getString(1));
					
					System.out.println("이름 : "+rs.getString(2));
					
					System.out.println("나이 : "+rs.getInt(3));
					
	     			System.out.println("주소 : "+rs.getString(4));
					System.out.println("----------------------------------------");
					}
			}catch(SQLException e) {
				System.out.println("데이터 조회에 문제가 생겼습니다. 상세내용 -"+e.getMessage());
				}
				
			}
		
	




		
	
		
	}


