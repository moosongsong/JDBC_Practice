

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class MemberDAO{
	ArrayList<Member> members = new ArrayList<Member>();
	private static MemberDAO instance = new MemberDAO();

	private MemberDAO(){
	}

	public static MemberDAO getInstance(){
		return instance;
	}

	public ArrayList<Member> getMembers() {
		return members;
	}

	public int getLastInsertID(Connection con) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		int id = 0;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT LAST_INSERT_ID();");
			
			if(rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			rs.close();
			stmt.close();
		}
		return id;
	}

	public void setMembers(ArrayList<Member> members) {
		this.members = members;
	}

	public boolean registerMember(Connection con, Member member){
		PreparedStatement pstmt = null;
		boolean result = false;

		try{
			pstmt = con.prepareStatement("Insert into members Values(DEFAULT, ?, ?)");
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, member.getNick());
			result = (pstmt.executeUpdate()>0);
			if(result) {
				member.setId(getLastInsertID(con));
			}
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}finally{
			try{pstmt.close();}
			catch(Exception e){}
		}
		return result;
	}


}
