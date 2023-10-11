
package com.jbrod.analizer.lexer;

import com.jbrod.analizer.parser.automatas.Recurrentes;
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
        
        
        System.out.println("Prueba rango: ");
        
        
        pruebaRange("range(20)");
        pruebaRange("range(20,2)");
        pruebaRange("range(2,3,4)");
        pruebaRange("x");
        System.out.println(""); System.out.println(""); System.out.println(""); System.out.println(""); 
        
        
        pruebaArreglo("[]");
        pruebaArreglo("[1]");
        pruebaArreglo("[2,3,4]");
        pruebaArreglo("f");
        
    }
    
    private static void pruebaRange(String prueba) throws IOException{
        Recurrentes r = new Recurrentes();
        Lexer lexer = new  Lexer();
        
        lexer.Analize(prueba);
        System.out.println("Prueba rango: " + prueba + r.rango(lexer.getTokenList(), 0));
        
    }
    
    private static void pruebaArreglo(String prueba) throws IOException{
        Recurrentes r = new Recurrentes();
        Lexer lexer = new Lexer(); 
        
        lexer.Analize(prueba);
        System.out.println("Prueba Arreglo: " + prueba + r.array(lexer.getTokenList(), 0));
        
    }
        
        
        /*//String flex = "C:/Users/Jorge/OneDrive/Documents/NetBeansProjects/Lenguajes/parser-py/analizer/analizer/src/main/java/com/jbrod/analizer/lexer/Lexer.flex";
        
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

