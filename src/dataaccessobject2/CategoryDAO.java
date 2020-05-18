package dataaccessobject2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import viewobject.Category;

public class CategoryDAO {

	public static boolean insert(Connection con, Category category) {
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			pstmt = con.prepareStatement("INSERT INTO category VALUES(?, ?)");
			pstmt.setString(1, category.getCode());
			pstmt.setString(2, category.getName());
			result = (pstmt.executeUpdate() == 1); // executeUpdate의 반환값이 1이면 result에 true들어간다.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	// CategoryDAO의 인스턴스를 만들지 않고 getComics를 호출하기 위해 static으로 만든다.
	public static ArrayList<Category> getCategory(Connection con) {

		ArrayList<Category> list = new ArrayList<>();

		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM category");

			while (rs.next()) {
				Category c = new Category();
				c.setCode(rs.getString("category_code"));
				c.setName(rs.getString("category_name"));
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
			}
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}

		return list;
	}
}
