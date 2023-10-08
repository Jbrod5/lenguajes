
package com.jbrod.analizer.lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

    public static void main(String[] args) throws IOException {
        //String flex = "C:/Users/Jorge/OneDrive/Documents/NetBeansProjects/Lenguajes/parser-py/analizer/analizer/src/main/java/com/jbrod/analizer/lexer/Lexer.flex";
        
        String test = "def if + \"cadena \" : ( 4 + 5 )\n" +
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
                        "\" mi cadena que no cierra";
        
        String ruta = "";
        
        Reader stringReader = new StringReader(test);
        Tokenizer lexer = new Tokenizer(stringReader);
        
        Token token;
       
        
        
        //try {
        
            
            for (int i = 0; i < 15; i++) {
                System.out.println("Todo bien: " +  " | n: " + i);
                token = lexer.yylex();
                System.out.println("Lex: " + token.getLexeme() + " | Type: " + token.getTokenType() + " |");
                System.out.println("");
            
            }
            
            /*int i = 0; 
            while(true){
                i++;
                System.out.println("Todo bien: " + lexer.yylex() + " | n: " + i);
                token = lexer.yylex();
                System.out.println("Lex: " + token.getLexeme() + " | Type: " + token.getTokenType() + " |");
                System.out.println("");
                if(token == null ){
                    System.out.println("encontro nulo");
                    break;
                }
            }*/
            
            
           /* Reader rd = new BufferedReader(new FileReader(ruta));
            Lexer lex = new Lexer(rd);
            Token tk; 
            while(true){
                tk = lex.yylex();*/
                
            }
            
            
        /*} catch (IOException ex) {
            Logger.getLogger(Lexergen.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Algo salio mal");
        }
        
        int kk = 4;
        
    }*/
}
