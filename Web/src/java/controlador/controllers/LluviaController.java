package controlador.controllers;

import model.weather.RainFall;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import data.weather.LluviaDAO;
import data.util.EntityManagerFactorySingleton;
import java.io.Serializable;
import java.util.Date;
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

@ManagedBean(name = "lluviaController")
@SessionScoped
public class LluviaController implements Serializable {

    private LluviaDAO jpaController = null;
    private List<RainFall> items = null;
    private RainFall selected;
    @ManagedProperty(value = "#{permisoController}")
    private PermisoController permisoBean;

    public LluviaController() {
    }

    public RainFall getSelected() {
        return selected;
    }

    public void setSelected(RainFall selected) {
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

    private LluviaDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new LluviaDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public RainFall prepareCreate() {
        selected = new RainFall();
        selected.setFarm(permisoBean.getSignInBean().getFinca());
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LluviaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LluviaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LluviaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<RainFall> getItems() {
        if (items == null) {
            items = getJpaController().findLluviaEntities();
        }
        return items;
    }

    public List<RainFall> getItems(Date fecha1,Date fecha2) {
        return getJpaController().findLluviaEntities(fecha1,fecha2);
    }
    
    public List<RainFall> getItems(Date fecha1) {
        return getJpaController().findLluviaEntities(fecha1);
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

    public List<RainFall> getItemsAvailableSelectMany() {
        return getJpaController().findLluviaEntities();
    }

    public List<RainFall> getItemsAvailableSelectOne() {
        return getJpaController().findLluviaEntities();
    }

    public float calcularPromedio(Date fecha1,Date fecha2) {
        float promedio = 0;
        List<RainFall> lista = getItems(fecha1,fecha2);
        for (RainFall l : lista) {
            promedio += l.getMilimeters();
        }
        if (lista.size() > 1) {
            promedio = promedio / lista.size();
        }
        return promedio;
    }
    
    public float calcularPromedio(Date fecha1) {
        float promedio = 0;
        List<RainFall> lista = getItems(fecha1);
        for (RainFall l : lista) {
            promedio += l.getMilimeters();
        }
        if (lista.size() > 1) {
            promedio = promedio / lista.size();
        }
        return promedio;
    }

    @FacesConverter(forClass = RainFall.class)
    public static class LluviaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LluviaController controller = (LluviaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "lluviaController");
            return controller.getJpaController().findLluvia(getKey(value));
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
            if (object instanceof RainFall) {
                RainFall o = (RainFall) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), RainFall.class.getName()});
                return null;
            }
        }

    }

}
