package vista;

import modelo.Monitoreodeenfermedades;
import vista.util.JsfUtil;
import vista.util.JsfUtil.PersistAction;
import controlador.MonitoreodeenfermedadesJpaController;

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

@ManagedBean(name = "monitoreodeenfermedadesController")
@SessionScoped
public class MonitoreodeenfermedadesController implements Serializable {

    private MonitoreodeenfermedadesJpaController jpaController = null;
    private List<Monitoreodeenfermedades> items = null;
    private Monitoreodeenfermedades selected;

    public MonitoreodeenfermedadesController() {
    }

    public Monitoreodeenfermedades getSelected() {
        return selected;
    }

    public void setSelected(Monitoreodeenfermedades selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MonitoreodeenfermedadesJpaController getJpaController() {
        if (jpaController == null) {
            jpaController = new MonitoreodeenfermedadesJpaController(Persistence.createEntityManagerFactory("WebPU"));
        }
        return jpaController;
    }

    public Monitoreodeenfermedades prepareCreate() {
        selected = new Monitoreodeenfermedades();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MonitoreodeenfermedadesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MonitoreodeenfermedadesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MonitoreodeenfermedadesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Monitoreodeenfermedades> getItems() {
        if (items == null) {
            items = getJpaController().findMonitoreodeenfermedadesEntities();
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

    public List<Monitoreodeenfermedades> getItemsAvailableSelectMany() {
        return getJpaController().findMonitoreodeenfermedadesEntities();
    }

    public List<Monitoreodeenfermedades> getItemsAvailableSelectOne() {
        return getJpaController().findMonitoreodeenfermedadesEntities();
    }

    @FacesConverter(forClass = Monitoreodeenfermedades.class)
    public static class MonitoreodeenfermedadesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MonitoreodeenfermedadesController controller = (MonitoreodeenfermedadesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "monitoreodeenfermedadesController");
            return controller.getJpaController().findMonitoreodeenfermedades(getKey(value));
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
            if (object instanceof Monitoreodeenfermedades) {
                Monitoreodeenfermedades o = (Monitoreodeenfermedades) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Monitoreodeenfermedades.class.getName()});
                return null;
            }
        }

    }

}
