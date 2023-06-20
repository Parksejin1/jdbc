package koreait.jdbc.day04;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyMallMain {
	
	public static void main(String[] args) {
		System.out.println(":::::: 김땡땡 쇼핑몰에 오신걸 환영합니다. :::::");
		System.out.println("<< 상품 소개 >>");
		JProductDao jProductDao = new JProductDao();
		try {
			List<JProduct> products = jProductDao.selectAll();
			for(JProduct product : products)
				System.out.println(product);
			
		} catch (SQLException e) {
			System.out.println("상품소개 예외 : " + e.getMessage());
		}
		
		System.out.println("\n<< 편리한 상품구매를 위해 검색하기 >>");
		Scanner sc = new Scanner(System.in);
		System.out.print("검색어 입력 >>> ");
		String pname = sc.nextLine();
		try {
			List<JProduct> products = jProductDao.selectByPname(pname);
			for(JProduct product : products)
				System.out.println(product);
			
		} catch (SQLException e) {
			System.out.println("상품검색 예외 : " + e.getMessage());
		}
		
		
		
				
		
		String name;
		String pcode;
		String price;
		boolean sel=true;
		
		jCustomDao cdao = new jCustomDao();
		JCustomer customer=null;
		boolean isLogin=false;
		String customid=null;
		
		System.out.println("\n<<상품 구매를 위해 로그인하기");
		
		while(!isLogin) {
		System.out.println("간편 로그인 - 사용자 id입력(로그인취소는0000)");
		 customid=sc.nextLine();
		if(customid.equals("0000")) break;
			try {
			customer = cdao.selectById(customid);
			if(customer !=null) {
				System.out.println(customer.getName()+"고객님환영합니다!!!");
			isLogin=true;}
			else System.out.println("입력하신 고객 ID가 존재 하지않습니다");
		}catch(SQLException e) {
		System.out.println("간편로그인 예외 :"+e.getMessage());
		}
		}
		
		//로그인 상태일때만 실행
		JBuyDao bdao = new JBuyDao();
		List<JBUY> carts = new ArrayList<>();
		if(isLogin) {
		while(true) {
			System.out.println("\n장바구니에 담기합니다.");
			System.out.println("구매할 상품코드 입력하세요 (0000 입력시 종료)>>>>>");
			String prcode= sc.nextLine();
			if(prcode.equals("0000")) break; 
			System.out.println("구매할 수량을 입력하세요.");
			int quantity = Integer.parseInt(sc.nextLine());
			carts.add(new JBUY(0,customid,prcode, quantity,null));
			
			System.out.println("장바구니에 담긴 물건을 구매하시겠습니까.(y/Y)");
			if(sc.nextLine().toLowerCase().equals("y"))break;
		}
		
		
		System.out.println("장바구니 확인 : "+carts);
		int count = bdao.insertManny(carts);
		if(count!=0)
	System.out.println("\n 결제를 완료했습니다 현재까지 " +customer.getName() +" 회원님의 구매 내역입니다.");
		//5번에 장바구니 담긴 상품이 j_Buy 테이블에 1)저장,2)잘못된수량 rollback 까지되는지확인
		//6. 마이페이지 -구매내역보기,총 구매 금액을 출력해주기 ->sql 테스트 해보고 메소드 작성
		
		try {
			List<MypageBuy> buys = bdao.mypageBuy(customid);
			DecimalFormat df = new DecimalFormat("###,###,###");
			for(MypageBuy b : buys) {
				System.out.println(String.format("%15s %20s %5d %10s %16s", 
						b.getBuy_date(),
						b.getPname(),
						b.getQuantity(),
						df.format(b.getPrice()),
						df.format(b.getTotal())));
			}
			long total = bdao.myMoney(customid);
			System.out.println("총 구매 금액 : "+df.format(total));
		}catch(SQLException e) {
			System.out.println("구매 내역예외 : "+e.getMessage());
		}
		
		}
		else {System.out.println("로그인을 취소했습니다 프로그램을 종료합니다.");
		}
		sc.close();
	}
		
	}
	
	
	
	
	
	
	
	
	


