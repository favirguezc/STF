/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import datos.finanzas.VentaDAO;
import datos.util.EntityManagerFactorySingleton;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.finanzas.ventas.Venta;
import modelo.produccion.administracion.Persona;

/**
 *
 * @author JohnFredy
 */
@ManagedBean(name = "ventaController")
@SessionScoped
public class VentaController {

    private Venta selected;
    private List<Venta> items = null;
    private VentaDAO jpaController = null;
    
    public VentaController() {
    }

    private VentaDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new VentaDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Venta getSelected() {
        return selected;
    }

    public void setSelected(Venta selected) {
        this.selected = selected;
    }
    
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public Venta prepareCreate() {
        selected = new Venta();
        initializeEmbeddableKey();
        return selected;
    }
     
    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("VentaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
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
            items = getJpaController().findVentaEntities();
        }
        return items;
    }
    
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
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

    public List<Venta> leerLista(Persona cliente, Date inicio, Date fin) {
        return getJpaController().findVentaEntities(cliente, inicio, fin);
    }
    
    public Venta sumarRegistros(Persona cliente, Date inicio, Date fin) {
        List<Venta> leerLista = leerLista(cliente, inicio, fin);
        Venta suma = new Venta(null, cliente, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        for (Venta v : leerLista) {
            suma.sumar(v);
        }
        return suma;
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
