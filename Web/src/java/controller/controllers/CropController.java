package controller.controllers;

import controller.util.CellDataExtractor;
import model.crop.Crop;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.Storage;
import data.crop.CropDAO;
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
import model.administration.Farm;
import model.administration.Lot;
import model.administration.ModuleClass;
import model.administration.Person;
import model.administration.RoleEnum;
import model.util.DateTools;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean(name = "cropController")
@SessionScoped
public class CropController implements Serializable {

    private CropDAO jpaController = null;
    private List<Crop> items = null;
    private Crop selected;
    @ManagedProperty(value = "#{permissionController}")
    private PermissionController permissionBean;
    @ManagedProperty(value = "#{cultivationController}")
    private CultivationController cultivationBean;
    //File variables
    private String message;
    //Chart variables
    private CartesianChartModel model;
    private Date date;
    private int year;
    private int month;
    private int terrain;
    private int period;
    private Lot lot;
    private ModuleClass module;

    public CropController() {
        year = DateTools.getYear();
        month = DateTools.getMonth();
        date = DateTools.getDate();
        terrain = 0;
        period = 0;
    }

    @PostConstruct
    public void init() {
        createChart();
    }

    public Crop getSelected() {
        return selected;
    }

    public void setSelected(Crop selected) {
        this.selected = selected;
    }

    public PermissionController getPermissionBean() {
        return permissionBean;
    }

    public CultivationController getCultivationBean() {
        return cultivationBean;
    }

    public void setCultivationBean(CultivationController cultivationBean) {
        this.cultivationBean = cultivationBean;
    }

    public void setPermissionBean(PermissionController permissionBean) {
        this.permissionBean = permissionBean;
    }

    public CartesianChartModel getModel() {
        return model;
    }

