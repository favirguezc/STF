/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.graficas;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author fredy
 */
public class GeneradorDeGraficas {

    static final Color[] colores = {
        Color.BLUE,
        Color.GRAY,
        Color.GREEN,
        Color.ORANGE,
        Color.MAGENTA,
        Color.RED,
        Color.CYAN};

    public static JFreeChart crearGraficaDeBarras(CategoryDataset datos, String titulo, String dominio, String rango, int series) {
        CategoryPlot plot = new CategoryPlot();
        plot.setDomainAxis(new CategoryAxis(dominio));
        plot.setRangeAxis(new NumberAxis(rango));
        plot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = new BarRenderer();
        renderer.setDrawBarOutline(false);
        for (int i = 0; i < series; i++) {
            renderer.setSeriesPaint(i, colores[i % colores.length]);
        }
        plot.setDataset(datos);
        plot.setRenderer(renderer);
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        JFreeChart chart;
        chart = new JFreeChart(plot);
        chart.setTitle(titulo);
        return chart;
    }
    
    public static JFreeChart crearGraficoTorta(PieDataset datos, String titulo){
        JFreeChart createPieChart = ChartFactory.createPieChart(titulo, datos, true, true, true);
        Plot plot = createPieChart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        return createPieChart;
    }
}
