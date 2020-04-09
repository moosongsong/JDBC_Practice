import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ArticleDAO {
	private static ArticleDAO instance = new ArticleDAO();

	private ArticleDAO() {
		;
	}

	public static ArticleDAO getInstance() {
		return instance;
	}

	public boolean insert(Connection con, Article article) {
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "Insert into articles values (?,?,?,?,?,?);";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, getNextInt(con, article.getP_code()));
			pstmt.setString(2, article.getP_code());
			pstmt.setString(3, article.getWriter());
			pstmt.setString(4, article.getTitle());
			pstmt.setString(5, article.getText());

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nownow = sdf.format(article.getRegdate());

			pstmt.setString(6, nownow);

			if(pstmt.executeUpdate()>0) {
				System.out.println("Success!!!!");
			}
			else {
				throw new Exception("i don't know about this error");
			}
		} catch (SQLException e) {
			System.out.println("FAIL");
		} catch (Exception e) {
			System.out.println("FAIL");
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("FAIL");
			}
		}
		return result;
	}

	public boolean insert(Connection con, String code, String writer,
			String title, String text, String regdate) {
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "Insert into articles values (?,?,?,?,?,?);";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, getNextInt(con, code));
			pstmt.setString(2, code);
			pstmt.setString(3, writer);
			pstmt.setString(4, title);
			pstmt.setString(5, text);

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nownow = sdf.format(regdate);

			pstmt.setString(6, nownow);

			if(pstmt.executeUpdate()>0) {
				System.out.println("Success!!!!");
			}
			else {
				throw new Exception("i don't know about this error");
			}
		} catch (SQLException e) {
			System.out.println("FAIL");
		} catch (Exception e) {
			System.out.println("FAIL");
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("FAIL");
			}
		}

		return result;
	}

	public int getNextInt(Connection con, String code) throws SQLException {
		int id=0;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select ifnull (max(id), 0) +1 AS nextId from articles where p_code like '"+code+"';");
			if(rs.next()) {
				id = rs.getInt("nextId");
			}

		} catch (SQLException e) {
			System.err.println("there is problems when computes articles table's id....");
			throw e;
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {

			}
			try {
				stmt.close();
			} catch (SQLException e) {

			}
		}


		return id;
	}

	
	//글삭제
	public boolean delete(Connection con, Article article) {
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "Delete from articles where id = "+article.getId()+
					" and p_code like '"+article.getP_code()+"';";

		try {
			pstmt = con.prepareStatement(sql);

			if(pstmt.executeUpdate()>0) {
				System.out.println("Success!!!");
			}
			else {
				throw new Exception();
			}

		} catch (SQLException e) {
			System.out.println("fail");
		} catch (Exception e) {
			System.out.println("fail");
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("close fail");
			}
		}
		return result;
	}


	//글편집
//	public boolean update(Connection con, )


	//글검색
	public ArrayList<Article> getArticleList(Connection con){
		ArrayList<Article> list = new ArrayList<Article>();

		String sql = "Select * from artlcles;";
		Statement stmt = null;
		ResultSet rs = null;


		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				Article temp = new Article();

				temp.setId(rs.getInt(1));
				temp.setP_code(rs.getString(2));
				temp.setWriter(rs.getString(3));
				temp.setText(rs.getString(4));
				temp.setText(rs.getString(5));
				temp.setRegdate(rs.getString(6));

				list.add(temp);
				System.out.println("Success!!!!");
			}


		} catch (SQLException e) {
			System.out.println("FAIL");
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("close fail");
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println("close fail");
			}
		}

		return list;
	}

}
