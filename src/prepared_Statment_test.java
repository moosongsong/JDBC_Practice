import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class prepared_Statment_test {

	public static void main(String[] args) {

		// preparedStatement�� ���ο� �̸� � SQL���� ���������� ������ �ִ�.
		Connection con = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:mariadb://127.0.0.1:3306/example?user=example&password=1234";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url);
			pstmt = con.prepareStatement("INSERT INTO members VALUES(?, ?, ?, MD5(?));");
			// ���� ? �κп� ���� ���� Query���� �ϼ��ȴ�.
			// ���� ? �κп� ���� �Ҵ��ϴ� ���
			pstmt.setString(1, "PSTMT2");
			pstmt.setString(2, "�׽�Ʈ��2");
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
