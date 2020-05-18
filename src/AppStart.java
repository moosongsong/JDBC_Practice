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
			// categoryCode�� varchar���̰� Query������ ���̱� ������ ' '�� ����� �Ѵ�.
			while (rs.next()) {
				Comic comic = new Comic();
				comic.setId(rs.getInt("id"));
				comic.setTitle(rs.getString("title"));
				comic.setPrice(rs.getInt("price"));
				comic.setCategoryCode(rs.getString("category_code"));
				comic.setPublisherCode(rs.getString("publisher_code"));
				comic.setCategory(category);
				list.add(comic); // list�� �߰�
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
			// System.out.println("JDBC ����̹� �˻� ����");
			con = DriverManager.getConnection(url);
			// System.out.println("DB Server ���� ����");
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM category");

			// �ݺ��� ���鼭 ArrayList�� Category��ü�� ���� �����Ѵ�.
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
//			System.out.println("�з� �ڵ� : " + category.getCode());
//			System.out.println("�з��� : " + category.getName());
//		}
		return list;
	}

	public static void main(String[] args) {

		// ������ �޼ҵ��� getCategory(), getComics()�� ����ϱ� ���� AppStart �ν��Ͻ� ����
		AppStart app = new AppStart();

		// categories���ٰ� ��� Category��ü�� ����ֱ�
		ArrayList<Category> categories = app.getCategory();

		// for���� ���鼭 Category��ü���� ���� getComics�� �ؼ� �ش� ��ü�� category_code�� ������ Comic��ü����
		// ���� ArrayList<Comic> �� ��ȯ�޴´�.
		// ��ȯ���� ArrayList<Comic>�� category��ü ������ �ʵ��� ArrayList<Comic>�� �����Ѵ�.
		for (Category category : categories) {
			category.setComics(app.getComics(category));
		}

		for (Category category : categories) {
			System.out.println("===================");
			System.out.println("�з� �ڵ� : " + category.getCode());
			System.out.println("�з��� : " + category.getName());
			ArrayList<Comic> comics = category.getComics();
			System.out.print("�ش� �з��� ���� ��� : ");
			for (Comic comic : comics) {
				System.out.print(comic.getTitle() + " | ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("���α׷� ����");
	}

}
