import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Insert_Test {

	public static void main(String[] args) {

		Connection con = null;
		String url = "jdbc:mariadb://127.0.0.1:3306/example?user=example&password=1234";
		Statement stmt = null;
		// insert시에는 반환되는게 없으므로 ResultSet객체 필요없다.
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url);
			stmt = con.createStatement();
			// stmt.execute("INSERT INTO members VALUES(' ')");
			// INSERT시에는 executeQuery메소드가 아닌 execute를 사용한다.
			int result = stmt
					.executeUpdate(
							"INSERT INTO members VALUES('RA','나상우', 'robbyra@gmail.com', LEFT(password('1234'),32));");
			System.out.println("result : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (Exception e) {
			}
			try {
				con.close();
			} catch (Exception e) {
			}
		}
	}
}
