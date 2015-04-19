package controlador.controllers;

import model.applications.Application;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import datos.produccion.aplicaciones.AplicacionDAO;
import datos.util.EntityManagerFactorySingleton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.applications.Chemical;

@ManagedBean(name = "aplicacionController")
@SessionScoped
public class AplicacionController implements Serializable {

    private AplicacionDAO jpaController = null;
    private List<Application> items = null;
    private Application selected;
    private List<Chemical> insumos;
    @ManagedProperty(value = "#{permisoController}")
    private PermisoController permisoBean;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;

    public AplicacionController() {
    }

    public Application getSelected() {
        return selected;
    }

    public void setSelected(Application selected) {
        this.selected = selected;
    }

    public PermisoController getPermisoBean() {
        return permisoBean;
    }

    public void setPermisoBean(PermisoController permisoBean) {
        this.permisoBean = permisoBean;
    }

    public SignInController getSignInBean() {
        return signInBean;
    }

    public void setSignInBean(SignInController signInBean) {
        this.signInBean = signInBean;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AplicacionDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new AplicacionDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Application prepareCreate() {
        selected = new Application();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AplicacionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AplicacionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AplicacionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Application> getItems() {
        if (items == null) {
            items = getJpaController().findAplicacionEntitiesForSelectedFarm(signInBean.getFinca());
        }
        return items;
    }

    public List<Chemical> getInsumos() {
        insumos = new ArrayList<Chemical>();
        for (Chemical i : new InsumoController().getItems()) {
            if (i.getAplicationType() == selected.getType()) {
                insumos.add(i);
            }
        }
        return insumos;
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

    public List<Application> getItemsAvailableSelectMany() {
        return getItems();
    }

    public List<Application> getItemsAvailableSelectOne() {
        return getItems();
    }

    public void tipo() {

    }

    @FacesConverter(forClass = Application.class)
    public static class AplicacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AplicacionController controller = (AplicacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "aplicacionController");
            return controller.getJpaController().findAplicacion(getKey(value));
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
            if (object instanceof Application) {
                Application o = (Application) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Application.class.getName()});
                return null;
            }
        }

    }

}
