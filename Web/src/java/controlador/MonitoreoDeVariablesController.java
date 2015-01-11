package controlador;

import modelo.produccion.monitoreo.MonitoreoDeVariables;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import dao.MonitoreoDeVariablesJpaController;

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

@ManagedBean(name = "monitoreoDeVariablesController")
@SessionScoped
public class MonitoreoDeVariablesController implements Serializable {

    private MonitoreoDeVariablesJpaController jpaController = null;
    private List<MonitoreoDeVariables> items = null;
    private MonitoreoDeVariables selected;

    public MonitoreoDeVariablesController() {
    }

    public MonitoreoDeVariables getSelected() {
        return selected;
    }

    public void setSelected(MonitoreoDeVariables selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MonitoreoDeVariablesJpaController getJpaController() {
        if (jpaController == null) {
            jpaController = new MonitoreoDeVariablesJpaController(Persistence.createEntityManagerFactory("WebPU"));
        }
        return jpaController;
    }

    public MonitoreoDeVariables prepareCreate() {
        selected = new MonitoreoDeVariables();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MonitoreoDeVariablesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MonitoreoDeVariablesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MonitoreoDeVariablesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MonitoreoDeVariables> getItems() {
        if (items == null) {
            items = getJpaController().findMonitoreoDeVariablesEntities();
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

    public List<MonitoreoDeVariables> getItemsAvailableSelectMany() {
        return getJpaController().findMonitoreoDeVariablesEntities();
    }

    public List<MonitoreoDeVariables> getItemsAvailableSelectOne() {
        return getJpaController().findMonitoreoDeVariablesEntities();
    }

    @FacesConverter(forClass = MonitoreoDeVariables.class)
    public static class MonitoreoDeVariablesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MonitoreoDeVariablesController controller = (MonitoreoDeVariablesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "monitoreoDeVariablesController");
            return controller.getJpaController().findMonitoreoDeVariables(getKey(value));
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
            if (object instanceof MonitoreoDeVariables) {
                MonitoreoDeVariables o = (MonitoreoDeVariables) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MonitoreoDeVariables.class.getName()});
                return null;
            }
        }

    }

}
