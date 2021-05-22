package JUnit_Test;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Amazon_Scraper.Product;
import Amazon_Scraper.ProductParser;
import Amazon_Scraper.app_properties;
import Amazon_Scraper.WebDriverThread.Country;

class ProductParserTest {
	private String key = "webdriver.chrome.driver";
	private WebDriver driver = null;
	private ArrayList<Product> product_list = new ArrayList<Product>();
	private ArrayList<String> product_link_list = null;

	@BeforeEach                                         
	public void setUp() throws Exception {
		System.setProperty(key, app_properties.loadSetting(key));
		driver = new ChromeDriver();
		driver.get(app_properties.loadSetting(Country.CAN.toString()));
	}
	
	@Test
	@DisplayName("Verifying productParsingEngine(...)")   
	public void test_productParsingEngine() throws InterruptedException, IOException {
		product_link_list = JUnitUtility.loadProductLinkFileToArrayList();
		ProductParser.productParsingEngine(driver, product_link_list, product_list, Country.TEST); 
		assertNotNull("pageScanningEngine(...) okay", product_list); 
	}

}
