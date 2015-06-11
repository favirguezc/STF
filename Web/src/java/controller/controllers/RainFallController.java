package controller.controllers;

import controller.util.CellDataExtractor;
import model.weather.RainFall;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.Storage;
import data.weather.RainFallDAO;
import data.util.EntityManagerFactorySingleton;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
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
import model.util.DateTools;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean(name = "rainFallController")
@SessionScoped
public class RainFallController implements Serializable {

    private RainFallDAO jpaController = null;
    private List<RainFall> items = null;
    private RainFall selected;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;
    //File variables
    private String message;
    //Chart variables
    private LineChartModel model;
    private int year;
    private int month;

    @PostConstruct
    public void init() {
        year = DateTools.getYear();
        month = DateTools.getMonth();
        createModel();
    }

    public RainFall getSelected() {
        return selected;
    }

    public void setSelected(RainFall selected) {
        this.selected = selected;
    }

    public PermissionController getPermissionBean() {
        return permissionBean;
    }

    public void setPermissionBean(PermissionController permissionBean) {
        this.permissionBean = permissionBean;
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

    public LineChartModel getModel() {
        return model;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RainFallDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new RainFallDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public RainFall prepareCreate() {
        selected = new RainFall();
        selected.setFarm(permissionBean.getSignInBean().getFarm());
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleRainFall").getString("RainFallCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleRainFall").getString("RainFallUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleRainFall").getString("RainFallDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<RainFall> getItems() {
        if (items == null || items.isEmpty()) {
            items = getJpaController().findRainFallEntities();
        }
        return items;
    }

    public List<RainFall> getItems(Date fecha1, Date fecha2) {
        return getJpaController().findRainFallEntities(fecha1, fecha2);
    }

    public List<RainFall> getItems(Date fecha1) {
        return getJpaController().findRainFallEntities(fecha1);
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

    public List<RainFall> getItemsAvailableSelectMany() {
        return getJpaController().findRainFallEntities();
    }

    public List<RainFall> getItemsAvailableSelectOne() {
        return getJpaController().findRainFallEntities();
    }

    public void save(RainFall rainFall) {
        selected = rainFall;
        if (getJpaController().findRainFallEntities(rainFall.getRainDate()).isEmpty()) {
            persist(PersistAction.CREATE, null);
        }
    }

    public float calcularMean(Date fecha1, Date fecha2) {
        float mean = 0;
        List<RainFall> lista = getItems(fecha1, fecha2);
        for (RainFall l : lista) {
            mean += l.getMilimeters();
        }
        if (lista.size() > 1) {
            mean = mean / lista.size();
        }
        return mean;
    }

    public float calculateMean(Date fecha1) {
        float mean = 0;
        List<RainFall> lista = getItems(fecha1);
        for (RainFall l : lista) {
            mean += l.getMilimeters();
        }
        if (lista.size() > 1) {
            mean = mean / lista.size();
        }
        return mean;
    }

    @FacesConverter(forClass = RainFall.class)
    public static class RainFallControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RainFallController controller = (RainFallController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "rainFallController");
            return controller.getJpaController().findRainFall(getKey(value));
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
            if (object instanceof RainFall) {
                RainFall o = (RainFall) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), RainFall.class.getName()});
                return null;
            }
        }
    }

    //File functions
    public void upload(FileUploadEvent event) {
        String filename = "rainFall" + new Date().getTime() + ".xlsx";
        if (permissionBean.getSignInBean().getFarm() != null) {
            Storage.save(filename, event.getFile());
            parse(filename);
            Storage.delete(filename);
        } else {
            message = "¡Error! No hay una finca seleccionada.";
        }
        JsfUtil.addSuccessMessage(message);
    }

    private void parse(String filename) {
        int created = 0;
        try {
            FileInputStream fis = null;
            fis = new FileInputStream(new File(filename));
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            Iterator<XSSFSheet> sheetIterator = workbook.iterator();
            RainFall temp = null;
            while (sheetIterator.hasNext()) {
                XSSFSheet sheet = sheetIterator.next();
                int yearSheetName = Integer.parseInt(sheet.getSheetName());
                Iterator<Row> rowIterator = sheet.iterator();
                rowIterator.next();
                rowIterator.next();
                rowIterator.next();
                rowIterator.next();

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    if (row.getCell(0) != null) {
                        int monthCellValue = DateTools.getMonth(row.getCell(0).getStringCellValue());
                        int dayOfMonthCellValue = new GregorianCalendar(yearSheetName, monthCellValue, 1).getActualMaximum(Calendar.DAY_OF_MONTH);
                        for (int day = 1; day <= dayOfMonthCellValue; day++) {
                            Cell cell = row.getCell(day);
                            double millimetersCellValue = CellDataExtractor.parseNumber(cell);
                            if (millimetersCellValue > 0) {
                                temp = new RainFall();
                                temp.setFarm(permissionBean.getSignInBean().getFarm());
                                temp.setRainDate(DateTools.getDate(yearSheetName, monthCellValue, day));
                                temp.setMilimeters((float) millimetersCellValue);
                                save(temp);
                                created++;
                            }
                        }
                    }
                }
            }
            fis.close();
            message = "Se crearon " + created + " nuevos registros.";
        } catch (Exception e) {
            e.printStackTrace();
            message = "Error inesperado.";
        }
    }

    //Chart functions
    public void createModel() {
        model = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Lluvia");
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(year, month, 1));
        for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            Date date = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            series1.set(i + 1, calculateMean(date));
        }

        model.addSeries(series1);
        model.setShowPointLabels(true);
        model.getAxes().put(AxisType.X, new CategoryAxis("Día"));
        model.setTitle("Promedio de Lluvia por Mes " + DateTools.getMonth(month) + " de " + year);
        model.setLegendPosition("e");
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }

}
