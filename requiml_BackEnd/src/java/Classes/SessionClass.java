package Classes;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named(value = "sessionClass")
public class SessionClass{

    public static HttpSession getSession(){
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }
    
    public static HttpServletRequest getRequest(){
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
    
    public static void signIn(UserClass user){
        HttpSession hs = SessionClass.getSession();
        hs.setAttribute("user",user);
        
    }
    
    public static void signOff(){
        HttpSession hs = SessionClass.getSession();
        hs.invalidate();
    }
    
    public static UserClass verifySession() throws IOException{
        HttpSession hs = SessionClass.getSession();
        UserClass us = (UserClass) hs.getAttribute("user");
        FacesContext context = FacesContext.getCurrentInstance(); 
        if(us == null) context.getExternalContext().redirect("/index.html");
        return us;
    }    
}
