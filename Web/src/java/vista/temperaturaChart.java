/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.TemperaturaController;
import controlador.util.JsfUtil;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import modelo.variablesClimaticas.Temperatura;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import util.DateTools;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "temperaturaChart")
public class temperaturaChart implements Serializable {

    private LineChartModel modelo1;
    private LineChartModel modelo2;
    private int ano1;
    private int mes;
    private int ano2;

    @PostConstruct
    public void init() {
        ano1 = new Date().getYear() + 1900;
        ano2 = new Date().getYear() + 1900;
        mes = new Date().getMonth();
        createModel1();
        createModel2();
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

    public LineChartModel getModelo2() {
        return modelo2;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno2() {
        return ano2;
    }

    public void setAno2(int ano2) {
        this.ano2 = ano2;
    }

    public void createModel1() {
        modelo1 = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();
        series1.setLabel("Temperatura");
        series2.setLabel("Humedad");
        series3.setLabel("Punto De Rocío");
        TemperaturaController controlador = new TemperaturaController();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date(ano1 - 1900, 0, 1));
        for (int i = 0; i < 12; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            Date fecha2 = cal.getTime();
            Temperatura promedioTemp;
            promedioTemp = controlador.calcularPromedio(fecha1, fecha2);
            cal.add(Calendar.DAY_OF_MONTH, 1);

            series1.set(DateTools.getMes(i), promedioTemp.getTemperatura());
            series2.set(DateTools.getMes(i), promedioTemp.getHumedad());
            series3.set(DateTools.getMes(i), promedioTemp.getPuntoDeRocio());
        }

        modelo1.addSeries(series1);
        modelo1.addSeries(series2);
        modelo1.addSeries(series3);
        modelo1.setShowPointLabels(true);
        modelo1.getAxes().put(AxisType.X, new CategoryAxis("Mes"));
        modelo1.setTitle("Promedio de Temperatura por Mes Año " + ano1);
        modelo1.setLegendPosition("e");
        Axis yAxis = modelo1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(40);
    }

    public void createModel2() {
        modelo2 = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();

        series1.setLabel("Temperatura");
        series2.setLabel("Humedad");
        series3.setLabel("Punto De Rocío");

        TemperaturaController controlador = new TemperaturaController();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date(ano2 - 1900, mes, 1));

        for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            Date fecha2 = cal.getTime();
            Temperatura promedioTemp;
            promedioTemp = controlador.calcularPromedio(fecha1, fecha2);

            series1.set(i + 1, promedioTemp.getTemperatura());
            series2.set(i + 1, promedioTemp.getHumedad());
            series3.set(i + 1, promedioTemp.getPuntoDeRocio());
        }

        modelo2.addSeries(series1);
        modelo2.addSeries(series2);
        modelo2.addSeries(series3);
        modelo2.setShowPointLabels(true);
        modelo2.getAxes().put(AxisType.X, new CategoryAxis("Día"));
        modelo2.setTitle("Promedio de Temperatura por Día " + DateTools.getMes(mes) + " de " + ano2);
        modelo2.setLegendPosition("e");
        Axis yAxis = modelo1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(40);
    }
}
