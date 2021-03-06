/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.produccion.variablesClimaticas;

import controlador.produccion.variablesClimaticas.LluviaControlador;
import datos.exceptions.NonexistentEntityException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.produccion.variablesClimaticas.Lluvia;
import modelo.util.DateTools;
import modelo.util.TableColumnAdjuster;

/**
 *
 * @author fredy
 */
public class LluviasIF extends javax.swing.JInternalFrame {

    /**
     * Creates new form LluviasIF
     */
    private Lluvia registroSeleccionado = null;
    private LluviaControlador controlador;
    private List<Lluvia> lista;

    public LluviasIF() {
        initComponents();
        controlador = new LluviaControlador();
        añoSpinner.setValue(DateTools.getYear());
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
        jLabel2 = new javax.swing.JLabel();
        añoSpinner = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
        nuevoButton = new javax.swing.JButton();
        eliminarButton = new javax.swing.JButton();
        editarButton = new javax.swing.JButton();
        guardarButton = new javax.swing.JButton();
        edicionPanel = new javax.swing.JPanel();
        fechaDateChooserCombo = new datechooser.beans.DateChooserCombo();
        jLabel54 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        mmsFloatField = new modelo.util.FloatField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        principalTable = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Control de Lluvias");
        setMinimumSize(new java.awt.Dimension(1259, 548));

        jPanel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Año");

        añoSpinner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        añoSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(2014), Integer.valueOf(2012), null, Integer.valueOf(1)));
        añoSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                añoSpinnerStateChanged(evt);
            }
        });

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

    jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel12.setText("Milímetros De Lluvia");

    javax.swing.GroupLayout edicionPanelLayout = new javax.swing.GroupLayout(edicionPanel);
    edicionPanel.setLayout(edicionPanelLayout);
    edicionPanelLayout.setHorizontalGroup(
        edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(edicionPanelLayout.createSequentialGroup()
            .addGap(36, 36, 36)
            .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel54)
                .addComponent(jLabel12))
            .addGap(25, 25, 25)
            .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(fechaDateChooserCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(mmsFloatField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(281, Short.MAX_VALUE))
    );
    edicionPanelLayout.setVerticalGroup(
        edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(edicionPanelLayout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel54)
                .addComponent(fechaDateChooserCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel12)
                .addComponent(mmsFloatField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
    );

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(57, 57, 57)
            .addComponent(jLabel2)
            .addGap(18, 18, 18)
            .addComponent(añoSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(69, 69, 69)
            .addComponent(edicionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(44, 44, 44)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(añoSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(edicionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
    );

    principalTable.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    principalTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {"Enero", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
            {"Febrero", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
            {"Marzo", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
            {"Abril", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
            {"Mayo", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
            {"Junio", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
            {"Julio", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
            {"Agosto", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
            {"Septiembre", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
            {"Octubre", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
            {"Noviembre", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
            {"Diciembre", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
        },
        new String [] {
            "Mes", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "Total"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
        };
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    principalTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
    TableColumnAdjuster tca = new TableColumnAdjuster(principalTable);
    tca.adjustColumns();
    principalTable.setRowSelectionAllowed(false);
    principalTable.getTableHeader().setReorderingAllowed(false);
    jScrollPane1.setViewportView(principalTable);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1)
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        if (principalTable.getSelectedRow() == -1 || principalTable.getSelectedRow() < 1) {
            JOptionPane.showMessageDialog(null, "No hay un registro seleccionado, por favor seleccione uno.", "Registro no seleccionado", JOptionPane.INFORMATION_MESSAGE);
        } else if (principalTable.getValueAt(principalTable.getSelectedRow(), principalTable.getSelectedColumn()) == null) {
            JOptionPane.showMessageDialog(null, "No hay un registro válido seleccionado, por favor seleccione uno.", "Registro no seleccionado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Calendar cal = GregorianCalendar.getInstance();
            for (Lluvia c : lista) {
                cal.setTime(c.getFecha());
                if (cal.get(Calendar.MONTH) == principalTable.getSelectedRow()
                        && cal.get(Calendar.DAY_OF_MONTH) == principalTable.getSelectedColumn()) {
                    registroSeleccionado = c;
                    break;
                }
            }
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
        if (principalTable.getSelectedRow() == -1 || principalTable.getSelectedRow() < 1) {
            JOptionPane.showMessageDialog(null, "No hay un registro seleccionado, por favor seleccione uno.", "Registro no seleccionado", JOptionPane.INFORMATION_MESSAGE);
        } else if (principalTable.getValueAt(principalTable.getSelectedRow(), 0) == null) {
            JOptionPane.showMessageDialog(null, "No hay un registro válido seleccionado, por favor seleccione uno.", "Registro no seleccionado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Calendar cal = GregorianCalendar.getInstance();
            for (Lluvia c : lista) {
                cal.setTime(c.getFecha());
                if (cal.get(Calendar.MONTH) == principalTable.getSelectedRow()
                        && cal.get(Calendar.DAY_OF_MONTH) == principalTable.getSelectedColumn()) {
                    registroSeleccionado = c;
                    break;
                }
            }
            guardar(true);
        }
        cargarDatosRegistroSeleccionado();
    }//GEN-LAST:event_editarButtonActionPerformed

    private void guardarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarButtonActionPerformed
        // TODO add your handling code here:
        boolean transacionRealizada = false;
        float mms = mmsFloatField.getFloat();
        if (registroSeleccionado == null) {
            Lluvia nueva = controlador.nuevo(fechaDateChooserCombo.getSelectedDate().getTime(), mms);
            if (controlador.validar(nueva, true)) {
                controlador.guardar(nueva);
                transacionRealizada = true;
            }
        } else {
            registroSeleccionado.setFecha(fechaDateChooserCombo.getSelectedDate().getTime());
            registroSeleccionado.setMm(mms);
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
    private javax.swing.JSpinner añoSpinner;
    private javax.swing.JPanel edicionPanel;
    private javax.swing.JButton editarButton;
    private javax.swing.JButton eliminarButton;
    private datechooser.beans.DateChooserCombo fechaDateChooserCombo;
    private javax.swing.JButton guardarButton;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private modelo.util.FloatField mmsFloatField;
    private javax.swing.JButton nuevoButton;
    private javax.swing.JTable principalTable;
    // End of variables declaration//GEN-END:variables

    private void cargarTablaPrincipal() {
        for (int i = 0; i < principalTable.getRowCount(); i++) {
            for (int j = 1; j < principalTable.getColumnCount(); j++) {
                principalTable.getModel().setValueAt(null, i, j);
            }
        }
        Date esteMes = DateTools.getDate((int) añoSpinner.getValue(), 0, 1);
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(esteMes);
        c.add(Calendar.YEAR, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        Date siguienteMes = c.getTime();
        lista = controlador.buscarLista(esteMes, siguienteMes);
        for (int i = 0; i < lista.size(); i++) {
            Lluvia t = lista.get(i);
            c.setTime(t.getFecha());
            principalTable.getModel().setValueAt(t.getMm(), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        }
        float suma = 0;
        for (int i = 0; i < 12; i++) {
            suma = 0;
            for (int j = 1; j <= 31; j++) {
                if (principalTable.getValueAt(i, j) != null) {
                    suma += (Float) principalTable.getValueAt(i, j);
                }
            }
            principalTable.getModel().setValueAt(suma, i, 32);
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
            mmsFloatField.setFloat(null);
        } else {
            Calendar c = GregorianCalendar.getInstance();
            c.setTime(registroSeleccionado.getFecha());
            fechaDateChooserCombo.setSelectedDate(c);
            mmsFloatField.setFloat(registroSeleccionado.getMm());
        }
    }
}
