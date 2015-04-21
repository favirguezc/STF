/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import model.finances.sales.Sale;
import model.administration.Person;
import model.util.DateTools;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author John Fredy
 */
@ManagedBean(name = "ventaChart")
public class VentaChart {

    private LineChartModel model1;
    private LineChartModel model2;
    private LineChartModel model3;
    private LineChartModel model4;
    private BarChartModel model5;
    private int ano1;
    private int ano2;
    private int ano4;
    private int mes2;
    private Date fecha3;
    private Person cliente1;
    private Person cliente2;
    private Person cliente3;
    private Person cliente4;
    private String opcion1;
    private String opcion2;
    private String opcion3;
    private String opcion4;

    @PostConstruct
    public void init() {
        ano1 = ano2 = ano4 = DateTools.getYear();
        mes2 = DateTools.getMonth();
        fecha3 = DateTools.getDate();
        createModel1();
        createModel2();
        createModel3();
        createModel4();
    }

    public LineChartModel getModel1() {
        return model1;
    }

    public void setModel1(LineChartModel model1) {
        this.model1 = model1;
    }

    public LineChartModel getModel2() {
        return model2;
    }

    public void setModel2(LineChartModel model2) {
        this.model2 = model2;
    }

    public LineChartModel getModel3() {
        return model3;
    }

    public void setModel3(LineChartModel model3) {
        this.model3 = model3;
    }

    public LineChartModel getModel4() {
        return model4;
    }

    public void setModel4(LineChartModel model4) {
        this.model4 = model4;
    }

    public BarChartModel getModel5() {
        return model5;
    }

    public void setModel5(BarChartModel model5) {
        this.model5 = model5;
    }

    public int getAno1() {
        return ano1;
    }

    public void setAno1(int ano1) {
        this.ano1 = ano1;
    }

    public int getAno2() {
        return ano2;
    }

    public void setAno2(int ano2) {
        this.ano2 = ano2;
    }

    public int getAno4() {
        return ano4;
    }

    public void setAno4(int ano4) {
        this.ano4 = ano4;
    }

    public int getMes2() {
        return mes2;
    }

    public void setMes2(int mes2) {
        this.mes2 = mes2;
    }

    public Date getFecha3() {
        return fecha3;
    }

    public void setFecha3(Date fecha3) {
        this.fecha3 = fecha3;
    }

    public Person getCliente1() {
        return cliente1;
    }

    public void setCliente1(Person cliente1) {
        this.cliente1 = cliente1;
    }

    public Person getCliente2() {
        return cliente2;
    }

    public void setCliente2(Person cliente2) {
        this.cliente2 = cliente2;
    }

    public Person getCliente3() {
        return cliente3;
    }

    public void setCliente3(Person cliente3) {
        this.cliente3 = cliente3;
    }

    public Person getCliente4() {
        return cliente4;
    }

    public void setCliente4(Person cliente4) {
        this.cliente4 = cliente4;
    }

    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public String getOpcion4() {
        return opcion4;
    }

    public void setOpcion4(String opcion4) {
        this.opcion4 = opcion4;
    }

