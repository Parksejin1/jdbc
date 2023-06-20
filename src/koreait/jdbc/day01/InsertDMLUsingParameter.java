package koreait.jdbc.day01;


// 제일 먼저 해야할것 : 데이터 베이스 연결  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

//학생 성적처리 프로그램 중에 새로운 학생을 등록(입력) 하는 기능을 만들어 봅시다.(테이블에 insert 실행)
// 파라미터를 전달하는 방식으로 sql 실행하기
public class InsertDMLUsingParameter {

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
		
		  
		  //insert SQL 작성 : 실행 되는 값이 되도록 매개변스로 처리합니다 매개변수 기호는 ? 입니다.
		  
		  String sql="insert into TBL_STUDENT values(?,?,?,?)";
		  //PreparedStatement 객체를 생성하면서 실행할 sql을 설정합니다.
//		    PreparedStatement 겍체는 Connection 객체를 메소드로 만듭니다. 
//		  Connection 구현객체는 dbms 종류에따라 생성되고 PreparedStatement 객체도 그에 따라 구현객체가 결정됨.
		  
		  PreparedStatement Pstmt = conn.prepareStatement(sql);
		  
		  // PreparedStatement() 메소드는 객체를 생성해서 리턴합니다.
		  
		  //매개변수에 값 전달하기 : 매개변수의 데이터형식 순서 문자열,문자열,정수,문자열 xxxx는 매개변수의 타입
		  Pstmt.setString(1,"2023004");	//1번 인덱스에 "2023004" 대입 
		  Pstmt.setString(2,"김땡사");	
		  Pstmt.setInt(3,17);
		  Pstmt.setString(4,"강원도");
		  
		 Pstmt.execute();
		 Pstmt.close();
		  
		System.out.println("정상적으로 새로운 학생이 등록되었습니다 ");
		 
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


 