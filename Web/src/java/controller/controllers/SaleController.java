/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controllers;

import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.exceptions.NonexistentEntityException;
import data.finances.PriceDAO;
import data.finances.incomes.PaymentDAO;
import data.finances.incomes.SaleDAO;
import data.finances.incomes.SaleItemDAO;
import data.util.EntityManagerFactorySingleton;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.finances.Price;
import model.finances.incomes.Sale;
import model.administration.Person;
import model.crop.ClassificationTypeEnum;
import model.finances.incomes.Payment;
import model.finances.incomes.SaleItem;
import model.util.DateTools;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author JohnFredy
 */
@ManagedBean(name = "saleController")
@SessionScoped
public class SaleController implements Serializable {

    private Sale selected;
    private List<Sale> items = null;
    private SaleDAO jpaController = null;
    private PriceDAO priceJpaController = null;
    private PaymentDAO paymentJpaController = null;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;
    private SaleItemDAO saleItemJpaController = null;
    private List<Price> prices = null;
    private List<SaleItem> saleItems = null;
    private List<SaleItem> oldSaleItems = null;
    private boolean extraType;
    private boolean oneType;
    private boolean twoType;
    private boolean threeType;
    private boolean fourType;
    private boolean fiveType;
    private boolean dummieType;
    private boolean editing;
    //Chart variables
    private LineChartModel model;
    private Date date;
    private int year;
    private int month;
    private int period;
    private String option;
    private Person customer;

    public SaleController() {
        year = DateTools.getYear();
        month = DateTools.getMonth();
        date = DateTools.getDate();
        period = 0;
        option = "cantidad";
    }

    @PostConstruct
    public void init() {
        createChart();
    }

