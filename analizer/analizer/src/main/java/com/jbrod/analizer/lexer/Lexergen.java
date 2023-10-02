
package com.jbrod.analizer.lexer;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import jflex.anttask.JFlexTask;

/**
 *
 * @author Jorge
 */
public class Lexergen {

    public static void main(String[] args) {
        String flex = "C:/Users/Jorge/OneDrive/Documents/NetBeansProjects/Lenguajes/parser-py/analizer/analizer/src/main/java/com/jbrod/analizer/lexer/Lexer.flex";
        /*
        String test = "def if \"cadena \" : ( 4 + 5 )\n" +
                        "mi_id_\n" +
                        "#comentario \n" +
                        "while\n" +
                        "for\n" +
                        ">=\n" +
                        "<\n" +
                        "'cadena en comillas simples'\n" +
                        "\n" +
                        "#un error se puede presentar como la siguiente linea\n" +
                        "\n" +
                        "\" mi cadena que no cierra";*/
        
        String test = "id";
        
        Reader stringReader = new StringReader(test);
        Lexer lexer = new Lexer(stringReader);
        
        Token token;
        
        try {
            System.out.println("Todo bien: " + lexer.yylex());
           token = lexer.yylex();
            
        } catch (IOException ex) {
            Logger.getLogger(Lexergen.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Algo salio mal");
        }
        
        int kk = 4;
        
    }
}
