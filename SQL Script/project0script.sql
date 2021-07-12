DROP TABLE IF EXISTS account_info;
DROP TABLE IF EXISTS user_info;


CREATE TABLE account_info(
username varchar(50) PRIMARY KEY,
pass_word varchar(30) NOT NULL,
access_type varchar(1) NOT NULL,
balance numeric(20,2),
join_date timestamp DEFAULT current_timestamp
);

CREATE TABLE user_info(
username varchar(50) REFERENCES account_info(username),
first_name varchar(30) NOT NULL,
last_name varchar(30) NOT NULL
);

INSERT INTO account_info(username, pass_word, access_type)
	VALUES('admin1', 'admin1', 'a'),
	('employee1', 'employee1', 'e');
	
INSERT INTO user_info(username, first_name, last_name)
	VALUES('admin1', 'fletcher', 'wilmott'),
	('employee1', 'tom', 'woods');