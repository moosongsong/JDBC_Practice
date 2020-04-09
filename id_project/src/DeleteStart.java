import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteStart {

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			con = ServerInfo.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			rs = stmt.executeQuery("SELECT * from members;");
			
			//alter update
			while(rs.next()) {
				if(rs.getString("email").equals("gogogo@naver.com")) {
					rs.updateString(2, "Á¹·Á");
					rs.updateString(3, "·ê·çÇÏ¼¼¿ä");
					rs.updateRow();
				}
			}
			
			//insert
//			rs.moveToInsertRow();//ºóÇàÀ» ÇÏ³ª ¸¸µé¾î ³õ´Â´Ù.
//			rs.updateInt(1, 4000);
//			rs.updateString(2, "gogogo@naver.com");
//			rs.updateString(3, "Hola");
//			
//			rs.insertRow();
//			rs.moveToCurrentRow();
			
			//delete
//			while(rs.next()) {
//				if(rs.getString("nick").equals("±è±âÈñ")) {
//					System.out.println(rs.getInt("id")+"is deleting...");
//					rs.deleteRow();
//					break;
//				}
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
			}
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
