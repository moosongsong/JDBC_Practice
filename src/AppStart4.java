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

			// 자동 commit을 끈다.
			con.setAutoCommit(false);

			Category ca = new Category("LECT", "학원");
			Comic co = new Comic(0, "테스트입력", 9999, "LECT", "HUNI", ca);

			// Comic insert전에 Category부터 insert
			if (CategoryDAO.insert(con, ca)) {
				System.out.println("===========");
				System.out.println("정상적으로 카테고리가 INSERT 되었습니다.");
				System.out.println("===========");
				if (ComicDAO.insert(con, co)) {
					System.out.println("===========");
					System.out.println("정상적으로 Comic이 INSERT 되었습니다.");
					System.out.println("===========");
					con.commit();
				} else {
					con.rollback();
					System.err.println("Comic INSERT에 실패했습니다.");
				}
			} else {
				con.rollback();
				System.err.println("Category INSERT에 실패했습니다.");
			}

			// 원상복귀한다.
			// 만약 위의 insert문에서 하나라도 에러가 난다면 아래 setAutoCommit을 만나서 다시 돌아간다.
			// 만약 위의 insert문이 모두 성공하면 위에서 con.commit();을 하므로 여기서 setAutoCommit을
			// trueㄹ 해도 바뀌는 것이 없다.
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
