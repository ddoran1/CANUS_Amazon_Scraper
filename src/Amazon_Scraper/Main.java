package Amazon_Scraper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Amazon_Scraper.WebDriverThread.Country;


/*
 * This is the driver class that will serve as be the main engine. The main() 
 * will initiate the WebDriver threads from WebDriverThread.java and then
 * proceed to call on the python scripts that  will process the raw data
 * 
 * @author David Doran
 */
public class Main {
	public static ArrayList<String> list = null;
	public static ArrayList<Product> product_list = null;
	public static SQLConnection conn = null;

	public static void main(String[] args){
		
		/*************************************
		 *****     WebDriver Threads     *****
		 *************************************/
		try {			
			conn = new SQLConnection();
			WebDriverThread can_thread = new WebDriverThread(app_properties.loadSetting(Country.CAN.toString()), Country.CAN);
			WebDriverThread us_thread = new WebDriverThread(app_properties.loadSetting(Country.US.toString()), Country.US);
			can_thread.start();
			us_thread.start();
	
			can_thread.join();
			us_thread.join();			
		}
		catch(InterruptedException e) {
			e.getStackTrace();
			System.exit(0);
		}
		catch(Exception e) {
			e.getStackTrace();
			System.exit(0);
		}
		
		
		/*************************************
		 ******     Python Scripts      ******
		 *************************************/
		try {
			System.out.println("EXPORTING CAN PRODUCT ENTRIES TO FILE...");
			Process p = Runtime.getRuntime().exec("python ./src/Python_Scripts/gold_miner.py");
			BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			String ret = output.readLine();
			System.out.println(ret);
			String line = "";
			while ((line = error.readLine()) != null) {
	            System.out.println(line);
	        }
			
			error.close();
			output.close();
			
			System.out.println("SYSTEM EXIT CODE: " + p.exitValue());
			
		}
		catch(Exception e) {
			System.out.println("Python script:  FAILED TO EXECUTE\n");
			e.printStackTrace();
		}
	}

}
