package koreait.jdbc.day03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day02.OracleUtilty;

//DAO : data Access(접근-읽기와 쓰기 )Object
//      ㄴSQL 실행 메소드를 모아놓은 클래스



/*
 * StudentDao 의 내용을 요약
 * insert,update 는 sql 파라미터에 전달한 데이터의 타입을 dto
 * delete 는 										원시형 또는 String
 * delete sql 의 조건절 컬럼이 여러개 일때는 dto 가 될수있습니다. map도 종종 사용
 * 
 * insert,update,delete 는 정수 리턴값으로 반영된 행의 개수를 전달
 * selectone : sql 
 * selectAll : 
 */
public class StudentDao {

	
		//나중에 db를 '쉽게코딩' 하기위한 '프레임 워크' 를 사용하면 Exception 처리 안해도됩니다.
		public int insert(StudentDto student) throws SQLException{
			
			Connection connection = OracleUtilty.getConnection();
			String sql ="insert into TBL_STUDENT values(?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, student.getStuno());
			ps.setString(2, student.getName());
			ps.setInt(3, student.getAge());
			ps.setString(4, student.getAddress());
			int result =ps.executeUpdate();
			
			ps.close();
			connection.close();
			return result;
			
			
	}
		
		
		public int update(StudentDto student) throws SQLException{
		
			Connection connection = OracleUtilty.getConnection();
			String sql = "update TBL_STUDENT\r\n"
				+ "set age = ?,address = ?\r\n"
				+ "where stuno =?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, student.getAge());
			ps.setString(2,student.getAddress());
			ps.setString(3,student.getStuno());
			int result = ps.executeUpdate();
			
		ps.close();
		connection.close();
		return result;
		}
	
		//delete 메소드는 여러분이 만드세요	
		public int delete(String stuno) throws SQLException{
			
			Connection connection = OracleUtilty.getConnection();
			String sql = "delete TBL_STUDENT\r\n"
					+ "where stuno =?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, stuno);
			int result = ps.executeUpdate();
			
			ps.close();
			connection.close();
			return result;
			
			
		}
		
	//select 모두 조회
		public List<StudentDto> selectAll() throws SQLException{
			Connection connection = OracleUtilty.getConnection();
			String sql="select*from TBL_STUDENT";
//			StudentDto SD =null;
			PreparedStatement ps = connection.prepareStatement(sql);
			List<StudentDto> result = new ArrayList<>();	
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String stuno= rs.getString(1);
				String name = rs.getString(2);			
			    int age = rs.getInt(3);			
     			String address = rs.getString(4);
//     			SD =  new StudentDto(stuno,name,age,address);
     			result.add(new StudentDto(stuno,name,age,address));
			}
			
			return result;
			
		}
   
		
		
		
		
		
		
		
		
		public StudentDto selectOne(String stuno) throws SQLException{
			
			Connection connection = OracleUtilty.getConnection();
			String sql="select*from TBL_STUDENT where stuno =?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, stuno);
			
			ResultSet rs = ps.executeQuery();	// =실행
			
			StudentDto result = null;
			if(rs.next()) {
								
				String name = rs.getString(2);			
			    int age = rs.getInt(3);			
     			String address = rs.getString(4);
     			result = new StudentDto(stuno,name,age,address);
     			// 코드를 줄이면 아래와 같이 사용
     			// return  new StudentDto(stuno,name,age,address);
			}
			
				return result;
			
			
		}
		
		
		
}
