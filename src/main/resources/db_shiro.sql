create database db_shiro character set utf8;

use db_shiro;

-- 用户表
create table users(
 id int primary key auto_increment,
 username varchar(50),
 password varchar(50)
);

insert into users(username, password) values('admin', '123456');

-- 用户角色表
create table user_role(
 id int PRIMARY key auto_increment,
 user_name varchar(50),
 role_name varchar(50)
);

insert into user_role(user_name, role_name) values('admin', 'user');

-- 角色权限表
create table roles_permissions(
 id int PRIMARY key auto_increment,
 permission varchar(200),
 role_name varchar(50)
);

insert into roles_permissions(permission, role_name) values('user:add', 'user');