/*
Add recurrence rate
Add attribute (necessary, neutral, frivilous)

<<<***NOT TESTED***>>>
*/

CREATE TABLE IF NOT EXISTS CANAM_Product(
	primary_key INTEGER PRIMARY KEY,
	name TEXT NOT NULL, 
	brand TEXT NOT NULL,
	link TEXT NOT NULL,
	price REAL,
	num_of_ratings REAL,
	rating REAL,
	);