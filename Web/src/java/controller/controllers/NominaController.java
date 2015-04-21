package controller.controllers;

import model.finances.payroll.Payroll;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import datos.finanzas.NominaDAO;
import data.util.EntityManagerFactorySingleton;

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
import model.administration.Farm;
import model.administration.Person;
import model.crop.Crop;
import model.work.Job;
import model.work.Work;

@ManagedBean(name = "nominaController")
@SessionScoped
public class NominaController implements Serializable {

    private Payroll selected;
    private List<Payroll> items = null;
    private NominaDAO jpaController = null;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;
    @ManagedProperty(value = "#{cropCotroller}")
    private CropController cropCotroller;
    @ManagedProperty(value = "#{workController}")
    private WorkController workController;
    @ManagedProperty(value = "#{jobController}")
    private JobController jobController;

    public NominaController() {
    }

    public Payroll getSelected() {
        return selected;
    }

    public void setSelected(Payroll selected) {
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

    public CropController getCropCotroller() {
        return cropCotroller;
    }

    public void setCropCotroller(CropController cropCotroller) {
        this.cropCotroller = cropCotroller;
    }

    public WorkController getWorkController() {
        return workController;
    }

    public void setWorkController(WorkController workController) {
        this.workController = workController;
    }

    public JobController getJobController() {
        return jobController;
    }

    public void setJobController(JobController jobController) {
        this.jobController = jobController;
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

    public Payroll prepareCreate() {
        selected = new Payroll();
        selected.setFarm(((SignInController) JsfUtil.getSession().getAttribute("signInController")).getFarm());
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        // Generar Total
        selected.setDateUntil(getSaturday(selected.getDateUntil()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selected.getDateUntil());
        calendar.add(Calendar.DAY_OF_YEAR, -5);  // numero de días a añadir, o restar en caso de días<0
        selected.setDateFrom(calendar.getTime());
        if (!validarFecha()) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/Bundle").getString("NominaFechaNoPermitiva"));
        } else {
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

    public List<Payroll> getItems() {
        if (items == null) {
            if (signInBean.getFarm() != null) {
                items = getJpaController().findNominaEntitiesForSelectedFarm(signInBean.getFarm());
            } else {
                JsfUtil.addErrorMessage("Seleccione una Farm");
            }
        }
        return items;
    }

    public void setItems(List<Payroll> items) {
        this.items = items;
    }

    public List<Payroll> getItemsAvailableSelectMany() {
        return getJpaController().findNominaEntities();
    }

    public List<Payroll> getItemsAvailableSelectOne() {
        return getJpaController().findNominaEntities();
    }

    public List<Payroll> leerLista(Farm farm, Person trabajador, Date inicio, Date fin) {
        return getJpaController().findNominaEntities(farm, trabajador, inicio, fin);
    }

    private static Date getSaturday(Date fecha) {
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(fecha);
        c.add(Calendar.DAY_OF_MONTH, -c.get(Calendar.DAY_OF_WEEK) + 7);
        return c.getTime();
    }

    private boolean validarFecha() {
        //
        Calendar calendar = GregorianCalendar.getInstance();
        if (selected.getDateUntil().after(calendar.getTime())) {
            return false;
        }
        List<Payroll> anteriores = leerLista(selected.getFarm(), selected.getWorker(), selected.getDateFrom(), selected.getDateUntil());
        if (anteriores != null && !anteriores.isEmpty()) {
            for (Payroll nomina : anteriores) {
                if (nomina.getDateUntil().equals(selected.getDateUntil())) {
                    return false;
                }
            }
        }
        return true;
    }

    public float getValorRecolectado() {
        float valorRecolectado = 0;
        if (selected != null) {
            Crop totalRecolectado = cropCotroller.sumarRegistros(selected.getWorker(), null, selected.getDateFrom(), selected.getDateUntil());
            valorRecolectado = (totalRecolectado.getWeight() / 500) * 125;
        }
        return valorRecolectado;
    }

    public List<Job> getJobesSelected() {

        List<Job> jobes = null;
        if (selected != null) {
            jobes = new ArrayList<Job>();
            List<Work> worksRealizados = workController.leerLista(selected.getWorker(), selected.getDateFrom(), selected.getDateUntil());
            for (Job job : jobController.getItemsAvailableSelectOne()) {
                float valorWork = 0;
                for (Work work : worksRealizados) {
                    if (work.getJob().equals(job)) {
                        valorWork += job.getHourlyRate() * work.getHoursSpent();
                    }
                }
                if (valorWork != 0) {
                    Job nueva = job;
                    nueva.setHourlyRate(valorWork);
                    jobes.add(nueva);
                }
            }
        }
        return jobes;
    }

    private void calcularTotal() {

        float total = 0;

        //Fruta recolectada
        float valorRecolectado = getValorRecolectado();

        //Works hechos
        float valorWorks = 0;
        List<Work> worksRealizados = workController.leerLista(selected.getWorker(), selected.getDateFrom(), selected.getDateUntil());
        for (Job job : jobController.getItemsAvailableSelectOne()) {
            for (Work work : worksRealizados) {
                if (work.getJob().equals(job)) {
                    valorWorks += job.getHourlyRate() * work.getHoursSpent();
                }
            }
        }

        //Total
        total += valorRecolectado + valorWorks;
        selected.setTotal(total);
    }

    @FacesConverter(forClass = Payroll.class)
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
            if (object instanceof Payroll) {
                Payroll o = (Payroll) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Payroll.class.getName());
            }
        }

    }

}
