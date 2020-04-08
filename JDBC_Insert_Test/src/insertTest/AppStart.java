package insertTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AppStart {

	public static void main(String[] args) {
		Statement stmt = null;
		Connection con = null;
		//사용자 위치 지정
		final String url = "jdbc:mariadb://localhost:3306/example";
		//사용할 DB 가지고 오기
		final String user = "example";
		//비밀번호
		final String pass = "1234";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pass);
			stmt = con.createStatement();
			int result = stmt.executeUpdate("Insert into members values ('lee', 'JIN', 'song@naver.com', MD5('1234'));");
			System.out.println("result : "+result);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {		}
			try {
				con.close();
			} catch (SQLException e) {		}
			
		}
	}

}
