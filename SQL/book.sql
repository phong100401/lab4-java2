CREATE DATABASE IF NOT EXISTS ebookshop; USE
ebookshop;
DROP TABLE IF EXISTS
books;
CREATE TABLE books(
id INT,
title VARCHAR(50),
author VARCHAR(50),
price FLOAT,
qty INT,
PRIMARY KEY(id)
); INSERT INTO books
VALUES(
1001,
'Java for Dummies',
'Dang Kim Thi',
11.11,
11
);
INSERT INTO books
VALUES(
1002,
'More Java for Dummies',
'CodeLean VN',
22.22,
22
);
INSERT INTO books
VALUES(
1003,
'More Java for more dummies',
'Mohammad Ali',
33.33,
33
);
INSERT INTO books
VALUES(
1004,
'A Cup for Java',
'Kumar',
44.44,
44
);
INSERT INTO books
VALUES(
1005,
'A Teaspoon of Java',
'Kevin Jones',
55.55,
55
);
SELECT * FROM books;