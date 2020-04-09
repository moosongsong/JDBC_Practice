import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PartDAO {
	private static PartDAO instance = new PartDAO();

	private PartDAO() {
		;
	}

	public static PartDAO getInstance() {
		return instance;
	}

	//분류를 등록할 수 있는 기능
	public boolean insert(Connection con, Part part) {
		return insert(con, part.getCode(), part.getName());
	}
	public boolean insert(Connection con, String code, String name) {//Overloading
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "Insert into parts values (?,?);";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, code);
			pstmt.setString(2, name);

			result = (pstmt.executeUpdate()>0);
		} catch (SQLException e) {
			System.out.println("FAIL...");
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("close fail...");
			}
		}
		return result;
	}

	//분류삭제

	public boolean delete(Connection con, Part part) {
		return delete(con, part.getCode());
	}

	public boolean  delete(Connection con, String code) {
		boolean result = false;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM parts where p_code like '"+code+"';";

		try {
			stmt = con.prepareStatement(sql);

			if(stmt.executeUpdate()>0) {
				System.out.println("success!!!");
			}
			else {
				throw new Exception("FAIL");
			}
		} catch (SQLException e) {
			System.out.println("fail");
		} catch (Exception e) {
			System.out.println("fail");
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println("close fail");
			}
		}
		return result;
	}


	//분류를 편집할 수 있는 기능
	public boolean update(Connection con, Part part) {
		return update(con, part.getCode(), part.getName());
	}

	public boolean update(Connection con, String code, String name) {
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "Update parts set p_name like '"+name+"' "
				+ "where p_code = '"+code+"';";

		try {
			pstmt = con.prepareStatement(sql);

			if(pstmt.executeUpdate()>0) {
				System.out.println("Success");
			}else {
				throw new Exception();
			}

		} catch (SQLException e) {
			System.out.println("fail");
		} catch (Exception e) {
			System.out.println("fail");
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("fail");
			}
		}
		return result;
	}



	
	//분류를 검색할 수 있는 기능
	public ArrayList<Part> getPartList(Connection con) {
		ArrayList<Part> list = new ArrayList<Part>();

		String sql = "SELECT * FROM parts";
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				Part temp = new Part();

				temp.setCode(rs.getString(1));
				temp.setName(rs.getString(2));

				list.add(temp);
				System.out.println("Searching Success!!!");
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

	public Part getPart(Connection con, Part part) {
		Part temp = new Part();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "Select * from parts where p_code='"+part.getCode()+"' and p_name Like '"+part.getName()+"';";


		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				temp.setCode(rs.getString(1));
				temp.setName(rs.getString(2));
			}
		} catch (SQLException e) {
			System.out.println("FAIL");
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("close fail");
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("close fail");
			}
		}


		return temp;
	}

	public Part getPart(Connection con, String code) {
		Part temp = new Part();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "Select * from parts where p_code='"+code+"';";


		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				temp.setCode(rs.getString(1));
				temp.setName(rs.getString(2));
			}
		} catch (SQLException e) {
			System.out.println("FAIL");
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("close fail");
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("close fail");
			}
		}


		return temp;
	}
}
