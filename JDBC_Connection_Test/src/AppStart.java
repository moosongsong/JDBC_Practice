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
		//연결객체에 컨넥션을 만들어 사용이 가능하나 , 동기화가 힘들다.
		//컨넥션 관리 객체는 반드시 동기화!!!!!1
		//con객체로 동기화 블럭으로 가두기 ->속도 느려짐.
		
		//2
		//작업 별로 컨넥션을 사용하게끔 하는 것도 방법임. 메인쓰레드 마나 하나씩.
		ArrayList<Category> list = null;
		
		try {
			con = DriverManager.getConnection(url,user,pass);
			con.setAutoCommit(false);//자동커밋 안함.
			
			Category cate = new Category("LECT", "학원");
			Comic co = new Comic(0, "하늘산소닌", 20000, "LECT", "HUNI", cate);
			
			//카테고리 먼저 넣어야 함
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
			con.rollback();//이미 커밋 되면 롯백 하든 말든 상관없음.
			
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
