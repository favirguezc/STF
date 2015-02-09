/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.produccion;

import controlador.produccion.administracion.ModuloControlador;
import controlador.produccion.monitoreo.MonitoreoControlador;
import controlador.produccion.monitoreo.MonitoreoDeVariablesControlador;
import controlador.produccion.monitoreo.VariableControlador;
import dao.exceptions.NonexistentEntityException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.produccion.administracion.Modulo;
import modelo.produccion.monitoreo.Monitoreo;
import modelo.produccion.monitoreo.MonitoreoDeVariables;
import modelo.produccion.monitoreo.Riesgo;
import modelo.produccion.monitoreo.Variable;
import util.TableColumnAdjuster;

/**
 *
 * @author fredy
 */
public class MonitoreoDeVariablesIF extends javax.swing.JInternalFrame {

    /**
     * Creates new form MonitoreoDeVariablesIF
     */
    private MonitoreoDeVariablesControlador controlador;
    private MonitoreoDeVariables registroSeleccionado;
    private List<MonitoreoDeVariables> lista;

    public MonitoreoDeVariablesIF() {
        initComponents();
        controlador = new MonitoreoDeVariablesControlador();
        registroSeleccionado = null;
        cargarListas();
        cargarTablaPrincipal();
        guardar(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        nuevoButton = new javax.swing.JButton();
        eliminarButton = new javax.swing.JButton();
        editarButton = new javax.swing.JButton();
        guardarButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        monitoreoFiltroComboBox = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        principalTable = new javax.swing.JTable();
        edicionPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        moduloComboBox = new javax.swing.JComboBox();
        monitoreoComboBox = new javax.swing.JComboBox();
        variableComboBox = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        conteoLongField = new util.LongField();
        relacionTextField = new javax.swing.JTextField();
        riesgoComboBox = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Monitoreo de Enfermedades");
        setMinimumSize(new java.awt.Dimension(1141, 592));

        nuevoButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nuevoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/32x32/add42.png"))); // NOI18N
        nuevoButton.setText("Nuevo");
        nuevoButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nuevoButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        nuevoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoButtonActionPerformed(evt);
            }
        });

        eliminarButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        eliminarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/32x32/delete27.png"))); // NOI18N
        eliminarButton.setText("Eliminar");
        eliminarButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eliminarButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        eliminarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarButtonActionPerformed(evt);
            }
        });

        editarButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        editarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/32x32/edit23.png"))); // NOI18N
        editarButton.setText("Editar");
        editarButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editarButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarButtonActionPerformed(evt);
            }
        });

        guardarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/32x32/database13.png"))); // NOI18N
        guardarButton.setText("Guardar");
        guardarButton.setEnabled(false);
        guardarButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        guardarButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        guardarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarButtonActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Monitoreo");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Monitoreo");

        monitoreoFiltroComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nuevoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(eliminarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(guardarButton)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(monitoreoFiltroComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(490, 490, 490))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guardarButton)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(editarButton)
                        .addComponent(eliminarButton)
                        .addComponent(nuevoButton)))
                .addGap(315, 315, 315))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(monitoreoFiltroComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        principalTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        principalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Monitoreo", "Módulo", "Variable", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        principalTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableColumnAdjuster tca = new TableColumnAdjuster(principalTable);
        tca.adjustColumns();
        jScrollPane1.setViewportView(principalTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );

        edicionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Edición"));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Módulo");

        moduloComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        monitoreoComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        variableComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        variableComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                variableComboBoxActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Monitoreo");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Variable");

        conteoLongField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        relacionTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        relacionTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relacionTextFieldActionPerformed(evt);
            }
        });

        riesgoComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Conteo");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Relación");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Riesgo");

        javax.swing.GroupLayout edicionPanelLayout = new javax.swing.GroupLayout(edicionPanel);
        edicionPanel.setLayout(edicionPanelLayout);
        edicionPanelLayout.setHorizontalGroup(
            edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edicionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(monitoreoComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(moduloComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(variableComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(conteoLongField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(relacionTextField)
                    .addComponent(riesgoComboBox, 0, 129, Short.MAX_VALUE)))
        );
        edicionPanelLayout.setVerticalGroup(
            edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edicionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(monitoreoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(moduloComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(variableComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(conteoLongField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(relacionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(riesgoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edicionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(edicionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoButtonActionPerformed
        // TODO add your handling code here:
        registroSeleccionado = null;
        guardar(true);
        cargarDatosRegistroSeleccionado();
    }//GEN-LAST:event_nuevoButtonActionPerformed

    private void eliminarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarButtonActionPerformed
        // TODO add your handling code here:
        guardar(false);
        if (principalTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "No hay un registro seleccionado, por favor seleccione uno.", "Registro no seleccionado", JOptionPane.INFORMATION_MESSAGE);
        } else if (principalTable.getValueAt(principalTable.getSelectedRow(), 0) == null) {
            JOptionPane.showMessageDialog(null, "No hay un registro válido seleccionado, por favor seleccione uno.", "Registro no seleccionado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            registroSeleccionado = lista.get(principalTable.getSelectedRow());
            try {
                controlador.eliminar(registroSeleccionado.getId());
            } catch (NonexistentEntityException ex) {
                JOptionPane.showMessageDialog(null, "El registro ya no existe.", "Error al eliminar", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "El registro tiene datos relacionados.", "Error al eliminar", JOptionPane.INFORMATION_MESSAGE);
            }
            registroSeleccionado = null;
            cargarTablaPrincipal();
        }
    }//GEN-LAST:event_eliminarButtonActionPerformed

    private void editarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarButtonActionPerformed
        // TODO add your handling code here:
        guardar(false);
        registroSeleccionado = null;
        if (principalTable.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "No hay un registro seleccionado, por favor seleccione uno.", "Registro no seleccionado", JOptionPane.INFORMATION_MESSAGE);
        } else if (principalTable.getValueAt(principalTable.getSelectedRow(), 0) == null) {
            JOptionPane.showMessageDialog(null, "No hay un registro válido seleccionado, por favor seleccione uno.", "Registro no seleccionado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            registroSeleccionado = lista.get(principalTable.getSelectedRow());
            guardar(true);
        }
        cargarDatosRegistroSeleccionado();
    }//GEN-LAST:event_editarButtonActionPerformed

    private void guardarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarButtonActionPerformed
        // TODO add your handling code here:
        Monitoreo monitoreo = (Monitoreo) monitoreoComboBox.getSelectedItem();
        Modulo modulo = (Modulo) moduloComboBox.getSelectedItem();
        Variable variable = (Variable) variableComboBox.getSelectedItem();
        int conteo = conteoLongField.getInteger();
        String relacion = relacionTextField.getText();
        Riesgo riesgo = (Riesgo) riesgoComboBox.getSelectedItem();

        if (registroSeleccionado == null) {
            MonitoreoDeVariables nuevo = controlador.nuevo(monitoreo, modulo, variable, conteo, relacion, riesgo);
            if (controlador.validar(nuevo)) {
                controlador.guardar(nuevo);
                guardar(false);
                cargarDatosRegistroSeleccionado();
                cargarTablaPrincipal();
            }
        } else {
            registroSeleccionado.setMonitoreo(monitoreo);
            registroSeleccionado.setModulo(modulo);
            registroSeleccionado.setVariable(variable);
            registroSeleccionado.setConteo(conteo);
            registroSeleccionado.setRelacion(relacion);
            registroSeleccionado.setRiesgo(riesgo);
            if (controlador.validar(registroSeleccionado)) {
                try {
                    controlador.editar(registroSeleccionado);
                    guardar(false);
                    cargarDatosRegistroSeleccionado();
                    cargarTablaPrincipal();
                } catch (Exception ex) {
                }
            }
        }
    }//GEN-LAST:event_guardarButtonActionPerformed

    private void relacionTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relacionTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_relacionTextFieldActionPerformed

    private void variableComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_variableComboBoxActionPerformed
        // TODO add your handling code here:
        if (variableComboBox.getSelectedItem() != null) {
            switch (((Variable) variableComboBox.getSelectedItem()).getTipoDeValoracion()) {
                case CONTEO:
                    conteoLongField.setEnabled(true);
                    relacionTextField.setEnabled(false);
                    relacionTextField.setText(null);
                    riesgoComboBox.setEnabled(false);
                    riesgoComboBox.setSelectedIndex(0);
                    break;
                case RELACION:
                    conteoLongField.setEnabled(false);
                    conteoLongField.setInteger(0);
                    relacionTextField.setEnabled(true);
                    riesgoComboBox.setEnabled(false);
                    riesgoComboBox.setSelectedIndex(0);
                    break;
                case RIESGO:
                    conteoLongField.setEnabled(false);
                    conteoLongField.setInteger(0);
                    relacionTextField.setEnabled(false);
                    relacionTextField.setText(null);
                    riesgoComboBox.setEnabled(true);
                    break;
            }
        }
    }//GEN-LAST:event_variableComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private util.LongField conteoLongField;
    private javax.swing.JPanel edicionPanel;
    private javax.swing.JButton editarButton;
    private javax.swing.JButton eliminarButton;
    private javax.swing.JButton guardarButton;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox moduloComboBox;
    private javax.swing.JComboBox monitoreoComboBox;
    private javax.swing.JComboBox monitoreoFiltroComboBox;
    private javax.swing.JButton nuevoButton;
    private javax.swing.JTable principalTable;
    private javax.swing.JTextField relacionTextField;
    private javax.swing.JComboBox riesgoComboBox;
    private javax.swing.JComboBox variableComboBox;
    // End of variables declaration//GEN-END:variables

    private void cargarTablaPrincipal() {
        while (principalTable.getRowCount() > 0) {
            ((DefaultTableModel) principalTable.getModel()).removeRow(0);
        }
        lista = controlador.leerLista((Monitoreo) moduloComboBox.getSelectedItem());
        Object valor = null;
        for (MonitoreoDeVariables mv : lista) {
            switch (mv.getVariable().getTipoDeValoracion()) {
                case CONTEO:
                    valor = mv.getConteo();
                    break;
                case RELACION:
                    valor = mv.getRelacion();
                    break;
                case RIESGO:
                    valor = mv.getRiesgo();
                    break;
            }
            Object[] row = {mv.getMonitoreo(), mv.getModulo(), mv.getVariable(), valor};
            ((DefaultTableModel) principalTable.getModel()).addRow(row);
        }
    }

    private void guardar(boolean b) {
        guardarButton.setEnabled(b);
        edicionPanel.setVisible(b);
        if (!b) {
            registroSeleccionado = null;
        }
    }

    private void cargarDatosRegistroSeleccionado() {
        if (registroSeleccionado == null) {
            if (monitoreoComboBox.getItemCount() > 0) {
                monitoreoComboBox.setSelectedIndex(0);
            }
            if (moduloComboBox.getItemCount() > 0) {
                moduloComboBox.setSelectedIndex(0);
            }
            if (variableComboBox.getItemCount() > 0) {
                variableComboBox.setSelectedIndex(0);
            }
            conteoLongField.setEnabled(false);
            conteoLongField.setInteger(0);
            relacionTextField.setEnabled(false);
            relacionTextField.setText(null);
            riesgoComboBox.setEnabled(false);
            riesgoComboBox.setSelectedIndex(0);
        } else {
            monitoreoComboBox.setSelectedItem(registroSeleccionado.getMonitoreo());
            moduloComboBox.setSelectedItem(registroSeleccionado.getModulo());
            variableComboBox.setSelectedItem(registroSeleccionado.getVariable());
            conteoLongField.setEnabled(false);
            relacionTextField.setEnabled(false);
            riesgoComboBox.setEnabled(false);
            switch (registroSeleccionado.getVariable().getTipoDeValoracion()) {
                case CONTEO:
                    conteoLongField.setEnabled(true);
                    conteoLongField.setInteger(registroSeleccionado.getConteo());
                    break;
                case RELACION:
                    relacionTextField.setEnabled(true);
                    relacionTextField.setText(registroSeleccionado.getRelacion());
                    break;
                case RIESGO:
                    riesgoComboBox.setEnabled(true);
                    riesgoComboBox.setSelectedItem(registroSeleccionado.getRiesgo());
                    break;
            }
        }
    }

    private void cargarListas() {
        cargarListaMonitoreos();
        cargarListaModulos();
        cargarListaVariables();
        cargarListaRiesgos();
    }

    private void cargarListaModulos() {
        moduloComboBox.removeAllItems();
        List<Modulo> leerLista = new ModuloControlador().leerLista();
        for (Modulo m : leerLista) {
            moduloComboBox.addItem(m);
        }
        if (leerLista.size() > 0) {
            moduloComboBox.setSelectedIndex(0);
        }
    }

    private void cargarListaMonitoreos() {
        monitoreoComboBox.removeAllItems();
        monitoreoFiltroComboBox.removeAllItems();
        List<Monitoreo> leerLista = new MonitoreoControlador().leerlista();
        for (Monitoreo m : leerLista) {
            monitoreoComboBox.addItem(m);
            monitoreoFiltroComboBox.addItem(m);
        }
        if (leerLista.size() > 0) {
            monitoreoComboBox.setSelectedIndex(0);
            monitoreoFiltroComboBox.setSelectedIndex(0);
        }
    }

    private void cargarListaVariables() {
        variableComboBox.removeAllItems();
        List<Variable> leerLista = new VariableControlador().leerLista();
        for (Variable v : leerLista) {
            variableComboBox.addItem(v);
        }
        if (leerLista.size() > 0) {
            variableComboBox.setSelectedIndex(0);
        }
    }

    private void cargarListaRiesgos() {
        riesgoComboBox.removeAllItems();
        for (Riesgo r : Riesgo.values()) {
            riesgoComboBox.addItem(r);
        }
    }

}
