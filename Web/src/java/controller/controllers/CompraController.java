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
import data.finances.purchase.ChemicalPurchaseDAO;
import data.finances.PriceDAO;
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
import model.finances.purchase.ChemicalPurchase;
import model.administration.Farm;

/**
 *
 * @author John Fredy
 */
@ManagedBean(name = "compraController")
@SessionScoped
public class CompraController implements Serializable{

    private ChemicalPurchase selected = null;
    private List<ChemicalPurchase> items = null;
    private Price precio = null;
    private boolean nuePrecio;
    private ChemicalPurchaseDAO jpaController = null;
    private PriceDAO precioJpaController = null;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;

    public CompraController() {
    }
    
    public ChemicalPurchaseDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new ChemicalPurchaseDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }
    
    private PriceDAO getPrecioJpaController(){
        if(precioJpaController == null){
            precioJpaController = new PriceDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return precioJpaController;
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
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    
    public List<ChemicalPurchase> getCompraItemsAvailableSelectMany() {
        return getJpaController().findChemicalPurchaseEntities();
    }

    public List<ChemicalPurchase> getCompraItemsAvailableSelectOne() {
        return getJpaController().findChemicalPurchaseEntities();
    }

    public List<ChemicalPurchase> getItems() {
        if (items == null) {
            if (signInBean.getFarm() != null) {
                items = getJpaController().findChemicalPurchaseEntitiesForSelectedFarm(signInBean.getFarm());
            } else {
                JsfUtil.addErrorMessage("Seleccione una Farm");
            }
        }
        return items;
    }
    
    public ChemicalPurchase prepareCreate() {
        selected = new ChemicalPurchase();
        if (signInBean.getFarm() != null) {
            selected.setFarm(signInBean.getFarm());
            initializeEmbeddableKey();
        } else {
            JsfUtil.addErrorMessage("Seleccione una farm");
            selected = null;
        }
        return selected;
    }
    
    public void create() {
        savePrecio();    
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CompraCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void prepareUpdate(){
        precio = getPrecioJpaController().findPrice(selected.getChemical().getName());
    }
    
    public void update() {
        savePrecio();
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CompraUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CompraDeleted"));
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
    
    public List<ChemicalPurchase> leerLista(Farm farm, Date inicio, Date fin) {
        return getJpaController().findChemicalPurchaseEntities(farm, inicio, fin);
    }
    
    public ChemicalPurchase sumarRegistros(Farm farm, Date inicio, Date fin) {
        List<ChemicalPurchase> leerLista = leerLista(farm, inicio, fin);
        ChemicalPurchase suma = new ChemicalPurchase(null, farm, null,0,0);
        for (ChemicalPurchase v : leerLista) {
            suma.add(v);
        }
        return suma;
    }
    
    public void verifyPrecio(){
        if(selected.getChemical() != null){
            //search price by item
            precio = getPrecioJpaController().findPrice(selected.getChemical().getName());
            //if exists set
            if(precio != null){
                nuePrecio = false;
                selected.setPrice(precio.getPriceValue());
            }else{
                //else create new price
                nuePrecio = true;
                precio = new Price(selected.getChemical().getName(),0);
                selected.setPrice(0);
            }
        }
    }
    
    public void savePrecio(){
        if(nuePrecio){
            precio.setPriceValue(selected.getPrice());
            getPrecioJpaController().create(precio);
        }else{
            try {
                precio.setPriceValue(selected.getPrice());
                getPrecioJpaController().edit(precio);
            } catch (Exception ex) {
                Logger.getLogger(CompraController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FacesConverter(forClass = ChemicalPurchase.class)
    public static class CompraConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
            if (string == null || string.length() == 0) {
                return null;
            }
            long id = Long.parseLong(string);
            CompraController controller = (CompraController) facesContext.getApplication().getVariableResolver().resolveVariable(facesContext, "compra");
            return controller.getJpaController().findChemicalPurchase(id);
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ChemicalPurchase) {
                ChemicalPurchase o = (ChemicalPurchase) object;
                return String.valueOf(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: model.finanzas.compra.Compra");
            }
        }

    }
}
