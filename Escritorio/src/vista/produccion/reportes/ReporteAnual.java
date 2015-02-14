/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.produccion.reportes;

import java.awt.Frame;
import java.io.File;
import javax.swing.JFileChooser;
import vista.produccion.reportes.pdf.ReporteDeProduccionAnual;
import modelo.util.FileTypeFilter;

/**
 *
 * @author fredy
 */
public class ReporteAnual extends javax.swing.JDialog {

    public static final int POR_MES = 0, POR_SEMANA = 1, POR_DIA = 2;
    private int tipo;

    /**
     * Creates new form AñoD
     *
     * @param parent
     * @param modal
     * @param type
     */
    public ReporteAnual(Frame parent, boolean modal, int type) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.tipo = type;
        switch (tipo) {
            case POR_MES:
                this.setTitle("Reporte Anual De Recolección Por Mes");
                break;
            case POR_SEMANA:
                this.setTitle("Reporte Anual De Recolección Por Semana");
                break;
            case POR_DIA:
                this.setTitle("Reporte Anual De Recolección Por Día");
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

        añoSpinner = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        añoSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        añoSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(2014), Integer.valueOf(2012), null, Integer.valueOf(1)));
        añoSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                añoSpinnerStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Año");

        jButton1.setText("Generar Reporte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(añoSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(añoSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void añoSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_añoSpinnerStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_añoSpinnerStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileTypeFilter(".pdf", "PDF"));
        String ruta = "reporte de recolección anual";
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
            new ReporteDeProduccionAnual((int) añoSpinner.getValue(), tipo, fileChooser.getSelectedFile().getAbsolutePath()).crearReporte();
        }
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner añoSpinner;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel11;
    // End of variables declaration//GEN-END:variables
}