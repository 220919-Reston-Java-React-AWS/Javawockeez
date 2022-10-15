--Drops

drop table if exists users;
drop table if exists roles;


--Roles
create table roles(
	id serial primary key,
	title varchar(8) not null
);

insert into roles(title) 
values
('Customer'),
('Retailer');


--Users
create table users(
	id serial primary key,
	first_name varchar(30),
	last_name varchar(30),
	email varchar(256) not null unique, --320 or 256 characters allowed? conflicting sources
	pwd varchar(20) not null,
	role_id int not null default 1,
	constraint fk_user_role foreign key(role_id) references roles(id) on delete cascade
);

insert into users(first_name, last_name, email, pwd, role_id)
values 
('Aidan', 'Shafer', 'shaferai210@gmail.com', 'A1dan$hafer', 1),
('Brandin', 'Randolph', 'brandinrandolph@gmail.com', 'password123', 1),
('Chris', 'McMillen', 'chrismcmillen4533@yahoo.com', 'password21', 1),
('Daniel', 'Rivera', 'daniel@javawockeez.com', 'password123', 2);






select * from roles;
select * from users;


delete from users where users.id=7;


