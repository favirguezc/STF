/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controllers;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import model.weather.SoilMoisture;
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
@ManagedBean(name = "soilMoistureChart")
public class SoilMoistureChart implements Serializable {

    private LineChartModel model1;
    private int year1;
    private int month1;

    @PostConstruct
    public void init() {
        year1 = DateTools.getYear();
        month1 = DateTools.getMonth();
        createModel1();
    }

    public int getYear1() {
        return year1;
    }

    public void setYear1(int year1) {
        this.year1 = year1;
    }

    public int getMonth1() {
        return month1;
    }

    public void setMonth1(int month1) {
        this.month1 = month1;
    }

    public LineChartModel getModel1() {
        return model1;
    }

    public void createModel1() {
        model1 = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        series1.setLabel("Humedad del Suelo 15 cms");
        series2.setLabel("Humedad del Suelo 30 cms");
        SoilMoistureController controller = new SoilMoistureController();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(year1, month1, 1));
        for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            SoilMoisture mean = controller.getMean(cal.getTime());
            series1.set(i + 1, mean.getValueIn15Centimeters());
            series2.set(i + 1, mean.getValueIn30Centimeters());
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        model1.addSeries(series1);
        model1.addSeries(series2);
        model1.setShowPointLabels(true);
        model1.getAxes().put(AxisType.X, new CategoryAxis("Día"));
        model1.setTitle("Promedio de Humedad del Suelo por Mes " + DateTools.getMonth(month1) + " de " + year1);
        model1.setLegendPosition("e");
        Axis yAxis = model1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(40);
    }
}