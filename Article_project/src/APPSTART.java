import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class APPSTART {

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(ServerInfo.getURL());
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("Select * from parts;");
			
			while(rs.next()) {
				System.out.printf("%15s %10s\n", rs.getString(1), rs.getString(2));
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("Fail...1");
			System.exit(1);
		} catch (SQLException e) {
			System.out.println("Fail...2");
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("Fail...3");
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println("Fail...4");
			}
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Fail...5");
			}
		}
		
		
		
		
		
		
		
	}

}
