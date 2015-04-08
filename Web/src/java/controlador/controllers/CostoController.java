package controlador.controllers;

import modelo.finanzas.costo.Costo;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import datos.finanzas.CostoDAO;
import datos.util.EntityManagerFactorySingleton;

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
import modelo.finanzas.costo.TipoCosto;
import modelo.produccion.administracion.Lote;

@ManagedBean(name = "costoController")
@SessionScoped
public class CostoController implements Serializable {

    private Costo selected;
    private List<Costo> items = null;
    private CostoDAO jpaController = null;
    @ManagedProperty(value = "#{permisoController}")
    private PermisoController permisoBean;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;

    public CostoController() {
    }

    public Costo getSelected() {
        return selected;
    }

    public void setSelected(Costo selected) {
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
    
    private CostoDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new CostoDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Costo prepareCreate() {
        selected = new Costo();
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

    public List<Costo> getItems() {
        if (items == null) {
            if (signInBean.getFinca() != null) {
                items = getJpaController().findCostoEntitiesForSelectedFarm(signInBean.getFinca());
            } else {
                JsfUtil.addErrorMessage("Seleccione una Finca");
            }
        }
        return items;
    }

    public List<Costo> getItemsAvailableSelectMany() {
        return getJpaController().findCostoEntities();
    }

    public List<Costo> getItemsAvailableSelectOne() {
        return getJpaController().findCostoEntities();
    }
    
    public List<Costo> leerLista(Lote lote, TipoCosto tipo, Date inicio, Date fin) {
        return getJpaController().findCostoEntities(lote, tipo, inicio, fin);
    }

    public Costo sumarRegistros(Lote lote, TipoCosto tipo, Date inicio, Date fin) {
        List<Costo> leerLista = leerLista(lote, tipo, inicio, fin);
        Costo suma = new Costo();
        for (Costo c : leerLista) {
            //suma.sumar(c);
        }
        return suma;
    }

    @FacesConverter(forClass = Costo.class)
    public static class CostoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CostoController controller = (CostoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "costoController");
            return controller.getJpaController().findCosto(getKey(value));
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
            if (object instanceof Costo) {
                Costo o = (Costo) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Costo.class.getName());
            }
        }

    }

}