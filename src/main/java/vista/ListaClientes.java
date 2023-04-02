/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controller.ClienteController;
import dto.CargoDTO;
import dto.ClienteDTO;
import dto.SupermercadoDTO;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import utils.StringUtils;

/**
 *
 * @author trian
 */
public class ListaClientes extends javax.swing.JFrame {

    private ClienteController controller = new ClienteController();

    private DefaultTableModel modeloTablaClientes = new DefaultTableModel() {
        public boolean isCellEditable(int F, int C) {
            return false;
        }
    };

    private ClienteDTO clienteSeleccionado = null;

    private Long idCliente;

    /**
     * Creates new form ListaClientes
     */
    public ListaClientes() {
        initComponents();
        // Centrar jframe
        setLocationRelativeTo(null);

        // Cargamos columnas de tabla de usuarios
        CargarColumnasUsuarios();

        // Cargar modelo de usuarios
        CargarModeloTablaUsuarios();
    }

    private void CargarColumnasUsuarios() {
        modeloTablaClientes.addColumn("Identificacion");
        modeloTablaClientes.addColumn("Primer Nombre");
        modeloTablaClientes.addColumn("Segundo Nombre");
        modeloTablaClientes.addColumn("Primer Apellido");
        modeloTablaClientes.addColumn("Segundo Apellido");
        modeloTablaClientes.addColumn("Telefono");
        modeloTablaClientes.addColumn("Correo");
        modeloTablaClientes.addColumn("Id Cargo");
        modeloTablaClientes.addColumn("Nombre Cargo");
        modeloTablaClientes.addColumn("Id Supermercado");
        modeloTablaClientes.addColumn("Nombre Supermercado");
    }

    private void limpiarTablaUsuarios() {
        int numFilas = modeloTablaClientes.getRowCount();
        if (numFilas > 0) {
            for (int i = numFilas - 1; i >= 0; i--) {
                modeloTablaClientes.removeRow(i);
            }
        }
    }

    private void CargarModeloTablaUsuarios() {
        limpiarTablaUsuarios();
        List<ClienteDTO> listaClientes = controller.obtenerClientes(null);
        int numeroUsuarios = listaClientes.size();
        modeloTablaClientes.setNumRows(numeroUsuarios);
        for (int i = 0; i < numeroUsuarios; i++) {
            ClienteDTO cliente = listaClientes.get(i);
            Long identificacion = cliente.getIdentificacion();
            String primerNombre = cliente.getPrimerNombre();
            String segundoNombre = cliente.getSegundoNombre();
            String primerApellido = cliente.getPrimerApellido();
            String segundoApellido = cliente.getSegundoApellido();
            String telefono = cliente.getTelefono();
            String correoElectronico = cliente.getCorreoElectronico();
            Long cargoId = cliente.getCargo().getId();
            String cargoNombre = cliente.getCargo().getNombre();
            Long supermercadoId = cliente.getSupermercado().getId();
            String supermercadoNombre = cliente.getSupermercado().getNombre();
            modeloTablaClientes.setValueAt(identificacion, i, 0); // identificacion
            modeloTablaClientes.setValueAt(primerNombre, i, 1); // primerNombre
            modeloTablaClientes.setValueAt(segundoNombre, i, 2); // segundoNombre
            modeloTablaClientes.setValueAt(primerApellido, i, 3); // primerApellido
            modeloTablaClientes.setValueAt(segundoApellido, i, 4); // segundoApellido
            modeloTablaClientes.setValueAt(telefono, i, 5); // telefono
            modeloTablaClientes.setValueAt(correoElectronico, i, 6); // correoElectronico
            modeloTablaClientes.setValueAt(cargoId, i, 7); // cargoId
            modeloTablaClientes.setValueAt(cargoNombre, i, 8); // cargoNombre
            modeloTablaClientes.setValueAt(supermercadoId, i, 9); // cargoNombre
            modeloTablaClientes.setValueAt(supermercadoNombre, i, 10); // cargoNombre     
        }
    }

