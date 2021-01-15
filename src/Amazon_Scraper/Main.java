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
		try {
		//WEBDRIVER SETUP
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//WebDriver driver = null; Sorry, we just need to make sure you're not a robot. For best results, please make sure your browser is accepting cookies.
		driver.get("https://www.amazon.ca/");
		//driver.get("https://www.amazon.ca/gp/slredirect/picassoRedirect.html/ref=pa_sp_atf_aps_sr_pg1_1?ie=UTF8&adId=A08847781PNULUHZEY1ZP&url=%2FAnbenser-Lightweight-Athletic-Non-Slip-7-15%25EF%25BC%2588Grey%2Fdp%2FB08GKNB1K9%2Fref%3Dsr_1_4_sspa%3Fdchild%3D1%26keywords%3Dmens%2Bshoes%26qid%3D1610432390%26sr%3D8-4-spons%26psc%3D1&qualifier=1610432390&id=6842051030326868&widgetName=sp_atf");
		ArrayList<String> test_list = new ArrayList<String>();
		test_list.add("https://www.amazon.ca/gp/slredirect/picassoRedirect.html/ref=pa_sp_atf_aps_sr_pg1_1?ie=UTF8&adId=A08847781PNULUHZEY1ZP&url=%2FAnbenser-Lightweight-Athletic-Non-Slip-7-15%25EF%25BC%2588Grey%2Fdp%2FB08GKNB1K9%2Fref%3Dsr_1_4_sspa%3Fdchild%3D1%26keywords%3Dmens%2Bshoes%26qid%3D1610432390%26sr%3D8-4-spons%26psc%3D1&qualifier=1610432390&id=6842051030326868&widgetName=sp_atf");
		test_list.add("https://www.amazon.ca/gp/slredirect/picassoRedirect.html/ref=pa_sp_atf_aps_sr_pg1_1?ie=UTF8&adId=A044170348EAUIRBN3YV&url=%2FFUJEAK-Breathable-Athletic-Comfortable-Lightweight%2Fdp%2FB08HWXDZYL%2Fref%3Dsr_1_2_sspa%3Fdchild%3D1%26keywords%3Dmens%2Bshoes%26qid%3D1610497293%26sr%3D8-2-spons%26psc%3D1&qualifier=1610497293&id=8598974689049394&widgetName=sp_atf");
		ProductParser.productParsingEngine(driver, test_list);
		
		//ENTER KEY WORDS INTO SEARCH BAR
		//utility.search(driver, "mens shoes");
		
		//SCAN THE PAGES AND COLLECT PRODUCT LINKS
		//list = utility.pageScanningEngine(driver);
		//System.out.println(list.size() + " items scanned\n");
		
		//VISIT EACH LINK AND PARSE OUT ALL THE PRODUCT INFORMATION
		//ProductParser.productParsingEngine(driver, list);
		
		//CLOSE DRIVER
		//System.out.println("CLOSING DRIVER...\n");
		//driver.close();
		//System.out.println("DRIVER CLOSED!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
