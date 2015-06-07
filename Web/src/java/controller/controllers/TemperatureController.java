package controller.controllers;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import controller.util.DateParser;
import controller.util.Font;
import model.weather.Temperature;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.Storage;
import controller.util.TableRowData;
import data.weather.TemperatureDAO;
import data.util.EntityManagerFactorySingleton;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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
    private LineChartModel model;
    private int period;
    private Date date;
    private int year;
    private int month;
    private List<TableRowData> chartPairs;
    private String header1;
    private String header2;
    private String header3;
    private String header4;
    private String header5;
    private String header6;

    public TemperatureController() {
        year = DateTools.getYear();
        month = DateTools.getMonth();
        date = DateTools.getDate();
        period = 0;
    }

    @PostConstruct
    public void init() {
        createChart();
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

    public LineChartModel getModel() {
        return model;
    }

    public void setModel(LineChartModel model) {
        this.model = model;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public List<TableRowData> getChartPairs() {
        return chartPairs;
    }

    public void setChartPairs(List<TableRowData> chartPairs) {
        this.chartPairs = chartPairs;
    }

    public String getHeader1() {
        return header1;
    }

    public void setHeader1(String header1) {
        this.header1 = header1;
    }

    public String getHeader2() {
        return header2;
    }

    public void setHeader2(String header2) {
        this.header2 = header2;
    }

    public String getHeader3() {
        return header3;
    }

    public void setHeader3(String header3) {
        this.header3 = header3;
    }

    public String getHeader4() {
        return header4;
    }

    public void setHeader4(String header4) {
        this.header4 = header4;
    }

    public String getHeader5() {
        return header5;
    }

    public void setHeader5(String header5) {
        this.header5 = header5;
    }

    public String getHeader6() {
        return header6;
    }

    public void setHeader6(String header6) {
        this.header6 = header6;
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
        if (items == null || items.isEmpty()) {
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
        ModuleClass module;
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
                module = thermometer.getModuleObject();
                while (line != null) {
                    Date dateTemp = DateParser.parseDate(line.split(",")[1].split(" ")[0]);
                    Date timeTemp = DateParser.parseTime(line.split(",")[1].split(" ")[1]);
                    float temperatureTemp = Float.parseFloat(line.split(",")[2]);
                    float soilMoistureTemp = Float.parseFloat(line.split(",")[3]);
                    float dewPointTemp = Float.parseFloat(line.split(",")[4]);
                    save(new Temperature(dateTemp, timeTemp, temperatureTemp, soilMoistureTemp, dewPointTemp, module));
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
    public void createChart() {
        model = new LineChartModel();
        chartPairs = new ArrayList<TableRowData>();
        header1 = null;
        header2 = null;
        header3 = null;
        header4 = null;
        header5 = null;
        header6 = null;
        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();
        LineChartSeries series4 = new LineChartSeries();
        LineChartSeries series5 = new LineChartSeries();
        LineChartSeries series6 = new LineChartSeries();
        Calendar cal = GregorianCalendar.getInstance();
        int maxPeriods = 0;
        Temperature meanTemp = new Temperature();
        TableRowData rowData;
        String axis = "";
        String chartTitle = "";
        switch (period) {
            case 0:
                maxPeriods = 24;
                cal.setTime(date);
                axis = "Hora";
                chartTitle = "Temperatura por Hora " + DateFormatter.formatDateLong(date);
                header1 = "Temperatura";
                header2 = "Humedad";
                header3 = "Punto de Rocío";
                break;
            case 1:
                maxPeriods = 7;
                cal.setTime(DateTools.getFirstDayOfWeek(date));
                axis = "Día";
                chartTitle = "Temperatura por Día " + DateTools.getWeek(date);
                header1 = "Temperatura - Día";
                header2 = "Humedad - Día";
                header3 = "Punto de Rocío - Día";
                header4 = "Temperatura - Noche";
                header5 = "Humedad - Noche";
                header6 = "Punto de Rocío - Noche";
                break;
            case 2:
                maxPeriods = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                cal.setTime(DateTools.getDate(year, month, 1));
                axis = "Día";
                chartTitle = "Temperatura por Día " + DateTools.getMonth(month) + " de " + year;
                header1 = "Temperatura - Día";
                header2 = "Humedad - Día";
                header3 = "Punto de Rocío - Día";
                header4 = "Temperatura - Noche";
                header5 = "Humedad - Noche";
                header6 = "Punto de Rocío - Noche";
                break;
            case 3:
                maxPeriods = 12;
                cal.setTime(DateTools.getDate(year, 0, 1));
                axis = "Mes";
                chartTitle = "Temperatura por Mes Año " + year;
                header1 = "Temperatura - Día";
                header2 = "Humedad - Día";
                header3 = "Punto de Rocío - Día";
                header4 = "Temperatura - Noche";
                header5 = "Humedad - Noche";
                header6 = "Punto de Rocío - Noche";
                break;
        }
        for (int i = 1; i <= maxPeriods; i++) {
            Date startDate = cal.getTime();
            rowData = new TableRowData();
            String label = "";
            switch (period) {
                case 0:
                    label = (i - 1) + "";
                    break;
                case 1:
                    label = DateTools.getDayOfWeek(i);
                    break;
                case 2:
                    label = i + "";
                    break;
                case 3:
                    label = DateTools.getMonth(i - 1);
                    break;
            }
            rowData.setLabel(label);
            switch (period) {
                case 0:
                    meanTemp = calcularMean(startDate, i - 1);
                    break;
                case 1:
                    meanTemp = calcularMean(startDate, startDate, 2);
                    break;
                case 2:
                    meanTemp = calcularMean(startDate, startDate, 2);
                    break;
                case 3:
                    cal.add(Calendar.MONTH, 1);
                    cal.add(Calendar.DAY_OF_MONTH, -1);
                    meanTemp = calcularMean(startDate, cal.getTime(), 2);
                    break;
            }
            series1.set(label, meanTemp.getTemperature());
            series2.set(label, meanTemp.getHumidity());
            series3.set(label, meanTemp.getDewPoint());
            rowData.setValue1(meanTemp.getTemperature());
            rowData.setValue2(meanTemp.getHumidity());
            rowData.setValue3(meanTemp.getDewPoint());
            if (period != 0) {
                switch (period) {
                    case 1:
                        meanTemp = calcularMean(startDate, startDate, 1);
                        break;
                    case 2:
                        meanTemp = calcularMean(startDate, startDate, 1);
                        break;
                    case 3:
                        meanTemp = calcularMean(startDate, cal.getTime(), 1);
                        break;
                }
                series4.set(label, meanTemp.getTemperature());
                series5.set(label, meanTemp.getHumidity());
                series6.set(label, meanTemp.getDewPoint());
                rowData.setValue4(meanTemp.getTemperature());
                rowData.setValue5(meanTemp.getHumidity());
                rowData.setValue6(meanTemp.getDewPoint());
                cal.add(Calendar.DAY_OF_MONTH, 1);
            }
            chartPairs.add(rowData);
        }
        series1.setLabel(header1);
        series2.setLabel(header2);
        series3.setLabel(header3);
        if (period != 0) {
            series4.setLabel(header4);
            series5.setLabel(header5);
            series6.setLabel(header6);
        }
        model.addSeries(series1);
        model.addSeries(series2);
        model.addSeries(series3);
        if (period != 0) {
            model.addSeries(series4);
            model.addSeries(series5);
            model.addSeries(series6);
        }
        model.setShowPointLabels(true);
        model.getAxes().put(AxisType.X, new CategoryAxis(axis));
        model.setTitle(chartTitle);
        model.setLegendPosition("e");
    }

    //Report functions
    public void postProcessPDF(Object document) throws DocumentException {
        Document pdf = (Document) document;
        pdf.add(Chunk.NEWLINE);
        Paragraph footer = new Paragraph("Este reporte fue generado automáticamente\n", Font.getFont(12));
        footer.add(new Paragraph("Fecha de creación: " + DateFormatter.formatDateLong(DateTools.getDate()) + " a las " + DateFormatter.formatTime(DateTools.getDate()), Font.getFont(12)));
        pdf.add(footer);
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
        Paragraph header = new Paragraph("Finca: " + permissionBean.getSignInBean().getFarm().getName() + "\n", Font.getFont(20));
        header.setAlignment(Element.ALIGN_CENTER);
        String title = "";
        String subtitle = "";
        switch (period) {
            case 0:
                title = "Temperatura por Hora";
                subtitle = DateFormatter.formatDateLong(date);
                break;
            case 1:
                title = "Temperatura por Día";
                subtitle = DateTools.getWeek(date);
                break;
            case 2:
                title = "Temperatura por Día";
                subtitle = DateTools.getMonth(month) + " de " + year;
                break;
            case 3:
                title = "Temperatura por Mes";
                subtitle = "Año " + year;
                break;
        }
        Paragraph paragraph = new Paragraph(title, Font.getFont(20));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        header.add(paragraph);
        paragraph = new Paragraph(subtitle, Font.getFont(20));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        header.add(paragraph);
        header.setAlignment(Element.ALIGN_CENTER);
        pdf.add(header);
        pdf.add(Chunk.NEWLINE);
    }
}
