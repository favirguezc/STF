/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controllers;

import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.finances.PriceDAO;
import data.finances.sales.SaleDAO;
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
import model.finances.sales.Sale;
import model.administration.Person;

/**
 *
 * @author JohnFredy
 */
@ManagedBean(name = "ventaController")
@SessionScoped
public class VentaController implements Serializable {

    private Sale selected;
    private List<Sale> items = null;
    private SaleDAO jpaController = null;
    private Price precio = null;
    private boolean nuePrecio;
    private PriceDAO precioJpaController = null;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;

    public VentaController() {
    }

    private SaleDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new SaleDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    private PriceDAO getPrecioJpaController(){
        if(precioJpaController == null){
            precioJpaController = new PriceDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return precioJpaController;
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
            JsfUtil.addErrorMessage("Seleccione una farm");
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
                JsfUtil.addErrorMessage("Seleccione una Farm");
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

    public List<Sale> leerLista(Person cliente, Date inicio, Date fin) {
        return getJpaController().findSaleEntities(cliente, inicio, fin);
    }

    public Sale sumarRegistros(Person cliente, Date inicio, Date fin) {
        List<Sale> leerLista = leerLista(cliente, inicio, fin);
        Sale suma = new Sale(null, cliente, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        for (Sale v : leerLista) {
            suma.sumar(v);
        }
        return suma;
    }

    public void verifyPrecio(){
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
    
    public void savePrecio(){
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
    public static class VentaConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
            if (string == null || string.length() == 0) {
                return null;
            }
            long id = Long.parseLong(string);
            VentaController controller = (VentaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ventaController");
            return controller.getJpaController().findSale(id);
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
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: model.finanzas.ventas.Venta");
            }
        }

    }
}
