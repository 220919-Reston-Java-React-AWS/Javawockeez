-- clear all table to start anew
drop table if exists accounts;

create table accounts ( 
	id SERIAL primary key,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	username varchar(50) not null unique,
	password varchar(50) not null
);

insert into accounts (first_name, last_name, username, password) values
	('joe', 'doe', 'jd', 'password');