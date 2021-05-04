package JUnit_Test;
import Amazon_Scraper.SQLConnection;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



class SQLConnectionTest {
	
	private SQLConnection conn;

	@BeforeEach                                         
	public void setUp() throws Exception {
		conn = new SQLConnection();
	}

	@Test
	@DisplayName("Test getConnection() method")   
	public void test_getConnection() {
		Object obj = new Object();
		Connection conn_test = (Connection)obj;
		System.out.println((Connection)(new Object()));
//		assertEquals(conn_test, conn.get_connection(),      
//				"Should not return null");          
	}
}
