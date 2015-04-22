package controller.controllers;

import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.administration.CultivationDAO;
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
import model.administration.Cultivation;

@ManagedBean(name = "cultivationController")
@SessionScoped
public class CultivationController implements Serializable {

    private CultivationDAO jpaController = null;
    private List<Cultivation> items = null;
    private Cultivation selected;

    public CultivationController() {
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

    private CultivationDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new CultivationDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Cultivation prepareCreate() {
        selected = new Cultivation();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleCultivation").getString("CultivationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleCultivation").getString("CultivationUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleCultivation").getString("CultivationDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Cultivation> getItems() {
        if (items == null) {
            items = getJpaController().findCultivationEntities();
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

    public List<Cultivation> getItemsAvailableSelectMany() {
        return getJpaController().findCultivationEntities();
    }

    public List<Cultivation> getItemsAvailableSelectOne() {
        return getJpaController().findCultivationEntities();
    }

    @FacesConverter(forClass = Cultivation.class)
    public static class CultivationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CultivationController controller = (CultivationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cultivationController");
            return controller.getJpaController().findCultivation(getKey(value));
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
