package Amazon_Scraper;

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
		
		boolean flag = true;
		int key = 2;
		//For whatever reason, the cell's 'cel_widget_id' are not perfectly sequential so the buffer will stand 
		//to keep the engine running until better means are found
		int buffer = 0;  
		while(flag) {
			try {
				String widget = driver.findElement(By.xpath("//div/div[@class=\"sg-col-inner\"]/span[@cel_widget_id=\"MAIN-SEARCH_RESULTS-"+ key +"\"]//a[@class=\"a-link-normal a-text-normal\"]")).getAttribute("href");
				System.out.println(widget + "\n");		
				key++;
			} catch(NoSuchElementException e) {
				System.out.println("WIDGET " + key + " NOT FOUND");
				key++;
				buffer++;
				
				if(buffer == 10)
					flag = false;
			}
		}
		
		utility.paging(driver);
		
		
		//*******************************************************
		//************  SEARCH RESULT IDENTIFIERS  **************
		//*******************************************************
		// Widget top-level:  data-component-type="s-search-result" data-index="2"   -->> Note: starts at 2 and iterates down from there
		// Widget mid-level:  cel_widget_id="MAIN-SEARCH_RESULTS-2"  -->> Note: starts at 2 and iterates down from there
		// href bottom-level: class="a-link-normal a-text-normal" 
		// Select the widget and select the link via class??

		
	}

}
