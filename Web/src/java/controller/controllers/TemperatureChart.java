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
    private int ano1;
    private int mes;
    private int anoMes;
    private Date fechaSemana;
    private Date fechaDia;

    @PostConstruct
    public void init() {
        ano1 = anoMes = DateTools.getYear();
        mes = DateTools.getMonth();
        fechaSemana = DateTools.getDate();
        fechaDia = DateTools.getDate();
        createModel1();
        createModel2();
        createModel3();
        createModel4();
    }

    public LineChartModel getModel1() {
        return model1;
    }

    public int getAno1() {
        return ano1;
    }

    public void setAno1(int ano1) {
        this.ano1 = ano1;
    }

    public LineChartModel getModel2() {
        return model2;
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

    public LineChartModel getModel3() {
        return model3;
    }

    public Date getFechaDia() {
        return fechaDia;
    }

    public void setFechaDia(Date fechaDia) {
        this.fechaDia = fechaDia;
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
        series1.setLabel("Temperature - Día");
        series2.setLabel("SoilMoisture - Día");
        series3.setLabel("Punto De Rocío - Día");
        series4.setLabel("Temperature - Noche");
        series5.setLabel("SoilMoisture - Noche");
        series6.setLabel("Punto De Rocío - Noche");
        TemperatureController controller = new TemperatureController();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(ano1, 0, 1));
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
        model1.setTitle("Mean de Temperature por Mes - Año " + ano1);
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
        series1.setLabel("Temperature - Día");
        series2.setLabel("SoilMoisture - Día");
        series3.setLabel("Punto De Rocío - Día");
        series4.setLabel("Temperature - Noche");
        series5.setLabel("SoilMoisture - Noche");
        series6.setLabel("Punto De Rocío - Noche");

        TemperatureController controller = new TemperatureController();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(anoMes, mes, 1));

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
        model2.setTitle("Mean de Temperature por Día " + DateTools.getMonth(mes) + " de " + anoMes);
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
        series1.setLabel("Temperature - Día");
        series2.setLabel("SoilMoisture - Día");
        series3.setLabel("Punto De Rocío - Día");
        series4.setLabel("Temperature - Noche");
        series5.setLabel("SoilMoisture - Noche");
        series6.setLabel("Punto De Rocío - Noche");

        TemperatureController controller = new TemperatureController();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getFirstDayOfWeek(fechaSemana));

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
        model3.setTitle("Mean de Temperature " + DateTools.getWeek(fechaSemana));
        model3.setLegendPosition("e");
    }

    public void createModel4() {
        model4 = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();

        series1.setLabel("Temperature");
        series2.setLabel("SoilMoisture");
        series3.setLabel("Punto De Rocío");

        TemperatureController controller = new TemperatureController();

        for (int i = 0; i < 24; i++) {
            Temperature meanTemp;
            meanTemp = controller.calcularMean(fechaDia, i);

            series1.set(i + 1, meanTemp.getTemperature());
            series2.set(i + 1, meanTemp.getHumidity());
            series3.set(i + 1, meanTemp.getDewPoint());
        }

        model4.addSeries(series1);
        model4.addSeries(series2);
        model4.addSeries(series3);
        model4.setShowPointLabels(true);
        model4.getAxes().put(AxisType.X, new CategoryAxis("Hora"));
        model4.setTitle("Mean de Temperature " + DateFormatter.formatDate(fechaDia));
        model4.setLegendPosition("e");
    }

}
