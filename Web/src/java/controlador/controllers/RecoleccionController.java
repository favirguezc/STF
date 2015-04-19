package controlador.controllers;

import model.crop.Crop;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import data.crop.CropDAO;
import data.util.EntityManagerFactorySingleton;
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
import model.administration.Cultivation;
import model.administration.ModuleClass;
import model.administration.Person;
import model.administration.RoleEnum;
import model.util.DateTools;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean(name = "recoleccionController")
@SessionScoped
public class RecoleccionController implements Serializable {

    private CropDAO jpaController = null;
    private List<Crop> items = null;
    private Crop selected;
    @ManagedProperty(value = "#{permisoController}")
    private PermisoController permisoBean;
    @ManagedProperty(value = "#{cultivoController}")
    private CultivoController cultivoBean;

    public Crop getSelected() {
        return selected;
    }

    public void setSelected(Crop selected) {
        this.selected = selected;
    }

    public PermisoController getPermisoBean() {
        return permisoBean;
    }

    public CultivoController getCultivoBean() {
        return cultivoBean;
    }

    public void setCultivoBean(CultivoController cultivoBean) {
        this.cultivoBean = cultivoBean;
    }

    public void setPermisoBean(PermisoController permisoBean) {
        this.permisoBean = permisoBean;
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
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("RecoleccionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("RecoleccionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("RecoleccionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Crop> getItems() {
        if (items == null) {
            if (permisoBean.getSignInBean().getRol() == RoleEnum.ADMINISTRATIVE_ASSISTANT || permisoBean.getSignInBean().getUser().isSystemAdmin()) {
                items = leerLista(null, null, null, null);
            } else {
                items = leerLista(permisoBean.getSignInBean().getUser(), null, null, null);
            }
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
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

    public List<Crop> getItemsAvailableSelectMany() {
        return leerLista(null, null, null, null);
    }

    public List<Crop> getItemsAvailableSelectOne() {
        return leerLista(null, null, null, null);
    }

    public List<Crop> leerLista(Person recolector, Cultivation cultivo, Date inicio, Date fin) {
        if (cultivo == null) {
            return getJpaController().findRecoleccionEntities(recolector, permisoBean.getSignInBean().getFinca(), inicio, fin);
        }
        return getJpaController().findRecoleccionEntities(recolector, cultivo, inicio, fin);
    }

    public Crop sumarRegistros(Person recolector, Cultivation cultivo, Date inicio, Date fin) {
        List<Crop> leerLista = leerLista(recolector, cultivo, inicio, fin);
        Crop suma = new Crop();
        suma.setCultivation(cultivo);
        suma.setCollector(recolector);
        for (Crop r : leerLista) {
            suma.sumCrop(r);
        }
        return suma;
    }

    @FacesConverter(forClass = Crop.class)
    public static class RecoleccionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RecoleccionController controller = (RecoleccionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "recoleccionController");
            return controller.getJpaController().findRecoleccion(getKey(value));
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

    private LineChartModel modelo1;
    private LineChartModel modelo2;
    private LineChartModel modelo3;
    private LineChartModel modelo4;
    private BarChartModel modelo5;
    private int ano1;
    private int ano2;
    private int ano4;
    private int mes2;
    private Date fecha3;

    @PostConstruct
    public void init() {
        ano1 = ano2 = ano4 = DateTools.getYear();
        mes2 = DateTools.getMonth();
        fecha3 = DateTools.getDate();
        createModel1();
        createModel2();
        createModel3();
        createModel4();
        createModel5();
    }

    public BarChartModel getModelo5() {
        return modelo5;
    }

    public LineChartModel getModelo4() {
        return modelo4;
    }

    public int getAno4() {
        return ano4;
    }

    public void setAno4(int ano4) {
        this.ano4 = ano4;
    }

    public LineChartModel getModelo3() {
        return modelo3;
    }

    public Date getFecha3() {
        return fecha3;
    }

    public void setFecha3(Date fecha3) {
        this.fecha3 = fecha3;
    }

    public LineChartModel getModelo2() {
        return modelo2;
    }

    public int getAno2() {
        return ano2;
    }

    public void setAno2(int ano2) {
        this.ano2 = ano2;
    }

    public int getMes2() {
        return mes2;
    }

    public void setMes2(int mes2) {
        this.mes2 = mes2;
    }

    public LineChartModel getModelo1() {
        return modelo1;
    }

    public int getAno1() {
        return ano1;
    }

    public void setAno1(int ano1) {
        this.ano1 = ano1;
    }

    public void createModel1() {
        modelo1 = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Pesada Kg");

        Crop sumarRegistros;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(ano1, 0, 1));
        for (int i = 0; i < 12; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            Date fecha2 = cal.getTime();
            sumarRegistros = sumarRegistros(null, null, fecha1, fecha2);
            String mes = DateTools.getMonth(i);
            series1.set(mes, sumarRegistros.getWeight() / 1000);
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        modelo1.addSeries(series1);
        modelo1.setShowPointLabels(true);
        modelo1.getAxes().put(AxisType.X, new CategoryAxis("Mes"));
        modelo1.setTitle("Recolección por Mes Año " + ano1);
        modelo1.setLegendPosition("e");
        Axis yAxis = modelo1.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }

    public void createModel2() {
        modelo2 = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("");

        Crop sumarRegistros;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(ano2, mes2, 1));
        for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            sumarRegistros = sumarRegistros(null, null, fecha1, null);

            series1.set(i + 1, sumarRegistros.getWeight() / 1000);
        }

        modelo2.addSeries(series1);
        modelo2.setShowPointLabels(true);
        modelo2.getAxes().put(AxisType.X, new CategoryAxis("Día"));
        modelo2.setTitle("Recolección por Día " + DateTools.getMonth(mes2) + " de " + ano2);
        modelo2.setLegendPosition("e");
        Axis yAxis = modelo2.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }

    public void createModel3() {
        modelo3 = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Pesada Kg");

        Crop sumarRegistros;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getFirstDayOfWeek(fecha3));
        for (int i = 0; i < 7; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            sumarRegistros = sumarRegistros(null, null, fecha1, null);

            series1.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getWeight() / 1000);
        }

        modelo3.addSeries(series1);
        modelo3.setShowPointLabels(true);
        modelo3.getAxes().put(AxisType.X, new CategoryAxis("Día"));
        modelo3.setTitle("Recolección por Día " + DateTools.getWeek(fecha3));
        modelo3.setLegendPosition("e");
        Axis yAxis = modelo3.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }

