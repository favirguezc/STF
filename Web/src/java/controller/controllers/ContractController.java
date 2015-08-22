package controller.controllers;

import model.administration.Contract;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.administration.ContractDAO;
import data.administration.PersonDAO;
import data.util.EntityManagerFactorySingleton;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.administration.Person;

@ManagedBean(name = "contractController")
@SessionScoped
public class ContractController implements Serializable {

    private ContractDAO jpaController = null;
    private List<Contract> items = null;
    private Contract selected;
    private long cedula;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;

    public ContractController() {
    }

    public Contract getSelected() {
        return selected;
    }

    public void setSelected(Contract selected) {
        this.selected = selected;
    }

    public PermissionController getPermissionBean() {
        return permissionBean;
    }

    public void setPermissionBean(PermissionController permissionBean) {
        this.permissionBean = permissionBean;
    }

    public SignInController getSignInBean() {
        return signInBean;
    }

    public void setSignInBean(SignInController signInBean) {
        this.signInBean = signInBean;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ContractDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new ContractDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Contract prepareCreate() {
        selected = new Contract();
        if (signInBean.getFarm() != null) {
            selected.setFarm(signInBean.getFarm());
        } else {
            JsfUtil.addErrorMessage("Seleccione una finca");
            selected = null;
        }
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        Person findPersonPorCedula = new PersonDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findPersonByIdNumber(cedula);
        if (findPersonPorCedula == null) {
            JsfUtil.addErrorMessage("La cédula no está registrada.");
            selected=null;
        } else {
            selected.setPerson(findPersonPorCedula);
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleContract").getString("ContractCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        }

    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleContract").getString("ContractUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleContract").getString("ContractDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Contract> getItems() {
        if (items == null || items.isEmpty()) {
            items = getJpaController().findContractEntities(signInBean.getFarm());
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            if (!permissionBean.currentUserHasPermission(persistAction, selected.getClass())) {
                return;
            }
            try {
                if (persistAction == PersistAction.UPDATE) {
                    getJpaController().edit(selected);
                } else if (persistAction == PersistAction.CREATE) {
                    getJpaController().create(selected);
                } else {
                    getJpaController().destroy(selected.getId());
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Contract> getItemsAvailableSelectMany() {
        return getJpaController().findContractEntities();
    }

    public List<Contract> getItemsAvailableSelectOne() {
        return getJpaController().findContractEntities();
    }

    Iterable<Contract> getItems(Person user) {
        return getJpaController().findContractEntities(user);
    }

    @FacesConverter(forClass = Contract.class)
    public static class ContractControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContractController controller = (ContractController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contractController");
            return controller.getJpaController().findContract(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Contract) {
                Contract o = (Contract) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Contract.class.getName()});
                return null;
            }
        }

    }

}