    public void setModel(CartesianChartModel model) {
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

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CropDAO getJpaController() {
        if (jpaController == null) {
            jpaController = new CropDAO(EntityManagerFactorySingleton.getEntityManagerFactory());
        }
        return jpaController;
    }

    public Crop prepareCreate() {
        selected = new Crop();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleCrop").getString("CropCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleCrop").getString("CropUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleCrop").getString("CropDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Crop> getItems() {
        if (items == null) {
            if (permissionBean.getSignInBean().getRole() == RoleEnum.ADMINISTRATIVE_ASSISTANT || permissionBean.getSignInBean().getUser().isSystemAdmin()) {
                items = readListByFarm(null, permissionBean.getSignInBean().getFarm(), null, null);
            } else {
                items = readListByModule(permissionBean.getSignInBean().getUser(), null, null, null);
            }
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

    public List<Crop> getItemsAvailableSelectMany() {
        return getItems();
    }

    public List<Crop> getItemsAvailableSelectOne() {
        return getItems();
    }

    public void save(Crop crop) {
        selected = crop;
        persist(PersistAction.CREATE, null);
    }

    public List<Crop> readListByModule(Person recolector, ModuleClass module, Date inicio, Date fin) {
        return getJpaController().findCropEntities(recolector, module, inicio, fin);
    }

    public List<Crop> readListByLot(Person recolector, Lot lot, Date inicio, Date fin) {
        return getJpaController().findCropEntities(recolector, lot, inicio, fin);
    }

    public List<Crop> readListByFarm(Person recolector, Farm farm, Date inicio, Date fin) {
        return getJpaController().findCropEntities(recolector, farm, inicio, fin);
    }

    public Crop sumRegistersByModule(Person recolector, ModuleClass module, Date inicio, Date fin) {
        List<Crop> list = readListByModule(recolector, module, inicio, fin);
        Crop suma = new Crop();
        suma.setCollector(recolector);
        for (Crop r : list) {
            suma.sumCrop(r);
        }
        return suma;
    }

    public Crop sumRegistersByLot(Person recolector, Lot lot, Date inicio, Date fin) {
        List<Crop> leerLista = readListByLot(recolector, lot, inicio, fin);
        Crop suma = new Crop();
        suma.setCollector(recolector);
        for (Crop r : leerLista) {
            suma.sumCrop(r);
        }
        return suma;
    }

    public Crop sumRegistersByFarm(Person recolector, Farm farm, Date inicio, Date fin) {
        List<Crop> leerLista = readListByFarm(recolector, farm, inicio, fin);
        Crop suma = new Crop();
        suma.setCollector(recolector);
        for (Crop r : leerLista) {
            suma.sumCrop(r);
        }
        return suma;
    }

    @FacesConverter(forClass = Crop.class)
    public static class CropControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CropController controller = (CropController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cropController");
            return controller.getJpaController().findCrop(getKey(value));
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
            if (object instanceof Crop) {
                Crop o = (Crop) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Crop.class.getName()});
                return null;
            }
        }

    }

    //File functions
    public void upload(FileUploadEvent event) {
        String filename = "crop" + new Date().getTime() + ".xlsx";
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
//            FileInputStream fis = null;
//            fis = new FileInputStream(new File(filename));
//            XSSFWorkbook workbook = new XSSFWorkbook(fis);
//            Iterator<XSSFSheet> sheetIterator = workbook.iterator();
//            Crop temp = null;
//            while (sheetIterator.hasNext()) {
//                XSSFSheet sheet = sheetIterator.next();
//                int yearSheetName = Integer.parseInt(sheet.getSheetName());
//                Iterator<Row> rowIterator = sheet.iterator();
//                rowIterator.next();
//                rowIterator.next();
//                rowIterator.next();
//                rowIterator.next();
//
//                while (rowIterator.hasNext()) {
//                    Row row = rowIterator.next();
//                    if (row.getCell(0) != null) {
//                        int monthCellValue = DateTools.getMonth(row.getCell(0).getStringCellValue());
//                        int dayOfMonthCellValue = new GregorianCalendar(yearSheetName, monthCellValue, 1).getActualMaximum(Calendar.DAY_OF_MONTH);
//                        for (int day = 1; day <= dayOfMonthCellValue; day++) {
//                            Cell cell = row.getCell(day);
//                            double millimetersCellValue = CellDataExtractor.parseNumber(cell);
//                            if (millimetersCellValue > 0) {
//                                temp = new Crop();
//                                
//                                save(temp);
//                                created++;
//                            }
//                        }
//                    }
//                }
//            }
//            fis.close();
//            message = "Se crearon " + created + " nuevos registros.";
//        } catch (Exception e) {
//            e.printStackTrace();
//            message = "Error inesperado.";
//        }
    }

    //Chart functions
    public void createChart() {
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Pesada Kg");
        Crop totalSum = new Crop();
        Calendar cal = GregorianCalendar.getInstance();
        int maxPeriods = 0;
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
            switch (terrain) {
                case 0:
                    totalSum = sumRegistersByModule(null, module, startDate, endDate);
                    break;
                case 1:
                    totalSum = sumRegistersByLot(null, lot, startDate, endDate);
                    break;
                case 2:
                    totalSum = sumRegistersByFarm(null, permissionBean.getSignInBean().getFarm(), startDate, endDate);
                    break;
            }
            String label = "";
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
            series1.set(label, totalSum.getWeightInKilograms());
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        model = new LineChartModel();
        model.addSeries(series1);
        model.setShowPointLabels(true);
        switch (period) {
            case 0:
                model.getAxes().put(AxisType.X, new CategoryAxis("Día"));
                model.setTitle("Recolección por Día " + DateTools.getWeek(date));
                break;
            case 1:
                model.getAxes().put(AxisType.X, new CategoryAxis("Día"));
                model.setTitle("Recolección por Día " + DateTools.getMonth(month) + " de " + year);
                break;
            case 2:
                model.getAxes().put(AxisType.X, new CategoryAxis("Semana"));
                model.setTitle("Recolección por Semana Año" + year);
                break;
            case 3:
                model.getAxes().put(AxisType.X, new CategoryAxis("Mes"));
                model.setTitle("Recolección por Mes Año" + year);
                break;
        }
        model.setLegendPosition("e");
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }

}
