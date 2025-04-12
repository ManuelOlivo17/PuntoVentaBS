

package com.mycompany.PuntoDeVenta2;

//import java.awt.*;
//import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author VICTECH
 */

public class Venta extends javax.swing.JPanel {

    /**
     * Creates new form Venta
     */
    
    public Venta() {
        initComponents();
        
    jTableProductos.setModel(new javax.swing.table.DefaultTableModel(
    new Object[][]{}, // Inicia la tabla sin filas
    new String[]{ "Código", "Nombre", "Precio", "Cantidad" // Define los encabezados de las columnas
    }
) {
    boolean[] canEdit = new boolean[]{
        false, false, false, true // Permite editar solo la columna "Cantidad"
    };

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex]; // Retorna si una celda es editable según su columna
    }
});
        
    // Hacer que al presionar "Enter" en el JTextField, se active el botón de Enter
    jTextFieldCodigos.addActionListener(e -> jButtonEnter.doClick());

    }
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private void actualizarTotal() { // Método para calcular y actualizar el total
    double total = 0.0; // Inicializa el total en 0
    DefaultTableModel model = (DefaultTableModel) jTableProductos.getModel(); // Obtiene el modelo de la tabla

    for (int i = 0; i < model.getRowCount(); i++) { // Itera sobre las filas de la tabla
        double precio = (double) model.getValueAt(i, 2); // Obtiene el precio de la columna 2
        int cantidad = (int) model.getValueAt(i, 3); // Obtiene la cantidad de la columna 3
        total += precio * cantidad; // Suma al total el resultado de precio * cantidad
    }

    lblTotal.setText("$ " + String.format("%.2f", total)); // Actualiza la etiqueta con el total formateado
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private double obtenerTotalTabla() { // Método para calcular el total de la tabla
    double total = 0.0; // Inicializa el total en 0

    for (int i = 0; i < jTableProductos.getRowCount(); i++) { // Recorre las filas de la tabla
        double precio = (double) jTableProductos.getValueAt(i, 2); // Obtiene el precio de la columna 2
        int cantidad = (int) jTableProductos.getValueAt(i, 3); // Obtiene la cantidad de la columna 3
        total += precio * cantidad; // Suma al total el resultado de precio * cantidad
    }

    return total; // Retorna el total calculado
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private boolean procesarVenta(boolean incluirIVA) { // Método para procesar la venta
    try {
        StringBuilder contenidoActualizado = new StringBuilder(); // String para almacenar el contenido actualizado
        BufferedReader br = new BufferedReader(new FileReader("Inventario.txt")); // Lectura del archivo de inventario
        String linea;

        while ((linea = br.readLine()) != null) { // Leer cada línea del archivo
            String[] datos = linea.split(","); // Separar datos de la línea por comas

            if (datos.length == 4) { // Verificar que la línea tenga los datos esperados
                String codigo = datos[0]; // Código del producto
                String nombre = datos[1]; // Nombre del producto
                double precio = Double.parseDouble(datos[2]); // Precio del producto
                int stock = Integer.parseInt(datos[3]); // Cantidad en stock

                boolean vendido = false; // Bandera para verificar si el producto fue vendido
                for (int i = 0; i < jTableProductos.getRowCount(); i++) { // Recorrer filas de la tabla
                    String codigoVenta = (String) jTableProductos.getValueAt(i, 0); // Código de venta
                    int cantidadVenta = (int) jTableProductos.getValueAt(i, 3); // Cantidad vendida

                    if (codigo.equals(codigoVenta)) { // Verificar si el producto coincide
                        stock -= cantidadVenta; // Reducir el stock
                        vendido = true; // Marcar como vendido
                        break;
                    }
                }

                if (stock > 0 || !vendido) { // Agregar línea actualizada si queda stock o no fue vendido
                    contenidoActualizado.append(String.join(",", codigo, nombre, String.valueOf(precio), String.valueOf(stock))).append("\n");
                }
            }
        }
        br.close(); // Cerrar el lector de archivo

        PrintWriter pw = new PrintWriter(new FileWriter("Inventario.txt")); // Escribir contenido actualizado en el archivo
        pw.write(contenidoActualizado.toString()); // Guardar los cambios
        pw.close(); // Cerrar el escritor de archivo

    } catch (java.io.FileNotFoundException e) { // Manejar error si no se encuentra el archivo
        JOptionPane.showMessageDialog(this, "El archivo Inventario.txt no fue encontrado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    } catch (java.io.IOException e) { // Manejar errores de lectura/escritura
        JOptionPane.showMessageDialog(this, "Error al leer/escribir el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    } catch (Exception e) { // Manejar cualquier otro error
        JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    ((DefaultTableModel) jTableProductos.getModel()).setRowCount(0); // Limpiar la tabla tras la venta
    actualizarTotal(); // Actualizar el total después de procesar la venta

    return true; // Indicar que la venta fue procesada correctamente
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldCodigos = new javax.swing.JTextField();
        jButtonEnter = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButtonInsVarios = new javax.swing.JButton();
        jButtonBuscar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProductos = new javax.swing.JTable();
        jButtonCobrar = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("VENTAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1300, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 70));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("CODIGO O NOMBRE DEL PRODUCTO");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 330, 40));

        jTextFieldCodigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodigosActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldCodigos, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 330, 40));

        jButtonEnter.setText("✅ Enter - Añadir Producto");
        jButtonEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnterActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonEnter, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 90, 210, 40));

        jButtonInsVarios.setText("📖 INS Varios ");
        jButtonInsVarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsVariosActionPerformed(evt);
            }
        });

        jButtonBuscar.setText("🔍 Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jButtonInsVarios, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1005, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonInsVarios, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 1600, 90));

        jTableProductos.setModel(new javax.swing.table.DefaultTableModel(
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
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableProductos);
        if (jTableProductos.getColumnModel().getColumnCount() > 0) {
            jTableProductos.getColumnModel().getColumn(0).setResizable(false);
            jTableProductos.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 930, 490));

        jButtonCobrar.setText("COBRAR");
        jButtonCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCobrarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonCobrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 410, 260, 90));

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(102, 153, 255));
        lblTotal.setText("$ 0.00");
        jPanel1.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 300, 320, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnterActionPerformed
       
        String entrada = jTextFieldCodigos.getText().trim(); // Obtiene y elimina espacios en blanco del texto ingresado

    if (entrada.isEmpty()) { // Verifica si el campo está vacío
        JOptionPane.showMessageDialog(this, "Por favor, ingrese un código o nombre de producto.", "Error", JOptionPane.ERROR_MESSAGE); // Muestra un mensaje de error
        return; // Termina la ejecución del método
    }

    try (BufferedReader br = new BufferedReader(new FileReader("Inventario.txt"))) { // Abre el archivo "Inventario.txt" para lectura
        String linea;
        boolean encontrado = false; // Bandera para saber si se encontró el producto

        while ((linea = br.readLine()) != null) { // Lee cada línea del archivo
            String[] datos = linea.split(","); // Divide la línea en un array usando comas como separador

            if (datos.length == 4) { // Verifica que la línea tenga los datos esperados
                String codigo = datos[0]; // Código del producto
                String nombre = datos[1]; // Nombre del producto
                double precio = Double.parseDouble(datos[2]); // Precio del producto
                int stock = Integer.parseInt(datos[3]); // Stock disponible del producto

                if (codigo.equalsIgnoreCase(entrada) || nombre.equalsIgnoreCase(entrada)) { // Compara código o nombre ignorando mayúsculas/minúsculas
                    DefaultTableModel model = (DefaultTableModel) jTableProductos.getModel(); // Obtiene el modelo de la tabla
                    model.addRow(new Object[]{codigo, nombre, precio, 1}); // Agrega una fila con la cantidad predeterminada: 1
                    actualizarTotal(); // Actualiza el total mostrado
                    encontrado = true; // Marca que el producto fue encontrado
                    break; // Sale del bucle
                }
            }
        }

        if (!encontrado) { // Si no se encontró el producto
            JOptionPane.showMessageDialog(this, "Producto no encontrado en el inventario.", "Error", JOptionPane.ERROR_MESSAGE); // Muestra un mensaje de error
        }

    } catch (Exception e) { // Captura cualquier excepción
        JOptionPane.showMessageDialog(this, "Error al leer el archivo de inventario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); // Muestra el mensaje de error
    }

    // Limpia el contenido del JTextField después de procesar el producto
    jTextFieldCodigos.setText("");
    
    }//GEN-LAST:event_jButtonEnterActionPerformed

    private void jButtonCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCobrarActionPerformed
    // Obtener la ventana principal desde el JPanel
    JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

    // Crear el JDialog sin AWT
    JDialog dialogoCobro = new JDialog(parentFrame, "Cobrar", true);
    dialogoCobro.setSize(450, 350);

    // Crear el panel principal con GroupLayout
    JPanel panel = new JPanel();
    GroupLayout layout = new GroupLayout(panel);
    panel.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);

    // Etiquetas y campos de entrada con fuentes y colores 100% Swing
    JLabel lblTotalCobro = new JLabel("Total a Cobrar:");
    lblTotalCobro.setFont(UIManager.getFont("Label.font"));

    JLabel lblTotalValor = new JLabel(lblTotal.getText());
    lblTotalValor.setFont(UIManager.getFont("Label.font").deriveFont(28f)); // Fuente más grande
    lblTotalValor.setForeground(UIManager.getColor("OptionPane.questionDialog.border.background")); // Verde fuerte de Swing

    JCheckBox chkIncluirIVA = new JCheckBox("Incluir IVA (16%)");

    JLabel lblPagoCon = new JLabel("Pagó Con:");
    JTextField txtPagoCon = new JTextField();

    JLabel lblCambio = new JLabel("Cambio:");
    JLabel lblCambioValor = new JLabel("$ 0.00");
    lblCambioValor.setFont(UIManager.getFont("Label.font").deriveFont(28f)); // Fuente más grande
    lblCambioValor.setForeground(UIManager.getColor("OptionPane.errorDialog.border.background")); // Rojo fuerte de Swing

    // Botones grandes con estilos Swing
    JButton btnAceptar = new JButton("Aceptar");
    JButton btnCancelar = new JButton("Cancelar");

    btnAceptar.setFont(UIManager.getFont("Button.font").deriveFont(18f)); // Fuente más grande
    btnCancelar.setFont(UIManager.getFont("Button.font").deriveFont(18f)); // Fuente más grande

    btnAceptar.setSize(120, 40);// Botón grande
    btnCancelar.setSize(120, 40);// Botón grande

    // Eventos de los componentes
    chkIncluirIVA.addActionListener(e -> {
        double total = obtenerTotalTabla();
        if (chkIncluirIVA.isSelected()) {
            total *= 1.16;
        }
        lblTotalValor.setText(String.format("$ %.2f", total));
    });

    txtPagoCon.addActionListener(e -> {
        try {
            double pagoCon = Double.parseDouble(txtPagoCon.getText().trim());
            double totalCobro = Double.parseDouble(lblTotalValor.getText().replace("$", "").trim());
            double cambio = pagoCon - totalCobro;
            lblCambioValor.setText(cambio >= 0 ? String.format("$ %.2f", cambio) : "Monto insuficiente");
        } catch (NumberFormatException ex) {
            lblCambioValor.setText("Entrada inválida");
        }
    });

    btnAceptar.addActionListener(e -> {
        if (procesarVenta(chkIncluirIVA.isSelected())) {
            JOptionPane.showMessageDialog(this, "Venta realizada con éxito.");
            dialogoCobro.dispose();
        }
    });

    btnCancelar.addActionListener(e -> dialogoCobro.dispose());

    // Configurar GroupLayout con distribución más profesional
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTotalCobro)
                .addGap(20)
                .addComponent(lblTotalValor))
            .addComponent(chkIncluirIVA)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblPagoCon)
                .addComponent(txtPagoCon))
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblCambio)
                .addComponent(lblCambioValor))
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnCancelar)
                .addGap(30)
                .addComponent(btnAceptar))
    );

    layout.setVerticalGroup(
        layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblTotalCobro)
                .addComponent(lblTotalValor))
            .addComponent(chkIncluirIVA)
            .addGap(15)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblPagoCon)
                .addComponent(txtPagoCon))
            .addGap(15)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblCambio)
                .addComponent(lblCambioValor))
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btnCancelar)
                .addComponent(btnAceptar))
    );

        // Agregar el panel al diálogo
    dialogoCobro.add(panel);
    dialogoCobro.setLocationRelativeTo(parentFrame);
    dialogoCobro.setVisible(true);
    }//GEN-LAST:event_jButtonCobrarActionPerformed

    private void jButtonInsVariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsVariosActionPerformed
