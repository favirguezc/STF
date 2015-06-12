package controller.controllers;

import model.finances.expenses.Payroll;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import data.finances.expenses.PayrollDAO;
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
import model.finances.cash.Cash;
import model.finances.cash.CashConcept;
import model.util.DateTools;
import model.work.Job;
import model.work.Work;

@ManagedBean(name = "payrollController")
@SessionScoped
public class PayrollController implements Serializable {

    private Payroll selected;
    private List<Payroll> items = null;
    private PayrollDAO jpaController = null;
    private Cash cash;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;
    @ManagedProperty(value = "#{signInController}")
    private SignInController signInBean;
    @ManagedProperty(value = "#{cropCotroller}")
    private CropController cropController;
    @ManagedProperty(value = "#{workController}")
    private WorkController workController;
    @ManagedProperty(value = "#{jobController}")
    private JobController jobController;
    @ManagedProperty(value = "#{cashConceptController}")
    private CashConceptController cashConceptController;
    
    public PayrollController() {
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

    public CropController getCropController() {
        if (cropController == null){
            FacesContext context = FacesContext.getCurrentInstance();
            cropController = (CropController) context.getApplication().
                        getELResolver().getValue(context.getELContext(), null, "cropController");
        }
        return cropController;
    }

    public void setCropController(CropController cropController) {
        this.cropController = cropController;
    }

    public WorkController getWorkController() {
        if (workController == null){
            FacesContext context = FacesContext.getCurrentInstance();
            workController = (WorkController) context.getApplication().
                        getELResolver().getValue(context.getELContext(), null, "workController");
        }
        return workController;
    }

    public void setWorkController(WorkController workController) {
        this.workController = workController;
    }

    public JobController getJobController() {
        if (jobController == null){
            FacesContext context = FacesContext.getCurrentInstance();
            jobController = (JobController) context.getApplication().
                        getELResolver().getValue(context.getELContext(), null, "jobController");
        }
        return jobController;
    }

    public void setJobController(JobController jobController) {
        this.jobController = jobController;
    }

    private PayrollDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new PayrollDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }
    
    public CashConceptController getCashConceptController() {
        return cashConceptController;
    }

    public void setCashConceptController(CashConceptController cashConceptController) {
        this.cashConceptController = cashConceptController;
    }
    
    public Cash getCash() {
        return cash;
    }

    public void setCash(Cash cash) {
        this.cash = cash;
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
        // Generate Total
        selected.setDateUntil(getSaturday(selected.getDateUntil()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selected.getDateUntil());
        calendar.add(Calendar.DAY_OF_YEAR, -5);  // number of days to add, o subtract when the days<0
        selected.setDateFrom(calendar.getTime());
        if (!validateDate()) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/BundlePayroll").getString("PayrollDateNoEnable"));
        } else {
            calculateTotal();
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundlePayroll").getString("PayrollCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        }
    }

    //Posiblemente no se use
    public void pay() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundlePayroll").getString("PayrollPaid"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundlePayroll").getString("PayrollDeleted"));
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
                    createCashConcept();
                    getJpaController().edit(selected);
                    cashConceptController.setNullItems();
                } else if (persistAction == PersistAction.CREATE) {
                    getJpaController().create(selected);
                } else {
                    getJpaController().destroy(selected.getId());
                    cashConceptController.setNullItems();
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    private void createCashConcept(){
        CashConcept concept = new CashConcept();
        concept.setConceptDate(selected.getDateUntil());
        concept.setDescription("Nomina " + selected.getWorker().getName() + " " + selected.getWorker().getLastName() + " " + DateTools.getWeek_Short(selected.getDateUntil()));
        concept.setIncome(false);
        concept.setConceptValue(selected.getTotal());
        concept.setCash(cash);
        selected.setAsociatedConcept(concept);
    }
    
    public List<Payroll> getItems() {
        if (items == null || items.isEmpty()) {
            if (signInBean.getFarm() != null) {
                items = getJpaController().findPayrollEntitiesForSelectedFarm(signInBean.getFarm());
            } else {
                JsfUtil.addErrorMessage("Seleccione una Finca");
            }
        }
        return items;
    }

    public void setItems(List<Payroll> items) {
        this.items = items;
    }

    public List<Payroll> getItemsAvailableSelectMany() {
        return getJpaController().findPayrollEntities();
    }

    public List<Payroll> getItemsAvailableSelectOne() {
        return getJpaController().findPayrollEntities();
    }

    public List<Payroll> readList(Farm farm, Person worker, Date start, Date end) {
        return getJpaController().findPayrollEntities(farm, worker, start, end);
    }

    private static Date getSaturday(Date day) {
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(day);
        c.add(Calendar.DAY_OF_MONTH, -c.get(Calendar.DAY_OF_WEEK) + 7);
        return c.getTime();
    }

    private boolean validateDate() {
        Calendar calendar = GregorianCalendar.getInstance();
        if (selected.getDateUntil().after(calendar.getTime())) {
            return false;
        }
        List<Payroll> previousItems = readList(selected.getFarm(), selected.getWorker(), selected.getDateFrom(), selected.getDateUntil());
        if (previousItems != null && !previousItems.isEmpty()) {
            for (Payroll payroll : previousItems) {
                if (payroll.getDateUntil().equals(selected.getDateUntil())) {
                    return false;
                }
            }
        }
        return true;
    }

    public float getCroppedValue() {
        float croppedValue = 0;
        if (selected != null) {
            Crop totalCropped = getCropController().sumRegistersByModule(selected.getWorker(), null, selected.getDateFrom(), selected.getDateUntil());
            croppedValue = (totalCropped.getWeight() / 500) * 125;
        }
        return croppedValue;
    }

    public List<Job> getJobsSelected() {

        List<Job> jobs = null;
        if (selected != null) {
            jobs = new ArrayList<Job>();
            List<Work> doneWorks = getWorkController().leerLista(selected.getWorker(), selected.getDateFrom(), selected.getDateUntil());
            for (Job job : getJobController().getItemsAvailableSelectOne()) {
                float workValue = 0;
                for (Work work : doneWorks) {
                    if (work.getJob().equals(job)) {
                        workValue += job.getHourlyRate() * work.getHoursSpent();
                    }
                }
                if (workValue != 0) {
                    Job newJob = job;
                    newJob.setHourlyRate(workValue);
                    jobs.add(newJob);
                }
            }
        }
        return jobs;
    }

    private void calculateTotal() {

        float total = 0;
        //Fruit cropped
        float croppedValue = getCroppedValue();
        //Works done
        float worksValue = 0;
        List<Work> doneWorks = getWorkController().leerLista(selected.getWorker(), selected.getDateFrom(), selected.getDateUntil());
        for (Job job : getJobController().getItemsAvailableSelectOne()) {
            for (Work work : doneWorks) {
                if (work.getJob().equals(job)) {
                    worksValue += job.getHourlyRate() * work.getHoursSpent();
                }
            }
        }
        //Total
        total += croppedValue + worksValue;
        selected.setTotal(total);
    }

    @FacesConverter(forClass = Payroll.class)
    public static class PayrollControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PayrollController controller = (PayrollController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "payrollController");
            return controller.getJpaController().findPayroll(getKey(value));
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
