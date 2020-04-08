package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vo.Category;
import vo.Comic;

public class ComicDAO {
	public static ArrayList<Comic> getComics(Category category){
		ArrayList<Comic> list = new ArrayList<Comic>();

		Statement stmt = null;
		ResultSet rs = null;
		
		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//			con = DriverManager.getConnection(url, user, pass);
//			
			stmt = MyConnection.getConnection().createStatement();
			rs = stmt.executeQuery("SELECT * FROM comics where category_code ='"+category+"'");
			
			while(rs.next()) {
				Comic co = new Comic();
				co.setId(rs.getInt("id"));
				co.setTitle(rs.getString("title"));
				co.setPrice(rs.getInt("price"));
				co.setCategory_code(rs.getString("category_code"));
				co.setPublisher_code(rs.getString("publisher_code"));
				co.setCategory(category);
				list.add(co);
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
		
		System.out.println("--------------------------------------------");
		System.out.println("분류코드 : "+category.getCode());
		System.out.println("분류이름 : "+category.getName());
		System.out.println("포함서적 : "+category.getComics());
		
		return list;
	}
}
