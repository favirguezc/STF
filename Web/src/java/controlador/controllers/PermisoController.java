package controlador.controllers;

import model.administration.Permission;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import datos.produccion.administracion.PermisoDAO;
import datos.util.EntityManagerFactorySingleton;
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

@ManagedBean(name = "permisoController")
@SessionScoped
public class PermisoController implements Serializable {

    private PermisoDAO jpaController = null;
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

    private PermisoDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new PermisoDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Permission prepareCreate() {
        selected = new Permission();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PermisoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PermisoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PermisoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Permission> getItems() {
        if (items == null) {
            items = getJpaController().findPermisoEntities();
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

    public boolean currentUserHasPermission(Action accion, String requestPath) {
        if (signInBean.getUser().isSystemAdmin()) {
            return true;
        }
        requestPath = requestPath.toLowerCase();
        if (signInBean.getRol() == null) {
            if (requestPath.contains(PageEnum.Index.toString().toLowerCase())) {
                return true;
            } else {
                return false;
            }
        }
        if (signInBean.getRol() == RoleEnum.ADMINISTRATIVE_ASSISTANT && requestPath.contains(PageEnum.Permiso.toString().toLowerCase())) {
            return true;
        }
        for (PageEnum pagina : PageEnum.values()) {
            if (requestPath.contains(pagina.toString().toLowerCase())) {
                if (getJpaController().findPermiso(signInBean.getRol(), pagina, accion)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean currentUserHasPermission(PersistAction persistAction, Class c) {
        Action accion = Action.READ;
        if (persistAction == PersistAction.CREATE || persistAction == PersistAction.UPDATE) {
            accion = Action.WRITE;
        }
        if (persistAction == PersistAction.DELETE) {
            accion = Action.DELETE;
        }
        if (currentUserHasPermission(accion, c.getSimpleName())) {
            return true;
        } else {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PermissionErrorOcurred"));
            return false;
        }

    }

    public List<Permission> getItemsAvailableSelectMany() {
        return getJpaController().findPermisoEntities();
    }

    public List<Permission> getItemsAvailableSelectOne() {
        return getJpaController().findPermisoEntities();
    }

    @FacesConverter(forClass = Permission.class)
    public static class PermisoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PermisoController controller = (PermisoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "permisoController");
            return controller.getJpaController().findPermiso(getKey(value));
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
