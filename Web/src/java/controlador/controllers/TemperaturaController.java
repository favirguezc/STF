package controlador.controllers;

import model.weather.Temperature;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import datos.produccion.variablesClimaticas.TemperaturaDAO;
import datos.util.EntityManagerFactorySingleton;
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

@ManagedBean(name = "temperaturaController")
@SessionScoped
public class TemperaturaController implements Serializable {

    private TemperaturaDAO jpaController = null;
    private List<Temperature> items = null;
    private Temperature selected;
    @ManagedProperty(value = "#{permisoController}")
    private PermisoController permisoBean;

    public TemperaturaController() {
    }

    public Temperature getSelected() {
        return selected;
    }

    public void setSelected(Temperature selected) {
        this.selected = selected;
    }

    public PermisoController getPermisoBean() {
        return permisoBean;
    }

    public void setPermisoBean(PermisoController permisoBean) {
        this.permisoBean = permisoBean;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TemperaturaDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new TemperaturaDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Temperature prepareCreate() {
        selected = new Temperature();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TemperaturaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TemperaturaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TemperaturaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Temperature> getItems() {
        if (items == null) {
            items = getJpaController().findTemperaturaEntities();
        }
        return items;
    }

    public List<Temperature> getItems(Date fecha1, Date fecha2) {
        return getJpaController().findTemperaturaEntities(fecha1, fecha2, 0);
    }

    public List<Temperature> getItems(Date fecha1, Date fecha2, int tipo) {
        return getJpaController().findTemperaturaEntities(fecha1, fecha2, tipo);
    }

    public List<Temperature> getItems(Date fecha1, int hora) {
        return getJpaController().findTemperaturaEntities(fecha1, hora);
    }

    public Temperature calcularPromedio(Date fecha1, Date fecha2) {
        return calcularPromedio(fecha1, fecha2, 0);
    }

    public Temperature calcularPromedio(Date fecha1, Date fecha2, int tipo) {
        Temperature promedio = new Temperature(null, null, 0, 0, 0, null);
        List<Temperature> buscarLista = getItems(fecha1, fecha2, tipo);
        for (Temperature t : buscarLista) {
            promedio.sumar(t);
        }
        if (buscarLista.size() > 1) {
            promedio.dividir(buscarLista.size());
        }
        return promedio;
    }

    public Temperature calcularPromedio(Date fechaDia, int hora) {
        Temperature promedio = new Temperature(null, null, 0, 0, 0, null);
        List<Temperature> buscarLista = getItems(fechaDia, hora);
        for (Temperature t : buscarLista) {
            promedio.sumar(t);
        }
        if (buscarLista.size() > 1) {
            promedio.dividir(buscarLista.size());
        }
        return promedio;
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

    public List<Temperature> getItemsAvailableSelectMany() {
        return getJpaController().findTemperaturaEntities();
    }

    public List<Temperature> getItemsAvailableSelectOne() {
        return getJpaController().findTemperaturaEntities();
    }

    public void guardar(Temperature temperatura) {
        selected = temperatura;
        persist(PersistAction.CREATE, null);
    }

    public Temperature nuevo(Date fecha, Date hora, float temperatura, float humedad, float puntoDeRocio, ModuleClass modulo) {
        return new Temperature(fecha, hora, temperatura, humedad, puntoDeRocio, modulo);
    }

    @FacesConverter(forClass = Temperature.class)
    public static class TemperaturaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TemperaturaController controller = (TemperaturaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "temperaturaController");
            return controller.getJpaController().findTemperatura(getKey(value));
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
