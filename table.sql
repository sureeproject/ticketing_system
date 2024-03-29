use ticket_system;
drop table user;
drop table ticket;
drop table user_order;

CREATE TABLE user (
  pk_user_id INT NOT NULL AUTO_INCREMENT,
  email TEXT NOT NULL,
  password VARCHAR(255) NOT NULL,
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (pk_user_id),
  CONSTRAINT unique_email UNIQUE (email(100))
);


CREATE TABLE ticket (
  pk_ticket_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
  status VARCHAR(255) NOT NULL,
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (pk_ticket_id)
);

CREATE TABLE user_order (
  pk_order_id INT NOT NULL AUTO_INCREMENT,
  fk_user_id INT NOT NULL,
  fk_ticket_id INT NOT NULL,
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (pk_order_id),
  FOREIGN KEY (fk_user_id) REFERENCES user(pk_user_id),
  FOREIGN KEY (fk_ticket_id) REFERENCES ticket(pk_ticket_id),
  CONSTRAINT unique_ticket_user UNIQUE (fk_ticket_id, fk_user_id)
);
