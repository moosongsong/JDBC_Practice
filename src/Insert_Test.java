import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Insert_Test {

	public static void main(String[] args) {

		Connection con = null;
		String url = "jdbc:mariadb://127.0.0.1:3306/example?user=example&password=1234";
		Statement stmt = null;
		// insert�ÿ��� ��ȯ�Ǵ°� �����Ƿ� ResultSet��ü �ʿ����.
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url);
			stmt = con.createStatement();
			// stmt.execute("INSERT INTO members VALUES(' ')");
			// INSERT�ÿ��� executeQuery�޼ҵ尡 �ƴ� execute�� ����Ѵ�.
			int result = stmt
					.executeUpdate(
							"INSERT INTO members VALUES('RA','�����', 'robbyra@gmail.com', LEFT(password('1234'),32));");
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
