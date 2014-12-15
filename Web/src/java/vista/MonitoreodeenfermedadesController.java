package vista;

import modelo.produccion.MonitoreoDeEnfermedades;
import vista.util.JsfUtil;
import vista.util.JsfUtil.PersistAction;
import controlador.MonitoreoDeEnfermedadesJpaController;

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

@ManagedBean(name = "monitoreoDeEnfermedadesController")
@SessionScoped
public class MonitoreoDeEnfermedadesController implements Serializable {

    private MonitoreoDeEnfermedadesJpaController jpaController = null;
    private List<MonitoreoDeEnfermedades> items = null;
    private MonitoreoDeEnfermedades selected;

    public MonitoreoDeEnfermedadesController() {
    }

    public MonitoreoDeEnfermedades getSelected() {
        return selected;
    }

    public void setSelected(MonitoreoDeEnfermedades selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MonitoreoDeEnfermedadesJpaController getJpaController() {
        if (jpaController == null) {
            jpaController = new MonitoreoDeEnfermedadesJpaController(Persistence.createEntityManagerFactory("WebPU"));
        }
        return jpaController;
    }

    public MonitoreoDeEnfermedades prepareCreate() {
        selected = new MonitoreoDeEnfermedades();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MonitoreoDeEnfermedadesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MonitoreoDeEnfermedadesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MonitoreoDeEnfermedadesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MonitoreoDeEnfermedades> getItems() {
        if (items == null) {
            items = getJpaController().findMonitoreoDeEnfermedadesEntities();
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

    public List<MonitoreoDeEnfermedades> getItemsAvailableSelectMany() {
        return getJpaController().findMonitoreoDeEnfermedadesEntities();
    }

    public List<MonitoreoDeEnfermedades> getItemsAvailableSelectOne() {
        return getJpaController().findMonitoreoDeEnfermedadesEntities();
    }

    @FacesConverter(forClass = MonitoreoDeEnfermedades.class)
    public static class MonitoreoDeEnfermedadesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MonitoreoDeEnfermedadesController controller = (MonitoreoDeEnfermedadesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "monitoreoDeEnfermedadesController");
            return controller.getJpaController().findMonitoreoDeEnfermedades(getKey(value));
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
            if (object instanceof MonitoreoDeEnfermedades) {
                MonitoreoDeEnfermedades o = (MonitoreoDeEnfermedades) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MonitoreoDeEnfermedades.class.getName()});
                return null;
            }
        }

    }

}
