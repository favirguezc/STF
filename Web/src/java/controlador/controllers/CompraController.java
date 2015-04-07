/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.controllers;

import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import datos.finanzas.CompraDAO;
import datos.finanzas.PrecioDAO;
import datos.util.EntityManagerFactorySingleton;
import java.io.Serializable;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.convert.FacesConverter;
import modelo.finanzas.Precio;
import modelo.finanzas.compra.Compra;
import modelo.produccion.administracion.Lote;

/**
 *
 * @author John Fredy
 */
@ManagedBean(name = "compraController")
@SessionScoped
public class CompraController implements Serializable{

    private Compra selected = null;
    private List<Compra> items = null;
    private Precio precio = null;
    private boolean nuePrecio;
    private CompraDAO jpaController = null;
    private PrecioDAO precioJpaController = null;
    @ManagedProperty(value = "#{permisoController}")
    private PermisoController permisoBean;

    public CompraController() {
    }
    
    public CompraDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new CompraDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }
    
    private PrecioDAO getPrecioJpaController(){
        if(precioJpaController == null){
            precioJpaController = new PrecioDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return precioJpaController;
    }

    public Compra getSelected() {
        return selected;
    }

    public void setSelected(Compra selected) {
        this.selected = selected;
    }

    public PermisoController getPermisoBean() {
        return permisoBean;
    }

    public void setPermisoBean(PermisoController permisoBean) {
        this.permisoBean = permisoBean;
    }
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    
    public List<Compra> getCompraItemsAvailableSelectMany() {
        return getJpaController().findCompraEntities();
    }

    public List<Compra> getCompraItemsAvailableSelectOne() {
        return getJpaController().findCompraEntities();
    }

    public List<Compra> getItems() {
        if (items == null) {
            items = getJpaController().findCompraEntities();
        }
        return items;
    }
    
    public Compra prepareCreate() {
        selected = new Compra();
        initializeEmbeddableKey();
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
        precio = getPrecioJpaController().findPrecio(selected.getInsumo().getNombre());
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
    
    public List<Compra> leerLista(Lote lote, Date inicio, Date fin) {
        return getJpaController().findCompraEntities(lote, inicio, fin);
    }
    
    public Compra sumarRegistros(Lote lote, Date inicio, Date fin) {
        List<Compra> leerLista = leerLista(lote, inicio, fin);
        Compra suma = new Compra(null, lote, null,0,0);
        for (Compra v : leerLista) {
            suma.sumar(v);
        }
        return suma;
    }
    
    public void verifyPrecio(){
        if(selected.getInsumo() != null){
            //search precio by item
            precio = getPrecioJpaController().findPrecio(selected.getInsumo().getNombre());
            //if exists set
            if(precio != null){
                nuePrecio = false;
                selected.setPrecio(precio.getValor());
            }else{
                //else create new precion
                nuePrecio = true;
                precio = new Precio(selected.getInsumo().getNombre(),0);
                selected.setPrecio(0);
            }
        }else{
            //insert code here
        }
    }
    
    public void savePrecio(){
        if(nuePrecio){
            precio.setValor(selected.getPrecio());
            getPrecioJpaController().create(precio);
        }else{
            try {
                precio.setValor(selected.getPrecio());
                getPrecioJpaController().edit(precio);
            } catch (Exception ex) {
                Logger.getLogger(CompraController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FacesConverter(forClass = Compra.class)
    public static class CompraConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
            if (string == null || string.length() == 0) {
                return null;
            }
            long id = Long.parseLong(string);
            CompraController controller = (CompraController) facesContext.getApplication().getVariableResolver().resolveVariable(facesContext, "compra");
            return controller.getJpaController().findCompra(id);
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Compra) {
                Compra o = (Compra) object;
                return String.valueOf(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: modelo.finanzas.compra.Compra");
            }
        }

    }
}
