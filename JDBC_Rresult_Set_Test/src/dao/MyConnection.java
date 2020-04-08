package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {

	private static Connection connection =null;
	static final String url = "jdbc:mariadb://localhost:3306/example";
	static final String user = "example";
	static final String pass = "1234";
	
	public static void open() throws SQLException{
		connection = DriverManager.getConnection(url, user, pass);
	}
	
	public static Connection getConnection() throws SQLException {
		return connection;
	}
	
	public static void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			;
		}
	}
	
	public static void close(Statement stmt) {
		try {
			stmt.close();
		} catch (SQLException e) {
			;
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			;
		}
	}
}