    public void createModel1() {
        model1 = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();
        LineChartSeries series4 = new LineChartSeries();
        LineChartSeries series5 = new LineChartSeries();
        LineChartSeries series6 = new LineChartSeries();
        LineChartSeries series7 = new LineChartSeries();
        series1.setLabel("Extra");
        series2.setLabel("Primera");
        series3.setLabel("Segunda");
        series4.setLabel("Tercera");
        series5.setLabel("Cuarta");
        series6.setLabel("Quinta");
        series7.setLabel("Total");

        VentaController controller = new VentaController();

        Sale sumarRegistros;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(ano1, 0, 1));
        for (int i = 0; i < 12; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            Date fecha2 = cal.getTime();
            sumarRegistros = controller.sumarRegistros(cliente1, fecha1, fecha2);
            String mes = DateTools.getMonth(i);
            if (opcion1 != null && opcion1.equals("cantidad")) {
                series1.set(mes, sumarRegistros.getExtraGrams());
                series2.set(mes, sumarRegistros.getFirstGrams());
                series3.set(mes, sumarRegistros.getSecondGrams());
                series4.set(mes, sumarRegistros.getThirdGrams());
                series5.set(mes, sumarRegistros.getFourthGrams());
                series6.set(mes, sumarRegistros.getFifthGrams());
                series7.set(mes, sumarRegistros.getTotalQuality());
            } else {
                series1.set(mes, sumarRegistros.getExtraTotalPrice());
                series2.set(mes, sumarRegistros.getFirstTotalPrice());
                series3.set(mes, sumarRegistros.getSecondTotalPrice());
                series4.set(mes, sumarRegistros.getThirdTotalPrice());
                series5.set(mes, sumarRegistros.getFourthTotalPrice());
                series6.set(mes, sumarRegistros.getFifthTotalPrice());
                series7.set(mes, sumarRegistros.getSaleTotal());
            }
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        model1.addSeries(series1);
        model1.addSeries(series2);
        model1.addSeries(series3);
        model1.addSeries(series4);
        model1.addSeries(series5);
        model1.addSeries(series6);
        model1.addSeries(series7);
        model1.setShowPointLabels(true);
        model1.getAxes().put(AxisType.X, new CategoryAxis("Mes"));
        model1.setTitle("Venta por Mes Año " + ano1);
        model1.setLegendPosition("e");
        Axis yAxis = model1.getAxis(AxisType.Y);
        yAxis.setMin(0);

    }

    public void createModel2() {
        model2 = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();
        LineChartSeries series4 = new LineChartSeries();
        LineChartSeries series5 = new LineChartSeries();
        LineChartSeries series6 = new LineChartSeries();
        LineChartSeries series7 = new LineChartSeries();
        series1.setLabel("Extra");
        series2.setLabel("Primera");
        series3.setLabel("Segunda");
        series4.setLabel("Tercera");
        series5.setLabel("Cuarta");
        series6.setLabel("Quinta");
        series7.setLabel("Total");

        VentaController controller = new VentaController();

        Sale sumarRegistros;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(ano2, mes2, 1));
        for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            Date fecha2 = cal.getTime();
            sumarRegistros = controller.sumarRegistros(cliente2, fecha1, fecha2);

