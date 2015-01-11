package controlador;

import modelo.produccion.recoleccion.Recoleccion;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import dao.RecoleccionJpaController;

import java.io.Serializable;
import java.util.Date;
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
import modelo.produccion.administracion.Lote;
import modelo.produccion.administracion.Persona;

@ManagedBean(name = "recoleccionController")
@SessionScoped
public class RecoleccionController implements Serializable {

    private RecoleccionJpaController jpaController = null;
    private List<Recoleccion> items = null;
    private Recoleccion selected;

    public RecoleccionController() {
    }

    public Recoleccion getSelected() {
        return selected;
    }

    public void setSelected(Recoleccion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RecoleccionJpaController getJpaController() {
        if (jpaController == null) {
            jpaController = new RecoleccionJpaController(Persistence.createEntityManagerFactory("WebPU"));
        }
        return jpaController;
    }

    public Recoleccion prepareCreate() {
        selected = new Recoleccion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("RecoleccionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("RecoleccionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("RecoleccionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Recoleccion> getItems() {
        if (items == null) {
            items = getJpaController().findRecoleccionEntities();
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

    public List<Recoleccion> getItemsAvailableSelectMany() {
        return getJpaController().findRecoleccionEntities();
    }

    public List<Recoleccion> getItemsAvailableSelectOne() {
        return getJpaController().findRecoleccionEntities();
    }

    public List<Recoleccion> leerLista(Persona recolector, Lote lote, Date inicio, Date fin) {
        return getJpaController().findRecoleccionEntities(recolector, lote, inicio, fin);
    }

    public Recoleccion sumarRegistros(Persona recolector, Lote lote, Date inicio, Date fin) {
        List<Recoleccion> leerLista = leerLista(recolector, lote, inicio, fin);
        Recoleccion suma = new Recoleccion(lote, null, recolector, 0, 0, 0, 0, 0, 0, 0);
        for (Recoleccion r : leerLista) {
            suma.sumar(r);
        }
        return suma;
    }

    @FacesConverter(forClass = Recoleccion.class)
    public static class RecoleccionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RecoleccionController controller = (RecoleccionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "recoleccionController");
            return controller.getJpaController().findRecoleccion(getKey(value));
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
            if (object instanceof Recoleccion) {
                Recoleccion o = (Recoleccion) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Recoleccion.class.getName()});
                return null;
            }
        }

    }

}
