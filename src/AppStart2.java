
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
			System.out.println("분류 코드 : " + category.getCode());
			System.out.println("분류명 : " + category.getName());
			ArrayList<Comic> comics = category.getComics();
			System.out.print("해당 분류의 도서 목록 : ");
			for (Comic comic : comics) {
				System.out.print(comic.getTitle() + " | ");
			}
			System.out.println();
		}

		MyConnection.close();

		System.out.println();
		System.out.println("프로그램 종료");
	}

}
