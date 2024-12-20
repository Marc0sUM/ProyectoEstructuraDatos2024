/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.time.LocalTime;
import java.io.*;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
public class sistemaTiquetes {

    private NodoC primero;
    private NodoC ultimo;

    public boolean isEmpty() {
        return primero == null;
    }

    public void push(Persona p) {
        NodoC nuevo = new NodoC();
        nuevo.setElementoP(p);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setAtras(nuevo);
            ultimo = nuevo;
        }
    }

    public NodoC pop() {
        NodoC aux = primero;
        if (primero != null) {
            primero = primero.getAtras();
            aux.setAtras(null);
        }
        return aux;
    }

    //Revisar cambiar estoy modificando la cola
    public Persona extraer(int id, sistemaTiquetes cola) {
        sistemaTiquetes colaAuxiliar = new sistemaTiquetes();
        NodoC eliminado = null;

        while (!cola.isEmpty()) {
            NodoC nodoActual = cola.pop();
            if (nodoActual.getElementoP().getId() == id) {
                eliminado = nodoActual;
            } else {
                colaAuxiliar.push(nodoActual.getElementoP());
            }
        }

        while (!colaAuxiliar.isEmpty()) {
            cola.push(colaAuxiliar.pop().getElementoP());
        }

        return eliminado.getElementoP();
    }

    public Persona encuentra(int id, sistemaTiquetes cola) {
        sistemaTiquetes colaAuxiliar = new sistemaTiquetes();
        NodoC encontrado = null;

        while (!cola.isEmpty()) {
            NodoC nodoActual = cola.pop();
            if (nodoActual.getElementoP().getId() == id) {
                encontrado = nodoActual;
            }
            colaAuxiliar.push(nodoActual.getElementoP());
        }

        while (!colaAuxiliar.isEmpty()) {
            cola.push(colaAuxiliar.pop().getElementoP());
        }

        return (encontrado != null) ? encontrado.getElementoP() : null;
    }
    
    public Persona modificarHora(sistemaTiquetes cola){
        sistemaTiquetes colaAux = new sistemaTiquetes();
        Persona primero = null;
        
        while(!cola.isEmpty()){
           NodoC nodoActual = cola.pop();
           if(primero == null){
               primero = nodoActual.getElementoP();
               LocalTime horaActual = LocalTime.now();
               String horaFormateada = horaActual.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
               primero.setHoraAtencion(horaFormateada);
           }
           colaAux.push(nodoActual.getElementoP());
        }
        while(!colaAux.isEmpty()){
            cola.push(colaAux.pop().getElementoP());
        }
        return primero;
    }
    
    public void guardarAtendidos(sistemaTiquetes cola, String ArchivoName){
        sistemaTiquetes colaAux = new  sistemaTiquetes();
        Persona primero = null;
        
        while(!cola.isEmpty()){
            NodoC nodoActual = cola.pop();
            
            if(primero == null){
                primero = nodoActual.getElementoP();
            }
            colaAux.push(nodoActual.getElementoP());
        }
        while(!colaAux.isEmpty()){
            cola.push(colaAux.pop().getElementoP());
        }
        
        if(primero != null){
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(ArchivoName, true))){
                writer.write(primero.toString());
                writer.newLine();
            }catch(IOException e){
                System.err.println("Error al escribir el file: "+ e.getMessage());
            }
        }else{
            System.out.println("La cola esta vacia, nada por guardar");
        }
    }

    public int tamannoCola(sistemaTiquetes cola) {
        int contador = 0;
        NodoC actual = primero;
        while (actual != null) {
            contador++;
            actual = actual.getAtras();
        }
        return contador;
    }

    public String toString() {
        String s = "";
        NodoC aux = primero;
        while (aux != null) {
            s += aux + "\n";
            aux = aux.getAtras();
        }
        return s;
    }
}
