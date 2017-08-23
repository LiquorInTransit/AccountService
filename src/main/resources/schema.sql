create table ACCOUNT (
	id INTEGER PRIMARY KEY,
	user_id INTEGER,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	address VARCHAR(255),
	is_driver BOOLEAN
);

create table CUSTOMER (
	id INTEGER PRIMARY KEY,
	user_id INTEGER,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	address VARCHAR(255)
);

create table DRIVER (
	id INTEGER PRIMARY KEY,
	user_id INTEGER,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	address VARCHAR(255)
);