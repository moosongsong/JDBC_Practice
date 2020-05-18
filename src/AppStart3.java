import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import dataaccessobject2.CategoryDAO;
import dataaccessobject2.ComicDAO;
import viewobject.Category;
import viewobject.Comic;

public class AppStart3 {

	public static final String URL = "jdbc:mariadb://localhost:3306/example?user=example&password=1234";

	public static void main(String[] args) {

		Connection con = null;
		ArrayList<Category> list = null;

		try {
			con = DriverManager.getConnection(URL);
			Category c = new Category("WARS", "����");

			if (CategoryDAO.insert(con, c)) {
				System.out.println("===========");
				System.out.println("���������� INSERT �Ǿ����ϴ�.");
				System.out.println("===========");
			} else
				System.err.println("INSERT�� �����߽��ϴ�.");

			list = CategoryDAO.getCategory(con);
			for (int i = 0; i < list.size(); i++) {
				Category category = list.get(i);
				category.setComics(ComicDAO.getComics(con, category));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		for (Category category : list) {
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
	}
}
