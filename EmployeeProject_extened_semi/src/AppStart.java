import java.sql.Connection;
import java.util.ArrayList;

public class AppStart {
	public static void main(String[] args) {
		Connection con = null;
		
		try {
			ServerManager.getInstance().open();
			con = ServerManager.getInstance().getConnection();
			TypeDAO.getInstance().setTypeList(con, "RE");
			
			ArrayList<Type> list = TypeDAO.getInstance().getList();
			for (Type t : list) {
				System.out.println(t.getCode() + " : " + t.getName());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try { con.close(); } catch (Exception e) {}
		}
	}
}
