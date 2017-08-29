create table CUSTOMER (
	id INTEGER PRIMARY KEY,
	user_id INTEGER,
	address VARCHAR(255),
	payment_method VARCHAR(255)
);

create table DRIVER (
	id INTEGER PRIMARY KEY,
	user_id INTEGER,
	car VARCHAR(255)
);