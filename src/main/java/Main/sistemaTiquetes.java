/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Marcos
 */
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
