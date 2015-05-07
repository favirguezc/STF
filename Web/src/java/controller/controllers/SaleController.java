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
import data.finances.incomes.SaleDAO;
import data.finances.incomes.SaleItemDAO;
import data.util.EntityManagerFactorySingleton;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import model.finances.Price;
import model.finances.incomes.Sale;
import model.administration.Person;
import model.crop.ClassificationTypeEnum;
import model.finances.incomes.SaleItem;

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
    private Price price = null;
    private boolean newPrice;
    private PriceDAO priceJpaController = null;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;
    private SaleItemDAO saleItemJpaController = null;
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

    public SaleController() {
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
                        saleItems.add(saleItem);
                    }
                }
            }else{
                if(exitsSaleItem(type)){
                    //when read
                    saleItem = searchSaleItemByType(type);
                }else{
                    //when create
                    saleItems.add(saleItem);
                }
            }
        }else{
            saleItem = null;
            removeSaleItem(type);
            
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
    
    private void removeSaleItem(ClassificationTypeEnum type){
        Iterator<SaleItem> iterator = saleItems.iterator();
        while(iterator.hasNext()){
            SaleItem item = iterator.next();
            if(item.getType() == type){
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
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleSale").getString("SaleCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
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
    
    public void prepareUpdate(){
        //precio = getPrecioJpaController().findPrice(selected.getChemical().getNombre());
        editing = true;
        initializeOldSaleItems();
    }

    public void update() {
        //savePrecio();
        calculateSaleTotalValue();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleSale").getString("SaleUpdated"));
        editing = false;
    }

    public void prepareView(){
        saleItems = getSaleItemJpaController().findSaleItemEntities(selected);
        initializeTypeBooleans();
        setTypeBooleans();
    }
    
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleSale").getString("SaleDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
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
                } else if (persistAction == PersistAction.CREATE) {
                    getJpaController().create(selected);
                    createSaleItems();
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
    
    public List<Sale> getItemsAvailableSelectMany() {
        return getJpaController().findSaleEntities();
    }

    public List<Sale> getItemsAvailableSelectOne() {
        return getJpaController().findSaleEntities();
    }

    public List<Sale> readList(Person customer, Date start, Date end) {
        return getJpaController().findSaleEntities(customer, start, end);
    }

    public Sale sumRegistries(Person customer, Date start, Date end) {
        List<Sale> readList = readList(customer, start, end);
        Sale sum = new Sale();
        for (Sale s : readList) {
            sum.sumar(s);
        }
        return sum;
    }

    public void verifyPrice(){
//        if(selected.getChemical() != null){
//            //search precio by item
//            precio = getPrecioJpaController().findPrice(selected.getChemical().getNombre());
//            //if exists set
//            if(precio != null){
//                nuePrecio = false;
//                selected.setPrecio(precio.getValor());
//            }else{
//                //else create new precio
//                nuePrecio = true;
//                precio = new Price(selected.getChemical().getNombre(),0);
//                selected.setPrecio(0);
//            }
//        }
    }
    
    public void savePrice(){
//        if(nuePrecio){
//            precio.setValor(selected.getPrecio());
//            getPrecioJpaController().create(precio);
//        }else{
//            try {
//                precio.setValor(selected.getPrecio());
//                getPrecioJpaController().edit(precio);
//            } catch (Exception ex) {
//                Logger.getLogger(CompraController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
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
}
