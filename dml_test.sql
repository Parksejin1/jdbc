--CRUD : 데이터의 생성,읽기,갱신, 삭제를 가리키는 약자.
-- 프로그램이 가져야할 사용자 인터페이스(메뉴) 기본 기능.

--단순조회 테스트(read)
select*from TBL_STUDENT ;

-- insert 테스트(create)
insert into TBL_STUDENT values('2023001','김땡떙',16,'경기도');

-- update 테스트
update TBL_STUDENT
set age = 17,address = '종로구'
where stuno = '2023101';

-- delete삭제 테스트
delete from TBL_STUDENT where stuno='2023101'


select*from TBL_SCORE where subject ='과학'

--   여기서부터는 다른 테이블로 연습

/*
 *1. 회원 로그인 - 간단히 회원아이디를 입력해서 존재하면 로그인 성공
2. 상품 목록 보기
3. 상품명으로 검색하기	(그외 가격대 별 검색) 
4. 상품 장바구니 담기 - 장바구니 테이블이 없으므로 구매를 원하는 상품을 List 에 담기
5. 상품 구매(결제)하기 - 장바구니의 데이터를 구매 테이블에 입력하기  (여러개 insert)
6. 나의 구매 내역 보기 
 */

select*from TBL_CUSTOM; 
select*from TBL_PRODUCT;
select*from TBL_PRODUCT where pname like'%'|| '동원' ||'%';


select*from TBL_BUY;
select*from TBL_BUY where customid='mina012';


--기존에 연습했던 테이블을 변경하지 않도록 새롭게 복사해서 jdbc 구현합니다.
create table j_custom as select*from tbl_custom; -- pk fk 는 필요하면 따로 추가 필요

select*from j_custom;

create table j_PRODUCT as select*from TBL_PRODUCT;
select*from j_PRODUCT;

create table j_BUY as select*from TBL_BUY;
select*from j_BUY;

--custom id ,pcode,buy_seq 컬럼으로 pk설정하기
-- tbl_buy 테이블에는 외래키도 2개가 있습니다. (j_buy 외래키 설정 제외하고 하겠습니다.)
alter table j_custom add constraint custom_pk primary key (custom_id);
alter table j_PRODUCT add  primary key (pcode);
alter table j_BUY add  primary key (buy_seq);



--추가데이터 입력
insert into j_PRODUCT values('zzz01','B1','오뚜기바몬드카레',2400);
insert into j_PRODUCT values('APP01','A1','얼음골사과 1박스',32500);
insert into j_PRODUCT values('APP99','A1','씻은사과 10개',25000);

--j_buy 테이블의 사용할 시퀀스
drop sequence jbuy_seq;
create sequence jbuy_seq start with 2000;
select jbuy_seq.nextval from dual;

--rollback
select*from j_buy;
alter table j_buy add constraint q_check check (quantity between 1 and 30);
--check 제약조건 오류
insert into j_buy values (jbuy_seq.nextval,'twice','APP99',33,sysdate);

--6.마이페이지 구매내역
select buy_date, p.pcode,pname,quantity,price,quantity*price total
from j_buy b
join j_product p
on p.pcode=b.pcode
and b.customid='twice'
order by buy_date desc;
--자주 사용될 join 결과는 view 로 만들기 view 는 create or replace로 생성후에 수정까지 가능
-- view 는 가상의 물리적인 테이블이 아닙니다. 물리적 테이블을 이용해서 만들어진 가상의 테이블(논리적 테이블)
create or replace view mypage_buy
as
select buy_date, customid, p.pcode, pname,quantity,price,quantity*price total
from j_buy b
join j_product p
on p.pcode=b.pcode
order by buy_date desc;

select*from mypage_buy where customid='twice';

select sum(total) from mypage_buy where customid='twice';


-- 평가문제  매출


select mem.custno,mem.custname,mem.grade,Sum(amount*price) as total
from member_tbl_02 mem
join (
select  custno,Sum(amount*price) as total
from money_tbl_02
group by 
custno
having
total >0) groupmoney
on mem.custno=groupmoney.custno
order by total desc;

CREATE VIEW sales
as
SELECT a.CUSTNO , a.CUSTNAME , a.GRADE , b.total 
FROM MEMBER_TBL_02 a
join(
SELECT CUSTNO , sum(amount*price) total 
FROM MONEY_TBL_02 
GROUP BY custno) b
ON a.CUSTNO = b.custno
ORDER BY b.total desc;

-- step 1 회원별 매출합계
select custno, sum(price) from money_tbl_02 group by custno;


-- step 2 정렬 기준 확인하기
select custno, sum(price) from money_tbl_02 group by custno order by sum(price) desc;

-- step 3 custno 컬럼으로 조인하여 고객 정보 전체 가져오기
select * from member_tbl_02 met,
   (select custno, sum(price) asum from money_tbl_02 mot 
   group by custno
   order by asum desc) sale
where met.custno = sale.custno;
-- 또는
select * from member_tbl_02 met join
   (select custno, sum(price) asum from money_tbl_02 mot 
   group by custno
   order by asum desc) sale
on met.custno = sale.custno;

-- step 4 필요한 컬럼만 가져오기
select met.custno, custname,
   decode(met.grade, 'A', 'VIP', 'B', '일반', 'C', '직원') as grade,
   asum
   from member_tbl_02 met join
   (select custno, sum(price) asum from money_tbl_02 mot 
   group by custno
   order by asum desc) sale
   on met.custno = sale.custno ORDER BY asum desc;
-- 또는
select met.custno, custname,
   decode(met.grade, 'A', 'VIP', 'B', '일반', 'C', '직원') as grade,
   sale.asum
   from member_tbl_02 met,
   (select custno, sum(price) asum from money_tbl_02 mot 
   group by custno
   order by asum desc) sale
   where met.custno = sale.custno 
   ORDER BY total desc;

++ decode(grade, 'A', 'VIP', 'B', '일반', 'C', '직원');

-- 외부조인 : 매출이 없는 회원도 포함한다.
select met.custno, custname,
   decode(met.grade, 'A', 'VIP', 'B', '일반', 'C', '직원') as grade,
   nvl(sale.asum,0) total
   from member_tbl_02 met LEFT OUTER join
   (select custno, sum(price) asum from money_tbl_02 mot 
   group by custno
   order by asum desc) sale
   on met.custno = sale.custno ORDER BY total DESC ,custno;
   

   select*from j_Buy where  buyseq=?;
   
   
   
   --6월19일 로그인 구현하기 위한 패스워드 컬럼 추가를 합니다ㅣ.
   -- 패스워드 컬럼은 해시값 64문자를 저장합니다.
   
   alter table j_custom add password char(64); 
   
 update j_custom set
 password ='03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4'
 where custom_id ='twice';
