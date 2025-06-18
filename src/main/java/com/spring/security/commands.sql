desc students;

insert into students(first_name,last_name,email) VALUES ("Sri","Rama","srirama@gmail.com");
insert into students(first_name,last_name,email) VALUES ("Sita","Rama","sitarama@gmail.com");
insert into students(first_name,last_name,email) VALUES ("Laxmana","Rama","laxmana@gmail.com");
insert into students(first_name,last_name,email) VALUES ("Ravana","Poulatsya","ravana@gmail.com");

select * from students;

drop table student;

create table users (username varchar(50) not null, password varchar(50) not null, enabled TINYINT not null, PRIMARY KEY (username));

create table members (user_id varchar(50) not null, pw varchar(68) not null, active TINYINT not null, PRIMARY KEY (user_id));

insert into users VALUES 
("srirama","{bcrypt}$2a$10$kul9eHBbxkpS40KKpaD6D.EgsjjMLyHZoFtxFxQzKS2FQxEOuLB6y",1),
("sitarama","{bcrypt}$2a$10$jMWi/mVKIb8KwWJKgq.et.CiLhc6F7ETWEskdj13BoiqxhSl.OFKe",1),
("laxmana","{bcrypt}$2a$10$z1lG42YLhXhW6mfCfcWCSOcNaDraPxcSAVMboQaD3lplLG9hVuN0y",1),
("ravana","{bcrypt}$2a$10$nyAs5vh2TPL5gVbQzJinX.aR6ICXg.JAwp0RQT29TC1EshTMIA28G",0);

insert into members VALUES 
("srirama","{bcrypt}$2a$10$kul9eHBbxkpS40KKpaD6D.EgsjjMLyHZoFtxFxQzKS2FQxEOuLB6y",1),
("sitarama","{bcrypt}$2a$10$jMWi/mVKIb8KwWJKgq.et.CiLhc6F7ETWEskdj13BoiqxhSl.OFKe",1),
("laxmana","{bcrypt}$2a$10$z1lG42YLhXhW6mfCfcWCSOcNaDraPxcSAVMboQaD3lplLG9hVuN0y",1),
("ravana","{bcrypt}$2a$10$nyAs5vh2TPL5gVbQzJinX.aR6ICXg.JAwp0RQT29TC1EshTMIA28G",0);
select * from users;

create table authorities (username varchar(50) not null, authority varchar(50) not null, unique key authorities_idx_1 (username, authority), constraint authorities_ibfk_1 FOREIGN KEY(username) references users(username));

create table roles (user_id varchar(50) not null, role varchar(50) not null, unique key roles_idx_1 (user_id, role), constraint roles_ibfk_1 FOREIGN KEY(user_id) references members(user_id));

desc authorities;
insert into authorities values 
("srirama","ROLE_ADMIN"),
("sitarama","ROLE_ADMIN"),
("laxmana","ROLE_MANAGER"),
("ravana","ROLE_USER");

insert into roles values 
("srirama","ROLE_ADMIN"),
("sitarama","ROLE_ADMIN"),
("laxmana","ROLE_MANAGER"),
("ravana","ROLE_USER");

insert into users values ("hanuman","{bcrypt}$2a$10$b32tj2.CBSDgI3zLxSiBbOLccXP7tV0x3FbL3Q6a8MNc0pZXA9r.q",1);
insert into authorities values ("hanuman","ROLE_MANAGER");
drop table if exists users;
drop table if exists authorities;
create table users (username varchar(50) not null, password varchar(68) not null, enabled TINYINT not null, PRIMARY KEY (username));

