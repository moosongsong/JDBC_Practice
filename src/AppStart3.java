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
			Category c = new Category("WARS", "전쟁");

			if (CategoryDAO.insert(con, c)) {
				System.out.println("===========");
				System.out.println("정상적으로 INSERT 되었습니다.");
				System.out.println("===========");
			} else
				System.err.println("INSERT에 실패했습니다.");

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
			System.out.println("분류 코드 : " + category.getCode());
			System.out.println("분류명 : " + category.getName());
			ArrayList<Comic> comics = category.getComics();
			System.out.print("해당 분류의 도서 목록 : ");
			for (Comic comic : comics) {
				System.out.print(comic.getTitle() + " | ");
			}
			System.out.println();
		}
	}
}
