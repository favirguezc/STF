package controlador.controllers;

import model.weather.SoilMoisture;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import data.weather.HumedadDelSueloDAO;
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

@ManagedBean(name = "humedadDelSueloController")
@SessionScoped
public class HumedadDelSueloController implements Serializable {

    private HumedadDelSueloDAO jpaController = null;
    private List<SoilMoisture> items = null;
    private SoilMoisture selected;
    @ManagedProperty(value = "#{permisoController}")
    private PermisoController permisoBean;

    public HumedadDelSueloController() {
    }

    public SoilMoisture getSelected() {
        return selected;
    }

    public void setSelected(SoilMoisture selected) {
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

    private HumedadDelSueloDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new HumedadDelSueloDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public SoilMoisture prepareCreate() {
        selected = new SoilMoisture();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("HumedadDelSueloCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("HumedadDelSueloUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("HumedadDelSueloDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SoilMoisture> getItems() {
        if (items == null) {
            items = getJpaController().findHumedadDelSueloEntities();
        }
        return items;
    }

    public List<SoilMoisture> getItems(Date fecha) {
        return getJpaController().findHumedadDelSueloEntities(fecha);
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

    public List<SoilMoisture> getItemsAvailableSelectMany() {
        return getJpaController().findHumedadDelSueloEntities();
    }

    public List<SoilMoisture> getItemsAvailableSelectOne() {
        return getJpaController().findHumedadDelSueloEntities();
    }

    public SoilMoisture calcularPromedio(Date time) {
        SoilMoisture promedio = new SoilMoisture();
        promedio.setMeasurementDate(time);
        List<SoilMoisture> lista = getItems(time);
        for (SoilMoisture h : lista) {
            promedio.sumSoilMoisture(h);
        }
        if(lista.size()>1){
            promedio.divideSoilMoistureBy(lista.size());
        }
        return promedio;
    }

    @FacesConverter(forClass = SoilMoisture.class)
    public static class HumedadDelSueloControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            HumedadDelSueloController controller = (HumedadDelSueloController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "humedadDelSueloController");
            return controller.getJpaController().findHumedadDelSuelo(getKey(value));
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
