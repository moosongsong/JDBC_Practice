import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import dataaccessobject2.CategoryDAO;
import dataaccessobject2.ComicDAO;
import viewobject.Category;
import viewobject.Comic;

public class AppStart4 {

	public static final String URL = "jdbc:mariadb://localhost:3306/example?user=example&password=1234";

	public static void main(String[] args) {

		Connection con = null;
		ArrayList<Category> list = null;

		try {
			con = DriverManager.getConnection(URL);

			// �ڵ� commit�� ����.
			con.setAutoCommit(false);

			Category ca = new Category("LECT", "�п�");
			Comic co = new Comic(0, "�׽�Ʈ�Է�", 9999, "LECT", "HUNI", ca);

			// Comic insert���� Category���� insert
			if (CategoryDAO.insert(con, ca)) {
				System.out.println("===========");
				System.out.println("���������� ī�װ��� INSERT �Ǿ����ϴ�.");
				System.out.println("===========");
				if (ComicDAO.insert(con, co)) {
					System.out.println("===========");
					System.out.println("���������� Comic�� INSERT �Ǿ����ϴ�.");
					System.out.println("===========");
					con.commit();
				} else {
					con.rollback();
					System.err.println("Comic INSERT�� �����߽��ϴ�.");
				}
			} else {
				con.rollback();
				System.err.println("Category INSERT�� �����߽��ϴ�.");
			}

			// ���󺹱��Ѵ�.
			// ���� ���� insert������ �ϳ��� ������ ���ٸ� �Ʒ� setAutoCommit�� ������ �ٽ� ���ư���.
			// ���� ���� insert���� ��� �����ϸ� ������ con.commit();�� �ϹǷ� ���⼭ setAutoCommit��
			// true�� �ص� �ٲ�� ���� ����.
			con.setAutoCommit(true);

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
