import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DutieDAO {
  private static DutieDAO instance = new DutieDAO();
  private String currentDutie;
  private ArrayList<Dutie> list;

  pivate DutieDAO(){
    list = new ArrayList<>();
  }

  public static DutieDAO getInstance(){
    return instance;
  }

  public void setList(ArrayList<Dutie> list){
    this.list = list;
  }

  public ArrayList<Dutie> getList(){
    return this.list;
  }

  public boolean setDutieList(Connection con, String dutie){
    PreparedStatement stmt = null;
    ResultSet rs = null;
    boolean result = false;


    try{
      if(dutie == null){
        stmt = con.prepareStatement(....);
      }
      else{
        stmt = con.prepareStatement(....);
        stmt.setString(1,dutie);
      }
      ra=stmt.executeQuery();

      list.clear();

      if(rs.next()){
        String code = rs.getString("duty_code");
        String name = rs.getString("duty_name");
        Depart d = new Depart(code, name);
        list.add(d);
      }

      this.currentDept = dutie;
      if(this.currentDutie == null){
        this.currentDutie = "ALL";
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

  public String getCurrentDutie(){
    return currentDutie;
  }



}
