create database Chatroom;

use Chatroom;

drop table if exists user;
create table user(
userId int primary key auto_increment,
name varchar (50) unique ,
password varchar (50),
nickname varchar (50),
lastLogout Datetime
);

drop table if exists channel;
create table channel(
    channelId int primary key auto_increment,
    channelName varchar (50)
);

drop table if exists message;
create table message(
messageId int primary key auto_increment,
userId int,
channelId int,
content text,
sendTime datetime
)