CREATE SEQUENCE seq_agent
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE SEQUENCE seq_event
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE SEQUENCE seq_resource
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE resource
(
  id integer NOT NULL DEFAULT nextval('seq_resource'::regclass),
  name text,
  unit text,
  quantity real,
  CONSTRAINT resource_pk PRIMARY KEY (id)
);

CREATE TABLE pizza
(
  id integer NOT NULL,
  fat text,
  packaging text,
  CONSTRAINT pizza_pk PRIMARY KEY (id)
);

CREATE TABLE cash
(
  id integer NOT NULL,
  currency text,
  CONSTRAINT cash_pk PRIMARY KEY (id)
)

CREATE TABLE agent
(
  id integer NOT NULL DEFAULT nextval('seq_agent'::regclass),
  name text,
  address text,
  type text,
  details text,
  CONSTRAINT agent_pk PRIMARY KEY (id)
);

CREATE TABLE event
(
  id integer NOT NULL DEFAULT nextval('seq_event'::regclass),
  name text,
  event_date date,
  provider_id integer,
  receiver_id integer,
  resource_id integer,
  quantity real,
  CONSTRAINT event_pk PRIMARY KEY (id),
  CONSTRAINT event_fk1 FOREIGN KEY (provider_id)
      REFERENCES agent (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT event_fk2 FOREIGN KEY (receiver_id)
      REFERENCES agent (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT event_fk3 FOREIGN KEY (resource_id)
      REFERENCES resource (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE increment
(
  id integer NOT NULL,
  CONSTRAINT increment_pk PRIMARY KEY (id)
);

CREATE TABLE decrement
(
  id integer NOT NULL,
  CONSTRAINT decrement_pk PRIMARY KEY (id)
);

CREATE TABLE increment_decrement
(
  increment_id integer,
  decrement_id integer,
  CONSTRAINT increment_decrement_fk1 FOREIGN KEY (increment_id)
      REFERENCES increment (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT increment_decrement_fk2 FOREIGN KEY (decrement_id)
      REFERENCES decrement (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE sale
(
  id integer NOT NULL,
  CONSTRAINT sale_pk PRIMARY KEY (id)
);

CREATE TABLE cash_receipt
(
  id integer NOT NULL,
  CONSTRAINT cash_receipt_pk PRIMARY KEY (id)
);

CREATE TABLE users
(
  username character varying(20) NOT NULL,
  password character varying(20) NOT NULL,
  role character varying(20) NOT NULL,
  CONSTRAINT users_pkey PRIMARY KEY (username)
);

insert into users(username,password,role) values ('user','user','ROLE_USER');


