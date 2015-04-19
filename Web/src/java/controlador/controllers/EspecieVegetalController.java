package controlador.controllers;

import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import datos.produccion.cosecha.EspecieVegetalDAO;
import java.io.Serializable;
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
import modelo.produccion.cosecha.EspecieVegetal;

@ManagedBean(name = "especieVegetalController")
@SessionScoped
public class EspecieVegetalController implements Serializable {

    private EspecieVegetalDAO jpaController = null;
    private List<EspecieVegetal> items = null;
    private EspecieVegetal selected;

    public EspecieVegetalController() {
    }

    public EspecieVegetal getSelected() {
        return selected;
    }

    public void setSelected(EspecieVegetal selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EspecieVegetalDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new EspecieVegetalDAO(Persistence.createEntityManagerFactory("WebPU"));
        }
        return jpaController;
    }

    public EspecieVegetal prepareCreate() {
        selected = new EspecieVegetal();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle2").getString("EspecieVegetalCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle2").getString("EspecieVegetalUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle2").getString("EspecieVegetalDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<EspecieVegetal> getItems() {
        if (items == null) {
            items = getJpaController().findEspecieVegetalEntities();
        }
        return items;
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
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle2").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<EspecieVegetal> getItemsAvailableSelectMany() {
        return getJpaController().findEspecieVegetalEntities();
    }

    public List<EspecieVegetal> getItemsAvailableSelectOne() {
        return getJpaController().findEspecieVegetalEntities();
    }

    @FacesConverter(forClass = EspecieVegetal.class)
    public static class EspecieVegetalControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EspecieVegetalController controller = (EspecieVegetalController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "especieVegetalController");
            return controller.getJpaController().findEspecieVegetal(getKey(value));
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
            if (object instanceof EspecieVegetal) {
                EspecieVegetal o = (EspecieVegetal) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), EspecieVegetal.class.getName()});
                return null;
            }
        }

    }

}
