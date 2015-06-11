package controller.controllers;

import model.monitoring.InsectTrap;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.monitoring.InsectTrapDAO;
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

@ManagedBean(name = "insectTrapController")
@SessionScoped
public class InsectTrapController implements Serializable {

    private InsectTrapDAO jpaController = null;
    private List<InsectTrap> items = null;
    private InsectTrap selected;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;

    public InsectTrapController() {
    }

    public InsectTrap getSelected() {
        return selected;
    }

    public void setSelected(InsectTrap selected) {
        this.selected = selected;
    }

    public PermissionController getPermissionBean() {
        return permissionBean;
    }

    public void setPermissionBean(PermissionController permissionBean) {
        this.permissionBean = permissionBean;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private InsectTrapDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new InsectTrapDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public InsectTrap prepareCreate() {
        selected = new InsectTrap();
        selected.setFarm(permissionBean.getSignInBean().getFarm());
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleInsectTrap").getString("InsectTrapCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleInsectTrap").getString("InsectTrapUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleInsectTrap").getString("InsectTrapDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<InsectTrap> getItems() {
        if (items == null || items.isEmpty()) {
            items = getJpaController().findInsectTrapEntities();
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

    public List<InsectTrap> getItemsAvailableSelectMany() {
        return getJpaController().findInsectTrapEntities();
    }

    public List<InsectTrap> getItemsAvailableSelectOne() {
        return getJpaController().findInsectTrapEntities();
    }

    @FacesConverter(forClass = InsectTrap.class)
    public static class InsectTrapControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            InsectTrapController controller = (InsectTrapController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "insectTrapController");
            return controller.getJpaController().findInsectTrap(getKey(value));
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
            if (object instanceof InsectTrap) {
                InsectTrap o = (InsectTrap) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), InsectTrap.class.getName()});
                return null;
            }
        }

    }

}
