/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.util.JsfUtil;
import datos.util.EntityManagerFactorySingleton;
import datos.util.LoginDAO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String user, pass, mensaje;
    private boolean loggedin;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }

    public String login() {
        loggedin = new LoginDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).login(user, pass);
        if (loggedin) {
            // get Http Session and store username
            mensaje = "Hola " + user + "!";
            HttpSession session = getSession();
            session.setAttribute("username", user);
            return "/index.xhtml";
        } else {
            JsfUtil.addErrorMessage("Invalid Login! Please Try Again!");
            return "/login.xhtml";
        }
    }

    public String logout() {
        HttpSession session = getSession();
        session.invalidate();
        user = null;
        pass = null;
        mensaje = null;
        loggedin = false;
        return "/login.xhtml";
    }

    private static HttpSession getSession() {
        return (HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().
                getSession(false);
    }

}
