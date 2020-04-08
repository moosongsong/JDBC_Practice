import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class AppStart {
	public static void main(String[] args) {
		Connection con = null;
		Properties prop = new Properties();
		
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("jdbc ����̹��� driver manager�� ����Ͽ����ϴ�");
			
			con = DriverManager.getConnection(
					prop.getProperty("url"), 
					prop.getProperty("user"), 
					prop.getProperty("pass"));
			System.out.println("DB is connected....");
		} catch (ClassNotFoundException e) {
			System.out.println("�˻� ����");
		} catch (SQLException e) {
//			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
			}
		}
		
		
		
		
		
		
	}

}
