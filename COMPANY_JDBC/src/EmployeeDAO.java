import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeDAO {
	public static EmployeeDAO instance = new EmployeeDAO();
	
	private EmployeeDAO() {
		;
	}
	
	public static EmployeeDAO getInstance() {
		return instance;
	}
	//insert
	public boolean insert(Connection con, String code, String name,
					String dept_code, String duty_code, String type_code,
					String senior_code, int income, int worktime) {
		boolean result = false;
		
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO duties VALUES (?,?,?,?,?,?,?,?);";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, code);
			pstmt.setString(2, name);
			pstmt.setString(3, dept_code);
			pstmt.setString(4, duty_code);
			pstmt.setString(5, type_code);
			pstmt.setString(6, senior_code);
			pstmt.setInt(7, income);
			pstmt.setInt(8, worktime);
			
			
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
	
	public boolean delete(Connection con, Employee em) {
		return delete(con, em.getEmp_code());
	}
	
	public boolean delete(Connection con, String code) {
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM employees where emp_code LIKE '"+code+"';";
		
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
	public boolean update(Connection con, String code, String name) {
		boolean result = false;
		Statement stmt = null;
		String sql = "Update employee set emp_name like '"+name+"' "
				+ "where emp_code = '"+code+"';";
		
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
	public ArrayList<Employee> getEmployeeList(Connection con) {
		ArrayList<Employee> list = new ArrayList<Employee>();
		
		String sql = "select * from employees;";
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Employee temp = new Employee();
				
				temp.setEmp_code(rs.getString(1));
				temp.setEmp_name(rs.getString(2));
				
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
