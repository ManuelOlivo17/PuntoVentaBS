
package com.mycompany.PuntoDeVenta2;

//import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author VICTECH
 */
public class Inventarios extends javax.swing.JPanel {

    /**
     * Creates new form Productos
     */
    public Inventarios() {
    initComponents(); // Inicializa los componentes del panel

    // Configura el modelo de la tabla
    jTable1.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][]{}, // Sin filas iniciales
        new String[]{
            "Código", "Nombre", "Precio", "Stock" // Columnas visibles
        }
    ) {
        boolean[] canEdit = new boolean[]{
            false, false, false, false // Ninguna columna editable
        };

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit[columnIndex];
        }
    });

    cargarProductos(); // Llama al método para llenar la tabla con los datos
    jTable1.revalidate(); // Revalida la tabla para que refleje los cambios
    jTable1.repaint(); // Redibuja visualmente la tabla
}



    
///////////////////////////////////////////////////////////////////////
    
    private void cargarProductos() { // Método para cargar productos desde el archivo al modelo de la tabla
        DefaultTableModel modeloTabla = (DefaultTableModel) jTable1.getModel(); // Obtener el modelo de la tabla
        modeloTabla.setRowCount(0); // Limpiar cualquier dato existente en el modelo

        try (BufferedReader br = new BufferedReader(new FileReader("Inventario.txt"))) { // Abrir archivo "Inventario.txt" para lectura
            String linea;
            System.out.println("Intentando cargar datos desde el archivo..."); // Log para depuración

            while ((linea = br.readLine()) != null) { // Leer línea por línea del archivo
                System.out.println("Línea leída: " + linea); // Log de la línea leída
                String[] datos = linea.split(","); // Dividir la línea por comas

                if (datos.length == 4) { // Verificar que la línea tenga los 4 campos esperados
                    try {
                        String codigo = datos[0].trim(); // Código del producto
                        String nombre = datos[1].trim(); // Nombre del producto
                        double precio = Double.parseDouble(datos[2].trim()); // Precio del producto
                        int stock = Integer.parseInt(datos[3].trim()); // Cantidad de stock

                        modeloTabla.addRow(new Object[]{codigo, nombre, precio, stock}); // Añadir fila con los datos al modelo
                        System.out.println("Producto cargado: " + codigo + ", " + nombre + ", " + precio + ", " + stock); // Log del producto cargado
                    } catch (NumberFormatException e) { // Manejar errores en formato numérico
                        System.err.println("Error en el formato numérico: " + linea); // Log de error
                    }
                } else {
                    System.err.println("Línea inválida: " + linea); // Log de líneas con datos incompletos o inválidos
                }
            }
        } catch (Exception e) { // Captura errores en la operación de archivo
            JOptionPane.showMessageDialog(this, "Error al cargar el inventario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // Mostrar mensaje de error
        }

        jTable1.revalidate(); // Revalidar la tabla para refrescar los datos
        jTable1.repaint(); // Redibujar la tabla para actualizar visualmente

        System.out.println("Total de filas en la tabla: " + jTable1.getRowCount()); // Log para verificar el número total de filas cargadas
    }
  
/////////////////////////////////////////////////////////////////
    
private void abrirDialogoModificar(String codigo, String nombre, double precio, int stock, int filaSeleccionada) {
    // Obtener la ventana principal de este JPanel
    JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

    // Crear el diálogo modal sin AWT
    JDialog dialogoModificar = new JDialog(parentFrame, "Modificar Producto", true);
    dialogoModificar.setSize(400, 300);

    // Crear el panel principal con GroupLayout (100% Swing)
    JPanel panel = new JPanel();
    GroupLayout layout = new GroupLayout(panel);
    panel.setLayout(layout);

    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);

    // Crear etiquetas y campos de texto
    JLabel lblCodigo = new JLabel("Código:");
    JTextField txtCodigo = new JTextField(codigo);

    JLabel lblNombre = new JLabel("Nombre:");
    JTextField txtNombre = new JTextField(nombre);

    JLabel lblPrecio = new JLabel("Precio:");
    JTextField txtPrecio = new JTextField(String.valueOf(precio));

    JLabel lblStock = new JLabel("Stock:");
    JTextField txtStock = new JTextField(String.valueOf(stock));

    // Crear botones alineados correctamente
    JButton btnGuardar = new JButton("Guardar");
    JButton btnCancelar = new JButton("Cancelar");

    // Definir el diseño de los componentes con GroupLayout
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblCodigo)
                .addComponent(txtCodigo))
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblNombre)
                .addComponent(txtNombre))
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblPrecio)
                .addComponent(txtPrecio))
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblStock)
                .addComponent(txtStock))
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnCancelar)
                .addGap(20)
                .addComponent(btnGuardar))
    );

    layout.setVerticalGroup(
        layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblCodigo)
                .addComponent(txtCodigo))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblNombre)
                .addComponent(txtNombre))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblPrecio)
                .addComponent(txtPrecio))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblStock)
                .addComponent(txtStock))
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btnCancelar)
                .addComponent(btnGuardar))
    );

    // Acción del botón "Guardar"
    btnGuardar.addActionListener(e -> {
        try {
            String nuevoCodigo = txtCodigo.getText().trim();
            String nuevoNombre = txtNombre.getText().trim();
            double nuevoPrecio = Double.parseDouble(txtPrecio.getText().trim());
            int nuevoStock = Integer.parseInt(txtStock.getText().trim());

            DefaultTableModel modeloTabla = (DefaultTableModel) jTable1.getModel();
            modeloTabla.setValueAt(nuevoCodigo, filaSeleccionada, 0);
            modeloTabla.setValueAt(nuevoNombre, filaSeleccionada, 1);
            modeloTabla.setValueAt(nuevoPrecio, filaSeleccionada, 2);
            modeloTabla.setValueAt(nuevoStock, filaSeleccionada, 3);

            guardarCambiosInventario();

            JOptionPane.showMessageDialog(dialogoModificar, "Producto modificado con éxito.");
            dialogoModificar.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(dialogoModificar, "Error al guardar los cambios: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    // Acción del botón "Cancelar"
    btnCancelar.addActionListener(e -> dialogoModificar.dispose());

    // Agregar el panel al diálogo
    dialogoModificar.add(panel);
    dialogoModificar.setLocationRelativeTo(parentFrame);
    dialogoModificar.setVisible(true);
}

////////////////////////////////////////////////////////////////////////////////

    private void guardarCambiosInventario() { // Método para guardar los cambios realizados en el inventario
        try (PrintWriter pw = new PrintWriter("Inventario.txt")) { // Abrir el archivo "Inventario.txt" en modo escritura
            DefaultTableModel modeloTabla = (DefaultTableModel) jTable1.getModel(); // Obtener el modelo de la tabla

            for (int i = 0; i < modeloTabla.getRowCount(); i++) { // Recorrer cada fila de la tabla
                String codigo = (String) modeloTabla.getValueAt(i, 0); // Obtener el código del producto
                String nombre = (String) modeloTabla.getValueAt(i, 1); // Obtener el nombre del producto
                double precio = (double) modeloTabla.getValueAt(i, 2); // Obtener el precio del producto
                int stock = (int) modeloTabla.getValueAt(i, 3); // Obtener el stock del producto

                pw.println(String.join(",", codigo, nombre, String.valueOf(precio), String.valueOf(stock))); // Escribir los datos en el archivo separados por comas
            }
        } catch (Exception e) { // Manejar errores de escritura en el archivo
            JOptionPane.showMessageDialog(this, "Error al guardar los cambios en el inventario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // Mostrar mensaje de error
        }
    }

/////////////////////////////////////////////////////////////////////////////////
      
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JPanelFondoAmarillo = new javax.swing.JPanel();
        jLabelProductos = new javax.swing.JLabel();
        JPanelFondoGris = new javax.swing.JPanel();
        jButtonModificar = new javax.swing.JButton();
        jLabelProductos1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPanelFondoAmarillo.setBackground(new java.awt.Color(153, 153, 255));

        jLabelProductos.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelProductos.setForeground(new java.awt.Color(255, 255, 255));
        jLabelProductos.setText("Inventario");

        javax.swing.GroupLayout JPanelFondoAmarilloLayout = new javax.swing.GroupLayout(JPanelFondoAmarillo);
        JPanelFondoAmarillo.setLayout(JPanelFondoAmarilloLayout);
        JPanelFondoAmarilloLayout.setHorizontalGroup(
            JPanelFondoAmarilloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelFondoAmarilloLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabelProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(746, Short.MAX_VALUE))
        );
        JPanelFondoAmarilloLayout.setVerticalGroup(
            JPanelFondoAmarilloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelFondoAmarilloLayout.createSequentialGroup()
                .addComponent(jLabelProductos)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(JPanelFondoAmarillo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1440, 40));

        JPanelFondoGris.setBackground(new java.awt.Color(204, 204, 204));

        jButtonModificar.setText("Modificar");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPanelFondoGrisLayout = new javax.swing.GroupLayout(JPanelFondoGris);
        JPanelFondoGris.setLayout(JPanelFondoGrisLayout);
        JPanelFondoGrisLayout.setHorizontalGroup(
            JPanelFondoGrisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelFondoGrisLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jButtonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1247, Short.MAX_VALUE))
        );
        JPanelFondoGrisLayout.setVerticalGroup(
            JPanelFondoGrisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelFondoGrisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonModificar, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(JPanelFondoGris, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, -1));

        jLabelProductos1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelProductos1.setForeground(new java.awt.Color(153, 153, 255));
        jLabelProductos1.setText("PRODUCTOS");
        jPanel1.add(jLabelProductos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 230, -1));

        jScrollPane1.setForeground(new java.awt.Color(255, 0, 51));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Precio", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 950, 480));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
    int filaSeleccionada = jTable1.getSelectedRow(); // Obtener la fila seleccionada en la tabla

    if (filaSeleccionada == -1) { // Verificar si no se seleccionó ninguna fila
        JOptionPane.showMessageDialog(this, "Por favor, selecciona un producto para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE); // Mostrar advertencia
        return; // Terminar el método si no hay selección
    }

    // Obtener los datos del producto seleccionado
    String codigo = (String) jTable1.getValueAt(filaSeleccionada, 0); // Obtener el código del producto
    String nombre = (String) jTable1.getValueAt(filaSeleccionada, 1); // Obtener el nombre del producto
    double precio = (double) jTable1.getValueAt(filaSeleccionada, 2); // Obtener el precio del producto
    int stock = (int) jTable1.getValueAt(filaSeleccionada, 3); // Obtener el stock del producto

    // Abrir el diálogo para modificar el producto
    abrirDialogoModificar(codigo, nombre, precio, stock, filaSeleccionada); // Llamar al método para abrir el diálogo de modificación

    }//GEN-LAST:event_jButtonModificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanelFondoAmarillo;
    private javax.swing.JPanel JPanelFondoGris;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JLabel jLabelProductos;
    private javax.swing.JLabel jLabelProductos1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
