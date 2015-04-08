/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.controllers;

import controlador.util.JsfUtil;
import datos.produccion.administracion.ContratoDAO;
import datos.produccion.administracion.FincaDAO;
import datos.produccion.administracion.PersonaDAO;
import datos.util.EntityManagerFactorySingleton;
import datos.util.LoginDAO;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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

    private String userString;
    private String passString;
    private boolean step1userSignedIn;
    private boolean step2farmSelected;
    private boolean step3rolSelected;
    private Persona user;
    private Rol rol;
    private List<Rol> roles;
    private Finca finca;
    private List<Finca> fincas;
    private boolean hasActiveAccount;

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

    public boolean isStep1userSignedIn() {
        return step1userSignedIn;
    }

    public void setStep1userSignedIn(boolean step1userSignedIn) {
        this.step1userSignedIn = step1userSignedIn;
    }

    public boolean isStep2farmSelected() {
        return step2farmSelected;
    }

    public void setStep2farmSelected(boolean step2farmSelected) {
        this.step2farmSelected = step2farmSelected;
    }

    public boolean isStep3rolSelected() {
        return step3rolSelected;
    }

    public void setStep3rolSelected(boolean step3rolSelected) {
        this.step3rolSelected = step3rolSelected;
    }

    public Persona getUser() {
        return user;
    }

    public void setUser(Persona user) {
        this.user = user;
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

    public Finca getFinca() {
        return finca;
    }

    public void setFinca(Finca finca) {
        this.finca = finca;
    }

    public List<Finca> getFincas() {
        return fincas;
    }

    public void setFincas(List<Finca> fincas) {
        this.fincas = fincas;
    }

    public boolean isHasActiveAccount() {
        return hasActiveAccount;
    }

    public void setHasActiveAccount(boolean hasActiveAccount) {
        this.hasActiveAccount = hasActiveAccount;
    }

    public String goToSignUp() {
        return "/faces/signUp.xhtml";
    }

    public String goToSignIn() {
        return "/faces/signIn.xhtml";
    }

    public String signIn() {
        if (!step1userSignedIn) {
            step1userSignedIn = new LoginDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).login(userString, passString);
            if (step1userSignedIn) {
                long userId = Long.parseLong(userString);
                user = new PersonaDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findPersonaPorCedula(userId);
                fincas = new FincaDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findFincaEntitiesForCurrentUser(user);
                hasActiveAccount = new CuentaController().hasActiveAccount(user);
                if (!user.isAdministrador() && (fincas == null || fincas.isEmpty()) && !hasActiveAccount) {
                    JsfUtil.addErrorMessage("El usuario no tiene roles asignados. Por favor contacte al administrador de la finca.");
                    return signOut();
                } else if (fincas!=null && fincas.size() == 1) {
                    finca = fincas.get(0);
                    return signIn();
                }
            } else {
                JsfUtil.addErrorMessage("Usuario y/o contraseña inválidos! Por favor intente de nuevo.");
                return signOut();
            }
        } else if (!step2farmSelected) {
            if (finca != null) {
                step2farmSelected = true;
                roles = new ContratoDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findRolEntities(user, finca);
                if (roles != null && roles.size() == 1) {
                    rol = roles.get(0);
                    return signIn();
                } else if (roles == null || roles.isEmpty()) {
                    if (hasActiveAccount || user.isAdministrador()) {
                        return "/faces/secure/index.xhtml";
                    } else {
                        JsfUtil.addErrorMessage("El usuario no tiene roles asignados. Por favor contacte a un administrador de la finca.");
                        return signOut();
                    }
                }
            } else {
                if (user.isAdministrador()) {
                    return "/faces/secure/index.xhtml";
                } else {
                    JsfUtil.addErrorMessage("El campo Finca es obligatorio");
                }
            }
        } else {
            step3rolSelected = true;
            return "/faces/secure/index.xhtml";
        }
        return "";
    }

    public String signOut() {
        JsfUtil.getSession().invalidate();
        userString = null;
        passString = null;
        step1userSignedIn = false;
        step2farmSelected = false;
        step3rolSelected = false;
        System.out.println("Sesion anulada");
        return "/faces/signIn.xhtml";
    }

}
