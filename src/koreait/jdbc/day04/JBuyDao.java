package koreait.jdbc.day04;
//DAO 에는 입력과 출력은 포함시키지않습니다. 오직 어떤 형식의 데이터를 받아서
// 어떤 SQL을 살행하여 , 어떤값을 리턴할 것인가를 중점을 두고 정의하면 됩니다.
//DTOㄴ느 데이터를 저장하는 목적의 클래스 , DAO 는 어떤 동작을 할것인지를 장의한 메소드만 있습니다.


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day02.OracleUtilty;

public class JBuyDao<MyPageBuy> {	//구매와 관련된 CRUD 실행 SQL. DAO: JCustomerDao,JProductDao
//메소드 이름은 insert,update,select,selectBuyPname 등등으로 이름을 작성하세요

	
//	트랜잭션을 처리하는 예시 : auto commit 을 해체하고 직접 commit을 합니다.
	//try catch를 직접하세요. throw 아닙니다.
	
	public int insertManny(List<JBUY> carts) {
		Connection conn = OracleUtilty.getConnection();
		String sql = "insert into j_buy values (jbuy_seq.nextval , ? ,?,?,sysdate)";
		
		int count=0;
		PreparedStatement ps =null;
		try {
			conn.setAutoCommit(false);
			ps= conn.prepareStatement(sql);
		for(JBUY b : carts) {
			ps.setString(1,b.getCustomid());
			ps.setString(2,b.getPcode());
			ps.setInt(3,b.getQuantity());
			count +=ps.executeUpdate();
		}
		conn.commit();
		}catch(SQLException e){
			System.out.println("장바구니 상품 구매하기 예외 "+e.getMessage());
			try {
				conn.rollback();
			}catch(SQLException e1) {
				
			}
		}
							
		return count;
		
		
	}
//	select *from mypage_buy where customid='twice';
	public List<MypageBuy> mypageBuy(String customid) throws SQLException{
		Connection conn = OracleUtilty.getConnection();
		String sql="select*from mypage_buy where customid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, customid);
		ResultSet rs = ps.executeQuery();
		
		List<MypageBuy> list = new ArrayList<>();
		while(rs.next()) {
			list.add((MypageBuy) new MypageBuy(rs.getDate(1),
			rs.getString(2),
			rs.getString(3),
			rs.getString(4),
			rs.getInt(5),
			rs.getInt(6),
			rs.getLong(7)));
		
		}
		
		
		return list;
				
	}
	
//	select sum(total) from mypage_buy where customid='twicel;
	public long myMoney(String customid) throws SQLException {
		Connection conn = OracleUtilty.getConnection();
		String sql="select sum(total) from mypage_buy where customid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
//		 함수 조회하는 select는 항상 결과행이 1개, 컬럼도1개
		ps.setString(1, customid);
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		long sum=rs.getLong(1);
		return sum;		
		
	}
	public int insert(JBUY buy) {
		// TODO Auto-generated method stub
		return 1;
	}
	
	public JBUY selectOne(int buyseq) throws SQLException {
//  sql 실행을 구현을 하고 테스트 케이스 확인하기
		
		Connection connection = OracleUtilty.getConnection();
		String sql ="select*from j_Buy where  buy_seq=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, buyseq);
		JBUY buy=null;
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			buy = new JBUY(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDate(5));
			
		}
		return buy;
		
	}
		
	}
	
	
	
	
	
	
	

