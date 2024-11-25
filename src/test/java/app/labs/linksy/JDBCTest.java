package app.labs.linksy;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

public class JDBCTest {

	@Test
	void JDBCTest() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xepdb1", "hr", "hr");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
