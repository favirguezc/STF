package controller.controllers;

import model.finances.cash.CashConcept;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import datos.finanzas.ConceptoCajaDAO;
import data.util.EntityManagerFactorySingleton;

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
import javax.faces.model.SelectItem;
import model.finances.cash.Cash;

@ManagedBean(name = "conceptoCajaController")
@SessionScoped
public class ConceptoCajaController implements Serializable {

    private CashConcept selected;
    private List<CashConcept> items = null;
    private ConceptoCajaDAO jpaController = null;
    private List<SelectItem> itemsEntrada = null;
    private Cash cajaFiltro = null;
    private int entrada;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;

    public ConceptoCajaController() {
    }

    public CashConcept getSelected() {
        return selected;
    }

    public void setSelected(CashConcept selected) {
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

    public List<SelectItem> getItemsEntrada() {
        if(itemsEntrada == null){
            itemsEntrada = new ArrayList<SelectItem>();
            SelectItem item = new SelectItem(1, "Entrada");
            itemsEntrada.add(item);
            item = new SelectItem(2, "Salida");
            itemsEntrada.add(item);
        }
        return itemsEntrada;
    }

    public int getEntrada() {
        return entrada;
    }

    public void setEntrada(int entrada) {
        this.entrada = entrada;
    }

    public Cash getCajaFiltro() {
        return cajaFiltro;
    }

    public void setCajaFiltro(Cash cajaFiltro) {
        this.cajaFiltro = cajaFiltro;
    }
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    
    private ConceptoCajaDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new ConceptoCajaDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public CashConcept prepareCreate() {
        selected = new CashConcept();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        if(entrada == 1){
            selected.setIncome(true);
        }else if(entrada == 2){
            selected.setIncome(false);
        }
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ConceptoCajaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void prepareUpdate(){
        entrada = selected.isIncome()?1:2;
    }
    
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ConceptoCajaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ConceptoCajaDeleted"));
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


    public List<CashConcept> getItems() {
        if (items == null) {
            if (signInBean.getFarm() != null) {
                items = getJpaController().findConceptoCajaEntitiesForSelectedFarm(signInBean.getFarm());
                obtenerSaldo();
            } else {
                JsfUtil.addErrorMessage("Seleccione una Farm");
            }
        }
        return items;
    }

    private void obtenerSaldo(){
       float saldo = 0;
       for(CashConcept concepto : items){
           concepto.setBalance(saldo);
           saldo = concepto.getBalance();
       }
    }
    
    public List<CashConcept> getItemsAvailableSelectMany() {
        return getJpaController().findConceptoCajaEntities();
    }

    public List<CashConcept> getItemsAvailableSelectOne() {
        return getJpaController().findConceptoCajaEntities();
    }

    @FacesConverter(forClass = CashConcept.class)
    public static class ConceptoCajaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConceptoCajaController controller = (ConceptoCajaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "conceptoCajaController");
            return controller.getJpaController().findConceptoCaja(getKey(value));
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
            if (object instanceof CashConcept) {
                CashConcept o = (CashConcept) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + CashConcept.class.getName());
            }
        }

    }

}
