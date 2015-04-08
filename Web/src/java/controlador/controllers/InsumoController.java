package controlador.controllers;

import modelo.produccion.aplicaciones.Insumo;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import datos.produccion.aplicaciones.InsumoDAO;
import datos.util.EntityManagerFactorySingleton;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "insumoController")
public class InsumoController implements Serializable {

    private InsumoDAO jpaController = null;
    private List<Insumo> items = null;
    private Insumo selected;
    @ManagedProperty(value = "#{permisoController}")
    private PermisoController permisoBean;

    public InsumoController() {
    }

    public Insumo getSelected() {
        return selected;
    }

    public void setSelected(Insumo selected) {
        this.selected = selected;
    }

    public PermisoController getPermisoBean() {
        return permisoBean;
    }

    public void setPermisoBean(PermisoController permisoBean) {
        this.permisoBean = permisoBean;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private InsumoDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new InsumoDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Insumo prepareCreate() {
        selected = new Insumo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("InsumoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("InsumoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("InsumoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Insumo> getItems() {
        if (items == null) {
            items = getJpaController().findInsumoEntities();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            if (!permisoBean.currentUserHasPermission(persistAction, selected.getClass())) {
                return;
            }
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

    public List<Insumo> getItemsAvailableSelectMany() {
        return getJpaController().findInsumoEntities();
    }

    public List<Insumo> getItemsAvailableSelectOne() {
        return getJpaController().findInsumoEntities();
    }

    @FacesConverter(forClass = Insumo.class)
    public static class InsumoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            InsumoController controller = (InsumoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "insumoController");
            return controller.getJpaController().findInsumo(getKey(value));
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
            if (object instanceof Insumo) {
                Insumo o = (Insumo) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Insumo.class.getName()});
                return null;
            }
        }

    }

}