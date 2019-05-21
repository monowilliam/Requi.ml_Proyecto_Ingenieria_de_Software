package Controllers;

import Classes.SessionClass;
import Classes.UserClass;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "projectMngCtl")
@ViewScoped
public class projectMngCtl implements Serializable{
    
    private UserClass user;
    
    private String str_out;
    
    public void verifySession(){
        try {
            user = SessionClass.verifySession();
            
            str_out = user.getCorreo();
        } catch (IOException ex) {
            Logger.getLogger(projectMngCtl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UserClass getUser(){return user;}
    public void setUser(UserClass user){this.user = user;}
    public String getStr_out(){return str_out;}
    public void setStr_out(String str_out){this.str_out = str_out;}
}
