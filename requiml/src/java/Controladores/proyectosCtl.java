/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Clases.Sesion;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author johan
 */
@Named(value = "proyectosCtl")
@RequestScoped
public class proyectosCtl {
    private String str_out = "hey";
    
    public void SesionPrueba(){
        HttpSession hs = Sesion.getSession();
        str_out = (String) hs.getAttribute("correo");
    }

    public String getStr_out() {
        return str_out;
    }

    public void setStr_out(String str_out) {
        this.str_out = str_out;
    }
    
}
