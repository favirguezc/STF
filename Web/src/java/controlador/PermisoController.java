package controlador;

import modelo.produccion.administracion.Permiso;
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
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.produccion.administracion.Pagina;
import modelo.produccion.administracion.Rol;
import modelo.util.Accion;

@ManagedBean(name = "permisoController")
@SessionScoped
public class PermisoController implements Serializable {

    private PermisoDAO jpaController = null;
    private List<Permiso> items = null;
    private Permiso selected;

    public PermisoController() {
    }

    public Permiso getSelected() {
        return selected;
    }

    public void setSelected(Permiso selected) {
        this.selected = selected;
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

    public Permiso prepareCreate() {
        selected = new Permiso();
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

    public List<Permiso> getItems() {
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

    public boolean tienePermiso(Rol rol, Accion accion, String requestPath) {
        requestPath = requestPath.toLowerCase();
        if (rol == Rol.ASISTENTE_ADMINISTRATIVO && requestPath.contains(Pagina.Permiso.toString().toLowerCase())) {
            return true;
        }
        for (Pagina pagina : Pagina.values()) {
            if (requestPath.contains(pagina.toString().toLowerCase())) {
                if (getJpaController().findPermiso(rol, pagina, accion)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean tienePermiso(PersistAction persistAction, Class c) {
        Accion accion = Accion.Leer;
        if (persistAction == PersistAction.CREATE || persistAction == PersistAction.UPDATE) {
            accion = Accion.Escribir;
        }
        if (persistAction == PersistAction.DELETE) {
            accion = Accion.Eliminar;
        }
        if (tienePermiso(((LoginController) JsfUtil.getSession().getAttribute("loginController")).getRol(), accion, c.getSimpleName())) {
            return true;
        } else {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("PermissionErrorOcurred"));
            return false;
        }

    }

    public List<Permiso> getItemsAvailableSelectMany() {
        return getJpaController().findPermisoEntities();
    }

    public List<Permiso> getItemsAvailableSelectOne() {
        return getJpaController().findPermisoEntities();
    }

    @FacesConverter(forClass = Permiso.class)
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
            if (object instanceof Permiso) {
                Permiso o = (Permiso) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Permiso.class.getName()});
                return null;
            }
        }

    }

}
