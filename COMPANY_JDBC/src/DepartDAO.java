import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DepartDAO {
	private static DepartDAO instance = new DepartDAO();
	
	private DepartDAO() {
		;
	}
	
	public static DepartDAO getInstance() {
		return instance;
	}
	
	public boolean validate(Connection con, String code) {
		Statement stmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("Select count(*) as cnt from departs where dept_code = '"+code+"';");
			
			if(rs.next()) {
				result = (rs.getInt("cnt")>0);
			}
		} catch (SQLException e) {
			
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
		return result;
	}
	
	//insert
	public boolean insert(Connection con, Depart dept) {
		return insert(con, dept.getDept_code(), dept.getDept_name());
	}
	public boolean insert(Connection con, String code, String name) {
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO departs VALUES (?,?);";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, code);
			pstmt.setString(2, name);
			
			result = (pstmt.executeUpdate()>0);
		} catch (SQLException e) {
			System.out.println("FAIL....");
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("CLOSE FAIL...");
			}
		}
		
		return result;
	}
	
	//delete
	public boolean delete(Connection con, Depart dept) {
		return delete(con, dept.getDept_code());
	}
	public boolean delete(Connection con, String code) {
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM departs where dept_code LIKE '"+code+"';";
		
		Statement stmt = null;
		ResultSet rs = null;
		
		String subsql = "Select count(*) from departs where dept_code like '"+code+"';";
		
		int count = 0;
		
		//
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(subsql);
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e1) {
			System.out.println("fail");
			return result;
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				
			}
			try {
				rs.close();
			} catch (SQLException e) {
				
			}
		}
		//
		if(count != 0) {
			ArrayList<Employee> temp = null;
			EmployeeDAO tt = EmployeeDAO.getInstance();
			temp = tt.getEmployeeList(con);
			
			for (Employee employee : temp) {
				tt.delete(con, employee);
			}
		}
		try {
			pstmt = con.prepareStatement(sql);
			
			result = (pstmt.executeUpdate()>0);
			
		} catch (SQLException e) {
			System.out.println("FAIL...");
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("CLOSE FAIL...");
			}
		}

		return result;
	}
	
	//update
	public boolean update(Connection con, Depart dept) {
		return update(con, dept.getDept_code(), dept.getDept_name());
	}
	public boolean update(Connection con, String code, String name) {
		boolean result = false;
		Statement stmt = null;
		String sql = "Update departs set dept_name like '"+name+"' "
				+ "where dept_code = '"+code+"';";
		
		try {
			stmt = con.createStatement();
			
			result = (stmt.executeUpdate(sql)>0);
			
		} catch (SQLException e) {
			System.out.println("FAIL...");
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println("CLOSE FAIL...");
			}
		}
		
		return result;
	}
	//select
	public ArrayList<Depart> getDepartList(Connection con) {
		ArrayList<Depart> list = new ArrayList<Depart>();
		
		String sql = "select * from departs;";
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Depart temp = new Depart();
				
				temp.setDept_code(rs.getString(1));
				temp.setDept_name(rs.getString(2));
				
				list.add(temp);
			}
		} catch (SQLException e) {
			System.out.println("FAIL...");
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("close fail");
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println("close fail");
			}
		}
		return list;
	}
}
