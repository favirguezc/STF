package controller.controllers;

import model.monitoring.ParameterMonitoring;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.monitoring.ParameterMonitoringDAO;
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
import model.monitoring.ValuationTypeEnum;

@ManagedBean(name = "parameterMonitoringController")
@SessionScoped
public class ParameterMonitoringController implements Serializable {

    private ParameterMonitoringDAO jpaController = null;
    private List<ParameterMonitoring> items = null;
    private ParameterMonitoring selected;
    private int habilitado;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;

    public ParameterMonitoringController() {
    }

    public ParameterMonitoring getSelected() {
        return selected;
    }

    public void setSelected(ParameterMonitoring selected) {
        this.selected = selected;
    }

    public PermissionController getPermissionBean() {
        return permissionBean;
    }

    public void setPermissionBean(PermissionController permissionBean) {
        this.permissionBean = permissionBean;
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ParameterMonitoringDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new ParameterMonitoringDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public ParameterMonitoring prepareCreate() {
        selected = new ParameterMonitoring();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleParameterMonitoring").getString("ParameterMonitoringCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleParameterMonitoring").getString("ParameterMonitoringUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleParameterMonitoring").getString("ParameterMonitoringDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ParameterMonitoring> getItems() {
        if (items == null) {
            items = getJpaController().findParameterMonitoringEntities();
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

    public List<ParameterMonitoring> getItemsAvailableSelectMany() {
        return getJpaController().findParameterMonitoringEntities();
    }

    public List<ParameterMonitoring> getItemsAvailableSelectOne() {
        return getJpaController().findParameterMonitoringEntities();
    }

    public void value() {
        if (selected.getParameter() != null) {
            if (selected.getParameter().getValuationType() == ValuationTypeEnum.COUNT) {
                habilitado = 2;
            } else if (selected.getParameter().getValuationType() == ValuationTypeEnum.RELATION) {
                habilitado = 1;
            } else if (selected.getParameter().getValuationType() == ValuationTypeEnum.RISK) {
                habilitado = 3;
            } else if (selected.getParameter().getValuationType() == ValuationTypeEnum.PRESENCE) {
                habilitado = 4;
            }
        } else {
            habilitado = 0;
        }
    }

    @FacesConverter(forClass = ParameterMonitoring.class)
    public static class ParameterMonitoringControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ParameterMonitoringController controller = (ParameterMonitoringController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "parameterMonitoringController");
            return controller.getJpaController().findParameterMonitoring(getKey(value));
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
            if (object instanceof ParameterMonitoring) {
                ParameterMonitoring o = (ParameterMonitoring) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ParameterMonitoring.class.getName()});
                return null;
            }
        }

    }

}
