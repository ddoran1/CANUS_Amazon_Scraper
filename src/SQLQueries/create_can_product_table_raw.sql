CREATE TABLE IF NOT EXISTS can_product_table_raw(
	primary_key INTEGER PRIMARY KEY,
	name TEXT NOT NULL, 
	brand TEXT NOT NULL,
	link TEXT NOT NULL,
	floor_price REAL NOT NULL,
	ceiling_price REAL,
	num_of_ratings VARCHAR(7),
	rating VARCHAR(3)
	);