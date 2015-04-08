package controlador.controllers;

import modelo.finanzas.nomina.Nomina;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import datos.finanzas.NominaDAO;
import datos.util.EntityManagerFactorySingleton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import modelo.produccion.administracion.Finca;
import modelo.produccion.administracion.Persona;
import modelo.produccion.cosecha.Recoleccion;
import modelo.produccion.labores.Labor;
import modelo.produccion.labores.Trabajo;

@ManagedBean(name = "nominaController")
@SessionScoped
public class NominaController implements Serializable {

    private Nomina selected;
    private List<Nomina> items = null;
    private NominaDAO jpaController = null;
    @ManagedProperty(value = "#{permisoController}")
    private PermisoController permisoBean;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;
    @ManagedProperty(value = "#{recoleccionCotroller}")
    private RecoleccionController recoleccionCotroller;
    @ManagedProperty(value = "#{trabajoController}")
    private TrabajoController trabajoController;
    @ManagedProperty(value = "#{laborController}")
    private LaborController laborController;

    public NominaController() {
    }

    public Nomina getSelected() {
        return selected;
    }

    public void setSelected(Nomina selected) {
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

    public RecoleccionController getRecoleccionCotroller() {
        return recoleccionCotroller;
    }

    public void setRecoleccionCotroller(RecoleccionController recoleccionCotroller) {
        this.recoleccionCotroller = recoleccionCotroller;
    }

    public TrabajoController getTrabajoController() {
        return trabajoController;
    }

    public void setTrabajoController(TrabajoController trabajoController) {
        this.trabajoController = trabajoController;
    }

    public LaborController getLaborController() {
        return laborController;
    }

    public void setLaborController(LaborController laborController) {
        this.laborController = laborController;
    }

    private NominaDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new NominaDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }
    
    public Nomina prepareCreate() {
        selected = new Nomina();
        selected.setFinca(((SignInController) JsfUtil.getSession().getAttribute("signInController")).getFinca());
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        // Generar Total
        selected.setFecha(getSaturday(selected.getFecha()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selected.getFecha()); 
        calendar.add(Calendar.DAY_OF_YEAR, -5);  // numero de días a añadir, o restar en caso de días<0
        selected.setFechaDesde(calendar.getTime());
        if(!validarFecha()){
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NominaFechaNoPermitiva"));
        }else{
            calcularTotal();
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("NominaCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        }
    }

    //Posiblemente no se use
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("NominaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("NominaDeleted"));
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

    public List<Nomina> getItems() {
        if (items == null) {
            if (signInBean.getFinca() != null) {
                items = getJpaController().findNominaEntitiesForSelectedFarm(signInBean.getFinca());
            } else {
                JsfUtil.addErrorMessage("Seleccione una Finca");
            }
        }
        return items;
    }

    public void setItems(List<Nomina> items) {
        this.items = items;
    }

    public List<Nomina> getItemsAvailableSelectMany() {
        return getJpaController().findNominaEntities();
    }

    public List<Nomina> getItemsAvailableSelectOne() {
        return getJpaController().findNominaEntities();
    }
    
    public List<Nomina> leerLista(Finca finca, Persona trabajador, Date inicio, Date fin) {
        return getJpaController().findNominaEntities(finca, trabajador, inicio, fin);
    }
    
    private static Date getSaturday(Date fecha) {
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(fecha);
        c.add(Calendar.DAY_OF_MONTH, -c.get(Calendar.DAY_OF_WEEK) + 7);
        return c.getTime();
    }
    
    private boolean validarFecha(){
        //
        Calendar calendar = GregorianCalendar.getInstance();
        if(selected.getFecha().after(calendar.getTime())){
            return false;
        }
        List<Nomina> anteriores = leerLista(selected.getFinca(), selected.getTrabajador(), selected.getFechaDesde(), selected.getFecha());
        if(anteriores != null && !anteriores.isEmpty()){
            for(Nomina nomina : anteriores){
                if(nomina.getFecha().equals(selected.getFecha())){
                    return false;
                }
            }
        }
        return true;
    }
    
    public float getValorRecolectado(){
        float valorRecolectado = 0;
        if(selected != null){
            Recoleccion totalRecolectado = recoleccionCotroller.sumarRegistros(selected.getTrabajador(), null,selected.getFechaDesde(),selected.getFecha());
            valorRecolectado = (totalRecolectado.getPesadaGramos()/500)*125;
        }
        return valorRecolectado;
    }
    
    public List<Labor> getLaboresSelected(){
        
        List<Labor> labores = null;
        if(selected != null){
            labores = new ArrayList<Labor>();
            List<Trabajo> trabajosRealizados = trabajoController.leerLista(selected.getTrabajador(),selected.getFechaDesde(),selected.getFecha());
            for(Labor labor : laborController.getItemsAvailableSelectOne()){
                float valorTrabajo = 0;
                for(Trabajo trabajo : trabajosRealizados){
                    if(trabajo.getLabor().equals(labor)){
                        valorTrabajo += labor.getValor() * trabajo.getHoras();
                    }
                }
                if (valorTrabajo != 0){
                    Labor nueva = labor;
                    nueva.setValor(valorTrabajo);
                    labores.add(nueva);
                }
            }
        }
        return labores;
    }
    
    private void calcularTotal(){
        
        float total = 0;
        
        //Fruta recolectada
        float valorRecolectado = getValorRecolectado();
        
        //Trabajos hechos
        float valorTrabajos = 0;
        List<Trabajo> trabajosRealizados = trabajoController.leerLista(selected.getTrabajador(),selected.getFechaDesde(),selected.getFecha());
        for(Labor labor : laborController.getItemsAvailableSelectOne()){
            for(Trabajo trabajo : trabajosRealizados){
                if(trabajo.getLabor().equals(labor)){
                    valorTrabajos += labor.getValor() * trabajo.getHoras();
                }
            }
        }
        
        //Total
        total += valorRecolectado + valorTrabajos;
        selected.setTotal(total);
    }

    @FacesConverter(forClass = Nomina.class)
    public static class NominaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NominaController controller = (NominaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "nominaController");
            return controller.getJpaController().findNomina(getKey(value));
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
            if (object instanceof Nomina) {
                Nomina o = (Nomina) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Nomina.class.getName());
            }
        }

    }

}
