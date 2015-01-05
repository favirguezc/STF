/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.LoteController;
import controlador.RecoleccionController;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import modelo.administracion.Lote;
import modelo.produccion.Recoleccion;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import util.DateTools;

/**
 *
 * @author fredy
 */
@ManagedBean(name = "recoleccionChart")
public class RecoleccionChart implements Serializable {

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

    @PostConstruct
    public void init() {
        ano1 = ano2 = ano4 = new Date().getYear() + 1900;
        mes2 = new Date().getMonth();
        fecha3 = new Date();
        createModel1();
        createModel2();
        createModel3();
        createModel4();
        createModel5();
    }

    public BarChartModel getModelo5() {
        return modelo5;
    }

    public LineChartModel getModelo4() {
        return modelo4;
    }

    public int getAno4() {
        return ano4;
    }

    public void setAno4(int ano4) {
        this.ano4 = ano4;
    }

    public LineChartModel getModelo3() {
        return modelo3;
    }

    public Date getFecha3() {
        return fecha3;
    }

    public void setFecha3(Date fecha3) {
        this.fecha3 = fecha3;
    }

    public LineChartModel getModelo2() {
        return modelo2;
    }

    public int getAno2() {
        return ano2;
    }

    public void setAno2(int ano2) {
        this.ano2 = ano2;
    }

    public int getMes2() {
        return mes2;
    }

