create table bbscomment(
	seq int not null, 			-- 기본 글의 seq
	id varchar(50) not null, 	-- login한 유저 아이디
	content varchar(1000) not null,
	wdate timestamp not null
);

alter table bbscomment
add foreign key(id) references member(id);

select * from bbscomment;