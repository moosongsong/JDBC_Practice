import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DutyDAO {
	public static DutyDAO instance = new DutyDAO();
	
	private DutyDAO() {
		;
	}
	
	public static DutyDAO getInstance() {
		return instance;
	}
	
	public boolean validate(Connection con, String code) {
		Statement stmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("Select count(*) as cnt from duties where duty_code = '"+code+"';");
			
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
	public boolean insert(Connection con, Duty duty) {
		return insert(con, duty.getDuty_code(), duty.getDuty_name());
	}
	
	public boolean insert(Connection con, String code, String name) {
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO duties VALUES (?,?);";
		
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
	public boolean delete(Connection con, Duty duty) {
		return delete(con, duty.getDuty_code());
	}
	public boolean delete(Connection con, String code) {
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM duties where duty_code LIKE '"+code+"';";
		
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
	public boolean update(Connection con, Duty duty) {
		return update(con, duty.getDuty_code(), duty.getDuty_name());
	}

	public boolean update(Connection con, String code, String name) {
		boolean result = false;
		Statement stmt = null;
		String sql = "Update duties set duty_name like '"+name+"' "
				+ "where duty_code = '"+code+"';";
		
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
	public ArrayList<Duty> getDutyList(Connection con) {
		ArrayList<Duty> list = new ArrayList<Duty>();
		
		String sql = "select * from duties;";
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Duty temp = new Duty();
				
				temp.setDuty_code(rs.getString(1));
				temp.setDuty_name(rs.getString(2));
				
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
