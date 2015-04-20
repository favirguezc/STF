package controller.controllers;

import model.weather.SoilMoisture;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.weather.SoilMoistureDAO;
import data.util.EntityManagerFactorySingleton;
import java.io.Serializable;
import java.util.Date;
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

@ManagedBean(name = "soilMoistureController")
@SessionScoped
public class SoilMoistureController implements Serializable {

    private SoilMoistureDAO jpaController = null;
    private List<SoilMoisture> items = null;
    private SoilMoisture selected;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;

    public SoilMoistureController() {
    }

    public SoilMoisture getSelected() {
        return selected;
    }

    public void setSelected(SoilMoisture selected) {
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

    private SoilMoistureDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new SoilMoistureDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public SoilMoisture prepareCreate() {
        selected = new SoilMoisture();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SoilMoistureCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SoilMoistureUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SoilMoistureDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SoilMoisture> getItems() {
        if (items == null) {
            items = getJpaController().findSoilMoistureEntities();
        }
        return items;
    }

    public List<SoilMoisture> getItems(Date fecha) {
        return getJpaController().findSoilMoistureEntities(fecha);
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

    public List<SoilMoisture> getItemsAvailableSelectMany() {
        return getJpaController().findSoilMoistureEntities();
    }

    public List<SoilMoisture> getItemsAvailableSelectOne() {
        return getJpaController().findSoilMoistureEntities();
    }

    public SoilMoisture getMean(Date time) {
        SoilMoisture mean = new SoilMoisture();
        mean.setMeasurementDate(time);
        List<SoilMoisture> lista = getItems(time);
        for (SoilMoisture h : lista) {
            mean.sumSoilMoisture(h);
        }
        if(lista.size()>1){
            mean.divideSoilMoistureBy(lista.size());
        }
        return mean;
    }

    @FacesConverter(forClass = SoilMoisture.class)
    public static class SoilMoistureControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SoilMoistureController controller = (SoilMoistureController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "soilMoistureController");
            return controller.getJpaController().findSoilMoisture(getKey(value));
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
            if (object instanceof SoilMoisture) {
                SoilMoisture o = (SoilMoisture) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SoilMoisture.class.getName()});
                return null;
            }
        }

    }

}
