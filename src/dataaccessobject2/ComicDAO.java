package dataaccessobject2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import viewobject.Category;
import viewobject.Comic;

public class ComicDAO {

	public static boolean insert(Connection con, Comic comic) {

		boolean result = false;
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement("INSERT INTO comics VALUES(DEFAULT,?,?,?,?);");
			pstmt.setString(1, comic.getTitle());
			pstmt.setInt(2, comic.getPrice());
			pstmt.setString(3, comic.getCategoryCode());
			pstmt.setString(4, comic.getPublisherCode());
			result = (pstmt.executeUpdate() == 1);
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

	public static ArrayList<Comic> getComics(Connection con, Category category) {

		ArrayList<Comic> list = new ArrayList<>();

		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			stmt = con.createStatement();
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
