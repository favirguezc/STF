/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.graficas;

import controlador.produccion.recoleccion.RecoleccionControlador;
import controlador.produccion.monitoreo.TrampaDeInsectosControlador;
import java.awt.Color;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JPanel;
import modelo.produccion.recoleccion.Recoleccion;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import util.DateTools;

/**
 *
 * @author fredy
 */
public class RecoleccionAnualPorMesYTrampaDeInsectosIF extends javax.swing.JInternalFrame {

    /**
     * Creates new form GraficaDeRecoleccionPorLote
     */
    public RecoleccionAnualPorMesYTrampaDeInsectosIF() {
        initComponents();
        pintarGrafico();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        añoSpinner = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Gráfica De Recolección Por Mes y Temperatura");

        añoSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        añoSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(2014), Integer.valueOf(2012), null, Integer.valueOf(1)));
        añoSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                añoSpinnerStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Año");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(397, 397, 397)
                .addComponent(jLabel11)
                .addGap(46, 46, 46)
                .addComponent(añoSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(268, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(añoSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 812, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 482, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void añoSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_añoSpinnerStateChanged
        // TODO add your handling code here:
        pintarGrafico();
    }//GEN-LAST:event_añoSpinnerStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner añoSpinner;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

    private JPanel crearPanel() {
        ChartPanel chartPanel;
        chartPanel = new ChartPanel(
                crearGrafica(
                        crearDatosRecoleccion(),
                        crearDatosTrampaDeInsectos()));
        chartPanel.setDomainZoomable(false);
        chartPanel.setRangeZoomable(false);
        chartPanel.setSize(jPanel2.getSize());
        return chartPanel;
    }

    private JFreeChart crearGrafica(CategoryDataset datos1, CategoryDataset datos2) {
        CategoryPlot plot = new CategoryPlot();
        plot.setDomainAxis(new CategoryAxis("Mes"));
        plot.setRangeAxis(new NumberAxis("Producción"));
        plot.setRangeAxis(1, new NumberAxis("Número de Individuos"));
        plot.setBackgroundPaint(Color.WHITE);
        CategoryItemRenderer renderer1 = new BarRenderer();
        renderer1.setSeriesPaint(0, Color.BLUE);
        renderer1.setSeriesPaint(1, Color.GRAY);
        renderer1.setSeriesPaint(2, Color.GREEN);
        renderer1.setSeriesPaint(3, Color.ORANGE);
        renderer1.setSeriesPaint(4, Color.MAGENTA);
        renderer1.setSeriesPaint(5, Color.RED);
        plot.setDataset(0, datos1);
        plot.setRenderer(0, renderer1);
        CategoryItemRenderer renderer2 = new LineAndShapeRenderer();
        plot.setDataset(1, datos2);
        plot.setRenderer(1, renderer2);
        plot.mapDatasetToRangeAxis(1, 1);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        JFreeChart chart;
        chart = new JFreeChart(plot);
        chart.setTitle("Recolección Por Mes " + (int) añoSpinner.getValue());
        return chart;
    }

    private CategoryDataset crearDatosRecoleccion() {
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        final String e = "Extra";
        final String p = "Primera";
        final String s = "Segunda";
        final String t = "Tercera";
        final String c = "Cuarta";
        final String d = "Dañada";
        Recoleccion sumarRegistros;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date((int) añoSpinner.getValue() - 1900, 0, 1));
        for (int i = 0; i < 12; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            Date fecha2 = cal.getTime();
            sumarRegistros = new RecoleccionControlador().sumarRegistros(null, null, fecha1, fecha2);
            String mes = DateTools.getMes(i);
            datos.addValue(sumarRegistros.getExtraGramos() / 500, e, mes);
            datos.addValue(sumarRegistros.getPrimeraGramos() / 500, p, mes);
            datos.addValue(sumarRegistros.getSegundaGramos() / 500, s, mes);
            datos.addValue(sumarRegistros.getTerceraGramos() / 500, t, mes);
            datos.addValue(sumarRegistros.getCuartaGramos() / 500, c, mes);
            datos.addValue(sumarRegistros.getDanadaGramos() / 500, d, mes);

            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return datos;
    }

    private CategoryDataset crearDatosTrampaDeInsectos() {
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(new Date((int) añoSpinner.getValue() - 1900, 0, 1));
        for (int i = 0; i < 12; i++) {
            Date fecha1 = cal.getTime();
            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            Date fecha2 = cal.getTime();
            int individuos = new TrampaDeInsectosControlador().contar(fecha1, fecha2);
            datos.addValue(individuos, "Trampa de Insectos", DateTools.getMes(i));
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return datos;
    }

    private void pintarGrafico() {
        jPanel2.removeAll();
        jPanel2.add(crearPanel());
        repaint();
    }
}
