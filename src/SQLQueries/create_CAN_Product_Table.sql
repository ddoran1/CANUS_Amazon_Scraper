/*
NEED TO SORT OUT HOW TO STORE PRICE BETWEEN FIXED AND RANGE
*/
CREATE TABLE IF NOT EXISTS CAN_Product(
	primary_key INTEGER PRIMARY KEY,
	name TEXT NOT NULL, 
	brand_id INTEGER,
	link TEXT NOT NULL,
	price TEXT,
	num_of_ratings VARCHAR(7),
	rating VARCHAR(3),
	FOREIGN KEY (brand_id)
       REFERENCES brand (brand_id) 
	);