package start_point;

import java.util.ArrayList;
import dao.CategoryDAO;
import dao.ComicDAO;
import dao.MyConnection;
import vo.Category;


public class AppStart {  

	public static void main(String[] args) {

		ArrayList<Category> categories = null;
		try {
			MyConnection.open();
			categories = CategoryDAO.getCategory();
			
			for (Category category : categories) {
				category.setComics(ComicDAO.getComics(category));
			}
			
		} catch (Exception e) {
			;
		} finally {
			MyConnection.close();
		}
		
		System.out.println("end...");
	}	
}
