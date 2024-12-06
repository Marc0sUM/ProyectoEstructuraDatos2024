/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Marcos
 * 
 */
//------//
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
    
    public Persona extraer(int id, sistemaTiquetes cola){
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
    public Persona encuentra(int id, sistemaTiquetes cola){
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
