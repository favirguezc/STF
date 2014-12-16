/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.administracion;

import controlador.administracion.LoteControlador;
import controlador.administracion.ModuloControlador;
import dao.exceptions.NonexistentEntityException;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import modelo.administracion.Lote;
import modelo.administracion.Modulo;

/**
 *
 * @author fredy
 */
public class LoteIF extends javax.swing.JInternalFrame {

    /**
     * Creates new form LoteIF
     */
    private Lote registroSeleccionado = null;
    private Modulo registroModuloSeleccionado = null;
    private LoteControlador controlador;
    private ModuloControlador controladorModulo;
    private List<Lote> lista;
    DefaultListModel listModel = new DefaultListModel(), listModel2 = new DefaultListModel();

    public LoteIF() {
        initComponents();
        controlador = new LoteControlador();
        controladorModulo = new ModuloControlador();
        lotesList.setModel(listModel);
        modulosList.setModel(listModel2);
        guardarLote(false);
        guardarModulo(false);
        cargarListaLotes();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        lotesList = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        nuevoButton = new javax.swing.JButton();
        eliminarButton = new javax.swing.JButton();
        editarButton = new javax.swing.JButton();
        guardarButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        edicionPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nombreLoteTextField = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        modulosList = new javax.swing.JList();
        nuevoRolPersonaButton = new javax.swing.JButton();
        editarRolPersonaButton = new javax.swing.JButton();
        eliminarRolPersonaButton = new javax.swing.JButton();
        guardarRolPersonaButton = new javax.swing.JButton();
        edicionPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        nombreModuloTextField = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setTitle("Administración de Lotes");
        setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        setMinimumSize(new java.awt.Dimension(681, 508));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de lotes"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Lista de Lotes");

