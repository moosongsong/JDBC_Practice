import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class prepared_Statment_test {

	public static void main(String[] args) {

		// preparedStatement는 내부에 미리 어떤 SQL문을 수행할지를 가지고 있다.
		Connection con = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:mariadb://127.0.0.1:3306/example?user=example&password=1234";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url);
			pstmt = con.prepareStatement("INSERT INTO members VALUES(?, ?, ?, MD5(?));");
			// 위의 ? 부분에 값이 들어가야 Query문이 완성된다.
			// 위의 ? 부분에 값을 할당하는 방법
			pstmt.setString(1, "PSTMT2");
			pstmt.setString(2, "테스트용2");
			pstmt.setString(3, "pstmt2@gmail.com");
			pstmt.setString(4, "1234");
			int res = pstmt.executeUpdate();
			System.out.println("result : " + res);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
			try {
				con.close();
			} catch (Exception e) {
			}
		}
	}
}
