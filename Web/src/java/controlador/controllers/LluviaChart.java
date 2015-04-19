/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.controllers;

import controlador.controllers.LluviaController;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import model.util.DateTools;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "lluviaChart")
public class LluviaChart implements Serializable {

    private LineChartModel modelo1;
    private int ano1;
    private int mes1;

    @PostConstruct
    public void init() {
        ano1 = DateTools.getYear();
        mes1 = DateTools.getMonth();
        createModel1();
    }

    public int getAno1() {
        return ano1;
    }

    public void setAno1(int ano1) {
        this.ano1 = ano1;
    }

    public int getMes1() {
        return mes1;
    }

    public void setMes1(int mes1) {
        this.mes1 = mes1;
    }

    public LineChartModel getModelo1() {
        return modelo1;
    }

    public void createModel1() {
        modelo1 = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Lluvias");
        LluviaController controlador = new LluviaController();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(ano1, mes1, 1));
        for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            series1.set(i + 1, controlador.calcularPromedio(fecha1));
        }

        modelo1.addSeries(series1);
        modelo1.setShowPointLabels(true);
        modelo1.getAxes().put(AxisType.X, new CategoryAxis("Día"));
        modelo1.setTitle("Promedio de Lluvias por Mes " + DateTools.getMonth(mes1) + " de " + ano1);
        modelo1.setLegendPosition("e");
        Axis yAxis = modelo1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(40);
    }
}
