package controller.controllers;

import model.monitoring.Monitoring;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.monitoring.MonitoringDAO;
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

@ManagedBean(name = "monitoringController")
@SessionScoped
public class MonitoringController implements Serializable {

    private MonitoringDAO jpaController = null;
    private List<Monitoring> items = null;
    private Monitoring selected;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;

    public MonitoringController() {
    }

    public Monitoring getSelected() {
        return selected;
    }

    public void setSelected(Monitoring selected) {
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

    private MonitoringDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new MonitoringDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Monitoring prepareCreate() {
        selected = new Monitoring();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleMonitoring").getString("MonitoringCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleMonitoring").getString("MonitoringUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleMonitoring").getString("MonitoringDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Monitoring> getItems() {
        if (items == null) {
            items = getJpaController().findMonitoringEntities();
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

    public List<Monitoring> getItemsAvailableSelectMany() {
        return getJpaController().findMonitoringEntities();
    }

    public List<Monitoring> getItemsAvailableSelectOne() {
        return getJpaController().findMonitoringEntities();
    }

    @FacesConverter(forClass = Monitoring.class)
    public static class MonitoringControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MonitoringController controller = (MonitoringController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "monitoringController");
            return controller.getJpaController().findMonitoring(getKey(value));
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
            if (object instanceof Monitoring) {
                Monitoring o = (Monitoring) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Monitoring.class.getName()});
                return null;
            }
        }

    }

}
