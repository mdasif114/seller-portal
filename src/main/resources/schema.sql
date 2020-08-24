CREATE TABLE ROLE (
  ROLE_ID BIGINT NOT NULL,
  ROLE_NAME varchar(255) not null,
  CONSTRAINT PK_ROLE PRIMARY KEY (ROLE_ID)
);

CREATE TABLE USER (
  USER_ID BIGINT not null,
  account_type varchar(255) not null,
  shop_based_in varchar(255) not null,
  mobile_number varchar(255) not null,
  email varchar(255) not null,
  password varchar(255) not null,
  CONSTRAINT PK_USER PRIMARY KEY (USER_ID)
);

create table USERS_ROLES
(
  USER_ROLE_ID BIGINT not null,
  USER_ID BIGINT not null,
  ROLE_ID BIGINT not null
);

alter table USERS_ROLES
  add constraint USER_ROLE_PK primary key (USER_ROLE_ID);

alter table USERS_ROLES
  add constraint USER_ROLE_UK unique (USER_ID, ROLE_ID);

alter table USERS_ROLES
  add constraint USER_ROLE_FK1 foreign key (USER_ID)
  references user (USER_ID);

alter table USERS_ROLES
  add constraint USER_ROLE_FK2 foreign key (ROLE_ID)
  references role (ROLE_ID);
  
CREATE TABLE ADDRESS (
  USER_ID BIGINT not null,
  ADDRESS_ID BIGINT not null,
  line_one varchar(255),
  line_two varchar(255),
  city varchar(255),
  post_code varchar(255),
  country varchar(255)
);

alter table ADDRESS
  add constraint ADDRESS_PK primary key (ADDRESS_ID);

CREATE TABLE IDENTITY (
  USER_ID BIGINT not null,
  IDENTITY_ID BIGINT not null,
  document_type varchar(255) not null,
  document_number varchar(255) not null
);

alter table IDENTITY
  add constraint IDENTITY_PK primary key (IDENTITY_ID);
  
CREATE TABLE BANK (
  USER_ID BIGINT not null,
  BANK_ID BIGINT not null,
  account_name varchar(255) not null,
  account_number varchar(255) not null,
  bank_name varchar(255) not null,
  swift_code varchar(255) not null
);

alter table BANK
  add constraint BANK_PK primary key (BANK_ID);