/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.finanzas.reportes;

import java.io.File;
import javax.swing.JFileChooser;
import modelo.util.FileTypeFilter;
import vista.finanzas.reportes.pdf.ReporteDeVentaAnual;

/**
 *
 * @author John Fredy
 */
public class ReporteVentaAnual extends javax.swing.JDialog {

    public static final int POR_MES = 0, POR_SEMANA = 1, POR_DIA = 2;
    private int tipo;
    
    /**
     * Creates new form ReporteVentaAnual
     */
    public ReporteVentaAnual(java.awt.Frame parent, boolean modal, int type) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.tipo = type;
        switch (tipo) {
            case POR_MES:
                this.setTitle("Reporte Anual De Ventas Por Mes");
                break;
            case POR_SEMANA:
                this.setTitle("Reporte Anual De Ventas Por Semana");
                break;
            case POR_DIA:
                this.setTitle("Reporte Anual De Ventas Por Día");
                break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        generarRButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        anoSpinner = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte Anual de Ventas");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Año:");

        generarRButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        generarRButton.setText("Generar Reporte");
        generarRButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarRButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        anoSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        anoSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(2014), Integer.valueOf(2012), null, Integer.valueOf(1)));
        anoSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                anoSpinnerStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(generarRButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(47, 47, 47)
                .addComponent(anoSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(anoSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generarRButton)
                    .addComponent(cancelButton))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generarRButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarRButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileTypeFilter(".pdf", "PDF"));
        String ruta = "reporte de ventas anual";
        switch (tipo) {
            case POR_MES:
                ruta += " por mes";
                break;
            case POR_SEMANA:
                ruta += " por semana";
                break;
            case POR_DIA:
                ruta += " por dia";
                break;
        }
        ruta += ".pdf";
        fileChooser.setSelectedFile(new File(ruta));
        int returnVal = fileChooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            new ReporteDeVentaAnual((int) anoSpinner.getValue(), tipo, fileChooser.getSelectedFile().getAbsolutePath()).crearReporte();
        }
        this.dispose();
    }//GEN-LAST:event_generarRButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void anoSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_anoSpinnerStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_anoSpinnerStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner anoSpinner;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton generarRButton;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}