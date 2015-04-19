package controlador.controllers;

import model.monitoring.ParameterMonitoring;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import datos.produccion.monitoreo.MonitoreoDeVariablesDAO;
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
import model.monitoring.ValuationTypeEnum;

@ManagedBean(name = "monitoreoDeVariablesController")
@SessionScoped
public class MonitoreoDeVariablesController implements Serializable {

    private MonitoreoDeVariablesDAO jpaController = null;
    private List<ParameterMonitoring> items = null;
    private ParameterMonitoring selected;
    private int habilitado;
    @ManagedProperty(value = "#{permisoController}")
    private PermisoController permisoBean;

    public MonitoreoDeVariablesController() {
    }

    public ParameterMonitoring getSelected() {
        return selected;
    }

    public void setSelected(ParameterMonitoring selected) {
        this.selected = selected;
    }

    public PermisoController getPermisoBean() {
        return permisoBean;
    }

    public void setPermisoBean(PermisoController permisoBean) {
        this.permisoBean = permisoBean;
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

    private MonitoreoDeVariablesDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new MonitoreoDeVariablesDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public ParameterMonitoring prepareCreate() {
        selected = new ParameterMonitoring();
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

    public List<ParameterMonitoring> getItems() {
        if (items == null) {
            items = getJpaController().findMonitoreoDeVariablesEntities();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
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
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<ParameterMonitoring> getItemsAvailableSelectMany() {
        return getJpaController().findMonitoreoDeVariablesEntities();
    }

    public List<ParameterMonitoring> getItemsAvailableSelectOne() {
        return getJpaController().findMonitoreoDeVariablesEntities();
    }

    public void valor() {
        if (selected.getParameter() != null) {
            if (selected.getParameter().getValuationType() == ValuationTypeEnum.COUNT) {
                habilitado = 2;
            } else if (selected.getParameter().getValuationType() == ValuationTypeEnum.RELATION) {
                habilitado = 1;
            } else if (selected.getParameter().getValuationType() == ValuationTypeEnum.RISK){
                habilitado = 3;
            }
        } else {
            habilitado = 0;
        }
    }

    @FacesConverter(forClass = ParameterMonitoring.class)
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
