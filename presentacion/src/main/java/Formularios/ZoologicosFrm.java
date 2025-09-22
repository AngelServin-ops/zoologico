/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;

import DAO.ZoologicoDAO;
import com.github.lgooddatepicker.components.DatePicker;
import entidades.Zoologico;
import interfaces.IZoologicoDAO;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Jesus
 */
public class ZoologicosFrm extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ZoologicosFrm.class.getName());

    private IZoologicoDAO zoologicoDAO;

    /**
     * Creates new form ZoologicosFrm
     */
    public ZoologicosFrm() {
        initComponents();
        zoologicoDAO = new ZoologicoDAO();

        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    mostrarDatosSeleccionados();
                }
            }
        });

        cargarZoologicosEnTabla();

        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarTabla();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarTabla();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        txtBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (txtBuscar.getText().trim().equals("Buscar")) {
                    txtBuscar.setText("");
                }
            }
        });
    }

    private void filtrarTabla() {

        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        jTable1.setRowSorter(sorter);

        String textoBusqueda = txtBuscar.getText().trim();

        if (!textoBusqueda.isEmpty()) {

            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textoBusqueda));
        } else {

            sorter.setRowFilter(null);
        }
    }

    private void cargarZoologicosEnTabla() {
        DefaultTableModel modeloTabla = (DefaultTableModel) jTable1.getModel();
        modeloTabla.setRowCount(0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            List<Zoologico> zoologicos = zoologicoDAO.consultarTodosLosZoologicos();
            for (Zoologico z : zoologicos) {
                String fechaFormateada = (z.getFecha_inauguracion() != null) ? dateFormat.format(z.getFecha_inauguracion()) : "N/A";

                Object[] fila = new Object[]{
                    z.getId_zoologico(),
                    z.getNombre(),
                    z.getCiudad(),
                    z.getPais(),
                    fechaFormateada
                };
                modeloTabla.addRow(fila);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar zoológicos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarDatosSeleccionados() {
        int filaSeleccionada = jTable1.getSelectedRow();
        if (filaSeleccionada != -1) {
            try {

                int idZoologico = (int) jTable1.getValueAt(filaSeleccionada, 0);

                Zoologico zoologico = zoologicoDAO.consultarZoologicoPorId(idZoologico);

                if (zoologico != null) {
                    txtId.setText(String.valueOf(zoologico.getId_zoologico()));
                    txtNombre.setText(zoologico.getNombre());
                    txtCiudad.setText(zoologico.getCiudad());
                    txtPais.setText(zoologico.getPais());

                    if (zoologico.getFecha_inauguracion() != null) {
                        datePicker1.setDate(zoologico.getFecha_inauguracion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    } else {
                        datePicker1.setDate(null);
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar los datos del zoológico: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtCiudad.setText("");
        txtPais.setText("");
        datePicker1.setDate(null); // Limpiar el DatePicker
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblZoologico = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblCiudad = new javax.swing.JLabel();
        lblPais = new javax.swing.JLabel();
        lblFechaInauguracion = new javax.swing.JLabel();
        txtCiudad = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtPais = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnLimpiarCampo = new javax.swing.JButton();
        datePicker1 = new com.github.lgooddatepicker.components.DatePicker();
        btnAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblZoologico.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblZoologico.setText("Zoologico");

        lblId.setText("Id:");

        lblNombre.setText("Nombre:");

        lblCiudad.setText("Ciudad:");

        lblPais.setText("Pais:");

        lblFechaInauguracion.setText("Fecha de inauguracion:");

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id_zoologico", "Nombre", "Ciudad", "Pais", "Fecha de inauguracion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        txtBuscar.setText("Buscar");
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });

        btnLimpiarCampo.setText("Limpiar Campos");
        btnLimpiarCampo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarCampoActionPerformed(evt);
            }
        });

        btnAtras.setText("<");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(lblZoologico, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblFechaInauguracion)
                                .addComponent(lblCiudad)
                                .addComponent(lblNombre)
                                .addComponent(lblPais)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(lblId)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAtras)
                                .addGap(104, 104, 104)))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(datePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNombre)
                            .addComponent(txtCiudad)
                            .addComponent(txtPais)
                            .addComponent(txtId)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(btnLimpiarCampo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEditar)
                            .addComponent(btnAgregar))
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnConsultar)
                            .addComponent(btnEliminar))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 733, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnAtras)
                        .addGap(3, 3, 3)
                        .addComponent(lblZoologico, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblId)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCiudad)
                            .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPais)
                            .addComponent(txtPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFechaInauguracion)
                            .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregar)
                            .addComponent(btnEliminar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEditar)
                            .addComponent(btnConsultar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLimpiarCampo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            if (txtNombre.getText().isEmpty() || txtCiudad.getText().isEmpty() || txtPais.getText().isEmpty() || datePicker1.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Zoologico zoologico = new Zoologico();
            zoologico.setNombre(txtNombre.getText());
            zoologico.setCiudad(txtCiudad.getText());
            zoologico.setPais(txtPais.getText());

            Date fechaInauguracion = Date.from(datePicker1.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            zoologico.setFecha_inauguracion(fechaInauguracion);

            zoologicoDAO.agregarZoologico(zoologico);
            limpiarCampos();
            cargarZoologicosEnTabla();
            JOptionPane.showMessageDialog(this, "Zoológico agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar zoológico: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            logger.severe("Error al agregar zoológico: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int filaSeleccionada = jTable1.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un zoológico de la tabla para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            if (txtNombre.getText().isEmpty() || txtCiudad.getText().isEmpty() || txtPais.getText().isEmpty() || datePicker1.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idZoologico = (int) jTable1.getValueAt(filaSeleccionada, 0);

            Zoologico zoologico = new Zoologico();
            zoologico.setId_zoologico(idZoologico);
            zoologico.setNombre(txtNombre.getText());
            zoologico.setCiudad(txtCiudad.getText());
            zoologico.setPais(txtPais.getText());

            Date fechaInauguracion = Date.from(datePicker1.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            zoologico.setFecha_inauguracion(fechaInauguracion);

            zoologicoDAO.actualizarZoologico(zoologico);
            limpiarCampos();
            cargarZoologicosEnTabla();
            JOptionPane.showMessageDialog(this, "Zoológico actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al editar zoológico: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            logger.severe("Error al editar zoológico: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaSeleccionada = jTable1.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un zoológico de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar este zoológico?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                int idZoologico = (int) jTable1.getValueAt(filaSeleccionada, 0);
                Zoologico zoologico = new Zoologico();
                zoologico.setId_zoologico(idZoologico);

                zoologicoDAO.eliminarZoologico(zoologico);
                limpiarCampos();
                cargarZoologicosEnTabla();
                JOptionPane.showMessageDialog(this, "Zoológico eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar zoológico: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                logger.severe("Error al eliminar zoológico: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        cargarZoologicosEnTabla();
        JOptionPane.showMessageDialog(this, "Tabla de zoológicos actualizada.", "Consulta Exitosa", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnLimpiarCampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarCampoActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarCampoActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // Crea una instancia de la pantalla del menú principal
        MenuPrincipalFrm menuPrincipal = new MenuPrincipalFrm();

        // Hace visible la pantalla del menú principal
        menuPrincipal.setVisible(true);

        // Cierra la ventana actual (AnimalesFrm o la ventana que contenga este botón)
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        filtrarTabla();
    }//GEN-LAST:event_txtBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiarCampo;
    private com.github.lgooddatepicker.components.DatePicker datePicker1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCiudad;
    private javax.swing.JLabel lblFechaInauguracion;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPais;
    private javax.swing.JLabel lblZoologico;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPais;
    // End of variables declaration//GEN-END:variables
}
