package vista;

import modelo.Humedaddelsuelo;
import vista.util.JsfUtil;
import vista.util.JsfUtil.PersistAction;
import controlador.HumedaddelsueloJpaController;

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

@ManagedBean(name = "humedaddelsueloController")
@SessionScoped
public class HumedaddelsueloController implements Serializable {

    private HumedaddelsueloJpaController jpaController = null;
    private List<Humedaddelsuelo> items = null;
    private Humedaddelsuelo selected;

    public HumedaddelsueloController() {
    }

    public Humedaddelsuelo getSelected() {
        return selected;
    }

    public void setSelected(Humedaddelsuelo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private HumedaddelsueloJpaController getJpaController() {
        if (jpaController == null) {
            jpaController = new HumedaddelsueloJpaController(Persistence.createEntityManagerFactory("WebPU"));
        }
        return jpaController;
    }

    public Humedaddelsuelo prepareCreate() {
        selected = new Humedaddelsuelo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("HumedaddelsueloCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("HumedaddelsueloUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("HumedaddelsueloDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Humedaddelsuelo> getItems() {
        if (items == null) {
            items = getJpaController().findHumedaddelsueloEntities();
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

    public List<Humedaddelsuelo> getItemsAvailableSelectMany() {
        return getJpaController().findHumedaddelsueloEntities();
    }

    public List<Humedaddelsuelo> getItemsAvailableSelectOne() {
        return getJpaController().findHumedaddelsueloEntities();
    }

    @FacesConverter(forClass = Humedaddelsuelo.class)
    public static class HumedaddelsueloControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            HumedaddelsueloController controller = (HumedaddelsueloController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "humedaddelsueloController");
            return controller.getJpaController().findHumedaddelsuelo(getKey(value));
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
            if (object instanceof Humedaddelsuelo) {
                Humedaddelsuelo o = (Humedaddelsuelo) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Humedaddelsuelo.class.getName()});
                return null;
            }
        }

    }

}
