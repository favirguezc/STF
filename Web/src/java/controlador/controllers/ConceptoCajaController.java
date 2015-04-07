package controlador.controllers;

import modelo.finanzas.caja.ConceptoCaja;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import datos.finanzas.ConceptoCajaDAO;
import datos.util.EntityManagerFactorySingleton;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.faces.model.SelectItem;
import modelo.finanzas.caja.Caja;

@ManagedBean(name = "conceptoCajaController")
@SessionScoped
public class ConceptoCajaController implements Serializable {

    private ConceptoCaja selected;
    private List<ConceptoCaja> items = null;
    private ConceptoCajaDAO jpaController = null;
    private List<SelectItem> itemsEntrada = null;
    private Caja cajaFiltro = null;
    private int entrada;
    @ManagedProperty(value = "#{permisoController}")
    private PermisoController permisoBean;

    public ConceptoCajaController() {
    }

    public ConceptoCaja getSelected() {
        return selected;
    }

    public void setSelected(ConceptoCaja selected) {
        this.selected = selected;
    }

    public PermisoController getPermisoBean() {
        return permisoBean;
    }

    public void setPermisoBean(PermisoController permisoBean) {
        this.permisoBean = permisoBean;
    }

    public List<SelectItem> getItemsEntrada() {
        if(itemsEntrada == null){
            itemsEntrada = new ArrayList<SelectItem>();
            SelectItem item = new SelectItem(1, "Entrada");
            itemsEntrada.add(item);
            item = new SelectItem(2, "Salida");
            itemsEntrada.add(item);
        }
        return itemsEntrada;
    }

    public int getEntrada() {
        return entrada;
    }

    public void setEntrada(int entrada) {
        this.entrada = entrada;
    }

    public Caja getCajaFiltro() {
        return cajaFiltro;
    }

    public void setCajaFiltro(Caja cajaFiltro) {
        this.cajaFiltro = cajaFiltro;
    }
    
    private ConceptoCajaDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new ConceptoCajaDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public ConceptoCaja prepareCreate() {
        selected = new ConceptoCaja();
        return selected;
    }

    public void create() {
        if(entrada == 1){
            selected.setEntrada(true);
        }else if(entrada == 2){
            selected.setEntrada(false);
        }
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ConceptoCajaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void prepareUpdate(){
        entrada = selected.isEntrada()?1:2;
    }
    
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ConceptoCajaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ConceptoCajaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
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


    public List<ConceptoCaja> getItems() {
        if (items == null) {
            items = getJpaController().findConceptoCajaEntities();
            obtenerSaldo();
        }
        return items;
    }

    private void obtenerSaldo(){
       float saldo = 0;
       for(ConceptoCaja concepto : items){
           concepto.setSaldo(saldo);
           saldo = concepto.getSaldo();
       }
    }
    
    public List<ConceptoCaja> getItemsAvailableSelectMany() {
        return getJpaController().findConceptoCajaEntities();
    }

    public List<ConceptoCaja> getItemsAvailableSelectOne() {
        return getJpaController().findConceptoCajaEntities();
    }

    @FacesConverter(forClass = ConceptoCaja.class)
    public static class ConceptoCajaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConceptoCajaController controller = (ConceptoCajaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "conceptoCajaController");
            return controller.getJpaController().findConceptoCaja(getKey(value));
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
            if (object instanceof ConceptoCaja) {
                ConceptoCaja o = (ConceptoCaja) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ConceptoCaja.class.getName());
            }
        }

    }

}
