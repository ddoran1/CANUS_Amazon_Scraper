package Amazon_Scraper;

import java.io.IOException;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * This class will be used for the purpose of parsing data out of the collection of product links
 * collected in the initial pass through 
 * 
 * @author David Doran
 */
public class ProductParser {
	
	public static ArrayList<Product> product_list = new ArrayList<Product>();
	public static ArrayList<String> error_list = new ArrayList<String>();
	public static Exception NoSuchElementException;
	
	/*
	 * Cycles though given list of links and parses out product data into an ArrayList, also complies problematic links for future fixes
	 * @param WebDriver - @param WebDriver - The WebDriver object for getting the HTML from each product page
	 * @param ArrayList<String> - the list of links to be scraped and parsed
	 */
	public static ArrayList<Product> productParsingEngine(WebDriver driver, ArrayList<String> list) throws InterruptedException, IOException {
		Product product = null;
		
		for(int i = 0; i < list.size(); i++) {
			driver.get(list.get(i));
			System.out.println("Viewing item " + (i+1) + " @ " + list.get(i));
			product = productDataParser(driver, list.get(i));
			if(product == null)
				error_list.add(list.get(i));
			else
				product_list.add(product);
		}
		return product_list;
	}
	
	/*
	 * Parses through HTML to acquire product data
	 * @param WebDriver - @param WebDriver - The WebDriver object for getting the HTML from each product page
	 * @param String - specific product link
	 */
	public static Product productDataParser(WebDriver driver, String link) throws InterruptedException, IOException {
		try {
			Product product = new Product();
			Document doc = Jsoup.parse(driver.getPageSource(), driver.getCurrentUrl());
		
			//LINK
			product.setLink(link);
		
			//BRAND
			Element brand = doc.getElementById("bylineInfo");
			String parsed_brand = parseBrand(brand.html());
			System.out.println("\t" + parsed_brand);
			product.setName(parsed_brand);

			//NAME
			Elements name = doc.getElementById("titleSection").getElementsByTag("span");
			System.out.println("\t" + name.text());
			product.setName(name.text());
		
	
			//PRICE
			//******************************************************************
			//** NOTE: If the product does not have one unified price, but rather has a range instead, returns null pointer exception
			//******************************************************************
			String parsed_price = "";
			try {
				Elements price = doc.getElementById("priceInsideBuyBox_feature_div").getElementsByTag("span");
				parsed_price = parsePrice(price.text());
				System.out.println("\t" + parsed_price);
				product.setPrice(parsed_price);
				
			} catch(NullPointerException e){
				Elements price = doc.getElementById("unifiedPrice_feature_div").getElementsByTag("span");
				parsed_price = parsePrice(price.text());
				System.out.println("\tPRICE RANGE:  " + parsed_price);
				product.setPrice(parsed_price);
			}

			//NUMBER OF RATINGS AND PRODUCT RATING
			//******************************************************************
			//** NOTE: if no reviews then this returns a null pointer exception
			//******************************************************************
			try {
				//NUMBER OF RATINGS
				Element num_of_ratings = doc.getElementById("acrCustomerReviewText");
				String parsed_num_of_ratings = parseNumOfRatings(num_of_ratings.html());
				System.out.println("\t" + parsed_num_of_ratings);
				product.setRating(parsed_num_of_ratings);
		
				//RATING
				Elements rating = doc.getElementsByClass("a-icon-alt");
				String parsed_rating = parseRating(rating.get(0).html());
				System.out.println("\t" + parsed_rating);
				product.setRating(parsed_rating);
				
			} catch(NullPointerException e) {
				System.out.println("\tPRODUCT NOT YET RATED");
			}

			return product;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String parseBrand(String raw) {
		String brand = "";
		
		if(raw.contains("Visit")) 
			brand = raw.substring(raw.indexOf("the") + 4, raw.indexOf("Store") - 1);
		else if(raw.contains("Brand:"))
			brand = raw.substring(raw.indexOf("Brand:") + 7);
		else
			return raw;
		
		return brand;
	}
	
	public static String parsePrice(String raw) {
		String price = "";
		
		if(raw.contains("&")) 
			price = raw.substring(0 ,raw.indexOf("&") - 1);
		else if(raw.contains("CDN$"))
			price = raw.substring(0, raw.length()-1);
		else
			return raw;
		
		return price;
	}
	
	public static String parseRating(String raw) {
		String rating = "";
		
		rating = raw.substring(0, raw.indexOf("out") - 1);
		
		return rating;
	}
	
	public static String parseNumOfRatings(String raw) {
		String num_of_rating = "";
		
		num_of_rating = raw.substring(0, raw.indexOf("ratings") - 1);
		
		return num_of_rating;
	}
	
	
	
	//********************************************************************
	//**  WAIT AND CHECK METHODS: USED FOR CHECKING IF HTML WAS LOADED  **
	//********************************************************************
	
	
	
	public static void wait(WebDriver driver) {
		boolean add_button = false;
		
		try {
			while(!add_button){
				
				if(add_button = check(driver))
					break;
				
				Thread.sleep(300);
				System.out.println("Waiting for cart to load.............");
			}			 
		}catch(InterruptedException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void waitForCSS(WebDriver driver, String css_selector) {
		boolean add_button = false;
		
		try {
			while(!add_button){
				
				if(add_button = checkCSS(driver, css_selector))
					break;
				
				Thread.sleep(300);
				System.out.println("Waiting for HTML component to load.............");
			}			 
		}catch(InterruptedException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static boolean checkCSS(WebDriver driver, String css_selector) {
		try {
            @SuppressWarnings("unused")
			WebElement add_button = driver.findElement(By.cssSelector(css_selector));
                                             			
            return true;			
		}catch(NoSuchElementException e) {
			return false;
		}
	}
	
	private static boolean check(WebDriver driver) {
		try {
            @SuppressWarnings("unused")
			WebElement add_button = driver.findElement(By.id("addToCart_feature_div"));
                                             			
            return true;			
		}catch(NoSuchElementException e) {
			return false;
		}
	}

}
