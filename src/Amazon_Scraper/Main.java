package Amazon_Scraper;

import java.util.ArrayList;
import Amazon_Scraper.WebDriverThread.Country;


/*
 * This is the driver class that will initiate the WebDriver and be the main managing engine
 * 
 * @author David Doran
 */
public class Main {
	public static ArrayList<String> list = null;
	public static ArrayList<Product> product_list = null;
	public static SQLConnection conn = null;

	public static void main(String[] args){
		try {			
			conn = new SQLConnection();
			WebDriverThread can_thread = new WebDriverThread(app_properties.loadSetting(Country.CAN.toString()), Country.CAN);
			WebDriverThread us_thread = new WebDriverThread(app_properties.loadSetting(Country.US.toString()), Country.US);
			can_thread.start();
			us_thread.start();
	
			can_thread.join();
			us_thread.join();
			
/****************************
 * RUN PYTHON SCRIPTS HERE
 ****************************/

		}
		catch(InterruptedException e) {
			e.getStackTrace();
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		
		
		//RUN PYTHON SCRIPTS
	}

}
