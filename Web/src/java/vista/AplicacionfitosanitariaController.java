package vista;

import modelo.produccion.AplicacionFitosanitaria;
import vista.util.JsfUtil;
import vista.util.JsfUtil.PersistAction;
import controlador.AplicacionFitosanitariaJpaController;

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

@ManagedBean(name = "aplicacionFitosanitariaController")
@SessionScoped
public class AplicacionFitosanitariaController implements Serializable {

    private AplicacionFitosanitariaJpaController jpaController = null;
    private List<AplicacionFitosanitaria> items = null;
    private AplicacionFitosanitaria selected;

    public AplicacionFitosanitariaController() {
    }

    public AplicacionFitosanitaria getSelected() {
        return selected;
    }

    public void setSelected(AplicacionFitosanitaria selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AplicacionFitosanitariaJpaController getJpaController() {
        if (jpaController == null) {
            jpaController = new AplicacionFitosanitariaJpaController(Persistence.createEntityManagerFactory("WebPU"));
        }
        return jpaController;
    }

    public AplicacionFitosanitaria prepareCreate() {
        selected = new AplicacionFitosanitaria();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AplicacionFitosanitariaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AplicacionFitosanitariaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AplicacionFitosanitariaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AplicacionFitosanitaria> getItems() {
        if (items == null) {
            items = getJpaController().findAplicacionFitosanitariaEntities();
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

    public List<AplicacionFitosanitaria> getItemsAvailableSelectMany() {
        return getJpaController().findAplicacionFitosanitariaEntities();
    }

    public List<AplicacionFitosanitaria> getItemsAvailableSelectOne() {
        return getJpaController().findAplicacionFitosanitariaEntities();
    }

    @FacesConverter(forClass = AplicacionFitosanitaria.class)
    public static class AplicacionFitosanitariaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AplicacionFitosanitariaController controller = (AplicacionFitosanitariaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "aplicacionFitosanitariaController");
            return controller.getJpaController().findAplicacionFitosanitaria(getKey(value));
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
            if (object instanceof AplicacionFitosanitaria) {
                AplicacionFitosanitaria o = (AplicacionFitosanitaria) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AplicacionFitosanitaria.class.getName()});
                return null;
            }
        }

    }

}
