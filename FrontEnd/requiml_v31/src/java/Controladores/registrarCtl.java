package Controladores;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import BD.SGBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "registrarCtl")
@RequestScoped
public class registrarCtl {
    private String nombre,apellido,correo,indicativo,celular,contraseña,contraseña2;
    private String str_out = "No registrado";

    public String registroUsuario() throws Exception{
        boolean complete;
        ResultSet usuarios,consulta_id;
        complete = !(nombre.isEmpty() || 
          correo.isEmpty() || contraseña.isEmpty() || contraseña2.isEmpty());
        if(complete){
            SGBD conx = new SGBD();
            int max_id;
            str_out = conx.Conectar();
            String query = "SELECT * FROM usuarios WHERE correo = '" + correo + "'";
            usuarios = conx.getBD().executeQuery(query);
            if(!usuarios.next()){
                consulta_id = conx.getBD().executeQuery("SELECT MAX(IdUsuario) FROM usuarios");
                if(consulta_id.next())
                    max_id = Integer.parseInt(consulta_id.getString("MAX(IdUsuario)"));
                else
                    max_id = 1;
                // str_out = Integer.toString(max_id);
                String opSQL = "INSERT INTO usuarios (IdUsuario,Nombre,Apellido,Celular,Correo,Contraseña,Descripcion)";
                opSQL += " VALUES("+Integer.toString(max_id+1)+", '"+nombre+"', '"+apellido+"', '"+indicativo+celular+"', '"+correo+"', '"+contraseña+"', NULL)";
                PreparedStatement BD = conx.getConnect().prepareStatement(opSQL);
                BD.executeUpdate();
                FacesMessage msg = new FacesMessage("Registro exitoso","INFO MSG");
                msg.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().addMessage(null,msg);
                str_out = "registro exitoso";
                // return "/index.xhtml";
            } else {
                FacesMessage msg = new FacesMessage("Error al registrar","ERROR MSG");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null,msg);
                str_out = "correo ya usado, intente otra vez";
            }
            conx.Desconectar();
        }
        return null;
    }

    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre = nombre;}
    public String getApellido(){return apellido;}
    public void setApellido(String apellido){this.apellido = apellido;}
    public String getCorreo(){return correo;}
    public void setCorreo(String correo){this.correo = correo;}
    public String getIndicativo(){return indicativo;}
    public void setIndicativo(String indicativo){this.indicativo = indicativo;}
    public String getCelular(){return celular;}
    public void setCelular(String celular){this.celular = celular;}
    public String getContraseña(){return contraseña;}
    public void setContraseña(String contraseña){this.contraseña = contraseña;}
    public String getContraseña2(){return contraseña2;}
    public void setContraseña2(String contraseña2){this.contraseña2 = contraseña2;}
    public String getStr_out(){return str_out;}
    public void setStr_out(String str_out){this.str_out = str_out;}
}

