--뉴스기사와 댓글과의 조인
SELECT n.news_idx AS news_idx, title, count(c.comments_idx) AS cnt
 FROM news n LEFT OUTER join comments c
 on n.news_idx=c.news_idx
 GROUP BY n.news_idx, title;
-- 그룹바이에 명시된 컬럼만이 select절에 올수있다.
-- 결론 : 뉴스 기사는 자식 댓글이 있건 없건, join시 무조건 가져와야함
-- 			따라서, 부모인 뉴스 테이블을 outer join으로 처리하자!!

create table animal(
idx number
,category varchar(30)
,name varchar(30)
,rank number
,primary key(idx)
);

create sequence seq_animal
increment by 1
start with 1;

insert into animal(idx,category,name,rank) values(seq_animal.nextval,'조류','참새',5);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'포유류','고양이',3);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'파충류','뱀',9);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'어류','상어',12);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'포유류','치타',2);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'양서류','개구리',8);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'파충류','도마뱀',4);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'곤충','메뚜기',6);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'양서류','맹꽁이',10);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'조류','독수리',1);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'어류','참치',11);
insert into animal(idx,category,name,rank) values(seq_animal.nextval,'포유류','고래',7);

SELECT * FROM animal ORDER BY CATEGORY  ASC ;

SELECT * FROM animal ORDER BY CATEGORY ASC, rank  ASC ; --정렬의 정렬. 정렬 내에서의 정렬

CREATE TABLE reboard(
 reboard_idx NUMBER PRIMARY KEY
 , title varchar2(100)
 , writer varchar2(20)
 , content clob
 , regdate DATE DEFAULT sysdate
 , hit NUMBER DEFAULT 0
 , team NUMBER DEFAULT 0 -- 관련글 뭉쳐다니게 하기 위한 컬럼
 , step NUMBER DEFAULT 0 --관련글 내에서의 정렬
 , depth NUMBER DEFAULT 0 --답변의 깊이
);

CREATE SEQUENCE seq_reboard
INCREMENT BY 1
START WITH 1;

CREATE TABLE joinyogurt(
joinyogurt_idx NUMBER PRIMARY KEY
, name varchar2(20)
, id varchar2(20)
, pass varchar2(64)
, mobile NUMBER
, birth NUMBER
, email varchar2(50)
 , regdate DATE DEFAULT sysdate
);

CREATE SEQUENCE seq_joinyogurt
INCREMENT BY 1
START WITH 1;

create table dept(
deptno number 
,dname varchar2(14)
,loc varchar2(13)
,primary key(deptno)
);

insert into dept(deptno,dname,loc) values(10,'ACCOUNTING','NEW YORK');
insert into dept(deptno,dname,loc) values(20,'RESEARCH','DALLAS');
insert into dept(deptno,dname,loc) values(30,'SALES','CHICAGO');
insert into dept(deptno,dname,loc) values(40,'OPERATIONS','BOSTON');

create table emp(
empno number 
,ename varchar2(10)     
,job varchar2(9)         
,mgr number
,hiredate date
,sal number
,comm number
,deptno number
,primary key(empno)
);

