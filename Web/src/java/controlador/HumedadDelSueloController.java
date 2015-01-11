package controlador;

import modelo.produccion.variablesClimaticas.HumedadDelSuelo;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import dao.HumedadDelSueloJpaController;

import java.io.Serializable;
import java.util.Date;
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

@ManagedBean(name = "humedadDelSueloController")
@SessionScoped
public class HumedadDelSueloController implements Serializable {

    private HumedadDelSueloJpaController jpaController = null;
    private List<HumedadDelSuelo> items = null;
    private HumedadDelSuelo selected;

    public HumedadDelSueloController() {
    }

    public HumedadDelSuelo getSelected() {
        return selected;
    }

    public void setSelected(HumedadDelSuelo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private HumedadDelSueloJpaController getJpaController() {
        if (jpaController == null) {
            jpaController = new HumedadDelSueloJpaController(Persistence.createEntityManagerFactory("WebPU"));
        }
        return jpaController;
    }

    public HumedadDelSuelo prepareCreate() {
        selected = new HumedadDelSuelo();
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

    public List<HumedadDelSuelo> getItems() {
        if (items == null) {
            items = getJpaController().findHumedadDelSueloEntities();
        }
        return items;
    }

    public List<HumedadDelSuelo> getItems(Date fecha) {
        return getJpaController().findHumedadDelSueloEntities(fecha);
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

    public List<HumedadDelSuelo> getItemsAvailableSelectMany() {
        return getJpaController().findHumedadDelSueloEntities();
    }

    public List<HumedadDelSuelo> getItemsAvailableSelectOne() {
        return getJpaController().findHumedadDelSueloEntities();
    }

    public HumedadDelSuelo calcularPromedio(Date time) {
        HumedadDelSuelo promedio = new HumedadDelSuelo(time, 0, 0, null);
        List<HumedadDelSuelo> lista = getItems(time);
        for (HumedadDelSuelo h : lista) {
            promedio.sumar(h);
        }
        if(lista.size()>1){
            promedio.dividir(lista.size());
        }
        return promedio;
    }

    @FacesConverter(forClass = HumedadDelSuelo.class)
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
            if (object instanceof HumedadDelSuelo) {
                HumedadDelSuelo o = (HumedadDelSuelo) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), HumedadDelSuelo.class.getName()});
                return null;
            }
        }

    }

}
