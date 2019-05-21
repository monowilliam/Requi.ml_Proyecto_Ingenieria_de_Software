package Controllers;

import Classes.SessionClass;
import Classes.UserClass;
import Classes.messageClass;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "loginCtl")
@RequestScoped
public class loginCtl implements Serializable {
    private String email, password;
    // to prove
    private String str_out;
    
    public String Login() throws Exception{
        String page = null;
        boolean can = false;
        if(!(email.isEmpty() || password.isEmpty())){
            try{
                UserClass user = new UserClass();
                user.UserInfo(email);
                if(user.getCorreo() != null) can = user.getContraseña().equals(password);
                if(can){
                    SessionClass.signIn(user);
                    page = "/projectManager.xhtml";
                }else{
                    messageClass.error("Error al iniciar sesión!","Correo o contraseña incorrectas");
                }
            }catch(Exception ex){
                // all errors will be interpreted like a error in the access to the DB 
                messageClass.error("Error!","Error al acceder a la base de datos "+ex);             
            }
        } else {
            messageClass.error("Error!","Campos vacios");
        }
        return page;
    }

    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}
    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}
    public String getStr_out(){return str_out;}
    public void setStr_out(String str_out){this.str_out = str_out;}
}