    private void eliminarCliente() {

        if (idCliente != null) {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de eliminar el cliente?");
            if (opcion == 0) {
                String respuesta = controller.eliminarCliente(idCliente);
                JOptionPane.showMessageDialog(null, respuesta, "", JOptionPane.INFORMATION_MESSAGE);
                idCliente = null;
                clienteSeleccionado = null;
                CargarModeloTablaUsuarios();
            } else {
                idCliente = null;
                clienteSeleccionado = null;
                CargarModeloTablaUsuarios();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Señor usuario seleccione un cliente para su eliminacion.", "", JOptionPane.INFORMATION_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBusquedaUsuario = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        tablaClientes.getSelectionModel().addListSelectionListener(
            new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    if (!event.getValueIsAdjusting() && (tablaClientes.getSelectedRow()>= 0)) {//This line prevents double events
                        int filaSeleccionada = tablaClientes.getSelectedRow();

                        // Se crea el objeto cliente a partir de la fila seleccionada
                        Long identificacion = Long.parseLong(modeloTablaClientes.getValueAt(filaSeleccionada, 0).toString());
                        String primerNombre = modeloTablaClientes.getValueAt(filaSeleccionada, 1).toString();
                        String segundoNombre = modeloTablaClientes.getValueAt(filaSeleccionada, 2).toString();
                        String primerApellido = modeloTablaClientes.getValueAt(filaSeleccionada, 3).toString();
                        String segundoApellido = modeloTablaClientes.getValueAt(filaSeleccionada, 4).toString();
                        String telefono = modeloTablaClientes.getValueAt(filaSeleccionada, 5).toString();
                        String correoElectronico = modeloTablaClientes.getValueAt(filaSeleccionada, 6).toString();
                        Long idCargo = Long.parseLong(modeloTablaClientes.getValueAt(filaSeleccionada, 7).toString());
                        String nombreCargo = modeloTablaClientes.getValueAt(filaSeleccionada, 8).toString();
                        Long idSupermercado = Long.parseLong(modeloTablaClientes.getValueAt(filaSeleccionada, 9).toString());
                        String nombreSupermercado = modeloTablaClientes.getValueAt(filaSeleccionada, 10).toString();

                        clienteSeleccionado = new ClienteDTO();
                        clienteSeleccionado.setIdentificacion(identificacion);
                        clienteSeleccionado.setPrimerNombre(primerNombre);
                        clienteSeleccionado.setSegundoNombre(segundoNombre);
                        clienteSeleccionado.setPrimerApellido(primerApellido);
                        clienteSeleccionado.setSegundoApellido(segundoApellido);
                        clienteSeleccionado.setTelefono(telefono);
                        clienteSeleccionado.setCorreoElectronico(correoElectronico);
                        clienteSeleccionado.setCargo(new CargoDTO(idCargo, nombreCargo));
                        clienteSeleccionado.setSupermercado(new SupermercadoDTO(idSupermercado, nombreSupermercado, null));
                        // Se setea el id del cliente en caso de que quiera eliminar el cliente
                        idCliente = identificacion;

                    }            
                }
            }
        );
        btnMinimizar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JToggleButton();
        btnBorrar = new javax.swing.JToggleButton();
        btnNuevoCliente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 51, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Identificacion");

