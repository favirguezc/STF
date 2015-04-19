/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.controllers;

import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import datos.finanzas.PrecioDAO;
import datos.finanzas.VentaDAO;
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
import modelo.finanzas.Precio;
import modelo.finanzas.ventas.Venta;
import model.administration.Person;

/**
 *
 * @author JohnFredy
 */
@ManagedBean(name = "ventaController")
@SessionScoped
public class VentaController implements Serializable {

    private Venta selected;
    private List<Venta> items = null;
    private VentaDAO jpaController = null;
    private Precio precio = null;
    private boolean nuePrecio;
    private PrecioDAO precioJpaController = null;
    @ManagedProperty(value = "#{permisoController}")
    private PermisoController permisoBean;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;

    public VentaController() {
    }

    private VentaDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new VentaDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    private PrecioDAO getPrecioJpaController(){
        if(precioJpaController == null){
            precioJpaController = new PrecioDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return precioJpaController;
    }
    
    public Venta getSelected() {
        return selected;
    }

    public void setSelected(Venta selected) {
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

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public Venta prepareCreate() {
        selected = new Venta();
        if (signInBean.getFinca() != null) {
            selected.setFinca(signInBean.getFinca());
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
        //precio = getPrecioJpaController().findPrecio(selected.getInsumo().getNombre());
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

    public List<Venta> getItems() {
        if (items == null) {
            if (signInBean.getFinca() != null) {
                items = getJpaController().findVentaEntitiesForSelectedFarm(signInBean.getFinca());
            } else {
                JsfUtil.addErrorMessage("Seleccione una Finca");
            }
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

    public List<Venta> getItemsAvailableSelectMany() {
        return getJpaController().findVentaEntities();
    }

    public List<Venta> getItemsAvailableSelectOne() {
        return getJpaController().findVentaEntities();
    }

    public List<Venta> leerLista(Person cliente, Date inicio, Date fin) {
        return getJpaController().findVentaEntities(cliente, inicio, fin);
    }

    public Venta sumarRegistros(Person cliente, Date inicio, Date fin) {
        List<Venta> leerLista = leerLista(cliente, inicio, fin);
        Venta suma = new Venta(null, cliente, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        for (Venta v : leerLista) {
            suma.sumar(v);
        }
        return suma;
    }

    public void verifyPrecio(){
//        if(selected.getInsumo() != null){
//            //search precio by item
//            precio = getPrecioJpaController().findPrecio(selected.getInsumo().getNombre());
//            //if exists set
//            if(precio != null){
//                nuePrecio = false;
//                selected.setPrecio(precio.getValor());
//            }else{
//                //else create new precio
//                nuePrecio = true;
//                precio = new Precio(selected.getInsumo().getNombre(),0);
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
    
    @FacesConverter(forClass = Venta.class)
    public static class VentaConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
            if (string == null || string.length() == 0) {
                return null;
            }
            long id = Long.parseLong(string);
            VentaController controller = (VentaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ventaController");
            return controller.getJpaController().findVenta(id);
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Venta) {
                Venta o = (Venta) object;
                return String.valueOf(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: modelo.finanzas.ventas.Venta");
            }
        }

    }
}
