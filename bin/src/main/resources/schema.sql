CREATE TABLE role(
  id BIGINT not null,
  name varchar(255) not null,
  PRIMARY KEY (id)
);

CREATE TABLE user (
  id BIGINT not null,
  email varchar(255) not null,
  first_name varchar(255) not null,
  last_name varchar(255) not null,
  password varchar(255) not null,
  PRIMARY KEY (id),
  UNIQUE KEY (email)
);