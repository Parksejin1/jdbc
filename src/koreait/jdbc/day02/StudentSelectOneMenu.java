package koreait.jdbc.day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentSelectOneMenu {

	public static void main(String[] args) {
		Connection conn = OracleUtilty.getConnection();
		
		System.out.println("::::::::::::::학생을 학번으로 조회하는 메뉴:::::::::::");
		selectOneStuden(conn);
		
		OracleUtilty.close(conn);
		
		
		
	}

	private static void selectOneStuden(Connection conn) {
		Scanner sc = new Scanner(System.in);
		String stuno="2023002";
		String sql="select*from TBL_STUDENT where stuno =?";
		try(
		PreparedStatement ps = conn.prepareStatement(sql);
		){	
			ps.setString(1,stuno);
			//sql  실행하고 select 는 조회데이터를 결과로 받아 자바 변수에 저장해야함
//			 										->ResultSet 인터페이스 객체로 저장
			ResultSet rs = ps.executeQuery();
			System.out.println("rs 객체의 구현 클래스는 "+rs.getClass().getName());

		//rs.next() 데이터를 가져올 커서(위치)를 다음 행으로 이동합니다.
//			조회 결과 유무를 알려면 '제일먼저 실행해야 할 메소드-조회 결과 첫번째 행으로 이동' 입니다.
			System.out.println("조회결과가 있을까요?"+rs.next());
//			조회된 rs 에서 특정 컬럼값을 가져오기 할떄, 컬럼의 데이터 타입을 확인하고
//			getxxx 메소드 정하기 getxxxx 메소드의 인자는 컬럼의 인덱스 또는 컬럼 이름입니다.
			System.out.println("조회 결과 첫번쨰 컬럼의 값 : "+rs.getString(1));
			System.out.println("조회 결과 두번쨰 컬럼의 값 : "+rs.getString(2));
			System.out.println("조회 결과 세번쨰 컬럼의 값 : "+rs.getInt(3));
			System.out.println("조회 결과 네번쨰 컬럼의 값 : "+rs.getString(4));
			
			System.out.println("다음 조회결과가 있을까요?? "+rs.next());
	}catch(SQLException e) {
		System.out.println("데이터 조회에 문제가 생겼습니다. 상세내용 -"+e.getMessage());
		// 결과 집합을 모두 소모했음 -> 조회 결과가 없는데 rs.getxxx 메소드 실행할 때 예외의 메시지
		}
		sc.close();
	}

	
}
