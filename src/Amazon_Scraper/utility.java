package Amazon_Scraper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class utility {
	
	private enum Button {PRESENT, NOT_PRESENT}

	//UPDATE: Throw exception when no Next button or no more pages
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
	
	//For whatever reason, the cell's 'cel_widget_id' are not perfectly sequential so the buffer will stand 
	//to keep the engine running until better means are found
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
				
				if(buffer == 10)
					flag = false;
			}
		}
		return list;
	}
	
	public static void classViewer(WebDriver driver, String class_name) {
		List<WebElement> list = driver.findElements(new By.ByClassName(class_name));	
		Iterator itr = list.iterator();
		while(itr.hasNext()) 
			System.out.println(itr.next());
	}
	
}
