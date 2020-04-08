import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.CategoryDAO;
import dao.ComicDAO;
import vo.Category;
import vo.Comic;

public class AppStart {
	static final String url = "jdbc:mariadb://localhost:3306/example";
	static final String user = "example";
	static final String pass = "1234";
	
	public static void main(String[] args) {
		Connection con = null;
		//1
		//���ᰴü�� ���ؼ��� ����� ����� �����ϳ� , ����ȭ�� �����.
		//���ؼ� ���� ��ü�� �ݵ�� ����ȭ!!!!!1
		//con��ü�� ����ȭ ������ ���α� ->�ӵ� ������.
		
		//2
		//�۾� ���� ���ؼ��� ����ϰԲ� �ϴ� �͵� �����. ���ξ����� ���� �ϳ���.
		ArrayList<Category> list = null;
		
		try {
			con = DriverManager.getConnection(url,user,pass);
			con.setAutoCommit(false);//�ڵ�Ŀ�� ����.
			
			Category cate = new Category("LECT", "�п�");
			Comic co = new Comic(0, "�ϴû�Ҵ�", 20000, "LECT", "HUNI", cate);
			
			//ī�װ� ���� �־�� ��
			if(CategoryDAO.insert(con, cate)==true) {
				if(ComicDAO.insert(con, co)==true) {
					con.commit();
					System.out.println("Success!!!");
				}
				else {
					System.out.println("comic Fail...");
				}
			}else {
				System.out.println("category fail...");
			}
			con.rollback();//�̹� Ŀ�� �Ǹ� �Թ� �ϵ� ���� �������.
			
			con.setAutoCommit(true);
			
				
			list = CategoryDAO.getCategory(con);
			
			for (int j = 0; j < list.size(); j++) {
				Category category = list.get(j);
				category.setComics(ComicDAO.getComics(con, category));
			}
			
		} catch (SQLException e) {
			;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				;
			}
		}
	}

}
