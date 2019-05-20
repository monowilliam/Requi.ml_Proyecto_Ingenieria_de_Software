package DB;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBMS {
    private static final String DRIVER = "com.mysql.jdbc.Driver"; 
    private static final String URL = "jdbc:mysql://localhost:3306/prueba_numis";
    private static final String USER = "root", PASS = "";
    private Connection conx;
    private Statement DB;
    // to prove
    private boolean isConnect;
    
    public void DBMS(){
        isConnect = false;
        conx = null;
        DB = null;
    }

    public boolean Connect(){
        isConnect = false;
        try{
          Class.forName(DRIVER);
          conx = (Connection) DriverManager.getConnection(URL,USER,PASS);
          if(conx != null){DB = conx.createStatement(); isConnect = true;}
        } catch(ClassNotFoundException | SQLException e){
            isConnect = false;
        }
        return isConnect;
    }
    
    public boolean Disconnect(){
        isConnect = true;
        try{
            if(conx != null){
                if(!conx.isClosed()){conx.close(); DB.close(); isConnect = false;}
            }
        } catch(SQLException e){
            isConnect = true;
        }
        return isConnect;
    }
    
    public ResultSet consultDB(String query){
        ResultSet consult = null;
        try {
            consult = DB.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(DBMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consult;
    }
    
    public void insertDB(String opSQL){
        PreparedStatement DB1;
        try {
            DB1 = conx.prepareStatement(opSQL);
            DB1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBMS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConnect(){return conx;}
    public void setConnect(Connection conx){this.conx = conx;}
    public Statement getDB(){return DB;}
    public void setDB(Statement DB){this.DB = DB;}
    public boolean getIsConnect(){return isConnect;}
    public void setIsConnect(boolean isConnect){this.isConnect = isConnect;}
}
