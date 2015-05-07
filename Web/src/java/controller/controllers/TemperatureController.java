package controller.controllers;

import controller.util.DateParser;
import model.weather.Temperature;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.Storage;
import data.weather.TemperatureDAO;
import data.util.EntityManagerFactorySingleton;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.administration.ModuleClass;
import model.util.DateFormatter;
import model.util.DateTools;
import model.weather.Thermometer;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean(name = "temperatureController")
@SessionScoped
public class TemperatureController implements Serializable {

    private TemperatureDAO jpaController = null;
    private List<Temperature> items = null;
    private Temperature selected;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;
    //File variables
    private String message;
    //Chart variables
    private LineChartModel model1;
    private LineChartModel model2;
    private LineChartModel model3;
    private LineChartModel model4;
    private int year1;
    private int month;
    private int yearMonth;
    private Date dateWeek;
    private Date dateDay;

    @PostConstruct
    public void init() {
        year1 = yearMonth = DateTools.getYear();
        month = DateTools.getMonth();
        dateWeek = DateTools.getDate();
        dateDay = DateTools.getDate();
        createModel1();
        createModel2();
        createModel3();
        createModel4();
    }

    public Temperature getSelected() {
        return selected;
    }

    public void setSelected(Temperature selected) {
        this.selected = selected;
    }

    public PermissionController getPermissionBean() {
        return permissionBean;
    }

    public void setPermissionBean(PermissionController permissionBean) {
        this.permissionBean = permissionBean;
    }
    
    public LineChartModel getModel1() {
        return model1;
    }

    public int getYear1() {
        return year1;
    }

    public void setYear1(int year1) {
        this.year1 = year1;
    }

    public LineChartModel getModel2() {
        return model2;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(int yearMonth) {
        this.yearMonth = yearMonth;
    }

    public Date getDateWeek() {
        return dateWeek;
    }

    public void setDateWeek(Date dateWeek) {
        this.dateWeek = dateWeek;
    }

    public LineChartModel getModel3() {
        return model3;
    }

    public Date getDateDay() {
        return dateDay;
    }

    public void setDateDay(Date dateDay) {
        this.dateDay = dateDay;
    }

    public LineChartModel getModel4() {
        return model4;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TemperatureDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new TemperatureDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Temperature prepareCreate() {
        selected = new Temperature();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleTemperature").getString("TemperatureCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleTemperature").getString("TemperatureUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleTemperature").getString("TemperatureDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Temperature> getItems() {
        if (items == null) {
            items = getJpaController().findTemperatureEntities();
        }
        return items;
    }

    public List<Temperature> getItems(Date fecha1, Date fecha2) {
        return getJpaController().findTemperatureEntities(fecha1, fecha2, 0);
    }

    public List<Temperature> getItems(Date fecha1, Date fecha2, int tipo) {
        return getJpaController().findTemperatureEntities(fecha1, fecha2, tipo);
    }

    public List<Temperature> getItems(Date fecha1, int hora) {
        return getJpaController().findTemperatureEntities(fecha1, hora);
    }

    public Temperature calcularMean(Date fecha1, Date fecha2) {
        return calcularMean(fecha1, fecha2, 0);
    }

    public Temperature calcularMean(Date fecha1, Date fecha2, int tipo) {
        Temperature mean = new Temperature(null, null, 0, 0, 0, null);
        List<Temperature> buscarLista = getItems(fecha1, fecha2, tipo);
        for (Temperature t : buscarLista) {
            mean.sumar(t);
        }
        if (buscarLista.size() > 1) {
            mean.dividir(buscarLista.size());
        }
        return mean;
    }

    public Temperature calcularMean(Date fechaDia, int hora) {
        Temperature mean = new Temperature(null, null, 0, 0, 0, null);
        List<Temperature> buscarLista = getItems(fechaDia, hora);
        for (Temperature t : buscarLista) {
            mean.sumar(t);
        }
        if (buscarLista.size() > 1) {
            mean.dividir(buscarLista.size());
        }
        return mean;
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

    public List<Temperature> getItemsAvailableSelectMany() {
        return getJpaController().findTemperatureEntities();
    }

    public List<Temperature> getItemsAvailableSelectOne() {
        return getJpaController().findTemperatureEntities();
    }

    public void save(Temperature temperature) {
        selected = temperature;
        persist(PersistAction.CREATE, null);
    }

    public Temperature nuevo(Date fecha, Date hora, float temperature, float soilmoisture, float puntoDeRocio, ModuleClass moduleclass) {
        return new Temperature(fecha, hora, temperature, soilmoisture, puntoDeRocio, moduleclass);
    }

    @FacesConverter(forClass = Temperature.class)
    public static class TemperatureControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TemperatureController controller = (TemperatureController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "temperatureController");
            return controller.getJpaController().findTemperature(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Temperature) {
                Temperature o = (Temperature) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Temperature.class.getName()});
                return null;
            }
        }
    }
    
