package Amazon_Scraper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * This class is a collection of methods for general purpose or testing purposes
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
				System.out.println("Waiting for page to load.............");
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
	
}
