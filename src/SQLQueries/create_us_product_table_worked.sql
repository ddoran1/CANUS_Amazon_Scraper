CREATE TABLE IF NOT EXISTS us_product_table_worked(
	primary_key INTEGER PRIMARY KEY,
	name TEXT NOT NULL, 
	brand_id INTEGER,
	link TEXT NOT NULL,
	floor_price REAL NOT NULL,
	ceiling_price REAL,
	num_of_ratings VARCHAR(7),
	rating VARCHAR(3),
	FOREIGN KEY (brand_id)
       REFERENCES brand (brand_id) 
);