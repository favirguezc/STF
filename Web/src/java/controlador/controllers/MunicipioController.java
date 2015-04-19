package controlador.controllers;

import model.administration.Municipality;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import data.administration.MunicipalityDAO;
import data.util.EntityManagerFactorySingleton;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.administration.Department;

@ManagedBean(name = "municipioController")
public class MunicipioController implements Serializable {

    private MunicipalityDAO jpaController = null;
    private List<Municipality> items = null;
    private Municipality selected;

    public MunicipioController() {
    }

    public Municipality getSelected() {
        return selected;
    }

    public void setSelected(Municipality selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MunicipalityDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new MunicipalityDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Municipality prepareCreate() {
        selected = new Municipality();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MunicipioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MunicipioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MunicipioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Municipality> getItems() {
        if (items == null) {
            items = getJpaController().findMunicipioEntities();
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

    public List<Municipality> getItemsAvailableSelectMany() {
        return getJpaController().findMunicipioEntities();
    }

    public List<Municipality> getItemsAvailableSelectOne() {
        return getJpaController().findMunicipioEntities();
    }

    public List<Municipality> getItems(Department departamento) {
        return getJpaController().findMunicipioEntities(departamento);
    }

    @FacesConverter(forClass = Municipality.class)
    public static class MunicipioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MunicipioController controller = (MunicipioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "municipioController");
            return controller.getJpaController().findMunicipio(getKey(value));
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
            if (object instanceof Municipality) {
                Municipality o = (Municipality) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Municipality.class.getName()});
                return null;
            }
        }

    }

}
