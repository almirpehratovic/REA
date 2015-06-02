CREATE TABLE resource
(
  id integer NOT NULL auto_increment primary key,
  name text,
  unit text,
  quantity real
);

CREATE TABLE pizza
(
  id integer NOT NULL primary key,
  fat text,
  packaging text
);

CREATE TABLE cash
(
  id integer NOT NULL primary key,
  currency text
);

CREATE TABLE agent
(
  id integer NOT NULL auto_increment primary key,
  name text,
  address text,
  type text,
  details text
);


CREATE TABLE event
(
  id integer NOT NULL auto_increment primary key,
  name text,
  event_date date,
  provider_id integer,
  receiver_id integer,
  resource_id integer,
  quantity real,
  FOREIGN KEY (provider_id)  REFERENCES agent (id),
  FOREIGN KEY (receiver_id)  REFERENCES agent (id),
  FOREIGN KEY (resource_id)  REFERENCES resource (id)
);

CREATE TABLE increment
(
  id integer NOT NULL primary key
);

CREATE TABLE decrement
(
  id integer NOT NULL primary key
);

CREATE TABLE increment_decrement
(
  increment_id integer,
  decrement_id integer,
  FOREIGN KEY (increment_id) REFERENCES increment (id),
  FOREIGN KEY (decrement_id) REFERENCES decrement (id)
);

CREATE TABLE sale
(
  id integer NOT NULL primary key
);

CREATE TABLE cash_receipt
(
  id integer NOT NULL primary key
);

CREATE TABLE users
(
  username character varying(20) NOT NULL primary key,
  password character varying(20) NOT NULL,
  role character varying(20) NOT NULL
);

insert into users(username,password,role) values ('user','user','ROLE_USER');
insert into agent(name,address,type,details) values ('John Doe','Central Park 23a','FL','#34#####');
insert into agent(name,address,type,details) values ('Pizzeria Cheers','Wall Street 65d','ORG','#d.o.o.#1234567890987654####');
insert into resource(name,unit,quantity) values('US Dollars','$','150.5');
insert into cash(id,currency) values(1,'USD');