    public void createModel4() {
        modelo4 = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Pesada Kg");

        Crop sumarRegistros;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(ano4, 0, 1));
        for (int i = 0; i < 52; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 6);
            Date fecha2 = cal.getTime();
            sumarRegistros = sumarRegistros(null, null, fecha1, fecha2);

            series1.set(i + 1, sumarRegistros.getWeight() / 1000);

            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        modelo4.addSeries(series1);
        modelo4.setShowPointLabels(true);
        modelo4.getAxes().put(AxisType.X, new CategoryAxis("Semana"));
        modelo4.setTitle("Recolección por Semana Año " + ano4);
        modelo4.setLegendPosition("e");
        Axis yAxis = modelo4.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }

    public void createModel5() {
        modelo5 = new BarChartModel();
        List<Cultivation> cultivos = cultivoBean.getItems();
        ChartSeries[] series = new ChartSeries[cultivos.size()];
        for (int modulo = 0; modulo < cultivos.size(); modulo++) {
            series[modulo] = new ChartSeries();
        }

        Calendar c = GregorianCalendar.getInstance();
        c.setTime(DateTools.getDate(2012, 0, 1));
        Date fecha1;
        Date fecha2;
        double valor;
        for (int i = 2012; i <= DateTools.getYear(); i++) {
            fecha1 = c.getTime();
            c.add(Calendar.YEAR, 1);
            c.add(Calendar.DAY_OF_MONTH, -1);
            fecha2 = c.getTime();

            for (int modulo = 0; modulo < cultivos.size(); modulo++) {
                valor = sumarRegistros(null, cultivos.get(modulo), fecha1, fecha2).getWeight() / 1000;
                series[modulo].set(i, valor);
            }
        }
        for (int l = 0; l < cultivos.size(); l++) {
            series[l].setLabel(cultivos.get(l).toString());
            modelo5.addSeries(series[l]);
        }

        modelo5.getAxes().put(AxisType.X, new CategoryAxis("Módulo"));
        modelo5.setTitle("Recolección por Módulo");
        modelo5.setLegendPosition("e");
        Axis yAxis = modelo5.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }

}
