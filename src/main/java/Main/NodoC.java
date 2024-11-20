/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Marcos
 */
public class NodoC {
    private Persona elementoP;
    private NodoC atras;

    public NodoC() {
        this.elementoP = null;
        this.atras = null;
    }

    public Persona getElementoC() {
        return elementoP;
    }
    
    public Persona getElementoP() {
        return elementoP;
    }

    public void setElementoP(Persona elementoP) {
        this.elementoP = elementoP;
    }

    public NodoC getAtras() {
        return atras;
    }

    public void setAtras(NodoC atras) {
        this.atras = atras;
    }
    
       @Override
   public String toString() {
        return "Nombre: "+this.getElementoP().getNombre() 
                +" Tipo: "+this.getElementoP().getTipo()
                +" Id: "+this.getElementoP().getId()
                +" Edad: "+this.getElementoP().getEdad();
    }  
    
}
