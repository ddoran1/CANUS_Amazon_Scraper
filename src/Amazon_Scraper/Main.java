package Amazon_Scraper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.NoSuchElementException;
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
		
		
		ArrayList<String> list = new ArrayList();
		boolean flag = true;
		int page = 1;
		try {
			while(flag) {
				System.out.println("Scanning page.........." + page);
			
				utility.linkParser(driver, list);
			
				flag = utility.paging(driver);
				page++;
			}
		} catch(Exception e) {
			System.out.println("\nEnd of paging\n\n");
			e.printStackTrace();
		}
		
		
		for(int i = 0; i < list.size(); i++) 
			System.out.println(list.get(i));
			
		
		
		
		//*******************************************************
		//************  SEARCH RESULT IDENTIFIERS  **************
		//*******************************************************
		// Widget top-level:  data-component-type="s-search-result" data-index="2"   -->> Note: starts at 2 and iterates down from there
		// Widget mid-level:  cel_widget_id="MAIN-SEARCH_RESULTS-2"  -->> Note: starts at 2 and iterates down from there
		// href bottom-level: class="a-link-normal a-text-normal" 
		// Select the widget and select the link via class??

		
	}

}
