DROP TABLE IF EXISTS account_info;
DROP TABLE IF EXISTS user_info;

CREATE TABLE user_info(
username varchar(50) PRIMARY KEY,
first_name varchar(30) NOT NULL,
last_name varchar(30) NOT NULL
);

CREATE TABLE account_info(
account_number integer PRIMARY KEY,
username varchar(50)  REFERENCES user_info(username),
pass_word varchar(30) NOT NULL,
access_type varchar(1) NOT NULL,
balance numeric(20,2),
join_date timestamp DEFAULT current_timestamp
);

INSERT INTO user_info(username, first_name, last_name)
	VALUES('admin1', 'fletcher', 'wilmott'),
	('employee1', 'tom', 'woods'),
	('customer1', 'clinton', 'obi');
	

INSERT INTO account_info(account_number, username, pass_word, access_type, balance)
	VALUES(1, 'admin1', 'admin1', 'A', 0),
	(2, 'employee1', 'employee1', 'E', 0);
	