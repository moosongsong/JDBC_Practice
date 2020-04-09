import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TypeDAO {
	private String currentType;
	private static TypeDAO instance = new TypeDAO();
	private ArrayList<Type> types =new ArrayList<Type>();
	
	private TypeDAO() {
		;
	}
	
	public static TypeDAO getInstance() {
		return instance;
	}
	
	
	
	public ArrayList<Type> getTypes() {
		return types;
	}

	public void setTypes(ArrayList<Type> types) {
		this.types = types;
	}

	public void setCurrentType(String code) {
		this.currentType = code;
		setTypeList(code);
	}
	
	public void setTypeList(String type) {
		//테이블에서 code에 해당하는 type정보를 가져와서 컬렉션을 구성
	}
	
	public String getCurrentType() {
		return currentType;
	}

	public boolean validate(Connection con, String code) {
		Statement stmt = null;
		ResultSet rs = null;
		boolean result = false;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("Select count(*) as cnt from types where type_code = '"+code+"';");
			
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
	public boolean insert(Connection con, Type type) {
		return insert(con, type.getType_code(), type.getType_name());
	}
	public boolean insert(Connection con, String code, String name) {
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO types VALUES (?,?);";
		
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
	public boolean delete(Connection con, Type type) {
		return delete(con, type.getType_code());
	}
	public boolean delete(Connection con, String code) {
		boolean result = false;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "DELETE FROM types where type_code LIKE '"+code+"';";
		String subsql = "Select count(*) from types where type_code like '"+code+"';";
		
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
	public boolean update(Connection con, Type type) {
		return update(con, type.getType_code(), type.getType_name());
	}
	public boolean update(Connection con, String code, String name) {
		boolean result = false;
		Statement stmt = null;
		String sql = "Update types set type_name like '"+name+"' "
				+ "where type_code = '"+code+"';";
		
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
	public ArrayList<Type> getTypeList(Connection con) {
		ArrayList<Type> list = new ArrayList<Type>();
		
		String sql = "select * from types;";
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Type temp = new Type();
				
				temp.setType_code(rs.getString(1));
				temp.setType_name(rs.getString(2));
				
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

