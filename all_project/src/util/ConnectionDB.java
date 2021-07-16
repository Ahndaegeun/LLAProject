package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
	private ConnectionDB() {};
	
	private static ConnectionDB instance = new ConnectionDB();
	
	public static ConnectionDB getInstance() {
		return instance;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String id = "all_project";
		String pw = "java";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
			return conn;
		}

}
