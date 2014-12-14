package vista;

import modelo.Rolpersona;
import vista.util.JsfUtil;
import vista.util.JsfUtil.PersistAction;
import controlador.RolpersonaJpaController;

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

@ManagedBean(name = "rolpersonaController")
@SessionScoped
public class RolpersonaController implements Serializable {

    private RolpersonaJpaController jpaController = null;
    private List<Rolpersona> items = null;
    private Rolpersona selected;

    public RolpersonaController() {
    }

    public Rolpersona getSelected() {
        return selected;
    }

    public void setSelected(Rolpersona selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RolpersonaJpaController getJpaController() {
        if (jpaController == null) {
            jpaController = new RolpersonaJpaController(Persistence.createEntityManagerFactory("WebPU"));
        }
        return jpaController;
    }

    public Rolpersona prepareCreate() {
        selected = new Rolpersona();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("RolpersonaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("RolpersonaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("RolpersonaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Rolpersona> getItems() {
        if (items == null) {
            items = getJpaController().findRolpersonaEntities();
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

    public List<Rolpersona> getItemsAvailableSelectMany() {
        return getJpaController().findRolpersonaEntities();
    }

    public List<Rolpersona> getItemsAvailableSelectOne() {
        return getJpaController().findRolpersonaEntities();
    }

    @FacesConverter(forClass = Rolpersona.class)
    public static class RolpersonaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RolpersonaController controller = (RolpersonaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "rolpersonaController");
            return controller.getJpaController().findRolpersona(getKey(value));
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
            if (object instanceof Rolpersona) {
                Rolpersona o = (Rolpersona) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Rolpersona.class.getName()});
                return null;
            }
        }

    }

}
