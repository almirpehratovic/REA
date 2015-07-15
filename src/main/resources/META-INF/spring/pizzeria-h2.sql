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
  address text
);

CREATE TABLE customer
(
  id integer NOT NULL primary key,
  age integer
);

CREATE TABLE pizzeria
(
  id integer NOT NULL primary key,
  numOfEmployees integer
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

CREATE TABLE identification_setup
(
  id integer NOT NULL auto_increment primary key,
  entity text,
  lastId integer,
  prefix text,
  pattern text,
  suffix text
);

CREATE TABLE entity_group (
	id integer NOT NULL auto_increment primary key,
	name text
);

CREATE TABLE discount_group(
	id integer NOT NULL primary key,
	discount_percent real
);

CREATE TABLE resource_group (
	resource_id int not null,
	group_id int not null,
	FOREIGN KEY (resource_id) REFERENCES resource(id),
    FOREIGN KEY (group_id) REFERENCES entity_group(id)
);

CREATE TABLE agent_group (
	agent_id int not null,
	group_id int not null,
	FOREIGN KEY (agent_id) REFERENCES agent(id),
    FOREIGN KEY (group_id) REFERENCES entity_group(id)
);

CREATE TABLE event_group (
	event_id int not null,
	group_id int not null,
	FOREIGN KEY (event_id) REFERENCES event(id),
    FOREIGN KEY (group_id) REFERENCES entity_group(id)
);

CREATE TABLE users
(
  username character varying(20) NOT NULL primary key,
  password character varying(20) NOT NULL,
  role character varying(20) NOT NULL
);

insert into users(username,password,role) values ('user','user','ROLE_USER');
insert into agent(name,address) values ('John Doe','Central Park 23a');
insert into customer(id,age) values (1, 30);
insert into agent(name,address) values ('Pizzeria Cheers','Wall Street 65d');
insert into pizzeria(id,numOfEmployees) values (2,5);
insert into resource(name,unit,quantity) values('US Dollars','$','150.5');
insert into cash(id,currency) values(1,'USD');
insert into resource(name,unit,quantity) values('Funghi','unit','20.0');
insert into pizza(id,fat,packaging) values(2,'15%','M');
insert into identification_setup(entity,lastId,pattern) values('ba.ocean.pizzeria.domain.Pizza',100,'<static>P-</static><field length="2" case="upper">name</field><date>yyMM-ddS</date><static>-R-</static><id>id</id>');
insert into identification_setup(entity,lastId,pattern) values('ba.ocean.pizzeria.domain.CashReceipt',100,'<static>CR-</static><date>yyMM-ddS</date>');
insert into identification_setup(entity,lastId,pattern) values('ba.ocean.pizzeria.domain.Sale',100,'<static>SALE-</static><date>yyMM-ddS</date>');

insert into entity_group(name) values('Novogodišnji popust');
insert into discount_group(id,discount_percent) values(1,10.0);
insert into entity_group(name) values('Finalno sniženje');
insert into discount_group(id,discount_percent) values(2,30.0);
