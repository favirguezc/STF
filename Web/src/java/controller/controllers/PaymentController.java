package controller.controllers;

import controller.util.JsfUtil;
import model.finances.incomes.Payment;
import data.finances.incomes.PaymentDAO;
import data.finances.incomes.SaleDAO;
import data.util.EntityManagerFactorySingleton;

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
import model.finances.cash.Cash;
import model.finances.cash.CashConcept;
import model.finances.incomes.PaymentMethodEnum;
import model.finances.incomes.Sale;

@ManagedBean(name = "paymentController")
@SessionScoped
public class PaymentController implements Serializable {

    private Payment selected;
    private List<Payment> items = null;
    private PaymentDAO jpaController = null;
    private SaleDAO saleJpaController = null;
    private Cash cash;
    private boolean payWithCash;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;
    @ManagedProperty(value = "#{cashConceptController}")
    private CashConceptController cashConceptController;
    @ManagedProperty(value = "#{saleController}")
    private SaleController saleController;
    

    public PaymentController() {
    }

    public Payment getSelected() {
        return selected;
    }

    public void setSelected(Payment selected) {
        this.selected = selected;
    }

    private PaymentDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new PaymentDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public SaleDAO getSaleJpaController() {
        if (saleJpaController == null){
            saleJpaController = new SaleDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return saleJpaController;
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

    public SaleController getSaleController() {
        return saleController;
    }

    public void setSaleController(SaleController saleController) {
        this.saleController = saleController;
    }

    public Cash getCash() {
        return cash;
    }

    public void setCash(Cash cash) {
        this.cash = cash;
    }

    public boolean isPayWithCash() {
        return payWithCash;
    }

    public void setPayWithCash(boolean payWithCash) {
        this.payWithCash = payWithCash;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    
    public void prepareCreate() {
        selected = new Payment();
        cash = new Cash();
        payWithCash = true;
        if (signInBean.getFarm() != null) {
            selected.setFarm(signInBean.getFarm());
            initializeEmbeddableKey();
        } else {
            JsfUtil.addErrorMessage("Seleccione una finca");
            selected = null;
        }
    }

    public void create() {
        if(payWithCash){
            selected.setBank(null);
        }
        persist(JsfUtil.PersistAction.CREATE, ResourceBundle.getBundle("/BundlePayment").getString("PaymentCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void prepareUpdate(){
        cash = selected.getAsociatedConcept().getCash();
        verifyPayWithCash();
    }
    
    public void update() {
        if(payWithCash){
            selected.setBank(null);
        }
        persist(JsfUtil.PersistAction.UPDATE, ResourceBundle.getBundle("/BundlePayment").getString("PaymentUpdated"));
    }

    public void prepareView(){
        cash = selected.getAsociatedConcept().getCash();
    }
    
    public void destroy() {
        persist(JsfUtil.PersistAction.DELETE, ResourceBundle.getBundle("/BundlePayment").getString("PaymentDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            if (!getPermissionBean().currentUserHasPermission(persistAction, selected.getClass())) {
                return;
            }
            try {
                if (persistAction == JsfUtil.PersistAction.UPDATE) {
                    verifyUsedValue();
                    editCashConcept();
                    getJpaController().edit(selected);
                } else if (persistAction == JsfUtil.PersistAction.CREATE) {
                    verifySalesPayable();
                    createCashConcept();
                    getJpaController().create(selected);
                } else {
                    undoPayment();
                    getJpaController().destroy(selected.getId());
                }
                cashConceptController.setNullItems();
                saleController.setNullItems();
                JsfUtil.addSuccessMessage(successMessage);
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    private void verifyUsedValue(){
        if(selected.getUsedValue() > selected.getPaymentValue()){
            checkPayWithUnUsedValue(selected.getUsedValue() - selected.getPaymentValue());
            selected.setUsedValue(selected.getPaymentValue());
        }else{
            float valueForUse = selected.getPaymentValue() - selected.getUsedValue();
            float usedValue = valueForUse - paySalesPayable(valueForUse);
            selected.setUsedValue(selected.getUsedValue() + usedValue);
        }
    }
    
    private void verifySalesPayable(){
        if(!existPaymentsWithUnusedValue()){
            float valueForUse = selected.getPaymentValue();
            float usedValue = selected.getPaymentValue() - paySalesPayable(valueForUse);
            selected.setUsedValue(usedValue);
        }else{
            selected.setUsedValue(0);
        }
    }
    
    private boolean existPaymentsWithUnusedValue(){
        List<Payment> paymentsWereNotUse = getJpaController().findPaymentEntitiesWithUnusedValue(signInBean.getFarm(), selected.getCustomer());
        if(paymentsWereNotUse != null && !paymentsWereNotUse.isEmpty()){
            for(Payment payment : paymentsWereNotUse){
                float valueForUse = payment.getPaymentValue() - payment.getUsedValue();
                float usedValue = valueForUse - paySalesPayable(valueForUse);
                if(usedValue == valueForUse){
                    payment.setUsedValue(payment.getPaymentValue());
                    updatePayment(payment);
                }else{
                    payment.setUsedValue(payment.getUsedValue() + usedValue);
                    updatePayment(payment);
                    return true;
                }
            }
            return false;
        }else{
            return false;
        }
    }
    
    private float paySalesPayable(float valueForUse){
        List<Sale> salesPayable = getSaleJpaController().findSaleEntitiesPayable(signInBean.getFarm(), selected.getCustomer());
        if(salesPayable != null && !salesPayable.isEmpty()){
            for(Sale sale : salesPayable){
                float valuePayable = sale.getValuePayable();
                if(valuePayable >= valueForUse){
                    sale.setValuePayable(valuePayable - valueForUse);
                    updateSale(sale);
                    return 0;
                }else{
                    sale.setValuePayable(0);
                    updateSale(sale);
                    valueForUse = valueForUse - valuePayable;
                }
            }
        }
        return valueForUse;
    }
    
    private void updateSale(Sale sale){
        try {
            getSaleJpaController().edit(sale);
        } catch (Exception ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void updatePayment(Payment payment){
        try{
            getJpaController().edit(payment);
        }catch(Exception ex){
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void undoPayment(){
        if(selected.getUsedValue() != 0){
            checkPayWithUnUsedValue(selected.getUsedValue());
        }
    }
    
    private void checkPayWithUnUsedValue(float value){
        List<Payment> payments = getJpaController().findPaymentEntitiesWithUnusedValue(signInBean.getFarm(), selected.getCustomer());
        if(payments != null && !payments.isEmpty()){
            int size = payments.size();
            for(Payment payment : payments){
                if(payment.getId() != selected.getId()){
                    float unUsedValue = payment.getPaymentValue() - payment.getUsedValue();
                    if(unUsedValue >= value){
                        payment.setUsedValue(payment.getUsedValue() + value);
                        updatePayment(payment);
                        break;
                    }else{
                        payment.setUsedValue(payment.getPaymentValue());
                        updatePayment(payment);
                        value = value - unUsedValue;
                    }
                    
                }
                size--;
            }
            if(size==0){
                undoPaySale(value);
            }
        }else{
            undoPaySale(value);
        }
    }
    
    private void undoPaySale(float value){
        List<Sale> paidSales = getSaleJpaController().findSaleEntitiesPaidAndPayable(signInBean.getFarm(), selected.getCustomer());
        if(paidSales != null && !paidSales.isEmpty()){
            for(Sale sale : paidSales){
                float saleValue = sale.getSaleTotalValue() - sale.getValuePayable();
                if(saleValue >= value){
                    sale.setValuePayable(sale.getValuePayable() + value);
                    updateSale(sale);
                    break;
                }else{
                    sale.setValuePayable(sale.getSaleTotalValue());
                    updateSale(sale);
                    value = value - saleValue;
                }
            }
        }
    }
    
    private void createCashConcept(){
        CashConcept concept = new CashConcept();
        setCashConcept(concept);
        
    }
    
    private void editCashConcept() throws Exception{
        CashConcept concept = selected.getAsociatedConcept();
        setCashConcept(concept);
    }
    
    private void setCashConcept(CashConcept concept){
        concept.setConceptDate(selected.getPaymentDate());
        concept.setDescription("Pago de " + selected.getCustomer().getName() + " " + selected.getCustomer().getLastName());
        concept.setIncome(true);
        concept.setConceptValue(selected.getPaymentValue());
        concept.setCash(cash);
        concept.setAutogenerated(true);
        selected.setAsociatedConcept(concept);
    }
    
    public void verifyPayWithCash(){
        if(selected.getPaymentMethod() == PaymentMethodEnum.CASH){
            payWithCash = true;
        }else{
            payWithCash = false;
        }
    }
            
    public List<Payment> getItems() {
        if (items == null || items.isEmpty()) {
            if (signInBean.getFarm() != null) {
                items = getJpaController().findPaymentEntitiesForSelectedFarm(signInBean.getFarm());
            } else {
                JsfUtil.addErrorMessage("Seleccione una finca");
            }
        }
        return items;
    }

    public List<Payment> getItemsAvailableSelectMany() {
        return getJpaController().findPaymentEntities();
    }

    public List<Payment> getItemsAvailableSelectOne() {
        return getJpaController().findPaymentEntities();
    }

    @FacesConverter(forClass = Payment.class)
    public static class PaymentControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PaymentController controller = (PaymentController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "paymentController");
            return controller.getJpaController().findPayment(getKey(value));
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
            if (object instanceof Payment) {
                Payment o = (Payment) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Payment.class.getName());
            }
        }

    }

}
