package controlador.controllers;

import modelo.produccion.administracion.Contrato;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import datos.produccion.administracion.ContratoDAO;
import datos.produccion.administracion.PersonaDAO;
import datos.util.EntityManagerFactorySingleton;

import java.io.Serializable;
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
import modelo.produccion.administracion.Persona;

@ManagedBean(name = "contratoController")
@SessionScoped
public class ContratoController implements Serializable {

    private ContratoDAO jpaController = null;
    private List<Contrato> items = null;
    private Contrato selected;
    private long cedula;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;
    @ManagedProperty(value = "#{permisoController}")
    private PermisoController permisoBean;

    public ContratoController() {
    }

    public Contrato getSelected() {
        return selected;
    }

    public void setSelected(Contrato selected) {
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

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ContratoDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new ContratoDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Contrato prepareCreate() {
        selected = new Contrato();
        if (signInBean.getFinca() != null) {
            selected.setFinca(signInBean.getFinca());
        } else {
            JsfUtil.addErrorMessage("Seleccione una finca");
            selected = null;
        }
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        Persona findPersonaPorCedula = new PersonaDAO(EntityManagerFactorySingleton.getEntityManagerFactory()).findPersonaPorCedula(cedula);
        if (findPersonaPorCedula == null) {
            JsfUtil.addErrorMessage("La cédula no está registrada.");
            selected=null;
        } else {
            selected.setPersona(findPersonaPorCedula);
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ContratoCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        }

    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ContratoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ContratoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Contrato> getItems() {
        if (items == null) {
            items = getJpaController().findContratoEntities();
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

    public List<Contrato> getItemsAvailableSelectMany() {
        return getJpaController().findContratoEntities();
    }

    public List<Contrato> getItemsAvailableSelectOne() {
        return getJpaController().findContratoEntities();
    }

    Iterable<Contrato> getItems(Persona user) {
        return getJpaController().findContratoEntities(user);
    }

    @FacesConverter(forClass = Contrato.class)
    public static class ContratoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ContratoController controller = (ContratoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "contratoController");
            return controller.getJpaController().findContrato(getKey(value));
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
            if (object instanceof Contrato) {
                Contrato o = (Contrato) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Contrato.class.getName()});
                return null;
            }
        }

    }

}
