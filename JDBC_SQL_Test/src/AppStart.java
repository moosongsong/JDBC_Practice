import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class AppStart {
	public static void main(String[] args) {
		//����� ��ġ ����
		final String url = "jdbc:mariadb://localhost:3306/example";
		//����� DB ������ ����
		final String user = "example";
		//��й�ȣ
		final String pass = "1234";
		
		//��ɾ ����� ���� 
		Statement stmt=null;
		//���� ������ ������
		Connection con = null;
		//������� ��� ��
		ResultSet rs = null;
		
		try {
			//����̹��� �ε� �Ǿ����� Ȯ��
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("jdbc is here!!");
			
			//�Ŵ����� ���� ���ؼ� �����
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection is completed!!");
			
			//������ ���� �����Ϳ� �����ϱ�
			stmt = con.createStatement();
			rs = stmt.executeQuery("Select * from members;");//��ȯ�Ǵ� ����� reasultset
			
			while(rs.next()) {
				System.out.printf("%s %s\n", rs.getString(2), rs.getString(3));
				// �ι�° ����° �÷� ������ ����.//result set ��ü�� ���� ���� �ȴ�.
				//�÷����� 0���� ������ �ƴ϶� 1���� �����̴�.
			}
			
		}catch (ClassNotFoundException e) {
			System.out.println("driver is not here...");
		} catch (SQLException e) {
			System.out.println("Fail...");
		} finally {
			try {
				rs.close();
			} catch (SQLException e1) {		}
			try {
				stmt.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("close fail....");
			}
		}

	}

}
