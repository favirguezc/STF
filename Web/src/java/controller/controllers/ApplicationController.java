package controller.controllers;

import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.applications.ApplicationDAO;
import data.util.EntityManagerFactorySingleton;
import java.io.Serializable;
import java.util.ArrayList;
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
import model.applications.Application;
import model.applications.Chemical;

@ManagedBean(name = "applicationController")
@SessionScoped
public class ApplicationController implements Serializable {

    private ApplicationDAO jpaController = null;
    private List<Application> items = null;
    private Application selected;
    private List<Chemical> chemicals;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;

    public ApplicationController() {
    }

    public Application getSelected() {
        return selected;
    }

    public void setSelected(Application selected) {
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

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ApplicationDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new ApplicationDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Application prepareCreate() {
        selected = new Application();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ApplicationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ApplicationUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ApplicationDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Application> getItems() {
        if (items == null) {
            items = getJpaController().findApplicationEntitiesForSelectedFarm(signInBean.getFarm());
        }
        return items;
    }

    public List<Chemical> getChemicals() {
        chemicals = new ArrayList<Chemical>();
        for (Chemical i : new ChemicalController().getItems()) {
            if (i.getApplicationType() == selected.getType()) {
                chemicals.add(i);
            }
        }
        return chemicals;
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

    public List<Application> getItemsAvailableSelectMany() {
        return getItems();
    }

    public List<Application> getItemsAvailableSelectOne() {
        return getItems();
    }

    @FacesConverter(forClass = Application.class)
    public static class ApplicationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ApplicationController controller = (ApplicationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "applicationController");
            return controller.getJpaController().findApplication(getKey(value));
        }

        long getKey(String value) {
            long key;
            key = Long.parseLong(value);
            return key;
        }

        String getStringKey(long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Application) {
                Application o = (Application) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Application.class.getName()});
                return null;
            }
        }

    }

}
