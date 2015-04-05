/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.util.JsfUtil;
import datos.produccion.administracion.FincaDAO;
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
    private boolean paso1Completado;
    private boolean paso2Completado;
    private boolean paso3Completado;
    private Persona user;
    private Rol rol;
    private List<Rol> roles;
    private Finca finca;
    private List<Finca> fincas;

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

    public List<Finca> getFincas() {
        return fincas;
    }

    public void setFincas(List<Finca> fincas) {
        this.fincas = fincas;
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

    public boolean isPaso1Completado() {
        return paso1Completado;
    }

    public void setPaso1Completado(boolean paso1Completado) {
        this.paso1Completado = paso1Completado;
    }

    public boolean isPaso2Completado() {
        return paso2Completado;
    }

    public void setPaso2Completado(boolean paso2Completado) {
        this.paso2Completado = paso2Completado;
    }

    public boolean isPaso3Completado() {
        return paso3Completado;
    }

    public void setPaso3Completado(boolean paso3Completado) {
        this.paso3Completado = paso3Completado;
    }

    public String goToSignUp() {
        return "/faces/signUp.xhtml";
    }

    public String goToSignIn() {
        return "/faces/signIn.xhtml";
    }

    public String signIn() {
        if (!paso1Completado) {
            paso1Completado = new LoginDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).login(userString, passString);
            if (paso1Completado) {
                long userId = Long.parseLong(userString);
                user = new PersonaDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findPersonaPorCedula(userId);
                fincas = new FincaDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findFincaEntities(user);
                if (fincas == null || fincas.isEmpty()) {
                    JsfUtil.addErrorMessage("El usuario no tiene roles asignados. Por favor contacte a un administrador de la finca.");
                }
            } else {
                JsfUtil.addErrorMessage("Usuario y/o contraseña inválidos! Por favor intente de nuevo.");
                return signOut();
            }
        } else if (!paso2Completado) {
            roles = new RolPersonaDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findRolEntities(user);
            if (roles != null && roles.size() == 1) {
                rol = roles.get(0);
                return signIn();
            } else if (roles == null || roles.isEmpty()) {
                JsfUtil.addErrorMessage("El usuario no tiene roles asignados. Por favor contacte a un administrador de la finca.");
                return signOut();
            }
        } else {
            mensaje = "Hola " + user + ", " + rol;
            JsfUtil.getSession().setAttribute("username", userString);
            paso3Completado = true;
            return "/faces/secure/index.xhtml";
        }
        return "";
    }

    public String signOut() {
        JsfUtil.getSession().invalidate();
        userString = null;
        passString = null;
        mensaje = null;
        paso1Completado = false;
        paso2Completado = false;
        paso3Completado = false;
        return "/faces/signIn.xhtml";
    }

}
