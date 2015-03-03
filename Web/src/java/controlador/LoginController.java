/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.util.JsfUtil;
import datos.produccion.administracion.PersonaDAO;
import datos.produccion.administracion.RolPersonaDAO;
import datos.util.EntityManagerFactorySingleton;
import datos.util.LoginDAO;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.produccion.administracion.Persona;
import modelo.produccion.administracion.Rol;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String userString, passString, mensaje;
    private boolean loggedin;
    private Persona user;
    private Rol rol;
    private List<Rol> roles;

    public String getUserString() {
        return userString;
    }

    public void setUserString(String userString) {
        this.userString = userString;
    }

    public String getPassString() {
        return passString;
    }

    public void setPassString(String passString) {
        this.passString = passString;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
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

    public Persona getUser() {
        return user;
    }

    public void setUser(Persona user) {
        this.user = user;
    }

    public void login() {
        loggedin = new LoginDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).login(userString, passString);
        if (loggedin) {
            long userId = Long.parseLong(userString);
            user = new PersonaDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findPersonaPorCedula(userId);
            roles = new RolPersonaDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findRolEntities(user);
        } else {
            JsfUtil.addErrorMessage("Usuario y/o contraseña inválidos! Por favor intente de nuevo.");
        }
    }

    public String login2() {
        if (loggedin) {
            mensaje = "Hola " + user + ", "+rol;
            getSession().setAttribute("username", userString);
            return "/faces/secure/index.xhtml";
        } else {
            JsfUtil.addErrorMessage("Usuario y/o contraseña inválidos! Por favor intente de nuevo.");
            return "/faces/login.xhtml";
        }
    }

    public String logout() {
        getSession().invalidate();
        userString = null;
        passString = null;
        mensaje = null;
        loggedin = false;
        return "/faces/login.xhtml";
    }

    private static HttpSession getSession() {
        return (HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().
                getSession(false);
    }

}
