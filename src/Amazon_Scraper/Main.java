package Amazon_Scraper;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * This is the driver class that will initiate the WebDriver and be the main managing engine
 * 
 * @author David Doran
 */
public class Main {
	public static ArrayList<String> list = null;

	public static void main(String[] args) {
		//INITIATE SEARCH OF DESIRED ITEMS
		//**********  TO DO  **************
		//** DEVELOP GUI FOR FUTURE INPUT
		//** ADD DB FOR PARTITIONED DATA
		//*********************************
		
		//WEBDRIVER SETUP
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.ca/");
		
		//ENTER KEY WORDS INTO SEARCH BAR
		search(driver, "mens shoes");
		
		//SCAN THE PAGES AND COLLECT PRODUCT LINKS
		list = pageScanningEngine(driver);
		listViewer(list);
		
		//VISIT EACH LINK AND PARSE OUT ALL THE PRODUCT INFORMATION
		ProductParser.productParsingEngine(driver, list);
		
		//CLOSE DRIVER
		driver.close();
	}
	
	/*
	 * 
	 */
	public static ArrayList<String> pageScanningEngine(WebDriver driver){
		ArrayList<String> list = new ArrayList<String>();
		boolean flag = true;
		int page = 1;
		try {
			while(flag) {
				System.out.println("Scanning page.........." + page);
			
				utility.linkParser(driver, list);
			
				flag = utility.paging(driver);
				page++;
			}
			
			System.out.println("\nEnd of paging\n\n");
			return list;
			
		} catch(Exception e) {
			System.out.println("\nEnd of paging\n\n");
			//e.printStackTrace();
			return list;
		}
	}
	
	/*
	 * 
	 */
	public static void listViewer(ArrayList<String> list) {
		for(int i = 0; i < list.size(); i++) 
			System.out.println(list.get(i));
	}
	
	public static void search(WebDriver driver, String input) {
		WebElement searchBar = driver.findElement(By.id("twotabsearchtextbox"));
		WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
		searchBar.sendKeys(input);
		searchButton.click();
	}

}
