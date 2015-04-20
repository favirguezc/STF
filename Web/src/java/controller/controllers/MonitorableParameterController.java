package controller.controllers;

import model.monitoring.MonitorableParameter;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.monitoring.MonitorableParameterDAO;
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

@ManagedBean(name = "monitorableParameterController")
@SessionScoped
public class MonitorableParameterController implements Serializable {

    private MonitorableParameterDAO jpaController = null;
    private List<MonitorableParameter> items = null;
    private MonitorableParameter selected;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;

    public MonitorableParameterController() {
    }

    public MonitorableParameter getSelected() {
        return selected;
    }

    public void setSelected(MonitorableParameter selected) {
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

    private MonitorableParameterDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new MonitorableParameterDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public MonitorableParameter prepareCreate() {
        selected = new MonitorableParameter();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MonitorableParameterCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MonitorableParameterUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MonitorableParameterDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MonitorableParameter> getItems() {
        if (items == null) {
            items = getJpaController().findMonitorableParameterEntities();
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

    public List<MonitorableParameter> getItemsAvailableSelectMany() {
        return getJpaController().findMonitorableParameterEntities();
    }

    public List<MonitorableParameter> getItemsAvailableSelectOne() {
        return getJpaController().findMonitorableParameterEntities();
    }

    @FacesConverter(forClass = MonitorableParameter.class)
    public static class MonitorableParameterControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MonitorableParameterController controller = (MonitorableParameterController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "monitorableparameterController");
            return controller.getJpaController().findMonitorableParameter(getKey(value));
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
            if (object instanceof MonitorableParameter) {
                MonitorableParameter o = (MonitorableParameter) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MonitorableParameter.class.getName()});
                return null;
            }
        }

    }

}
