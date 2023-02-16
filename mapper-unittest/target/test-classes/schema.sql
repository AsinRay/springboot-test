
drop table if exists pushlog;
create table pushlog(id int primary key auto_increment, src varchar, dest varchar, msg varchar, dt timestamp);