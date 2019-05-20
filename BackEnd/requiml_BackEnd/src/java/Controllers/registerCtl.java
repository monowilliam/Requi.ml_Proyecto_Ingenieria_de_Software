package Controllers;

import Classes.UserClass;
import Classes.messageClass;
import DB.DBMS;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "registerCtl")
@RequestScoped
public class registerCtl implements Serializable{
    private String name,last_name,indicative,cellphone,email,password,confirm_pass;
    private String str_out;
    
    public void register(){
        DBMS conx = new DBMS();
        ResultSet max_id;
        boolean possible = !(name.isEmpty() || email.isEmpty() || 
                password.isEmpty() || confirm_pass.isEmpty());
        if(possible){
            UserClass user = new UserClass();
            try {
                conx.Connect();
                max_id = conx.consultDB("SELECT MAX(IdUsuario) AS Maximo FROM usuarios");
                if(max_id.next()) user.setIdUsuario(Integer.parseInt(max_id.getString("Maximo"))+1);
                else user.setIdUsuario(1);
                if(password.equals(confirm_pass)){
                    user.setNombre(name);
                    user.setApellido(last_name);
                    user.setCelular(indicative + cellphone);
                    user.setCorreo(email);
                    user.setContraseña(password);
                    try {
                        user.addUser(user);
                        messageClass.info("info", "Registro exitoso!");
                    } catch (Exception ex) {
                        messageClass.error("Error!", "Error al acceder a la base de datos! " + ex);
                    }
                }else{
                    messageClass.error("Error!", "Contraseñas no coinciden");
                }
                conx.Disconnect();
            } catch (SQLException ex) {
                messageClass.warn("Error!", "inténtelo otra vez" + ex);
            }
        } else {
            messageClass.error("Error!", "Datos faltantes");
        }
    }

    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public String getLast_name(){return last_name;}
    public void setLast_name(String last_name){this.last_name = last_name;}
    public String getIndicative(){return indicative;}
    public void setIndicative(String indicative){this.indicative = indicative;}
    public String getCellphone(){return cellphone;}
    public void setCellphone(String cellphone){this.cellphone = cellphone;}
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}
    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}
    public String getConfirm_pass(){return confirm_pass;}
    public void setConfirm_pass(String confirm_pass){this.confirm_pass = confirm_pass;}
    public String getStr_out(){return str_out;}
    public void setStr_out(String str_out){this.str_out = str_out;}
    
    
    
}
