package koreait.jdbc.day04;

import java.sql.Date;

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
public class JCustomer {
			private String customid;
			private String name;
			private String email;
			private int age;
			private Date rge_date;
			
}

