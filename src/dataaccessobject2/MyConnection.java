package dataaccessobject2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyConnection {

//	private static MyConnection connection = new MyConnection();
	public static final String URL = "jdbc:mariadb://localhost:3306/example?user=example&password=1234";

	public static Connection connection;

	// �����ڸ� �����.
//	private MyConnection() {}

	// MyConnection�ν��Ͻ��� ��ȯ�Ѵ�.
//	public static MyConnection getInstance() {
//		return connection;
//	}

	// connect�� ȣ���� ������ ���ο� ������ ����� �Ѵ�.
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
