/*
NEED TO SORT OUT HOW TO STORE PRICE BETWEEN FIXED AND RANGE
*/
CREATE TABLE IF NOT EXISTS CAN_Product(
	primary_key INTEGER PRIMARY KEY,
	name TEXT NOT NULL, 
	brand TEXT NOT NULL,
	link TEXT NOT NULL,
	price REAL,
	num_of_ratings REAL,
	rating REAL,
	);