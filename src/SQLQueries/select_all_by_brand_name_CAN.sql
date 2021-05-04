SELECT * FROM CAN_Product 
	LEFT JOIN brand 
		ON CAN_Product.brand_id = brand.brand_id
			WHERE brand.brand_name = '$brand_name'
/*
select * from CAN_Product
	WHERE brand_id = (SELECT brand_id FROM brand WHERE brand_name = '$brand_name')
	*/