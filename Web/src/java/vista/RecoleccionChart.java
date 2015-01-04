/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.RecoleccionController;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import modelo.produccion.Recoleccion;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
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
    private int ano1;

    @PostConstruct
    public void init() {
        ano1 = new Date().getYear() + 1900;
        createModel1();
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

}