    //File functions
    
    public void upload(FileUploadEvent event) {
        String filename = "temperature" + new Date().getTime() + ".txt";
        Storage.save(filename, event.getFile());
        parse(filename);
        Storage.delete(filename);
        JsfUtil.addSuccessMessage(message);
    }

    private void parse(String filename) {
        FileReader fr;
        BufferedReader br;
        ModuleClass moduleclass;
        int skip = 15, created = 0;

        try {
            fr = new FileReader(new File(filename));
            br = new BufferedReader(fr);
            String line;
            br.readLine();
            line = br.readLine();
            long nds = Long.parseLong(line.split(",")[5]);
            Thermometer thermometer = new ThermometerController().find(nds);
            if (thermometer != null) {
                moduleclass = thermometer.getModuleObject();
                TemperatureController controller = new TemperatureController();
                while (line != null) {
                    Date date = DateParser.parseDate(line.split(",")[1].split(" ")[0]);
                    Date time = DateParser.parseTime(line.split(",")[1].split(" ")[1]);
                    float temperature = Float.parseFloat(line.split(",")[2]);
                    float soilMoisture = Float.parseFloat(line.split(",")[3]);
                    float dewPoint = Float.parseFloat(line.split(",")[4]);
                    controller.save(controller.nuevo(date, time, temperature, soilMoisture, dewPoint, moduleclass));
                    created++;
                    for (int i = 1; i < skip; i++) {
                        if ((line = br.readLine()) == null) {
                            break;
                        }
                    }
                    if (line != null) {
                        line = br.readLine();
                    }
                }
                br.close();
                fr.close();
                message = "Se crearon " + created + " nuevos registros.";
            } else {
                message = "¡Error! Termómetro no registrado.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "Error inesperado.";
        }
    }
    
    //Chart functions
    
    public void createModel1() {
        model1 = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();
        LineChartSeries series4 = new LineChartSeries();
        LineChartSeries series5 = new LineChartSeries();
        LineChartSeries series6 = new LineChartSeries();
        series1.setLabel("Temperatura - Día");
        series2.setLabel("Humedad del Suelo - Día");
        series3.setLabel("Punto De Rocío - Día");
        series4.setLabel("Temperatura - Noche");
        series5.setLabel("Humedad del Suelo - Noche");
        series6.setLabel("Punto De Rocío - Noche");
        TemperatureController controller = new TemperatureController();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(year1, 0, 1));
        for (int i = 0; i < 12; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            Date fecha2 = cal.getTime();
            Temperature meanTemp;
            meanTemp = controller.calcularMean(fecha1, fecha2, 2);
            series1.set(DateTools.getMonth(i), meanTemp.getTemperature());
            series2.set(DateTools.getMonth(i), meanTemp.getHumidity());
            series3.set(DateTools.getMonth(i), meanTemp.getDewPoint());

            meanTemp = controller.calcularMean(fecha1, fecha2, 1);
            series4.set(DateTools.getMonth(i), meanTemp.getTemperature());
            series5.set(DateTools.getMonth(i), meanTemp.getHumidity());
            series6.set(DateTools.getMonth(i), meanTemp.getDewPoint());

            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        model1.addSeries(series1);
        model1.addSeries(series2);
        model1.addSeries(series3);
        model1.addSeries(series4);
        model1.addSeries(series5);
        model1.addSeries(series6);
        model1.setShowPointLabels(true);
        model1.getAxes().put(AxisType.X, new CategoryAxis("Mes"));
        model1.setTitle("Media de Temperatura por Mes - Año " + year1);
        model1.setLegendPosition("e");
    }

    public void createModel2() {
        model2 = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();
        LineChartSeries series4 = new LineChartSeries();
        LineChartSeries series5 = new LineChartSeries();
        LineChartSeries series6 = new LineChartSeries();
        series1.setLabel("Temperatura - Día");
        series2.setLabel("Humedad del Suelo - Día");
        series3.setLabel("Punto De Rocío - Día");
        series4.setLabel("Temperatura - Noche");
        series5.setLabel("Humedad del Suelo - Noche");
        series6.setLabel("Punto De Rocío - Noche");

        TemperatureController controller = new TemperatureController();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(yearMonth, month, 1));

        for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            Temperature meanTemp;
            meanTemp = controller.calcularMean(fecha1, fecha1, 2);

            series1.set(i + 1, meanTemp.getTemperature());
            series2.set(i + 1, meanTemp.getHumidity());
            series3.set(i + 1, meanTemp.getDewPoint());

            meanTemp = controller.calcularMean(fecha1, fecha1, 1);

            series4.set(i + 1, meanTemp.getTemperature());
            series5.set(i + 1, meanTemp.getHumidity());
            series6.set(i + 1, meanTemp.getDewPoint());
        }

