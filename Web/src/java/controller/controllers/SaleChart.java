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
import model.finances.incomes.Sale;
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
@ManagedBean(name = "saleChart")
public class SaleChart {

    private LineChartModel model1;
    private LineChartModel model2;
    private LineChartModel model3;
    private LineChartModel model4;
    private BarChartModel model5;
    private int year1;
    private int year2;
    private int year4;
    private int month2;
    private Date date3;
    private Person customer1;
    private Person customer2;
    private Person customer3;
    private Person customer4;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    @PostConstruct
    public void init() {
        year1 = year2 = year4 = DateTools.getYear();
        month2 = DateTools.getMonth();
        date3 = DateTools.getDate();
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

    public int getYear1() {
        return year1;
    }

    public void setYear1(int year1) {
        this.year1 = year1;
    }

    public int getYear2() {
        return year2;
    }

    public void setYear2(int year2) {
        this.year2 = year2;
    }

    public int getYear4() {
        return year4;
    }

    public void setYear4(int year4) {
        this.year4 = year4;
    }

    public int getMonth2() {
        return month2;
    }

    public void setMonth2(int mes2) {
        this.month2 = mes2;
    }

    public Date getDate3() {
        return date3;
    }

    public void setDate3(Date date3) {
        this.date3 = date3;
    }

    public Person getCustomer1() {
        return customer1;
    }

    public void setCustomer1(Person customer1) {
        this.customer1 = customer1;
    }

    public Person getCustomer2() {
        return customer2;
    }

    public void setCustomer2(Person customer2) {
        this.customer2 = customer2;
    }

    public Person getCustomer3() {
        return customer3;
    }

    public void setCustomer3(Person customer3) {
        this.customer3 = customer3;
    }

    public Person getCustomer4() {
        return customer4;
    }

    public void setCustomer4(Person customer4) {
        this.customer4 = customer4;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
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

        SaleController controller = new SaleController();

        Sale sumRegistries;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(year1, 0, 1));
        for (int i = 0; i < 12; i++) {
            Date date1 = cal.getTime();
            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            Date date2 = cal.getTime();
            sumRegistries = controller.sumRegistries(customer1, date1, date2);
            String month = DateTools.getMonth(i);
            if (option1 != null && option1.equals("cantidad")) {
                series1.set(month, sumRegistries.getExtraGrams());
                series2.set(month, sumRegistries.getFirstGrams());
                series3.set(month, sumRegistries.getSecondGrams());
                series4.set(month, sumRegistries.getThirdGrams());
                series5.set(month, sumRegistries.getFourthGrams());
                series6.set(month, sumRegistries.getFifthGrams());
                series7.set(month, sumRegistries.getTotalQuality());
            } else {
                series1.set(month, sumRegistries.getExtraTotalPrice());
                series2.set(month, sumRegistries.getFirstTotalPrice());
                series3.set(month, sumRegistries.getSecondTotalPrice());
                series4.set(month, sumRegistries.getThirdTotalPrice());
                series5.set(month, sumRegistries.getFourthTotalPrice());
                series6.set(month, sumRegistries.getFifthTotalPrice());
                series7.set(month, sumRegistries.getSaleTotal());
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
        model1.setTitle("Venta por Mes Año " + year1);
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

        SaleController controller = new SaleController();

        Sale sumRegistries;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(year2, month2, 1));
        for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            Date date1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            Date date2 = cal.getTime();
            sumRegistries = controller.sumRegistries(customer2, date1, date2);

            if (option2 != null && option2.equals("cantidad")) {
                series1.set(i + 1, sumRegistries.getExtraGrams());
                series2.set(i + 1, sumRegistries.getFirstGrams());
                series3.set(i + 1, sumRegistries.getSecondGrams());
                series4.set(i + 1, sumRegistries.getThirdGrams());
                series5.set(i + 1, sumRegistries.getFourthGrams());
                series6.set(i + 1, sumRegistries.getFifthGrams());
                series7.set(i + 1, sumRegistries.getTotalQuality());
            } else {
                series1.set(i + 1, sumRegistries.getExtraTotalPrice());
                series2.set(i + 1, sumRegistries.getFirstTotalPrice());
                series3.set(i + 1, sumRegistries.getSecondTotalPrice());
                series4.set(i + 1, sumRegistries.getThirdTotalPrice());
                series5.set(i + 1, sumRegistries.getFourthTotalPrice());
                series6.set(i + 1, sumRegistries.getFifthTotalPrice());
                series7.set(i + 1, sumRegistries.getSaleTotal());
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
        model2.setTitle("Venta por Día " + DateTools.getMonth(month2) + " de " + year2);
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

        SaleController controller = new SaleController();

        Sale sumRegistries;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getFirstDayOfWeek(date3));
        for (int i = 0; i < 7; i++) {
            Date date1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            Date date2 = cal.getTime();
            sumRegistries = controller.sumRegistries(customer3, date1, date2);

            if (option3 != null && option3.equals("cantidad")) {
                series1.set(DateTools.getDayOfWeek(i + 1), sumRegistries.getExtraGrams());
                series2.set(DateTools.getDayOfWeek(i + 1), sumRegistries.getFirstGrams());
                series3.set(DateTools.getDayOfWeek(i + 1), sumRegistries.getSecondGrams());
                series4.set(DateTools.getDayOfWeek(i + 1), sumRegistries.getThirdGrams());
                series5.set(DateTools.getDayOfWeek(i + 1), sumRegistries.getFourthGrams());
                series6.set(DateTools.getDayOfWeek(i + 1), sumRegistries.getFifthGrams());
                series7.set(DateTools.getDayOfWeek(i + 1), sumRegistries.getTotalQuality());
            } else {
                series1.set(DateTools.getDayOfWeek(i + 1), sumRegistries.getExtraTotalPrice());
                series2.set(DateTools.getDayOfWeek(i + 1), sumRegistries.getFirstTotalPrice());
                series3.set(DateTools.getDayOfWeek(i + 1), sumRegistries.getSecondTotalPrice());
                series4.set(DateTools.getDayOfWeek(i + 1), sumRegistries.getThirdTotalPrice());
                series5.set(DateTools.getDayOfWeek(i + 1), sumRegistries.getFourthTotalPrice());
                series6.set(DateTools.getDayOfWeek(i + 1), sumRegistries.getFifthTotalPrice());
                series7.set(DateTools.getDayOfWeek(i + 1), sumRegistries.getSaleTotal());
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
        model3.setTitle("Venta por Día " + DateTools.getWeek(date3));
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

        SaleController controller = new SaleController();

        Sale sumRegistries;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(year4, 0, 1));
        for (int i = 0; i < 52; i++) {
            Date date1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 6);
            Date date2 = cal.getTime();
            sumRegistries = controller.sumRegistries(customer4, date1, date2);
            if (option4 != null && option4.equals("cantidad")) {
                series1.set(i + 1, sumRegistries.getExtraGrams());
                series2.set(i + 1, sumRegistries.getFirstGrams());
                series3.set(i + 1, sumRegistries.getSecondGrams());
                series4.set(i + 1, sumRegistries.getThirdGrams());
                series5.set(i + 1, sumRegistries.getFourthGrams());
                series6.set(i + 1, sumRegistries.getFifthGrams());
                series7.set(i + 1, sumRegistries.getTotalQuality());
            } else {
                series1.set(i + 1, sumRegistries.getExtraTotalPrice());
                series2.set(i + 1, sumRegistries.getFirstTotalPrice());
                series3.set(i + 1, sumRegistries.getSecondTotalPrice());
                series4.set(i + 1, sumRegistries.getThirdTotalPrice());
                series5.set(i + 1, sumRegistries.getFourthTotalPrice());
                series6.set(i + 1, sumRegistries.getFifthTotalPrice());
                series7.set(i + 1, sumRegistries.getSaleTotal());
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
        model4.setTitle("Venta por Semana Año " + year4);
        model4.setLegendPosition("e");
        Axis yAxis = model4.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }
}
