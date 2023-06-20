package koreait.jdbc.day02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentDeleteMenu {

	public static void main(String[] args) {
String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		String user = "iclass";
		String password = "0419";
		
		System.out.println("::::::::::::::::::::::::: 학생 정보 수정 메뉴입니다 :::::::::::::::::::");
		System.out.println("<<지정된 학번에 대해 나이와 주소를 삭제할 수 있습니다.>>");
        try (Connection conn = DriverManager.getConnection(url, user, password);) {
			
	  deleteStudent(conn);
			
		} catch (Exception e) {
			System.out.println("오류 메시지 = " + e);
		}
	}

	
	
	protected static void  deleteStudent(Connection connection) throws Exception{
	
      String stuno;
		
		Scanner sc = new Scanner(System.in);
		String sql = "delete TBL_STUDENT\r\n"
				+ "where stuno =?";
			
		System.out.println("학생번호 0000입력은 삭제 취소입니다.");
		
		System.out.print("학번을 입력하세요 >>> ");
		stuno = sc.nextLine();
		
		if (stuno.equals("0000")) {
			System.out.println("학생 정보 삭제 취소합니다.");
			sc.close();
			return;
			
		}
		

		
	
		try(
		PreparedStatement ps = connection.prepareStatement(sql);){
		
		
		
		
		
		
		ps.setString(1, stuno);
		int count = ps.executeUpdate();	
		System.out.println("학생 정보 삭제"+count+"건이 완료되있습니다.");		
		}catch (SQLException e) {
			System.out.println("수정중에 문제 가 생겼습니다"+e.getMessage());
		}
	
	
		
		
	}
		
		
		
		
	
	}

