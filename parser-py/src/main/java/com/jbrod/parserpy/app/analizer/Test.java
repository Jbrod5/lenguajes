
package com.jbrod.parserpy.app.analizer;

import com.jbrod.parserpy.app.analizer.lexicon.Analizer;
import com.jbrod.parserpy.app.analizer.lexicon.Token;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class Test {
    
    public static void main(String[] args) {
        
        String prueba = ", . ( ) **= += %= = 10 31.2f 20.2  20.0.36.6 \n\"esto es un texto\' int double    \ndef class enum INT \n + - *   \nand  = True false $dentifie 1ero"
                + " \n"
                + "#Esto es un comentario \"siguesiendocomentariooo + -'";
        System.out.println(prueba);
        
        Analizer an = new Analizer();
        
        an.searchMatches(prueba);
        
        List<Token> lst = an.getListToken();
        for (Token token : lst) {
            System.out.println("columna: " + token.getColumn() + 
                    " | fila: " + token.getRow() +  
                    " | tokenType: " + token.getTokenType() + 
                    " | patron: " + token.getPattern() + 
                    " | lexema: " + token.getLexeme());
        }
        
        an.generateAnalysisReport();
        
        
    }

}
