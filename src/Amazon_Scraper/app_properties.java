package Amazon_Scraper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class app_properties {
	
	/*
	 * 
	 */
	public static String loadSetting(String key) {
		String value = "";
		
		try {
			Properties loadProps = new Properties();
			loadProps.loadFromXML(new FileInputStream(new File("src//Amazon_Scraper//settings.xml")));
			value = loadProps.getProperty(key);
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("Exception: file not found!");
		} catch (IOException e) {
			e.printStackTrace();
		}  catch (Exception e) {
			e.printStackTrace();
		}
		  
		 return value;
	}
	
	/*
	 * 
	 * Note: need to load all previously held properties else they are all overwritten
	 */
	public static void saveSetting(String key, String value) {

	    try {
		    Properties new_property = new Properties();
		    new_property.loadFromXML(new FileInputStream(new File("src//Amazon_Scraper//settings.xml")));
		    new_property.setProperty(key, value);
		    new_property.storeToXML(new FileOutputStream(new File("src//Amazon_Scraper//settings.xml")), "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
