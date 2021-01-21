package Amazon_Scraper;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * This class is a collection of methods for general purpose in Main.java; including testing and data viewing.
 * 
 * NOTE:  FUTURE ITEREATION COULD USE JSOUP AND DO AWAY WITH THE ARBITRARY WAIT TIMES
 * 
 * @author David Doran
 */
public class utility {
	
	private enum Button {PRESENT, NOT_PRESENT}

	/*
	 * This method takes in the present WebDriver and searches for the Next button at the bottom
	 * of the results page.  The method looks for the next button and returns a boolean as to whether 
	 * it has been found or not while giving the page time to load.
	 * @param WebDriver - The WebDriver object, after the results page has been reached
	 * @return The boolean value of whether or not the Next button has been found
	 * 
	 * UPDATE: Throw exception when no Next button or no more pages
	 */
	public static boolean paging(WebDriver driver) {
		boolean next_button = false;
		boolean no_button = false;
		
		try {
			while(!next_button && !no_button){
				
				if(next_button = check(Button.PRESENT, driver))
					break;
				if(no_button = check(Button.NOT_PRESENT, driver))
					break;
				
				Thread.sleep(300);
				//System.out.println("Waiting for page to load.............");
			}
			
			if(next_button == true)
				return true;
			else if(no_button == true)
				return false;
			else
				return false;
			
		} catch(NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}catch(InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * A private method that checks for one of tow indicators: the Next button has finally loaded  
	 * and is active, or the Next button has loaded but has been grayed out.
	 * @param Button - enum used to check between the grayed out Next button and an active one
	 * @param WebDriver - The WebDriver object, after the results page has been reached
	 * @return The boolean value of whether or not the Next button is active or grayed out
	 */
	private static boolean check(Button button, WebDriver driver) {
		try {
			switch (button) {
            	case PRESENT:
            		WebElement next = driver.findElement(By.xpath("//div/span[@class=\"celwidget slot=MAIN template=PAGINATION widgetId=pagination-button\"]"
						+ "//ul[@class=\"a-pagination\"]//li[@class=\"a-last\"]"));
            		next.click();
            		break;
                    
            	case NOT_PRESENT:
            		WebElement no_next = driver.findElement(By.xpath("//div/span[@class=\"celwidget slot=MAIN template=PAGINATION widgetId=pagination-button\"]"
						+ "//ul[@class=\"a-pagination\"]//li[@class=\"a-disabled a-last\"]"));
            		break;                   
			}
			
		return true;
			
		}catch(NoSuchElementException e) {
			return false;
		}
	}
	
	/*
	 * A method that obtains all the product links from a results page. Note: there is 
	 * an arbitrary buffering value as not all of the keys used to obtain the product links
	 * are perfectly sequential when rendered.
	 * @param WebDriver - The WebDriver object, after the results page has been reached
	 * @param ArrayList<String> - the list of all product links to be collected
	 */
	public static ArrayList<String> linkParser(WebDriver driver, ArrayList<String> list){
		boolean flag = true;
		int key = 2;
		int buffer = 0;  
		
		while(flag) {
			try {
				String product_link = driver.findElement(By.xpath("//div/div[@class=\"sg-col-inner\"]/span[@cel_widget_id=\"MAIN-SEARCH_RESULTS-"+ key +"\"]//a[@class=\"a-link-normal a-text-normal\"]")).getAttribute("href");
				list.add(product_link);
				key++;
			} catch(NoSuchElementException e) {
				key++;
				buffer++;
				
				if(buffer == 10)  //arbitrary buffering value
					flag = false;
			}
		}
		return list;
	}
	
	/*
	 * This method is the main engine that cycles through all the resulting pages after an 
	 * initial query has been entered into the Amazon search bar via the browser
	 * @param WebDriver - The WebDriver object, after initially instantiated in the Main.main
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
			System.out.println("\nEXCEPTION: End of paging\n\n");
			//e.printStackTrace();
			return list;
		}
	}
	
	/*
	 * Method for view the content of the list containing the collected links
	 * @param ArrayList<String> - List of parsed links
	 */
	public static void listViewer(ArrayList<String> list) {
		for(int i = 0; i < list.size(); i++) 
			System.out.println(list.get(i));
	}
	
	/*
	 * Used to enter keywords into the search bar and click the search button
	 * @param WebDriver - The WebDriver object, after the being brought to Amazon's main page
	 * @param String - the keywords to be queried
	 */
	public static void search(WebDriver driver, String input) {
		WebElement searchBar = driver.findElement(By.id("twotabsearchtextbox"));
		WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
		searchBar.sendKeys(input);
		searchButton.click();
	}
	
	/*
	 * A simple method for viewing the elements inside of a given class
	 * @param WebDriver - The WebDriver object, after the results page has been reached
	 * @param String - the given class name
	 */
	@SuppressWarnings("rawtypes")
	public static void classViewer(WebDriver driver, String class_name) {
		List<WebElement> list = driver.findElements(new By.ByClassName(class_name));	
		Iterator itr = list.iterator();
		while(itr.hasNext()) 
			System.out.println(itr.next());
	}
	
	/*
	 * A method for doing quick tests on the ProductParser.productParsingEngine(WebDriver, ArrayList<String>);
	 * @param WebDriver - The WebDriver object, after initially instantiated in the Main.main
	 */
	public static void test(WebDriver driver) throws InterruptedException, IOException {
		ArrayList<String> test_list = new ArrayList<String>();
		test_list.add("https://www.amazon.ca/gp/slredirect/picassoRedirect.html/ref=pa_sp_atf_aps_sr_pg1_1?ie=UTF8&adId=A08847781PNULUHZEY1ZP&url=%2FAnbenser-Lightweight-Athletic-Non-Slip-7-15%25EF%25BC%2588Grey%2Fdp%2FB08GKNB1K9%2Fref%3Dsr_1_4_sspa%3Fdchild%3D1%26keywords%3Dmens%2Bshoes%26qid%3D1610432390%26sr%3D8-4-spons%26psc%3D1&qualifier=1610432390&id=6842051030326868&widgetName=sp_atf");
		test_list.add("https://www.amazon.ca/gp/slredirect/picassoRedirect.html/ref=pa_sp_atf_aps_sr_pg1_1?ie=UTF8&adId=A044170348EAUIRBN3YV&url=%2FFUJEAK-Breathable-Athletic-Comfortable-Lightweight%2Fdp%2FB08HWXDZYL%2Fref%3Dsr_1_2_sspa%3Fdchild%3D1%26keywords%3Dmens%2Bshoes%26qid%3D1610497293%26sr%3D8-2-spons%26psc%3D1&qualifier=1610497293&id=8598974689049394&widgetName=sp_atf");
		test_list.add("https://www.amazon.ca/Mishansha-Womens-Waterproof-Outdoor-Walking/dp/B07VYLKVXL/ref=sr_1_17?dchild=1&keywords=mens+shoes&qid=1610682511&sr=8-17");
//		test_list.add("https://www.amazon.ca/Beita-Basketball-Fashion-Sneakers-Athletic/dp/B07VJLR6FW/ref=sr_1_31?dchild=1&keywords=mens+shoes&qid=1611248002&sr=8-31");
//		test_list.add("https://www.amazon.ca/Merrell-Mens-Jungle-Slip-Fudge/dp/B000G5XKJ2/ref=sr_1_30?dchild=1&keywords=mens+shoes&qid=1611248002&sr=8-30");
//		test_list.add("https://www.amazon.ca/Tommy-Hilfiger-Mens-PANDORA-Medium/dp/B014GNE354/ref=sr_1_28?dchild=1&keywords=mens+shoes&qid=1611248002&sr=8-28");
//		test_list.add("https://www.amazon.ca/Clarks-Cotrell-Oxfords-Tobacco-Leather/dp/B075R8PHDB/ref=sr_1_27?dchild=1&keywords=mens+shoes&qid=1611248002&sr=8-27");
//		test_list.add("https://www.amazon.ca/CLARKS-Mens-Geo-Lace-Oxford/dp/B079RJRQJM/ref=sr_1_63?dchild=1&keywords=mens+shoes&qid=1611248002&sr=8-63");
//		test_list.add("https://www.amazon.ca/DVS-Skateboard-Comanche-Black-Leather/dp/B08RMXFQYB/ref=sr_1_64?dchild=1&keywords=mens+shoes&qid=1611248002&sr=8-64");
//		test_list.add("https://www.amazon.ca/Skechers-Torque-Twist-Waterproof-Black/dp/B07QWQXG4P/ref=sr_1_65?dchild=1&keywords=mens+shoes&qid=1611248002&sr=8-65");
		test_list.add("https://www.amazon.ca/Clarks-Cotrell-Walk-Oxford-Tobacco/dp/B01ACRGV8K/ref=sr_1_67?dchild=1&keywords=mens+shoes&qid=1611248002&sr=8-67");
		ProductParser.productParsingEngine(driver, test_list);
	}
	
	public static void failure_Table_Test(WebDriver driver, SQLConnection conn) throws InterruptedException, IOException {
		ArrayList<String> list = conn.list_Failure_Table();
		ProductParser.productParsingEngine(driver, list);
	}
	
}