    public void setMes2(int mes2) {
        this.mes2 = mes2;
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

    public void createModel1() {
        modelo1 = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();
        LineChartSeries series4 = new LineChartSeries();
        LineChartSeries series5 = new LineChartSeries();
        LineChartSeries series6 = new LineChartSeries();
        LineChartSeries series7 = new LineChartSeries();
        LineChartSeries series8 = new LineChartSeries();
        LineChartSeries series9 = new LineChartSeries();
        series1.setLabel("Extra");
        series2.setLabel("Primera");
        series3.setLabel("Segunda");
        series4.setLabel("Tercera");
        series5.setLabel("Cuarta");
        series6.setLabel("Quinta");
        series7.setLabel("Dañada");
        series8.setLabel("Buena");
        series9.setLabel("Total");

        RecoleccionController controlador = new RecoleccionController();

        Recoleccion sumarRegistros;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date(ano1 - 1900, 0, 1));
        for (int i = 0; i < 12; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            Date fecha2 = cal.getTime();
            sumarRegistros = controlador.sumarRegistros(null, null, fecha1, fecha2);
            String mes = DateTools.getMes(i);
            series1.set(mes, sumarRegistros.getExtraGramos() / 500);
            series2.set(mes, sumarRegistros.getPrimeraGramos() / 500);
            series3.set(mes, sumarRegistros.getSegundaGramos() / 500);
            series4.set(mes, sumarRegistros.getTerceraGramos() / 500);
            series5.set(mes, sumarRegistros.getCuartaGramos() / 500);
            series6.set(mes, sumarRegistros.getQuintaGramos() / 500);
            series7.set(mes, sumarRegistros.getDanadaGramos() / 500);
            series8.set(mes, sumarRegistros.getBuenaGramos() / 500);
            series9.set(mes, sumarRegistros.getTotalGramos() / 500);

            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        modelo1.addSeries(series1);
        modelo1.addSeries(series2);
        modelo1.addSeries(series3);
        modelo1.addSeries(series4);
        modelo1.addSeries(series5);
        modelo1.addSeries(series6);
        modelo1.addSeries(series7);
        modelo1.addSeries(series8);
        modelo1.addSeries(series9);
        modelo1.setShowPointLabels(true);
        modelo1.getAxes().put(AxisType.X, new CategoryAxis("Mes"));
        modelo1.setTitle("Recolección por Mes Año " + ano1);
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
        LineChartSeries series8 = new LineChartSeries();
        LineChartSeries series9 = new LineChartSeries();
        series1.setLabel("Extra");
        series2.setLabel("Primera");
        series3.setLabel("Segunda");
        series4.setLabel("Tercera");
        series5.setLabel("Cuarta");
        series6.setLabel("Quinta");
        series7.setLabel("Dañada");
        series8.setLabel("Buena");
        series9.setLabel("Total");

        RecoleccionController controlador = new RecoleccionController();

        Recoleccion sumarRegistros;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date(ano2 - 1900, mes2, 1));
        for (int i = 0; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            Date fecha2 = cal.getTime();
            sumarRegistros = controlador.sumarRegistros(null, null, fecha1, fecha2);

            series1.set(i + 1, sumarRegistros.getExtraGramos() / 500);
            series2.set(i + 1, sumarRegistros.getPrimeraGramos() / 500);
            series3.set(i + 1, sumarRegistros.getSegundaGramos() / 500);
            series4.set(i + 1, sumarRegistros.getTerceraGramos() / 500);
            series5.set(i + 1, sumarRegistros.getCuartaGramos() / 500);
            series6.set(i + 1, sumarRegistros.getQuintaGramos() / 500);
            series7.set(i + 1, sumarRegistros.getDanadaGramos() / 500);
            series8.set(i + 1, sumarRegistros.getBuenaGramos() / 500);
            series9.set(i + 1, sumarRegistros.getTotalGramos() / 500);
        }

        modelo2.addSeries(series1);
        modelo2.addSeries(series2);
        modelo2.addSeries(series3);
        modelo2.addSeries(series4);
        modelo2.addSeries(series5);
        modelo2.addSeries(series6);
        modelo2.addSeries(series7);
        modelo2.addSeries(series8);
        modelo2.addSeries(series9);
        modelo2.setShowPointLabels(true);
        modelo2.getAxes().put(AxisType.X, new CategoryAxis("Día"));
        modelo2.setTitle("Recolección por Día " + DateTools.getMes(mes2) + " de " + ano2);
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
        LineChartSeries series8 = new LineChartSeries();
        LineChartSeries series9 = new LineChartSeries();
        series1.setLabel("Extra");
        series2.setLabel("Primera");
        series3.setLabel("Segunda");
        series4.setLabel("Tercera");
        series5.setLabel("Cuarta");
        series6.setLabel("Quinta");
        series7.setLabel("Dañada");
        series8.setLabel("Buena");
        series9.setLabel("Total");

        RecoleccionController controlador = new RecoleccionController();

        Recoleccion sumarRegistros;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(DateTools.getPrimerDiaDeLaSemana(fecha3));
        for (int i = 0; i < 7; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 1);
            Date fecha2 = cal.getTime();
            sumarRegistros = controlador.sumarRegistros(null, null, fecha1, fecha2);

            series1.set(DateTools.getDia(i + 1), sumarRegistros.getExtraGramos() / 500);
            series2.set(DateTools.getDia(i + 1), sumarRegistros.getPrimeraGramos() / 500);
            series3.set(DateTools.getDia(i + 1), sumarRegistros.getSegundaGramos() / 500);
            series4.set(DateTools.getDia(i + 1), sumarRegistros.getTerceraGramos() / 500);
            series5.set(DateTools.getDia(i + 1), sumarRegistros.getCuartaGramos() / 500);
            series6.set(DateTools.getDia(i + 1), sumarRegistros.getQuintaGramos() / 500);
            series7.set(DateTools.getDia(i + 1), sumarRegistros.getDanadaGramos() / 500);
            series8.set(DateTools.getDia(i + 1), sumarRegistros.getBuenaGramos() / 500);
            series9.set(DateTools.getDia(i + 1), sumarRegistros.getTotalGramos() / 500);
        }

