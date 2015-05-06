/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controllers;

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
@ManagedBean(name = "rainFallChart")
public class RainFallChart implements Serializable {

    private LineChartModel model;
    private int year;
    private int month;

    @PostConstruct
    public void init() {
        year = DateTools.getYear();
        month = DateTools.getMonth();
        createModel();
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

    public void createModel() {
        model = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Lluvia");
        RainFallController controller = new RainFallController();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(year, month, 1));
        for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            Date date = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            series1.set(i + 1, controller.calcularMean(date));
        }

        model.addSeries(series1);
        model.setShowPointLabels(true);
        model.getAxes().put(AxisType.X, new CategoryAxis("Día"));
        model.setTitle("Promedio de Lluvia por Mes " + DateTools.getMonth(month) + " de " + year);
        model.setLegendPosition("e");
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(40);
    }
}