int filaSeleccionada = jTableProductos.getSelectedRow(); // Obtiene la fila seleccionada en la tabla

    if (filaSeleccionada == -1) { // Verifica si no se ha seleccionado ninguna fila
        JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla para cambiar la cantidad.", "Error", JOptionPane.ERROR_MESSAGE); // Muestra un mensaje de error
        return; // Termina el método si no hay selección
    }

    String nuevaCantidadStr = JOptionPane.showInputDialog(this, "Ingrese la nueva cantidad:"); // Solicita al usuario la nueva cantidad
    if (nuevaCantidadStr != null) { // Verifica que no se haya cancelado el diálogo
        try {
            int nuevaCantidad = Integer.parseInt(nuevaCantidadStr); // Convierte el texto ingresado en un número entero
            if (nuevaCantidad > 0) { // Verifica que la nueva cantidad sea mayor a 0
                DefaultTableModel model = (DefaultTableModel) jTableProductos.getModel(); // Obtiene el modelo de la tabla
                model.setValueAt(nuevaCantidad, filaSeleccionada, 3); // Actualiza la cantidad en la columna 3 de la fila seleccionada
                actualizarTotal(); // Llama al método para recalcular el total
            } else {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE); // Muestra un mensaje si la cantidad no es válida
            }
        } catch (NumberFormatException e) { // Captura excepción si el texto ingresado no es un número válido
            JOptionPane.showMessageDialog(this, "Cantidad inválida.", "Error", JOptionPane.ERROR_MESSAGE); // Muestra un mensaje de error
        }
    }
    }//GEN-LAST:event_jButtonInsVariosActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
