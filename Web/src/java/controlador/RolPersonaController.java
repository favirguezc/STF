package controlador;

import modelo.administracion.RolPersona;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import dao.RolPersonaJpaController;

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

@ManagedBean(name = "rolPersonaController")
@SessionScoped
public class RolPersonaController implements Serializable {

    private RolPersonaJpaController jpaController = null;
    private List<RolPersona> items = null;
    private RolPersona selected;

    public RolPersonaController() {
    }

    public RolPersona getSelected() {
        return selected;
    }

    public void setSelected(RolPersona selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RolPersonaJpaController getJpaController() {
        if (jpaController == null) {
            jpaController = new RolPersonaJpaController(Persistence.createEntityManagerFactory("WebPU"));
        }
        return jpaController;
    }

    public RolPersona prepareCreate() {
        selected = new RolPersona();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("RolPersonaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("RolPersonaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("RolPersonaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<RolPersona> getItems() {
        if (items == null) {
            items = getJpaController().findRolPersonaEntities();
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

    public List<RolPersona> getItemsAvailableSelectMany() {
        return getJpaController().findRolPersonaEntities();
    }

    public List<RolPersona> getItemsAvailableSelectOne() {
        return getJpaController().findRolPersonaEntities();
    }

    @FacesConverter(forClass = RolPersona.class)
    public static class RolPersonaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RolPersonaController controller = (RolPersonaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "rolPersonaController");
            return controller.getJpaController().findRolPersona(getKey(value));
        }

        long getKey(String value) {
            long key;
            key = Long.parseLong(value);
            return key;
        }

        String getStringKey(long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof RolPersona) {
                RolPersona o = (RolPersona) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), RolPersona.class.getName()});
                return null;
            }
        }

    }

}
