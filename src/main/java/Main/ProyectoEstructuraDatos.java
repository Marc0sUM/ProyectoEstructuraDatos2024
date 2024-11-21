/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Main;

import javax.swing.JOptionPane;
import java.time.LocalTime;


public class ProyectoEstructuraDatos {

    public static void main(String[] args) {

        LocalTime horaActual = LocalTime.now();
        int cantTiquetes = 0;
        int num=0;
        sistemaTiquetes Caja = new sistemaTiquetes();
        cantTiquetes = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de tiquetes a generar: "));

        while (num != cantTiquetes){
        
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente");
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del cliente: "));
            int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del cliente: "));
            String tramite = JOptionPane.showInputDialog("Ingrese el tramite a realizar(Deposito, Retiro, Cambio de divisas): ").toLowerCase();
            String tipo = JOptionPane.showInputDialog("Ingrese el tipo(P:Preferencial, A:Un solo tramite, B:Dos o mas tramites): ").toUpperCase();
            String horaCreacion = "" + horaActual;
            Persona cliente = new Persona(nombre, id, edad, tramite, tipo, horaCreacion, edad);
            Caja.push(cliente);
            num++;
        }
        
        System.out.println(Caja);
        System.out.println(Caja.encuentra(3, Caja));
        System.out.println(Caja);
    }
}
