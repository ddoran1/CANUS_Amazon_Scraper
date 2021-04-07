package Amazon_Scraper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;

public class WebDriverThread extends Thread{
	public enum Country {US, CAN}
	private String key = "webdriver.chrome.driver";
	private String uri;
	private Country country;
	private ArrayList<String> product_link_list = new ArrayList<String>();
	private ArrayList<Product> product_list = new ArrayList<Product>();

    public WebDriverThread(String uriIn, Country natIn) {
        this.uri = uriIn;
        this.country = natIn;
    }
    
    public String getURI() {
    	return uri;
    }
    
    public Country getCountry() {
    	return country;
    }
    
    public void run() {
    	try {
    		//WEBDRIVER AND DB SETUP
    		System.setProperty(key, app_properties.loadSetting(key));
    		WebDriver driver = new ChromeDriver();
    		driver.get(this.getURI());
	
    		//ENTER KEY WORDS INTO SEARCH BAR
    		utility.search(driver, "pink gold auto shut off coffee mug warmer with wifi");
	
    		//SCAN THE PAGES AND COLLECT PRODUCT LINKS
    		utility.pageScanningEngine(driver, product_link_list);
    		System.out.println(product_link_list.size() + " items scanned:");
	
    		//VISIT EACH LINK AND PARSE OUT ALL THE PRODUCT INFORMATION
    		ProductParser.productParsingEngine(driver, product_link_list, product_list, this.getCountry());
		
    		//CLOSE DRIVER
    		System.out.println("CLOSING DRIVER FOR " + this.getCountry().toString() + "...");
    		driver.quit();
    		System.out.println(this.getCountry().toString() + "DRIVER CLOSED");					
    	}
    	catch(Exception e) {
    		e.printStackTrace();	
    	}
    }  
}