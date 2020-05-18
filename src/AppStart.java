import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import viewobject.Category;
import viewobject.Comic;

public class AppStart {

	public ArrayList<Comic> getComics(Category category) {

		ArrayList<Comic> list = new ArrayList<>();

		final String url = "jdbc:mariadb://localhost:3306/example?user=example&password=1234";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url);
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM comics WHERE category_code = '" + category.getCode() + "'");
			// categoryCode가 varchar형이고 Query문에서 쓰이기 때문에 ' '로 묶어야 한다.
			while (rs.next()) {
				Comic comic = new Comic();
				comic.setId(rs.getInt("id"));
				comic.setTitle(rs.getString("title"));
				comic.setPrice(rs.getInt("price"));
				comic.setCategoryCode(rs.getString("category_code"));
				comic.setPublisherCode(rs.getString("publisher_code"));
				comic.setCategory(category);
				list.add(comic); // list에 추가
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
			try {
				con.close();
			} catch (Exception e) {
			}
		}

		return list;
	}

	public ArrayList<Category> getCategory() {

		ArrayList<Category> list = new ArrayList<>();

		final String url = "jdbc:mariadb://localhost:3306/example?user=example&password=1234";

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			// System.out.println("JDBC 드라이버 검색 성공");
			con = DriverManager.getConnection(url);
			// System.out.println("DB Server 접속 성공");
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM category");

			// 반복문 돌면서 ArrayList에 Category객체를 만들어서 삽입한다.
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
			try {
				con.close();
			} catch (Exception e) {
			}
		}

//		for (Category category : list) {
//			System.out.println("=====================");
//			System.out.println("분류 코드 : " + category.getCode());
//			System.out.println("분류명 : " + category.getName());
//		}
		return list;
	}

	public static void main(String[] args) {

		// 비정적 메소드인 getCategory(), getComics()를 사용하기 위해 AppStart 인스턴스 생성
		AppStart app = new AppStart();

		// categories에다가 모든 Category객체를 집어넣기
		ArrayList<Category> categories = app.getCategory();

		// for문을 돌면서 Category객체마다 각각 getComics를 해서 해당 객체의 category_code를 가지는 Comic객체들이
		// 모인 ArrayList<Comic> 을 반환받는다.
		// 반환받은 ArrayList<Comic>을 category객체 내부의 필드인 ArrayList<Comic>에 저장한다.
		for (Category category : categories) {
			category.setComics(app.getComics(category));
		}

		for (Category category : categories) {
			System.out.println("===================");
			System.out.println("분류 코드 : " + category.getCode());
			System.out.println("분류명 : " + category.getName());
			ArrayList<Comic> comics = category.getComics();
			System.out.print("해당 분류의 도서 목록 : ");
			for (Comic comic : comics) {
				System.out.print(comic.getTitle() + " | ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("프로그램 종료");
	}

}
