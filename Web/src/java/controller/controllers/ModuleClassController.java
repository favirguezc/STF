package controller.controllers;

import model.administration.ModuleClass;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.administration.ModuleDAO;
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

@ManagedBean(name = "moduleClassController")
@SessionScoped
public class ModuleClassController implements Serializable {

    private ModuleDAO jpaController = null;
    private List<ModuleClass> items = null;
    private ModuleClass selected;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;

    public ModuleClassController() {
    }

    public ModuleClass getSelected() {
        return selected;
    }

    public void setSelected(ModuleClass selected) {
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

    private ModuleDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new ModuleDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public ModuleClass prepareCreate() {
        selected = new ModuleClass();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleModule").getString("ModuleClassCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleModule").getString("ModuleClassUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleModule").getString("ModuleClassDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ModuleClass> getItems() {
        if (items == null || items.isEmpty()) {
            if (signInBean.getFarm() != null) {
                items = getJpaController().findModuleEntitiesForSelectedFarm(signInBean.getFarm());
            } else {
                JsfUtil.addErrorMessage("Seleccione una Finca");
            }
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

    public List<ModuleClass> getItemsAvailableSelectMany() {
        return getJpaController().findModuleEntities();
    }

    public List<ModuleClass> getItemsAvailableSelectOne() {
        return getJpaController().findModuleEntities();
    }

    @FacesConverter(forClass = ModuleClass.class)
    public static class ModuleClassControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ModuleClassController controller = (ModuleClassController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "moduleClassController");
            return controller.getJpaController().findModule(getKey(value));
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
            if (object instanceof ModuleClass) {
                ModuleClass o = (ModuleClass) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ModuleClass.class.getName()});
                return null;
            }
        }

    }

}
