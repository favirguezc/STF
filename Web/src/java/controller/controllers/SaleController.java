/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controllers;

import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.finances.PriceDAO;
import data.finances.incomes.SaleDAO;
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
import model.finances.Price;
import model.finances.incomes.Sale;
import model.administration.Person;

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

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public Sale prepareCreate() {
        selected = new Sale();
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
        //savePrecio();
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("VentaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void prepareUpdate(){
        //precio = getPrecioJpaController().findPrice(selected.getChemical().getNombre());
    }

    public void update() {
        //savePrecio();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VentaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("VentaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
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
        Sale sum = new Sale(null, customer, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
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
