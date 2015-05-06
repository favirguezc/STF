package controller.controllers;

import model.weather.Temperature;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.weather.TemperatureDAO;
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
import model.administration.ModuleClass;

@ManagedBean(name = "temperatureController")
@SessionScoped
public class TemperatureController implements Serializable {

    private TemperatureDAO jpaController = null;
    private List<Temperature> items = null;
    private Temperature selected;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;

    public TemperatureController() {
    }

    public Temperature getSelected() {
        return selected;
    }

    public void setSelected(Temperature selected) {
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

    private TemperatureDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new TemperatureDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Temperature prepareCreate() {
        selected = new Temperature();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleTemperature").getString("TemperatureCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleTemperature").getString("TemperatureUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleTemperature").getString("TemperatureDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Temperature> getItems() {
        if (items == null) {
            items = getJpaController().findTemperatureEntities();
        }
        return items;
    }

    public List<Temperature> getItems(Date fecha1, Date fecha2) {
        return getJpaController().findTemperatureEntities(fecha1, fecha2, 0);
    }

    public List<Temperature> getItems(Date fecha1, Date fecha2, int tipo) {
        return getJpaController().findTemperatureEntities(fecha1, fecha2, tipo);
    }

    public List<Temperature> getItems(Date fecha1, int hora) {
        return getJpaController().findTemperatureEntities(fecha1, hora);
    }

    public Temperature calcularMean(Date fecha1, Date fecha2) {
        return calcularMean(fecha1, fecha2, 0);
    }

    public Temperature calcularMean(Date fecha1, Date fecha2, int tipo) {
        Temperature mean = new Temperature(null, null, 0, 0, 0, null);
        List<Temperature> buscarLista = getItems(fecha1, fecha2, tipo);
        for (Temperature t : buscarLista) {
            mean.sumar(t);
        }
        if (buscarLista.size() > 1) {
            mean.dividir(buscarLista.size());
        }
        return mean;
    }

    public Temperature calcularMean(Date fechaDia, int hora) {
        Temperature mean = new Temperature(null, null, 0, 0, 0, null);
        List<Temperature> buscarLista = getItems(fechaDia, hora);
        for (Temperature t : buscarLista) {
            mean.sumar(t);
        }
        if (buscarLista.size() > 1) {
            mean.dividir(buscarLista.size());
        }
        return mean;
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

    public List<Temperature> getItemsAvailableSelectMany() {
        return getJpaController().findTemperatureEntities();
    }

    public List<Temperature> getItemsAvailableSelectOne() {
        return getJpaController().findTemperatureEntities();
    }

    public void save(Temperature temperature) {
        selected = temperature;
        persist(PersistAction.CREATE, null);
    }

    public Temperature nuevo(Date fecha, Date hora, float temperature, float soilmoisture, float puntoDeRocio, ModuleClass moduleclass) {
        return new Temperature(fecha, hora, temperature, soilmoisture, puntoDeRocio, moduleclass);
    }

    @FacesConverter(forClass = Temperature.class)
    public static class TemperatureControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TemperatureController controller = (TemperatureController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "temperatureController");
            return controller.getJpaController().findTemperature(getKey(value));
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
            if (object instanceof Temperature) {
                Temperature o = (Temperature) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Temperature.class.getName()});
                return null;
            }
        }

    }

}
