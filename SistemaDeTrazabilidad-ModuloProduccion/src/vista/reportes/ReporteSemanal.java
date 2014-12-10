/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.reportes;

import controlador.administracion.LoteControlador;
import controlador.administracion.RolControlador;
import controlador.administracion.RolPersonaControlador;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import modelo.administracion.Lote;
import modelo.administracion.Persona;
import modelo.administracion.Rol;
import vista.pdf.ReporteDeProduccionSemanal;
import util.DateTools;
import util.FileTypeFilter;

/**
 *
 * @author fredy
 */
public class ReporteSemanal extends javax.swing.JDialog {

    /**
     * Creates new form ReporteSemanal
     */
    public ReporteSemanal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        cargarListaLotes();
        cargarListaRecolectores();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loteComboBox = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        recolectorComboBox = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        fechaDateChooserCombo = new datechooser.beans.DateChooserCombo();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        loteComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loteComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                loteComboBoxItemStateChanged(evt);
            }
        });
        loteComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loteComboBoxActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Lote");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Recolector");

        recolectorComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        recolectorComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                recolectorComboBoxItemStateChanged(evt);
            }
        });
        recolectorComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recolectorComboBoxActionPerformed(evt);
            }
        });

        jButton3.setText("Generar Reporte");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Cancelar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        fechaDateChooserCombo.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Fecha");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fechaDateChooserCombo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(loteComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(recolectorComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fechaDateChooserCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(loteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(recolectorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loteComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_loteComboBoxItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_loteComboBoxItemStateChanged

    private void loteComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loteComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loteComboBoxActionPerformed

    private void recolectorComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_recolectorComboBoxItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_recolectorComboBoxItemStateChanged

    private void recolectorComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recolectorComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_recolectorComboBoxActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileTypeFilter(".pdf", "PDF"));
        String ruta = "reporte de recolección semanal ";
        ruta += DateTools.getSemana(fechaDateChooserCombo.getCurrent().getTime());
        if ((Lote) loteComboBox.getSelectedItem() != null) {
            ruta += " " + ((Lote) loteComboBox.getSelectedItem()).toString();
        }
        if ((Persona) recolectorComboBox.getSelectedItem() != null) {
            ruta += " " + ((Persona) recolectorComboBox.getSelectedItem()).toString();
        }
        ruta += ".pdf";
        fileChooser.setSelectedFile(new File(ruta));
        int returnVal = fileChooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            new ReporteDeProduccionSemanal(fechaDateChooserCombo.getCurrent().getTime(), fileChooser.getSelectedFile().getAbsolutePath(), (Persona) recolectorComboBox.getSelectedItem(), (Lote) loteComboBox.getSelectedItem()).crearReporte();
        }
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo fechaDateChooserCombo;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JComboBox loteComboBox;
    private javax.swing.JComboBox recolectorComboBox;
    // End of variables declaration//GEN-END:variables

    private void cargarListaRecolectores() {
        recolectorComboBox.removeAllItems();
        recolectorComboBox.addItem(null);
        Rol rol = null;
        try {
            rol = new RolControlador().buscar("Recolector(a)");
        } catch (Exception ex) {
        }
        for (Persona p : new RolPersonaControlador().leerLista(rol)) {
            recolectorComboBox.addItem(p);
        }
    }

    private void cargarListaLotes() {
        loteComboBox.removeAllItems();
        loteComboBox.addItem(null);
        List<Lote> leerLista = new LoteControlador().leerLista();
        for (Lote l : leerLista) {
            loteComboBox.addItem(l);
        }
    }
}