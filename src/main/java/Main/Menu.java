/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import com.sun.source.tree.ContinueTree;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;
import java.time.LocalTime;
import java.util.Random;

public class Menu {

    //Variables para el funcionamiento del sistema
    Random random = new Random();
    ConfiguracionBanco configuracionBanco = new ConfiguracionBanco();
    LocalTime horaActual = LocalTime.now();
    int cantTiquetes = 0;
    int num = 0;
    int numeroCaja;

    public void mostrarMenu() {
        boolean salir = false;
        try {

            while (!salir) {
                String menuPrincipal = "Elige una opción:\n"
                        + "1. Cargar Datos\n"
                        + "2. Acceder al Banco\n"
                        + "3. Eliminar Banco\n"
                        + "4. Salir\n";
                String opcion = JOptionPane.showInputDialog(null, menuPrincipal, "Menú", JOptionPane.QUESTION_MESSAGE);

                if (opcion == null) {
                    salir = true;
                    continue;
                }

                switch (opcion) {
                    case "1":
                        configuracionBanco.verificarConfiguracion();
                        System.out.println(configuracionBanco.getCantCajas());
                        break;
                    case "2":
                        mostrarSubmenu();
                        break;
                    case "3":
                        int opcionEli = JOptionPane.showConfirmDialog(null,
                                "¿Desea eliminar el archivo de configuración?",
                                "Confirmar Eliminación",
                                JOptionPane.YES_NO_OPTION);
                        if (opcionEli == JOptionPane.YES_OPTION) {
                            configuracionBanco.eliminarArchivoConfiguracion();
                        }
                        break;
                    case "4":
                        salir = true;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida. Intenta de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingreso el formato incorrecto" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        JOptionPane.showMessageDialog(null, "¡Gracias por usar nuestro Sistema!", "Salir", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarSubmenu() {
        boolean salirSubmenu = false;
        try {
            while (!salirSubmenu) {
                String submenu = "Submenú:\n"
                        + "1. Crear Tiquetes\n"
                        + "2. Muestra las Cajas y su contenido\n"
                        + "3. Volver al menú principal";
                String subOpcion = JOptionPane.showInputDialog(null, submenu, "Submenú", JOptionPane.QUESTION_MESSAGE);

                if (subOpcion == null) { // Usuario presiona cancelar
                    salirSubmenu = true;
                    continue;
                }
                switch (subOpcion) {
                    case "1":
                        num = 0;
                        JOptionPane.showMessageDialog(null, "Generando Tiquetes....", "Información", JOptionPane.INFORMATION_MESSAGE);
                        cantTiquetes = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de tiquetes a generar: "));
                        while (num != cantTiquetes) {
                            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente");
                            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del cliente: "));
                            int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del cliente: "));
                            String tramite = JOptionPane.showInputDialog("Ingrese el tramite a realizar(Deposito, Retiro, Cambio de divisas): ").toLowerCase();
                            String tipo = JOptionPane.showInputDialog("Ingrese el tipo(P:Preferencial, A:Un solo tramite, B:Dos o mas tramites): ").toUpperCase();
                            String horaCreacion = "" + horaActual;
                            Persona cliente = new Persona(nombre, id, edad, tramite, tipo, horaCreacion, edad);
                            switch (tipo) {
                                case "P": // Caja Preferencial
                                    configuracionBanco.getCajaPreferencial().push(cliente);
                                    JOptionPane.showMessageDialog(null, "El cliente ha sido asignado a la Caja Preferencial.");
                                    break;

                                case "A": // Caja Rápida (Un solo trámite)
                                    configuracionBanco.getCajaRapida().push(cliente);
                                    JOptionPane.showMessageDialog(null, "El cliente ha sido asignado a la Caja Rápida.");
                                    break;

                                case "B": // Cajas Estándar 
                                    System.out.println(configuracionBanco.getCantCajas());
                                    numeroCaja = (random.nextInt(configuracionBanco.getCantCajas())); // Distribuir entre cajas estándar
                                    System.out.println(numeroCaja + "Caja a la que se asigna la persona");
                                    configuracionBanco.getCajaEstandar(numeroCaja).push(cliente);
                                    JOptionPane.showMessageDialog(null, "El cliente ha sido asignado a la Caja Estándar " + (numeroCaja + 1) + ".");
                                    break;

                                default:
                                    JOptionPane.showMessageDialog(null, "Tipo inválido. Inténtalo nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
                                    continue; // Volver a solicitar los datos del cliente
                            }
                            num++;
                        }
                        break;
                    case "2":
                        configuracionBanco.getCajaEstandar(numeroCaja);//Guarda la cantidad de cajas estandar en la variable
                        JOptionPane.showMessageDialog(null, "Mostrando las colas de las cajas", "Información", JOptionPane.INFORMATION_MESSAGE);
                        JOptionPane.showMessageDialog(null, configuracionBanco.getCajaPreferencial().toString(), "Información de la Caja Preferencial", JOptionPane.INFORMATION_MESSAGE);
                        JOptionPane.showMessageDialog(null, configuracionBanco.getCajaRapida().toString(), "Información de la Caja Rapida", JOptionPane.INFORMATION_MESSAGE);

                        for (int i = 0; i <= numeroCaja; i++) {
                            System.out.println("Numero de cajas estandar"+numeroCaja);
                            System.out.println("Imprimiendo"+i);
                            JOptionPane.showMessageDialog(null, configuracionBanco.getCajaEstandar(i).toString(), "Información de la Caja Estandar "+(i+1), JOptionPane.INFORMATION_MESSAGE);
//                            JOptionPane.showMessageDialog(null, configuracionBanco.getCajaEstandar(numeroCaja).toString(), "Información de la Caja Estandar", JOptionPane.INFORMATION_MESSAGE);

                        }
//                        JOptionPane.showMessageDialog(null, configuracionBanco.getCajaEstandar(numeroCaja).toString(), "Información de la Caja", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case "3":
                        salirSubmenu = true;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida. Intenta de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingreso el formato incorrecto" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
