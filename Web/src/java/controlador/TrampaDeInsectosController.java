package controlador;

import modelo.produccion.monitoreo.TrampaDeInsectos;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import datos.produccion.monitoreo.TrampaDeInsectosDAO;
import datos.util.EntityManagerFactorySingleton;
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

@ManagedBean(name = "trampaDeInsectosController")
@SessionScoped
public class TrampaDeInsectosController implements Serializable {

    private TrampaDeInsectosDAO jpaController = null;
    private List<TrampaDeInsectos> items = null;
    private TrampaDeInsectos selected;

    public TrampaDeInsectosController() {
    }

    public TrampaDeInsectos getSelected() {
        return selected;
    }

    public void setSelected(TrampaDeInsectos selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TrampaDeInsectosDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new TrampaDeInsectosDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public TrampaDeInsectos prepareCreate() {
        selected = new TrampaDeInsectos();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("TrampaDeInsectosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("TrampaDeInsectosUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("TrampaDeInsectosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TrampaDeInsectos> getItems() {
        if (items == null) {
            items = getJpaController().findTrampaDeInsectosEntities();
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

    public List<TrampaDeInsectos> getItemsAvailableSelectMany() {
        return getJpaController().findTrampaDeInsectosEntities();
    }

    public List<TrampaDeInsectos> getItemsAvailableSelectOne() {
        return getJpaController().findTrampaDeInsectosEntities();
    }

    @FacesConverter(forClass = TrampaDeInsectos.class)
    public static class TrampaDeInsectosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TrampaDeInsectosController controller = (TrampaDeInsectosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "trampaDeInsectosController");
            return controller.getJpaController().findTrampaDeInsectos(getKey(value));
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
            if (object instanceof TrampaDeInsectos) {
                TrampaDeInsectos o = (TrampaDeInsectos) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TrampaDeInsectos.class.getName()});
                return null;
            }
        }

    }

}
