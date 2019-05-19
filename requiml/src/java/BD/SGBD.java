package BD;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SGBD {
    private String str_out;
    private Connection connect;
    private Statement BD;
    private static final String DRIVER = "com.mysql.jdbc.Driver"; 
    private static final String URL = "jdbc:mysql://localhost:3306/prueba_numis";
    private static final String USER = "root", PASS = "";
    
    public void SGBD(){
        str_out = "";
        connect = null;
        BD = null;
    }
    
    public String Conectar() throws Exception{
        // connect = null;
        try{
          Class.forName(DRIVER);
          connect = (Connection) DriverManager.getConnection(URL,USER,PASS);
          if(connect != null){BD = connect.createStatement(); str_out = "Conectó";}
        } catch(Exception e){
            str_out = "No conectó";
            throw e;
        }
        return str_out;
    }
    public String Desconectar() throws Exception{
        try{
            if(connect != null){
                if(!connect.isClosed()){connect.close(); BD.close();}
            }
        } catch(Exception e){
            throw e;
        }
        return "Desconectado";
    }
    public String getStr_out(){return str_out;}
    public void setStr_out(String str_out){this.str_out = str_out;}
    public Connection getConnect(){return connect;}
    public void setConnect(Connection connect){this.connect = connect;}
    public Statement getBD(){return BD;}
    public void setBD(Statement BD){this.BD = BD;}
}
