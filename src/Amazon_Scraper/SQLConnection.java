package Amazon_Scraper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteDataSource;

public class SQLConnection {
	public SQLiteDataSource ds = null;
	public static PreparedStatement pst = null;
	public static ResultSet rs = null;

	public SQLConnection() {
		try {
			init();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			
	        try {
	            ds = new SQLiteDataSource();
	            ds.setUrl("jdbc:sqlite:appDB.db");
	        } catch ( Exception e ) {
	            e.printStackTrace();
	            System.exit(0);
	        }
	        
	        System.out.println( "Opened database successfully" );

	        
	        
	        try{
	        	conn = ds.getConnection();
	        } catch ( SQLException e ) {
	            e.printStackTrace();
	            System.exit(0);
	        }

	        System.out.println( "Created database successfully" );

	        return conn;
		}catch(Exception e) {
			e.getStackTrace();
			return null;
		}
	}
		
	public void init() throws ClassNotFoundException {
		getConnection();
		clearDB();
		init_CANUS_Product_Table();
		init_CAN_Product_Table();
		init_US_Product_Table();
	}
	
	public void init_CANUS_Product_Table() {
		Connection conn = null;
		try {
			String query = getQuery("src\\SQLQueries\\create_CANUS_Product_Table.sql");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			System.out.println("CREATE TABLE FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}
	
	public void init_CAN_Product_Table() {
		Connection conn = null;
		try {
			String query = getQuery("src\\SQLQueries\\create_CAN_Product_Table.sql");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			System.out.println("CREATE TABLE FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}
	
	public void init_US_Product_Table() {
		Connection conn = null;
		try {
			String query = getQuery("src\\SQLQueries\\create_US_Product_Table.sql");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			System.out.println("CREATE TABLE FAILED" + "\n\tconn = " + conn);
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
			System.out.print("\nCANUS_Product Table:  \n");
			while(rs.next()){
					System.out.print("\tprimary_key = " + rs.getString("primary_key") + "\t");
					System.out.print("\tname  = " + rs.getString("name") + "\t");
					System.out.print("\tbrand = " + rs.getString("brand") + "\t");
					System.out.print("\tlink = " + rs.getString("link") + "\t");
					System.out.print("\tprice= " + rs.getString("price") + "\t");
					System.out.print("\tnum_of_ratings = " + rs.getString("num_of_ratings") + "\t");
					System.out.print("\trating = " + rs.getString("rating") + "\t");
					System.out.print("\n");
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
			String query = getQuery("src\\SQLQueries\\view_CAN_Product_Table.sql");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			System.out.print("\nCAN_Product Table:  \n");
			while(rs.next()){
					System.out.print("\tprimary_key = " + rs.getString("primary_key") + "\t");
					System.out.print("\tname  = " + rs.getString("name") + "\t");
					System.out.print("\tbrand = " + rs.getString("brand") + "\t");
					System.out.print("\tlink = " + rs.getString("link") + "\t");
					System.out.print("\tprice= " + rs.getString("price") + "\t");
					System.out.print("\tnum_of_ratings = " + rs.getString("num_of_ratings") + "\t");
					System.out.print("\trating = " + rs.getString("rating") + "\t");
					System.out.print("\n");
	        }
			conn.close();
		}catch(Exception e) {
			System.out.println("TABLE VIEW FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}
	
	public void view_US_Product_Table() {
		Connection conn = null;
		
		try {
			String query = getQuery("src\\SQLQueries\\view_US_Product_Table.sql");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			System.out.print("\nUS_Product Table:  \n");
			while(rs.next()){
					System.out.print("\tprimary_key = " + rs.getString("primary_key") + "\t");
					System.out.print("\tname  = " + rs.getString("name") + "\t");
					System.out.print("\tbrand = " + rs.getString("brand") + "\t");
					System.out.print("\tlink = " + rs.getString("link") + "\t");
					System.out.print("\tprice= " + rs.getString("price") + "\t");
					System.out.print("\tnum_of_ratings = " + rs.getString("num_of_ratings") + "\t");
					System.out.print("\trating = " + rs.getString("rating") + "\t");
					System.out.print("\n");
	        }
			conn.close();
		}catch(Exception e) {
			System.out.println("TABLE VIEW FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}
	
	public void clearDB() {
		Connection conn = null;
		Statement statement = null;
		String query = "";
				
		try {
			query = getQuery("src\\application\\SQLQueries\\drop_CANUS_Product_Table.sql");
			conn = getConnection();
			statement = conn.createStatement();
			statement.executeUpdate(query);
			
			query = getQuery("src\\application\\SQLQueries\\drop_CAN_Product_Table.sql");
			conn = getConnection();
			statement = conn.createStatement();
			statement.executeUpdate(query);
			
			query = getQuery("src\\application\\SQLQueries\\drop_US_Product_Table.sql");
			conn = getConnection();
			statement = conn.createStatement();
			statement.executeUpdate(query);

			conn.close();
		}catch(Exception e) {
			System.out.println("DROP TABLE(S) FAILED" + "\n\tconn = " + conn);
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
