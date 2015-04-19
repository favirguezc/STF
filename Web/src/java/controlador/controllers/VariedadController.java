package controlador.controllers;

import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import data.crop.VariedadDAO;
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
import model.crop.Variety;

@ManagedBean(name = "variedadController")
@SessionScoped
public class VariedadController implements Serializable {

    private VariedadDAO jpaController = null;
    private List<Variety> items = null;
    private Variety selected;

    public VariedadController() {
    }

    public Variety getSelected() {
        return selected;
    }

    public void setSelected(Variety selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private VariedadDAO getDAO() {
        if (jpaController == null) {
            jpaController = new VariedadDAO(Persistence.createEntityManagerFactory("WebPU"));
        }
        return jpaController;
    }

    public Variety prepareCreate() {
        selected = new Variety();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle2").getString("VariedadCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle2").getString("VariedadUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle2").getString("VariedadDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Variety> getItems() {
        if (items == null) {
            items = getDAO().findVariedadEntities();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction == PersistAction.UPDATE) {
                    getDAO().edit(selected);
                } else if (persistAction == PersistAction.CREATE) {
                    getDAO().create(selected);
                } else {
                    getDAO().destroy(selected.getId());
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle2").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Variety> getItemsAvailableSelectMany() {
        return getDAO().findVariedadEntities();
    }

    public List<Variety> getItemsAvailableSelectOne() {
        return getDAO().findVariedadEntities();
    }

    @FacesConverter(forClass = Variety.class)
    public static class VariedadControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VariedadController controller = (VariedadController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "variedadController");
            return controller.getDAO().findVariedad(getKey(value));
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
            if (object instanceof Variety) {
                Variety o = (Variety) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Variety.class.getName()});
                return null;
            }
        }

    }

}
