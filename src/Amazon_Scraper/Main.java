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
	public static ArrayList<Product> product_list = null;
	public static SQLConnection conn = null;

	public static void main(String[] args) {
		//INITIATE SEARCH OF DESIRED ITEMS
		//**********  TO DO  **************
		//** DEVELOP GUI FOR FUTURE INPUT
		//***** input files from user
		//***** input search bar
		//** ADD DB FOR PARTITIONED DATA
		//*********************************
		
		try {
			//WEBDRIVER AND DB SETUP
			System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.get("https://www.amazon.ca/");
			conn = new SQLConnection();
		
			//ENTER KEY WORDS INTO SEARCH BAR
			utility.search(driver, "mens shoes");
		
			//SCAN THE PAGES AND COLLECT PRODUCT LINKS
			list = utility.pageScanningEngine(driver);
			System.out.println(list.size() + " items scanned\n");
		
			//VISIT EACH LINK AND PARSE OUT ALL THE PRODUCT INFORMATION
			product_list = ProductParser.productParsingEngine(driver, list);
			
//			//TEST SUITE
//			conn = new SQLConnection();
//			utility.test(driver);
//			utility.failure_Table_Test(driver, conn);
//			conn.test_Failure();
			
			//VIEW DB INPUT
			conn.view_CAN_Product_Table();
			conn.view_Failure_Table();

			//CLOSE DRIVER
			System.out.println("CLOSING DRIVER...\n");
			driver.quit();
			System.out.println("DRIVER CLOSED");
			
		}catch(Exception e) {
			e.printStackTrace();	
		}
	}

}
