package controller.controllers;

import model.finances.cash.CashConcept;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.finances.cash.CashConceptDAO;
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

@ManagedBean(name = "cashConceptController")
@SessionScoped
public class CashConceptController implements Serializable {

    private CashConcept selected;
    private List<CashConcept> items = null;
    private CashConceptDAO jpaController = null;
    private List<SelectItem> incomeItems = null;
    private Cash cashFilter = null;
    private int income;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;

    public CashConceptController() {
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

    public List<SelectItem> getIncomeItems() {
        if(incomeItems == null){
            incomeItems = new ArrayList<SelectItem>();
            SelectItem item = new SelectItem(1, "Entrada");
            incomeItems.add(item);
            item = new SelectItem(2, "Salida");
            incomeItems.add(item);
        }
        return incomeItems;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public Cash getCashFilter() {
        return cashFilter;
    }

    public void setCashFilter(Cash cashFilter) {
        this.cashFilter = cashFilter;
    }
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    
    private CashConceptDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new CashConceptDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public CashConcept prepareCreate() {
        selected = new CashConcept();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        if(income == 1){
            selected.setIncome(true);
        }else if(income == 2){
            selected.setIncome(false);
        }
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleCashConcept").getString("CashConceptCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void prepareUpdate(){
        income = selected.isIncome()?1:2;
    }
    
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleCashConcept").getString("CashConceptUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleCashConcept").getString("CashConceptDeleted"));
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
                items = getJpaController().findCashConceptEntitiesForSelectedFarm(signInBean.getFarm());
                calculateBalance();
            } else {
                JsfUtil.addErrorMessage("Seleccione una finca");
            }
        }
        return items;
    }

    private void calculateBalance(){
       float balance = 0;
       for(CashConcept concept : items){
           concept.setBalance(balance);
           balance = concept.getBalance();
       }
    }
    
    public List<CashConcept> getItemsAvailableSelectMany() {
        return getJpaController().findCashConceptEntities();
    }

    public List<CashConcept> getItemsAvailableSelectOne() {
        return getJpaController().findCashConceptEntities();
    }

    @FacesConverter(forClass = CashConcept.class)
    public static class CashConceptControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CashConceptController controller = (CashConceptController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cashConceptController");
            return controller.getJpaController().findCashConcept(getKey(value));
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
