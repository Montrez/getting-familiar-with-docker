DROP DATABASE IF EXISTS `attorneygo_db`;

CREATE DATABASE `attorneygo_db`;

USE `attorneygo_schema`;

CREATE TABLE `new_table` (
  `user_id`		int(11)			NOT NULL	AUTO_INCREMENT,
  `username` 	varchar(45)		NOT NULL,
  `password`	varchar(45)		NOT NULL,
  `name` 		varchar(40) 	NOT NULL,
  `enabled`		bit(1)			NOT NULL,
  PRIMARY KEY (`user_id`)
);

insert into new_table(username, password, name, enabled) values
('cweems', 'weems', 'Charles Weems', 1),
('mcox', 'cox', 'Montrez Cox', 1),
('bderricho', 'derricho', 'Brandon Derricho', 1),
('jtolson', 'tolson', 'Jack Tolson', 1),
('fdallass', 'dallass', 'Francisca Dallass', 1),
('smartin', 'martin', 'Shea Martin', 1);