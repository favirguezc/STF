package vista;

import modelo.Monitoreodeplagas;
import vista.util.JsfUtil;
import vista.util.JsfUtil.PersistAction;
import controlador.MonitoreodeplagasJpaController;

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

@ManagedBean(name = "monitoreodeplagasController")
@SessionScoped
public class MonitoreodeplagasController implements Serializable {

    private MonitoreodeplagasJpaController jpaController = null;
    private List<Monitoreodeplagas> items = null;
    private Monitoreodeplagas selected;

    public MonitoreodeplagasController() {
    }

    public Monitoreodeplagas getSelected() {
        return selected;
    }

    public void setSelected(Monitoreodeplagas selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MonitoreodeplagasJpaController getJpaController() {
        if (jpaController == null) {
            jpaController = new MonitoreodeplagasJpaController(Persistence.createEntityManagerFactory("WebPU"));
        }
        return jpaController;
    }

    public Monitoreodeplagas prepareCreate() {
        selected = new Monitoreodeplagas();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MonitoreodeplagasCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MonitoreodeplagasUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MonitoreodeplagasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Monitoreodeplagas> getItems() {
        if (items == null) {
            items = getJpaController().findMonitoreodeplagasEntities();
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

    public List<Monitoreodeplagas> getItemsAvailableSelectMany() {
        return getJpaController().findMonitoreodeplagasEntities();
    }

    public List<Monitoreodeplagas> getItemsAvailableSelectOne() {
        return getJpaController().findMonitoreodeplagasEntities();
    }

    @FacesConverter(forClass = Monitoreodeplagas.class)
    public static class MonitoreodeplagasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MonitoreodeplagasController controller = (MonitoreodeplagasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "monitoreodeplagasController");
            return controller.getJpaController().findMonitoreodeplagas(getKey(value));
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
            if (object instanceof Monitoreodeplagas) {
                Monitoreodeplagas o = (Monitoreodeplagas) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Monitoreodeplagas.class.getName()});
                return null;
            }
        }

    }

}
