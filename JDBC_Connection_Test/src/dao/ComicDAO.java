package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vo.Category;
import vo.Comic;

public class ComicDAO {
	
	public static boolean insert(Connection con, Comic comic) {
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "Insert into comics values (default,?,?,?,?);";
		
		try {
			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, comic.getId());
			pstmt.setString(1, comic.getTitle());
			pstmt.setInt(2, comic.getPrice());
			pstmt.setString(3, comic.getCategory_code());
			pstmt.setString(4, comic.getPublisher_code());
			result = (pstmt.executeUpdate()>0);
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
	
	public static ArrayList<Comic> getComics(Connection con, Category category){
		ArrayList<Comic> list = new ArrayList<Comic>();

		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
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
//		
//		System.out.println("--------------------------------------------");
//		System.out.println("분류코드 : "+category.getCode());
//		System.out.println("분류이름 : "+category.getName());
//		System.out.println("포함서적 : "+category.getComics());
		
		return list;
	}
}
