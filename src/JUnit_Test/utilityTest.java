package JUnit_Test;

import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Amazon_Scraper.app_properties;
import Amazon_Scraper.utility;
import Amazon_Scraper.WebDriverThread.Country;

class utilityTest {
	private String key = "webdriver.chrome.driver";
	private WebDriver driver = null;
	private ArrayList<String> product_link_list = new ArrayList<String>();

	@BeforeEach                                         
	public void setUp() throws Exception {
		System.setProperty(key, app_properties.loadSetting(key));
		driver = new ChromeDriver();
		driver.get(app_properties.loadSetting(Country.TEST));
	}
	
	@Test
	@DisplayName("Verifying pageScanningEngine(...)")   
	public void test_getConnection() {
		utility.pageScanningEngine(driver, product_link_list); 
		assertNotNull("Issue with pageScanningEngine(...)", product_link_list); 
	}

}
