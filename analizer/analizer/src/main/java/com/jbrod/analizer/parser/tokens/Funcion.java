
package com.jbrod.analizer.parser.tokens;

import java.util.LinkedList;

/**
 *
 * @author Jorge
 */
public class Funcion extends CodeBlock { 

    LinkedList<String> params; //parametros que llevan dentro las funciones
    
    public Funcion(LinkedList <String> params) {
        super(null, 0, 0, null, 0);
        this.params = params; 
    }
    
    

}
