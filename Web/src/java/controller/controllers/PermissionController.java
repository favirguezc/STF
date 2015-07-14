package controller.controllers;

import model.administration.Permission;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.administration.PermissionDAO;
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
import model.administration.PageEnum;
import model.administration.RoleEnum;
import model.util.Action;

@ManagedBean(name = "permissionController")
@SessionScoped
public class PermissionController implements Serializable {

    private PermissionDAO jpaController = null;
    private List<Permission> items = null;
    private Permission selected;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;

    public Permission getSelected() {
        return selected;
    }

    public void setSelected(Permission selected) {
        this.selected = selected;
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

    private PermissionDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new PermissionDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Permission prepareCreate() {
        selected = new Permission();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundlePermission").getString("PermissionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundlePermission").getString("PermissionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundlePermission").getString("PermissionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Permission> getItems() {
        if (items == null || items.isEmpty()) {
            items = getJpaController().findPermissionEntities();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
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

    public boolean currentUserHasPermission(Action action, String requestPath) {
        requestPath = requestPath.toLowerCase();
        if (signInBean.getUser() == null && requestPath.contains("person")) {
            return true;
        }
        if (signInBean.getUser().isSystemAdmin()) {
            return true;
        }
        if (signInBean.getRole() == null) {
            if (requestPath.contains(PageEnum.NOTE.toString().toLowerCase())) {
                return true;
            } else {
                return false;
            }
        }
        if (signInBean.getRole() == RoleEnum.ADMINISTRATIVE_ASSISTANT && requestPath.contains(PageEnum.PERMISSION.name().toLowerCase())) {
            return true;
        }
        for (PageEnum page : PageEnum.values()) {
            if (requestPath.contains(page.name().toLowerCase())) {
                if (getJpaController().findPermission(signInBean.getRole(), page, action)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean currentUserHasPermission(PersistAction persistAction, Class c) {
        Action action = Action.READ;
        if (persistAction == PersistAction.CREATE || persistAction == PersistAction.UPDATE) {
            action = Action.WRITE;
        }
        if (persistAction == PersistAction.DELETE) {
            action = Action.DELETE;
        }
        if (currentUserHasPermission(action, c.getSimpleName())) {
            System.out.println("Si tiene permiso");
            return true;
        } else {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/BundlePermission").getString("PermissionErrorOcurred"));
            System.out.println("No tiene permiso");
            return false;
        }

    }

    public List<Permission> getItemsAvailableSelectMany() {
        return getJpaController().findPermissionEntities();
    }

    public List<Permission> getItemsAvailableSelectOne() {
        return getJpaController().findPermissionEntities();
    }

    @FacesConverter(forClass = Permission.class)
    public static class PermissionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PermissionController controller = (PermissionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "permissionController");
            return controller.getJpaController().findPermission(getKey(value));
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
            if (object instanceof Permission) {
                Permission o = (Permission) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Permission.class.getName()});
                return null;
            }
        }

    }

}