            if (opcion2 != null && opcion2.equals("cantidad")) {
                series1.set(i + 1, sumarRegistros.getExtraGrams());
                series2.set(i + 1, sumarRegistros.getFirstGrams());
                series3.set(i + 1, sumarRegistros.getSecondGrams());
                series4.set(i + 1, sumarRegistros.getThirdGrams());
                series5.set(i + 1, sumarRegistros.getFourthGrams());
                series6.set(i + 1, sumarRegistros.getFifthGrams());
                series7.set(i + 1, sumarRegistros.getTotalQuality());
            } else {
                series1.set(i + 1, sumarRegistros.getExtraTotalPrice());
                series2.set(i + 1, sumarRegistros.getFirstTotalPrice());
                series3.set(i + 1, sumarRegistros.getSecondTotalPrice());
                series4.set(i + 1, sumarRegistros.getThirdTotalPrice());
                series5.set(i + 1, sumarRegistros.getFourthTotalPrice());
                series6.set(i + 1, sumarRegistros.getFifthTotalPrice());
                series7.set(i + 1, sumarRegistros.getSaleTotal());
            }
        }

        model2.addSeries(series1);
        model2.addSeries(series2);
        model2.addSeries(series3);
        model2.addSeries(series4);
        model2.addSeries(series5);
        model2.addSeries(series6);
        model2.addSeries(series7);
        model2.setShowPointLabels(true);
        model2.getAxes().put(AxisType.X, new CategoryAxis("Día"));
        model2.setTitle("Venta por Día " + DateTools.getMonth(mes2) + " de " + ano2);
        model2.setLegendPosition("e");
        Axis yAxis = model2.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }

    public void createModel3() {
        model3 = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();
        LineChartSeries series4 = new LineChartSeries();
        LineChartSeries series5 = new LineChartSeries();
        LineChartSeries series6 = new LineChartSeries();
        LineChartSeries series7 = new LineChartSeries();
        series1.setLabel("Extra");
        series2.setLabel("Primera");
        series3.setLabel("Segunda");
        series4.setLabel("Tercera");
        series5.setLabel("Cuarta");
        series6.setLabel("Quinta");
        series7.setLabel("Total");

        VentaController controller = new VentaController();

        Sale sumarRegistros;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getFirstDayOfWeek(fecha3));
        for (int i = 0; i < 7; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            Date fecha2 = cal.getTime();
            sumarRegistros = controller.sumarRegistros(cliente3, fecha1, fecha2);

            if (opcion3 != null && opcion3.equals("cantidad")) {
                series1.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getExtraGrams());
                series2.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getFirstGrams());
                series3.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getSecondGrams());
                series4.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getThirdGrams());
                series5.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getFourthGrams());
                series6.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getFifthGrams());
                series7.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getTotalQuality());
            } else {
                series1.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getExtraTotalPrice());
                series2.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getFirstTotalPrice());
                series3.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getSecondTotalPrice());
                series4.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getThirdTotalPrice());
                series5.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getFourthTotalPrice());
                series6.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getFifthTotalPrice());
                series7.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getSaleTotal());
            }
        }

        model3.addSeries(series1);
        model3.addSeries(series2);
        model3.addSeries(series3);
        model3.addSeries(series4);
        model3.addSeries(series5);
        model3.addSeries(series6);
        model3.addSeries(series7);
        model3.setShowPointLabels(true);
        model3.getAxes().put(AxisType.X, new CategoryAxis("Día"));
        model3.setTitle("Venta por Día " + DateTools.getWeek(fecha3));
        model3.setLegendPosition("e");
        Axis yAxis = model3.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }

    public void createModel4() {
        model4 = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();
        LineChartSeries series4 = new LineChartSeries();
        LineChartSeries series5 = new LineChartSeries();
        LineChartSeries series6 = new LineChartSeries();
        LineChartSeries series7 = new LineChartSeries();
        series1.setLabel("Extra");
        series2.setLabel("Primera");
        series3.setLabel("Segunda");
        series4.setLabel("Tercera");
        series5.setLabel("Cuarta");
        series6.setLabel("Quinta");
        series7.setLabel("Total");

        VentaController controller = new VentaController();

        Sale sumarRegistros;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(ano4, 0, 1));
        for (int i = 0; i < 52; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 6);
            Date fecha2 = cal.getTime();
            sumarRegistros = controller.sumarRegistros(cliente4, fecha1, fecha2);
            if (opcion4 != null && opcion4.equals("cantidad")) {
                series1.set(i + 1, sumarRegistros.getExtraGrams());
                series2.set(i + 1, sumarRegistros.getFirstGrams());
                series3.set(i + 1, sumarRegistros.getSecondGrams());
                series4.set(i + 1, sumarRegistros.getThirdGrams());
                series5.set(i + 1, sumarRegistros.getFourthGrams());
                series6.set(i + 1, sumarRegistros.getFifthGrams());
                series7.set(i + 1, sumarRegistros.getTotalQuality());
            } else {
                series1.set(i + 1, sumarRegistros.getExtraTotalPrice());
                series2.set(i + 1, sumarRegistros.getFirstTotalPrice());
                series3.set(i + 1, sumarRegistros.getSecondTotalPrice());
                series4.set(i + 1, sumarRegistros.getThirdTotalPrice());
                series5.set(i + 1, sumarRegistros.getFourthTotalPrice());
                series6.set(i + 1, sumarRegistros.getFifthTotalPrice());
                series7.set(i + 1, sumarRegistros.getSaleTotal());
            }

            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        model4.addSeries(series1);
        model4.addSeries(series2);
        model4.addSeries(series3);
        model4.addSeries(series4);
        model4.addSeries(series5);
        model4.addSeries(series6);
        model4.addSeries(series7);
        model4.setShowPointLabels(true);
        model4.getAxes().put(AxisType.X, new CategoryAxis("Semana"));
        model4.setTitle("Venta por Semana Año " + ano4);
        model4.setLegendPosition("e");
        Axis yAxis = model4.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }
}
