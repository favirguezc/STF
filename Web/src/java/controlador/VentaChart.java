/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import modelo.finanzas.ventas.Venta;
import modelo.produccion.administracion.Persona;
import modelo.util.DateTools;
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
    private Persona cliente1;
    private Persona cliente2;
    private Persona cliente3;
    private Persona cliente4;
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

    public LineChartModel getModelo1() {
        return modelo1;
    }

    public void setModelo1(LineChartModel modelo1) {
        this.modelo1 = modelo1;
    }

    public LineChartModel getModelo2() {
        return modelo2;
    }

    public void setModelo2(LineChartModel modelo2) {
        this.modelo2 = modelo2;
    }

    public LineChartModel getModelo3() {
        return modelo3;
    }

    public void setModelo3(LineChartModel modelo3) {
        this.modelo3 = modelo3;
    }

    public LineChartModel getModelo4() {
        return modelo4;
    }

    public void setModelo4(LineChartModel modelo4) {
        this.modelo4 = modelo4;
    }

    public BarChartModel getModelo5() {
        return modelo5;
    }

    public void setModelo5(BarChartModel modelo5) {
        this.modelo5 = modelo5;
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

    public Persona getCliente1() {
        return cliente1;
    }

    public void setCliente1(Persona cliente1) {
        this.cliente1 = cliente1;
    }

    public Persona getCliente2() {
        return cliente2;
    }

    public void setCliente2(Persona cliente2) {
        this.cliente2 = cliente2;
    }

    public Persona getCliente3() {
        return cliente3;
    }

    public void setCliente3(Persona cliente3) {
        this.cliente3 = cliente3;
    }

    public Persona getCliente4() {
        return cliente4;
    }

    public void setCliente4(Persona cliente4) {
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
        modelo1 = new LineChartModel();
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

        VentaController controlador = new VentaController();

        Venta sumarRegistros;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(ano1, 0, 1));
        for (int i = 0; i < 12; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            Date fecha2 = cal.getTime();
            sumarRegistros = controlador.sumarRegistros(cliente1, fecha1, fecha2);
            String mes = DateTools.getMonth(i);
            if (opcion1 != null && opcion1.equals("cantidad")) {
                series1.set(mes, sumarRegistros.getExtraGramos());
                series2.set(mes, sumarRegistros.getPrimeraGramos());
                series3.set(mes, sumarRegistros.getSegundaGramos());
                series4.set(mes, sumarRegistros.getTerceraGramos());
                series5.set(mes, sumarRegistros.getCuartaGramos());
                series6.set(mes, sumarRegistros.getQuintaGramos());
                series7.set(mes, sumarRegistros.getCantidadTotal());
            } else {
                series1.set(mes, sumarRegistros.getExtraPrecioTotal());
                series2.set(mes, sumarRegistros.getPrimeraPrecioTotal());
                series3.set(mes, sumarRegistros.getSegundaPrecioTotal());
                series4.set(mes, sumarRegistros.getTerceraPrecioTotal());
                series5.set(mes, sumarRegistros.getCuartaPrecioTotal());
                series6.set(mes, sumarRegistros.getQuintaPrecioTotal());
                series7.set(mes, sumarRegistros.getVentaTotal());
            }
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        modelo1.addSeries(series1);
        modelo1.addSeries(series2);
        modelo1.addSeries(series3);
        modelo1.addSeries(series4);
        modelo1.addSeries(series5);
        modelo1.addSeries(series6);
        modelo1.addSeries(series7);
        modelo1.setShowPointLabels(true);
        modelo1.getAxes().put(AxisType.X, new CategoryAxis("Mes"));
        modelo1.setTitle("Venta por Mes Año " + ano1);
        modelo1.setLegendPosition("e");
        Axis yAxis = modelo1.getAxis(AxisType.Y);
        yAxis.setMin(0);

    }

    public void createModel2() {
        modelo2 = new LineChartModel();
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

        VentaController controlador = new VentaController();

        Venta sumarRegistros;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(ano2, mes2, 1));
        for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            Date fecha2 = cal.getTime();
            sumarRegistros = controlador.sumarRegistros(cliente2, fecha1, fecha2);

            if (opcion2 != null && opcion2.equals("cantidad")) {
                series1.set(i + 1, sumarRegistros.getExtraGramos());
                series2.set(i + 1, sumarRegistros.getPrimeraGramos());
                series3.set(i + 1, sumarRegistros.getSegundaGramos());
                series4.set(i + 1, sumarRegistros.getTerceraGramos());
                series5.set(i + 1, sumarRegistros.getCuartaGramos());
                series6.set(i + 1, sumarRegistros.getQuintaGramos());
                series7.set(i + 1, sumarRegistros.getCantidadTotal());
            } else {
                series1.set(i + 1, sumarRegistros.getExtraPrecioTotal());
                series2.set(i + 1, sumarRegistros.getPrimeraPrecioTotal());
                series3.set(i + 1, sumarRegistros.getSegundaPrecioTotal());
                series4.set(i + 1, sumarRegistros.getTerceraPrecioTotal());
                series5.set(i + 1, sumarRegistros.getCuartaPrecioTotal());
                series6.set(i + 1, sumarRegistros.getQuintaPrecioTotal());
                series7.set(i + 1, sumarRegistros.getVentaTotal());
            }
        }

        modelo2.addSeries(series1);
        modelo2.addSeries(series2);
        modelo2.addSeries(series3);
        modelo2.addSeries(series4);
        modelo2.addSeries(series5);
        modelo2.addSeries(series6);
        modelo2.addSeries(series7);
        modelo2.setShowPointLabels(true);
        modelo2.getAxes().put(AxisType.X, new CategoryAxis("Día"));
        modelo2.setTitle("Venta por Día " + DateTools.getMonth(mes2) + " de " + ano2);
        modelo2.setLegendPosition("e");
        Axis yAxis = modelo2.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }

    public void createModel3() {
        modelo3 = new LineChartModel();
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

        VentaController controlador = new VentaController();

        Venta sumarRegistros;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getFirstDayOfWeek(fecha3));
        for (int i = 0; i < 7; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            Date fecha2 = cal.getTime();
            sumarRegistros = controlador.sumarRegistros(cliente3, fecha1, fecha2);

            if (opcion3 != null && opcion3.equals("cantidad")) {
                series1.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getExtraGramos());
                series2.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getPrimeraGramos());
                series3.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getSegundaGramos());
                series4.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getTerceraGramos());
                series5.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getCuartaGramos());
                series6.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getQuintaGramos());
                series7.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getCantidadTotal());
            } else {
                series1.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getExtraPrecioTotal());
                series2.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getPrimeraPrecioTotal());
                series3.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getSegundaPrecioTotal());
                series4.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getTerceraPrecioTotal());
                series5.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getCuartaPrecioTotal());
                series6.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getQuintaPrecioTotal());
                series7.set(DateTools.getDayOfWeek(i + 1), sumarRegistros.getVentaTotal());
            }
        }

        modelo3.addSeries(series1);
        modelo3.addSeries(series2);
        modelo3.addSeries(series3);
        modelo3.addSeries(series4);
        modelo3.addSeries(series5);
        modelo3.addSeries(series6);
        modelo3.addSeries(series7);
        modelo3.setShowPointLabels(true);
        modelo3.getAxes().put(AxisType.X, new CategoryAxis("Día"));
        modelo3.setTitle("Venta por Día " + DateTools.getWeek(fecha3));
        modelo3.setLegendPosition("e");
        Axis yAxis = modelo3.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }

    public void createModel4() {
        modelo4 = new LineChartModel();
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

        VentaController controlador = new VentaController();

        Venta sumarRegistros;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getDate(ano4, 0, 1));
        for (int i = 0; i < 52; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 6);
            Date fecha2 = cal.getTime();
            sumarRegistros = controlador.sumarRegistros(cliente4, fecha1, fecha2);
            if (opcion4 != null && opcion4.equals("cantidad")) {
                series1.set(i + 1, sumarRegistros.getExtraGramos());
                series2.set(i + 1, sumarRegistros.getPrimeraGramos());
                series3.set(i + 1, sumarRegistros.getSegundaGramos());
                series4.set(i + 1, sumarRegistros.getTerceraGramos());
                series5.set(i + 1, sumarRegistros.getCuartaGramos());
                series6.set(i + 1, sumarRegistros.getQuintaGramos());
                series7.set(i + 1, sumarRegistros.getCantidadTotal());
            } else {
                series1.set(i + 1, sumarRegistros.getExtraPrecioTotal());
                series2.set(i + 1, sumarRegistros.getPrimeraPrecioTotal());
                series3.set(i + 1, sumarRegistros.getSegundaPrecioTotal());
                series4.set(i + 1, sumarRegistros.getTerceraPrecioTotal());
                series5.set(i + 1, sumarRegistros.getCuartaPrecioTotal());
                series6.set(i + 1, sumarRegistros.getQuintaPrecioTotal());
                series7.set(i + 1, sumarRegistros.getVentaTotal());
            }

            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        modelo4.addSeries(series1);
        modelo4.addSeries(series2);
        modelo4.addSeries(series3);
        modelo4.addSeries(series4);
        modelo4.addSeries(series5);
        modelo4.addSeries(series6);
        modelo4.addSeries(series7);
        modelo4.setShowPointLabels(true);
        modelo4.getAxes().put(AxisType.X, new CategoryAxis("Semana"));
        modelo4.setTitle("Venta por Semana Año " + ano4);
        modelo4.setLegendPosition("e");
        Axis yAxis = modelo4.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }
}
