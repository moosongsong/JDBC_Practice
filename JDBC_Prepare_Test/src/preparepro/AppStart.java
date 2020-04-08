package preparepro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AppStart {

	public static void main(String[] args) {
		
		//����� ��ġ ����
		final String url = "jdbc:mariadb://localhost:3306/example";
		//����� DB ������ ����
		final String user = "example";
		//��й�ȣ
		final String pass = "1234";
		
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("jdbc is here!!");
			
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection is completed!!");
			
			stmt = con.prepareStatement("Insert INTO members Values (?, ?, ?, ?);");
			
			//app ���忡���� prepare ���� �׳� statement�� ����ϴ� ���� ����.
			//�޸� ������ �߻��� ������ ���� �����̴�.
			
			stmt.setString(1, "min");
			stmt.setString(2, "suga");
			stmt.setString(3, "gogo@gmail.com");
			stmt.setString(4, "1234");
		
			
			int result = stmt.executeUpdate();
			
			System.out.println("Result : "+result);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				
			}
			try {
				con.close();
			} catch (SQLException e) {
				
			}
		}
	}

}
