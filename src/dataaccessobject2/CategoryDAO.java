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
			result = (pstmt.executeUpdate() == 1); // executeUpdate�� ��ȯ���� 1�̸� result�� true����.
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
	// CategoryDAO�� �ν��Ͻ��� ������ �ʰ� getComics�� ȣ���ϱ� ���� static���� �����.
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
