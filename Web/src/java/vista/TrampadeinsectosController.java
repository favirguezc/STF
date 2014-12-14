package vista;

import modelo.Trampadeinsectos;
import vista.util.JsfUtil;
import vista.util.JsfUtil.PersistAction;
import controlador.TrampadeinsectosJpaController;

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
import javax.persistence.Persistence;

@ManagedBean(name = "trampadeinsectosController")
@SessionScoped
public class TrampadeinsectosController implements Serializable {

    private TrampadeinsectosJpaController jpaController = null;
    private List<Trampadeinsectos> items = null;
    private Trampadeinsectos selected;

    public TrampadeinsectosController() {
    }

    public Trampadeinsectos getSelected() {
        return selected;
    }

    public void setSelected(Trampadeinsectos selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TrampadeinsectosJpaController getJpaController() {
        if (jpaController == null) {
            jpaController = new TrampadeinsectosJpaController(Persistence.createEntityManagerFactory("WebPU"));
        }
        return jpaController;
    }

    public Trampadeinsectos prepareCreate() {
        selected = new Trampadeinsectos();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TrampadeinsectosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TrampadeinsectosUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TrampadeinsectosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Trampadeinsectos> getItems() {
        if (items == null) {
            items = getJpaController().findTrampadeinsectosEntities();
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

    public List<Trampadeinsectos> getItemsAvailableSelectMany() {
        return getJpaController().findTrampadeinsectosEntities();
    }

    public List<Trampadeinsectos> getItemsAvailableSelectOne() {
        return getJpaController().findTrampadeinsectosEntities();
    }

    @FacesConverter(forClass = Trampadeinsectos.class)
    public static class TrampadeinsectosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TrampadeinsectosController controller = (TrampadeinsectosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "trampadeinsectosController");
            return controller.getJpaController().findTrampadeinsectos(getKey(value));
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
            if (object instanceof Trampadeinsectos) {
                Trampadeinsectos o = (Trampadeinsectos) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Trampadeinsectos.class.getName()});
                return null;
            }
        }

    }

}
