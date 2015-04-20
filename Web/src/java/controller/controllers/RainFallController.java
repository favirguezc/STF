package controller.controllers;

import model.weather.RainFall;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.weather.RainFallDAO;
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

@ManagedBean(name = "rainFallController")
@SessionScoped
public class RainFallController implements Serializable {

    private RainFallDAO jpaController = null;
    private List<RainFall> items = null;
    private RainFall selected;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;

    public RainFallController() {
    }

    public RainFall getSelected() {
        return selected;
    }

    public void setSelected(RainFall selected) {
        this.selected = selected;
    }

    public PermissionController getPermissionBean() {
        return permissionBean;
    }

    public void setPermissionBean(PermissionController permissionBean) {
        this.permissionBean = permissionBean;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RainFallDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new RainFallDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public RainFall prepareCreate() {
        selected = new RainFall();
        selected.setFarm(permissionBean.getSignInBean().getFarm());
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("RainFallCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("RainFallUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("RainFallDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<RainFall> getItems() {
        if (items == null) {
            items = getJpaController().findRainFallEntities();
        }
        return items;
    }

    public List<RainFall> getItems(Date fecha1,Date fecha2) {
        return getJpaController().findRainFallEntities(fecha1,fecha2);
    }
    
    public List<RainFall> getItems(Date fecha1) {
        return getJpaController().findRainFallEntities(fecha1);
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            if (!permissionBean.currentUserHasPermission(persistAction, selected.getClass())) {
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
        return getJpaController().findRainFallEntities();
    }

    public List<RainFall> getItemsAvailableSelectOne() {
        return getJpaController().findRainFallEntities();
    }

    public float calcularMean(Date fecha1,Date fecha2) {
        float mean = 0;
        List<RainFall> lista = getItems(fecha1,fecha2);
        for (RainFall l : lista) {
            mean += l.getMilimeters();
        }
        if (lista.size() > 1) {
            mean = mean / lista.size();
        }
        return mean;
    }
    
    public float calcularMean(Date fecha1) {
        float mean = 0;
        List<RainFall> lista = getItems(fecha1);
        for (RainFall l : lista) {
            mean += l.getMilimeters();
        }
        if (lista.size() > 1) {
            mean = mean / lista.size();
        }
        return mean;
    }

    @FacesConverter(forClass = RainFall.class)
    public static class RainFallControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RainFallController controller = (RainFallController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "rainfallController");
            return controller.getJpaController().findRainFall(getKey(value));
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
