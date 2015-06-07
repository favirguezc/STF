package controller.controllers;

import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import model.crop.Variety;
import data.crop.VarietyDAO;
import data.util.EntityManagerFactorySingleton;
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

@ManagedBean(name = "varietyController")
@SessionScoped
public class VarietyController implements Serializable {

    private VarietyDAO jpaController = null;
    private List<Variety> items = null;
    private Variety selected;

    public VarietyController() {
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

    private VarietyDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new VarietyDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Variety prepareCreate() {
        selected = new Variety();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleVariety").getString("VarietyCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleVariety").getString("VarietyUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleVariety").getString("VarietyDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Variety> getItems() {
        if (items == null || items.isEmpty()) {
            items = getJpaController().findVarietyEntities();
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
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Variety> getItemsAvailableSelectMany() {
        return getJpaController().findVarietyEntities();
    }

    public List<Variety> getItemsAvailableSelectOne() {
        return getJpaController().findVarietyEntities();
    }

    @FacesConverter(forClass = Variety.class)
    public static class VarietyControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VarietyController controller = (VarietyController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "varietyController");
            return controller.getJpaController().findVariety(getKey(value));
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
