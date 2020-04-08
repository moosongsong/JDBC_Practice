import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class AppStart {
	public static void main(String[] args) {
		//사용자 위치 지정
		final String url = "jdbc:mariadb://localhost:3306/example";
		//사용할 DB 가지고 오기
		final String user = "example";
		//비밀번호
		final String pass = "1234";
		
		//명령어를 기록할 구문 
		Statement stmt=null;
		//디비와 연결한 컨넥터
		Connection con = null;
		//가지고온 결과 셋
		ResultSet rs = null;
		
		try {
			//드라이버가 로딩 되었는지 확인
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("jdbc is here!!");
			
			//매니저를 통한 컨넥션 만들기
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection is completed!!");
			
			//실행할 구문 컨넥터와 연결하기
			stmt = con.createStatement();
			rs = stmt.executeQuery("Select * from members;");//반환되는 결과가 reasultset
			
			while(rs.next()) {
				System.out.printf("%s %s\n", rs.getString(2), rs.getString(3));
				// 두번째 세번째 컬럼 가지고 오기.//result set 자체가 뷰라고 보면 된다.
				//컬럼수는 0부터 시작이 아니라 1부터 시작이다.
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