    private SaleDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new SaleDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    private PriceDAO getPriceJpaController(){
        if(priceJpaController == null){
            priceJpaController = new PriceDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return priceJpaController;
    }
    
    private SaleItemDAO getSaleItemJpaController() {
        if (saleItemJpaController == null) {
            saleItemJpaController = new SaleItemDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return saleItemJpaController;
    }

    public PaymentDAO getPaymentJpaController() {
        if (paymentJpaController == null) {
            paymentJpaController = new PaymentDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return paymentJpaController;
    }
    
    public Sale getSelected() {
        return selected;
    }

    public void setSelected(Sale selected) {
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

    public List<Sale> getItems() {
        if (items == null) {
            if (signInBean.getFarm() != null) {
                items = getJpaController().findSaleEntitiesForSelectedFarm(signInBean.getFarm());
            } else {
                JsfUtil.addErrorMessage("Seleccione una finca");
            }
        }
        return items;
    }
    
    public boolean isExtraType() {
        return extraType;
    }

    public void setExtraType(boolean extraType) {
        this.extraType = extraType;
    }

    public boolean isOneType() {
        return oneType;
    }

    public void setOneType(boolean oneType) {
        this.oneType = oneType;
    }

    public boolean isTwoType() {
        return twoType;
    }

    public void setTwoType(boolean twoType) {
        this.twoType = twoType;
    }

    public boolean isThreeType() {
        return threeType;
    }

    public void setThreeType(boolean threeType) {
        this.threeType = threeType;
    }

    public boolean isFourType() {
        return fourType;
    }

    public void setFourType(boolean fourType) {
        this.fourType = fourType;
    }

    public boolean isFiveType() {
        return fiveType;
    }

    public void setFiveType(boolean fiveType) {
        this.fiveType = fiveType;
    }

    public boolean isDummieType() {
        return dummieType;
    }

    public void setDummieType(boolean dummieType) {
        this.dummieType = dummieType;
    }

    public LineChartModel getModel() {
        return model;
    }

    public void setModel(LineChartModel model) {
        this.model = model;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public SaleItem getExtraTypeSaleItem(){
        SaleItem extraTypeSaleItem = assignSaleItem(ClassificationTypeEnum.EXTRA, extraType);
        return extraTypeSaleItem;
    }
    
    public SaleItem getOneTypeSaleItem(){
        SaleItem oneTypeSaleItem = assignSaleItem(ClassificationTypeEnum.ONE, oneType);
        return oneTypeSaleItem;
    }
    
    public SaleItem getTwoTypeSaleItem(){
        SaleItem twoTypeSaleItem = assignSaleItem(ClassificationTypeEnum.TWO, twoType);
        return twoTypeSaleItem;
    }
    
    public SaleItem getThreeTypeSaleItem(){
        SaleItem threeTypeSaleItem = assignSaleItem(ClassificationTypeEnum.THREE, threeType);
        return threeTypeSaleItem;
    }
    
    public SaleItem getFourTypeSaleItem(){
        SaleItem fourTypeSaleItem = assignSaleItem(ClassificationTypeEnum.FOUR, fourType);
        return fourTypeSaleItem;
    }
    
    public SaleItem getFiveTypeSaleItem(){
        SaleItem fiveTypeSaleItem = assignSaleItem(ClassificationTypeEnum.FIVE, fiveType);
        return fiveTypeSaleItem;
    }
    
    public SaleItem getDummieTypeSaleItem(){
        SaleItem dummieTypeSaleItem = assignSaleItem(ClassificationTypeEnum.DUMMIE, dummieType);
        return dummieTypeSaleItem;
    }
    
    private SaleItem assignSaleItem(ClassificationTypeEnum type, boolean allowed){
        SaleItem saleItem = new SaleItem();
        if(allowed){
            saleItem.setType(type);
            if(editing){
                //when editing
                if(exitsOldSaleItem(type)){
                    if(!exitsSaleItem(type)){
                        //if old one exits then use
                        saleItem = searchOldSaleItemByType(type);
                        saleItems.add(saleItem);
                    }else{
                        //when read
                        saleItem = searchSaleItemByType(type);
                    }
                }else{
                    if(exitsSaleItem(type)){
                        //when read
                        saleItem = searchSaleItemByType(type);
                    }else{
                        //when create
                        Price price = searchPriceByType(type);
                        if(price != null){ //if exits then set
                            saleItem.setUnitValue(price.getPriceValue());
                        }
                        saleItems.add(saleItem);
                    }
                }
            }else{
                if(exitsSaleItem(type)){
                    //when read
                    saleItem = searchSaleItemByType(type);
                }else{
                    //when create
                    Price price = searchPriceByType(type);
                    if(price != null){ //if exits then set
                        saleItem.setUnitValue(price.getPriceValue());
                    }
                    saleItems.add(saleItem);
                }
            }
        }else{
            saleItem = null;
            removeSaleItem(type);
            removePrice(type);
            
        }
        return saleItem;
    }
    
    private boolean exitsSaleItem(ClassificationTypeEnum type){
        return exitsSaleItem(type, saleItems);
    }
    
    private boolean exitsOldSaleItem(ClassificationTypeEnum type){
        return exitsSaleItem(type, oldSaleItems);
    }
    
    private boolean exitsSaleItem(ClassificationTypeEnum type, List<SaleItem> items){
        for(SaleItem item : items){
            if(item.getType() == type){
                return true;
            }
        }
        return false;
    }
    
    private SaleItem searchSaleItemByType(ClassificationTypeEnum type){
        return searchSaleItemByType(type, saleItems);
    }
    
    private SaleItem searchOldSaleItemByType(ClassificationTypeEnum type){
        return searchSaleItemByType(type, oldSaleItems);
    }
    
    private SaleItem searchSaleItemByType(ClassificationTypeEnum type, List<SaleItem> items){
        SaleItem found = null;
        for(SaleItem item : items){
            if(item.getType() == type){
                found = item;
            }
        }
        return found;
    }
    
    private Price searchPriceByType(ClassificationTypeEnum type){
        Price price = getPriceJpaController().findPrice(type.toString());
        if(price == null){
            price = new Price();
            price.setItem(type.toString());
            prices.add(price);
            return null;
        }
        prices.add(price);
        return price;
    }
    
    private void removeSaleItem(ClassificationTypeEnum type){
        Iterator<SaleItem> iterator = saleItems.iterator();
        while(iterator.hasNext()){
            SaleItem item = iterator.next();
            if(item.getType() == type){
                iterator.remove();
            }
        }
    }
    
    private void removePrice(ClassificationTypeEnum type){
        Iterator<Price> iterator = prices.iterator();
        while(iterator.hasNext()){
            Price price = iterator.next();
            if(price.getItem().equals(type.toString())){
                iterator.remove();
            }
        }
    }
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private void initializeSaleItems(){
        saleItems = new ArrayList<SaleItem>();
        initializeTypeBooleans();
        prices = new ArrayList<Price>();
    }
    
    private void initializeTypeBooleans(){
        extraType = false;
        oneType = false;
        twoType = false;
        threeType = false;
        fourType = false;
        fiveType = false;
        dummieType = false;
    }
    
    public Sale prepareCreate() {
        selected = new Sale();
        if (signInBean.getFarm() != null) {
            selected.setFarm(signInBean.getFarm());
            initializeEmbeddableKey();
            initializeSaleItems();
        } else {
            JsfUtil.addErrorMessage("Seleccione una finca");
            selected = null;
        }
        return selected;
    }

    public void create() {
        //savePrecio();
        calculateSaleTotalValue();
        float valuePayable = verifyPaymentsWithUnUsedValue(selected.getSaleTotalValue());
        selected.setValuePayable(valuePayable);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleSale").getString("SaleCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    private float verifyPaymentsWithUnUsedValue(float valuePayable){
        List<Payment> unUsedPays = getPaymentJpaController().findPaymentEntitiesWithUnusedValue(signInBean.getFarm());
        if(unUsedPays != null && !unUsedPays.isEmpty()){
            for(Payment payment : unUsedPays){
                float unUsedValue = payment.getPaymentValue() - payment.getUsedValue();
                if(unUsedValue >= valuePayable){
                    payment.setUsedValue(payment.getUsedValue() + valuePayable);
                    updatePayment(payment);
                    return 0;
                }else{
                    payment.setUsedValue(payment.getPaymentValue());
                    updatePayment(payment);
                    valuePayable = valuePayable - unUsedValue;
                }
            }
        }
        return valuePayable;
    }
    
    public void calculateSaleTotalValue(){
        float saleTotalValue = 0;
        for(SaleItem item : saleItems){
            saleTotalValue += item.getTotalValue();
        }
        selected.setSaleTotalValue(saleTotalValue);
    }

    private void initializeOldSaleItems(){
        oldSaleItems = getSaleItemJpaController().findSaleItemEntities(selected);
        saleItems = new ArrayList<SaleItem>(oldSaleItems);
        initializeTypeBooleans();
        setTypeBooleans();
        findAndSetPrices();
    }
    
    private void setTypeBooleans(){
        for(SaleItem item : saleItems){
            switch(item.getType()){
                case EXTRA:
                    extraType = true;
                    break;
                case ONE:
                    oneType = true;
                    break;
                case TWO:
                    twoType = true;
                    break;
                case THREE:
                    threeType = true;
                    break;
                case FOUR:
                    fourType = true;
                    break;
                case FIVE:
                    fiveType = true;
                    break;
                case DUMMIE:
                    dummieType = true;
                    break;
            }
        }
    }
    
    private void findAndSetPrices(){
        prices = new ArrayList<Price>();
        Price findPrice = null;
        for(SaleItem item : saleItems){
            findPrice = getPriceJpaController().findPrice(item.getType().toString());
            if(findPrice != null){
                prices.add(findPrice);
            }
        }
    }
    
    public void prepareUpdate(){
        editing = true;
        initializeOldSaleItems();
    }

    public void update() {
        calculateSaleTotalValue();
        verifyValuePayable();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleSale").getString("SaleUpdated"));
        editing = false;
    }

    private void verifyValuePayable(){
        float oldSaleTotalValue = calculateOldSaleTotalValue();
        float newSaleTotalValue = selected.getSaleTotalValue();
        if(oldSaleTotalValue != newSaleTotalValue){
            //if newValue is greater than oldValue,then the difference is added to valuePayable
            if(oldSaleTotalValue < newSaleTotalValue){
                float newValuePayable = selected.getValuePayable() + (newSaleTotalValue - oldSaleTotalValue);
                newValuePayable = verifyPaymentsWithUnUsedValue(newValuePayable);
                selected.setValuePayable(newValuePayable);
            }else {
                //if newvalue is less, then the difference is subtracted to valuePayable, 
                //and if the result is less than 0, a sale (or more) is sought and is subtracted from its valuePayable
                float newValuePayable = selected.getValuePayable() - (oldSaleTotalValue - newSaleTotalValue);
                if(newValuePayable < 0){
                    selected.setValuePayable(0);
                    float surplusValuePayable = newValuePayable * (-1);
                    searchAndSetSurplusValuePayable(surplusValuePayable);
                }else{
                    selected.setValuePayable(newValuePayable);
                }
            }
            
        }
    }
    
    private void searchAndSetSurplusValuePayable(float surplusValuePayable){
        List<Sale> sales = getJpaController().findSaleEntitiesPayable(selected.getFarm());
        int size = sales.size();
        float value = surplusValuePayable;
        if(sales != null && !sales.isEmpty()){
            for(Sale sale : sales){
                if(sale.getId() != selected.getId()){
                    float newValuePayable = sale.getValuePayable() - value;
                    if(newValuePayable >= 0){
                        sale.setValuePayable(newValuePayable);
                        updateSale(sale);
                        break;
                    }else{
                        sale.setValuePayable(0);
                        updateSale(sale);
                        value = newValuePayable * (-1);
                    }
                }
                size--;
            }
            if(size == 0){
                undoPayment(value);
            }
        }else{
            undoPayment(value);
        }
    }
    
    private void updateSale(Sale sale){
        try {
            getJpaController().edit(sale);
        } catch (Exception ex) {
            Logger.getLogger(SaleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void undoPayment(float value){
        List<Payment> payments = getPaymentJpaController().findPaymentEntitiesWithUnusedAndUsedValue(signInBean.getFarm());
        if(payments != null && !payments.isEmpty()){
            for(Payment payment : payments){
                float usedValue = payment.getUsedValue();
                if(usedValue >= value){
                    payment.setUsedValue(usedValue - value);
                    updatePayment(payment);
                    break;
                }else{
                    payment.setUsedValue(0);
                    updatePayment(payment);
                    value = value - usedValue;
                }
            }
        }
    }
    
    private void updatePayment(Payment payment){
        try{
            getPaymentJpaController().edit(payment);
        }catch(Exception ex){
            Logger.getLogger(SaleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public float calculateOldSaleTotalValue(){
        float oldSaleTotalValue = 0;
        for(SaleItem item : oldSaleItems){
            oldSaleTotalValue += item.getTotalValue();
        }
        return oldSaleTotalValue;
    }
    
    public void prepareView(){
        saleItems = getSaleItemJpaController().findSaleItemEntities(selected);
        initializeTypeBooleans();
        setTypeBooleans();
    }
    
    public void destroy() {
        verifySaleValuePayable();
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleSale").getString("SaleDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    private void verifySaleValuePayable(){
        if(selected.getValuePayable() < selected.getSaleTotalValue()){
            float paidValue = selected.getSaleTotalValue() - selected.getValuePayable();
            searchAndSetSurplusValuePayable(paidValue);
        }
    }
    
    public void cancel(){
        selected = null;
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
                    updateSaleItems();
                    savePrices();
                } else if (persistAction == PersistAction.CREATE) {
                    getJpaController().create(selected);
                    createSaleItems();
                    savePrices();
                } else {
                    destroySaleItems();
                    getJpaController().destroy(selected.getId());
                }
                JsfUtil.addSuccessMessage(successMessage);
                //change mode to list
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    private void createSaleItems(){
        Sale saleCreated = getJpaController().findSale(selected);
        for(SaleItem item : saleItems){
            item.setSale(saleCreated);
            getSaleItemJpaController().create(item);
        }
    }
    
    private void updateSaleItems() throws Exception {
        for(ClassificationTypeEnum type : ClassificationTypeEnum.values()){
            if(exitsOldSaleItem(type)){
                if(exitsSaleItem(type)){
                    //Modify SaleITem, there is into both
                    SaleItem modifiedSaleItem = searchSaleItemByType(type);
                    getSaleItemJpaController().edit(modifiedSaleItem);
                }else{
                    //Delete SaleItem, there is only into oldSaleItems list
                    SaleItem removeSaleItem = searchOldSaleItemByType(type);
                    getSaleItemJpaController().destroy(removeSaleItem.getId());
                }
            }else{
                if(exitsSaleItem(type)){
                    //New SaleItem, there is only into saleItems list
                    SaleItem createSaleItem = searchSaleItemByType(type);
                    createSaleItem.setSale(selected);
                    getSaleItemJpaController().create(createSaleItem);
                }
            }

        }
    }
    
    private void destroySaleItems() throws NonexistentEntityException {
        List<SaleItem> destroyingSaleItems = getSaleItemJpaController().findSaleItemEntities(selected);
        for(SaleItem item : destroyingSaleItems){
            getSaleItemJpaController().destroy(item.getId());
        }
    }
    
    public void setNullItems(){
        items = null;
    }
    
    public List<Sale> getItemsAvailableSelectMany() {
        return getJpaController().findSaleEntities();
    }

    public List<Sale> getItemsAvailableSelectOne() {
        return getJpaController().findSaleEntities();
    }

    public List<Sale> readList(Person customer, Date start, Date end) {
        return getJpaController().findSaleEntities(customer, start, end, signInBean.getFarm());
    }

    public Sale sumRegistries(Person customer, Date start, Date end) {
        List<Sale> readList = readList(customer, start, end);
        Sale sum = new Sale();
        for (Sale s : readList) {
            sum.sumSale(s);
        }
        return sum;
    }
    
    public void savePrices(){
        for(Price price : prices){
            Price searchPrice = getPriceJpaController().findPrice(price.getItem());
            ClassificationTypeEnum type = ClassificationTypeEnum.stringTo(price.getItem());
            SaleItem item = searchSaleItemByType(type);
            price.setPriceValue(item.getUnitValue());
            if(searchPrice == null){
                getPriceJpaController().create(price);
            }else{
                try {
                    getPriceJpaController().edit(price);
                } catch (Exception ex) {
                    Logger.getLogger(SaleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public List<SaleItem> readItemsListByCustomer(Person customer, Date startDate, Date endDate, ClassificationTypeEnum type) {
        return getSaleItemJpaController().findSaleItemEntities(customer, startDate, endDate, type);
    }
    
    public SaleItem sumItemsRegistriesByCustomer(Person customer, Date startDate, Date endDate, ClassificationTypeEnum type) {
        List<SaleItem>  readList = readItemsListByCustomer(customer, startDate, endDate, type);
        SaleItem sum = new SaleItem();
        sum.setQuantity(0);
        sum.setUnitValue(0);
        for (SaleItem register : readList) {
            sum.sumSaleItem(register);
        }
        return sum;
    }
    
    @FacesConverter(forClass = Sale.class)
    public static class SaleConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SaleController controller = (SaleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "saleController");
            return controller.getJpaController().findSale(getKey(value));
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
            if (object instanceof Sale) {
                Sale o = (Sale) object;
                return String.valueOf(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Sale.class.getName());
            }
        }

    }
    
    //Chart functions
    public void createChart() {
        model = new LineChartModel();
        LineChartSeries[] series = new LineChartSeries[8];
        ClassificationTypeEnum[] types = ClassificationTypeEnum.values();
        List<SaleItem> itemsSums = new ArrayList<SaleItem>();
        for (int i = 0; i < 8; i++) {
            series[i] = new LineChartSeries(types[i].toString());
            if(types[i] == ClassificationTypeEnum.DAMAGED){
                series[i] = new LineChartSeries("Total");
            }
        }
        Sale totalSum = new Sale();
        Calendar cal = GregorianCalendar.getInstance();
        int maxPeriods = 0;
        switch (period) {
            case 0:
                maxPeriods = 7;
                cal.setTime(DateTools.getFirstDayOfWeek(date));
                break;
            case 1:
                maxPeriods = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                cal.setTime(DateTools.getDate(year, month, 1));
                break;
            case 2:
                maxPeriods = 52;
                cal.setTime(DateTools.getDate(year, 0, 1));
                break;
            case 3:
                maxPeriods = 12;
                cal.setTime(DateTools.getDate(year, 0, 1));
                break;
        }
        
        for (int i = 1; i <= maxPeriods; i++) {
            Date startDate = cal.getTime();
            switch (period) {
                case 2:
                    cal.add(Calendar.DAY_OF_MONTH, 6);
                    break;
                case 3:
                    cal.add(Calendar.MONTH, 1);
                    cal.add(Calendar.DAY_OF_MONTH, -1);
                    break;
            }
            Date endDate = cal.getTime();
            if (period == 0 || period == 1) {
                endDate = null;
            }
            String label = "";
            switch (period) {
                case 0:
                    label = DateTools.getDayOfWeek(i);
                    break;
                case 1:
                    label = i + "";
                    break;
                case 2:
                    label = i + "";
                    break;
                case 3:
                    label = DateTools.getMonth(i - 1);
                    break;
            }
            for (int t = 0; t < 8; t++) {
                ClassificationTypeEnum type = types[t];
                if(types[t] != ClassificationTypeEnum.DAMAGED){
                    SaleItem itemSum = sumItemsRegistriesByCustomer(customer, startDate, endDate, type);
                    itemsSums.add(itemSum);
                    if(option.equals("cantidad")){
                        series[t].set(label, itemSum.getQuantity());
                    }else{
                        series[t].set(label, itemSum.getTotalValue());
                    }
                }else{
                    if(option.equals("cantidad")){
                        SaleItem itemSum = sumItemsRegistriesByCustomer(customer, startDate, endDate, null);
                        series[t].set(label, itemSum.getQuantity());
                    }else{
                        totalSum = sumRegistries(customer, startDate, endDate);
                        series[t].set(label, totalSum.getSaleTotalValue());
                    }
                }
            }
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        model = new LineChartModel();
        for(int t = 0; t < 8; t ++ ){
            model.addSeries(series[t]);
        }
        model.setShowPointLabels(true);
        switch (period) {
            case 0:
                model.getAxes().put(AxisType.X, new CategoryAxis("Día"));
                model.setTitle("Ventas por Día " + DateTools.getWeek(date));
                break;
            case 1:
                model.getAxes().put(AxisType.X, new CategoryAxis("Día"));
                model.setTitle("Ventas por Día " + DateTools.getMonth(month) + " de " + year);
                break;
            case 2:
                model.getAxes().put(AxisType.X, new CategoryAxis("Semana"));
                model.setTitle("Ventas por Semana Año" + year);
                break;
            case 3:
                model.getAxes().put(AxisType.X, new CategoryAxis("Mes"));
                model.setTitle("Ventas por Mes Año" + year);
                break;
        }
        model.setLegendPosition("e");
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }
}
