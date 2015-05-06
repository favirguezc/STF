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
import model.weather.Temperature;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import model.util.DateFormatter;
import model.util.DateTools;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "temperatureChart")
public class TemperatureChart implements Serializable {

    private LineChartModel model1;
    private LineChartModel model2;
    private LineChartModel model3;
    private LineChartModel model4;
    private int year1;
    private int month;
    private int yearMonth;
    private Date dateWeek;
    private Date dateDay;

    @PostConstruct
    public void init() {
        year1 = yearMonth = DateTools.getYear();
        month = DateTools.getMonth();
        dateWeek = DateTools.getDate();
        dateDay = DateTools.getDate();
        createModel1();
        createModel2();
        createModel3();
        createModel4();
    }

    public LineChartModel getModel1() {
        return model1;
    }

    public int getYear1() {
        return year1;
    }

    public void setYear1(int year1) {
        this.year1 = year1;
    }

    public LineChartModel getModel2() {
        return model2;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(int yearMonth) {
        this.yearMonth = yearMonth;
    }

    public Date getDateWeek() {
        return dateWeek;
    }

    public void setDateWeek(Date dateWeek) {
        this.dateWeek = dateWeek;
    }

    public LineChartModel getModel3() {
        return model3;
    }

    public Date getDateDay() {
        return dateDay;
    }

    public void setDateDay(Date dateDay) {
        this.dateDay = dateDay;
    }

    public LineChartModel getModel4() {
        return model4;
    }

    public void createModel1() {
        model1 = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();
        LineChartSeries series4 = new LineChartSeries();
        LineChartSeries series5 = new LineChartSeries();
        LineChartSeries series6 = new LineChartSeries();
        series1.setLabel("Temperatura - Día");
        series2.setLabel("Humedad del Suelo - Día");
        series3.setLabel("Punto De Rocío - Día");
        series4.setLabel("Temperatura - Noche");
        series5.setLabel("Humedad del Suelo - Noche");
        series6.setLabel("Punto De Rocío - Noche");
        TemperatureController controller = new TemperatureController();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(year1, 0, 1));
        for (int i = 0; i < 12; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            Date fecha2 = cal.getTime();
            Temperature meanTemp;
            meanTemp = controller.calcularMean(fecha1, fecha2, 2);
            series1.set(DateTools.getMonth(i), meanTemp.getTemperature());
            series2.set(DateTools.getMonth(i), meanTemp.getHumidity());
            series3.set(DateTools.getMonth(i), meanTemp.getDewPoint());

            meanTemp = controller.calcularMean(fecha1, fecha2, 1);
            series4.set(DateTools.getMonth(i), meanTemp.getTemperature());
            series5.set(DateTools.getMonth(i), meanTemp.getHumidity());
            series6.set(DateTools.getMonth(i), meanTemp.getDewPoint());

            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        model1.addSeries(series1);
        model1.addSeries(series2);
        model1.addSeries(series3);
        model1.addSeries(series4);
        model1.addSeries(series5);
        model1.addSeries(series6);
        model1.setShowPointLabels(true);
        model1.getAxes().put(AxisType.X, new CategoryAxis("Mes"));
        model1.setTitle("Media de Temperatura por Mes - Año " + year1);
        model1.setLegendPosition("e");
    }

    public void createModel2() {
        model2 = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();
        LineChartSeries series4 = new LineChartSeries();
        LineChartSeries series5 = new LineChartSeries();
        LineChartSeries series6 = new LineChartSeries();
        series1.setLabel("Temperatura - Día");
        series2.setLabel("Humedad del Suelo - Día");
        series3.setLabel("Punto De Rocío - Día");
        series4.setLabel("Temperatura - Noche");
        series5.setLabel("Humedad del Suelo - Noche");
        series6.setLabel("Punto De Rocío - Noche");

        TemperatureController controller = new TemperatureController();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(yearMonth, month, 1));

        for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            Temperature meanTemp;
            meanTemp = controller.calcularMean(fecha1, fecha1, 2);

            series1.set(i + 1, meanTemp.getTemperature());
            series2.set(i + 1, meanTemp.getHumidity());
            series3.set(i + 1, meanTemp.getDewPoint());

            meanTemp = controller.calcularMean(fecha1, fecha1, 1);

            series4.set(i + 1, meanTemp.getTemperature());
            series5.set(i + 1, meanTemp.getHumidity());
            series6.set(i + 1, meanTemp.getDewPoint());
        }

