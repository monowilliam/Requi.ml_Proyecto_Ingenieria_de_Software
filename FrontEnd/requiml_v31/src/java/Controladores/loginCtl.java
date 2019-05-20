package Controladores;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import BD.SGBD;
import Clases.Sesion;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


@Named(value = "loginCtl")
@RequestScoped
public class loginCtl {
    private String correo, contraseña, str_out = "funciono";
    
    public String iniciarSesion() throws Exception{
        SGBD conx = new SGBD();
        ResultSet usuarios;
        String page = null;
        boolean can = false;
        String query = "SELECT * FROM usuarios ";
        query += "WHERE correo = '" + correo + "'";
        if(contraseña == null) str_out = "papi, es null";
        if(!(correo.isEmpty() || contraseña == null)){
            str_out = "entro!"; 
            try{
                str_out = conx.Conectar();
                usuarios = conx.getBD().executeQuery(query);
                if(usuarios.next()) can = usuarios.getString("contraseña").equals(contraseña);                
                // str_out = Boolean.toString(can);
                if(can){
                    // str_out = "funciono melo caramelo";
                    HttpSession hs = Sesion.getSession();
                    hs.setAttribute("correo",correo);
                    page = "/gestionproyecto.xhtml";
                } else {
                    // str_out = "nonas";
                    FacesMessage msg = new FacesMessage("Error al iniciar sesion","ERROR MSG");
                    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                    FacesContext.getCurrentInstance().addMessage(null,msg);
                }
                conx.Desconectar();
            } catch(Exception e){
                throw e;
            }
        }
        return page;
    }
    
    public String cerrarSesion(){
        HttpSession hs = Sesion.getSession();
        hs.invalidate();
        return "/login.xhtml";
    }
    
    public String getCorreo(){return correo;}
    public void setCorreo(String correo){this.correo = correo;}
    public String getContraseña(){return contraseña;}
    public void setContraseña(String contraseña){this.contraseña = contraseña;}
    public String getStr_out(){return str_out;}
    public void setStr_out(String str_out){this.str_out = str_out;}
}
