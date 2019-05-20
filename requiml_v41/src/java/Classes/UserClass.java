package Classes;

import DB.DBMS;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserClass {
    // the same names that are register in the Data Base (DB)
    private int IdUsuario;
    private String nombre,apellido,celular,correo,contraseña,descripcion,titulo,foto;
    
    public UserClass(){
        IdUsuario = -1;
        nombre=null;apellido=null;celular=null;correo=null;
        contraseña=null;descripcion=null;titulo=null;foto=null;
    }

    public void UserInfo(String email) throws Exception {
        DBMS conx = new DBMS();
        ResultSet user;
        String query = "SELECT * FROM usuarios WHERE correo = '" + email + "'";
        try {
            conx.Connect();
            user = conx.consultDB(query);
            if(user.next()){
                this.setIdUsuario(Integer.parseInt(user.getString("IdUsuario")));
                this.setNombre(user.getString("nombre"));
                this.setApellido(user.getString("apellido"));
                this.setCelular(user.getString("celular"));
                this.setCorreo(user.getString("correo"));
                this.setContraseña(user.getString("contrasena"));
                this.setDescripcion(user.getString("descripcion"));
                this.setTitulo(user.getString("titulo"));
                this.setFoto(user.getString("foto"));
            }
            conx.Disconnect();
        } catch (NumberFormatException | SQLException ex) {
            throw ex;
        }
    }
    
    public static void addUser(UserClass user){
        DBMS conx = new DBMS();
        String opSQL = "INSERT INTO usuarios VALUES";
        opSQL += "("+Integer.toString(user.getIdUsuario())+",'"+user.getNombre()+"','"+user.getApellido()+"',";
        opSQL += "'"+user.getCelular()+"'"+",'"+user.getCorreo()+"','"+user.getContraseña()+"',NULL,NULL,NULL)";
        try {
            conx.Connect();
            conx.insertDB(opSQL);
            conx.Disconnect();
        } catch (Exception ex) {
            Logger.getLogger(UserClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getIdUsuario(){return IdUsuario;}
    public void setIdUsuario(int IdUsuario){this.IdUsuario = IdUsuario;}
    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre = nombre;}
    public String getApellido(){return apellido;}
    public void setApellido(String apellido){this.apellido = apellido;}
    public String getCelular(){return celular;}
    public void setCelular(String celular){this.celular = celular;}
    public String getCorreo(){return correo;}
    public void setCorreo(String correo){this.correo = correo;}
    public String getContraseña(){return contraseña;}
    public void setContraseña(String contraseña){this.contraseña = contraseña;}
    public String getDescripcion(){return descripcion;}
    public void setDescripcion(String descripcion){this.descripcion = descripcion;}
    public String getTitulo(){return titulo;}
    public void setTitulo(String titulo){this.titulo = titulo;}
    public String getFoto(){return foto;}
    public void setFoto(String foto){this.foto = foto;}
}
