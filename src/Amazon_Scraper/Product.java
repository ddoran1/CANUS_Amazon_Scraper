package Amazon_Scraper;

/*
 * This class defines a Product from the data provided from the product page
 * 
 * @author David Doran
 */

public class Product {
	
	private String link;
	private String name;
	private String brand;
	private String floor_price;
	private String ceiling_price;
	private String rating;
	private String num_of_ratings;
	
	
	public Product(String linkIn, String nameIn, String brandIn, String floor_priceIn, String ceiling_priceIn, String ratingIn, String num_of_ratingsIn) {
		link = linkIn;
		name = nameIn;
		floor_price = floor_priceIn;
		ceiling_price = ceiling_priceIn;
		rating = ratingIn;
		num_of_ratings = num_of_ratingsIn;
	}
	
	public Product() {
		link = "";
		name = "";
		brand = "";
		floor_price = "";
		ceiling_price = "";
		rating = "";
		num_of_ratings = "";
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public String getName() {
		return name;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getBrand() {
		return brand;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFloorPrice() {
		return floor_price;
	}


	public void setFloorPrice(String floor_price) {
		this.floor_price = floor_price;
	}
	
	public String getCeilingPrice() {
		return floor_price;
	}


	public void setCeilingPrice(String ceiling_price) {
		this.ceiling_price = ceiling_price;
	}


	public String getRating() {
		return rating;
	}


	public void setRating(String rating) {
		this.rating = rating;
	}


	public String getNum_of_ratings() {
		return num_of_ratings;
	}


	public void setNum_of_ratings(String num_of_ratings) {
		this.num_of_ratings = num_of_ratings;
	}
	
	public String toString() {
		System.out.println("\tname: \t\t" + name);
		System.out.println("\tbrand: \t\t" + brand);
		System.out.println("\tlink: \t\t" + link);
		System.out.println("\tfloor_price: \t\t" + floor_price);
		System.out.println("\tceiling_price: \t\t" + ceiling_price);
		System.out.println("\tnum_of_ratings: " + num_of_ratings);
		System.out.println("\trating: \t" + rating);
		
		return "";
	}

}
