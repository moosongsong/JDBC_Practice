package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vo.Category;

public class CategoryDAO {
	
	public static boolean insert(Connection con, Category category) {
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "Insert Into category Values (?,?);";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category.getCode());
			pstmt.setString(2, category.getName());
			result = (pstmt.executeUpdate() > 0);
			
		} catch (SQLException e) {
			;
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				;
			}
		}
		return result;
	}
	
	public static ArrayList<Category> getCategory(Connection con){
		ArrayList<Category> list = new ArrayList<Category>();

		Statement stmt = null;
		ResultSet rs = null;

		
		try {
			stmt = con.createStatement();//´ÝÀ¸¸é ¾ÈµÊ.
			rs = stmt.executeQuery("SELECT * FROM category;");
			while(rs.next()) {
				Category ca = new Category();
				ca.setCode(rs.getString("category_code"));
				ca.setName(rs.getString("category_name"));
				list.add(ca);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return list;
	}
}
