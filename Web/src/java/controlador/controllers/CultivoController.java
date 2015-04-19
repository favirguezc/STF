package controlador.controllers;

import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import data.administration.CultivoDAO;
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
import model.administration.Cultivation;

@ManagedBean(name = "cultivoController")
@SessionScoped
public class CultivoController implements Serializable {

    private CultivoDAO jpaController = null;
    private List<Cultivation> items = null;
    private Cultivation selected;

    public CultivoController() {
    }

    public Cultivation getSelected() {
        return selected;
    }

    public void setSelected(Cultivation selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CultivoDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new CultivoDAO(Persistence.createEntityManagerFactory("WebPU"));
        }
        return jpaController;
    }

    public Cultivation prepareCreate() {
        selected = new Cultivation();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle2").getString("CultivoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle2").getString("CultivoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle2").getString("CultivoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Cultivation> getItems() {
        if (items == null) {
            items = getJpaController().findCultivoEntities();
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

    public List<Cultivation> getItemsAvailableSelectMany() {
        return getJpaController().findCultivoEntities();
    }

    public List<Cultivation> getItemsAvailableSelectOne() {
        return getJpaController().findCultivoEntities();
    }

    @FacesConverter(forClass = Cultivation.class)
    public static class CultivoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CultivoController controller = (CultivoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cultivoController");
            return controller.getJpaController().findCultivo(getKey(value));
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
            if (object instanceof Cultivation) {
                Cultivation o = (Cultivation) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Cultivation.class.getName()});
                return null;
            }
        }

    }

}
