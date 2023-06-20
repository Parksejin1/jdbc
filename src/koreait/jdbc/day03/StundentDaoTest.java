package koreait.jdbc.day03;

import java.sql.SQLException;
import java.util.List;

public class StundentDaoTest {

	public static void main(String[] args) {
		
		StudentDao dao = new StudentDao();
		
		
		System.out.println("1. insert 테스트 ");
		System.out.println("2023009 김떙구 17 강남구 - 데이터 입력");
		StudentDto dto = new StudentDto("2023009","김떙구",17,"강남구");
		try {
			int count = dao.insert(dto);
			System.out.println("학생등록 "+count+"건 입력 성공!!");
			System.out.println(" 입력 결과 조회"+dao.selectOne(dto.getStuno()));
		} catch (SQLException e) {
			System.out.println("예외 -"+e.getMessage());
			
		}
		
		
		System.out.println("2. update 테스트 ");
		System.out.println("2023009 김떙구를 16 용산구 - 데이터 입력");
		dto= new StudentDto("2023009","김떙구",16,"용산구");
		try {
			int count = dao.update(dto);
			System.out.println("학생등록 "+count+"건 정보수정 성공!!");
			System.out.println(" 수정 결과 조회"+dao.selectOne(dto.getStuno()));
		} catch (SQLException e) {
			System.out.println("예외 -"+e.getMessage());
		}
		
		System.out.println("3. delete 테스트");
		System.out.println("2023009 - 데이터 삭제");
		try {
			int count = dao.delete("2023009");
			System.out.println("학생등록 "+count+"건 삭제 성공!!");
		System.out.println(" 삭제 결과 조회"+dao.selectOne("2023009"));
		} catch (SQLException e) {
			System.out.println("예외 -"+e.getMessage());
		}
		
		System.out.println("4. select 테스트");
		System.out.println("학생 테이블의 모든 데이터 조회하여 출력합니다.");
		try {
			List<StudentDto> list = dao.selectAll();
			for(StudentDto stu : list) {
				System.out.println(stu);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

}
