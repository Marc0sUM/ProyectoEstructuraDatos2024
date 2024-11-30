/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import com.sun.source.tree.ContinueTree;
import javax.swing.JOptionPane;

public class Menu {

    public void mostrarMenu() {
        boolean salir = false;

        while (!salir) {
            String menuPrincipal = "Elige una opción:\n"
                    + "1. Cargar Datos\n"
                    + "2. Salir\n";
            String opcion = JOptionPane.showInputDialog(null, menuPrincipal, "Menú", JOptionPane.QUESTION_MESSAGE);

            if (opcion == null) { 
                salir = true;
                continue;
            }

            switch (opcion) {
                case "1":
                    mostrarSubmenu();
                    break; 
                case "2":
                    salir = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Intenta de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        JOptionPane.showMessageDialog(null, "¡Gracias por usar el programa!", "Salir", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarSubmenu() {
        boolean salirSubmenu = false;

        while (!salirSubmenu) {
            String submenu = "Submenú:\n"
                    + "1. Subopción 1\n"
                    + "2. Subopción 2\n"
                    + "3. Volver al menú principal";
            String subOpcion = JOptionPane.showInputDialog(null, submenu, "Submenú", JOptionPane.QUESTION_MESSAGE);

            if (subOpcion == null) { // Usuario presiona cancelar
                salirSubmenu = true;
                continue;
            }

            switch (subOpcion) {
                case "1":
                    JOptionPane.showMessageDialog(null, "Seleccionaste Subopción 1", "Información", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Seleccionaste Subopción 2", "Información", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "3":
                    salirSubmenu = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Intenta de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
