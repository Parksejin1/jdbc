package koreait.jdbc.day04;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JBUY {

	private int buy_seq;
	private String customid;
	private String pcode;
	private int quantity;
	private Date buy_date; 
	
	
	
	
}

//필드값이 모두 같으면 equals 로 true 이 되도록 하고싶다.
//-> equals 와 hashcode 를 재정의해야 합니다.
//=> vo 입니다 vo는 테스트 케이스 에서 객체를 비교할때 사용할수 있습니다. assertEquals 비교
