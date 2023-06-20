package koreait.jdbc.day01;
//다른 DBMS 클라이언트 프로그램과 같이 db를 사용할수 있는 동작을 구현
// 제일 먼저 해야할것 : 데이터 베이스 연결  
import java.sql.Connection;
import java.sql.DriverManager;

public class OracleConnectionTest {

	public static void main(String[] args) {
		
		//0. Connection 은 인터페이스로 직접 객체를 생성하지 않고 구현클래스가 있어야합니다. 
//		db 에서는 db드라이버가 접속하려는 db의 종류에 따라 알아서(프록시) 구현클래스와 구현객체를 만듭니다.
		Connection conn=null;
		//1. 아래 4개의 필수 연결 정보를 설정합니다.
		//접속하고자 하는 서버의 주소 (포털접속 https://www.naver.com과 비슷한개념)
		String uri="jdbc:oracle:thin:@localhost:1521:xe";
		
		//oracle.jdbc.driver 는 ojdbc6.jar에 포함된 패키지 이름
		//OracleDriver 는 오라클 드라이버 클래스 이름
		String driver="oracle.jdbc.driver.OracleDriver";
		String user="iclass";
		String password="0419";

		
		try {
			//2. 드라이버 클래스 객체를 메모리에 로드(올리기)
			//    ㄴ 연결 객체를 만들어주는 역할을 합니다.
		  Class.forName(driver);
		  
		  //3. DriverManager 클래스는 연결객체를 만듭니다.  - 2번의 객체를 동작
//		  이떄 2번에서 만든객체 즉 dbms 에 따라 구현객체가 만들어짐
		  
		  conn = DriverManager.getConnection(uri,user,password);
		  System.out.println("conn 객체의 구현클래스 : "+conn.getClass().getName());
		  //oracle.jdbc.driver.T4CConnection 클래스로 객체가 생성
		  
		  //4. 3번의 결과로 오라클 db에 맞는 연결객체가 생성됩니다.
		  System.out.println("연결 상태 = " +conn);
		  if(conn!=null)
			  System.out.println("오라클 데이터 베이스 연결 성공|| ");
		  else 
			  System.out.println("오라클 데이터 베이스 연결 실패|| ");
		
		}catch (Exception e) {	//ClassNotfoundException, SQLException 처리 필요
			System.out.println("ClassNotfoundException = 드라이버 경로가 잘못됐습니다.");
			System.out.println("SQLException = url 또는 user 또는 password가 잘못됐습니다.");
			System.out.println("오류 메시지 - "+e.getMessage());
			e.printStackTrace();  //Exception 발생의 모든 원인을 cascade 형식으로 출력
		}finally {
			try {
				if(conn !=null)
					conn.close(); 	//SQL Exception 처리
				
				
			}catch(Exception e) {}
		}
		
	
}
}

/*		API : Application Programming Interface 
 * 			: 서로 다른 소프트웨어 시스템 간의 연결을 위한 방식(라이브러리로 제공됩니다.). 인터페이스는 소통
 * 
 *  	jdbc : 자바와 dbms를 연결하는 api. 오라클은 ojdbX.jar 이고 x는 오라클 jdbc 버전 표시
 */
 