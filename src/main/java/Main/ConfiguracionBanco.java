/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class ConfiguracionBanco {

    private static final String CONFIG_FILE = "prod.txt";
    private sistemaTiquetes[] cajas;
    private sistemaTiquetes cajaPreferencial;
    private sistemaTiquetes cajaRapida;
    private int cantCajas = 0;

    public int getCantCajas() {
        return cantCajas;
    }

    public sistemaTiquetes getCajaPreferencial() {
        return cajaPreferencial;
    }

    public sistemaTiquetes getCajaRapida() {
        return cajaRapida;
    }

    public sistemaTiquetes getCajaEstandar(int index) {
        if (index >= 0 && index < cajas.length) {
            return cajas[index];
        } else {
            return null;
        }
    }

    public void verificarConfiguracion() {
        File archivo = new File(CONFIG_FILE);
        if (archivo.exists()) {
            cargarConfiguracion(archivo);
        } else {
            configurarBanco(archivo);
        }
        crearCajas();
    }

    private void cargarConfiguracion(File archivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            StringBuilder configuracion = new StringBuilder();
            String linea;
            boolean encontrado = false;

            // Lee el archivo
            while ((linea = reader.readLine()) != null) {
                configuracion.append(linea).append("\n");
                // Buscamos la parte que contiene la cantidad de cajas
                if (linea.contains("Cantidad de Cajas:")) {
                    // Extrae la parte después del contains"
                    String[] partes = linea.split("Cantidad de Cajas: ");
                    if (partes.length > 1) {
                        // Toma el valor y luego recorta
                        String cantidadCajasStr = partes[1].split(" ")[0].trim();
                        try {
                            cantCajas = Integer.parseInt(cantidadCajasStr);
                            System.err.println(cantCajas);
                            encontrado = true;
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Error al leer la cantidad de cajas en el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "No se encontró la cantidad de cajas en el archivo de configuración.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(null, "Configuración existente:\n" + configuracion.toString(),
                    "Configuración Cargada", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la configuración: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configurarBanco(File archivo) {
        try {
            String nombreBanco = JOptionPane.showInputDialog(null, "Ingrese el nombre del banco: ", "Configuracion del banco", JOptionPane.QUESTION_MESSAGE);
            if (nombreBanco == null || nombreBanco.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nombre del banco no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String cantidadCajas
                    = JOptionPane.showInputDialog(null, "Ingrese la cantidad de cajas: ", "Configuracion del banco", JOptionPane.QUESTION_MESSAGE);
            if (cantidadCajas == null || cantidadCajas.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "La cantidad de cajas no puede estar vacia", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                cantCajas = Integer.parseInt(cantidadCajas);
                if (cantCajas <= 0) {
                    throw new NumberFormatException();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero valido para la cantidad de cajas", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(null,
                    "Configurando las cajas del banco...\nDebe existir al menos una preferencial y una rapida.",
                    "Configuracion de Cajas", JOptionPane.INFORMATION_MESSAGE);

            try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
                writer.print("Nombre del Banco: " + nombreBanco);
                writer.print(" Cantidad de Cajas: " + (cantCajas));
                writer.print(" Caja Preferencial Creada: SI ");
                writer.print(" Caja Rapida Creada: SI ");

                JOptionPane.showMessageDialog(null,
                        "Configuracion guardada exitosamente" + CONFIG_FILE,
                        "Exito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la configuracion: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void crearCajas() {
        if (cantCajas > 0) {
            cajas = new sistemaTiquetes[cantCajas];
            for (int i = 0; i < cantCajas; i++) {
                cajas[i] = new sistemaTiquetes();
                JOptionPane.showMessageDialog(null, "Caja" + (i + 1) + " abierta");
            }

            cajaPreferencial = new sistemaTiquetes();
            JOptionPane.showMessageDialog(null, "Caja Preferencial abierta");

            cajaRapida = new sistemaTiquetes();
            JOptionPane.showMessageDialog(null, "Caja Rapida abierta");
        } else {
            JOptionPane.showMessageDialog(null, "Cantidad de Cajas no válida en el archivo de configuración.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarArchivoConfiguracion() {
        File archivo = new File(CONFIG_FILE);

        if (archivo.exists()) {
            if (archivo.delete()) {
                JOptionPane.showMessageDialog(null,
                        "El archivo de configuración ha sido eliminado exitosamente.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "No se pudo eliminar el archivo de configuración.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "El archivo de configuración no existe.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
}
