use servlet;

insert into role (code, name) values('ADMIN','Quản trị hệ thống');
insert into role (code, name) values('USER','Người dùng');

insert into user (username, password,fullname,status,roleid) values('admin','123456','admin',1,1);
insert into user (username, password,fullname,status,roleid) values('nguyevana','123456','Nguyễn Văn A',1,2);
insert into user (username, password,fullname,status,roleid) values('ldh','123456','Lê Đức Hà',1,2);