int filaSeleccionada = jTableProductos.getSelectedRow(); // Obtener la fila seleccionada

    // Verificar que se haya seleccionado una fila
    if (filaSeleccionada != -1) { 
        int confirmacion = JOptionPane.showConfirmDialog(this, // Mostrar cuadro de confirmación
            "¿Estás seguro de que deseas eliminar este producto?", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) { // Si el usuario confirma la eliminación
            DefaultTableModel model = (DefaultTableModel) jTableProductos.getModel(); // Obtener modelo de la tabla
            model.removeRow(filaSeleccionada); // Eliminar la fila seleccionada

            actualizarTotal(); // Actualizar el total después de la eliminación
            JOptionPane.showMessageDialog(this, "Producto eliminado de la selección."); // Mostrar mensaje de éxito
        }
    } else {
        JOptionPane.showMessageDialog(this, "Por favor, selecciona un producto para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE); // Mostrar advertencia si no se selecciona una fila
    }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
    // Obtener la ventana principal desde el JPanel
    JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

    // Crear el JDialog sin AWT
    JDialog dialogoBuscar = new JDialog(parentFrame, "Buscar Producto", true);
    dialogoBuscar.setSize(600, 400);

    // Crear el panel principal con GroupLayout
    JPanel panel = new JPanel();
    GroupLayout layout = new GroupLayout(panel);
    panel.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);

    // Barra de búsqueda sin AWT
    JLabel lblBuscar = new JLabel("Buscar:");
    JTextField txtBuscar = new JTextField(20);

    // Modelo y tabla para mostrar los productos
    DefaultTableModel modeloBuscar = new DefaultTableModel(new String[]{"Código", "Nombre", "Precio", "Stock"}, 0);
    JTable tablaBuscar = new JTable(modeloBuscar);
    JScrollPane scrollTabla = new JScrollPane(tablaBuscar);

    // Leer los datos del archivo Inventario.txt y llenar la tabla
    try (BufferedReader br = new BufferedReader(new FileReader("Inventario.txt"))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");
            if (datos.length == 4) {
                String codigo = datos[0];
                String nombre = datos[1];
                double precio = Double.parseDouble(datos[2]);
                int cantidad = Integer.parseInt(datos[3]);
                modeloBuscar.addRow(new Object[]{codigo, nombre, precio, cantidad});
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al leer los productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Botones sin AWT y con mejor diseño
    JButton btnSeleccionar = new JButton("Seleccionar");
    JButton btnCancelar = new JButton("Cancelar");

    btnSeleccionar.setFont(UIManager.getFont("Button.font").deriveFont(16f));
    btnCancelar.setFont(UIManager.getFont("Button.font").deriveFont(16f));

    btnSeleccionar.setSize(120, 40);
    btnCancelar.setSize(120, 40);

    // Filtrar la tabla en tiempo real sin KeyAdapter (reemplazado con DocumentListener)
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
            filtrarTabla();
        }

        private void filtrarTabla() {
            String textoBusqueda = txtBuscar.getText().trim().toLowerCase();
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modeloBuscar);
            tablaBuscar.setRowSorter(sorter);
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textoBusqueda));
        }
    });

    // Acción para el botón "Seleccionar"
    btnSeleccionar.addActionListener(e -> {
        int filaSeleccionada = tablaBuscar.getSelectedRow();
        if (filaSeleccionada != -1) {
            String codigoSeleccionado = tablaBuscar.getValueAt(filaSeleccionada, 0).toString();
            jTextFieldCodigos.setText(codigoSeleccionado);
            dialogoBuscar.dispose();
        } else {
            JOptionPane.showMessageDialog(dialogoBuscar, "Por favor, selecciona un producto.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    });

    // Acción para el botón "Cancelar"
    btnCancelar.addActionListener(e -> dialogoBuscar.dispose());

    // Configurar GroupLayout con distribución estética
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblBuscar)
                .addComponent(txtBuscar))
            .addComponent(scrollTabla)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnCancelar)
                .addGap(20)
                .addComponent(btnSeleccionar))
    );

    layout.setVerticalGroup(
        layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblBuscar)
                .addComponent(txtBuscar))
            .addComponent(scrollTabla)
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(btnCancelar)
                .addComponent(btnSeleccionar))
    );

    // Agregar el panel al diálogo
    dialogoBuscar.add(panel);
    dialogoBuscar.setLocationRelativeTo(parentFrame);
    dialogoBuscar.setVisible(true);
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jTextFieldCodigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodigosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCodigosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCobrar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonEnter;
    private javax.swing.JButton jButtonInsVarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProductos;
    private javax.swing.JTextField jTextFieldCodigos;
    private javax.swing.JLabel lblTotal;
    // End of variables declaration//GEN-END:variables
}
