/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;

import DAO.EspecieDAO;
import entidades.Especie;
import interfaces.IEspecieDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Jesus
 */
public class EspeciesFrm extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EspeciesFrm.class.getName());

    private final IEspecieDAO especieDAO;
    private DefaultTableModel modeloTabla;

    /**
     * Creates new form EspeciesFrm
     */
    public EspeciesFrm() {
        initComponents();
        this.especieDAO = new EspecieDAO();
        inicializarTabla();
        inicializarComboPeligroExtincion();
        cargarEspeciesEnTabla();

        // Agregar el ListSelectionListener
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarDatosSeleccionados();
            }
        });

        // Añadir un MouseListener al txtBuscar para limpiar el texto al hacer clic
        txtBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (txtBuscar.getText().trim().equals("Buscar")) {
                    txtBuscar.setText("");
                }
            }
        });

        // Opcional: Agregar el DocumentListener para la búsqueda en tiempo real
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
                // Este método no es relevante para JTextFields planos, se deja vacío
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

    private void mostrarDatosSeleccionados() {
        int filaSeleccionada = jTable1.getSelectedRow();
        if (filaSeleccionada != -1) {
            try {
                int idEspecie = (int) jTable1.getValueAt(filaSeleccionada, 0);
                Especie especie = especieDAO.consultarEspeciePorId(idEspecie);

                if (especie != null) {
                    txtIdEspecie.setText(String.valueOf(especie.getId_especie()));
                    txtNombreVulgar.setText(especie.getNombre_vulgar());
                    txtNombreCientifico.setText(especie.getNombre_cientifico());
                    txtFamilia.setText(especie.getFamilia());

                    // Cargar el JComboBox
                    if (especie.getPeligro_extincion()) {
                        comboBoxPeligroExtencion.setSelectedItem("Sí");
                    } else {
                        comboBoxPeligroExtencion.setSelectedItem("No");
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar los datos de la especie: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                logger.log(Level.SEVERE, "Error al cargar los datos de la especie", ex);
            }
        }
    }

    private void inicializarTabla() {
        modeloTabla = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "id_especie", "Nombre vulgar", "Nombre científico", "Familia", "Peligro de extinción"
                }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable1.setModel(modeloTabla);
    }

    private void cargarEspeciesEnTabla() {
        try {
            modeloTabla.setRowCount(0);
            List<Especie> especies = especieDAO.consultarTodasLasEspecies();
            for (Especie especie : especies) {
                modeloTabla.addRow(new Object[]{
                    especie.getId_especie(),
                    especie.getNombre_vulgar(),
                    especie.getNombre_cientifico(),
                    especie.getFamilia(),
                    especie.getPeligro_extincion()
                });
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al cargar las especies", ex);
            JOptionPane.showMessageDialog(this, "Error al cargar las especies: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtIdEspecie.setText("");
        txtNombreVulgar.setText("");
        txtNombreCientifico.setText("");
        txtFamilia.setText("");

    }

    private void inicializarComboPeligroExtincion() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("No");
        model.addElement("Sí");

        comboBoxPeligroExtencion.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblId = new javax.swing.JLabel();
        lblNombreVulgar = new javax.swing.JLabel();
        lblNombreCientifico = new javax.swing.JLabel();
        lblFamilia = new javax.swing.JLabel();
        lblPeligroExtincion = new javax.swing.JLabel();
        txtIdEspecie = new javax.swing.JTextField();
        txtNombreVulgar = new javax.swing.JTextField();
        txtNombreCientifico = new javax.swing.JTextField();
        txtFamilia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnLimpiarCampos = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        lblTitulo = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        comboBoxPeligroExtencion = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblId.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblId.setText("Id_especie");

        lblNombreVulgar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblNombreVulgar.setText("Nombre vulgar:");

        lblNombreCientifico.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblNombreCientifico.setText("Nombre cientifico:");

        lblFamilia.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblFamilia.setText("Familia:");

        lblPeligroExtincion.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        lblPeligroExtincion.setText("Peligro de extinción:");

        txtIdEspecie.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        txtIdEspecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdEspecieActionPerformed(evt);
            }
        });

        txtNombreVulgar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        txtNombreVulgar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreVulgarActionPerformed(evt);
            }
        });

        txtNombreCientifico.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        txtNombreCientifico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreCientificoActionPerformed(evt);
            }
        });

        txtFamilia.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        txtFamilia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFamiliaActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id_especie", "Nombre vulgar", "Nombre cientifico", "Familia", "Peligro de extinción"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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

        btnAgregar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEditar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnConsultar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        btnLimpiarCampos.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnLimpiarCampos.setText("Limpiar Campos");
        btnLimpiarCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarCamposActionPerformed(evt);
            }
        });

        txtBuscar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        txtBuscar.setText("Buscar");
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        lblTitulo.setText("Especies");

        btnAtras.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        btnAtras.setText("<");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        comboBoxPeligroExtencion.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        comboBoxPeligroExtencion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxPeligroExtencion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxPeligroExtencionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombreVulgar)
                                    .addComponent(lblNombreCientifico)
                                    .addComponent(lblFamilia)
                                    .addComponent(lblPeligroExtincion))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFamilia, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtNombreCientifico, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtNombreVulgar)
                                    .addComponent(comboBoxPeligroExtencion, 0, 156, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblId)
                                .addGap(104, 104, 104)
                                .addComponent(txtIdEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnAtras)
                        .addGap(102, 102, 102)
                        .addComponent(lblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLimpiarCampos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                    .addComponent(txtBuscar))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                    .addComponent(btnAtras))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblId)
                            .addComponent(txtIdEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombreVulgar)
                            .addComponent(txtNombreVulgar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombreCientifico)
                            .addComponent(txtNombreCientifico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFamilia)
                            .addComponent(txtFamilia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPeligroExtincion)
                            .addComponent(comboBoxPeligroExtencion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEditar)
                            .addComponent(btnAgregar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminar)
                            .addComponent(btnConsultar))
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiarCampos)
                        .addGap(83, 83, 83))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdEspecieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdEspecieActionPerformed

    private void txtNombreVulgarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreVulgarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreVulgarActionPerformed

    private void txtNombreCientificoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreCientificoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreCientificoActionPerformed

    private void txtFamiliaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFamiliaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFamiliaActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int filaSeleccionada = jTable1.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una especie de la tabla para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            if (txtNombreVulgar.getText().isEmpty() || txtNombreCientifico.getText().isEmpty() || txtFamilia.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = Integer.parseInt(txtIdEspecie.getText());
            String nombreVulgar = txtNombreVulgar.getText();
            String nombreCientifico = txtNombreCientifico.getText();
            String familia = txtFamilia.getText();

            boolean peligroExtincion = comboBoxPeligroExtencion.getSelectedItem().toString().equalsIgnoreCase("Sí");

            Especie especieAEditar = new Especie(id, nombreVulgar, nombreCientifico, familia, peligroExtincion);
            especieDAO.actualizarEspecie(especieAEditar);

            JOptionPane.showMessageDialog(this, "Especie editada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarEspeciesEnTabla();
            limpiarCampos();
        } catch (SQLException | NumberFormatException ex) {
            logger.log(Level.SEVERE, "Error al editar la especie", ex);
            JOptionPane.showMessageDialog(this, "Error al editar la especie: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            String nombreVulgar = txtNombreVulgar.getText();
            String nombreCientifico = txtNombreCientifico.getText();
            String familia = txtFamilia.getText();

            boolean peligroExtincion = "Sí".equals(comboBoxPeligroExtencion.getSelectedItem().toString());

            Especie nuevaEspecie = new Especie(nombreVulgar, nombreCientifico, familia, peligroExtincion);
            especieDAO.agregarEspecie(nuevaEspecie);

            JOptionPane.showMessageDialog(this, "Especie agregada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarEspeciesEnTabla();
            limpiarCampos();
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al agregar la especie", ex);
            JOptionPane.showMessageDialog(this, "Error al agregar la especie: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // Get the index of the selected row in the table
        int filaSeleccionada = jTable1.getSelectedRow();

        // Check if a row is actually selected
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una especie de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Ask the user for confirmation before deleting
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar esta especie?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        // If the user confirms
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // Get the ID from the selected row's first column (index 0)
                int idEspecie = (int) jTable1.getValueAt(filaSeleccionada, 0);

                // Create a temporary Especie object with only the ID
                Especie especieAEliminar = new Especie();
                especieAEliminar.setId_especie(idEspecie);

                // Call the DAO's delete method
                especieDAO.eliminarEspecie(especieAEliminar);

                // Provide feedback to the user, clear fields, and refresh the table
                JOptionPane.showMessageDialog(this, "Especie eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                cargarEspeciesEnTabla();
            } catch (SQLException ex) {
                // Handle database-related errors
                JOptionPane.showMessageDialog(this, "Error al eliminar la especie: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                logger.log(Level.SEVERE, "Error al eliminar la especie", ex);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        try {
            int id = Integer.parseInt(txtIdEspecie.getText());
            Especie especieEncontrada = especieDAO.consultarEspeciePorId(id);

            if (especieEncontrada != null) {
                txtNombreVulgar.setText(especieEncontrada.getNombre_vulgar());
                txtNombreCientifico.setText(especieEncontrada.getNombre_cientifico());
                txtFamilia.setText(especieEncontrada.getFamilia());

                if (especieEncontrada.getPeligro_extincion()) {
                    comboBoxPeligroExtencion.setSelectedItem("Sí");
                } else {
                    comboBoxPeligroExtencion.setSelectedItem("No");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró ninguna especie con el ID especificado.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException | NumberFormatException ex) {
            logger.log(Level.SEVERE, "Error al consultar la especie", ex);
            JOptionPane.showMessageDialog(this, "Error al consultar la especie: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnLimpiarCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarCamposActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarCamposActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        filtrarTabla();    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // Crea una instancia de la pantalla del menú principal
        MenuPrincipalFrm menuPrincipal = new MenuPrincipalFrm();

        // Hace visible la pantalla del menú principal
        menuPrincipal.setVisible(true);

        // Cierra la ventana actual (AnimalesFrm o la ventana que contenga este botón)
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void comboBoxPeligroExtencionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxPeligroExtencionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxPeligroExtencionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new EspeciesFrm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiarCampos;
    private javax.swing.JComboBox<String> comboBoxPeligroExtencion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblFamilia;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNombreCientifico;
    private javax.swing.JLabel lblNombreVulgar;
    private javax.swing.JLabel lblPeligroExtincion;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtFamilia;
    private javax.swing.JTextField txtIdEspecie;
    private javax.swing.JTextField txtNombreCientifico;
    private javax.swing.JTextField txtNombreVulgar;
    // End of variables declaration//GEN-END:variables
}
