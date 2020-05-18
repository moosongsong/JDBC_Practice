package dataaccessobject;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import viewobject.Category;

public class CategoryDAO {

	// CategoryDAO�� �ν��Ͻ��� ������ �ʰ� getComics�� ȣ���ϱ� ���� static���� �����.
	public static ArrayList<Category> getCategory() {

		ArrayList<Category> list = new ArrayList<>();
//
//		final String url = "jdbc:mariadb://localhost:3306/example?user=example&password=1234";
//
//		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			stmt = MyConnection.getConnection().createStatement();
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
