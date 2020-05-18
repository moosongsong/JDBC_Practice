package dataaccessobject;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import viewobject.Category;
import viewobject.Comic;

public class ComicDAO {

	// ComicDAO의 인스턴스를 만들지 않고 getComics를 호출하기 위해 static으로 만든다.
	public static ArrayList<Comic> getComics(Category category) {

		ArrayList<Comic> list = new ArrayList<>();

//		final String url = "jdbc:mariadb://localhost:3306/example?user=example&password=1234";
//		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			stmt = MyConnection.getConnection().createStatement();
			rs = stmt.executeQuery("SELECT * FROM comics WHERE category_code = '" + category.getCode() + "'");

			while (rs.next()) {
				Comic comic = new Comic();
				comic.setId(rs.getInt("id"));
				comic.setTitle(rs.getString("title"));
				comic.setPrice(rs.getInt("price"));
				comic.setCategoryCode(rs.getString("category_code"));
				comic.setPublisherCode(rs.getString("publisher_code"));
				comic.setCategory(category);
				list.add(comic);
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
