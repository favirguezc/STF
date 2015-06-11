/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controllers;

import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import data.finances.expenses.ChemicalPurchaseDAO;
import data.finances.PriceDAO;
import data.finances.cash.CashConceptDAO;
import data.util.EntityManagerFactorySingleton;
import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.convert.FacesConverter;
import model.finances.Price;
import model.finances.expenses.ChemicalPurchase;
import model.administration.Farm;
import model.finances.cash.Cash;
import model.finances.cash.CashConcept;

/**
 *
 * @author John Fredy
 */
@ManagedBean(name = "chemicalPurchaseController")
@SessionScoped
public class ChemicalPurchaseController implements Serializable{

    private ChemicalPurchase selected = null;
    private List<ChemicalPurchase> items = null;
    private Price price = null;
    private boolean newPrice;
    private Cash cash;
    private boolean pay;
    private CashConcept deleteConcept;
    private ChemicalPurchaseDAO jpaController = null;
    private PriceDAO priceJpaController = null;
    private CashConceptDAO conceptJpaController = null;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;
    @ManagedProperty(value = "#{cashConceptController}")
    private CashConceptController cashConceptController;

    public ChemicalPurchaseController() {
    }
    
    public ChemicalPurchaseDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new ChemicalPurchaseDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }
    
    private PriceDAO getPriceJpaController(){
        if(priceJpaController == null){
            priceJpaController = new PriceDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return priceJpaController;
    }

    private CashConceptDAO getConceptJpaController(){
        if(conceptJpaController == null){
            conceptJpaController = new CashConceptDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return conceptJpaController;
    }
    
    public ChemicalPurchase getSelected() {
        return selected;
    }

    public void setSelected(ChemicalPurchase selected) {
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

    public CashConceptController getCashConceptController() {
        return cashConceptController;
    }

    public void setCashConceptController(CashConceptController cashConceptController) {
        this.cashConceptController = cashConceptController;
    }
    
    public Cash getCash() {
        return cash;
    }

    public void setCash(Cash cash) {
        this.cash = cash;
    }

    public boolean isPay() {
        return pay;
    }

    public void setPay(boolean pay) {
        this.pay = pay;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    
    public List<ChemicalPurchase> getItemsAvailableSelectMany() {
        return getJpaController().findChemicalPurchaseEntities();
    }

    public List<ChemicalPurchase> getItemsAvailableSelectOne() {
        return getJpaController().findChemicalPurchaseEntities();
    }

    public List<ChemicalPurchase> getItems() {
        if (items == null) {
            if (signInBean.getFarm() != null) {
                items = getJpaController().findChemicalPurchaseEntitiesForSelectedFarm(signInBean.getFarm());
            } else {
                JsfUtil.addErrorMessage("Seleccione una finca");
            }
        }
        return items;
    }
    
    public ChemicalPurchase prepareCreate() {
        selected = new ChemicalPurchase();
        pay = false;
        if (signInBean.getFarm() != null) {
            selected.setFarm(signInBean.getFarm());
            initializeEmbeddableKey();
        } else {
            JsfUtil.addErrorMessage("Seleccione una finca");
            selected = null;
        }
        return selected;
    }
    
    public void create() {
        savePrice();
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleChemicalPurchase").getString("ChemicalPurchaseCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void prepareUpdate(){
        verifyPaid();
        price = getPriceJpaController().findPrice(selected.getChemical().getName());
    }
    
    public void update() {
        savePrice();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleChemicalPurchase").getString("ChemicalPurchaseUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleChemicalPurchase").getString("ChemicalPurchaseDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            if (!permissionBean.currentUserHasPermission(persistAction, selected.getClass())) {
                return;
            }
            try {
                if (persistAction == PersistAction.UPDATE) {
                    editCashConcept();
                    getJpaController().edit(selected);
                    if(deleteConcept != null){
                        getConceptJpaController().destroy(deleteConcept.getId());
                    }
                } else if (persistAction == PersistAction.CREATE) {
                    createCashConcept();
                    getJpaController().create(selected);
                } else {
                    getJpaController().destroy(selected.getId());
                }
                cashConceptController.setNullItems();
                JsfUtil.addSuccessMessage(successMessage);
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }
    
    public List<ChemicalPurchase> readList(Farm farm, Date start, Date end) {
        return getJpaController().findChemicalPurchaseEntities(farm, start, end);
    }
    
    public ChemicalPurchase sumRegistries(Farm farm, Date start, Date end) {
        List<ChemicalPurchase> readList = readList(farm, start, end);
        ChemicalPurchase sum = new ChemicalPurchase(null, farm, null,0,0);
        for (ChemicalPurchase v : readList) {
            sum.add(v);
        }
        return sum;
    }
    
    public void verifyPrice(){
        if(selected.getChemical() != null){
            //search price by item
            price = getPriceJpaController().findPrice(selected.getChemical().getName());
            //if exists set
            if(price != null){
                newPrice = false;
                selected.setPrice(price.getPriceValue());
            }else{
                //else create new price
                newPrice = true;
                price = new Price(selected.getChemical().getName(),0);
                selected.setPrice(0);
            }
        }
    }
    
    public void savePrice(){
        if(newPrice){
            price.setPriceValue(selected.getPrice());
            getPriceJpaController().create(price);
        }else{
            try {
                price.setPriceValue(selected.getPrice());
                getPriceJpaController().edit(price);
            } catch (Exception ex) {
                Logger.getLogger(ChemicalPurchaseController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void createCashConcept(){
        if(pay){
            CashConcept concept = new CashConcept();
            setCashConcept(concept);
        }
    }
    
    private void editCashConcept(){
        deleteConcept = null;
        if(pay){
            CashConcept concept = selected.getAsociatedConcept();
            if(concept == null){
                concept = new CashConcept();   
            }
            setCashConcept(concept);
        }else{
            CashConcept concept = selected.getAsociatedConcept();
            if(concept != null){
                deleteConcept = concept;
                selected.setAsociatedConcept(null);
            }
        }
        
    }
    
    private void setCashConcept(CashConcept concept){
        concept.setConceptDate(selected.getPurchaseDate());
        concept.setDescription("Compra de " + selected.getChemical().getName());
        concept.setIncome(false);
        concept.setConceptValue(selected.getTotal());
        concept.setCash(cash);
        selected.setAsociatedConcept(concept);
    }
    
    public void verifyPaid(){
        if(selected.getAsociatedConcept() != null){
            pay = true;
        }else{
            pay = false;
        }
    }

    @FacesConverter(forClass = ChemicalPurchase.class)
    public static class ChemicalPurchaseConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ChemicalPurchaseController controller = (ChemicalPurchaseController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "chemicalPurchaseController");
            return controller.getJpaController().findChemicalPurchase(getKey(value));
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
            if (object instanceof ChemicalPurchase) {
                ChemicalPurchase o = (ChemicalPurchase) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ChemicalPurchase.class.getName());
            }
        }

    }
}
