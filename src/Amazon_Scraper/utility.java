package Amazon_Scraper;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class utility {

	//UPDATE: Throw exception when no Next button or no more pages
	public static void paging(WebDriver driver) {
		try {
			WebElement next = driver.findElement(By.className("a-last"));
			next.click();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void classViewer(WebDriver driver, String class_name) {
		List<WebElement> list = driver.findElements(new By.ByClassName(class_name));	
		Iterator itr = list.iterator();
		while(itr.hasNext()) 
			System.out.println(itr.next());
	}
	
}
