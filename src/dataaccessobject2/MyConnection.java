package dataaccessobject2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyConnection {

//	private static MyConnection connection = new MyConnection();
	public static final String URL = "jdbc:mariadb://localhost:3306/example?user=example&password=1234";

	public static Connection connection;

	// 생성자를 숨긴다.
//	private MyConnection() {}

	// MyConnection인스턴스를 반환한다.
//	public static MyConnection getInstance() {
//		return connection;
//	}

	// connect를 호출할 때마다 새로운 연결을 만들게 한다.
	public static void connect() {
		try {
			connection = DriverManager.getConnection(URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws Exception {
//		return DriverManager.getConnection(URL);
		return connection;
	}

	public static void close() {
		try {
			connection.close();
		} catch (Exception e) {
		}
	}

	public static void close(Statement smt) {
		try {
			smt.close();
		} catch (Exception e) {
		}
	}

	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (Exception e) {
		}
	}

}
