
package com.jbrod.analizer.parser.automatas;

import com.jbrod.analizer.parser.Parser;
import com.jbrod.analizer.parser.tokens.SyntaxToken;
import java.util.LinkedList;

/**
 *
 * @author Jorge
 */
public class VariablesAsignacion {

    
    private Parser parser; 
    private LinkedList<SyntaxToken> TokenSyntaxList;  

    /**
     * Construye un objeto con una colección de autómatas para detectar variables y asignaciones.
     * @param TokenSyntaxList: Recibe la lista de tokens de sintaxis global de la clase maestra.
     **/
    public VariablesAsignacion(Parser parser, LinkedList<SyntaxToken> TokenSyntaxList) {
        this.parser = parser;
        this.TokenSyntaxList = TokenSyntaxList;
    }
    
    /*Estructura: identificador operador_asignacion expresión

    variable = identificador
    op asign  =   “ = ”  | “ += ” | “ -= ” |  “ **= ” | “ /= ” | “ //= ” | “ %= ” | “ *= ”

    asignación = identificador  { “ , ”  identificador }  op asign   expresión { “ , “ expresión }
    # comprobar  -> al menos la misma cantidad de expresiones que identificadores*/

    
    
    
}
