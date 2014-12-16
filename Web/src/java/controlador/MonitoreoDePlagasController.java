package controlador;

import modelo.produccion.MonitoreoDePlagas;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import dao.MonitoreoDePlagasJpaController;

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

@ManagedBean(name = "monitoreoDePlagasController")
@SessionScoped
public class MonitoreoDePlagasController implements Serializable {

    private MonitoreoDePlagasJpaController jpaController = null;
    private List<MonitoreoDePlagas> items = null;
    private MonitoreoDePlagas selected;

    public MonitoreoDePlagasController() {
    }

    public MonitoreoDePlagas getSelected() {
        return selected;
    }

    public void setSelected(MonitoreoDePlagas selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MonitoreoDePlagasJpaController getJpaController() {
        if (jpaController == null) {
            jpaController = new MonitoreoDePlagasJpaController(Persistence.createEntityManagerFactory("WebPU"));
        }
        return jpaController;
    }

    public MonitoreoDePlagas prepareCreate() {
        selected = new MonitoreoDePlagas();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MonitoreoDePlagasCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MonitoreoDePlagasUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MonitoreoDePlagasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MonitoreoDePlagas> getItems() {
        if (items == null) {
            items = getJpaController().findMonitoreoDePlagasEntities();
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

    public List<MonitoreoDePlagas> getItemsAvailableSelectMany() {
        return getJpaController().findMonitoreoDePlagasEntities();
    }

    public List<MonitoreoDePlagas> getItemsAvailableSelectOne() {
        return getJpaController().findMonitoreoDePlagasEntities();
    }

    @FacesConverter(forClass = MonitoreoDePlagas.class)
    public static class MonitoreoDePlagasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MonitoreoDePlagasController controller = (MonitoreoDePlagasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "monitoreoDePlagasController");
            return controller.getJpaController().findMonitoreoDePlagas(getKey(value));
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
            if (object instanceof MonitoreoDePlagas) {
                MonitoreoDePlagas o = (MonitoreoDePlagas) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MonitoreoDePlagas.class.getName()});
                return null;
            }
        }

    }

}
