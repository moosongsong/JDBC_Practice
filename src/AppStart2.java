
import java.util.ArrayList;

import dataaccessobject.CategoryDAO;
import dataaccessobject.ComicDAO;
import dataaccessobject.MyConnection;
import viewobject.Category;
import viewobject.Comic;

public class AppStart2 {

	public static void main(String[] args) {

		ArrayList<Category> categories = null;

		MyConnection.connect();
		categories = CategoryDAO.getCategory();
		for (Category category : categories) {
			category.setComics(ComicDAO.getComics(category));
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

		MyConnection.close();

		System.out.println();
		System.out.println("���α׷� ����");
	}

}
