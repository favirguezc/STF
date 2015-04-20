/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controllers;

import controller.util.JsfUtil;
import data.administration.ContractDAO;
import data.administration.FarmDAO;
import data.administration.PersonDAO;
import data.util.EntityManagerFactorySingleton;
import data.util.LoginDAO;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.administration.Farm;
import model.administration.Person;
import model.administration.RoleEnum;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "signInController")
@SessionScoped
public class SignInController implements Serializable {

    private String userString;
    private String passString;
    private String buttonMessage;
    private boolean step1userSignedIn;
    private boolean step2farmSelected;
    private boolean step3rolSelected;
    private Person user;
    private RoleEnum role;
    private List<RoleEnum> roles;
    private Farm farm;
    private List<Farm> farms;

    public SignInController() {
        EntityManagerFactorySingleton.getEntityManagerFactory();
        buttonMessage = "Iniciar Sesión";
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

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public String getButtonMessage() {
        return buttonMessage;
    }

    public void setButtonMessage(String buttonMessage) {
        this.buttonMessage = buttonMessage;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEnum> roles) {
        this.roles = roles;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public List<Farm> getFarms() {
        return farms;
    }

    public void setFarms(List<Farm> farms) {
        this.farms = farms;
    }

    public String goToSignUp() {
        return "/faces/signUp.xhtml";
    }

    public String goToSignIn() {
        return "/faces/signIn.xhtml";
    }

    public String selectFarm() {
        step2farmSelected = false;
        step3rolSelected = false;
        farm = null;
        role = null;
        buttonMessage = "Seleccionar Farm";
        return goToSignIn();
    }

    public String selectRol() {
        step3rolSelected = false;
        role = null;
        buttonMessage = "Seleccionar Rol";
        return goToSignIn();
    }

    public String signIn() {
        if (!step1userSignedIn) {
            step1userSignedIn = new LoginDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).login(userString, passString);
            if (step1userSignedIn) {
                long userId = Long.parseLong(userString);
                user = new PersonDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findPersonByIdNumber(userId);
                farms = new FarmDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findFarmEntitiesForCurrentUser(user);
                if (!user.isSystemAdmin() && (farms == null || farms.isEmpty())) {
                    JsfUtil.addErrorMessage("El usuario no tiene roles asignados. Por favor contacte al administrador de la finca.");
                    return signOut();
                } else if (farms != null && farms.size() == 1) {
                    farm = farms.get(0);
                    buttonMessage = "Seleccionar Finca";
                    return signIn();
                } else if (farms == null || farms.isEmpty()) {
                    return "/faces/secure/index.xhtml";
                }
            } else {
                JsfUtil.addErrorMessage("Usuario y/o contraseña inválidos! Por favor intente de nuevo.");
                return signOut();
            }
        } else if (!step2farmSelected) {
            if (farm != null) {
                step2farmSelected = true;
                roles = new ContractDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findRolEntities(user, farm);
                if (roles != null && roles.size() == 1) {
                    role = roles.get(0);
                    buttonMessage = "Seleccionar Rol";
                    return signIn();
                } else if (roles == null || roles.isEmpty()) {
                    if (user.isSystemAdmin()) {
                        return "/faces/secure/index.xhtml";
                    } else {
                        JsfUtil.addErrorMessage("El usuario no tiene roles asignados. Por favor contacte a un administrador de la farm.");
                        return signOut();
                    }
                }
            } else {
                if (user.isSystemAdmin()) {
                    return "/faces/secure/index.xhtml";
                } else {
                    JsfUtil.addErrorMessage("El campo Farm es obligatorio");
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
        user = null;
        farm = null;
        role = null;
        step1userSignedIn = false;
        step2farmSelected = false;
        step3rolSelected = false;
        buttonMessage = "Iniciar Sesión";
        System.out.println("Sesion anulada");
        return "/faces/signIn.xhtml";
    }

}
