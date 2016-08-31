DROP TABLE IF EXISTS additionaluserinfo;
DROP TABLE IF EXISTS pictureurls;
DROP TABLE IF EXISTS ad;
DROP TABLE IF EXISTS passwordtoken;
DROP TABLE IF EXISTS verificationtoken;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;

create table users(
    username varchar(60) NOT NULL,
    primary key(username),
    password varchar(60) NOT NULL,
    enabled boolean not null
);

create table authorities (
   auth_id int unsigned NOT NULL AUTO_INCREMENT,
   username varchar(60) not null,
   authority varchar(60) not null,
   primary key(auth_id),
   constraint fk_authorities_users foreign key (username) references users(username)  
);

create table verificationtoken(
    id bigint unsigned NOT NULL AUTO_INCREMENT,
    primary key(id),
    token varchar(60) NOT NULL,
    username varchar(60) NOT NULL,
	expiryDate DATE NOT NULL
);

create table passwordtoken(
    id bigint unsigned NOT NULL AUTO_INCREMENT,
    primary key(id),
    token varchar(60) NOT NULL,
    username varchar(60) NOT NULL,
	expiryDate DATE NOT NULL
);

create table ad(
	id bigint unsigned NOT NULL AUTO_INCREMENT,
    primary key(id),   
	province varchar(60) NOT NULL,
	municipality varchar(60) NOT NULL,
	department varchar(60) NOT NULL,
	adType varchar(60) NOT NULL,
	personOrCompany varchar(60) NOT NULL,
	rubric varchar(60) NOT NULL,
	messageText varchar(60) NOT NULL,
	zipCode varchar(60) NOT NULL,
	price varchar(60) NOT NULL,
	name varchar(60) NOT NULL,
	email varchar(60) NOT NULL,
	telephone varchar(60) NOT NULL,
	addedDate DATE NOT NULL
);

create table pictureurls(
	id bigint unsigned NOT NULL AUTO_INCREMENT,    	
	url varchar(60) NOT NULL,
	advertid bigint unsigned NOT NULL,
	primary key(id),
	constraint fk_pictureurls_ad foreign key (advertid) references ad(id) 
);

create table additionaluserinfo (
   username varchar(60) not null,
   province varchar(60) not null,
   municipality varchar(60) not null,
   personOrCompany varchar(60) not null,
   name varchar(60) not null,
   address varchar(60),
   zipCode varchar(60),
   telephone varchar(60) not null,
   sex varchar(60),
   birthdate varchar(60),
   primary key(username),
   constraint fk_additionalinfo_users foreign key (username) references users(username)  
);

INSERT INTO users VALUES ('teodore@testaaja.fi','$2a$10$ZibSjZvWWRHo8QR3bqsZreaANqRjZyPIA8DIYRRWxQwTYrIGujAIS',1),
('toni@testaaja.fi','$2a$10$ZibSjZvWWRHo8QR3bqsZreaANqRjZyPIA8DIYRRWxQwTYrIGujAIS',2);;

INSERT INTO authorities VALUES (1,'teodore@testaaja.fi','TEST_AUTH');

INSERT INTO passwordtoken VALUES(1,'32870e41-4334-4640-9e84-408df5ad6c6b','teodore@testaaja.fi','2016-07-12');

INSERT INTO ad VALUES (1,'Pirkanmaa','Tampere','0','sell','person','testiotsikko','testiteksti','33340','100','teodore testaaja','teodore@testaaja.fi','0401234567','2016-08-12'),
(2,'Pirkanmaa','Tampere','0','sell','person','testiotsikko','testiteksti','33340','100','teodore testaaja','teodore@testaaja.fi','0401234567','2016-08-12');

INSERT INTO additionaluserinfo VALUES ('teodore@testaaja.fi','Pirkanmaa','Tampere','person','teodore testaaja','testikatu 1','33340','0401234567','M','01.01.1910');

INSERT INTO pictureurls VALUES (1,'randomFolderNameHere/696441008620124.jpg',1);

INSERT INTO verificationtoken VALUES (1,'3a3a2cef-7238-4e2d-9681-c4e8b682282d','teodore@testaaja.fi','2016-08-09');
