/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.controllers;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import modelo.produccion.administracion.Modulo;
import modelo.produccion.cosecha.Recoleccion;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import modelo.util.DateTools;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "recoleccionChart")
public class RecoleccionChart implements Serializable {

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

        RecoleccionController controlador = new RecoleccionController();

        Recoleccion sumarRegistros;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(ano1, 0, 1));
        for (int i = 0; i < 12; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            Date fecha2 = cal.getTime();
            sumarRegistros = controlador.sumarRegistros(null, null, fecha1, fecha2);
            String mes = DateTools.getMonth(i);
            series1.set(mes, sumarRegistros.getPesadaGramos() / 1000);
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
        series1.setLabel("Pesada Kg");

        RecoleccionController controlador = new RecoleccionController();

        Recoleccion sumarRegistros;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(ano2, mes2, 1));
        for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            sumarRegistros = controlador.sumarRegistros(null, null, fecha1, null);

            series1.set(i + 1, sumarRegistros.getPesadaGramos() / 1000);
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

        RecoleccionController controlador = new RecoleccionController();

        Recoleccion sumarRegistros;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getFirstDayOfWeek(fecha3));
        for (int i = 0; i < 7; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            sumarRegistros = controlador.sumarRegistros(null, null, fecha1, null);

            series1.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getPesadaGramos() / 1000);
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

        RecoleccionController controlador = new RecoleccionController();

        Recoleccion sumarRegistros;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(ano4, 0, 1));
        for (int i = 0; i < 52; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 6);
            Date fecha2 = cal.getTime();
            sumarRegistros = controlador.sumarRegistros(null, null, fecha1, fecha2);

            series1.set(i + 1, sumarRegistros.getPesadaGramos() / 1000);

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
        List<Modulo> modulos = new ModuloController().getItems();
        ChartSeries[] series = new ChartSeries[modulos.size()];
        for (int modulo = 0; modulo < modulos.size(); modulo++) {
            series[modulo] = new ChartSeries();
        }
        RecoleccionController controlador = new RecoleccionController();

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

            for (int modulo = 0; modulo < modulos.size(); modulo++) {
                valor = controlador.sumarRegistros(null, modulos.get(modulo), fecha1, fecha2).getPesadaGramos() / 1000;
                series[modulo].set(i, valor);
            }
        }
        for (int l = 0; l < modulos.size(); l++) {
            series[l].setLabel(modulos.get(l).toString());
            modelo5.addSeries(series[l]);
        }

        modelo5.getAxes().put(AxisType.X, new CategoryAxis("Módulo"));
        modelo5.setTitle("Recolección por Módulo");
        modelo5.setLegendPosition("e");
        Axis yAxis = modelo5.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }

}