insert into emp(empno,ename,job,mgr,hiredate,sal,deptno) values(7369,'SMITH','CLERK',7902,'80/12/17',800,20);
insert into emp(empno,ename,job,mgr,hiredate,sal,comm,deptno) values(7499,'ALLEN','SALESMAN',7698,'81/02/20',1600,300,30);
insert into emp(empno,ename,job,mgr,hiredate,sal,comm,deptno) values(7521,'WARD','SALESMAN',7698,'81/02/22',1250,500,30);
insert into emp(empno,ename,job,mgr,hiredate,sal,deptno) values(7566,'JONES','MANAGER',7839,'81/04/02',2975,20);
insert into emp(empno,ename,job,mgr,hiredate,sal,comm,deptno) values(7654,'MARTIN','SALESMAN',7698,'81/09/28',1250,1400,30);
insert into emp(empno,ename,job,mgr,hiredate,sal,deptno) values(7698,'BLAKE','MANAGER',7839,'81/05/01',2850,30);
insert into emp(empno,ename,job,mgr,hiredate,sal,deptno) values(7782,'CLARK','MANAGER',7839,'81/06/09',2450,10);
insert into emp(empno,ename,job,mgr,hiredate,sal,deptno) values(7788,'SCOTT','ANALYST',7566,'87/04/19',3000,20);
insert into emp(empno,ename,job,hiredate,sal,deptno) values(7839,'KING','PRESIDENT','81/11/17',5000,10);
insert into emp(empno,ename,job,mgr,hiredate,sal,comm,deptno) values(7844,'TURNER','SALESMAN',7698,'81/09/08',1500,0,30);
insert into emp(empno,ename,job,mgr,hiredate,sal,deptno) values(7876,'ADAMS','CLERK',7788,'87/05/23',1100,20);
insert into emp(empno,ename,job,mgr,hiredate,sal,deptno) values(7900,'JAMES','CLERK',7698,'81/12/03',950,30);
insert into emp(empno,ename,job,mgr,hiredate,sal,deptno) values(7902,'FORD','ANALYST',7566,'81/12/03',3000,20);
insert into emp(empno,ename,job,mgr,hiredate,sal,deptno) values(7934,'MILLER','CLERK',7782,'82/01/23',1300,10); 


CREATE TABLE test(
 test_idx NUMBER PRIMARY KEY
 , title varchar2(100)
 , regdate DATE DEFAULT sysdate
);

CREATE SEQUENCE seq_test
INCREMENT BY 1
START WITH 1;

ALTER TABLE test ADD poster varchar2(100);
ALTER TABLE test ADD place varchar2(30);
ALTER TABLE test ADD book varchar2(100);
ALTER TABLE test ADD applydate varchar2(100);
ALTER TABLE test ADD fee varchar2(200);
ALTER TABLE test ADD sort varchar2(200);
ALTER TABLE test ADD tel varchar2(200);

select * from test;

ALTER TABLE test MODIFY(title varchar2(200));
ALTER TABLE test MODIFY(place varchar2(200));
ALTER TABLE test MODIFY(book varchar2(200));
ALTER TABLE test MODIFY(poster varchar2(200));


CREATE TABLE heart(
 heart_idx NUMBER PRIMARY KEY
 , title varchar2(100)
 , regdate DATE DEFAULT sysdate
);

CREATE SEQUENCE seq_heart
INCREMENT BY 1
START WITH 1;

ALTER TABLE heart ADD poster varchar2(100);
ALTER TABLE heart ADD place varchar2(100);
ALTER TABLE heart ADD book varchar2(100);
ALTER TABLE heart ADD applydate varchar2(100);

ALTER TABLE heart ADD fee varchar2(200);
ALTER TABLE heart ADD sort varchar2(200);
ALTER TABLE heart ADD tel varchar2(200);

ALTER TABLE heart MODIFY(title varchar2(300));
ALTER TABLE heart MODIFY(poster varchar2(300));
ALTER TABLE heart MODIFY(place varchar2(300));
ALTER TABLE heart MODIFY(book varchar2(300));
ALTER TABLE heart MODIFY(fee varchar2(300));

ALTER TABLE heart ADD CONSTRAINT title_unq UNIQUE(title); 
ALTER TABLE heart DROP CONSTRAINT title_unq;

ALTER TABLE heart RENAME COLUMN edit TO applydate;
delete from heart;

ALTER TABLE heart DROP COLUMN tel;


CREATE TABLE test2(
 test2_idx NUMBER PRIMARY KEY
 , title varchar2(200)
 , poster varchar2(200)
 , place varchar2(200)
 , book varchar2(200)
 , applydate varchar2(100)
 , fee varchar2(200)
 , sort varchar2(200)
 , regdate DATE DEFAULT sysdate
);

CREATE SEQUENCE seq_test2
INCREMENT BY 1
START WITH 1;

ALTER TABLE test2 MODIFY(book varchar2(500));
ALTER TABLE test2 MODIFY(fee varchar2(500));

ALTER TABLE DIARY DROP COLUMN icon;