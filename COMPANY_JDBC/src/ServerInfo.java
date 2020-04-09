import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServerInfo {
	private static String SERVER = "192.168.30.200";
	private static String USER = "student";
	private static String PASS = "1234";
	private static String DATABASE = "student";
	public static String getSERVER() {
		return SERVER;
	}
	public static String getUSER() {
		return USER;
	}
	public static String getPASS() {
		return PASS;
	}
	public static String getDATABASE() {
		return DATABASE;
	}
	public static void setSERVER(String sERVER) {
		SERVER = sERVER;
	}
	public static void setUSER(String uSER) {
		USER = uSER;
	}
	public static void setPASS(String pASS) {
		PASS = pASS;
	}
	public static void setDATABASE(String dATABASE) {
		DATABASE = dATABASE;
	}
	public static String getURL() {
		return "jdbc:mariadb://"+SERVER+":3306/"+DATABASE+"?user="+USER+"&password="+PASS;
	}
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("driver is not here...");
			System.exit(1);
		}
		return DriverManager.getConnection(getURL());
	}
}
