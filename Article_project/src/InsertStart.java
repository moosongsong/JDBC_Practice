import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InsertStart {
	
	public static int getNextInt(Connection con, String code) throws SQLException {
		int id=0;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select ifnull (max(id), 0) +1 AS nextId from articles where p_code like '"+code+"';");
			if(rs.next()) {
				id = rs.getInt("nextId");
			}
			
		} catch (SQLException e) {
			System.err.println("there is problems when computes articles table's id....");
			throw e;
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				
			}
		}
		
		
		return id;
	}
	
	public static void main(String [] args) {
		Connection con =null;
		PreparedStatement stmt = null;
		
		try {
			con = ServerInfo.getConnection();
			stmt = con.prepareStatement("Insert into articles values (?,?,?,?,?,?)");
			
						
			stmt.setInt(1, getNextInt(con, "fishing"));//this problem is here!!!!
			stmt.setString(2, "fishing");
			stmt.setString(3, "쥬글랭");
			stmt.setString(4, "시러");
			stmt.setString(5, "반장 4개월 남음");
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nownow = sdf.format(cal.getTime());
			
			stmt.setString(6, nownow);
			
			if(stmt.executeUpdate()>0) {
				System.out.println("Success!!!!");
			}
			else {
				throw new Exception("i don't know about this error");
			}
			
		} catch (SQLException e) {
			System.out.println("Fail(inserting)");
		} catch (Exception e) {
			System.err.println("fail....");
		}
		
		finally {
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
