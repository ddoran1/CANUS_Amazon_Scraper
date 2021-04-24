INSERT INTO CAN_Product(name, brand_id, link, price, num_of_ratings, rating)
	VALUES('$name', (SELECT brand_id from brand WHERE brand_name='$brand'), '$link', '$price', '$num_of_ratings', '$rating');
	
/*
INSERT INTO bar (description, foo_id) VALUES
   ( 'testing',     (SELECT id from foo WHERE type='blue') );
*/