        lotesList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lotesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lotesList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel1)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        guardarButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/32x32/puzzle26.png"))); // NOI18N
        jButton1.setText("Módulos");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(guardarButton)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(editarButton)
                        .addComponent(eliminarButton)
                        .addComponent(nuevoButton)))
                .addGap(315, 315, 315))
        );

        edicionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Edición Lote"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nombre");

        nombreLoteTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout edicionPanelLayout = new javax.swing.GroupLayout(edicionPanel);
        edicionPanel.setLayout(edicionPanelLayout);
        edicionPanelLayout.setHorizontalGroup(
            edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edicionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nombreLoteTextField)
                .addContainerGap())
        );
        edicionPanelLayout.setVerticalGroup(
            edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edicionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(edicionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombreLoteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de módulos"));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Módulos");

        modulosList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        modulosList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(modulosList);

        nuevoRolPersonaButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nuevoRolPersonaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/16x16/add42.png"))); // NOI18N
        nuevoRolPersonaButton.setText("Nuevo");
        nuevoRolPersonaButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        nuevoRolPersonaButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        nuevoRolPersonaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoRolPersonaButtonActionPerformed(evt);
            }
        });

        editarRolPersonaButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        editarRolPersonaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/16x16/edit23.png"))); // NOI18N
        editarRolPersonaButton.setText("Editar");
        editarRolPersonaButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editarRolPersonaButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        editarRolPersonaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarRolPersonaButtonActionPerformed(evt);
            }
        });

        eliminarRolPersonaButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        eliminarRolPersonaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/16x16/delete27.png"))); // NOI18N
        eliminarRolPersonaButton.setText("Eliminar");
        eliminarRolPersonaButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eliminarRolPersonaButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        eliminarRolPersonaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarRolPersonaButtonActionPerformed(evt);
            }
        });

        guardarRolPersonaButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        guardarRolPersonaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/16x16/database13.png"))); // NOI18N
        guardarRolPersonaButton.setText("Guardar");
        guardarRolPersonaButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        guardarRolPersonaButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        guardarRolPersonaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarRolPersonaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jLabel4)
                .addContainerGap(218, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editarRolPersonaButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eliminarRolPersonaButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(guardarRolPersonaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nuevoRolPersonaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nuevoRolPersonaButton)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(editarRolPersonaButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(eliminarRolPersonaButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(guardarRolPersonaButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        edicionPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Edición Módulo"));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nombre");

        nombreModuloTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout edicionPanel1Layout = new javax.swing.GroupLayout(edicionPanel1);
        edicionPanel1.setLayout(edicionPanel1Layout);
        edicionPanel1Layout.setHorizontalGroup(
            edicionPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, edicionPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nombreModuloTextField)
                .addContainerGap())
        );
        edicionPanel1Layout.setVerticalGroup(
            edicionPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(edicionPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(edicionPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nombreModuloTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(edicionPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edicionPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edicionPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edicionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nuevoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoButtonActionPerformed
        // TODO add your handling code here:
        registroSeleccionado = null;
        guardarLote(true);
        cargarDatosRegistroSeleccionado();
    }//GEN-LAST:event_nuevoButtonActionPerformed

    private void eliminarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarButtonActionPerformed
        // TODO add your handling code here:
        if (lotesList.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "No hay un registro seleccionado, por favor seleccione uno.", "Registro no seleccionado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            registroSeleccionado = (Lote) lotesList.getSelectedValue();
            try {
                controlador.eliminar(registroSeleccionado.getId());
            } catch (NonexistentEntityException ex) {
                JOptionPane.showMessageDialog(null, "El registro ya no existe.", "Error al eliminar", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "El registro tiene datos relacionados.", "Error al eliminar", JOptionPane.INFORMATION_MESSAGE);
            }
            registroSeleccionado = null;
            cargarListaLotes();
        }
        guardarLote(false);
    }//GEN-LAST:event_eliminarButtonActionPerformed

    private void editarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarButtonActionPerformed
        // TODO add your handling code here:
        guardarLote(false);
        if (lotesList.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "No hay un registro seleccionado, por favor seleccione uno.", "Registro no seleccionado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            registroSeleccionado = (Lote) lotesList.getSelectedValue();
            guardarLote(true);
        }
        cargarDatosRegistroSeleccionado();
    }//GEN-LAST:event_editarButtonActionPerformed

    private void guardarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarButtonActionPerformed
        // TODO add your handling code here:
        boolean transacionRealizada = false;
        if (registroSeleccionado == null) {
            Lote nuevo = controlador.nuevo(nombreLoteTextField.getText());
            if (controlador.validar(nuevo, true)) {
                controlador.guardar(nuevo);
                transacionRealizada = true;
            }
        } else {
            registroSeleccionado.setNombre(nombreLoteTextField.getText());
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
            guardarLote(false);
        }
        cargarListaLotes();
    }//GEN-LAST:event_guardarButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (lotesList.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "No hay un registro seleccionado, por favor seleccione uno.", "Registro no seleccionado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            registroSeleccionado = (Lote) lotesList.getSelectedValue();
            cargarListaModulos();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void nuevoRolPersonaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoRolPersonaButtonActionPerformed
        // TODO add your handling code here:
        nombreModuloTextField.setText(null);
        registroModuloSeleccionado = null;
        guardarModulo(true);
    }//GEN-LAST:event_nuevoRolPersonaButtonActionPerformed

    private void editarRolPersonaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarRolPersonaButtonActionPerformed
        // TODO add your handling code here:
        registroModuloSeleccionado = null;
        guardarRolPersonaButton.setEnabled(false);
        if (modulosList.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "No hay un registro seleccionado, por favor seleccione uno.", "Registro no seleccionado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            registroModuloSeleccionado = (Modulo) modulosList.getSelectedValue();
            nombreModuloTextField.setText(registroModuloSeleccionado.getNombre());
            guardarModulo(true);
        }
    }//GEN-LAST:event_editarRolPersonaButtonActionPerformed

    private void eliminarRolPersonaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarRolPersonaButtonActionPerformed
        // TODO add your handling code here:
        registroModuloSeleccionado = null;
        if (modulosList.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "No hay un registro seleccionado, por favor seleccione uno.", "Registro no seleccionado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            registroModuloSeleccionado = (Modulo) modulosList.getSelectedValue();
            try {
                controladorModulo.eliminar(registroModuloSeleccionado.getId());
                cargarListaModulos();
            } catch (NonexistentEntityException ex) {
                JOptionPane.showMessageDialog(null, "El registro ya no existe.", "Error al eliminar", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "El registro tiene datos relacionados.", "Error al eliminar", JOptionPane.INFORMATION_MESSAGE);
            }
            registroModuloSeleccionado = null;
        }
        nombreModuloTextField.setText(null);
    }//GEN-LAST:event_eliminarRolPersonaButtonActionPerformed

    private void guardarRolPersonaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarRolPersonaButtonActionPerformed
        // TODO add your handling code here:
        //falta el área
        if (registroModuloSeleccionado == null) {
            Modulo nuevo = controladorModulo.nuevo(nombreModuloTextField.getText(),0, registroSeleccionado);
            if (controladorModulo.validar(nuevo)) {
                controladorModulo.guardar(nuevo);
                nombreModuloTextField.setText(null);
                guardarModulo(false);
            }
        } else {
            registroModuloSeleccionado.setNombre(nombreModuloTextField.getText());
            if (controladorModulo.validar(registroModuloSeleccionado)) {
                try {
                    controladorModulo.editar(registroModuloSeleccionado);
                    nombreModuloTextField.setText(null);
                    guardarModulo(false);
                } catch (Exception ex) {
                }
            }
        }
        cargarListaModulos();
    }//GEN-LAST:event_guardarRolPersonaButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel edicionPanel;
    private javax.swing.JPanel edicionPanel1;
    private javax.swing.JButton editarButton;
    private javax.swing.JButton editarRolPersonaButton;
    private javax.swing.JButton eliminarButton;
    private javax.swing.JButton eliminarRolPersonaButton;
    private javax.swing.JButton guardarButton;
    private javax.swing.JButton guardarRolPersonaButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList lotesList;
    private javax.swing.JList modulosList;
    private javax.swing.JTextField nombreLoteTextField;
    private javax.swing.JTextField nombreModuloTextField;
    private javax.swing.JButton nuevoButton;
    private javax.swing.JButton nuevoRolPersonaButton;
    // End of variables declaration//GEN-END:variables

    private void guardarLote(boolean b) {
        //edicionPanel.setVisible(b);
        nombreLoteTextField.setEnabled(b);
        guardarButton.setEnabled(b);
        if (!b) {
            registroSeleccionado = null;
        }
    }

    private void guardarModulo(boolean b) {
        //edicionPanel1.setVisible(b);
        nombreModuloTextField.setEnabled(b);
        guardarRolPersonaButton.setEnabled(b);
        if (!b) {
            registroModuloSeleccionado = null;
        }
    }

    private void cargarDatosRegistroSeleccionado() {
        if (registroSeleccionado == null) {
            nombreLoteTextField.setText(null);
        } else {
            nombreLoteTextField.setText(registroSeleccionado.getNombre());
        }
    }

    private void cargarListaLotes() {
        listModel.removeAllElements();
        lista = controlador.leerLista();
        lista.stream().forEach((l) -> {
            listModel.addElement(l);
        });
    }

    private void cargarListaModulos() {
        listModel2.removeAllElements();
        new ModuloControlador().leerLista(registroSeleccionado).stream().forEach((m) -> {
            listModel2.addElement(m);
        });
    }
}