        model2.addSeries(series1);
        model2.addSeries(series2);
        model2.addSeries(series3);
        model2.addSeries(series4);
        model2.addSeries(series5);
        model2.addSeries(series6);
        model2.setShowPointLabels(true);
        model2.getAxes().put(AxisType.X, new CategoryAxis("Día"));
        model2.setTitle("Promedio de Temperatura por Día " + DateTools.getMonth(month) + " de " + yearMonth);
        model2.setLegendPosition("e");
    }

    public void createModel3() {
        model3 = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();
        LineChartSeries series4 = new LineChartSeries();
        LineChartSeries series5 = new LineChartSeries();
        LineChartSeries series6 = new LineChartSeries();
        series1.setLabel("Temperatura - Día");
        series2.setLabel("Humedad del Suelo - Día");
        series3.setLabel("Punto De Rocío - Día");
        series4.setLabel("Temperatura - Noche");
        series5.setLabel("Humedad del Suelo - Noche");
        series6.setLabel("Punto De Rocío - Noche");

        TemperatureController controller = new TemperatureController();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getFirstDayOfWeek(dateWeek));

        for (int i = 0; i < 7; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            Temperature meanTemp;
            meanTemp = controller.calcularMean(fecha1, fecha1, 2);

            series1.set(DateTools.getDayOfWeek(i + 1), meanTemp.getTemperature());
            series2.set(DateTools.getDayOfWeek(i + 1), meanTemp.getHumidity());
            series3.set(DateTools.getDayOfWeek(i + 1), meanTemp.getDewPoint());

            meanTemp = controller.calcularMean(fecha1, fecha1, 1);

            series4.set(DateTools.getDayOfWeek(i + 1), meanTemp.getTemperature());
            series5.set(DateTools.getDayOfWeek(i + 1), meanTemp.getHumidity());
            series6.set(DateTools.getDayOfWeek(i + 1), meanTemp.getDewPoint());
        }

        model3.addSeries(series1);
        model3.addSeries(series2);
        model3.addSeries(series3);
        model3.addSeries(series4);
        model3.addSeries(series5);
        model3.addSeries(series6);
        model3.setShowPointLabels(true);
        model3.getAxes().put(AxisType.X, new CategoryAxis("Día"));
        model3.setTitle("Promedio de Temperatura " + DateTools.getWeek(dateWeek));
        model3.setLegendPosition("e");
    }

    public void createModel4() {
        model4 = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();

        series1.setLabel("Temperatura");
        series2.setLabel("Humedad del Suelo");
        series3.setLabel("Punto De Rocío");

        TemperatureController controller = new TemperatureController();

        for (int i = 0; i < 24; i++) {
            Temperature meanTemp;
            meanTemp = controller.calcularMean(dateDay, i);

            series1.set(i, meanTemp.getTemperature());
            series2.set(i, meanTemp.getHumidity());
            series3.set(i, meanTemp.getDewPoint());
        }

        model4.addSeries(series1);
        model4.addSeries(series2);
        model4.addSeries(series3);
        model4.setShowPointLabels(true);
        model4.getAxes().put(AxisType.X, new CategoryAxis("Hora"));
        model4.setTitle("Promedio de Temperatura " + DateFormatter.formatDate(dateDay));
        model4.setLegendPosition("e");
    }

}
