package com.mycompany.PuntoDeVenta2;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;

/*
public class PuntoDeVenta2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { // Ejecuta el programa en el hilo de despacho de eventos
            JFMenu menuPrincipal = new JFMenu(); // Crea instancia de la ventana principal (JFMenu)
            menuPrincipal.setVisible(true); // Hace visible la ventana principal
        });
    }
}
*/

public class PuntoDeVenta2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { // Ejecuta el programa en el hilo de despacho de eventos
            JFMenu menuPrincipal = new JFMenu(); // Crea instancia de la ventana principal (JFMenu)
            menuPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH); // Configura la ventana en pantalla completa
            menuPrincipal.setVisible(true); // Hace visible la ventana principal
        });
    }
}
