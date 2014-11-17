/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.produccion;

import controlador.administracion.LoteControlador;
import controlador.produccion.MonitoreoDePlagasControlador;
import dao.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.administracion.Lote;
import modelo.produccion.MonitoreoDePlagas;

/**
 *
 * @author fredy
 */
public class MonitoreoDeEnfermedadesIF extends javax.swing.JInternalFrame {

    /**
     * Creates new form MonitoreoDePlagasIF
     */
    private MonitoreoDePlagasControlador controlador;
    private MonitoreoDePlagas registroSeleccionado;
    private List<MonitoreoDePlagas> lista;

    public MonitoreoDeEnfermedadesIF() {
        initComponents();
        controlador = new MonitoreoDePlagasControlador();
        registroSeleccionado = null;
        cargarListaLotes();
        cargarTablaPrincipal();
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
        fechaChooserCombo = new datechooser.beans.DateChooserCombo();
        loteComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        principalTable = new javax.swing.JTable();
        edicionPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        moduloSpinner = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        arañitaSpinner = new javax.swing.JSpinner();
        thripsSpinner = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        cyclamenSpinner = new javax.swing.JSpinner();
        chisasSpinner = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        otroTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        babosasSpinner = new javax.swing.JSpinner();
        otroSpinner = new javax.swing.JSpinner();
        florSpinner = new javax.swing.JSpinner();
        frutoSpinner = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        coronasSpinner = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Monitoreo de Plagas");
        setMinimumSize(new java.awt.Dimension(1141, 592));

        nuevoButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nuevoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/clipboard_32x32.png"))); // NOI18N
        nuevoButton.setText("Nuevo");
        nuevoButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nuevoButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        nuevoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoButtonActionPerformed(evt);
            }
        });

        eliminarButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        eliminarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/trash_32x32.png"))); // NOI18N
        eliminarButton.setText("Eliminar");
        eliminarButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eliminarButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        eliminarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarButtonActionPerformed(evt);
            }
        });

        editarButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        editarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/edit_32x32.png"))); // NOI18N
        editarButton.setText("Editar");
        editarButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editarButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarButtonActionPerformed(evt);
            }
        });

        guardarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/save_32x32.png"))); // NOI18N
        guardarButton.setText("Guardar");
        guardarButton.setEnabled(false);
        guardarButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        guardarButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        guardarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarButtonActionPerformed(evt);
            }
        });

        fechaChooserCombo.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
        fechaChooserCombo.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                fechaChooserComboOnSelectionChange(evt);
            }
        });

        loteComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loteComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                loteComboBoxItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Fecha");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Lote");

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
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(fechaChooserCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(loteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(loteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addComponent(fechaChooserCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        principalTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        principalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Módulo", "Arañita Roja", "Thrips", "Cylamen", "Chisas", "Babosas", "Otro", "Otro", "Flor", "Fruto", "N° Coronas"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
        );

        edicionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Edición"));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Módulo");

        moduloSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        moduloSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Arañita");

        arañitaSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        arañitaSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 2, 1));

        thripsSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        thripsSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 2, 1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Thrips");

        cyclamenSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cyclamenSpinner.setModel(new javax.swing.SpinnerListModel(new String[] {"No", "Si"}));

        chisasSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        chisasSpinner.setModel(new javax.swing.SpinnerListModel(new String[] {"No", "Si"}));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Cyclamen");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Chisas");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Babosas");

        otroTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        otroTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otroTextFieldActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Otro");

        babosasSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        babosasSpinner.setModel(new javax.swing.SpinnerListModel(new String[] {"No", "Si"}));

        otroSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        otroSpinner.setModel(new javax.swing.SpinnerListModel(new String[] {"No", "Si"}));

        florSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        florSpinner.setModel(new javax.swing.SpinnerListModel(new String[] {"No", "Si"}));

        frutoSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        frutoSpinner.setModel(new javax.swing.SpinnerListModel(new String[] {"No", "Si"}));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Flor");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Fruto");

        coronasSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        coronasSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Coronas");

        javax.swing.GroupLayout edicionPanelLayout = new javax.swing.GroupLayout(edicionPanel);
        edicionPanel.setLayout(edicionPanelLayout);
        edicionPanelLayout.setHorizontalGroup(
            edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edicionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(edicionPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(96, 96, 96)
                        .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(frutoSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(florSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(coronasSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(edicionPanelLayout.createSequentialGroup()
                        .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(58, 58, 58)
                        .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(arañitaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(moduloSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(thripsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cyclamenSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chisasSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(babosasSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(edicionPanelLayout.createSequentialGroup()
                                .addComponent(otroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(otroSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addGap(18, 41, Short.MAX_VALUE))
        );
        edicionPanelLayout.setVerticalGroup(
            edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edicionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(moduloSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(arañitaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(thripsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cyclamenSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chisasSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(babosasSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(otroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(otroSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(florSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(frutoSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(coronasSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
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
                .addComponent(edicionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        Lote lote = (Lote) loteComboBox.getSelectedItem();
        int modulo = (int) moduloSpinner.getValue();
        int aranita = (int) arañitaSpinner.getValue();
        int thrips = (int) thripsSpinner.getValue();
        boolean cyclamen = false;
        if (cyclamenSpinner.getValue().equals("Si")) {
            cyclamen = true;
        }
        boolean chisas = false;
        if (chisasSpinner.getValue().equals("Si")) {
            chisas = true;
        }
        boolean babosas = false;
        if (babosasSpinner.getValue().equals("Si")) {
            cyclamen = true;
        }
        String otro = otroTextField.getText();
        boolean otrov = false;
        if (otroSpinner.getValue().equals("Si")) {
            otrov = true;
        }
        boolean flor = false;
        if (florSpinner.getValue().equals("Si")) {
            flor = true;
        }
        boolean fruto = false;
        if (frutoSpinner.getValue().equals("Si")) {
            fruto = true;
        }
        int coronas = (int) coronasSpinner.getValue();
        Date fecha = fechaChooserCombo.getSelectedDate().getTime();
        if (registroSeleccionado == null) {
            MonitoreoDePlagas nuevo = controlador.nuevo(lote, modulo, aranita, thrips, cyclamen, chisas, babosas, otro, otrov, flor, fruto, coronas, fecha);
            if (controlador.validar(nuevo)) {
                controlador.guardar(nuevo);
                guardar(false);
                cargarDatosRegistroSeleccionado();
                cargarTablaPrincipal();
            }
        } else {
            registroSeleccionado.setAranita(aranita);
            registroSeleccionado.setBabosas(babosas);
            registroSeleccionado.setChisas(chisas);
            registroSeleccionado.setCoronas(coronas);
            registroSeleccionado.setCyclamen(cyclamen);
            registroSeleccionado.setFlor(flor);
            registroSeleccionado.setFruto(fruto);
            registroSeleccionado.setModulo(modulo);
            registroSeleccionado.setOtro(otro);
            registroSeleccionado.setOtrov(otrov);
            registroSeleccionado.setThrips(thrips);
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

    private void loteComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_loteComboBoxItemStateChanged
        // TODO add your handling code here:
        cargarTablaPrincipal();
    }//GEN-LAST:event_loteComboBoxItemStateChanged

    private void fechaChooserComboOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_fechaChooserComboOnSelectionChange
        // TODO add your handling code here:
        cargarTablaPrincipal();
    }//GEN-LAST:event_fechaChooserComboOnSelectionChange

    private void otroTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otroTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_otroTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner arañitaSpinner;
    private javax.swing.JSpinner babosasSpinner;
    private javax.swing.JSpinner chisasSpinner;
    private javax.swing.JSpinner coronasSpinner;
    private javax.swing.JSpinner cyclamenSpinner;
    private javax.swing.JPanel edicionPanel;
    private javax.swing.JButton editarButton;
    private javax.swing.JButton eliminarButton;
    private datechooser.beans.DateChooserCombo fechaChooserCombo;
    private javax.swing.JSpinner florSpinner;
    private javax.swing.JSpinner frutoSpinner;
    private javax.swing.JButton guardarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox loteComboBox;
    private javax.swing.JSpinner moduloSpinner;
    private javax.swing.JButton nuevoButton;
    private javax.swing.JSpinner otroSpinner;
    private javax.swing.JTextField otroTextField;
    private javax.swing.JTable principalTable;
    private javax.swing.JSpinner thripsSpinner;
    // End of variables declaration//GEN-END:variables

    private void cargarTablaPrincipal() {
        while (principalTable.getRowCount() > 0) {
            ((DefaultTableModel) principalTable.getModel()).removeRow(0);
        }
        lista = controlador.leerLista((Lote) loteComboBox.getSelectedItem(), fechaChooserCombo.getSelectedDate().getTime());
        for (MonitoreoDePlagas mp : lista) {
            Object[] row = {mp.getModulo(),
                mp.getAranita(),
                mp.getThrips(),
                mp.isCyclamen(),
                mp.isChisas(),
                mp.isBabosas(),
                mp.getOtro(),
                mp.isOtrov(),
                mp.isFlor(),
                mp.isFruto(),
                mp.getCoronas()};
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
            moduloSpinner.setValue(0);
            arañitaSpinner.setValue(0);
            thripsSpinner.setValue(0);
            cyclamenSpinner.setValue("No");
            chisasSpinner.setValue("No");
            babosasSpinner.setValue("No");
            otroTextField.setText(null);
            otroSpinner.setValue("No");
            florSpinner.setValue("No");
            frutoSpinner.setValue("No");
            coronasSpinner.setValue(0);
        } else {
            moduloSpinner.setValue(registroSeleccionado.getModulo());
            arañitaSpinner.setValue(registroSeleccionado.getAranita());
            thripsSpinner.setValue(registroSeleccionado.getThrips());
            if (registroSeleccionado.isCyclamen()) {
                cyclamenSpinner.setValue("Si");
            } else {
                cyclamenSpinner.setValue("No");
            }
            if (registroSeleccionado.isChisas()) {
                chisasSpinner.setValue("Si");
            } else {
                chisasSpinner.setValue("No");
            }
            if (registroSeleccionado.isBabosas()) {
                babosasSpinner.setValue("Si");
            } else {
                babosasSpinner.setValue("No");
            }
            otroTextField.setText(registroSeleccionado.getOtro());
            if (registroSeleccionado.isOtrov()) {
                otroSpinner.setValue("Si");
            } else {
                otroSpinner.setValue("No");
            }
            if (registroSeleccionado.isFlor()) {
                florSpinner.setValue("Si");
            } else {
                florSpinner.setValue("No");
            }
            if (registroSeleccionado.isFruto()) {
                frutoSpinner.setValue("Si");
            } else {
                frutoSpinner.setValue("No");
            }
            coronasSpinner.setValue(registroSeleccionado.getCoronas());
        }
    }

    private void cargarListaLotes() {
        loteComboBox.removeAllItems();
        List<Lote> leerLista = new LoteControlador().leerLista();
        for (Lote l : leerLista) {
            loteComboBox.addItem(l);
        }
        if (leerLista.size() > 0) {
            loteComboBox.setSelectedIndex(0);
        }
    }
}
