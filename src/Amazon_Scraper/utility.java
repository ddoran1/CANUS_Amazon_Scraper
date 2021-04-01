package Amazon_Scraper;
import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Amazon_Scraper.WebDriverThread.Country;

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
	public static void linkParser(WebDriver driver, ArrayList<String> product_link_list){
		boolean flag = true;
		int key = 2;
		int buffer = 0;  
		
		while(flag) {
			try {
				String product_link = driver.findElement(By.xpath("//div/div[@class=\"sg-col-inner\"]/span[@cel_widget_id=\"MAIN-SEARCH_RESULTS-"+ key +"\"]//a[@class=\"a-link-normal a-text-normal\"]")).getAttribute("href");
				product_link_list.add(product_link);
				key++;
			} catch(NoSuchElementException e) {
				key++;
				buffer++;
				
				if(buffer == 10)  //arbitrary buffering value
					flag = false;
			}
		}
	}
	
	/*
	 * This method is the main engine that cycles through all the resulting pages after an 
	 * initial query has been entered into the Amazon search bar via the browser
	 * @param WebDriver - The WebDriver object, after initially instantiated in the Main.main
	 */
	public static void pageScanningEngine(WebDriver driver, ArrayList<String> product_link_list){
		boolean flag = true;
		int page = 1;
		try {
			while(flag) {
				System.out.println("Scanning page.........." + page);
			
				utility.linkParser(driver, product_link_list);
			
				flag = utility.paging(driver);
				page++;
				break;
			}
			
			System.out.println("\nEnd of paging\n\n");
			
		} catch(Exception e) {
			System.out.println("\nEXCEPTION: End of paging\n\n");
			//e.printStackTrace();
		}
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
	
	public static void test(WebDriver driver, Country country) {
		ArrayList<String> product_link_list = new ArrayList<String>();
		ArrayList<Product> product_list = new ArrayList<Product>();
		
		product_link_list.add("");

		try {
			ProductParser.productParsingEngine(driver, product_link_list, product_list, country);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i =0; i < product_link_list.size(); i++)
			System.out.println(product_link_list.get(i));
		System.out.println("\n");
	}
}
