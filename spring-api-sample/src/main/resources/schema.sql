-- H2
--CREATE table IF NOT EXISTS users(id int primary key, name varchar(30));

-- PostgreSQL
CREATE table IF NOT EXISTS users(id integer primary key, name varchar(30));
CREATE table IF NOT EXISTS command_status(id integer primary key, command_name varchar(30), status varchar(1));

