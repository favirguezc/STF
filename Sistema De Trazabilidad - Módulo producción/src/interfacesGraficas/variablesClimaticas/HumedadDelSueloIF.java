/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesGraficas.variablesClimaticas;

import controlador.variablesClimaticas.HumedadDelSueloControlador;
import dao.exceptions.NonexistentEntityException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import modelo.variablesClimaticas.HumedadDelSuelo;

/**
 *
 * @author fredy
 */
public class HumedadDelSueloIF extends javax.swing.JInternalFrame {

    /**
     * Creates new form HumedadDelSueloIF
     */
    private HumedadDelSuelo registroSeleccionado = null;
    private HumedadDelSueloControlador controlador;
    private List<HumedadDelSuelo> lista;

    public HumedadDelSueloIF() {
        initComponents();
        controlador = new HumedadDelSueloControlador();
        añoSpinner.setValue(new Date().getYear() + 1900);
        mesComboBox.setSelectedIndex(new Date().getMonth());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        mesComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        añoSpinner = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        principalTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        nuevoButton = new javax.swing.JButton();
        eliminarButton = new javax.swing.JButton();
        editarButton = new javax.swing.JButton();
        guardarButton = new javax.swing.JButton();
        edicionPanel = new javax.swing.JPanel();
        fechaDateChooserCombo = new datechooser.beans.DateChooserCombo();
        jLabel54 = new javax.swing.JLabel();
        horaSpinner = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        _15cmsFloatField = new util.FloatField();
        _30cmsFloatField = new util.FloatField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Monitoreo de Humedad Del Suelo");
        setMaximumSize(new java.awt.Dimension(2147483647, 600));
        setMinimumSize(new java.awt.Dimension(837, 530));

        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Mes");

        mesComboBox.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mesComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Marzo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        mesComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mesComboBoxItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Año");

        añoSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(2014), Integer.valueOf(2012), null, Integer.valueOf(1)));
        añoSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                añoSpinnerStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(mesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(añoSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(añoSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        principalTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        principalTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Fecha", "Hora", "15 cms", "30 cms"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        principalTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(principalTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guardarButton)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(editarButton)
                        .addComponent(eliminarButton)
                        .addComponent(nuevoButton)))
                .addContainerGap())
        );

        fechaDateChooserCombo.setCurrentView(new datechooser.view.appearance.AppearancesList("Swing",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    fechaDateChooserCombo.setCalendarPreferredSize(new java.awt.Dimension(330, 210));
    fechaDateChooserCombo.setNothingAllowed(false);
    fechaDateChooserCombo.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
    fechaDateChooserCombo.setMinDate(new java.util.GregorianCalendar(2012, 0, 1));
    fechaDateChooserCombo.setNavigateFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11));
    fechaDateChooserCombo.setBehavior(datechooser.model.multiple.MultyModelBehavior.SELECT_SINGLE);

    jLabel54.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel54.setText("Fecha");

    horaSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    horaSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.HOUR_OF_DAY));
    horaSpinner.setEditor(new javax.swing.JSpinner.DateEditor(horaSpinner, "HH:mm"));

    jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel11.setText("Hora");

    jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel12.setText("15 Cms");

    jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel13.setText("30 Cms");

    _15cmsFloatField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

    _30cmsFloatField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

    javax.swing.GroupLayout edicionPanelLayout = new javax.swing.GroupLayout(edicionPanel);
    edicionPanel.setLayout(edicionPanelLayout);
    edicionPanelLayout.setHorizontalGroup(
        edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(edicionPanelLayout.createSequentialGroup()
            .addGap(36, 36, 36)
            .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel54)
                .addComponent(jLabel11)
                .addComponent(jLabel13)
                .addComponent(jLabel12))
            .addGap(25, 25, 25)
            .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(fechaDateChooserCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(_30cmsFloatField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(_15cmsFloatField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(horaSpinner, javax.swing.GroupLayout.Alignment.LEADING)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    edicionPanelLayout.setVerticalGroup(
        edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(edicionPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel54)
                .addComponent(fechaDateChooserCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(horaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel11))
            .addGap(18, 18, 18)
            .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel12)
                .addComponent(_15cmsFloatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(26, 26, 26)
            .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel13)
                .addComponent(_30cmsFloatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(14, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(edicionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(edicionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mesComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mesComboBoxItemStateChanged
        // TODO add your handling code here:
        cargarTablaPrincipal();
    }//GEN-LAST:event_mesComboBoxItemStateChanged

    private void añoSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_añoSpinnerStateChanged
        // TODO add your handling
        cargarTablaPrincipal();
    }//GEN-LAST:event_añoSpinnerStateChanged

    private void nuevoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoButtonActionPerformed
        // TODO add your handling code here:
        registroSeleccionado = null;
        guardar(true);
        cargarDatosRegistroSeleccionado();
    }//GEN-LAST:event_nuevoButtonActionPerformed

    private void eliminarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarButtonActionPerformed
        // TODO add your handling code here:
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
        guardar(false);
    }//GEN-LAST:event_eliminarButtonActionPerformed

    private void editarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarButtonActionPerformed
        // TODO add your handling code here:
        guardar(false);
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
        boolean transacionRealizada = false;
        if (registroSeleccionado == null) {
            float _15 = 0, _30 = 0;
            try {
                _15 = Float.parseFloat(_15cmsFloatField.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "La humedad del suelo en 15 cms no es un número real.", "Error de dato", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            try {
                _30 = Float.parseFloat(_30cmsFloatField.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "La humedad del suelo en 30 cms no es un número real.", "Error de dato", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            HumedadDelSuelo nueva = controlador.nuevo(fechaDateChooserCombo.getSelectedDate().getTime(), _30, _15, (Date) horaSpinner.getValue());
            if (controlador.validar(nueva, true)) {
                controlador.guardar(nueva);
                transacionRealizada = true;
            }
        } else {
            float _15 = 0, _30 = 0;
            try {
                _15 = Float.parseFloat(_15cmsFloatField.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "La humedad del suelo en 15 cms no es un número real.", "Error de dato", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            try {
                _30 = Float.parseFloat(_30cmsFloatField.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "La humedad del suelo en 30 cms no es un número real.", "Error de dato", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            registroSeleccionado.setFecha(fechaDateChooserCombo.getSelectedDate().getTime());
            registroSeleccionado.setHora((Date) horaSpinner.getValue());
            registroSeleccionado.set15Cms(_15);
            registroSeleccionado.set30Cms(_30);
            if (controlador.validar(registroSeleccionado, false)) {
                try {
                    controlador.editar(registroSeleccionado);
                    transacionRealizada = true;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "El registro ya no existe.", "Error al eliminar", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        if (transacionRealizada) {
            guardar(false);
            cargarTablaPrincipal();
        }
    }//GEN-LAST:event_guardarButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private util.FloatField _15cmsFloatField;
    private util.FloatField _30cmsFloatField;
    private javax.swing.JSpinner añoSpinner;
    private javax.swing.JPanel edicionPanel;
    private javax.swing.JButton editarButton;
    private javax.swing.JButton eliminarButton;
    private datechooser.beans.DateChooserCombo fechaDateChooserCombo;
    private javax.swing.JButton guardarButton;
    private javax.swing.JSpinner horaSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox mesComboBox;
    private javax.swing.JButton nuevoButton;
    private javax.swing.JTable principalTable;
    // End of variables declaration//GEN-END:variables

    private void cargarTablaPrincipal() {
        for (int i = 0; i < principalTable.getRowCount(); i++) {
            for (int j = 0; j < principalTable.getColumnCount(); j++) {
                principalTable.getModel().setValueAt(null, i, j);
            }
        }
        Date esteMes = new Date((int) añoSpinner.getValue() - 1900, (int) mesComboBox.getSelectedIndex(), 1);
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(esteMes);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        Date siguienteMes = c.getTime();
        lista = controlador.buscarLista(esteMes, siguienteMes);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy", new Locale("es", "CO"));
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
        for (int i = 0; i < lista.size(); i++) {
            HumedadDelSuelo t = lista.get(i);
            principalTable.getModel().setValueAt(dateFormat.format(t.getFecha()), i, 0);
            principalTable.getModel().setValueAt(timeFormat.format(t.getHora()), i, 1);
            principalTable.getModel().setValueAt(t.get15Cms(), i, 2);
            principalTable.getModel().setValueAt(t.get30Cms(), i, 3);
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
            fechaDateChooserCombo.setSelectedDate(GregorianCalendar.getInstance());
            horaSpinner.setValue(new Date());
            _15cmsFloatField.setText("0.0");
            _30cmsFloatField.setText("0.0");
        } else {
            Calendar c = GregorianCalendar.getInstance();
            c.setTime(registroSeleccionado.getFecha());
            fechaDateChooserCombo.setSelectedDate(c);
            horaSpinner.setValue(registroSeleccionado.getHora());
            _15cmsFloatField.setText(registroSeleccionado.get15Cms() + "");
            _30cmsFloatField.setText(registroSeleccionado.get30Cms() + "");
        }
    }
}
