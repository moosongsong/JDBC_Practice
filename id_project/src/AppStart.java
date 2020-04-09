import java.sql.Connection;
import java.sql.SQLException;

public class AppStart {

	public static void main(String[] args) {
		Connection con = null;
		try {
			con = ServerInfo.getConnection();
			Member member = new Member(0,"노는게 제일좋아", "모두들 모여라");
			if(MemberDAO.getInstance().registerMember(con, member)) {
				System.out.println("SUCCESS!!!");
				System.out.println("----------------------------------");
				System.out.println("ID : "+member.getId());
				System.out.println("email : "+member.getEmail());
				System.out.println("NICK : "+member.getNick());
			}
			else {
				System.out.println("FAIL....");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