        modelo3.addSeries(series1);
        modelo3.addSeries(series2);
        modelo3.addSeries(series3);
        modelo3.addSeries(series4);
        modelo3.addSeries(series5);
        modelo3.addSeries(series6);
        modelo3.addSeries(series7);
        modelo3.addSeries(series8);
        modelo3.addSeries(series9);
        modelo3.setShowPointLabels(true);
        modelo3.getAxes().put(AxisType.X, new CategoryAxis("Día"));
        modelo3.setTitle("Recolección por Día " + DateTools.getSemana(fecha3));
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
        LineChartSeries series8 = new LineChartSeries();
        LineChartSeries series9 = new LineChartSeries();
        series1.setLabel("Extra");
        series2.setLabel("Primera");
        series3.setLabel("Segunda");
        series4.setLabel("Tercera");
        series5.setLabel("Cuarta");
        series6.setLabel("Quinta");
        series7.setLabel("Dañada");
        series8.setLabel("Buena");
        series9.setLabel("Total");

        RecoleccionController controlador = new RecoleccionController();

        Recoleccion sumarRegistros;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date(ano4 - 1900, 0, 1));
        for (int i = 0; i < 52; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.DAY_OF_MONTH, 6);
            Date fecha2 = cal.getTime();
            sumarRegistros = controlador.sumarRegistros(null, null, fecha1, fecha2);

            series1.set(i + 1, sumarRegistros.getExtraGramos() / 500);
            series2.set(i + 1, sumarRegistros.getPrimeraGramos() / 500);
            series3.set(i + 1, sumarRegistros.getSegundaGramos() / 500);
            series4.set(i + 1, sumarRegistros.getTerceraGramos() / 500);
            series5.set(i + 1, sumarRegistros.getCuartaGramos() / 500);
            series6.set(i + 1, sumarRegistros.getQuintaGramos() / 500);
            series7.set(i + 1, sumarRegistros.getDanadaGramos() / 500);
            series8.set(i + 1, sumarRegistros.getBuenaGramos() / 500);
            series9.set(i + 1, sumarRegistros.getTotalGramos() / 500);

            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        modelo4.addSeries(series1);
        modelo4.addSeries(series2);
        modelo4.addSeries(series3);
        modelo4.addSeries(series4);
        modelo4.addSeries(series5);
        modelo4.addSeries(series6);
        modelo4.addSeries(series7);
        modelo4.addSeries(series8);
        modelo4.addSeries(series9);
        modelo4.setShowPointLabels(true);
        modelo4.getAxes().put(AxisType.X, new CategoryAxis("Semana"));
        modelo4.setTitle("Recolección por Semana Año " + ano4);
        modelo4.setLegendPosition("e");
        Axis yAxis = modelo4.getAxis(AxisType.Y);
        yAxis.setMin(0);
    }

    public void createModel5() {
        modelo5 = new BarChartModel();
        List<Lote> lotes = new LoteController().getItems();
        ChartSeries[] series = new ChartSeries[lotes.size()];
        for (int l = 0; l < lotes.size(); l++) {
            series[l] = new ChartSeries();
        }
        RecoleccionController controlador = new RecoleccionController();

        Calendar c = GregorianCalendar.getInstance();
        c.setTime(new Date(2012 - 1900, 0, 1));
        Date fecha1;
        Date fecha2;
        double valor;
        for (int i = 2012; i <= (new Date().getYear() + 1900); i++) {
            fecha1 = c.getTime();
            c.add(Calendar.YEAR, 1);
            c.add(Calendar.DAY_OF_MONTH, -1);
            fecha2 = c.getTime();

            for (int l = 0; l < lotes.size(); l++) {
                valor = controlador.sumarRegistros(null, lotes.get(l), fecha1, fecha2).getBuenaGramos() / 500;
                series[l].set(i, valor);
            }
        }
        for (int l = 0; l < lotes.size(); l++) {
            series[l].setLabel(lotes.get(l).getNombre());
            modelo5.addSeries(series[l]);
        }
        
        modelo5.getAxes().put(AxisType.X, new CategoryAxis("Lote"));
        modelo5.setTitle("Recolección por Lote");
        modelo5.setLegendPosition("e");
        Axis yAxis = modelo5.getAxis(AxisType.Y);
        yAxis.setMin(0);        
    }

}
