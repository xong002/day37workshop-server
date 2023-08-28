create database feeds;
use feeds;

create table posts (
	post_id varchar(8) not null,
    comments mediumtext,
    picture mediumblob,
    constraint pk_post_id primary key(post_id)
);

select * from posts;

delete from posts where post_id = '123';

insert into posts values ('123', 'hello', null);