        model2.addSeries(series1);
        model2.addSeries(series2);
        model2.addSeries(series3);
        model2.addSeries(series4);
        model2.addSeries(series5);
        model2.addSeries(series6);
        model2.setShowPointLabels(true);
        model2.getAxes().put(AxisType.X, new CategoryAxis("Día"));
        model2.setTitle("Promedio de Temperatura por Día " + DateTools.getMonth(month) + " de " + yearMonth);
        model2.setLegendPosition("e");
    }

    public void createModel3() {
        model3 = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();
        LineChartSeries series4 = new LineChartSeries();
        LineChartSeries series5 = new LineChartSeries();
        LineChartSeries series6 = new LineChartSeries();
        series1.setLabel("Temperatura - Día");
        series2.setLabel("Humedad del Suelo - Día");
        series3.setLabel("Punto De Rocío - Día");
        series4.setLabel("Temperatura - Noche");
        series5.setLabel("Humedad del Suelo - Noche");
        series6.setLabel("Punto De Rocío - Noche");

        TemperatureController controller = new TemperatureController();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getFirstDayOfWeek(dateWeek));

        for (int i = 0; i < 7; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            Temperature meanTemp;
            meanTemp = controller.calcularMean(fecha1, fecha1, 2);

            series1.set(DateTools.getDayOfWeek(i + 1), meanTemp.getTemperature());
            series2.set(DateTools.getDayOfWeek(i + 1), meanTemp.getHumidity());
            series3.set(DateTools.getDayOfWeek(i + 1), meanTemp.getDewPoint());

            meanTemp = controller.calcularMean(fecha1, fecha1, 1);

            series4.set(DateTools.getDayOfWeek(i + 1), meanTemp.getTemperature());
            series5.set(DateTools.getDayOfWeek(i + 1), meanTemp.getHumidity());
            series6.set(DateTools.getDayOfWeek(i + 1), meanTemp.getDewPoint());
        }

        model3.addSeries(series1);
        model3.addSeries(series2);
        model3.addSeries(series3);
        model3.addSeries(series4);
        model3.addSeries(series5);
        model3.addSeries(series6);
        model3.setShowPointLabels(true);
        model3.getAxes().put(AxisType.X, new CategoryAxis("Día"));
        model3.setTitle("Promedio de Temperatura " + DateTools.getWeek(dateWeek));
        model3.setLegendPosition("e");
    }

    public void createModel4() {
        model4 = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();

        series1.setLabel("Temperatura");
        series2.setLabel("Humedad del Suelo");
        series3.setLabel("Punto De Rocío");

        TemperatureController controller = new TemperatureController();

        for (int i = 0; i < 24; i++) {
            Temperature meanTemp;
            meanTemp = controller.calcularMean(dateDay, i);

            series1.set(i, meanTemp.getTemperature());
            series2.set(i, meanTemp.getHumidity());
            series3.set(i, meanTemp.getDewPoint());
        }

        model4.addSeries(series1);
        model4.addSeries(series2);
        model4.addSeries(series3);
        model4.setShowPointLabels(true);
        model4.getAxes().put(AxisType.X, new CategoryAxis("Hora"));
        model4.setTitle("Promedio de Temperatura " + DateFormatter.formatDate(dateDay));
        model4.setLegendPosition("e");
    }

}
