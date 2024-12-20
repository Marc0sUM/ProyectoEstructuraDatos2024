/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import com.sun.source.tree.ContinueTree;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Menu {

    //Variables para el funcionamiento del sistema
    Random random = new Random();
    ConfiguracionBanco configuracionBanco = new ConfiguracionBanco();
    String horaActual = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    int cantTiquetes = 0;
    int num = 0;
    int numeroCaja;
    int cantClientesAtendidos;
    //Lleva la cuenta de la cja que antendio mas
    int cajaP = 0;
    int cajaA = 0;
    int cajaB = 0;
    //Diferencia en segundos para el promedio
    int diferenciaSegundos=0;

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
                        System.out.println("CantCajasCreadas" + configuracionBanco.getCantCajas());
                        break;
                    case "2":
                        if (configuracionBanco.getCantCajas() == 0) {
                            JOptionPane.showMessageDialog(null, "¡Debes cargar los datos del Banco antes de acceder!");
                        } else {
                            mostrarSubmenu();
                        }
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
                        + "3. Atender las cajas\n"
                        + "4. Reportes\n"
                        + "5. Volver al menú principal";
                String subOpcion = JOptionPane.showInputDialog(null, submenu, "Submenú", JOptionPane.QUESTION_MESSAGE);

                if (subOpcion == null) { // Usuario presiona cancelar
                    salirSubmenu = true;
                    continue;
                }
                switch (subOpcion) {
                    case "1":
                        num = 0;
                        JOptionPane.showMessageDialog(null, "Generando Tiquetes....", "Información", JOptionPane.INFORMATION_MESSAGE);
                        generacionTiquetes();
                        break;
                    case "2":
                        JOptionPane.showMessageDialog(null, "Mostrando las colas de las cajas", "Información", JOptionPane.INFORMATION_MESSAGE);
                        imprimeInfoCajas();
                        break;
                    case "3":
                        atenderCajas();
                        break;
                    case "4":
                        leerHistorial("transaccion");
                        break;
                    case "5":
                        salirSubmenu = true;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida. Intenta de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingreso el formato incorrecto");
        }
    }

    private void atenderCajas() {
        boolean salirAtender = false;
        try {
            while (!salirAtender) {
                String menuAtender = "Atender Cajas:\n"
                        + "1. Atender caja Preferencial\n"
                        + "2. Atender caja Rapida\n"
                        + "3. Atender cajas estandar\n"
                        + "4. Atender todas las cajas\n"
                        + "5. Volver\n";
                String subOpcion = JOptionPane.showInputDialog(null, menuAtender, "Menu Atender", JOptionPane.QUESTION_MESSAGE);

                if (subOpcion == null) {
                    salirAtender = true;
                    continue;
                }
                switch (subOpcion) {
                    case "1":
                        atenderCajaPreferencial();
                        break;
                    case "2":
                        atenderCajaRapida();
                        break;
                    case "3":
                        atenderCajaEstandar();
                        break;
                    case "4":
                        atenderTodasCajas();
                        break;
                    case "5":
                        salirAtender = true;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida. Intenta de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingreso el formato incorrecto");
        }
    }

    private void generacionTiquetes() {
        try {
            cantTiquetes = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de tiquetes a generar: "));
            while (num != cantTiquetes) {
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente");
                int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del cliente: "));
                int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del cliente: "));
                String tramite = JOptionPane.showInputDialog("Ingrese el tramite a realizar(Deposito, Retiro, Cambio de divisas): ").toLowerCase();
                String tipo = JOptionPane.showInputDialog("Ingrese el tipo(P:Preferencial, A:Un solo tramite, B:Dos o mas tramites): ").toUpperCase();
                String horaCreacion = horaActual;
                Persona cliente = new Persona(nombre, id, edad, tramite, tipo, horaCreacion, null);
                switch (tipo) {
                    case "P": // Caja Preferencial
                        int filaP = configuracionBanco.getCajaPreferencial().tamannoCola(configuracionBanco.getCajaPreferencial());
                        if (filaP >= 5) {
                            JOptionPane.showMessageDialog(null, "La caja esta llena, debe esperar a que atiendan a los clientes.");
                        } else {
                            JOptionPane.showMessageDialog(null, "El cliente ha sido asignado a la Caja Preferencial.");
                            if (filaP == 0) {
                                JOptionPane.showMessageDialog(null, "No hay personas en la cola, sera atendido a continuacion");
                            } else {
                                JOptionPane.showMessageDialog(null, "Y tiene " + filaP
                                        + " personas adelante en la fila");
                            }
                            configuracionBanco.getCajaPreferencial().push(cliente);
                        }
                        break;

                    case "A": // Caja Rápida (Un solo trámite)
                        int filaR = configuracionBanco.getCajaRapida().tamannoCola(configuracionBanco.getCajaRapida());
                        if (filaR >= 5) {
                            JOptionPane.showMessageDialog(null, "La caja esta llena, debe esperar a que atiendan a los clientes.");
                        } else {
                            JOptionPane.showMessageDialog(null, "El cliente ha sido asignado a la Caja Rápida.");
                            if (filaR == 0) {
                                JOptionPane.showMessageDialog(null, "No hay personas en la cola, sera atendido a continuacion");
                            } else {
                                JOptionPane.showMessageDialog(null, "Y tiene " + filaR
                                        + " personas adelante en la fila");
                            }
                            configuracionBanco.getCajaRapida().push(cliente);
                        }
                        break;

                    case "B": // Cajas Estándar 
                        System.out.println(configuracionBanco.getCantCajas());
                        int filaE = configuracionBanco.getCajaEstandar(cajaMenosCola()).tamannoCola(configuracionBanco.getCajaEstandar(cajaMenosCola()));
                        if (filaE >= 5) {
                            JOptionPane.showMessageDialog(null, "La caja esta llena, debe esperar a que atiendan a los clientes.");
                        } else {
                            System.out.println(cajaMenosCola() + "Caja a la que se asigna la persona");
                            if (filaE == 0) {
                                JOptionPane.showMessageDialog(null, "El cliente ha sido asignado a la Caja Estándar " + (cajaMenosCola() + 1)
                                        + " y no hay personas en la cola, sera atendido a continuacion");
                            } else {
                                JOptionPane.showMessageDialog(null, "El cliente ha sido asignado a la Caja Estándar " + (cajaMenosCola() + 1)
                                        + " y tiene " + filaE
                                        + " personas adelante en la fila.");
                            }
                            configuracionBanco.getCajaEstandar(cajaMenosCola()).push(cliente);
                        }
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Tipo inválido. Inténtalo nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
                        continue; // Volver a solicitar los datos del cliente
                }
                num++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingreso el formato incorrecto");
        }
    }

    private void imprimeInfoCajas() {
        JOptionPane.showMessageDialog(null, configuracionBanco.getCajaPreferencial().toString(), "Información de la Caja Preferencial", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(null, configuracionBanco.getCajaRapida().toString(), "Información de la Caja Rapida", JOptionPane.INFORMATION_MESSAGE);

        for (int i = 0; i < configuracionBanco.getCantCajas(); i++) {
            System.out.println("Numero de cajas estandar" + numeroCaja);
            System.out.println("Imprimiendo" + i);
            JOptionPane.showMessageDialog(null, configuracionBanco.getCajaEstandar(i).toString(), "Información de la Caja Estandar " + (i + 1), JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private int cajaMenosCola() {
        int menor = 1000;
        int cajaIndice = 0;
        if (configuracionBanco.getCantCajas() > 0) {
            for (int i = 0; i < configuracionBanco.getCantCajas(); i++) {
                int colaActual = configuracionBanco.getCajaEstandar(i).tamannoCola(configuracionBanco.getCajaEstandar(i));
                if (colaActual < menor) {
                    menor = colaActual;
                    cajaIndice = i;
                }
            }
        }
        return cajaIndice;
    }

    private void atenderCajaPreferencial() {
        configuracionBanco.getCajaPreferencial().modificarHora(configuracionBanco.getCajaPreferencial());
        configuracionBanco.getCajaPreferencial().guardarAtendidos(configuracionBanco.getCajaPreferencial(), "transaccion");
        configuracionBanco.getCajaPreferencial().pop();
        cantClientesAtendidos++;
        JOptionPane.showMessageDialog(null, "El cliente ha sido atendido");
    }

    private void atenderCajaRapida() {
        configuracionBanco.getCajaRapida().modificarHora(configuracionBanco.getCajaRapida());
        configuracionBanco.getCajaRapida().guardarAtendidos(configuracionBanco.getCajaRapida(), "transaccion");
        configuracionBanco.getCajaRapida().pop();
        cantClientesAtendidos++;
        JOptionPane.showMessageDialog(null, "El cliente ha sido atendido");
    }

    private void atenderCajaEstandar() {
        for (int i = 0; i < configuracionBanco.getCantCajas(); i++) {
            System.out.println("Numero de cajas estandar" + numeroCaja);
            configuracionBanco.getCajaEstandar(i).modificarHora(configuracionBanco.getCajaEstandar(i));
            configuracionBanco.getCajaEstandar(i).guardarAtendidos(configuracionBanco.getCajaEstandar(i), "transaccion");
            configuracionBanco.getCajaEstandar(i).pop();
        }
        JOptionPane.showMessageDialog(null, "El cliente ha sido atendido");
    }

    private void atenderTodasCajas() {
        configuracionBanco.getCajaPreferencial().modificarHora(configuracionBanco.getCajaPreferencial());
        configuracionBanco.getCajaPreferencial().guardarAtendidos(configuracionBanco.getCajaPreferencial(), "transaccion");
        configuracionBanco.getCajaPreferencial().pop();
        cantClientesAtendidos++;
        configuracionBanco.getCajaRapida().modificarHora(configuracionBanco.getCajaRapida());
        configuracionBanco.getCajaRapida().guardarAtendidos(configuracionBanco.getCajaRapida(), "transaccion");
        configuracionBanco.getCajaRapida().pop();
        cantClientesAtendidos++;
        for (int i = 0; i < configuracionBanco.getCantCajas(); i++) {
            System.out.println("Numero de cajas estandar" + numeroCaja);
            configuracionBanco.getCajaEstandar(i).modificarHora(configuracionBanco.getCajaEstandar(i));
            configuracionBanco.getCajaEstandar(i).guardarAtendidos(configuracionBanco.getCajaEstandar(i), "transaccion");
            configuracionBanco.getCajaEstandar(i).pop();
            cantClientesAtendidos++;
        }
        JOptionPane.showMessageDialog(null, "Se han atendido los clientes en todas las cajas");
    }

    public void leerHistorial(String archivoName) {
        int cantAtendidos = 0;
        String cajaMasAtendio="";
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoName))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Persona persona = parseLinea(linea);
                if (persona != null) {
                    JOptionPane.showMessageDialog(null, "Lista de personas Atendidas\n"
                            + "\nNombre: " + persona.getNombre()
                            + "\nId: " + persona.getId()
                            + "\nEdad: " + persona.getEdad()
                            + "\nTramite: " + persona.getTramite()
                            + "\nTipo: " + persona.getTipo()
                            + "\nHora Creacion: " + persona.getHoraCreacion()
                            + "\nHora Atencion: " + persona.getHoraAtencion()
                    );
                }
                cantAtendidos++;
                cajaMasAtendio = cajaAtendioMas(persona);
                promedioAtencionTodas(persona);
            }
            int promedioSegundos = diferenciaSegundos/cantAtendidos;
            int minPromedio = (promedioSegundos % 3600)/60;
            JOptionPane.showMessageDialog(null, "El banco a atendido a : " + cantAtendidos + " personas");
            JOptionPane.showMessageDialog(null, "La caja que mas atendio fue : " + cajaMasAtendio );
            JOptionPane.showMessageDialog(null, "El promedio en minutos de atencion de las cajas es : " + minPromedio+" min");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer el documento" + e.getMessage());
        }
    }

    private Persona parseLinea(String linea) {
        try {
            String contenido = linea.substring(linea.indexOf("{") + 1,
                    linea.lastIndexOf("}"));
            String[] campos = contenido.split(",");

            String nombre = campos[0].split("=")[1];
            int id = Integer.parseInt(campos[1].split("=")[1]);
            int edad = Integer.parseInt(campos[2].split("=")[1]);
            String tramite = campos[3].split("=")[1];
            String tipo = campos[4].split("=")[1];
            String horaCreacion = campos[5].split("=")[1];
            String horaAtencion = campos[6].split("=")[1];

            return new Persona(nombre, id, edad, tramite, tipo, horaCreacion, horaAtencion);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo parsear la info" + linea);
            return null;
        }
    }

    private String cajaAtendioMas(Persona persona) {
        if (persona.getTipo().contains("P")) {
            cajaP++;
        } else if (persona.getTipo().contains("A")) {
            cajaA++;
        } else if (persona.getTipo().contains("B")) {
            cajaB++;
        }
        if (cajaP >= cajaA && cajaP >= cajaB) {
            return "Caja Preferencial";
        } else if (cajaA >= cajaP && cajaA >= cajaB) {
            return "Caja Rapida";
        } else {
            return "Cajas Estandar";
        }

    }
    
    private void promedioAtencionTodas(Persona persona){
        
        LocalTime creacion = LocalTime.parse(persona.getHoraCreacion());
        LocalTime atencion = LocalTime.parse(persona.getHoraAtencion());
        
        int segundosCreacion = creacion.toSecondOfDay();
        int segundosAtencion = atencion.toSecondOfDay();
        
        diferenciaSegundos += (segundosAtencion -  segundosCreacion);
    }

}
