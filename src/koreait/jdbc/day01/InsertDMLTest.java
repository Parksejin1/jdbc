package koreait.jdbc.day01;

//try catch finally 개선
// 제일 먼저 해야할것 : 데이터 베이스 연결  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

//학생 성적처리 프로그램 중에 새로운 학생을 등록(입력) 하는 기능을 만들어 봅시다.(테이블에 insert 실행)
public class InsertDMLTest {

	public static void main(String[] args) {
		
	
	
		
		String uri="jdbc:oracle:thin:@localhost:1521:xe";
		
		
		String driver="oracle.jdbc.driver.OracleDriver";
		String user="iclass";
		String password="0419";

		
		try (
				
		Connection conn = DriverManager.getConnection(uri,user,password);
				
				)
		{
			

		  System.out.println("연결 상태 = " +conn);
		  if(conn!=null)
			  System.out.println("오라클 데이터 베이스 연결 성공|| ");
		
		  
		  String sql="insert into TBL_STUDENT values('2023003','김땡이',17,'경기도')";
		  //PreparedStatement 객체를 생성하면서 실행할 sql을 설정합니다.
//		    PreparedStatement 겍체는 Connection 객체를 메소드로 만듭니다. 
//		  Connection 구현객체는 dbms 종류에따라 생성되고 PreparedStatement 객체도 그에 따라 구현객체가 결정됨.
		  
		  PreparedStatement Pstmt = conn.prepareStatement(sql);
		  System.out.println("Pstmt 객체의 구현 클래스 : "+Pstmt.getClass().getName());
		  
		 Pstmt.execute();
		 Pstmt.close();
		  
		
		}catch (Exception e) {	
			
			System.out.println("오류 메시지 - "+e.getMessage());
			e.printStackTrace();  //Exception 발생의 모든 원인을 cascade 형식으로 출력
		}
		}
		
		//conn.close();를 명시적으로 실행할 필요가 없습니다.
}


/*
 *  statement 인터페이스는 sql 쿼리 처리와 관련된 방법을 정의합니다.
 *  객체는 SQL 쿼리문을 데이터베이스에 전송합니다. Connection 객체를 통해서 만듭니다.
 *  
 *  PreparedStatement 는 Statement의 자식 인터페이스
 *  특징은 sql을 먼저 컴파일하고 sql 실행에 필요한 값은 
 */


 