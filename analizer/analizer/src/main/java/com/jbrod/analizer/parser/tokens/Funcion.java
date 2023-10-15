
package com.jbrod.analizer.parser.tokens;

import java.util.LinkedList;

/**
 *
 * @author Jorge
 */
public class Funcion extends CodeBlock { 

    LinkedList<String> params; //parametros que llevan dentro las funciones
    
    public Funcion(LinkedList <String> params, String sentencia, int fila, int columna, String tipo, int posicionFinalLista) {
        super(sentencia,fila,columna,tipo, posicionFinalLista);
        params = new LinkedList<>();
        this.params = params; 
    }
    
    

}
