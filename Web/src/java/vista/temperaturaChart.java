/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.TemperaturaController;
import controlador.util.JsfUtil;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import modelo.variablesClimaticas.Temperatura;
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
@ManagedBean(name = "temperaturaChart")
public class temperaturaChart implements Serializable {

    private LineChartModel modelo1;
    private int ano;

    @PostConstruct
    public void init() {
        ano = new Date().getYear() + 1900;
        createModel();
    }

    public LineChartModel getModelo1() {
        return modelo1;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void createModel() {
        modelo1 = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        LineChartSeries series2 = new LineChartSeries();
        LineChartSeries series3 = new LineChartSeries();
        series1.setLabel("Temperatura");
        series2.setLabel("Humedad");
        series3.setLabel("Punto De Rocío");
        TemperaturaController controlador = new TemperaturaController();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date(ano - 1900, 0, 1));
        for (int i = 0; i < 12; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            Date fecha2 = cal.getTime();
            Temperatura promedioTemp;
            promedioTemp = controlador.calcularPromedio(fecha1, fecha2);
            cal.add(Calendar.DAY_OF_MONTH, 1);

            series1.set(DateTools.getMes(i), promedioTemp.getTemperatura());
            series2.set(DateTools.getMes(i), promedioTemp.getHumedad());
            series3.set(DateTools.getMes(i), promedioTemp.getPuntoDeRocio());
        }

        modelo1.addSeries(series1);
        modelo1.addSeries(series2);
        modelo1.addSeries(series3);
        modelo1.setShowPointLabels(true);
        modelo1.getAxes().put(AxisType.X, new CategoryAxis("Mes"));
        modelo1.setTitle("Temperatura por Mes Año " + ano);
        modelo1.setLegendPosition("e");
        Axis yAxis = modelo1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(40);
    }
}
