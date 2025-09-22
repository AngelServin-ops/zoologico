/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;

import DAO.AnimalDAO;
import static com.mysql.cj.conf.PropertyKey.logger;
import entidades.Animal;
import entidades.Animal.Sexo;
import entidades.Especie;
import entidades.Zoologico;
import interfaces.IAnimalDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import javax.swing.DefaultComboBoxModel;
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
public class AnimalesFrm extends javax.swing.JFrame {

    private IAnimalDAO animalDAO;

    /**
     * Creates new form AnimalesFrm
     */
    public AnimalesFrm() {
        initComponents();
        animalDAO = new AnimalDAO();
        cargarOpcionesComboBox();
        cargarAnimalesEnTabla();

        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    mostrarDatosSeleccionados();
                }
            }
        });

        // Implementación de la funcionalidad de búsqueda
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
                // No se usa para JTextfield
            }
        });

        // **Lógica para limpiar el texto al hacer clic**
        txtBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Verifica si el texto actual es el texto por defecto
                if (txtBuscar.getText().equals("Buscar")) {
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

    private void mostrarDatosSeleccionados() {
        int filaSeleccionada = jTable1.getSelectedRow();
        if (filaSeleccionada != -1) {
            try {
                int idAnimal = (int) jTable1.getValueAt(filaSeleccionada, 0);
                Animal animal = animalDAO.consultarAnimalPorId(idAnimal);

                if (animal != null) {
                    txtIdAnimal.setText(String.valueOf(animal.getId_animal()));
                    txtIdentificacion.setText(animal.getIdentificacion());
                    comboBoxSexo.setSelectedItem(animal.getSexo());
                    txtAñoNacimiento.setText(String.valueOf(animal.getAnio_nacimiento()));
                    // Falta cargar Especie y Zoologico, si hay campos para ellos.
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar los datos del animal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cargarOpcionesComboBox() {
        DefaultComboBoxModel<String> modeloSexo = new DefaultComboBoxModel<>();
        modeloSexo.addElement("MACHO");
        modeloSexo.addElement("HEMBRA");
        comboBoxSexo.setModel(modeloSexo);
    }

    private void cargarAnimalesEnTabla() {
        DefaultTableModel modeloTabla = (DefaultTableModel) jTable1.getModel();

        modeloTabla.setRowCount(0);

        try {
            List<Animal> animales = animalDAO.consultarTodosLosAnimales();

            for (Animal animal : animales) {
                Object[] fila = new Object[6];
                fila[0] = animal.getId_animal();
                fila[1] = animal.getIdentificacion();
                fila[2] = animal.getSexo();
                fila[3] = animal.getAnio_nacimiento();

                fila[4] = (animal.getEspecie() != null) ? animal.getEspecie().getId_especie() : null;
                fila[5] = (animal.getZoologico() != null) ? animal.getZoologico().getId_zoologico() : null;

                modeloTabla.addRow(fila);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar animales: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblIdAnimal = new javax.swing.JLabel();
        lblIdentificacion = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        txtIdAnimal = new javax.swing.JTextField();
        txtIdentificacion = new javax.swing.JTextField();
        lblAñoNacimiento = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnLimpiarCampos = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        lblTitulo = new javax.swing.JLabel();
        comboBoxSexo = new javax.swing.JComboBox<>();
        txtAñoNacimiento = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id_animal", "identificacion", "sexo", "año de nacimiento", "id_especie", "id_zoologico"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        lblIdAnimal.setText("id_animal");

        lblIdentificacion.setText("Identificacion");

        lblSexo.setText("Sexo");

        txtIdAnimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdAnimalActionPerformed(evt);
            }
        });

        txtIdentificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdentificacionActionPerformed(evt);
            }
        });

        lblAñoNacimiento.setText("Año de Nacimiento");

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

        btnLimpiarCampos.setText("Limpiar Campos");
        btnLimpiarCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarCamposActionPerformed(evt);
            }
        });

        btnAtras.setText("<");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        txtBuscar.setText("Buscar");
        txtBuscar.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        txtBuscar.setSelectedTextColor(new java.awt.Color(204, 204, 204));
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitulo.setText("Animales");

        comboBoxSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBoxSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxSexoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAtras)
                                .addGap(32, 32, 32)
                                .addComponent(lblTitulo)
                                .addGap(143, 143, 143))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblIdentificacion)
                                            .addComponent(lblSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblIdAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(49, 49, 49))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblAñoNacimiento)
                                        .addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(txtAñoNacimiento))
                                    .addComponent(txtIdAnimal)
                                    .addComponent(txtIdentificacion)
                                    .addComponent(comboBoxSexo, 0, 174, Short.MAX_VALUE)))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnLimpiarCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(48, 48, 48)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(btnAtras))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(lblTitulo)))
                        .addGap(7, 7, 7))
                    .addComponent(txtBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIdAnimal)
                            .addComponent(txtIdAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIdentificacion)
                            .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSexo)
                            .addComponent(comboBoxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAñoNacimiento)
                            .addComponent(txtAñoNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                            .addComponent(btnConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLimpiarCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdAnimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdAnimalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdAnimalActionPerformed

    private void txtIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificacionActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            if (txtIdentificacion.getText().isEmpty() || comboBoxSexo.getSelectedItem() == null || txtAñoNacimiento.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos: Identificación, Sexo y Año de Nacimiento.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int anioNacimiento;
            try {

                anioNacimiento = Integer.parseInt(txtAñoNacimiento.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El 'Año de Nacimiento' debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Sexo sexo = Sexo.valueOf(comboBoxSexo.getSelectedItem().toString().toUpperCase());

            Animal animal = new Animal();
            animal.setIdentificacion(txtIdentificacion.getText());
            animal.setSexo(sexo);
            animal.setAnio_nacimiento(anioNacimiento);

            animalDAO.agregarAnimal(animal);

            limpiarCampos();
            cargarAnimalesEnTabla();

            JOptionPane.showMessageDialog(this, "Animal agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar animal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int filaSeleccionada = jTable1.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un animal de la tabla para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            if (txtIdentificacion.getText().isEmpty() || comboBoxSexo.getSelectedItem() == null || txtAñoNacimiento.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, completa los campos de Identificación, Sexo y Año de Nacimiento.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int anioNacimiento;
            try {

                anioNacimiento = Integer.parseInt(txtAñoNacimiento.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El 'Año de Nacimiento' debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Sexo sexo = Sexo.valueOf(comboBoxSexo.getSelectedItem().toString().toUpperCase());

            int idAnimal = (int) jTable1.getValueAt(filaSeleccionada, 0);

            Animal animal = new Animal();
            animal.setId_animal(idAnimal);
            animal.setIdentificacion(txtIdentificacion.getText());
            animal.setSexo(sexo);
            animal.setAnio_nacimiento(anioNacimiento);

            animalDAO.actualizarAnimal(animal);

            limpiarCampos();
            cargarAnimalesEnTabla();

            JOptionPane.showMessageDialog(this, "Animal actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al editar animal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaSeleccionada = jTable1.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un animal de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar este animal?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            try {

                int idAnimal = (int) jTable1.getValueAt(filaSeleccionada, 0);

                Animal animal = new Animal();
                animal.setId_animal(idAnimal);

                animalDAO.eliminarAnimal(animal);

                limpiarCampos();
                cargarAnimalesEnTabla();

                JOptionPane.showMessageDialog(this, "Animal eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar animal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        cargarAnimalesEnTabla();

        JOptionPane.showMessageDialog(this, "Tabla de animales actualizada.", "Consulta Exitosa", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void limpiarCampos() {
        txtIdAnimal.setText("");
        txtIdentificacion.setText("");
        txtAñoNacimiento.setText("");

    }
    private void btnLimpiarCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarCamposActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarCamposActionPerformed


    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed

        MenuPrincipalFrm menuPrincipal = new MenuPrincipalFrm();

        menuPrincipal.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        filtrarTabla();
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void comboBoxSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxSexoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnLimpiarCampos;
    private javax.swing.JComboBox<String> comboBoxSexo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblAñoNacimiento;
    private javax.swing.JLabel lblIdAnimal;
    private javax.swing.JLabel lblIdentificacion;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtAñoNacimiento;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtIdAnimal;
    private javax.swing.JTextField txtIdentificacion;
    // End of variables declaration//GEN-END:variables
}
