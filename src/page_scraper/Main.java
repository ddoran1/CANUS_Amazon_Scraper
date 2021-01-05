package page_scraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * This is the driver class that will initiate the WebDriver and be the main managing engine
 * 
 * @author David Doran
 */

public class Main {

	public static void main(String[] args) {
		
		//WEBDRIVER SETUP
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.ca/");
		
		//INITIATE SEARCH OF DESIRED ITEMS
		//**********  TO DO  **************
		//** DEVELOP GUI FOR FUTURE INPUT
		//** ADD DB FOR PARTITIONED DATA
		//*********************************
		WebElement searchBar = driver.findElement(By.id("twotabsearchtextbox"));
		WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
		
		searchBar.sendKeys("mens shoes");
		searchButton.click();
		
		System.out.println(driver.getCurrentUrl());
		System.out.println("\n");
		System.out.println(driver.findElements(By.xpath("//class=\"a-pagination\"")));
			
		
	}

}
