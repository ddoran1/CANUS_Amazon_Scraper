package JUnit_Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class JUnitUtility {
	private static String path = "src//JUnit_Test//product_link_list.txt";
	
	public static void main(String[] args) {
		//LOAD TEST DATA
		saveLinkToFile("");
		saveLinkToFile("");
		saveLinkToFile("");
		saveLinkToFile("");
		saveLinkToFile("");
		saveLinkToFile("");
		saveLinkToFile("");
		saveLinkToFile("");
		saveLinkToFile("");
		saveLinkToFile("");
		
		ArrayList<String> list = loadProductLinkFileToArrayList();
		for(int i = 0; i < list.size(); i++)
			System.out.println(list.get(i));
	}
	
	public static void saveLinkToFile(String link) {
	    try {
		    FileWriter fw = new FileWriter(new File(path));
		    fw.write(link + "\n");	   	    
	    	fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveLinksToFile(ArrayList<String> product_links) {
	    try {
		    FileWriter fw = new FileWriter(new File(path));
		    
			for(int i = 0; i < product_links.size(); i++)
				fw.write(product_links.get(i) + "\n");

	    	fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> loadProductLinkFileToArrayList() {
		ArrayList<String> product_link_list = new ArrayList<String>();
		try {
		      Scanner sc = new Scanner(new File(path));
		      while (sc.hasNextLine()) 
		    	  product_link_list.add(sc.nextLine());
		      
		      sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Exception: file not found!");
		}  catch (Exception e) {
			e.printStackTrace();
		}
		  
		 return product_link_list;
	}
}
