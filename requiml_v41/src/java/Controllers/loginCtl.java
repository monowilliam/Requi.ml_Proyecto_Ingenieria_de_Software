package Controllers;

import Classes.SessionClass;
import Classes.UserClass;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "loginCtl")
@RequestScoped
public class loginCtl {
    private String email, password;
    // to prove
    private String str_out;
    
    public String Login() throws Exception{
        String page = null;
        boolean can = false;
        if(!(email.isEmpty() || password.isEmpty())){
            /*
            DBMS conx = new DBMS();
            String query = "SELECT * FROM usuarios WHERE correo = '" + email + "'";
            try{
                //ResultSet user = UserClass.UserInfo(email); //have a error
                // pasar todo lo realizado con el ResultSet al metodo de la clase
                // UserClass, cambiando a que retorne un UserClass y no un ResultSet
                conx.Connect();
                ResultSet user = conx.consultDB(query);
                if(user.next()) can = user.getString("contraseña").equals(password);
                if(can){
                    str_out = "inicio sesion";
                    
                    SessionClass.signIn(us);
                    page = null;
                }
                conx.Disconnect();
            }catch(Exception e){
                // all errors will be interpreted like a error in the access to the DB 
                FacesContext context = FacesContext.getCurrentInstance();
                String msg = "Error al acceder a la base de datos";
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",""+e));                
            }
            */
            try{
                UserClass user = new UserClass();
                user.UserInfo(email);
                str_out = user.getCorreo();
                if(user != null) can = user.getContraseña().equals(password);
                if(can){
                    str_out = "inicio sesion";
                    SessionClass.signIn(user);
                    page = "/projectManager.xhtml";
                }else{
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al iniciar sesión!","Correo o contraseña incorrectas"));
                }
            }catch(Exception e){
                // all errors will be interpreted like a error in the access to the DB 
                FacesContext context = FacesContext.getCurrentInstance();
                String msg = "Error al acceder a la base de datos";
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!",""+e));                
            }
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
