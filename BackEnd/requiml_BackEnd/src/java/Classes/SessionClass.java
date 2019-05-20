package Classes;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named(value = "sessionClass")
@ViewScoped
public class SessionClass implements Serializable{

    public static HttpSession getSession(){
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }
    public static HttpServletRequest getRequest(){
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
    
    public static void signIn(UserClass user){
        // agregar un try-catch que en el catch lance el mensaje "no se ha iniciado sesión"
        HttpSession hs = SessionClass.getSession();
        hs.setAttribute("user",user);
        
    }
    
    public static void signOff(){
        HttpSession hs = SessionClass.getSession();
        hs.invalidate();
    }
    
    public void verifySession() throws IOException{
        // agregar un try-catch que en el catch lance el mensaje "no se ha iniciado sesión"
        HttpSession hs = SessionClass.getSession();
        UserClass us = (UserClass) hs.getAttribute("user");
        FacesContext context = FacesContext.getCurrentInstance(); 
        if(us == null) context.getExternalContext().redirect("/index.html");
        else context.getExternalContext().redirect("/projectManager.html");
    }
    
    public void verifySession2(){
        try{
            FacesContext context = FacesContext.getCurrentInstance();
            
        } catch (Exception e){
        }
    }
    
}
