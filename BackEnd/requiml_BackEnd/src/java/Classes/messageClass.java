package Classes;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class messageClass {
    public static void info(String title, String msg){
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, title, msg));
    }
    public static void warn(String title, String msg){
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, title, msg));
    }
    public static void error(String title, String msg){
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, title, msg));
    }
}
