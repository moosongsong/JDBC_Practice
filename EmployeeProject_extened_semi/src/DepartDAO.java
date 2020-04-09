import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DepartDAO {
  private static DepartDAO instance = new DepartDAO();
  private String currentDept;
  private ArrayList<Depart> list;

  private DepartDAO(){
    list = new ArrayList<>();
  }

  public static DepartDAO getInstance() {
    return instance;
  }

 public void setList(ArrayList<Depart> list){
   this.list = list;
 }

 public ArrayList<Depart> getList(){
   return this.list;
 }

 public boolean setDepartList(Connection con, String depart){
   PreparedStatement stmt = null;
   ResultSet rs = null;
   boolean result = false;

   try{
     if(depart == null){
       stmt = con.prepareStatement(....);
     }
     else{
       stmt = con.prepareStatement(....);
       stmt.setString(1,depart);
     }
     ra=stmt.executeQuery();

     list.clear();

     if(rs.next()){
       String code = rs.getString("dept_code");
       String name = rs.getString("dept_name");
       Depart d = new Depart(code, name);
       list.add(d);
     }

     this.currentDept = dept;
     if(this.currentDept == null){
       this.currentDept = "ALL";
     }
     result = true;
   }
   catch (SQLException e){
     e.printStackTrace();
     return result;
   }
   finally {
     try { rs.close(); } catch(Exception e) {}
     try { stmt.close(); } catch(Exception e) {}
     try { con.close(); } catch(Exception e) {}
   }
   return result;
 }

 public String getCurrentDept(){
   return currentDept;
 }
 //
 public boolean validate(Connection con, String code) {
   Statement stmt = null;
   ResultSet rs = null;
   boolean result = false;
   try {
     stmt = con.createStatement();
     rs = stmt.executeQuery("SELECT COUNT(*) AS cnt FROM departs WHERE type_code = '" + code + "'");
     if (rs.next()) {
       result = (rs.getInt("cnt") > 0);
     }
   }
   catch (SQLException e) {
     e.printStackTrace();
     return false;
   }
   finally {
     try { rs.close(); } catch (Exception e) {}
     try { stmt.close(); } catch (Exception e) {}
   }
   return result;
 }

 //insert
 public boolean insert(Connection con, Depart dept) {
   return insert(con, dept.getDept_code(), dept.getDept_name());
 }
 public boolean insert(Connection con, String code, String name) {
   boolean result = false;
   PreparedStatement pstmt = null;

   try {
     pstmt = con.prepareStatement(SQLManager.SQL_INSERT_TYPE);

     pstmt.setString(1, code);
     pstmt.setString(2, name);

     result = (pstmt.executeUpdate()>0);
   } catch (SQLException e) {
     System.out.println("FAIL....(insert)");
     return false;
   } finally {
     try {
       pstmt.close();
     } catch (SQLException e) {
       System.out.println("CLOSE FAIL...");
     }
   }

   return result;
 }

 //delete
 public boolean delete(Connection con, Depart dept) {
   return delete(con, dept.getDept_code());
 }
 public boolean delete(Connection con, String code) {
   boolean result = false;
   PreparedStatement pstmt = null;

   if(code == null){
     return false;
   }

   try {
     con.setAutoCommit(false);
     String sql = "DELETE FROM employees where dept_code LIKE '"+code+"';";

     if(EmployeeDAO.getInstance().executeUpdate(con,sql)){
       pstmt = con.prepareStatement(SQLManager.SQL_DELETE_TYPE);
       stmt.setString(1, code);
       result = (pstmt.executeUpdate()>0);
       if(result){
         con.commit();
       }
     }
   }
   catch (SQLException e) {
     System.out.println("FAIL...");
     return result;
   } finally {
      try { stmt.close(); } catch (Exception e) {}
      try { con.rollback(); } catch (Exception e) {}
      try { con.setAutoCommit(true); } catch (Exception e) {}
     }
   }
   return result;
 }
 //update
 public boolean update(Connection con, Depart dept) {
   return update(con, dept.getDept_code(), dept.getDept_name());
 }
 public boolean update(Connection con, String code, String name) {
   boolean result = false;
	 PreparedStatement stmt = null;

   String sql = "Update departs set dept_name like '"+name+"' "
       + "where dept_code = '"+code+"';";

   try {
     stmt = con.prepareStatement(SQLManager.SQL_UPDATE_TYPE);
     stmt.setString(1, name);
     stmt.setString(2, code);
     result = (stmt.executeUpdate() > 0);
   } catch (SQLException e) {
     System.out.println("FAIL...");
     return false;
   } finally {
     try {
       stmt.close();
     } catch (SQLException e) {
       System.out.println("CLOSE FAIL...");
     }
   }
   return result;
 }
}