        txtBusquedaUsuario.setBackground(new java.awt.Color(255, 255, 255));
        txtBusquedaUsuario.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtBusquedaUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaUsuarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaUsuarioKeyTyped(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 51, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Gestion de usuarios");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/userIcono.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(502, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(0, 51, 153));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tablaClientes.setModel(modeloTablaClientes);
        jScrollPane1.setViewportView(tablaClientes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnMinimizar.setBackground(new java.awt.Color(51, 204, 255));
        btnMinimizar.setForeground(new java.awt.Color(102, 204, 255));
        btnMinimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/minimiazar.png"))); // NOI18N
        btnMinimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizarActionPerformed(evt);
            }
        });

        btnCerrar.setBackground(new java.awt.Color(204, 0, 51));
        btnCerrar.setForeground(new java.awt.Color(204, 0, 51));
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cerrar.png"))); // NOI18N
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(51, 153, 255));
        btnActualizar.setForeground(new java.awt.Color(51, 153, 255));
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editar.png"))); // NOI18N
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnBorrar.setBackground(new java.awt.Color(255, 51, 51));
        btnBorrar.setForeground(new java.awt.Color(255, 51, 51));
        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/eliminar.png"))); // NOI18N
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnNuevoCliente.setBackground(new java.awt.Color(0, 102, 102));
        btnNuevoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/guardar.png"))); // NOI18N
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBusquedaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                        .addComponent(btnMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(btnMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusquedaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(84, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMinimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizarActionPerformed
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_btnMinimizarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        int opcion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de Cerrar el Sistema?");
        if (opcion == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtBusquedaUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaUsuarioKeyReleased
        String filtro = txtBusquedaUsuario.getText();
        if (StringUtils.validarString(filtro)) {
            CargarModeloTablaUsuarios();
        } else {
            Long idCliente = Long.parseLong(filtro);
            List<ClienteDTO> listaClientes = controller.obtenerClientes(idCliente);
            int numeroUsuarios = listaClientes.size();
            modeloTablaClientes.setRowCount(numeroUsuarios);
            for (int i = 0; i < numeroUsuarios; i++) {
                ClienteDTO cliente = listaClientes.get(i);
                Long identificacion = cliente.getIdentificacion();
                String primerNombre = cliente.getPrimerNombre();
                String segundoNombre = cliente.getSegundoNombre();
                String primerApellido = cliente.getPrimerApellido();
                String segundoApellido = cliente.getSegundoApellido();
                String telefono = cliente.getTelefono();
                String correoElectronico = cliente.getCorreoElectronico();
                Long cargoId = cliente.getCargo().getId();
                String cargoNombre = cliente.getCargo().getNombre();
                Long supermercadoId = cliente.getSupermercado().getId();
                String supermercadoNombre = cliente.getSupermercado().getNombre();
                modeloTablaClientes.setValueAt(identificacion, i, 0); // identificacion
                modeloTablaClientes.setValueAt(primerNombre, i, 1); // primerNombre
                modeloTablaClientes.setValueAt(segundoNombre, i, 2); // segundoNombre
                modeloTablaClientes.setValueAt(primerApellido, i, 3); // primerApellido
                modeloTablaClientes.setValueAt(segundoApellido, i, 4); // segundoApellido
                modeloTablaClientes.setValueAt(telefono, i, 5); // telefono
                modeloTablaClientes.setValueAt(correoElectronico, i, 6); // correoElectronico
                modeloTablaClientes.setValueAt(cargoId, i, 7); // cargoId
                modeloTablaClientes.setValueAt(cargoNombre, i, 8); // cargoNombre
                modeloTablaClientes.setValueAt(supermercadoId, i, 9); // cargoNombre
                modeloTablaClientes.setValueAt(supermercadoNombre, i, 10); // cargoNombre       
            }
        }
    }//GEN-LAST:event_txtBusquedaUsuarioKeyReleased

    private void txtBusquedaUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaUsuarioKeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }

        if (txtBusquedaUsuario.getText().trim().length() == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_txtBusquedaUsuarioKeyTyped

    private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoClienteActionPerformed
        CrearEditarUsuario ventanaCreacion = new CrearEditarUsuario(null, true, null, "Nuevo Cliente", false);
        ventanaCreacion.setVisible(true);
        ventanaCreacion.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        ventanaCreacion.setLocation(600, 150);
        ventanaCreacion.setAlwaysOnTop(true);
        if (!StringUtils.validarString(ventanaCreacion.getInformacion())) {
            CargarModeloTablaUsuarios();
        }
    }//GEN-LAST:event_btnNuevoClienteActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        eliminarCliente();
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        int opcion = JOptionPane.showConfirmDialog(this, "¿Estas seguro de modificar el cliente?");
        // Si el usuario confirma la edicion del cliente
        if (opcion == 0) {
            if (clienteSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "No has Seleccionado un cliente para actualizar");
            }

            CrearEditarUsuario ventanaEdicion = new CrearEditarUsuario(null, true, clienteSeleccionado, "Edicion Cliente", true);
            ventanaEdicion.setVisible(true);
            if (ventanaEdicion.getInformacion() != "" || ventanaEdicion.getInformacion() != null) {
                CargarModeloTablaUsuarios();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Proceso de edicion Cancelado");
            clienteSeleccionado = null;
            idCliente = null;
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnActualizar;
    private javax.swing.JToggleButton btnBorrar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnMinimizar;
    private javax.swing.JButton btnNuevoCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTextField txtBusquedaUsuario;
    // End of variables declaration//GEN-END:variables
}
