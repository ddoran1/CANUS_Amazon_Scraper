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
	private String price;
	private String rating;
	private String num_of_ratings;
	
	
	public Product(String linkIn, String nameIn, String brandIn, String priceIn, String ratingIn, String num_of_ratingsIn) {
		link = linkIn;
		name = nameIn;
		price = priceIn;
		rating = ratingIn;
		num_of_ratings = num_of_ratingsIn;
	}
	
	public Product() {
		link = "";
		name = "";
		brand = "";
		price = "";
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


	public String getPrice() {
		return price;
	}


	public void setPrice(String amount) {
		this.price = amount;
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
		System.out.println("\tprice: \t\t" + price);
		System.out.println("\tnum_of_ratings: " + num_of_ratings);
		System.out.println("\trating: \t" + rating);
		
		return "";
	}

}
