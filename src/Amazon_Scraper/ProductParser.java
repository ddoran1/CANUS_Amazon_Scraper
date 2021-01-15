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
	public static Exception NoSuchElementException;
	
	/*
	 * 
	 */
	public static void productParsingEngine(WebDriver driver, ArrayList<String> list) throws InterruptedException, IOException {
		Product product = null;
		
		for(int i = 0; i < list.size(); i++) {
			driver.get(list.get(i));
			System.out.println("Viewing item " + (i+1) + " @ " + list.get(i));
			//wait(driver);
			product = productDataParser(driver, list.get(i));
			if(product == null) {
				while(product == null) {
					//wait(driver);
					product = productDataParser(driver, list.get(i));
				}
			}else {		
				product_list.add(product);	
			}
		}
	}
	
	public static Product productDataParser(WebDriver driver, String link) throws InterruptedException, IOException {
		Product product = new Product();
		Thread.sleep(300);
		Document doc = Jsoup.parse(driver.getPageSource(), driver.getCurrentUrl());
		
		//LINK
		product.setLink(link);
		
		//BRAND
		Element brand = doc.getElementById("bylineInfo");
		System.out.println("\t" + brand.html());
		product.setName(brand.html());

		//NAME
		Elements name = doc.getElementById("titleSection").getElementsByTag("span");
		System.out.println("\t" + name.text());
		product.setName(name.text());
		
	
		//PRICE
		//******************************************************************
		//** NOTE: If the product does not have one unified price, but rather has a range instead, returns null pointer exception
		//******************************************************************
		try {
			Elements price = doc.getElementById("priceInsideBuyBox_feature_div").getElementsByTag("span");
			System.out.println("\t" + price.text());
			product.setPrice(price.text());
		} 
		//If the product does not have one unified price but rather has a range instead
		catch(NullPointerException e){
			Elements price = doc.getElementById("unifiedPrice_feature_div").getElementsByTag("span");
			System.out.println("\tPRICE RANGE:  " + price.text());
			product.setPrice(price.text());
		}

		//NUMBER OF RATINGS AND PRODUCT RATING
		//******************************************************************
		//** NOTE: if no reviews then this returns a null pointer exception
		//******************************************************************
		try {
			//NUMBER OF RATINGS
			Element num_of_ratings = doc.getElementById("acrCustomerReviewText");
			System.out.println("\t" + num_of_ratings.html());
			product.setRating(num_of_ratings.html());
		
			//RATING
			Elements rating = doc.getElementsByClass("a-icon-alt");
			System.out.println("\t" + rating.get(0).html());
			product.setRating(rating.html());
		} 
		catch(NullPointerException e) {
			System.out.println("\tPRODUCT NOT YET RATED");
		}

	
		return product;
	}
	
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
