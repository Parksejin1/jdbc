package koreait.jdbc.day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentSelectOneMenuComplete {

	public static void main(String[] args) {
		Connection conn = OracleUtilty.getConnection();
		
		System.out.println("::::::::::::::학생을 학번으로 조회하는 메뉴:::::::::::");
		selectOneStuden(conn);
		
		OracleUtilty.close(conn);
		
		
		
	}

	private static void selectOneStuden(Connection conn) {
		Scanner sc = new Scanner(System.in);
		
		String sql="select*from TBL_STUDENT where stuno =?";
		String stuno= sc.nextLine();
		try(
		PreparedStatement ps = conn.prepareStatement(sql);
		){	
			ps.setString(1,stuno);
			ResultSet rs = ps.executeQuery();
			System.out.println("rs 객체의 구현 클래스는 "+rs.getClass().getName());

		
			
			if(rs.next()) {	//주의 : 테이블 컬럼의 구조를 알아야 인덱스를 정할 수 있습니다.
			System.out.println("학번: "+rs.getString(1));
			System.out.println("학번: "+rs.getString("stuno"));
			System.out.println("이름 : "+rs.getString(2));
			System.out.println("이름 : "+rs.getString("name"));
			System.out.println("나이 : "+rs.getInt(3));
			System.out.println("나이 : "+rs.getInt("age"));
			System.out.println("주소 : "+rs.getString(4));
			System.out.println("주소 : "+rs.getString("address"));
			} else
			System.out.println("<<조회된 결과가 없습니다>>");
	}catch(SQLException e) {
		System.out.println("데이터 조회에 문제가 생겼습니다. 상세내용 -"+e.getMessage());
		}
		sc.close();
	}

	
}

/*
 * 
 * 모든 학생 조회하는 StudentSelectAllmenu 클래스
 * 과목명을 입력하면 해당과목 조회하는 ScoreSelectWithsubject 클래스
 */




