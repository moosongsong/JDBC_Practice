package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vo.Category;

public class CategoryDAO {
	
	public static ArrayList<Category> getCategory(){
		ArrayList<Category> list = new ArrayList<Category>();

		Statement stmt = null;
		ResultSet rs = null;

		
		try {
			stmt = MyConnection.getConnection().createStatement();
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
