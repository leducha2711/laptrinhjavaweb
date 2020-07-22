use servlet;

CREATE TABLE role(
	id bigint NOT NULL PRIMARY KEY 	auto_increment,
    name VARCHAR(150) NOT NULL,
    code VARCHAR(150) NOT NULL,
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createdby VARCHAR(250) NULL,
    modifiedby VARCHAR(250) NULL
);

CREATE TABLE user(
	id bigint NOT NULL PRIMARY KEY 	auto_increment,
    name VARCHAR(150) NOT NULL,
    password VARCHAR(150) NOT NULL,
    fullname VARCHAR(150)  NULL,
    status int NOT NULL,
    roleid bigint NOT NULL,
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
     createdby VARCHAR(250) NULL,
    modifiedby VARCHAR(250) NULL
);

alter table user add constraint fk_user_role foreign key (roleid) references role(id);

CREATE TABLE news(
	id bigint NOT NULL PRIMARY KEY 	auto_increment,
    title VARCHAR(255) NULL,
    thumbnail VARCHAR(255) NULL,
    shortdescription TEXT NULL,
    content TEXT NULL,
    categoryid bigint NOT NULL,
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
	createdby VARCHAR(250) NULL,
    modifiedby VARCHAR(250) NULL
);

CREATE TABLE category(
	id bigint NOT NULL PRIMARY KEY 	auto_increment,
    name VARCHAR(150) NOT NULL,
    code VARCHAR(150) NOT NULL,
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
	createdby VARCHAR(250) NULL,
    modifiedby VARCHAR(250) NULL
);


Alter table news add constraint fk_news_category foreign key (categoryid) references category(id);

CREATE TABLE comment(
	id bigint NOT NULL PRIMARY KEY 	auto_increment,
    content TEXT NOT NULL,
    user_id  bigint NOT NULL ,
    news_id  bigint NOT NULL ,
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
	createdby VARCHAR(250) NULL,
    modifiedby VARCHAR(250) NULL
);

Alter table comment add constraint fk_comment_user foreign key (user_id) references user(id);
Alter table comment add constraint fk_comment_news foreign key (news_id) references news(id);
