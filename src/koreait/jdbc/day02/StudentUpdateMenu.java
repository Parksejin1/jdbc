package koreait.jdbc.day02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentUpdateMenu {

	public static void main(String[] args) {
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		String user = "iclass";
		String password = "0419";
		
		System.out.println("::::::::::::::::::::::::: 학생 정보 수정 메뉴입니다 :::::::::::::::::::");
		System.out.println("<<지정된 학번에 대해 나이와 주소를 수정할 수 있습니다.>>");
		try (Connection conn = DriverManager.getConnection(url, user, password);) {
			
			updateStudent(conn);
			
		} catch (Exception e) {
			System.out.println("오류 메시지 = " + e);
		}
	}

	//반복없이 한개만 수정
	protected static void updateStudent(Connection connection) throws Exception{
		
		String stuno,address,age;
		
		Scanner sc = new Scanner(System.in);
		String sql = "update TBL_STUDENT\r\n"
				+ "set age = ?,address = ?\r\n"
				+ "where stuno =?";
			
		System.out.println("학생번호 0000입력은 수정 취소입니다.");
		
		System.out.print("학번을 입력하세요 >>> ");
		stuno = sc.nextLine();
		
		if (stuno.equals("0000")) {
			System.out.println("학생 정보 수정(입력)을 취소합니다.");
			return;
		}
		

		System.out.print("나이을 입력하세요(10이상, 30세 이하) >>> ");
		age = sc.nextLine();
		
		System.out.print("주소를 입력하세요 >>> ");
		address = sc.nextLine();
		
	
		try(
		PreparedStatement ps = connection.prepareStatement(sql);){
		
		
		
		
		
		ps.setInt(1,Integer.parseInt(age));
		ps.setString(2, address);
		ps.setString(3, stuno);
	//	ps.execute();	insert,update,delete,select 모두 실행
		int count = ps.executeUpdate(); //*리턴값은 반영된 행의 개수 -> 새로운 메소드 써보기
		//insert,update,delete 를 실행
		System.out.println("학생 정보 수정"+count+"건이 완료되있습니다.");
		
		}catch (SQLException e) {
			System.out.println("수정중에 문제 가 생겼습니다"+e.getMessage());
		}
	
	
		
		
	}
}
