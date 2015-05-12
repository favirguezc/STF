package controller.controllers;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import controller.util.Font;
import model.crop.Classification;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.Storage;
import controller.util.TableRowData;
import data.crop.ClassificationDAO;
import data.util.EntityManagerFactorySingleton;
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
import model.administration.Farm;
import model.administration.Lot;
import model.administration.ModuleClass;
import model.crop.ClassificationTypeEnum;
import model.util.DateFormatter;
import model.util.DateTools;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean(name = "classificationController")
@SessionScoped
public class ClassificationController implements Serializable {

    private ClassificationDAO jpaController = null;
    private List<Classification> items = null;
    private Classification selected;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;
    //File variables
    private String message;
    //Chart variables
    private LineChartModel model;
    private Date date;
    private int year;
    private int month;
    private int terrain;
    private int period;
    private Lot lot;
    private ModuleClass module;
    private List<TableRowData> chartPairs;
    private String header1;
    private String header2;
    private String header3;
    private String header4;
    private String header5;
    private String header6;
    private String header7;
    private String header8;

    public ClassificationController() {
        year = DateTools.getYear();
        month = DateTools.getMonth();
        date = DateTools.getDate();
        terrain = 0;
        period = 0;
        model = new LineChartModel();
    }

    @PostConstruct
    public void init() {
        createChart();
    }

    public Classification getSelected() {
        return selected;
    }

