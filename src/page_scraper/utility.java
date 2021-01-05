package page_scraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class utility {

	public static void paging(WebDriver driver) {
		WebElement next = driver.findElement(By.id("twotabsearchtextbox"));
	}
	
}
