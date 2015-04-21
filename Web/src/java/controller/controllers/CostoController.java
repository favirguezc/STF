package controller.controllers;

import model.finances.cost.Cost;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.finances.cost.CostDAO;
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
import model.administration.ModuleClass;
import model.finances.cost.CostTypeEnum;

@ManagedBean(name = "costoController")
@SessionScoped
public class CostoController implements Serializable {

    private Cost selected;
    private List<Cost> items = null;
    private CostDAO jpaController = null;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;

    public CostoController() {
    }

    public Cost getSelected() {
        return selected;
    }

    public void setSelected(Cost selected) {
        this.selected = selected;
    }

    public PermissionController getPermissionBean() {
        return permissionBean;
    }

    public void setPermissionBean(PermissionController permissionBean) {
        this.permissionBean = permissionBean;
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
    
    private CostDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new CostDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Cost prepareCreate() {
        selected = new Cost();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CostoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CostoUpdated"));
        
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CostoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
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

    public List<Cost> getItems() {
        if (items == null) {
            if (signInBean.getFarm() != null) {
                items = getJpaController().findCostEntitiesForSelectedFarm(signInBean.getFarm());
            } else {
                JsfUtil.addErrorMessage("Seleccione una Farm");
            }
        }
        return items;
    }

    public List<Cost> getItemsAvailableSelectMany() {
        return getJpaController().findCostEntities();
    }

    public List<Cost> getItemsAvailableSelectOne() {
        return getJpaController().findCostEntities();
    }
    
    public List<Cost> leerLista(ModuleClass moduleclass, CostTypeEnum tipo, Date inicio, Date fin) {
        return getJpaController().findCostEntities(moduleclass, tipo, inicio, fin);
    }

    public Cost sumarRegistros(ModuleClass moduleclass, CostTypeEnum tipo, Date inicio, Date fin) {
        List<Cost> leerLista = leerLista(moduleclass, tipo, inicio, fin);
        Cost suma = new Cost();
        for (Cost c : leerLista) {
            //suma.sumar(c);
        }
        return suma;
    }

    @FacesConverter(forClass = Cost.class)
    public static class CostoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CostoController controller = (CostoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "costoController");
            return controller.getJpaController().findCost(getKey(value));
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
            if (object instanceof Cost) {
                Cost o = (Cost) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Cost.class.getName());
            }
        }

    }

}
