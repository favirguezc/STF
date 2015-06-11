package controller.controllers;

import model.weather.Thermometer;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.weather.ThermometerDAO;
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

@ManagedBean(name = "thermometerController")
@SessionScoped
public class ThermometerController implements Serializable {

    private ThermometerDAO jpaController = null;
    private List<Thermometer> items = null;
    private Thermometer selected;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;

    public ThermometerController() {
    }

    public Thermometer getSelected() {
        return selected;
    }

    public void setSelected(Thermometer selected) {
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

    private ThermometerDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new ThermometerDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Thermometer prepareCreate() {
        selected = new Thermometer();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleThermometer").getString("ThermometerCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleThermometer").getString("ThermometerUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleThermometer").getString("ThermometerDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public Thermometer find(long nds) throws Exception {
        return getJpaController().findThermometer(nds);
    }

    public List<Thermometer> getItems() {
        if (items == null || items.isEmpty()) {
            items = getJpaController().findThermometerEntities();
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

    public List<Thermometer> getItemsAvailableSelectMany() {
        return getJpaController().findThermometerEntities();
    }

    public List<Thermometer> getItemsAvailableSelectOne() {
        return getJpaController().findThermometerEntities();
    }

    @FacesConverter(forClass = Thermometer.class)
    public static class ThermometerControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ThermometerController controller = (ThermometerController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "thermometerController");
            return controller.getJpaController().findThermometer(getKey(value));
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
            if (object instanceof Thermometer) {
                Thermometer o = (Thermometer) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Thermometer.class.getName()});
                return null;
            }
        }

    }

}
