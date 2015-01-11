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
import modelo.produccion.variablesClimaticas.Temperatura;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import util.DateFormatter;
import util.DateTools;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "temperaturaChart")
public class TemperaturaChart implements Serializable {

    private LineChartModel modelo1;
    private LineChartModel modelo2;
    private LineChartModel modelo3;
    private LineChartModel modelo4;
    private int ano1;
    private int mes;
    private int anoMes;
    private Date fechaSemana;
    private Date fechaDia;

    @PostConstruct
    public void init() {
        ano1 = new Date().getYear() + 1900;
        anoMes = new Date().getYear() + 1900;
        mes = new Date().getMonth();
        fechaSemana = new Date();
        fechaDia = new Date();
        createModel1();
        createModel2();
        createModel3();
        createModel4();
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

    public int getAnoMes() {
        return anoMes;
    }

    public void setAnoMes(int anoMes) {
        this.anoMes = anoMes;
    }

    public Date getFechaSemana() {
        return fechaSemana;
    }

    public void setFechaSemana(Date fechaSemana) {
        this.fechaSemana = fechaSemana;
    }

    public LineChartModel getModelo3() {
        return modelo3;
    }

    public Date getFechaDia() {
        return fechaDia;
    }

    public void setFechaDia(Date fechaDia) {
        this.fechaDia = fechaDia;
    }

    public LineChartModel getModelo4() {
        return modelo4;
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
        cal.setTime(new Date(anoMes - 1900, mes, 1));

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
        modelo2.setTitle("Promedio de Temperatura por Día " + DateTools.getMes(mes) + " de " + anoMes);
        modelo2.setLegendPosition("e");
    }

    public void createModel3() {
        modelo3 = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();

        series1.setLabel("Temperatura");
        series2.setLabel("Humedad");
        series3.setLabel("Punto De Rocío");

        TemperaturaController controlador = new TemperaturaController();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getPrimerDiaDeLaSemana(fechaSemana));

        for (int i = 0; i < 7; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            Date fecha2 = cal.getTime();
            Temperatura promedioTemp;
            promedioTemp = controlador.calcularPromedio(fecha1, fecha2);

            series1.set(DateTools.getDia(i + 1), promedioTemp.getTemperatura());
            series2.set(DateTools.getDia(i + 1), promedioTemp.getHumedad());
            series3.set(DateTools.getDia(i + 1), promedioTemp.getPuntoDeRocio());
        }

        modelo3.addSeries(series1);
        modelo3.addSeries(series2);
        modelo3.addSeries(series3);
        modelo3.setShowPointLabels(true);
        modelo3.getAxes().put(AxisType.X, new CategoryAxis("Día"));
        modelo3.setTitle("Promedio de Temperatura " + DateTools.getSemana(fechaSemana));
        modelo3.setLegendPosition("e");
    }

    public void createModel4() {
        modelo4 = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();

        series1.setLabel("Temperatura");
        series2.setLabel("Humedad");
        series3.setLabel("Punto De Rocío");

        TemperaturaController controlador = new TemperaturaController();

        for (int i = 0; i < 24; i++) {
            Temperatura promedioTemp;
            promedioTemp = controlador.calcularPromedio(fechaDia, i);

            series1.set(i + 1, promedioTemp.getTemperatura());
            series2.set(i + 1, promedioTemp.getHumedad());
            series3.set(i + 1, promedioTemp.getPuntoDeRocio());
        }

        modelo4.addSeries(series1);
        modelo4.addSeries(series2);
        modelo4.addSeries(series3);
        modelo4.setShowPointLabels(true);
        modelo4.getAxes().put(AxisType.X, new CategoryAxis("Hora"));
        modelo4.setTitle("Promedio de Temperatura " + DateFormatter.formatDate(fechaDia));
        modelo4.setLegendPosition("e");
    }

}
