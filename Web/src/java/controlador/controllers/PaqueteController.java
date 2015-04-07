package controlador.controllers;

import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import modelo.contabilidad.Paquete;
import datos.contabilidad.PaqueteDAO;
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

@ManagedBean(name = "paqueteController")
@SessionScoped
public class PaqueteController implements Serializable {

    private PaqueteDAO jpaController = null;
    private List<Paquete> items = null;
    private Paquete selected;
    @ManagedProperty(value = "#{permisoController}")
    private PermisoController permisoBean;

    public PaqueteController() {
    }

    public Paquete getSelected() {
        return selected;
    }

    public void setSelected(Paquete selected) {
        this.selected = selected;
    }

    public PermisoController getPermisoBean() {
        return permisoBean;
    }

    public void setPermisoBean(PermisoController permisoBean) {
        this.permisoBean = permisoBean;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PaqueteDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new PaqueteDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Paquete prepareCreate() {
        selected = new Paquete();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle2").getString("PaqueteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle2").getString("PaqueteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle2").getString("PaqueteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Paquete> getItems() {
        if (items == null) {
            items = getJpaController().findPaqueteEntities();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            if (!permisoBean.currentUserHasPermission(persistAction, selected.getClass())) {
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
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle2").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Paquete> getItemsAvailableSelectMany() {
        return getJpaController().findPaqueteEntities();
    }

    public List<Paquete> getItemsAvailableSelectOne() {
        return getJpaController().findPaqueteEntities();
    }

    @FacesConverter(forClass = Paquete.class)
    public static class PaqueteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PaqueteController controller = (PaqueteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "paqueteController");
            return controller.getJpaController().findPaquete(getKey(value));
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
            if (object instanceof Paquete) {
                Paquete o = (Paquete) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Paquete.class.getName()});
                return null;
            }
        }

    }

}
