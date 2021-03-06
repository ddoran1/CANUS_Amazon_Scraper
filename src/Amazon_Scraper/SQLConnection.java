package Amazon_Scraper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLConnection {

	public SQLConnection() {
		try {
			init();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		Connection conn = null;
			
		try {
			String key = "org.sqlite.JDBC";
			Class.forName(key);
			conn = DriverManager.getConnection(app_properties.loadSetting(key));
			return conn;
		} catch ( Exception e ) {
			e.printStackTrace();
			return null;
		}
	}
		
	public void init() throws ClassNotFoundException {
		
		try {
			Connection conn = null;
			conn = getConnection();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		clearDB();
		
		init_US_Product_Table_Raw();
		init_CAN_Product_Table_Raw();
		init_Failure_Table();
		init_Brand_Table();
		
		init_CAN_Product_Table_Worked();
		init_US_Product_Table_Worked();
		init_CANUS_Product_Table();
		
	}
		
	public void init_CANUS_Product_Table() {
		Connection conn = null;
		String query = "";
		try {
			query = getQuery("src\\SQLQueries\\create_CANUS_Product_Table.sql");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			conn.close();
			System.out.println("CANUS TABLE CREATION SUCCESSFUL"); 
		}catch(Exception e) {
			System.out.println("CREATE TABLE FAILED" 
		+ "\n\tconn = " + conn
		+ "\n\tquery = " + query);
			
			e.printStackTrace();
		}
	}
	
	public void init_CAN_Product_Table_Raw() {
		Connection conn = null;
		String query = "";
		try {
			query = getQuery("src\\SQLQueries\\create_can_product_table_raw.sql");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			conn.close();
			System.out.println("CAN TABLE CREATION SUCCESSFUL"); 
		}catch(Exception e) {
			System.out.println("CAN CREATE TABLE FAILED" 
		+ "\n\tconn = " + conn
		+ "\n\tquery = " + query);
			
			e.printStackTrace();
		}
	}
	
	public void init_CAN_Product_Table_Worked() {
		Connection conn = null;
		String query = "";
		try {
			query = getQuery("src\\SQLQueries\\create_can_product_table_worked.sql");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			conn.close();
			System.out.println("CAN TABLE CREATION SUCCESSFUL"); 
		}catch(Exception e) {
			System.out.println("CAN CREATE TABLE FAILED" 
		+ "\n\tconn = " + conn
		+ "\n\tquery = " + query);
			
			e.printStackTrace();
		}
	}
	
	public void init_US_Product_Table_Raw() {
		Connection conn = null;
		String query = "";
		try {
			query = getQuery("src\\SQLQueries\\create_us_product_table_raw.sql");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			conn.close();
			System.out.println("US TABLE CREATION SUCCESSFUL"); 
		}catch(Exception e) {
			System.out.println("US CREATE TABLE FAILED" 
		+ "\n\tconn = " + conn
		+ "\n\tquery = " + query);
			
			e.printStackTrace();
		}
	}
	
	public void init_US_Product_Table_Worked() {
		Connection conn = null;
		String query = "";
		try {
			query = getQuery("src\\SQLQueries\\create_us_product_table_worked.sql");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			conn.close();
			System.out.println("US TABLE CREATION SUCCESSFUL"); 
		}catch(Exception e) {
			System.out.println("US CREATE TABLE FAILED" 
		+ "\n\tconn = " + conn
		+ "\n\tquery = " + query);
			
			e.printStackTrace();
		}
	}
	
	public void init_Brand_Table() {
		Connection conn = null;
		String query = "";
		try {
			query = getQuery("src\\SQLQueries\\create_brand_table.sql");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			conn.close();
			System.out.println("BRAND TABLE CREATION SUCCESSFUL"); 
		}catch(Exception e) {
			System.out.println("BRAND TABLE FAILED" 
		+ "\n\tconn = " + conn
		+ "\n\tquery = " + query);
			
			e.printStackTrace();
		}
	}
	
	public void init_Failure_Table() {
		Connection conn = null;
		String query = "";
		try {
			query = getQuery("src\\SQLQueries\\create_Failure_Table.sql");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			conn.close();
			System.out.println("FAILURE TABLE CREATION SUCCESSFUL"); 
		}catch(Exception e) {
			System.out.println("FAILURE CREATE TABLE FAILED" 
		+ "\n\tconn = " + conn
		+ "\n\tquery = " + query);
			
			e.printStackTrace();
		}
	}
	
	public void view_CANUS_Product_Table() {
		Connection conn = null;
		
		try {
			String query = getQuery("src\\SQLQueries\\view_CANUS_Product_Table.sql");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			System.out.println("CANUS_Product Table:");
			while(rs.next()){
					System.out.println("\tprimary_key = " + rs.getString("primary_key"));
					System.out.println("\tname  = " + rs.getString("name"));
					System.out.println("\tbrand = " + rs.getString("brand"));
					System.out.println("\tlink = " + rs.getString("link"));
					System.out.println("\tprice= " + rs.getString("price"));
					System.out.println("\tnum_of_ratings = " + rs.getString("num_of_ratings"));
					System.out.println("\trating = " + rs.getString("rating"));
					System.out.println("\n");
	        }
			conn.close();
		}catch(Exception e) {
			System.out.println("TABLE VIEW FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}
	
	public void view_CAN_Product_Table() {
		Connection conn = null;
		
		try {
			String query = getQuery("src\\SQLQueries\\view_all_CAN_Product.sql");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			System.out.println("\n\nCAN_Product Table:");
			int item_count = 0;
			while(rs.next()){
					System.out.println("\tprimary_key    = " + rs.getString("primary_key"));
					System.out.println("\tname           = " + rs.getString("name"));
					System.out.println("\tbrand_id       = " + rs.getString("brand_id"));
					System.out.println("\tlink           = " + rs.getString("link"));
					System.out.println("\tprice          = " + rs.getString("price"));
					System.out.println("\tnum_of_ratings = " + rs.getString("num_of_ratings"));
					System.out.println("\trating         = " + rs.getString("rating"));
					System.out.println("\n");
					item_count++;
	        }
			System.out.println("Number of items in CAN table: " + item_count);
			conn.close();
		}catch(Exception e) {
			System.out.println("TABLE VIEW FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}
	
	public void view_US_Product_Table() {
		Connection conn = null;
		
		try {
			String query = getQuery("src\\SQLQueries\\view_all_US_Product.sql");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			System.out.println("US_Product Table:");
			int item_count = 0;
			while(rs.next()){
					System.out.println("\tprimary_key = " + rs.getString("primary_key"));
					System.out.println("\tname  = " + rs.getString("name"));
					System.out.println("\tbrand = " + rs.getString("brand"));
					System.out.println("\tlink = " + rs.getString("link"));
					System.out.println("\tprice= " + rs.getString("price"));
					System.out.println("\tnum_of_ratings = " + rs.getString("num_of_ratings"));
					System.out.println("\trating = " + rs.getString("rating"));
					System.out.println("\n");
					item_count++;
	        }
			System.out.println("Number of items in US table: " + item_count);
			conn.close();
		}catch(Exception e) {
			System.out.println("TABLE VIEW FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}
	
	public void view_Brand_Table() {
		Connection conn = null;
		
		try {
			String query = getQuery("src\\SQLQueries\\view_all_brands.sql");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			System.out.println("Brand Table:");
			while(rs.next()){
				System.out.println("\n\tbrand_id = " + rs.getString("brand_id"));
				System.out.println("\tbrand_name = " + rs.getString("brand_name"));
	        }
			conn.close();
		}catch(Exception e) {
			System.out.println("TABLE VIEW FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}
	
	public void view_Failure_Table() {
		Connection conn = null;
		
		try {
			String query = getQuery("src\\SQLQueries\\view_all_Failures.sql");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			System.out.println("Failure Table:");
			while(rs.next()){
				System.out.println("\n\tprimary_key = " + rs.getString("primary_key"));
				System.out.println("\tlink = " + rs.getString("link"));
	        }
			conn.close();
		}catch(Exception e) {
			System.out.println("TABLE VIEW FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> list_Failure_Table(){
		Connection conn = null;
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			String query = getQuery("src\\SQLQueries\\view_all_Failures.sql");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			System.out.println("Failure Table:");
			while(rs.next())
				list.add(rs.getString("link"));
	        
			conn.close();
			return list;
		}catch(Exception e) {
			System.out.println("TABLE LOAD FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
			return null;
		}
	}
	
	public void clearDB() {
		Connection conn = null;
		Statement statement = null;
		String query = "";
				
		try {
			query = getQuery("src\\SQLQueries\\drop_CANUS_Product_Table.sql");
			conn = getConnection();
			statement = conn.createStatement();
			statement.executeUpdate(query);
			
			query = getQuery("src\\SQLQueries\\drop_CAN_Product_Table.sql");
			conn = getConnection();
			statement = conn.createStatement();
			statement.executeUpdate(query);
			
			query = getQuery("src\\SQLQueries\\drop_US_Product_Table.sql");
			conn = getConnection();
			statement = conn.createStatement();
			statement.executeUpdate(query);
			
			query = getQuery("src\\SQLQueries\\drop_brand_table.sql");
			conn = getConnection();
			statement = conn.createStatement();
			statement.executeUpdate(query);
			
			query = getQuery("src\\SQLQueries\\drop_Failure_Table.sql");
			conn = getConnection();
			statement = conn.createStatement();
			statement.executeUpdate(query);

			conn.close();
		}catch(Exception e) {
			System.out.println("DROP TABLE(S) FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}
	
	public void insert_CANUS_Product(String name, String brand, String link, String price, String num_of_ratings, String rating) {
		Connection conn = null;
		String query = "";
		
		try {
			query = getQuery("src\\SQLQueries\\insert_CANUS_Product.sql");
			query = query.replace("$name", name);
			query = query.replace("$brand", brand);
			query = query.replace("$link", link);
			query = query.replace("$price", price);
			query = query.replace("$num_of_ratings", num_of_ratings);
			query = query.replace("$rating", rating);
			
			conn = getConnection();

			System.out.println("INPUTTING ITEM @ " + conn.toString());
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);	
			conn.close();
		}catch(Exception e) {
			System.out.println("INSERT FAILED");
			System.out.println("\tname: \t\t" + name);
			System.out.println("\tbrand: \t\t" + brand);
			System.out.println("\tlink: \t\t" + link);
			System.out.println("\tprice: \t\t" + price);
			System.out.println("\tnum_of_ratings: " + num_of_ratings);
			System.out.println("\trating: \t" + rating);
			System.out.println("\nQUERY:\n" + query + "\n");
			insert_Failure(link);
			e.printStackTrace();
		}
	}
	
	public void insert_CAN_Product_Raw(String name, String brand, String link, String price, String num_of_ratings, String rating) throws SQLException {
		Connection conn = null;
		String query = "";
		
		System.out.println("INSERTING...\t" + name);
		
		try {
			query = getQuery("src\\SQLQueries\\insert_can_product_raw.sql");
			query = query.replace("$name", name);
			query = query.replace("$brand", brand);
			query = query.replace("$link", link);
			query = query.replace("$price", price);
			query = query.replace("$num_of_ratings", num_of_ratings);
			query = query.replace("$rating", rating);
						
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);	
		}catch(Exception e) {
			System.out.println("INSERT FAILED");
			System.out.println("\tname: \t\t" + name);
			System.out.println("\tbrand: \t\t" + brand);
			System.out.println("\tlink: \t\t" + link);
			System.out.println("\tprice: \t\t" + price);
			System.out.println("\tnum_of_ratings: " + num_of_ratings);
			System.out.println("\trating: \t" + rating);
			System.out.println("\nQUERY:\n" + query + "\n");
			insert_Failure(link);
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
	
	public void insert_US_Product_Raw(String name, String brand, String link, String price, String num_of_ratings, String rating) throws SQLException {
		Connection conn = null;
		String query = "";
		
		System.out.println("INSERT INTO US:  " + name);
		
		try {
			query = getQuery("src\\SQLQueries\\insert_us_product_table_raw.sql");
			query = query.replace("$name", name);
			query = query.replace("$brand", brand);
			query = query.replace("$link", link);
			query = query.replace("$price", price);
			query = query.replace("$num_of_ratings", num_of_ratings);
			query = query.replace("$rating", rating);
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);	
		}catch(Exception e) {
			System.out.println("INSERT FAILED");
			System.out.println("\tname: \t\t" + name);
			System.out.println("\tbrand: \t\t" + brand);
			System.out.println("\tlink: \t\t" + link);
			System.out.println("\tprice: \t\t" + price);
			System.out.println("\tnum_of_ratings: " + num_of_ratings);
			System.out.println("\trating: \t" + rating);
			System.out.println("\nQUERY:\n" + query + "\n");
			insert_Failure(link);
			e.printStackTrace();
		} finally {
			conn.close();
			//System.out.println("...INSERTION COMPLETE!!\n");
		}
	}
	
	public void insert_brand(String brand) throws SQLException {
		Connection conn = null;
		String query = "";
		
		System.out.println("INSERT INTO BRAND:  " + brand);
		
		try {
			query = getQuery("src\\SQLQueries\\insert_brand_table.sql");
			query = query.replace("$brand_name", brand);
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);	
		}catch(Exception e) {
			System.out.println("INSERT FAILED");
			System.out.println("\tbrand: \t\t" + brand);
			System.out.println("\nQUERY:\n" + query + "\n");
			e.printStackTrace();
		} finally {
			conn.close();
			//System.out.println("...INSERTION COMPLETE!!\n");
		}
	}
	
	public void insert_Failure(String link) {
		Connection conn = null;
		
		try {
			String query = getQuery("src\\SQLQueries\\insert_Failure_Table.sql");
			query = query.replace("$link", link);
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);	
			conn.close();
		}catch(Exception e) {
			System.out.println("INSERT FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}
	
	public void test_Failure() {
		Connection conn = null;
		
		try {
			String query = "INSERT INTO CAN_Product(name, brand, link, price, num_of_ratings, rating)"
					+ "VALUES('Clarks Men''s Cotrell Walk Lace-Up Shoe', 'test', 'test', '', '', '');";
			System.out.println(query + "\n");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);	
			conn.close();
			
			view_CAN_Product_Table();
		}catch(Exception e) {
			System.out.println("INSERT FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}
	
	public String getQuery(String filePath) {
		String query = "";
	    try{
	        BufferedReader buffer = new BufferedReader(new FileReader(filePath));
	        StringBuilder sb = new StringBuilder();
	        String line = "";
	        while((line = buffer.readLine()) != null) {
	            sb.append(line);
	        }
	        query = sb.toString();
	        buffer.close();
	        
	        
	    }
	    catch(FileNotFoundException e){
	        e.printStackTrace();
//	        Prints out file names at given point in directory
//	        File file = new File(".");
//	        for(String fileNames : file.list()) 
//	        	System.out.println(fileNames);
	    }
	    catch(IOException e){
	        e.printStackTrace();
	    }
	    
	    return query;
	}

}
