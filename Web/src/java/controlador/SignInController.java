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
import modelo.produccion.administracion.Finca;
import modelo.produccion.administracion.Persona;
import modelo.produccion.administracion.Rol;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "signInController")
@SessionScoped
public class SignInController implements Serializable {

    private String userString, passString, mensaje;
    private boolean credentialsOk;
    private boolean rolSelected;
    private Persona user;
    private Rol rol;
    private List<Rol> roles;
    private Finca finca;

    public SignInController() {
        EntityManagerFactorySingleton.getEntityManagerFactory();
    }
    
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

    public Finca getFinca() {
        return finca;
    }

    public void setFinca(Finca finca) {
        this.finca = finca;
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

    public Persona getUser() {
        return user;
    }

    public void setUser(Persona user) {
        this.user = user;
    }

    public boolean isCredentialsOk() {
        return credentialsOk;
    }

    public void setCredentialsOk(boolean credentialsOk) {
        this.credentialsOk = credentialsOk;
    }

    public boolean isRolSelected() {
        return rolSelected;
    }

    public void setRolSelected(boolean rolSelected) {
        this.rolSelected = rolSelected;
    }

    public String goToSignUp() {
        return "/faces/signUp.xhtml";
    }

    public String goToSignIn() {
        return "/faces/signIn.xhtml";
    }

    public String signIn() {
        if (!credentialsOk) {
            credentialsOk = new LoginDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).login(userString, passString);
            if (credentialsOk) {
                long userId = Long.parseLong(userString);
                user = new PersonaDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findPersonaPorCedula(userId);
                roles = new RolPersonaDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findRolEntities(user);
                if (roles != null && roles.size() == 1) {
                    rol = roles.get(0);
                    return signIn();
                } else if (roles == null || roles.isEmpty()) {
                    JsfUtil.addErrorMessage("El usuario no tiene roles asignados. Por favor contacte a un administrador de la finca.");
                    return signOut();
                }
            } else {
                JsfUtil.addErrorMessage("Usuario y/o contraseña inválidos! Por favor intente de nuevo.");
                return signOut();
            }
            return "";
        } else {
            mensaje = "Hola " + user + ", " + rol;
            JsfUtil.getSession().setAttribute("username", userString);
            rolSelected = true;
            return "/faces/secure/index.xhtml";
        }
    }

    public String signOut() {
        JsfUtil.getSession().invalidate();
        userString = null;
        passString = null;
        mensaje = null;
        credentialsOk = false;
        rolSelected = false;
        return "/faces/signIn.xhtml";
    }

}