    public void setSelected(Classification selected) {
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

    public int getTerrain() {
        return terrain;
    }

    public void setTerrain(int terrain) {
        this.terrain = terrain;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Lot getLot() {
        return lot;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
    }

    public ModuleClass getModule() {
        return module;
    }

    public void setModule(ModuleClass module) {
        this.module = module;
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

    public String getHeader7() {
        return header7;
    }

    public void setHeader7(String header7) {
        this.header7 = header7;
    }

    public String getHeader8() {
        return header8;
    }

    public void setHeader8(String header8) {
        this.header8 = header8;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ClassificationDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new ClassificationDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Classification prepareCreate() {
        selected = new Classification();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleClassification").getString("ClassificationCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleClassification").getString("ClassificationUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleClassification").getString("ClassificationDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Classification> getItems() {
        if (items == null) {
            items = getJpaController().findClassificationEntities(permissionBean.getSignInBean().getFarm());
        }
        return items;
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

    public List<Classification> getItemsAvailableSelectMany() {
        return getItems();
    }

    public List<Classification> getItemsAvailableSelectOne() {
        return getItems();
    }

    public List<Classification> readListByModule(ModuleClass module, Date startDate, Date endDate, ClassificationTypeEnum type) {
        return getJpaController().findClassificationEntities(module, startDate, endDate, type);
    }

    public List<Classification> readListByLot(Lot lot, Date startDate, Date endDate, ClassificationTypeEnum type) {
        return getJpaController().findClassificationEntities(lot, startDate, endDate, type);
    }

    public List<Classification> readListByFarm(Farm farm, Date startDate, Date endDate, ClassificationTypeEnum type) {
        return getJpaController().findClassificationEntities(farm, startDate, endDate, type);
    }

    public Classification sumRegistersByModule(ModuleClass module, Date startDate, Date endDate, ClassificationTypeEnum type) {
        List<Classification> list = readListByModule(module, startDate, endDate, type);
        Classification sum = new Classification();
        for (Classification register : list) {
            sum.sumClassification(register);
        }
        return sum;
    }

    public Classification sumRegistersByLot(Lot lot, Date startDate, Date endDate, ClassificationTypeEnum type) {
        List<Classification> list = readListByLot(lot, startDate, endDate, type);
        Classification sum = new Classification();
        for (Classification register : list) {
            sum.sumClassification(register);
        }
        return sum;
    }

    public Classification sumRegistersByFarm(Farm farm, Date startDate, Date endDate, ClassificationTypeEnum type) {
        List<Classification> leerLista = readListByFarm(farm, startDate, endDate, type);
        Classification sum = new Classification();
        for (Classification register : leerLista) {
            sum.sumClassification(register);
        }
        return sum;
    }

    @FacesConverter(forClass = Classification.class)
    public static class ClasificacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ClassificationController controller = (ClassificationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "classificationController");
            return controller.getJpaController().findClassification(getKey(value));
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
            if (object instanceof Classification) {
                Classification o = (Classification) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Classification.class.getName()});
                return null;
            }
        }
    }

    //File functions
    public void upload(FileUploadEvent event) {
        String filename = "classification" + new Date().getTime() + ".xlsx";
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
//        int created = 0;
//        try {
//            FileInputStream file = new FileInputStream(new File(filename));
//            XSSFWorkbook workbook = new XSSFWorkbook(file);
//            XSSFSheet sheet = workbook.getSheetAt(0);
//            Iterator<Row> rowIterator = sheet.iterator();
//            rowIterator.next();
//            rowIterator.next();
//            SoilMoisture temp = null;
//            while (rowIterator.hasNext()) {
//                Row row = rowIterator.next();
//                Date date = CellDataExtractor.parseDate(row.getCell(0));
//                double cell15 = CellDataExtractor.parseNumber(row.getCell(1));
//                double cell30 = CellDataExtractor.parseNumber(row.getCell(2));
//                Date time = CellDataExtractor.parseTime(row.getCell(3));
//                if (date != null && time != null) {
//                    try {
//                        temp = new SoilMoisture();
//                        temp.setFarm(permissionBean.getSignInBean().getFarm());
//                        temp.setMeasurementDate(date);
//                        temp.setMeasurementTime(time);
//                        temp.setValueIn15Centimeters((float) cell15);
//                        temp.setValueIn30Centimeters((float) cell30);
//                        save(temp);
//                        created++;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                } else if (date == null) {
//                    System.out.println("Date null");
//                } else if (time == null) {
//                    System.out.println("Time null");
//                }
//            }
//            file.close();
//            message = "Se crearon " + created + " nuevos registros.";
//        } catch (Exception e) {
//            e.printStackTrace();
//            message = "Error inesperado.";
//        }
    }

    //Chart functions
    public void createChart() {
        chartPairs = new ArrayList<TableRowData>();
        LineChartSeries[] series = new LineChartSeries[8];
        ClassificationTypeEnum[] types = ClassificationTypeEnum.values();
        for (int i = 0; i < 8; i++) {
            series[i] = new LineChartSeries(types[i].toString());
        }
        header1 = types[0].toString();
        header2 = types[1].toString();
        header3 = types[2].toString();
        header4 = types[3].toString();
        header5 = types[4].toString();
        header6 = types[5].toString();
        header7 = types[6].toString();
        header8 = types[7].toString();
        Classification totalSum = new Classification();
        Calendar cal = GregorianCalendar.getInstance();
        int maxPeriods = 0;
        float[] total = new float[8];
        TableRowData rowData;
        switch (period) {
            case 0:
                maxPeriods = 7;
                cal.setTime(DateTools.getFirstDayOfWeek(date));
                break;
            case 1:
                maxPeriods = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                cal.setTime(DateTools.getDate(year, month, 1));
                break;
            case 2:
                maxPeriods = 52;
                cal.setTime(DateTools.getDate(year, 0, 1));
                break;
            case 3:
                maxPeriods = 12;
                cal.setTime(DateTools.getDate(year, 0, 1));
                break;
        }
        String label = "";
        for (int i = 1; i <= maxPeriods; i++) {
            Date startDate = cal.getTime();
            switch (period) {
                case 2:
                    cal.add(Calendar.DAY_OF_MONTH, 6);
                    break;
                case 3:
                    cal.add(Calendar.MONTH, 1);
                    cal.add(Calendar.DAY_OF_MONTH, -1);
                    break;
            }
            Date endDate = cal.getTime();
            if (period == 0 || period == 1) {
                endDate = null;
            }
            switch (period) {
                case 0:
                    label = DateTools.getDayOfWeek(i);
                    break;
                case 1:
                    label = i + "";
                    break;
                case 2:
                    label = i + "";
                    break;
                case 3:
                    label = DateTools.getMonth(i - 1);
                    break;
            }
            rowData = new TableRowData();
            rowData.setLabel(label);
            float sum = 0;
            for (int t = 0; t < 8; t++) {
                ClassificationTypeEnum type = types[t];
                switch (terrain) {
                    case 0:
                        totalSum = sumRegistersByModule(module, startDate, endDate, type);
                        break;
                    case 1:
                        totalSum = sumRegistersByLot(lot, startDate, endDate, type);
                        break;
                    case 2:
                        totalSum = sumRegistersByFarm(permissionBean.getSignInBean().getFarm(), startDate, endDate, type);
                        break;
                }
                series[t].set(label, totalSum.getWeightInKilograms());
                sum += totalSum.getWeightInKilograms();
                total[t] += totalSum.getWeightInKilograms();
                rowData.setValue(t + 1, totalSum.getWeightInKilograms());
            }
            rowData.setValue(9, sum);
            chartPairs.add(rowData);
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        rowData = new TableRowData();
        rowData.setLabel("Total");
        float sum = 0;
        for (int i = 0; i < 8; i++) {
            rowData.setValue(i + 1, total[i]);
            sum += total[i];
        }
        rowData.setValue(9, sum);
        chartPairs.add(rowData);
        model = new LineChartModel();
        for (int i = 0; i < 8; i++) {
            model.addSeries(series[i]);
        }
        model.setShowPointLabels(true);
        switch (period) {
            case 0:
                model.getAxes().put(AxisType.X, new CategoryAxis("Día"));
                model.setTitle("Clasificación por Día " + DateTools.getWeek(date));
                break;
            case 1:
                model.getAxes().put(AxisType.X, new CategoryAxis("Día"));
                model.setTitle("Clasificación por Día " + DateTools.getMonth(month) + " de " + year);
                break;
            case 2:
                model.getAxes().put(AxisType.X, new CategoryAxis("Semana"));
                model.setTitle("Clasificación por Semana Año" + year);
                break;
            case 3:
                model.getAxes().put(AxisType.X, new CategoryAxis("Mes"));
                model.setTitle("Clasificación por Mes Año" + year);
                break;
        }
        model.setLegendPosition("e");
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }

    //Report functions
    public void postProcessPDF(Object document) throws DocumentException {
        Document pdf = (Document) document;
        pdf.add(Chunk.NEWLINE);
        Paragraph footer = new Paragraph("*Valores en kilogramos", Font.getFont(12));
        footer.add(new Paragraph("Este reporte fue generado automáticamente", Font.getFont(12)));
        footer.add(new Paragraph("Fecha de creación: " + DateFormatter.formatDateLong(DateTools.getDate()) + " a las " + DateFormatter.formatTime(DateTools.getDate()), Font.getFont(12)));
        pdf.add(footer);
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);
        pdf.setMargins(20, 30, 20, 20);
        Paragraph header = new Paragraph("Finca: " + permissionBean.getSignInBean().getFarm().getName() + "\n", Font.getFont(20));
        header.setAlignment(Element.ALIGN_CENTER);
        String title = "";
        String subTitle = "";
        switch (period) {
            case 0:
                title = "Reporte Semanal Por Día";
                subTitle = DateTools.getWeek(date);
                break;
            case 1:
                title = "Reporte Mensual Por Día";
                subTitle = DateTools.getMonth(month) + " de " + year;
                break;
            case 2:
                title = "Reporte Anual Por Semana";
                subTitle = "" + year;
                break;
            case 3:
                title = "Reporte Anual Por Mes";
                subTitle = "" + year;
                break;
        }
        Paragraph paragraph = new Paragraph(title, Font.getFont(20));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        header.add(paragraph);
        paragraph = new Paragraph(subTitle, Font.getFont(20));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        header.add(paragraph);
        switch (terrain) {
            case 0:
                paragraph = new Paragraph("Módulo: " + module, Font.getFont(20));
                paragraph.setAlignment(Element.ALIGN_CENTER);
                header.add(paragraph);
                break;
            case 1:
                paragraph = new Paragraph("Lote: " + lot, Font.getFont(20));
                paragraph.setAlignment(Element.ALIGN_CENTER);
                header.add(paragraph);
                break;
        }
        header.setAlignment(Element.ALIGN_CENTER);
        pdf.add(header);
        pdf.add(Chunk.NEWLINE);
    }
}
