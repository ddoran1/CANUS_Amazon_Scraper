CREATE TABLE IF NOT EXISTS canus_product_table(
	primary_key INTEGER PRIMARY KEY,
	name TEXT NOT NULL, 
	brand_id INTEGER,
	link TEXT NOT NULL,
	floor_price REAL NOT NULL,
	ceiling_price REAL,
	num_of_ratings INTEGER,
	rating REAL,
	FOREIGN KEY (brand_id)
       REFERENCES brand (brand_id) 
);